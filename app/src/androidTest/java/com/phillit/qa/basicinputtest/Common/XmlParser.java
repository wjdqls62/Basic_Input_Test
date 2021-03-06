package com.phillit.qa.basicinputtest.Common;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;

import com.phillit.qa.basicinputtest.Common.DeviceType.DeviceType;
import com.phillit.qa.basicinputtest.Common.KeyType.KeyType;
import com.phillit.qa.basicinputtest.R;
import org.xmlpull.v1.XmlPullParserException;
import java.io.IOException;
import java.util.HashMap;

public class XmlParser {
    private XmlResourceParser parser;
    private Key key;
    private HashMap<String, Key> keyList;
    private HashMap<String, Key> specialKeyList;
    private Context context;
    private Resources resource;
    private Device device;
    private int parsingMode, language = 0;
    private String deviceModelName;


    public XmlParser(Context context, int parsingMode, int language, Device device){
        this.context = context;
        this.parsingMode = parsingMode;
        resource = context.getResources();
        this.device = device;
        this.language = language;
        deviceModelName = device.getDeviceModelName();
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

    public HashMap<String,Key> getSpecialKeyList(){
        return specialKeyList;
    }

    private void parseXML() throws IOException, XmlPullParserException {

        if(deviceModelName.equals(DeviceType.NEXUS5)){
            // 일반문자
            if(parsingMode == KeyType.PORTRAIT){
                if(language == KeyType.KOR_QWERTY){
                    parser = resource.getXml(R.xml.nx5_kor_qwerty_portrait);
                }else if(language == KeyType.ENG_QWERTY){
                    parser = resource.getXml(R.xml.nx5_eng_qwerty_portrait);
                }else if(language == KeyType.KOR_CHUNJIIN){
                    parser = resource.getXml(R.xml.nx5_kor_chunjiin_portrait);
                }else if(language == KeyType.KOR_SKY){
                    parser = resource.getXml(R.xml.nx5_kor_sky_portrait);
                }else if(language == KeyType.KOR_NARAGUL){
                    parser = resource.getXml(R.xml.nx5_kor_naragul_portrait);
                }else if(language == KeyType.KOR_DANMOUM){
                    parser = resource.getXml(R.xml.nx5_kor_danmoum_portrait);
                }

            }else if(parsingMode == KeyType.LANDSCAPE){
                if(language == KeyType.KOR_QWERTY){
                    parser = resource.getXml(R.xml.nx5_kor_qwerty_landscape);
                }else if(language == KeyType.ENG_QWERTY){
                    parser = resource.getXml(R.xml.nx5_eng_qwerty_landscape);
                }else if(language == KeyType.KOR_CHUNJIIN){
                    parser = resource.getXml(R.xml.nx5_kor_chunjiin_landscape);
                }else if(language == KeyType.KOR_SKY){
                    parser = resource.getXml(R.xml.nx5_kor_sky_landscape);
                }else if(language == KeyType.KOR_NARAGUL){
                    parser = resource.getXml(R.xml.nx5_kor_naragul_landscape);
                }else if(language == KeyType.KOR_DANMOUM){
                    parser = resource.getXml(R.xml.nx5_kor_danmoum_landscape);
                }
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
                    //key.logKeyinfo();
                    keyList.put(key.keyValue, key);
                    key = null;
                }
            }
            eventType = parser.next();
        } // End while

        if(deviceModelName.equals(DeviceType.NEXUS5)){
            // 특수문자
            if(parsingMode == KeyType.PORTRAIT){
                parser = resource.getXml(R.xml.nx5_common_special_character_portrait);
            }else if(parsingMode == KeyType.LANDSCAPE){
                parser = resource.getXml(R.xml.nx5_common_special_character_landscape);
            }
        }

        specialKeyList = new HashMap<>();

        eventType = parser.getEventType();

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
                    //key.logKeyinfo();
                    specialKeyList.put(key.keyValue, key);
                    key = null;
                }
            }
            eventType = parser.next();
        } // End while
    }
}