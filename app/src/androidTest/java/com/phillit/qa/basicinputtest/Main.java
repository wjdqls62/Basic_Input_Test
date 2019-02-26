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
import com.phillit.qa.basicinputtest.TestCase.TestCase_01;
import com.phillit.qa.basicinputtest.TestCase.TestCase_02;
import com.phillit.qa.basicinputtest.TestCase.TestCase_03;
import com.phillit.qa.basicinputtest.TestCase.TestCase_04;
import com.phillit.qa.basicinputtest.TestCase.TestCase_05;
import com.phillit.qa.basicinputtest.TestCase.TestCase_06;
import com.phillit.qa.basicinputtest.TestCase.TestCase_07;
import com.phillit.qa.basicinputtest.TestCase.TestCase_08;
import com.phillit.qa.basicinputtest.TestCase.TestCase_09;
import com.phillit.qa.basicinputtest.TestCase.TestCase_10;
import com.phillit.qa.basicinputtest.TestCase.TestCase_11;
import com.phillit.qa.basicinputtest.TestCase.TestCase_12;

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
            Log.i("@@@", "KOR_QWERTY_PORTRAIT - " + testPlan.getTestPlan(KeyType.QWERTY_KOREA, KeyType.PORTRAIT));
            // 한글QWERTY(세로모드) 입력
            if(testPlan.getTestPlan(KeyType.QWERTY_KOREA, KeyType.PORTRAIT)){
                TestCase_01 TC01 = new TestCase_01(device, "KOR_QWERTY_PORTRAIT");
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
            Log.i("@@@", "KOR_QWERTY_LANDSCAPE - " + testPlan.getTestPlan(KeyType.QWERTY_KOREA, KeyType.LANDSCAPE));
            // 한글QWERTY(가로모드) 입력
            if(testPlan.getTestPlan(KeyType.QWERTY_KOREA, KeyType.LANDSCAPE)){
                TestCase_02 TC02 = new TestCase_02(device, "KOR_QWERTY_LANDSCAPE");
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
            Log.i("@@@", "ENG_QWERTY_PORTRAIT - " + testPlan.getTestPlan(KeyType.QWERTY_ENGLISH, KeyType.PORTRAIT));
            // 영문QWERTY(세로모드) 입력
            if(testPlan.getTestPlan(KeyType.QWERTY_ENGLISH, KeyType.PORTRAIT)){
                TestCase_03 TC03 = new TestCase_03(device, "ENG_QWERTY_PORTRAIT");
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
            Log.i("@@@", "ENG_QWERTY_LANDSCAPE - " + testPlan.getTestPlan(KeyType.QWERTY_ENGLISH, KeyType.LANDSCAPE));
            // 영문QWERTY(가로모드) 입력
            if(testPlan.getTestPlan(KeyType.QWERTY_ENGLISH, KeyType.LANDSCAPE)){
                TestCase_04 TC04 = new TestCase_04(device, "ENG_QWERTY_LANDSCAPE");
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
            Log.i("@@@", "CHUNJIIN_PORTRAIT - " + testPlan.getTestPlan(KeyType.CHUNJIIN, KeyType.PORTRAIT));
            // 천지인(세로모드) 입력
            if(testPlan.getTestPlan(KeyType.CHUNJIIN, KeyType.PORTRAIT)){
                TestCase_05 TC05 = new TestCase_05(device, "CHUNJIIN_PORTRAIT");
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
            Log.i("@@@", "CHUNJIIN_LANDSCAPE - " + testPlan.getTestPlan(KeyType.CHUNJIIN, KeyType.LANDSCAPE));
            // 천지인(가로모드) 입력
            if(testPlan.getTestPlan(KeyType.CHUNJIIN, KeyType.LANDSCAPE)){
                TestCase_06 TC06 = new TestCase_06(device, "CHUNJIIN_LANDSCAPE");
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
            Log.i("@@@", "SKY_PORTRAIT - " + testPlan.getTestPlan(KeyType.SKY, KeyType.PORTRAIT));
            // SKY(세로모드) 입력
            if(testPlan.getTestPlan(KeyType.SKY, KeyType.PORTRAIT)){
                TestCase_07 TC07 = new TestCase_07(device, "SKY_PORTRAIT");
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
            Log.i("@@@", "SKY_LANDSCAPE - " + testPlan.getTestPlan(KeyType.SKY, KeyType.LANDSCAPE));
            // SKY(가로모드) 입력
            if(testPlan.getTestPlan(KeyType.SKY, KeyType.LANDSCAPE)){
                TestCase_08 TC08 = new TestCase_08(device, "SKY_LANDSCAPE");
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
            Log.i("@@@", "NARAGUL_PORTRAIT - " + testPlan.getTestPlan(KeyType.NARAGUL, KeyType.PORTRAIT));
            // 나랏글(세로모드) 입력
            if(testPlan.getTestPlan(KeyType.NARAGUL, KeyType.PORTRAIT)){
                TestCase_09 TC09 = new TestCase_09(device, "NARAGUL_PORTRAIT");
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
            Log.i("@@@", "NARAGUL_LANDSCAPE - " + testPlan.getTestPlan(KeyType.NARAGUL, KeyType.LANDSCAPE));
            // 나랏글(가로모드) 입력
            if(testPlan.getTestPlan(KeyType.NARAGUL, KeyType.LANDSCAPE)){
                TestCase_10 TC10 = new TestCase_10(device, "NARAGUL_LANDSCAPE");
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
            Log.i("@@@", "DANMOUM_PORTRAIT - " + testPlan.getTestPlan(KeyType.DANMOUM, KeyType.PORTRAIT));
            // 나랏글(가로모드) 입력
            if(testPlan.getTestPlan(KeyType.DANMOUM, KeyType.PORTRAIT)){
                TestCase_11 TC11 = new TestCase_11(device, "DANMOUM_PORTRAIT");
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
            Log.i("@@@", "DANMOUM_LANDSCAPE - " + testPlan.getTestPlan(KeyType.DANMOUM, KeyType.LANDSCAPE));
            // 단모음(가로모드) 입력
            if(testPlan.getTestPlan(KeyType.DANMOUM, KeyType.LANDSCAPE)){
                TestCase_12 TC12 = new TestCase_12(device, "DANMOUM_LANDSCAPE");
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