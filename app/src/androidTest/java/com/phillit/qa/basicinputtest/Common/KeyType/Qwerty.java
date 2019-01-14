package com.phillit.qa.basicinputtest.Common.KeyType;

import android.content.Context;
import com.phillit.qa.basicinputtest.Common.Key;
import com.phillit.qa.basicinputtest.Common.Utility;
import com.phillit.qa.basicinputtest.Common.XmlParser;
import java.util.HashMap;

public class Qwerty extends KeyType {
    private Utility utility;
    private HashMap<String, Key> keyList;
    private XmlParser parser;
    private int spacebar_x, spacebar_y, language;
    private int shift_x, shift_y;


    public Qwerty(Utility utility, Context context, int screenOrientation, int language){
        this.utility = utility;
        parser = new XmlParser(context, screenOrientation, language);
        this.language = language;
        keyList = parser.getKeyList();
        spacebar_x = keyList.get("^").keyCordinates.get(0).x;
        spacebar_y = keyList.get("^").keyCordinates.get(0).y;
        if(language == KeyType.QWERTY_ENGLISH){
            shift_x = keyList.get("↑").keyCordinates.get(0).x;
            shift_y = keyList.get("↑").keyCordinates.get(0).y;
        }
    }

    @Override
    public void input(String args) {
        char[] arrChar = args.toCharArray();
        Key key;

        for(int i=0; i<arrChar.length; i++){
            String targetChar = String.valueOf(arrChar[i]);

            if(language == KeyType.QWERTY_ENGLISH && isUpper(targetChar)){
                utility.getUiDevice().click(shift_x, shift_y);
                key = keyList.get(targetChar.toLowerCase());
            }else{
                key = keyList.get(targetChar);
            }

            for(int j=0; j<key.keyCordinates.size(); j++){
                utility.getUiDevice().click(key.keyCordinates.get(j).x, key.keyCordinates.get(j).y);
            }
        }
        // 단어입력 후 한칸띄우기(Spacebar)
        utility.getUiDevice().click(spacebar_x, spacebar_y);
    }

    private boolean isUpper(String str){
        return str.matches("^[A-Z]*$");
    }
}