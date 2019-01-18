package com.phillit.qa.basicinputtest.TestCase;

import android.os.RemoteException;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import android.util.Log;
import com.phillit.qa.basicinputtest.Common.TestCaseParser;
import com.phillit.qa.basicinputtest.Common.KeyType.KeyType;
import com.phillit.qa.basicinputtest.Common.KeyType.Qwerty;
import com.phillit.qa.basicinputtest.Common.Utility;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 테스트 명   : TestCase_01
 * 테스트 목적 : 세로모드상태에서 한글QWERTY의 입력을 검증한다.
 * 테스트 순서 :
   1. 네이버 메모 실행
   2. 새메모 선택
   3. 3초 대기
   4. /sdcard/QA/InputTest/TestWord.xls의 한글단어를 순차적으로 입력
 */

public class TestCase_01 {
    ArrayList<String> wordList;
    String testType = "";
    Utility device;
    int failCount, wordCnt = 0;
    UiSelector editText, btnSave;
    KeyType Qwerty_kor, Qwerty_eng;

    public TestCase_01(Utility device, String testType) {
        this.device = device;
        this.testType = testType;
    }

    public void start() throws IOException, RemoteException, UiObjectNotFoundException {
        ReadyTest();
        Test();
        FinishTest();
    }

    private void ReadyTest() throws RemoteException, UiObjectNotFoundException {
        // KeyType, WordList Load
        editText = new UiSelector().resourceId("com.phillit.qa.monkeyinput:id/edt_input");
        btnSave = new UiSelector().resourceId("com.phillit.qa.monkeyinput:id/btn_start");
        wordList = new TestCaseParser("kor").getWordList();
        wordCnt = wordList.size();
        Qwerty_eng = new Qwerty(device, device.getContext(), KeyType.QWERTY_PORTRAIT, KeyType.QWERTY_ENGLISH);
        Qwerty_kor = new Qwerty(device, device.getContext(), KeyType.QWERTY_PORTRAIT, KeyType.QWERTY_KOREA);

        // Monkey Input 실행
        device.launchApplication("Monkey Input");

        // 파일이름 터치
        device.touchObject("edt_filename");
        device.inputMethod(testType, Qwerty_eng);

        // 입력필드 터치
        device.getUiDevice().findObject(editText).click();

        // 언어변경(한글)
        device.userWait(1500);
        device.getUiDevice().click(205, 1690);

        // 세로모드
        // 3초 대기
        device.getUiDevice().setOrientationNatural();
        device.userWait(3000);

    }
    private void Test(){
        for(int i=0; i < wordCnt; i++){
            String temp = wordList.get(0);
            device.inputMethod(temp, Qwerty_kor);
            wordList.remove(0);
            Log.i("@@@", "Word Count : " + i + " / Input Word : " + temp);
        }

        // 10초 대기
        device.userWait(10000);
    }

    private void FinishTest() throws IOException {

        // SAVE 버튼
        device.touchObject("com.phillit.qa.monkeyinput:id/btn_start");


        /**
        String result = "";
        File file = new File("/sdcard/QA/InputTest/output_" + testType + ".txt");
        FileWriter writer = new FileWriter(file);
        try {
            result = device.getUiDevice().findObject(new UiSelector().resourceId("com.akeyboard.monkeytest:id/edt_test")).getText();
            writer.write(result);
        } catch (UiObjectNotFoundException e) {
            Log.i("@@@", "UI객체를 찾을 수 없음 : " + e.getLocalizedMessage());
            device.getUiDevice().takeScreenshot(new File("/sdcard/QA/InputTest/Fail_" + failCount + ".png"));
            failCount++;
            e.printStackTrace();
        }finally {
            writer.close();
        }
         **/
    }
}
