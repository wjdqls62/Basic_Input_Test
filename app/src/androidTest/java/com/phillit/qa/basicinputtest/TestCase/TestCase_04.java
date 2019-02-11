package com.phillit.qa.basicinputtest.TestCase;

import android.os.RemoteException;
import android.support.test.uiautomator.UiObjectNotFoundException;

import com.phillit.qa.basicinputtest.Common.TestCaseParser;
import com.phillit.qa.basicinputtest.Common.KeyType.KeyType;
import com.phillit.qa.basicinputtest.Common.KeyType.Qwerty;
import com.phillit.qa.basicinputtest.Common.Utility;
import java.io.IOException;

/**
 * 테스트 명   : TestCase_04
 * 테스트 목적 : 가로모드상태에서 영문QWERTY의 입력을 검증한다.
 * 테스트 순서 :
 1. Monkey Input 실행
 2. 가로모드
 3. /sdcard/QA/InputTest/TestWord.xls의 한글단어를 순차적으로 입력
 */

public class TestCase_04 {
    String testType = "";
    StringBuffer word;
    Utility device;
    KeyType Qwerty_eng;
    TestCaseParser parser;
    boolean isInternalTest = false;
    int saveCnt = 1000;

    public TestCase_04(Utility device, String testType) {
        this.device = device;
        this.testType = testType;
        isInternalTest = device.getTestPlan().isInternalTest;
        if(isInternalTest){
            saveCnt = 10;
        }
    }

    public void start() throws IOException, RemoteException, UiObjectNotFoundException {
        ReadyTest();
        Test();
        FinishTest();
    }

    private void ReadyTest() throws RemoteException, UiObjectNotFoundException {
        // 천지인 키타입으로 변경
        device.changeKeyType(KeyType.QWERTY_ENGLISH);

        // Parser, KeyType init
        parser = new TestCaseParser("eng", device.getContext());

        Qwerty_eng = new Qwerty(device, device.getContext(), KeyType.PORTRAIT, KeyType.QWERTY_ENGLISH);

        // Monkey Input 실행
        device.launchApplication("Monkey Input");

        // 파일이름 터치
        device.touchObject("edt_filename");
        device.inputMethod(testType, Qwerty_eng);

        // 입력필드 터치
        device.touchObject("com.phillit.qa.monkeyinput:id/edt_input");

        // 가로모드 후 가로모드배열 Reload
        // 10초 대기
        device.getUiDevice().setOrientationLeft();
        Qwerty_eng = new Qwerty(device, device.getContext(), KeyType.LANDSCAPE, KeyType.QWERTY_ENGLISH);
        device.userWait(10000);

    }
    private void Test() throws IOException {
        int i=1;
        while(true){
            word = parser.getWord(i);
            if(word == null){
                break;
            }else{
                device.inputMethod(word, Qwerty_eng);
            }
            if(i % saveCnt == 0){
                // 가로모드 상태에서 SAVE버튼이 보이지 않아 Back버튼 1회 터치한다
                device.getUiDevice().pressBack();
                device.userWait(5000);

                device.touchObject("com.phillit.qa.monkeyinput:id/btn_save");
                device.dumpsysMemifo(testType + "_meminfo");

                // 입력필드 터치
                device.touchObject("com.phillit.qa.monkeyinput:id/edt_input");
                device.userWait(5000);

                if(device.getBatteryStatus() <= device.BATTERY_MIN_VALUE){
                    device.chargeDevice();
                }
            }
            if(isInternalTest && i==100){
                break;
            }
            //Log.i("@@@", "WordCnt : " + i + " / Word : " + word);
            i++;
        }
        // 10초 대기
        device.userWait(10000);
    }

    private void FinishTest() throws RemoteException {
        // 세로모드
        // 5초 대기
        device.getUiDevice().setOrientationNatural();
        device.userWait(5000);

        // 다음 테스트시 불필요한 객체 해제
        device.Release();
        // 홈화면 이동
        device.goToIdle();
        // 10초 대기
        device.userWait(10000);
    }
}
