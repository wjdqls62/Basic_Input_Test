package com.phillit.qa.basicinputtest.Common.KeyType;

import android.content.Context;
import android.support.test.uiautomator.UiDevice;
import com.phillit.qa.basicinputtest.Common.Key;
import com.phillit.qa.basicinputtest.Common.XmlParserManager;
import java.util.HashMap;

public class Qwerty extends KeyType {
    private UiDevice uiDevice;
    private HashMap<String, Key> keyList;
    private XmlParserManager parser;

    public Qwerty(UiDevice uiDevice, Context context, int screenOrientation){
        this.uiDevice = uiDevice;
        parser = new XmlParserManager(context, screenOrientation);
        keyList = parser.getKeyList();
    }

    @Override
    public void input(String args) {
        char[] arrChar = args.toCharArray();
        Key key;

        for(int i=0; i<arrChar.length; i++){
            String targetChar = String.valueOf(arrChar[i]);
            key = keyList.get(targetChar);
            for(int j=0; j<key.keyCordinates.size(); j++){
                uiDevice.click(key.keyCordinates.get(j).x, key.keyCordinates.get(j).y);
            }
        }
    }
}