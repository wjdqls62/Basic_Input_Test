package com.phillit.qa.basicinputtest.Common;
import android.content.Context;
import android.util.Log;

import com.phillit.qa.basicinputtest.R;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class TestCaseParser {
    private HSSFWorkbook workbook;
    private HSSFSheet sheet;
    private StringBuffer word;
    private String filePath = "/sdcard/QA/InputTest/TestWord.xls";
    private FileInputStream fis;
    private ArrayList<String> wordList;
    private int getContents = 0; // 행에서 몇번째 데이터를 갖고오는지?

    public TestCaseParser(String mode, Context context){
        //int rowLength = 0;
        filePath = context.getResources().getString(R.string.path_TestCase);
        word = new StringBuffer();

        try {
            wordList = new ArrayList<>();
            fis = new FileInputStream(filePath);
            workbook = new HSSFWorkbook(fis);
            if(mode.equals("kor")){
                sheet = workbook.getSheet("한국어");
                getContents = 2;
            }else if(mode.equals("eng")){
                sheet = workbook.getSheet("영어");
                getContents = 0;
            }else if(mode.equals("Env")){
                sheet = workbook.getSheet("Env");
                getContents = 1;
        }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.i("@@@", "FileNotFoundException");
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("@@@", "IO Exception");
        }
    }

    public StringBuffer getWord(int index){
        try{
            word.setLength(0);
            word.append(sheet.getRow(index).getCell(getContents).getStringCellValue());
        }catch (NullPointerException e){
            return null;
        }
        return word;
    }

    public TestPlan getTestPlan(TestPlan testPlan){
        // InternalTest Check
        if(sheet.getRow(0).getCell(getContents).getStringCellValue().equals("Y")){
            Log.i("@@@", "set isInternal true");
            testPlan.isInternalTest = true;
        }
        // 한국어 QWERTY(세로) 테스트 여부 확인
        if(sheet.getRow(4).getCell(getContents).getStringCellValue().equals("Y")){
            testPlan.KOR_QWERTY_PORTRAIT = true;
        }
        // 한국어 QWERTY(가로) 테스트 여부 확인
        if(sheet.getRow(5).getCell(getContents).getStringCellValue().equals("Y")){
            testPlan.KOR_QWERTY_LANDSCAPE = true;
        }
        // 한국어 천지인(세로) 테스트 여부 확인
        if(sheet.getRow(6).getCell(getContents).getStringCellValue().equals("Y")){
            testPlan.KOR_CHUNJIIN_PORTRAIT = true;
        }
        // 한국어 천지인(가로) 테스트 여부 확인
        if(sheet.getRow(7).getCell(getContents).getStringCellValue().equals("Y")){
            testPlan.KOR_CHUNJIIN_LANDSCAPE = true;
        }
        // 한국어 스카이(세로) 테스트 여부 확인
        if(sheet.getRow(8).getCell(getContents).getStringCellValue().equals("Y")){
            testPlan.KOR_SKY_PORTRAIT = true;
        }
        // 한국어 스카이(가로) 테스트 여부 확인
        if(sheet.getRow(9).getCell(getContents).getStringCellValue().equals("Y")){
            testPlan.KOR_SKY_LANDSCAPE = true;
        }
        // 한국어 나랏글(세로) 테스트 여부 확인
        if(sheet.getRow(10).getCell(getContents).getStringCellValue().equals("Y")){
            testPlan.KOR_NARAGUL_PORTRAIT = true;
        }
        // 한국어 나랏글(가로) 테스트 여부 확인
        if(sheet.getRow(11).getCell(getContents).getStringCellValue().equals("Y")){
            testPlan.KOR_NARAGUL_LANDSCAPE = true;
        }
        // 한국어 단모음(세로) 테스트 여부 확인
        if(sheet.getRow(12).getCell(getContents).getStringCellValue().equals("Y")){
            testPlan.KOR_DANMOUM_PORTRAIT = true;
        }
        // 한국어 단모음(가로) 테스트 여부 확인
        if(sheet.getRow(13).getCell(getContents).getStringCellValue().equals("Y")){
            testPlan.KOR_DANMOUM_LANDSCAPE = true;
        }
        // 영어 QWERTY(세로) 테스트 여부 확인
        if(sheet.getRow(17).getCell(getContents).getStringCellValue().equals("Y")){
            testPlan.ENG_QWERTY_PORTRAIT = true;
        }
        // 영어 QWERTY(가로) 테스트 여부 확인
        if(sheet.getRow(18).getCell(getContents).getStringCellValue().equals("Y")){
            testPlan.ENG_QWERTY_LANDSCAPE = true;
        }
        return testPlan;
    }

    public ArrayList<String> getWordList(){
        return wordList;
    }
}
