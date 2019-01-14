package com.phillit.qa.basicinputtest.Common;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.util.Log;

import com.phillit.qa.basicinputtest.Common.KeyType.KeyType;
import com.phillit.qa.basicinputtest.R;
import org.xmlpull.v1.XmlPullParserException;
import java.io.IOException;
import java.util.HashMap;

public class XmlParser {
    private XmlResourceParser parser;
    private Key key;
    private HashMap<String, Key> keyList;
    private Context context;
    private Resources resource;
    private int parsingMode, language = 0;


    public XmlParser(Context context, int parsingMode, int language){
        this.context = context;
        this.parsingMode = parsingMode;
        resource = context.getResources();
        this.language = language;
        try {
            parseXML();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
    }

    public XmlParser(Context context, int parsingMode){
        this.context = context;
        this.parsingMode = parsingMode;
        resource = context.getResources();
        try {
            parseXML();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
    }

    public HashMap<String,Key> getKeyList(){
        return keyList;
    }

    private void parseXML() throws IOException, XmlPullParserException {

        if(parsingMode == KeyType.QWERTY_PORTRAIT){
            if(language == KeyType.QWERTY_KOREA){
                parser = resource.getXml(R.xml.kor_qwerty_portrait);
            }else if(language == KeyType.QWERTY_ENGLISH){
                parser = resource.getXml(R.xml.eng_qwerty_portrait);
            }

        }else if(parsingMode == KeyType.QWERTY_LANDSCAPE){
            if(language == KeyType.QWERTY_KOREA){
                parser = resource.getXml(R.xml.kor_qwerty_landscape);
            }else if(language == KeyType.QWERTY_ENGLISH){
                parser = resource.getXml(R.xml.eng_qwerty_portrait);
            }
        }

        keyList = new HashMap<>();

        int eventType = parser.getEventType();

        while (eventType != XmlResourceParser.END_DOCUMENT){
            String tagName = parser.getName();

            if(eventType == XmlResourceParser.START_TAG){
                if(tagName.equals("Value")){
                    key = new Key();
                    key.setKeyValue(parser.nextText());
                }else if(tagName.contains("X")){
                    key.setX(Integer.parseInt(parser.nextText()));
                }else if(tagName.contains("Y")){
                    key.setY(Integer.parseInt(parser.nextText()));
                }
            }else if(eventType == XmlResourceParser.END_TAG){
                if(tagName.equals("Key")){
                    // Parsing된 Key 정보 출력
                    key.logKeyinfo();
                    keyList.put(key.keyValue, key);
                    key = null;
                }
            }
            eventType = parser.next();
        } // End while
    }
}