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
import com.phillit.qa.basicinputtest.TestCase.TestCase_01_KOR_QEWRTY_PORT;
import com.phillit.qa.basicinputtest.TestCase.TestCase_02_KOR_QWERTY_LAND;
import com.phillit.qa.basicinputtest.TestCase.TestCase_03_ENG_QWERTY_PORT;
import com.phillit.qa.basicinputtest.TestCase.TestCase_04_ENG_QWERTY_LAND;
import com.phillit.qa.basicinputtest.TestCase.TestCase_05_KOR_CHUNJIIN_PORT;
import com.phillit.qa.basicinputtest.TestCase.TestCase_06_KOR_CHUNJIIN_LAND;
import com.phillit.qa.basicinputtest.TestCase.TestCase_07_KOR_SKY_PORT;
import com.phillit.qa.basicinputtest.TestCase.TestCase_08_KOR_SKY_LAND;
import com.phillit.qa.basicinputtest.TestCase.TestCase_09_KOR_NARAGUL_PORT;
import com.phillit.qa.basicinputtest.TestCase.TestCase_10_KOR_NARAGUL_LAND;
import com.phillit.qa.basicinputtest.TestCase.TestCase_11_KOR_DANMOUM_PORT;
import com.phillit.qa.basicinputtest.TestCase.TestCase_12_KOR_DANMOUM_LAND;

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

        try{
            Log.i("@@@", "KOR_QWERTY_PORTRAIT - " + testPlan.getTestPlan(KeyType.KOR_QWERTY, KeyType.PORTRAIT));
            // 한글QWERTY(세로모드) 입력
            if(testPlan.getTestPlan(KeyType.KOR_QWERTY, KeyType.PORTRAIT)){
                TestCase_01_KOR_QEWRTY_PORT TC01 = new TestCase_01_KOR_QEWRTY_PORT(device, "KOR_QWERTY_PORTRAIT");
                runTime += "================= KOR_QWERTY_PORTRAIT =================\n";
                runTime += device.RunTimeCheck("START");
                TC01.start();
                runTime += device.RunTimeCheck("END");
            }
        }catch(Exception e){
            e.printStackTrace();
            runTime += "***Exception occurred***\n" + e.getMessage() + "\n";
            runTime += device.RunTimeCheck("END");
        }

        try{
            Log.i("@@@", "KOR_QWERTY_LANDSCAPE - " + testPlan.getTestPlan(KeyType.KOR_QWERTY, KeyType.LANDSCAPE));
            // 한글QWERTY(가로모드) 입력
            if(testPlan.getTestPlan(KeyType.KOR_QWERTY, KeyType.LANDSCAPE)){
                TestCase_02_KOR_QWERTY_LAND TC02 = new TestCase_02_KOR_QWERTY_LAND(device, "KOR_QWERTY_LANDSCAPE");
                runTime += "================= KOR_QWERTY_LANDSCAPE =================\n";
                runTime += device.RunTimeCheck("START");
                TC02.start();
                runTime += device.RunTimeCheck("END");
            }
        }catch(Exception e){
            e.printStackTrace();
            runTime += "***Exception occurred***\n" + e.getMessage() + "\n";
            runTime += device.RunTimeCheck("END");
        }

        try{
            Log.i("@@@", "ENG_QWERTY_PORTRAIT - " + testPlan.getTestPlan(KeyType.ENG_QWERTY, KeyType.PORTRAIT));
            // 영문QWERTY(세로모드) 입력
            if(testPlan.getTestPlan(KeyType.ENG_QWERTY, KeyType.PORTRAIT)){
                TestCase_03_ENG_QWERTY_PORT TC03 = new TestCase_03_ENG_QWERTY_PORT(device, "ENG_QWERTY_PORTRAIT");
                runTime += "================= ENG_QWERTY_PORTRAIT =================\n";
                runTime += device.RunTimeCheck("START");
                TC03.start();
                runTime += device.RunTimeCheck("END");
            }
        }catch(Exception e){
            e.printStackTrace();
            runTime += "***Exception occurred***\n" + e.getMessage() + "\n";
            runTime += device.RunTimeCheck("END");
        }

        try{
            Log.i("@@@", "ENG_QWERTY_LANDSCAPE - " + testPlan.getTestPlan(KeyType.ENG_QWERTY, KeyType.LANDSCAPE));
            // 영문QWERTY(가로모드) 입력
            if(testPlan.getTestPlan(KeyType.ENG_QWERTY, KeyType.LANDSCAPE)){
                TestCase_04_ENG_QWERTY_LAND TC04 = new TestCase_04_ENG_QWERTY_LAND(device, "ENG_QWERTY_LANDSCAPE");
                runTime += "================= ENG_QWERTY_LANDSCAPE =================\n";
                runTime += device.RunTimeCheck("START");
                TC04.start();
                runTime += device.RunTimeCheck("END");
            }
        }catch(Exception e){
            e.printStackTrace();
            runTime += "***Exception occurred***\n" + e.getMessage() + "\n";
            runTime += device.RunTimeCheck("END");
        }

        try{
            Log.i("@@@", "CHUNJIIN_PORTRAIT - " + testPlan.getTestPlan(KeyType.KOR_CHUNJIIN, KeyType.PORTRAIT));
            // 천지인(세로모드) 입력
            if(testPlan.getTestPlan(KeyType.KOR_CHUNJIIN, KeyType.PORTRAIT)){
                TestCase_05_KOR_CHUNJIIN_PORT TC05 = new TestCase_05_KOR_CHUNJIIN_PORT(device, "CHUNJIIN_PORTRAIT");
                runTime += "================= CHUNJIIN_PORTRAIT =================\n";
                runTime += device.RunTimeCheck("START");
                TC05.start();
                runTime += device.RunTimeCheck("END");
            }
        }catch (Exception e){
            e.printStackTrace();
            runTime += "***Exception occurred***\n" + e.getMessage() + "\n";
            runTime += device.RunTimeCheck("END");
        }

        try{
            Log.i("@@@", "CHUNJIIN_LANDSCAPE - " + testPlan.getTestPlan(KeyType.KOR_CHUNJIIN, KeyType.LANDSCAPE));
            // 천지인(가로모드) 입력
            if(testPlan.getTestPlan(KeyType.KOR_CHUNJIIN, KeyType.LANDSCAPE)){
                TestCase_06_KOR_CHUNJIIN_LAND TC06 = new TestCase_06_KOR_CHUNJIIN_LAND(device, "CHUNJIIN_LANDSCAPE");
                runTime += "================= CHUNJIIN_LANDSCAPE =================\n";
                runTime += device.RunTimeCheck("START");
                TC06.start();
                runTime += device.RunTimeCheck("END");
            }
        }catch (Exception e){
            e.printStackTrace();
            runTime += "***Exception occurred***\n" + e.getMessage() + "\n";
            runTime += device.RunTimeCheck("END");
        }

        try{
            Log.i("@@@", "SKY_PORTRAIT - " + testPlan.getTestPlan(KeyType.KOR_SKY, KeyType.PORTRAIT));
            // KOR_SKY(세로모드) 입력
            if(testPlan.getTestPlan(KeyType.KOR_SKY, KeyType.PORTRAIT)){
                TestCase_07_KOR_SKY_PORT TC07 = new TestCase_07_KOR_SKY_PORT(device, "SKY_PORTRAIT");
                runTime += "================= SKY_PORTRAIT =================\n";
                runTime += device.RunTimeCheck("START");
                TC07.start();
                runTime += device.RunTimeCheck("END");
            }
        }catch (Exception e){
            e.printStackTrace();
            runTime += "***Exception occurred***\n" + e.getMessage() + "\n";
            runTime += device.RunTimeCheck("END");
        }

        try{
            Log.i("@@@", "SKY_LANDSCAPE - " + testPlan.getTestPlan(KeyType.KOR_SKY, KeyType.LANDSCAPE));
            // KOR_SKY(가로모드) 입력
            if(testPlan.getTestPlan(KeyType.KOR_SKY, KeyType.LANDSCAPE)){
                TestCase_08_KOR_SKY_LAND TC08 = new TestCase_08_KOR_SKY_LAND(device, "SKY_LANDSCAPE");
                runTime += "================= SKY_LANDSCAPE =================\n";
                runTime += device.RunTimeCheck("START");
                TC08.start();
                runTime += device.RunTimeCheck("END");
            }
        }catch (Exception e){
            e.printStackTrace();
            runTime += "***Exception occurred***\n" + e.getMessage() + "\n";
            runTime += device.RunTimeCheck("END");
        }

        try{
            Log.i("@@@", "NARAGUL_PORTRAIT - " + testPlan.getTestPlan(KeyType.KOR_NARAGUL, KeyType.PORTRAIT));
            // 나랏글(세로모드) 입력
            if(testPlan.getTestPlan(KeyType.KOR_NARAGUL, KeyType.PORTRAIT)){
                TestCase_09_KOR_NARAGUL_PORT TC09 = new TestCase_09_KOR_NARAGUL_PORT(device, "NARAGUL_PORTRAIT");
                runTime += "================= NARAGUL_PORTRAIT =================\n";
                runTime += device.RunTimeCheck("START");
                TC09.start();
                runTime += device.RunTimeCheck("END");
            }
        }catch (Exception e){
            e.printStackTrace();
            runTime += "***Exception occurred***\n" + e.getMessage() + "\n";
            runTime += device.RunTimeCheck("END");
        }

        try{
            Log.i("@@@", "NARAGUL_LANDSCAPE - " + testPlan.getTestPlan(KeyType.KOR_NARAGUL, KeyType.LANDSCAPE));
            // 나랏글(가로모드) 입력
            if(testPlan.getTestPlan(KeyType.KOR_NARAGUL, KeyType.LANDSCAPE)){
                TestCase_10_KOR_NARAGUL_LAND TC10 = new TestCase_10_KOR_NARAGUL_LAND(device, "NARAGUL_LANDSCAPE");
                runTime += "================= NARAGUL_LANDSCAPE =================\n";
                runTime += device.RunTimeCheck("START");
                TC10.start();
                runTime += device.RunTimeCheck("END");
            }
        }catch (Exception e){
            e.printStackTrace();
            runTime += "***Exception occurred***\n" + e.getMessage() + "\n";
            runTime += device.RunTimeCheck("END");
        }

        try{
            Log.i("@@@", "DANMOUM_PORTRAIT - " + testPlan.getTestPlan(KeyType.KOR_DANMOUM, KeyType.PORTRAIT));
            // 나랏글(가로모드) 입력
            if(testPlan.getTestPlan(KeyType.KOR_DANMOUM, KeyType.PORTRAIT)){
                TestCase_11_KOR_DANMOUM_PORT TC11 = new TestCase_11_KOR_DANMOUM_PORT(device, "DANMOUM_PORTRAIT");
                runTime += "================= DANMOUM_PORTRAIT =================\n";
                runTime += device.RunTimeCheck("START");
                TC11.start();
                runTime += device.RunTimeCheck("END");
            }
        }catch (Exception e){
            e.printStackTrace();
            runTime += "***Exception occurred***\n" + e.getMessage() + "\n";
            runTime += device.RunTimeCheck("END");
        }

        try{
            Log.i("@@@", "DANMOUM_LANDSCAPE - " + testPlan.getTestPlan(KeyType.KOR_DANMOUM, KeyType.LANDSCAPE));
            // 단모음(가로모드) 입력
            if(testPlan.getTestPlan(KeyType.KOR_DANMOUM, KeyType.LANDSCAPE)){
                TestCase_12_KOR_DANMOUM_LAND TC12 = new TestCase_12_KOR_DANMOUM_LAND(device, "DANMOUM_LANDSCAPE");
                runTime += "================= DANMOUM_LANDSCAPE =================\n";
                runTime += device.RunTimeCheck("START");
                TC12.start();
                runTime += device.RunTimeCheck("END");
            }
        }catch (Exception e){
            e.printStackTrace();
            runTime += "***Exception occurred***\n" + e.getMessage() + "\n";
            runTime += device.RunTimeCheck("END");
        }
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