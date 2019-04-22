package com.academy.mobile;

import com.academy.framework.BaseTest;
import com.academy.framework.util.PropertyReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Random;

public class InsertNewSubscriber  extends BaseTest{
    private String baseUrl = "http://localhost:8081/subscribers";

    @DataProvider
    public static Object[][] testSuccessInsertNewSubscriber() {
            String dataTablePath = PropertyReader.from("datatable").getProperty("datatable-path");
            return new Object[][] {{dataTablePath}};
        }

    @Test(dataProvider = "testSuccessInsertNewSubscriber")
    public void InsertNewSubscriber() {
        System.out.println( "Start inserting subscriber test" );

        SubscriberDataTableReader subscriberDataTableReader = new SubscriberDataTableReader("C:/Users/xt4k/IdeaProjects/Mobile/mobile/src/main/resources/subscribers.xlsx","add_subscriber");

        driver.get( subscriberDataTableReader.getUrl() );

        for (int i =0;i<subscriberDataTableReader.getSize();i++)
        {

            WebElement addButton = driver.findElement( By.id( "add" ) );
            addButton.click();

            WebElement fname = driver.findElement( By.id( "fname" ) );
            fname.clear();
            Random random = new Random();
            int r = random.nextInt( 1000 );
            fname.sendKeys(subscriberDataTableReader.getFname( i ) + r );

            WebElement lname = driver.findElement( By.id( "lname" ) );
            lname.clear();
            lname.sendKeys(subscriberDataTableReader.getLname( i ) + r );

            WebElement gender = driver.findElement( By.id(subscriberDataTableReader.getGender( i ) ) );
           gender.click();

            WebElement age = driver.findElement( By.id( "age" ) );
            age.clear();
            Random random2 = new Random();
            int r2 = random2.nextInt( 25 );
            age.sendKeys( String.valueOf( r2)+ subscriberDataTableReader.getAge( i ) );

            if (subscriberDataTableReader.getAction( i ).equals( "submit" ))
            {
                WebElement submit = driver.findElement(By.cssSelector( "body > div > form > button" ) );
                submit.click();
            }
            else
                driver.navigate().back();


        }




    }
}
