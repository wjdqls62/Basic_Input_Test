package com.phillit.qa.basicinputtest.Common;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;;
import com.phillit.qa.basicinputtest.Common.KeyType.KeyType;
import java.util.List;

public class Utility {
    private UiDevice uiDevice;
    private UiObject uiIObject;
    private UiSelector uiSelector;
    private Context context;
    private PackageManager packageManager;
    private KeyType keyType;

    public Utility(UiDevice Device, Context context){
        this.uiDevice = Device;
        this.context = context;
        uiSelector = new UiSelector();
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
}
