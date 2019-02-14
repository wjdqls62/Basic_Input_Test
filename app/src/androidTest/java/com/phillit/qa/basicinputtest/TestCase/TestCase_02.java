package com.phillit.qa.basicinputtest.TestCase;

import android.os.RemoteException;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import android.util.Log;

import com.phillit.qa.basicinputtest.Common.Configuration.Configuration;
import com.phillit.qa.basicinputtest.Common.TestCaseParser;
import com.phillit.qa.basicinputtest.Common.KeyType.KeyType;
import com.phillit.qa.basicinputtest.Common.KeyType.Qwerty;
import com.phillit.qa.basicinputtest.Common.Device;
import java.io.IOException;

/**
 * 테스트 명   : TestCase_02
 * 테스트 목적 : 가로모드상태에서 한글QWERTY의 입력을 검증한다.
 * 테스트 순서 :
 1. Monkey Input 실행
 2. 가로모드
 3. /sdcard/QA/InputTest/TestWord.xls의 한글단어를 순차적으로 입력
 */

public class TestCase_02 {
    String testType = "";
    String runTime;
    StringBuffer word;
    Device device;
    KeyType Qwerty_kor;
    TestCaseParser parser;
    boolean isInternalTest = false;
    int saveCnt = 1000;

    public TestCase_02(Device device, String testType) {
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
        // 천지인 키타입으로 변경
        device.changeKeyType(KeyType.QWERTY_KOREA);

        // Parser, KeyType init
        parser = new TestCaseParser("kor", device.getContext());

        Qwerty_kor = new Qwerty(device, device.getContext(), KeyType.LANDSCAPE, KeyType.QWERTY_KOREA);

        // Monkey Input 실행
        device.launchApplication("Monkey Input");

        // 파일이름(테스트 타입) 입력
        UiObject object = device.getUiDevice().findObject(new UiSelector().resourceId("com.phillit.qa.monkeyinput:id/edt_filename"));
        if(object.waitForExists(5000)){
            device.userWait(3000);
            object.setText(testType);
            device.userWait(3000);
        }

        // 입력필드 터치
        device.touchObject("com.phillit.qa.monkeyinput:id/edt_input");
        device.userWait(3000);

        // 언어변경(한글)
        device.changeKeyboardLanguage(KeyType.QWERTY_KOREA);

        // 가로모드
        // 10초 대기
        device.getUiDevice().setOrientationLeft();
        device.userWait(10000);
    }

    private void Test() throws IOException, RemoteException {
        int i=1;
        while(true){
            word = parser.getWord(i);
            if(word == null){
                break;
            }else{
                device.inputMethod(word, Qwerty_kor);
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

                if(device.getBatteryStatus() <= Configuration.BATTERY_MIN_VALUE){
                    device.chargeDevice();
                }
            }
            if(isInternalTest && i == Configuration.INTERNAL_TEST_COUNT){
                break;
            }
            Log.i("@@@", "WordCnt : " + i + " / Word : " + word);
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

        // 언어변경(영어)
        device.changeKeyboardLanguage(KeyType.QWERTY_KOREA);

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
