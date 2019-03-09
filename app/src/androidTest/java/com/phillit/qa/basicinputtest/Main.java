package com.phillit.qa.basicinputtest;

import android.content.Context;
import android.os.RemoteException;
import android.support.test.InstrumentationRegistry;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.util.Log;
import com.phillit.qa.basicinputtest.Common.Device;
import com.phillit.qa.basicinputtest.Common.KeyType.KeyType;
import com.phillit.qa.basicinputtest.Common.TestCaseParser;

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
    private static String runTime = "", totalRunTime = "", testingVersion = "";
    private static TestCaseParser testPlan;
    private UiDevice uiDevice;
    private Context context;
    private Device device;

    @Before
    public void ReadyTest() throws IOException, UiObjectNotFoundException {
        initTest();
    }

    @Test
    public void Test(){

    }

    private void initTest() throws IOException, UiObjectNotFoundException {
        context = InstrumentationRegistry.getTargetContext();
        uiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        device = new Device(uiDevice, context);
        testPlan = device.setTestPlan();
        device.Device_Precondition();
        totalRunTime += "=================" + "Total RunTime" + "=================\n";
        totalRunTime += device.TotalRunTimeCheck("START");
    }

    @After
    public void FinishTest() throws UiObjectNotFoundException, RemoteException, IOException {
        device.getUiDevice().setOrientationNatural();
        device.userWait(10000);
        totalRunTime += device.TotalRunTimeCheck("END");
        totalRunTime += "- Test Device : " + device.getDeviceModelName() + "\n";
        totalRunTime += "- Test Version : " + device.getAKeyboardVersion();

        device.sendReport(runTime, totalRunTime);
        device.userWait(10000);

        // Device Power Off
        device.PowerOff();
    }
}