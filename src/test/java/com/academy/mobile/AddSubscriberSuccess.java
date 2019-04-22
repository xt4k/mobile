package com.academy.mobile;

import com.academy.framework.BaseTest;
import com.academy.framework.TestConfReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import static org.testng.Assert.assertEquals;


public class AddSubscriberSuccess {
    // private final static Logger LOG = LogManager.getLogger( com.academy.mobile.AddSubscriberSuccess.class );
    // protected String baseUrl = "http://localhost:8081/subscribers";
    @Test
    public static void main(String[] args) throws Exception {
        String commonProperties = System.getProperty( "common.cfg" );
        Properties properties = new Properties();
        try {
            properties.load( new FileReader( "C:/Users/xt4k/IdeaProjects/Mobile/mobile/cfg/common.properties" ) );
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.setProperty( "webdriver.chrome.driver", properties.getProperty( "chrome.driver" ) );
        // disable notifications
        // Map<String, Object> prefs = new HashMap<String, Object>();
        //  prefs.put( "profile.default_content_setting_values.notifications", 2 );
        // prefs.put("intl.accept_languages", "en,en_US");
        //  ChromeOptions options = new ChromeOptions();
        //  options.setExperimentalOption( "prefs", prefs );
        WebDriver driver = new ChromeDriver();// options );


        SubscriberDataTableReader subscriberDataTableReader = new SubscriberDataTableReader( "C:/Users/xt4k/IdeaProjects/Mobile/mobile/src/main/resources/subscribers.xlsx", "add_subscriber" );

        driver.get( subscriberDataTableReader.getUrl() );

        for (int i = 0; i < subscriberDataTableReader.getSize(); i++) {

            WebElement addButton = driver.findElement( By.id( "add" ) );
            addButton.click();

            WebElement fname = driver.findElement( By.id( "fname" ) );
            fname.clear();
            Random random = new Random();
            int r = random.nextInt( 1000 );
            fname.sendKeys( subscriberDataTableReader.getFname( i ) + r );

            WebElement lname = driver.findElement( By.id( "lname" ) );
            lname.clear();
            lname.sendKeys( subscriberDataTableReader.getLname( i ) + r );

            WebElement gender = driver.findElement( By.id( subscriberDataTableReader.getGender( i ) ) );
            gender.click();

            WebElement age = driver.findElement( By.id( "age" ) );
            age.clear();
            Random random2 = new Random();
            int r2 = random2.nextInt( 25 );
            age.sendKeys( String.valueOf( r2 ) + subscriberDataTableReader.getAge( i ) );

            if (subscriberDataTableReader.getAction( i ).equals( "submit" )) {
                WebElement submit = driver.findElement( By.cssSelector( "body > div > form > button" ) );
                submit.click();
            } else
                driver.navigate().back();


        }


    }


}

