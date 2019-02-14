package com.phillit.qa.basicinputtest.Common;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.BatteryManager;
import android.os.RemoteException;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import android.text.TextUtils;
import android.util.Log;

import com.phillit.qa.basicinputtest.Common.Configuration.Configuration;
import com.phillit.qa.basicinputtest.Common.DeviceType.DeviceType;
import com.phillit.qa.basicinputtest.Common.KeyType.KeyType;
import com.phillit.qa.basicinputtest.Common.KeyType.Qwerty;
import com.phillit.qa.basicinputtest.R;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Device {
    public final int WAIT_FOR_UIOBJECT_TIME = 10000;
    private UiDevice uiDevice;
    private UiSelector uiSelector;
    private TestPlan testPlan;
    private Context context;
    private BatteryManager batteryManager;
    private PackageManager packageManager;
    private File meminfoFile;
    private String meminfo, deviceModelName;
    private Date date;
    private String start_Time, start_Time2, end_Time, end_Time2;

    public Device(UiDevice Device, Context context) throws IOException {
        this.uiDevice = Device;
        this.context = context;
        uiSelector = new UiSelector();
        testPlan = new TestPlan();
        deviceModelName = getProductModelName();
        batteryManager = (BatteryManager) context.getSystemService(Context.BATTERY_SERVICE);
    }

    public UiDevice getUiDevice(){
        return uiDevice;
    }

    public Context getContext(){
        return context;
    }

    public void userWait(long milSeconds)  {
        synchronized (uiDevice){
            try {
                uiDevice.wait(milSeconds);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void inputMethod(String text, KeyType keyType){
        keyType.input(text);
    }

    public void inputMethod(StringBuffer text, KeyType keyType){
        keyType.input(text);
    }

    public void goToIdle(){
        uiDevice.pressBack();
        userWait(500);
        uiDevice.pressBack();
        userWait(500);
        uiDevice.pressBack();
        userWait(500);
        uiDevice.pressBack();
        userWait(500);
        uiDevice.pressHome();
    }

    // 화면상 요소의 text값으로 객체를 터치한다
    public boolean touchText(String inputText){
        boolean result = false;
        UiObject object = null;
        try {
            object = uiDevice.findObject(new UiSelector().text(inputText));
            result = object.click();
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
        Log.i("@@@", "touchText() Result : " + result + " // " + object.getSelector().toString());
        return result;
    }

    // 화면상 요소의id값으로 객체를 터치한다
    public boolean touchObject(String id){
        boolean result = false;
        UiObject object = null;
        try {
            object = uiDevice.findObject(new UiSelector().resourceId(id));
            result = object.click();
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
        Log.i("@@@", "TouchObject Result : " + result + " // " + object.getSelector().toString());
        return result;
    }

    // 화면상 요소의 Object값으로 객체를 터치한다
    public boolean touchObject(UiObject object){
        boolean result = false;
        try {
            result = object.click();
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
        Log.i("@@@", "TouchObject Result : " + result + " // " + object.getSelector().toString());
        return result;
    }

    // Application을 실행한다
    public boolean launchApplication(String appName){
        Intent intent;
        String targetPackageName = "";
        boolean result = false;

        if(packageManager == null){
            packageManager = context.getPackageManager();
        }

        List<ApplicationInfo> apps = packageManager.getInstalledApplications(PackageManager.GET_META_DATA);

        for(int i=0; i<apps.size(); i++){
            String searchedApp = apps.get(i).loadLabel(packageManager).toString();
            if(searchedApp.equals(appName)){
                targetPackageName = apps.get(i).packageName;
                break;
            }
        }

        intent = context.getPackageManager().getLaunchIntentForPackage(targetPackageName);
        try{
            context.startActivity(intent);
            result = true;
        }catch(Exception e){
            Log.i("@@@", "Not found application...");
            e.printStackTrace();
        }

        Log.i("@@@", "launchApplication() Result : " + result + " / appName : " + appName);
        return result;
    }

    // 메모리 덤프
    public void dumpsysMemifo(String fileName) throws IOException {
        if(meminfoFile == null){
            meminfoFile = new File("/sdcard/QA/InputTest/" + fileName + ".txt");
        }
        FileWriter writer = new FileWriter(meminfoFile, true);
        meminfo = "======================= " + new Date() + " =======================\n";
        meminfo += getUiDevice().executeShellCommand("dumpsys meminfo com.phillit.akeyboard -d");
        writer.write(meminfo);
        writer.close();
    }

    // File객체 참조 해제
    public void Release(){
        meminfoFile = null;
    }

    public void setTestPlan(){
        TestCaseParser parser = new TestCaseParser("Env", context);
        testPlan = parser.getTestPlan(testPlan);
        parser = null;
    }

    public TestPlan getTestPlan(){
        return testPlan;
    }

    public void sendReport(String runTime, String totalRunTime) throws UiObjectNotFoundException {
        KeyType qwerty_eng = new Qwerty(this, context, KeyType.PORTRAIT, KeyType.QWERTY_ENGLISH);
        UiObject object = null;

        // Solid Explorer 실행
        launchApplication("Solid Explorer");
        userWait(5000);

        // 네비게이션 메뉴 선택
        object = uiDevice.findObject(new UiSelector().resourceId("pl.solidexplorer2:id/ab_icon"));
        if(object.waitForExists(WAIT_FOR_UIOBJECT_TIME)){
            touchObject(object);
            userWait(3000);
        }else{
            return;
        }

        // 북마크 선택
        object = uiDevice.findObject(new UiSelector().text("/storage/emulated/0/QA/InputTest"));
        if(object.waitForExists(WAIT_FOR_UIOBJECT_TIME)){
            touchObject(object);
            userWait(3000);
        }else{
            return;
        }

        // 메뉴버튼
        object = uiDevice.findObject(new UiSelector().resourceId("pl.solidexplorer2:id/action_overflow"));
        if(object.waitForExists(WAIT_FOR_UIOBJECT_TIME)){
            touchObject(object);
            userWait(3000);
        }else{
            return;
        }

        // 모두선택
        object = uiDevice.findObject(new UiSelector().text("모두 선택"));
        if(object.waitForExists(WAIT_FOR_UIOBJECT_TIME)){
            touchText("모두 선택");
            userWait(3000);

            // 메뉴버튼
            object = uiDevice.findObject(new UiSelector().resourceId("pl.solidexplorer2:id/action_overflow"));
            if(object.waitForExists(WAIT_FOR_UIOBJECT_TIME)){
                touchObject(object);
                userWait(3000);

                // 공유
                object = uiDevice.findObject(new UiSelector().text("공유"));
                if(object.waitForExists(WAIT_FOR_UIOBJECT_TIME)){
                    touchObject(object);
                    userWait(3000);

                    // 네이버 메일 선택
                    object = uiDevice.findObject(new UiSelector().text("네이버 메일"));
                    if(object.waitForExists(WAIT_FOR_UIOBJECT_TIME)){
                        touchObject(object);
                        userWait(5000);
                    }else{
                        return;
                    }
                }else{
                    return;
                }
            }else{
                return;
            }
        }else{
            return;
        }

        // 받는사람 EditText 터치
        object = uiDevice.findObject(new UiSelector().className("android.widget.EditText").index(0));
        if(object.waitForExists(WAIT_FOR_UIOBJECT_TIME)){
            //touchObject(object);
            userWait(3000);

            // InternalTest가 아닐경우 GroupMail로 전송
            if(!getTestPlan().isInternalTest){
                object.setText("paf617@phill-it.com");
                //inputMethod("paf617@phill-it.com", qwerty_eng);
            }else{
                object.setText("jeongbeen.son@phill-it.com");
                //inputMethod("jeongbeen.son@phill-it.com", qwerty_eng);
            }
            userWait(3000);

            // 메일 제목 입력
            object = uiDevice.findObject(new UiSelector().resourceId("com.nhn.android.mail:id/mailWriteTitle"));
            if(object.waitForExists(WAIT_FOR_UIOBJECT_TIME)){
                //touchObject(object);
                userWait(3000);
                // 제목 입력
                object.setText("[MonkeyTest] Result");
                //inputMethod("[MonkeyTest]^Result", qwerty_eng);
                userWait(3000);

                // 내용 입력
                object = uiDevice.findObject(new UiSelector().resourceId("com.nhn.android.mail:id/mailWriteRichEditView"));
                if(object.waitForExists(WAIT_FOR_UIOBJECT_TIME)){
                    object.setText("Hi.\nThis mail is automatically sent from Monkey Test. Please check the attached file.\n" + runTime + totalRunTime );
                    userWait(WAIT_FOR_UIOBJECT_TIME);

                    // 전송버튼
                    object = uiDevice.findObject(new UiSelector().resourceId("com.nhn.android.mail:id/actionSend"));
                    if(object.waitForExists(WAIT_FOR_UIOBJECT_TIME)){
                        touchObject(object);
                        userWait(WAIT_FOR_UIOBJECT_TIME * 2);
                    }
                }
            }else{
                return;
            }
        }else{
            return;
        }
        goToIdle();
    }

    public String TotalRunTimeCheck(String mode){
        if(mode.equals("START")){
            date = new Date();
            start_Time = new SimpleDateFormat("yyyy-MM-dd").format(date);
            start_Time2 = new SimpleDateFormat("HH:mm:ss").format(date);
            date = null;
            return "- Start : " + start_Time + " " + start_Time2 + "\n";
        }
        else if(mode.equals("END")){
            date = new Date();
            end_Time = new SimpleDateFormat("yyyy-MM-dd").format(date);
            end_Time2 = new SimpleDateFormat("HH:mm:ss").format(date);
            return "- End : " + end_Time + " " + end_Time2 + "\n";
        }else{
            return "";
        }
    }

    public String RunTimeCheck(String mode){
        String start_Time, start_Time2, end_Time, end_Time2;
        if(mode.equals("START")){
            date = new Date();
            start_Time = new SimpleDateFormat("yyyy-MM-dd").format(date);
            start_Time2 = new SimpleDateFormat("HH:mm:ss").format(date);
            date = null;
            return "- Start : " + start_Time + " " + start_Time2 + "\n";
        }
        else if(mode.equals("END")){
            date = new Date();
            end_Time = new SimpleDateFormat("yyyy-MM-dd").format(date);
            end_Time2 = new SimpleDateFormat("HH:mm:ss").format(date);
            return "- End : " + end_Time + " " + end_Time2 + "\n";
        }else{
            return "";
        }
    }

    // 현재 배터리 %를 가져온다.
    public int getBatteryStatus(){
        return batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);
    }

    // 5분마다 배터리 %를 체크하여 90%가 될때까지 대기한다.
    public void chargeDevice() throws RemoteException {
        uiDevice.sleep();
        while (true){
            userWait(300000);
            if(getBatteryStatus() >= Configuration.BATTERY_MAX_VALUE){
                Log.i("@@@", "Suspend the test to charge the battery.../ " + getBatteryStatus() + "%");
                uiDevice.wakeUp();
                break;
            }
            Log.i("@@@", "Resume the test... / " + getBatteryStatus() + "%");
        }
    }

    public boolean isNumber(String str){
        boolean result = false;

        try{
            Double.parseDouble(str);
            result = true;
        }catch (Exception e){}

        return result;
    }

    // 특수문자 체크
    public boolean isSpecialCharacter(String str){
        if(TextUtils.isEmpty(str)){
            return false;
        }
        for(int i=0; i < str.length(); i++){
            if(!str.equals("^")){
                if(!Character.isLetterOrDigit(str.charAt(i))){
                    return true;
                }else if(str.equals("π") || str.equals("ℓ")){
                    return true;
                }
            }
        }
        return false;
    }

    public void PowerOff() throws IOException {
        Log.i("@@@", "Test Finish. Shutdown device.");
        userWait(10000);
        uiDevice.executeShellCommand("reboot -p");
    }

    public void changeKeyType(int keyType) throws UiObjectNotFoundException {
        String aKeyboardAppName = getContext().getResources().getString(R.string.akeyboard_app_name);
        UiObject object = null;

        // 레빗 A키보드 실행
        launchApplication(aKeyboardAppName);

        // 언어메뉴 선택
        object = uiDevice.findObject(new UiSelector().resourceId("com.phillit.akeyboard:id/setting_main_language"));
        if(object.waitForExists(5000)){
            touchObject(object);
        }

        // Parameter에 맞는 언어메뉴 선택
        if(keyType == KeyType.QWERTY_ENGLISH){
            object = uiDevice.findObject(new UiSelector().resourceId("com.phillit.akeyboard:id/language_name").text("영어 (미국) / English(US)"));
            if(object.waitForExists(3000)){
                touchObject(object);
            }
        }else if(keyType == KeyType.QWERTY_KOREA || keyType == KeyType.CHUNJIIN || keyType == KeyType.SKY || keyType == KeyType.NARAGUL || keyType == KeyType.DANMOUM){
            object = uiDevice.findObject(new UiSelector().resourceId("com.phillit.akeyboard:id/language_name").text("한국어"));
            if(object.waitForExists(3000)){
                touchObject(object);
            }
        }

        if(keyType == KeyType.QWERTY_KOREA || keyType == KeyType.QWERTY_ENGLISH){
            object = uiDevice.findObject(new UiSelector().resourceId("com.phillit.akeyboard:id/keyboard_name").text("QWERTY"));
            if(object.waitForExists(3000)){
                touchObject(object);
            }
        }else if(keyType == KeyType.CHUNJIIN){
            object = uiDevice.findObject(new UiSelector().resourceId("com.phillit.akeyboard:id/keyboard_name").text("천지인"));
            if(object.waitForExists(3000)){
                touchObject(object);
            }
        }

        else if(keyType == KeyType.SKY){
            object = uiDevice.findObject(new UiSelector().resourceId("com.phillit.akeyboard:id/keyboard_name").text("스카이"));
            if(object.waitForExists(3000)){
                touchObject(object);
            }
        }

        else if(keyType == KeyType.NARAGUL){
            object = uiDevice.findObject(new UiSelector().resourceId("com.phillit.akeyboard:id/keyboard_name").text("나랏글"));
            if(object.waitForExists(3000)){
                touchObject(object);
            }
        }

        else if(keyType == KeyType.DANMOUM){
            object = uiDevice.findObject(new UiSelector().resourceId("com.phillit.akeyboard:id/keyboard_name").text("단모음"));
            if(object.waitForExists(3000)){
                touchObject(object);
            }
        }

        // 5초대기 후 홈스크린 진입
        userWait(5000);
        goToIdle();
    }

    public void changeKeyboardLanguage(int keyType){
        if(deviceModelName.equals(DeviceType.NEXUS5)){
            if(keyType == KeyType.SKY){
                uiDevice.click(1000, 1500);
            }else{
                uiDevice.click(205, 1690);
            }
        }
        userWait(WAIT_FOR_UIOBJECT_TIME);
    }

    public String getDeviceModelName(){
        return deviceModelName;
    }

    private String getProductModelName() throws IOException {
        return uiDevice.executeShellCommand("getprop ro.product.model").trim();
    }
}