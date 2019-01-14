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
    private String filePath = "/sdcard/QA/InputTest/TestWord.xls";
    private FileInputStream fis;
    private ArrayList<String> wordList;
    private int getContencs = 0; // 행에서 몇번째 데이터를 갖고오는지?

    public TestCaseParser(String mode){
        int rows = 0;

        try {
            wordList = new ArrayList<>();
            fis = new FileInputStream(filePath);
            Log.i("@@@", "isAvailable : " + fis.available());
            workbook = new HSSFWorkbook(fis);
            if(mode.equals("kor")){
                sheet = workbook.getSheet("한국어");
                getContencs = 2;
            }else if(mode.equals("eng")){
                sheet = workbook.getSheet("영어");
                getContencs = 0;
            }

            rows = sheet.getPhysicalNumberOfRows();

            // 행수(1부터 시작)
            for(int i=1; i<rows; i++){
                HSSFRow row = sheet.getRow(i);
                if(row != null){
                    // 1행에서 2번째열(Word Separate) 값 가져오기
                    HSSFCell cell = sheet.getRow(i).getCell(getContencs);
                    wordList.add(cell.getStringCellValue());
                    Log.i("@@@", cell.getStringCellValue());
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.i("@@@", "FileNotFoundException");
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("@@@", "IO Exception");
        }
    }

    public ArrayList<String> getWordList(){
        return wordList;
    }
}
