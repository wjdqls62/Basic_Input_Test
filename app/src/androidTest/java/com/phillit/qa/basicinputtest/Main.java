package com.phillit.qa.basicinputtest;

import android.content.Context;
import android.os.RemoteException;
import android.support.test.InstrumentationRegistry;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import android.util.Log;

import com.phillit.qa.basicinputtest.Common.KeyType.KeyType;
import com.phillit.qa.basicinputtest.Common.KeyType.Qwerty;
import com.phillit.qa.basicinputtest.Common.Utility;
import com.phillit.qa.basicinputtest.TestCase.TestCase_01;
import com.phillit.qa.basicinputtest.TestCase.TestCase_02;
import com.phillit.qa.basicinputtest.TestCase.TestCase_03;
import com.phillit.qa.basicinputtest.TestCase.TestCase_04;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

public class Main {
    private UiDevice uiDevice;
    private Context context;
    private Utility device;


    @Before
    public void ReadyTest(){
        initTest();
    }

    @Test
    public void Test() throws IOException, RemoteException, UiObjectNotFoundException {

        // 한글, 세로모드 입력
        new TestCase_01(device,"KOR_PORTRAIT").start();

        // 한글, 가로모드 입력
        //new TestCase_02(device,"KOR_LANDSCAPE").start();

        // 영문, 세로모드 입력
        //new TestCase_03(device,"ENG_PORTRAIT").start();

        // 영문, 가로모드 입력
        //new TestCase_04(device,"ENG_LANDSCAPE").start();

    }

    @After
    public void FinishTest(){
    }

    private void initTest(){
        context = InstrumentationRegistry.getTargetContext();
        uiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        device = new Utility(uiDevice, context);
    }
}

