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
 * 테스트 목적 : 가로모드상태에서 한글 QWERTY의 입력을 검증한다.
 * 테스트 순서 :
 1. 네이버 메모 실행
 2. 새메모 선택
 3. 3초 대기
 4. /sdcard/QA/InputTest/TestWord.xls의 한글단어를 순차적으로 입력
 */

public class TestCase_02 {
    ArrayList<String> wordList;
    String testType = "";
    Utility device;

    public TestCase_02(Utility device, String testType) {
        this.device = device;
        this.testType = testType;
    }

    public void start() throws IOException, RemoteException {
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
    private void Test() throws RemoteException {
        // 한글 단어 Load
        wordList = new TestCaseParser("kor").getWordList();
        // 한글 가로모드 키배열 Load
        KeyType Qwerty = new Qwerty(device, device.getContext(), KeyType.QWERTY_LANDSCAPE, KeyType.QWERTY_KOREA);

        // 가로모드
        device.getUiDevice().setOrientationLeft();
        // 0.5s 대기

        device.userWait(500);
        for(int i=0; i<wordList.size(); i++){
            device.inputMethod(wordList.get(i), Qwerty);
        }
        // 세로모드
        device.getUiDevice().setOrientationNatural();
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
            device.getUiDevice().pressBack();
            device.getUiDevice().pressBack();
            device.getUiDevice().pressBack();
            device.getUiDevice().pressHome();
        }
    }
}
