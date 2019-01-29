package com.phillit.qa.basicinputtest.Common;
import android.util.Log;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
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
    private HSSFRow row;
    private HSSFCell cell;
    private int getContents = 0; // 행에서 몇번째 데이터를 갖고오는지?
    private int rowLength = 0;

    public TestCaseParser(String mode){
        //int rowLength = 0;
        word = new StringBuffer();

        try {
            wordList = new ArrayList<>();
            fis = new FileInputStream(filePath);
            Log.i("@@@", "isAvailable : " + fis.available());
            workbook = new HSSFWorkbook(fis);
            if(mode.equals("kor")){
                sheet = workbook.getSheet("한국어");
                getContents = 2;
            }else if(mode.equals("eng")){
                sheet = workbook.getSheet("영어");
                getContents = 0;
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

    public ArrayList<String> getWordList(){
        return wordList;
    }
}
