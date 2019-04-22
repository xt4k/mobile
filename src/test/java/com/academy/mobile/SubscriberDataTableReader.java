package com.academy.mobile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class SubscriberDataTableReader {

    private String sheetName;
    private String url;
    private List<String> fname;
    private List<String> lname;
    private List<String> gender;
    private List<String> age;
    private List<String> action;


    public SubscriberDataTableReader(String confParameterName, String sheetName) {
        String url;
        List<String> fname = new ArrayList();
        List<String> lname = new ArrayList();
        List<String> gender = new ArrayList();
        List<String> age = new ArrayList();
        List<String> action = new ArrayList();
        File file = new File( confParameterName );

        try {
            XSSFWorkbook workbook = new XSSFWorkbook( new FileInputStream( file ) );
            XSSFSheet sheet = workbook.getSheet( sheetName );
            url=  sheet.getRow(1 ).getCell( 0 ).getStringCellValue();
            for (int i = 1; i <= sheet.getLastRowNum(); ++i) {
                XSSFRow parRow = sheet.getRow( i );
               // url= parRow.getCell( 0 ).getStringCellValue();

                fname.add( parRow.getCell( 1 ).getStringCellValue() );
                lname.add( parRow.getCell( 2 ).getStringCellValue() );
                gender.add( parRow.getCell( 3 ).getStringCellValue() );

                age.add( parRow.getCell( 4 ).getStringCellValue() );

                action.add( parRow.getCell( 5 ).getStringCellValue() );
                this.url = url;
                  }
        } catch (IOException var12) {
            System.out.println( "Something wrong" );
            var12.printStackTrace();
        }


        this.fname = fname;
        this.lname = lname;
        this.gender = gender;
        this.age = age;
        this.action = action;

        System.out.println( "read from excel conf file" );
    }

    public String toString() {
        return "TestConfReader. sheetName: " + this.sheetName + ", url:" + this.url.toString() + ", fname: " + this.fname.toString() + ", lname: " + this.lname.toString() + ", gender=" + this.gender.toString()+ ", age=" + this.age.toString()+ ", action=" + this.action.toString();
    }

    public String getUrl() {   return url;  }

    public int getSize() {   return action.size();  }

    public String getGender(int i) { return gender.get(i); }

    public String getAction(int i) { return action.get(i); }

    public String getAge(int i) { return age.get(i); }

    public String getFname(int i) {
        return (String) this.fname.get( i );
    }

    public String getLname(int i) {
        return (String) this.lname.get( i );
    }
}