package com.phillit.qa.basicinputtest.Common.KeyType;

import android.content.Context;
import android.util.Log;

import com.phillit.qa.basicinputtest.Common.Utility;

public class SKY extends Chunjiin {
    public SKY(Utility utility, Context context, int screenOrientation, int language) {
        super(utility, context, screenOrientation, language);
    }

    @Override
    public void input(String args) {
        super.input(args);
    }

    @Override
    public void input(StringBuffer args) {

    }

    @Override
    public boolean consonantCrash(String targetChar, String nextChar) {
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
        else if(targetChar.equals("ㅁ") || targetChar.equals("ㅅ") || targetChar.equals("ㅆ")){
            if(nextChar.equals("ㅁ") || nextChar.equals("ㅅ") || nextChar.equals("ㅆ")){
                return true;
            }
        }
        else if(targetChar.equals("ㅈ") || targetChar.equals("ㅊ") || targetChar.equals("ㅉ")){
            if(nextChar.equals("ㅈ") || nextChar.equals("ㅊ") || nextChar.equals("ㅉ")){
                return true;
            }
        }
        else if(targetChar.equals("ㅂ") || targetChar.equals("ㅍ") || targetChar.equals("ㅃ")){
            if(nextChar.equals("ㅂ") || nextChar.equals("ㅍ") || nextChar.equals("ㅃ")){
                return true;
            }
        }
        else if(targetChar.equals("ㄴ") || targetChar.equals("ㄹ")){
            if(nextChar.equals("ㄴ") || nextChar.equals("ㄹ")){
                return true;
            }
        }
        else if(targetChar.equals("ㅇ") || targetChar.equals("ㅎ")){
            if(nextChar.equals("ㅇ") || nextChar.equals("ㅎ")){
                return true;
            }
        }
        return false;
    }
}
