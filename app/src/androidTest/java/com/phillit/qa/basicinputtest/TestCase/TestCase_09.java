package com.phillit.qa.basicinputtest.TestCase;

import android.os.RemoteException;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import android.util.Log;

import com.phillit.qa.basicinputtest.Common.Configuration.Configuration;
import com.phillit.qa.basicinputtest.Common.KeyType.Naragul;
import com.phillit.qa.basicinputtest.Common.KeyType.SKY;
import com.phillit.qa.basicinputtest.Common.TestCaseParser;
import com.phillit.qa.basicinputtest.Common.KeyType.KeyType;
import com.phillit.qa.basicinputtest.Common.Device;
import java.io.IOException;

/**
 * 테스트 명   : TestCase_09
 * 테스트 목적 : 세로모드상태에서 나랏글의 입력을 검증한다.
 * 테스트 순서 :
 1. Monkey Input 실행
 2. 세로모드
 3. /sdcard/QA/InputTest/TestWord.xls의 한글단어를 순차적으로 입력
 */

public class TestCase_09 {
    String testType = "";
    String runTime;
    String word;
    Device device;
    KeyType Naragul;
    TestCaseParser parser;
    boolean isInternalTest = false;
    int saveCnt = 1000;

    public TestCase_09(Device device, String testType) {
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

    private void ReadyTest() throws RemoteException, UiObjectNotFoundException {
        runTime += device.RunTimeCheck("START");
        // 스카이 키타입으로 변경
        device.changeKeyType(KeyType.NARAGUL);

        // Parser, KeyType init
        parser = new TestCaseParser("kor", device.getContext());

        Naragul = new Naragul(device, device.getContext(), KeyType.PORTRAIT, KeyType.NARAGUL);

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
        device.changeKeyboardLanguage(KeyType.QWERTY_ENGLISH);

        // 세로모드
        // 10초 대기
        device.getUiDevice().setOrientationNatural();
        device.userWait(10000);
    }

    private void Test() throws IOException, RemoteException {
        int i=1;
        while(true){
            word = parser.getWord(i).toString();
            if(word == null){
                break;
            }else{
                device.inputMethod(word, Naragul);
            }
            if(i % saveCnt == 0){
                device.touchObject("com.phillit.qa.monkeyinput:id/btn_save");
                device.dumpsysMemifo(testType + "_meminfo");
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

    private void FinishTest(){
        // 언어변경(영어)
        device.changeKeyboardLanguage(KeyType.NARAGUL);
        device.userWait(5000);

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