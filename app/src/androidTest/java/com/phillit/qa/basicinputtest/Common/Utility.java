package com.phillit.qa.basicinputtest.Common;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.BatteryManager;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import android.util.Log;;
import com.phillit.qa.basicinputtest.Common.KeyType.KeyType;
import com.phillit.qa.basicinputtest.Common.KeyType.Qwerty;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Utility {
    public final int BATTERY_MIN_VALUE = 40;
    public final int BATTERY_MAX_VALUE = 90;
    private UiDevice uiDevice;
    private UiSelector uiSelector;
    private TestPlan testPlan;
    private Context context;
    private BatteryManager batteryManager;
    private PackageManager packageManager;
    private File meminfoFile;
    private String meminfo;
    private Date date;
    private String start_Time, start_Time2, end_Time, end_Time2;

    public Utility(UiDevice Device, Context context){
        this.uiDevice = Device;
        this.context = context;
        uiSelector = new UiSelector();
        testPlan = new TestPlan();
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
        uiDevice.pressHome();
    }

    // 화면상 요소의 text값으로 객체를 터치한다
    public void touchText(String inputText){
        try {
            uiDevice.findObject(new UiSelector().text(inputText)).click();
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    // 화면상 요소의id값으로 객체를 터치한다
    public void touchObject(String id){
        boolean result = false;
        try {
            uiDevice.findObject(new UiSelector().resourceId(id)).click();
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Application을 실행한다
    public void launchApplication(String appName){
        Intent intent;
        String targetPackageName = "";

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
        context.startActivity(intent);
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
        TestCaseParser parser = new TestCaseParser("Env");
        testPlan = parser.getTestPlan(testPlan);
        parser = null;
    }

    public TestPlan getTestPlan(){
        return testPlan;
    }

    public void sendReport() throws UiObjectNotFoundException {
        KeyType qwerty_eng = new Qwerty(this, context, KeyType.QWERTY_PORTRAIT, KeyType.QWERTY_ENGLISH);

        // Solid Explorer 실행
        launchApplication("Solid Explorer");
        userWait(5000);

        // 네비게이션 메뉴 선택
        touchObject("pl.solidexplorer2:id/ab_icon");
        userWait(3000);


        // 북마크 선택
        touchText("/storage/emulated/0/QA/InputTest");
        userWait(3000);

        // 메뉴버튼
        touchObject("pl.solidexplorer2:id/action_overflow");
        userWait(3000);

        // 모두선택
        touchText("모두 선택");
        userWait(3000);

        // 메뉴버튼
        touchObject("pl.solidexplorer2:id/action_overflow");
        userWait(3000);

        // 공유
        touchText("공유");
        userWait(3000);

        // 네이버 메일 선택
        touchText("네이버 메일");
        userWait(5000);

        // 받는사람 EditText 터치
        getUiDevice().findObject(new UiSelector().className("android.widget.EditText").index(0)).click();
        userWait(3000);
        inputMethod("paf617@phill-it.com", qwerty_eng);
        userWait(1000);
        // Enter 버튼
        getUiDevice().click(990, 1690);
        userWait(2000);

        // 제목 EditText 터치
        touchObject("com.nhn.android.mail:id/mailWriteTitle");
        userWait(3000);
        inputMethod("[MonkeyTest]^Result", qwerty_eng);
        userWait(3000);

        // 내용
        touchObject("com.nhn.android.mail:id/mailWriteRichEditView");
        userWait(3000);

        inputMethod("Hi.", qwerty_eng);
        getUiDevice().click(990, 1690);
        inputMethod("This^mail^is^automatically^sent^from^Monkey^Test.^Please^check^the^attached^file.", qwerty_eng);
        getUiDevice().click(990, 1690);
        inputMethod("Start:^", qwerty_eng);
        inputMethod(start_Time, qwerty_eng);
        inputMethod(start_Time2, qwerty_eng);
        // Enter
        getUiDevice().click(990, 1690);
        userWait(3000);
        inputMethod("End:^", qwerty_eng);
        inputMethod(end_Time, qwerty_eng);
        inputMethod(end_Time2, qwerty_eng);
        userWait(3000);

        touchObject("com.nhn.android.mail:id/actionSend");

        userWait(10000);

        goToIdle();
    }

    public void TimeCheck(String mode){
        if(mode.equals("START")){
            date = new Date();
            start_Time = new SimpleDateFormat("yyyy-MM-dd").format(date);
            start_Time2 = new SimpleDateFormat("HH:mm:ss").format(date);
            date = null;
        }
        else if(mode.equals("END")){
            date = new Date();
            end_Time = new SimpleDateFormat("yyyy-MM-dd").format(date);
            end_Time2 = new SimpleDateFormat("HH:mm:ss").format(date);
        }
    }

    // 현재 배터리 %를 가져온다.
    public int getBatteryStatus(){
        return batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);
    }

    // 5분마다 배터리 %를 체크하여 90%가 될때까지 대기한다.
    public void chargeDevice(){
        while (true){
            userWait(300000);
            if(getBatteryStatus() >= BATTERY_MAX_VALUE){
                Log.i("@@@", "Test is Resume.../ " + getBatteryStatus() + "%");
                break;
            }
            Log.i("@@@", "Test is pause. Battery charging... / " + getBatteryStatus() + "%");
        }
    }
}