package com.phillit.qa.basicinputtest;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.uiautomator.UiDevice;

import com.phillit.qa.basicinputtest.Common.Utility;
import com.phillit.qa.basicinputtest.TestCase.TestCase_01;

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
    public void Test() throws IOException {
        new TestCase_01(device,"KOR_PORTRAIT").start();
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

