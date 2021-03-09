package driver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class BaseClass {
    private static WebDriver driver;
    public static ExtentReports reports;
    public static ExtentTest test;

    public static void openBrowser(String url){
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\main\\java\\webDrivers\\chromedriver.exe");
        driver =  new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(url);
        test.log(LogStatus.INFO, "Launching the browser","Loading the URL - "+url);
    }

    public static WebDriver getWebdriver(){
        return driver;
    }

    //Clickss on the element
    public static void click(WebElement element, String stepDetail){
        try{
            element.click();
            test.log(LogStatus.FAIL, stepDetail,"Successfully clicked on the element");
        }catch (Exception e){
            test.log(LogStatus.FAIL, stepDetail,"Unable to click on the element - "+e.getLocalizedMessage());
        }
    }

    //Checks elements existence
    public static void verifyElementExistence(WebElement element, String stepDetail){
        try {
            if (element.isDisplayed()) {
                test.log(LogStatus.PASS, stepDetail, "Expected element is displayed");
            } else {
                test.log(LogStatus.FAIL, stepDetail, "Expected element is not displayed");
            }
        } catch (Exception e) {
            test.log(LogStatus.ERROR, stepDetail, "Unexpected error occurred - " + e.getLocalizedMessage());
        }
    }

    // Checks if the radio/checkbox is checked/unchecked as per user expectation
    public static void isSelected(WebElement element, String stepDetail, boolean expectedResult){
        try {
            boolean selected=false;
            if (element.isSelected()) {
                selected=true;
            }
            if(selected==expectedResult) {
                if (expectedResult == true) {
                    test.log(LogStatus.PASS, stepDetail, "Expected radio/checkbox is selected");
                } else if(expectedResult == false) {
                    test.log(LogStatus.PASS, stepDetail, "Expected radio/checkbox is not selected");
                }
            }else{
                test.log(LogStatus.FAIL, stepDetail, "Expected radio/checkbox is not as per the expected value");
            }
        } catch (Exception e) {
            test.log(LogStatus.ERROR, stepDetail, "Unexpected error occurred - " + e.getLocalizedMessage());
        }
    }


    // Takes the screenshot of screen
    public static void getscreenshot(String stepDetail){
        try {
            test.log(LogStatus.INFO, stepDetail, test.addScreenCapture(capture(getWebdriver())));
        }catch (Exception e){
            test.log(LogStatus.ERROR, stepDetail, "Unexpected error occurred while capturing screenshot - " + e.getLocalizedMessage());
        }
    }

    //Initiating and ending the report
    public static void initiateTest(String testName){
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        reports=new ExtentReports(System.getProperty("user.dir")+"\\src\\test\\Reports\\ExtentReportResults"+timeStamp+".html");
        test=reports.startTest(testName);
    }

    public static void endTest()
    {
        reports.endTest(test);
        reports.flush();
    }

    public static String capture(WebDriver driver) throws IOException {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File Dest = new File(System.getProperty("user.dir")+"\\src\\test\\Reports\\screenshots\\screenshot" + System.currentTimeMillis()+".png");
        String errflpath = Dest.getAbsolutePath();
        FileUtils.copyFile(scrFile, Dest);
        return errflpath;
    }
}
