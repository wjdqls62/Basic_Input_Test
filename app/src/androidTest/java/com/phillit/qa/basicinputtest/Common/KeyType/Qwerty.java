package com.phillit.qa.basicinputtest.Common.KeyType;

import android.content.Context;
import android.text.TextUtils;

import com.phillit.qa.basicinputtest.Common.Key;
import com.phillit.qa.basicinputtest.Common.Utility;
import com.phillit.qa.basicinputtest.Common.XmlParser;
import java.util.HashMap;

public class Qwerty extends KeyType {
    private Utility utility;
    private HashMap<String, Key> normalKeyList, specialKeyList;
    private XmlParser parser;
    private int spacebar_x, spacebar_y, language;
    private int shift_x, shift_y;


    public Qwerty(Utility utility, Context context, int screenOrientation, int language){
        this.utility = utility;
        parser = new XmlParser(context, screenOrientation, language);
        this.language = language;

        normalKeyList = parser.getKeyList();
        specialKeyList = parser.getSpecialKeyList();

        spacebar_x = normalKeyList.get("^").keyCordinates.get(0).x;
        spacebar_y = normalKeyList.get("^").keyCordinates.get(0).y;
        if(language == KeyType.QWERTY_ENGLISH){
            shift_x = normalKeyList.get("↑").keyCordinates.get(0).x;
            shift_y = normalKeyList.get("↑").keyCordinates.get(0).y;
        }
    }

    @Override
    public void input(String args) {
        char[] arrChar = args.toCharArray();
        boolean isSpecialChar = false;
        Key key;

        for(int i=0; i<arrChar.length; i++){
            String targetChar = String.valueOf(arrChar[i]);

            isSpecialChar = isSpecialCharacter(targetChar);

            // 특수문자가 아닐경우
            if(!isSpecialChar){
                // 영문인데 대문자의 경우
                if(language == KeyType.QWERTY_ENGLISH && isUpper(targetChar)){
                    utility.getUiDevice().click(shift_x, shift_y);
                    key = normalKeyList.get(targetChar.toLowerCase());
                }else{
                    key = normalKeyList.get(targetChar);
                }

                for(int j=0; j<key.keyCordinates.size(); j++){
                    utility.getUiDevice().click(key.keyCordinates.get(j).x, key.keyCordinates.get(j).y);
                }
            }
            // 특수문자의 경우
            else{
                key = specialKeyList.get(targetChar);
                for(int k=0; k<key.keyCordinates.size(); k++){
                    utility.getUiDevice().click(key.keyCordinates.get(k).x, key.keyCordinates.get(k).y);
                }
            }
        }
        // 단어입력 후 한칸띄우기(Spacebar)
        utility.getUiDevice().click(spacebar_x, spacebar_y);
    }

    private boolean isUpper(String str){
        return str.matches("^[A-Z]*$");
    }
    private boolean isSpecialCharacter(String str){
        if(TextUtils.isEmpty(str)){
            return false;
        }
        for(int i=0; i < str.length(); i++){
            if(!Character.isLetterOrDigit(str.charAt(i))){
                return true;
            }
        }
        return false;
    }
}