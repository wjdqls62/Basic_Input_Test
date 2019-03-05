package com.phillit.qa.basicinputtest.Common.KeyType.KOR_ENG;

import android.content.Context;

import com.phillit.qa.basicinputtest.Common.Key;
import com.phillit.qa.basicinputtest.Common.Device;
import com.phillit.qa.basicinputtest.Common.KeyType.KeyType;
import com.phillit.qa.basicinputtest.Common.XmlParser;
import java.util.HashMap;

public class Qwerty extends KeyType {
    private Device device;
    private HashMap<String, Key> normalKeyList, specialKeyList;
    private XmlParser parser;
    private StringBuffer buffer;
    private char[] arrChar;
    private Qwerty_Special_Character specialCharacter;
    private int spacebar_x, spacebar_y, language;
    private int shift_x, shift_y;
    boolean isSpecialChar = false;

    public Qwerty(Device device, Context context, int screenOrientation, int language){
        this.device = device;
        parser = new XmlParser(context, screenOrientation, language, device);
        this.language = language;
        buffer = new StringBuffer();

        // 문자키, 특수문자키를 XML로부터 읽어온다
        normalKeyList = parser.getKeyList();
        specialKeyList = parser.getSpecialKeyList();
        specialCharacter = new Qwerty_Special_Character(device, context, screenOrientation, language, specialKeyList);

        // 단어입력 후 띄어쓰기를 위해 Spacebar의 좌표를 Preload
        spacebar_x = normalKeyList.get("^").keyCordinates.get(0).x;
        spacebar_y = normalKeyList.get("^").keyCordinates.get(0).y;

        // 영문의 경우 대문자 입력을 위해 Shift의 좌표를 Preload
        if(language == KeyType.QWERTY_ENGLISH){
            shift_x = normalKeyList.get("↑").keyCordinates.get(0).x;
            shift_y = normalKeyList.get("↑").keyCordinates.get(0).y;
        }
    }

    @Override
    public void input(String args) {
        arrChar = args.toCharArray();
        typingKeyboard(arrChar);
        device.getUiDevice().click(spacebar_x, spacebar_y);
    }

    @Override
    public void input(StringBuffer args) {
        typingKeyboard(args);
        device.getUiDevice().click(spacebar_x, spacebar_y);
    }

    private void typingKeyboard(StringBuffer arrChar){
        Key key;
        String targetChar = "";

        for(int i=0; i<arrChar.length(); i++){
            targetChar = String.valueOf(arrChar.charAt(i));

            isSpecialChar = device.isSpecialCharacter(targetChar);

            // 특수문자가 아닐경우
            if(!isSpecialChar){
                // 영문인데 대문자의 경우
                if(language == KeyType.QWERTY_ENGLISH && isUpper(targetChar)){
                    device.getUiDevice().click(shift_x, shift_y);
                    key = normalKeyList.get(targetChar.toLowerCase());
                }else{
                    key = normalKeyList.get(targetChar);
                }

                for(int j=0; j<key.keyCordinates.size(); j++){
                    device.getUiDevice().click(key.keyCordinates.get(j).x, key.keyCordinates.get(j).y);
                }
            }
            // 특수문자의 경우
            else{
                specialCharacter.input(targetChar);
            }
        }
    }

    private void typingKeyboard(char[] arrChar){
        Key key;
        for(int i=0; i<arrChar.length; i++){
            String targetChar = String.valueOf(arrChar[i]);

            isSpecialChar = device.isSpecialCharacter(targetChar);

            // 특수문자가 아닐경우
            if(!isSpecialChar){
                // 영문인데 대문자의 경우
                if(language == KeyType.QWERTY_ENGLISH && isUpper(targetChar)){
                    device.getUiDevice().click(shift_x, shift_y);
                    key = normalKeyList.get(targetChar.toLowerCase());
                }else{
                    key = normalKeyList.get(targetChar);
                }

                for(int j=0; j<key.keyCordinates.size(); j++){
                    device.getUiDevice().click(key.keyCordinates.get(j).x, key.keyCordinates.get(j).y);
                }
            }
            // 특수문자의 경우
            else{
                specialCharacter.input(targetChar);
            }
        }
    }

    // 대문자 체크
    private boolean isUpper(String str){
        return str.matches("^[A-Z]*$");
    }
}