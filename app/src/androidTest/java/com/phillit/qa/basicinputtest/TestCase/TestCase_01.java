package com.phillit.qa.basicinputtest.TestCase;

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

    public TestCase_01(Utility device, String testType) {
        this.device = device;
        this.testType = testType;
    }

    public void start() throws IOException {
        ReadyTest();
        Test();
        FinishTest();
    }

    private void ReadyTest(){
        // 네이버 메모 실행
        device.launchApplication("네이버 메모");

        // 첫번째 메모 선택(메모를 작성하세요)
        device.touchObject("com.nhn.android.navermemo:id/memos_simple_writer_hint_view");

        // 3초 대기
        device.userWait(3000);
    }
    private void Test(){
        wordList = new TestCaseParser("kor").getWordList();
        KeyType Qwerty = new Qwerty(device, device.getContext(), KeyType.QWERTY_PORTRAIT, KeyType.QWERTY_KOREA);
        for(int i=0; i<wordList.size(); i++){
            device.inputMethod(wordList.get(i), Qwerty);
        }
    }

    private void FinishTest() throws IOException {
        String result = "";
        File file = new File("/sdcard/QA/InputTest/output_" + testType + ".txt");
        FileWriter writer = new FileWriter(file);
        try {
            result = device.getUiDevice().findObject(new UiSelector().resourceId("wm_editor_body")).getText();
            Log.i("@@@", "Result : " + result);
            writer.write(result);
        } catch (UiObjectNotFoundException e) {
            Log.i("@@@", "UI객체를 찾을 수 없음 : " + e.getLocalizedMessage());
            e.printStackTrace();
        }finally {
            writer.close();
        }
    }
}
