package com.phillit.qa.basicinputtest.TestCase;

import android.os.RemoteException;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import android.util.Log;
import com.phillit.qa.basicinputtest.Common.TestCaseParser;
import com.phillit.qa.basicinputtest.Common.KeyType.KeyType;
import com.phillit.qa.basicinputtest.Common.KeyType.Qwerty;
import com.phillit.qa.basicinputtest.Common.Utility;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 테스트 명   : TestCase_01
 * 테스트 목적 : 가로모드상태에서 영문QWERTY의 입력을 검증한다.
 * 테스트 순서 :
 1. 네이버 메모 실행
 2. 새메모 선택
 3. 3초 대기
 4. /sdcard/QA/InputTest/TestWord.xls의 단어를 순차적으로 입력
 */

public class TestCase_04 {
    ArrayList<String> wordList;
    String testType = "";
    Utility device;
    int failCnt = 0;

    public TestCase_04(Utility device, String testType) {
        this.device = device;
        this.testType = testType;
    }

    public void start() throws IOException, RemoteException {
        ReadyTest();
        Test();
        FinishTest();
    }

    private void ReadyTest() throws RemoteException {
        // Monkey Input 실행
        device.launchApplication("Monkey Input");

        // 텍스트필드 터치
        device.touchObject("com.akeyboard.monkeytest:id/edt_test");

        // 가로모드
        // 3초 대기
        device.getUiDevice().setOrientationLeft();
        device.userWait(3000);
    }
    private void Test() throws RemoteException {
        wordList = new TestCaseParser("eng").getWordList();
        KeyType Qwerty = new Qwerty(device, device.getContext(), KeyType.QWERTY_LANDSCAPE, KeyType.QWERTY_ENGLISH);

        for(int i=0; i<wordList.size(); i++){
            device.inputMethod(wordList.get(i), Qwerty);
            wordList.remove(i);
        }

        // 세로모드
        // 10초 대기
        device.getUiDevice().setOrientationNatural();
        device.userWait(10000);
    }

    private void FinishTest() throws IOException {
        String result = "";
        File file = new File("/sdcard/QA/InputTest/output_" + testType + ".txt");
        FileWriter writer = new FileWriter(file);
        try {
            result = device.getUiDevice().findObject(new UiSelector().resourceId("com.akeyboard.monkeytest:id/edt_test")).getText();
            Log.i("@@@", "Result : " + result);
            writer.write(result);
        } catch (UiObjectNotFoundException e) {
            device.getUiDevice().takeScreenshot(new File("/sdcard/QA/InputTest_Fail_" + failCnt + ".png"));
            Log.i("@@@", "UI객체를 찾을 수 없음 : " + e.getLocalizedMessage());
            e.printStackTrace();
        }finally {
            writer.close();
        }
        device.goToIdle();
    }
}
