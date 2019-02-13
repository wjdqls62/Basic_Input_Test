package com.phillit.qa.basicinputtest.TestCase;

import android.os.RemoteException;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.util.Log;
import com.phillit.qa.basicinputtest.Common.KeyType.SKY;
import com.phillit.qa.basicinputtest.Common.TestCaseParser;
import com.phillit.qa.basicinputtest.Common.KeyType.KeyType;
import com.phillit.qa.basicinputtest.Common.KeyType.Qwerty;
import com.phillit.qa.basicinputtest.Common.Utility;
import java.io.IOException;

/**
 * 테스트 명   : TestCase_06
 * 테스트 목적 : 가로모드상태에서 스카이의 입력을 검증한다.
 * 테스트 순서 :
 1. Monkey Input 실행
 2. 가로모드
 3. /sdcard/QA/InputTest/TestWord.xls의 한글단어를 순차적으로 입력
 */

public class TestCase_08 {
    String testType = "";
    String runTime;
    String word;
    Utility device;
    KeyType SKY, Qwerty_eng;
    TestCaseParser parser;
    boolean isInternalTest = false;
    int saveCnt = 1000;

    public TestCase_08(Utility device, String testType) {
        this.device = device;
        this.testType = testType;
        this.runTime = "=================" + testType + "=================\n";
        isInternalTest = device.getTestPlan().isInternalTest;
        if(isInternalTest){
            saveCnt = 10;
        }
    }

    public void start() throws IOException, RemoteException, UiObjectNotFoundException {
        Log.i("@@@", getClass().getName() + " start.");
        ReadyTest();
        Test();
        FinishTest();
    }

    private void ReadyTest() throws RemoteException, UiObjectNotFoundException{
        runTime += device.RunTimeCheck("START");
        // 스카이 키타입으로 변경
        device.changeKeyType(KeyType.SKY);

        // Parser, KeyType init
        parser = new TestCaseParser("kor", device.getContext());

        Qwerty_eng = new Qwerty(device, device.getContext(), KeyType.PORTRAIT, KeyType.QWERTY_ENGLISH);
        SKY = new SKY(device, device.getContext(), KeyType.LANDSCAPE, KeyType.SKY);

        // Monkey Input 실행
        device.launchApplication("Monkey Input");

        // 파일이름 터치
        device.touchObject("edt_filename");
        device.inputMethod(testType, Qwerty_eng);

        // 입력필드 터치
        device.touchObject("com.phillit.qa.monkeyinput:id/edt_input");
        device.userWait(3000);

        // 언어변경(한글)
        device.changeKeyboardLanguage(KeyType.SKY);

        // 가로모드
        // 10초 대기
        device.getUiDevice().setOrientationLeft();
        device.userWait(10000);

    }
    private void Test() throws IOException {
        int i=1;
        while(true){
            word = parser.getWord(i).toString();
            if(word == null){
                break;
            }else{
                device.inputMethod(word, SKY);
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
            Log.i("@@@", "WordCnt : " + i + " / Word : " + word);
            i++;
        }
        // 10초 대기
        device.userWait(10000);
    }

    private void FinishTest() throws RemoteException{
        // 세로모드
        // 5초 대기
        device.getUiDevice().setOrientationNatural();
        device.userWait(5000);

        // 언어변경(영어)
        device.changeKeyboardLanguage(KeyType.SKY);

        // 다음 테스트시 불필요한 객체 해제
        device.Release();
        // 홈화면 이동
        device.goToIdle();
        // 10초 대기
        device.userWait(10000);

        runTime += device.RunTimeCheck("END");
        Log.i("@@@", runTime);
    }

    public String getRunTime(){
        return runTime;
    }

}
