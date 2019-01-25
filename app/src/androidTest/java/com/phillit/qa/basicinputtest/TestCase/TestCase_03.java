package com.phillit.qa.basicinputtest.TestCase;

import android.os.RemoteException;
import android.util.Log;
import com.phillit.qa.basicinputtest.Common.TestCaseParser;
import com.phillit.qa.basicinputtest.Common.KeyType.KeyType;
import com.phillit.qa.basicinputtest.Common.KeyType.Qwerty;
import com.phillit.qa.basicinputtest.Common.Utility;
import java.io.IOException;

/**
 * 테스트 명   : TestCase_01
 * 테스트 목적 : 세로모드상태에서 한글QWERTY의 입력을 검증한다.
 * 테스트 순서 :
 1. 네이버 메모 실행
 2. 새메모 선택
 3. 3초 대기
 4. /sdcard/QA/InputTest/TestWord.xls의 한글단어를 순차적으로 입력
 */

public class TestCase_03 {
    String testType = "";
    StringBuffer word;
    Utility device;
    KeyType Qwerty_eng;
    TestCaseParser parser;

    public TestCase_03(Utility device, String testType) {
        this.device = device;
        this.testType = testType;
    }

    public void start() throws IOException, RemoteException {
        ReadyTest();
        Test();
        FinishTest();
    }

    private void ReadyTest() throws RemoteException {
        // Parser, KeyType init
        parser = new TestCaseParser("eng");

        Qwerty_eng = new Qwerty(device, device.getContext(), KeyType.QWERTY_PORTRAIT, KeyType.QWERTY_ENGLISH);

        // Monkey Input 실행
        device.launchApplication("Monkey Input");

        // 파일이름 터치
        device.touchObject("edt_filename");
        device.inputMethod(testType, Qwerty_eng);

        // 입력필드 터치
        device.touchObject("com.phillit.qa.monkeyinput:id/edt_input");

        // 세로모드
        // 3초 대기
        device.getUiDevice().setOrientationNatural();
        device.userWait(3000);
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
            if(i % 1000 == 0){
                device.touchObject("com.phillit.qa.monkeyinput:id/btn_save");
                device.dumpsysMemifo(testType + "_meminfo");
                device.userWait(5000);
            }
            Log.i("@@@", "WordCnt : " + i + " / Word : " + word);
            i++;
        }
        // 10초 대기
        device.userWait(10000);
    }

    private void FinishTest() {
        // 다음 테스트시 불필요한 객체 해제
        device.Release();
        // 홈화면 이동
        device.goToIdle();
        // 10초 대기
        device.userWait(10000);
    }
}