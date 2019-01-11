package com.phillit.qa.basicinputtest;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.os.RemoteException;
import android.support.test.InstrumentationRegistry;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import android.util.Log;

import com.phillit.qa.basicinputtest.Common.Key;
import com.phillit.qa.basicinputtest.Common.KeyType.KeyType;
import com.phillit.qa.basicinputtest.Common.KeyType.Qwerty;
import com.phillit.qa.basicinputtest.Common.Utility;
import com.phillit.qa.basicinputtest.Common.XmlParserManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.xmlpull.v1.XmlPullParserException;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

public class Main {
    private UiDevice uiDevice;
    private Context context;
    private Utility device;
    private Qwerty keyType_QWERTY;

    @Before
    public void ReadyTest(){
        initTest();
    }

    @Test
    public void Test() {
        String exptectedResult = "정신분열병 환자를 위한 사회";
        String result = "";

        for(int i=0; i<500; i++){
            boolean isEquals = false;

            try {
                // query창
                device.getUiDevice().click(200, 300);
                //device.getUiDevice().findObject(new UiSelector().resourceId("query")).click();
                device.userWait(500);
                inputIssue();
                device.userWait(200);
                result =  device.getUiDevice().findObject(new UiSelector().resourceId("query")).getText();
            } catch (UiObjectNotFoundException e) {
                e.printStackTrace();
                Log.i("QA_TEST", "Not Found Exception");
            }

            isEquals = exptectedResult.equals(result);

            Log.i("QA_TEST", "=============================================");
            Log.i("QA_TEST", "Count : " + i);
            Log.i("QA_TEST", "input Text : "+ result);
            Log.i("QA_TEST", "isEquals : " + isEquals);

            //uiDevice.findObject(new UiSelector().resourceId("clear_input")).click();
            for(int j=0; j<30; j++){
                device.getUiDevice().click(940, 1400);
            }
            device.userWait(300);
        }
    }

    public void inputIssue(){
        // 정신분열병 환자를 위한 사회

        // ㅈ
        device.getUiDevice().click(680, 1745);
        // .
        device.getUiDevice().click(410, 1400);
        // ㅣ
        device.getUiDevice().click(140, 1406);
        // ㅇ
        device.getUiDevice().click(410, 1930);


        //ㅅ
        device.getUiDevice().click(400, 1760);
        // ㅣ
        device.getUiDevice().click(140, 1406);
        // ㄴ
        device.getUiDevice().click(410, 1575);


        // ㅂ
        device.getUiDevice().click(135, 1750);
        // ㅜ
        device.getUiDevice().click(670, 1400);
        device.getUiDevice().click(410, 1400);
        //ㄴ
        device.getUiDevice().click(410, 1575);
        // ㅇ
        device.getUiDevice().click(410, 1930);
        // ㅕ
        device.getUiDevice().click(410, 1400);
        device.getUiDevice().click(410, 1400);
        device.getUiDevice().click(140, 1400);
        // ㄹ
        device.getUiDevice().click(400, 1580);
        device.getUiDevice().click(400, 1580);
        // ㅂ
        device.getUiDevice().click(135, 1750);
        // ㅕ
        device.getUiDevice().click(410, 1400);
        device.getUiDevice().click(410, 1400);
        device.getUiDevice().click(140, 1400);
        // ㅇ
        device.getUiDevice().click(410, 1930);

        // Spacebar
        device.getUiDevice().click(660, 1930);
        // Spacebar
        device.getUiDevice().click(660, 1930);


        // ㅎ
        device.getUiDevice().click(400, 1760);
        device.getUiDevice().click(400, 1760);
        // .
        device.getUiDevice().click(410, 1400);
        // ㅡ
        device.getUiDevice().click(670, 1400);
        // ㅏ
        device.getUiDevice().click(140, 1400);
        device.getUiDevice().click(410, 1400);
        // ㄴ
        device.getUiDevice().click(410, 1585);
        // ㅈ
        device.getUiDevice().click(670, 1760);
        // ㅏ
        device.getUiDevice().click(135, 1400);
        device.getUiDevice().click(410, 1400);
        // ㄹ
        device.getUiDevice().click(410, 1575);
        device.getUiDevice().click(410, 1575);
        // ㅡ
        device.getUiDevice().click(670, 1400);
        // ㄹ
        device.getUiDevice().click(410, 1575);
        device.getUiDevice().click(410, 1575);

        // Spacebar
        device.getUiDevice().click(660, 1930);
        // Spacebar
        device.getUiDevice().click(660, 1930);

        // ㅇ
        device.getUiDevice().click(400, 1930);
        // ㅜ
        device.getUiDevice().click(650, 1400);
        // ㅣ
        device.getUiDevice().click(410, 1400);
        device.getUiDevice().click(140, 1400);
        // ㅎ
        device.getUiDevice().click(410, 1750);
        device.getUiDevice().click(410, 1750);
        // ㅏ
        device.getUiDevice().click(140, 1400);
        device.getUiDevice().click(410, 1400);
        // ㄴ
        device.getUiDevice().click(410, 1575);

        // Spacebar
        device.getUiDevice().click(660, 1930);
        // Spacebar
        device.getUiDevice().click(660, 1930);

        // ㅅ
        device.getUiDevice().click(410, 1750);
        // ㅏ
        device.getUiDevice().click(140, 1400);
        device.getUiDevice().click(410, 1400);
        // ㅎ
        device.getUiDevice().click(410, 1750);
        device.getUiDevice().click(410, 1750);
        // ㅗ
        device.getUiDevice().click(410, 1400);
        device.getUiDevice().click(680, 1400);
        //ㅣ
        device.getUiDevice().click(140, 1400);
    }

    @After
    public void FinishTest(){


    }
    private void initTest(){
        context = InstrumentationRegistry.getTargetContext();
        uiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        device = new Utility(uiDevice, context);
        keyType_QWERTY =  new Qwerty(uiDevice, context, KeyType.QWERTY_PORTRAIT);
    }
}

