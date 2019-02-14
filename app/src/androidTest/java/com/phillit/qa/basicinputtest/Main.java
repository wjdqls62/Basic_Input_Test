package com.phillit.qa.basicinputtest;

import android.content.Context;
import android.os.RemoteException;
import android.support.test.InstrumentationRegistry;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObjectNotFoundException;
import com.phillit.qa.basicinputtest.Common.Device;
import com.phillit.qa.basicinputtest.TestCase.TestCase_01;
import com.phillit.qa.basicinputtest.TestCase.TestCase_02;
import com.phillit.qa.basicinputtest.TestCase.TestCase_03;
import com.phillit.qa.basicinputtest.TestCase.TestCase_04;
import com.phillit.qa.basicinputtest.TestCase.TestCase_05;
import com.phillit.qa.basicinputtest.TestCase.TestCase_06;
import com.phillit.qa.basicinputtest.TestCase.TestCase_07;
import com.phillit.qa.basicinputtest.TestCase.TestCase_08;
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
    private Device device;
    private String runTime = "", totalRunTime = "";

    @Before
    public void ReadyTest() throws IOException, UiObjectNotFoundException {
        initTest();
    }

    @Test
    public void Test() throws IOException, RemoteException, UiObjectNotFoundException {

        // 한글, 세로모드 입력
        if(device.getTestPlan().KOR_QWERTY_PORTRAIT){
            TestCase_01 TC01 = new TestCase_01(device, "KOR_QWERTY_PORTRAIT");
            TC01.start();
            runTime += TC01.getRunTime();
        }

        // 한글, 가로모드 입력
        if(device.getTestPlan().KOR_QWERTY_LANDSCAPE){
            TestCase_02 TC02 = new TestCase_02(device, "KOR_QWERTYLANDSCAPE");
            TC02.start();
            runTime += TC02.getRunTime();
        }

        // 영문, 세로모드 입력
        if(device.getTestPlan().ENG_QWERTY_PORTRAIT){
            TestCase_03 TC03 = new TestCase_03(device, "ENG_QWERTY_PORTRAIT");
            TC03.start();
            runTime += TC03.getRunTime();
        }

        // 영문, 가로모드 입력
        if(device.getTestPlan().ENG_QWERTY_LANDSCAPE){
            TestCase_04 TC04 = new TestCase_04(device, "ENG_QWERTY_LANDSCAPE");
            TC04.start();
            runTime += TC04.getRunTime();
        }

        if(device.getTestPlan().KOR_CHUNJIIN_PORTRAIT){
            TestCase_05 TC05 = new TestCase_05(device, "CHUNJIIN_PORTRAIT");
            TC05.start();
            runTime += TC05.getRunTime();
        }

        if(device.getTestPlan().KOR_CHUNJIIN_LANDSCAPE){
            TestCase_06 TC06 = new TestCase_06(device, "CHUNJIIN_LANDSCAPE");
            TC06.start();
            runTime += TC06.getRunTime();
        }

        if(device.getTestPlan().KOR_SKY_PORTRAIT){
            TestCase_07 TC07 = new TestCase_07(device, "SKY_PORTRAIT");
            TC07.start();
            runTime += TC07.getRunTime();
        }

        if(device.getTestPlan().KOR_SKY_LANDSCAPE){
            TestCase_08 TC08 = new TestCase_08(device, "SKY_LANDSCAPE");
            TC08.start();
            runTime += TC08.getRunTime();
        }
    }

    private void initTest() throws IOException, UiObjectNotFoundException {
        context = InstrumentationRegistry.getTargetContext();
        uiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        device = new Device(uiDevice, context);
        device.setTestPlan();
        device.Device_Precondition();
        totalRunTime += "=================" + "Total RunTime" + "=================\n";
        totalRunTime += device.TotalRunTimeCheck("START");
    }

    @After
    public void FinishTest() throws UiObjectNotFoundException, RemoteException, IOException {
        device.getUiDevice().setOrientationNatural();
        device.userWait(10000);
        totalRunTime += device.TotalRunTimeCheck("END");

        device.sendReport(runTime, totalRunTime);
        device.userWait(10000);

        // Device Power Off
        device.PowerOff();
    }
}