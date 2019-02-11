package com.phillit.qa.basicinputtest.Common.KeyType;

import android.content.Context;
import android.util.Log;

import com.phillit.qa.basicinputtest.Common.Key;
import com.phillit.qa.basicinputtest.Common.Utility;
import com.phillit.qa.basicinputtest.Common.XmlParser;
import java.util.HashMap;

public class Chunjiin extends KeyType{
    private Utility utility;
    private HashMap<String, Key> normalKeyList, specialKeyList;
    private XmlParser parser;
    private Qwerty_Special_Character specialCharacter;
    private String lastStr = "";
    private int spacebar_x, spacebar_y, language;
    boolean isSpecialChar = false;

    public Chunjiin(Utility utility, Context context, int screenOrientation, int language){
        this.utility = utility;
        parser = new XmlParser(context, screenOrientation, language);
        this.language = language;

        // 문자키, 특수문자키를 XML로부터 읽어온다
        normalKeyList = parser.getKeyList();
        specialKeyList = parser.getSpecialKeyList();
        specialCharacter = new Qwerty_Special_Character(utility, context, screenOrientation, language, specialKeyList);

        // 단어입력 후 띄어쓰기를 위해 Spacebar의 좌표를 Preload
        spacebar_x = normalKeyList.get("^").keyCordinates.get(0).x;
        spacebar_y = normalKeyList.get("^").keyCordinates.get(0).y;
    }

    private void typingKeyboard(char[] arrChar){
        Key key;

        for(int i=0; i<arrChar.length; i++){
            String targetChar = String.valueOf(arrChar[i]);
            String nextChar = "";

            isSpecialChar = utility.isSpecialCharacter(targetChar);

            // 특수문자가 아닐경우
            if(!isSpecialChar){
                key = normalKeyList.get(targetChar);

                for(int j=0; j<key.keyCordinates.size(); j++){
                    utility.getUiDevice().click(key.keyCordinates.get(j).x, key.keyCordinates.get(j).y);
                }
            }
            // 특수문자의 경우
            else{
                specialCharacter.input(targetChar);
            }

            // 자음충돌의 경우 현재문자와 다음 입력될 문자를 비교하여 동일하거나 같은 키패드에서 입력될때 컴포징해제
            if(i != arrChar.length-1){
                nextChar = String.valueOf(arrChar[i+1]);
                if(consonantCrash(targetChar, nextChar)){
                    utility.getUiDevice().click(spacebar_x, spacebar_y);
                }
            }
        }
    }

    // 현재 입력자음과 다음입력될 자음을 비교
    private boolean consonantCrash(String targetChar, String nextChar){
        if(targetChar.equals("ㄱ") || targetChar.equals("ㅋ") || targetChar.equals("ㄲ")){
            if(nextChar.equals("ㄱ") || nextChar.equals("ㅋ") || nextChar.equals("ㄲ")){
                return true;
            }
        }
        else if(targetChar.equals("ㄷ") || targetChar.equals("ㅌ") || targetChar.equals("ㄸ")){
            if(nextChar.equals("ㄷ") || nextChar.equals("ㅌ") || nextChar.equals("ㄸ")){
                return true;
            }
        }
        else if(targetChar.equals("ㅂ") || targetChar.equals("ㅍ") || targetChar.equals("ㅃ")){
            if(nextChar.equals("ㅂ") || nextChar.equals("ㅍ") || nextChar.equals("ㅃ")){
                return true;
            }
        }
        else if(targetChar.equals("ㅅ") || targetChar.equals("ㅎ") || targetChar.equals("ㅆ")){
            if(nextChar.equals("ㅅ") || nextChar.equals("ㅎ") || nextChar.equals("ㅆ")){
                return true;
            }
        }
        else if(targetChar.equals("ㅈ") || targetChar.equals("ㅊ") || targetChar.equals("ㅉ")){
            if(nextChar.equals("ㅈ") || nextChar.equals("ㅊ") || nextChar.equals("ㅉ")){
                return true;
            }
        }
        else if(targetChar.equals("ㄴ") || targetChar.equals("ㄹ")){
            if(nextChar.equals("ㄴ") || nextChar.equals("ㄹ")){
                return true;
            }
        }
        else if(targetChar.equals("ㅇ") || targetChar.equals("ㅁ")){
            if(nextChar.equals("ㅇ") || nextChar.equals("ㅁ")){
                return true;
            }
        }
        return false;
    }

    @Override
    public void input(String args) {
        char[] target = args.toCharArray();
        lastStr = String.valueOf(target[target.length-1]);

        typingKeyboard(target);

        if(utility.isNumber(lastStr) || utility.isSpecialCharacter(lastStr)){
            utility.getUiDevice().click(spacebar_x, spacebar_y);
        }else{
            utility.getUiDevice().click(spacebar_x, spacebar_y);
            utility.getUiDevice().click(spacebar_x, spacebar_y);
        }
    }

    @Override
    public void input(StringBuffer args) {

    }
}
