package pages;

import com.relevantcodes.extentreports.LogStatus;
import driver.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ContainerSearchPage extends BaseClass {

    private static ContainerSearchPage thisScript;

    public static synchronized ContainerSearchPage get(){
        if(thisScript==null){
            thisScript=new ContainerSearchPage();
        }
        thisScript=PageFactory.initElements(getWebdriver(),ContainerSearchPage.class);
        return thisScript;
    }

    public @FindBy(xpath="//button[text()='Containers']")
    WebElement container;

    public @FindAll(@FindBy(xpath="//div[text()='Images']/ancestor::div[1]//input[@value='store' and @type='checkbox']"))
    List<WebElement> verifiedPublisher;

    public @FindAll(@FindBy(xpath="//div[text()='Images']/ancestor::div[1]//input[@value='official' and @type='checkbox']"))
    List<WebElement> officialImages;

    public @FindBy(xpath="//div[contains(@data-testid,'currentFilters')]/div[text()='Publisher Content']")
    WebElement appliedFilterPublisherContent;

    public @FindAll(@FindBy(xpath="//div[text()='Categories']/ancestor::div[1]//input[@value='base' and @type='checkbox']"))
    List<WebElement> baseImages;

    public @FindAll(@FindBy(xpath="//div[text()='Categories']/ancestor::div[1]//input[@value='database' and @type='checkbox']"))
    List<WebElement> database;

    public @FindAll(@FindBy(xpath="//div[text()='Categories']/ancestor::div[1]//input[@value='analytics' and @type='checkbox']"))
    List<WebElement> analytics;

    public @FindAll(@FindBy(xpath="//div[text()='Categories']/ancestor::div[1]//input[@value='storage' and @type='checkbox']"))
    List<WebElement> storage;

    public @FindBy(xpath="//div[contains(@data-testid,'currentFilters')]/div[text()='Base Images']")
    WebElement appliedFilterBaseImages;

    public @FindBy(xpath="//div[contains(@data-testid,'currentFilters')]/div[text()='Databases']")
    WebElement appliedFilterDatabase;

    public void verifyContainerTab(){
        boolean flag=container.getAttribute("class").toString().contains("selected");
        if(flag==true){
            test.log(LogStatus.PASS, "Check if the control is in Containers Tab", "Control is in the Containers tab");
        }else{
            test.log(LogStatus.FAIL, "Check if the control is in Containers Tab", "Control is not in the Containers tab");
        }
    }
    public void verifyExistenceVerifiedPublisherCheckbox() {
        isElementPresent(verifiedPublisher,"Verify the existence of Verified Publisher checkbox");
    }
    public void verifyExistenceOfficialImagesCheckbox() {
        isElementPresent(officialImages,"Verify the existence of Official Images checkbox");
    }

    public void verifyExistenceAnalyticsCheckbox() {
        isElementPresent(analytics,"Verify the existence of Analytics checkbox");
    }
    public void verifyExistencStorageCheckbox() {
        isElementPresent(storage,"Verify the existence of Storage checkbox");
    }

    public void verifyExistenceBaseImagesCheckbox() {
        isElementPresent(baseImages,"Verify the existence of Base Images checkbox");
    }
    public void verifyExistenceDatabaseCheckbox() {
        isElementPresent(database,"Verify the existence of Official Images checkbox");
    }

    public void applyVerifiesPublisherFilter(){
        click(verifiedPublisher.get(0),"Click Verified Publisher checkbox to apply the filter");
        verifyElementExistence(appliedFilterPublisherContent,"Verify the Publisher Content filter is applied successfully");
        getscreenshot("Verified Publisher filter");
    }

    public void applyDatabaseFilter(){
        click(database.get(0),"Click Database checkbox to apply the filter");
        verifyElementExistence(appliedFilterPublisherContent,"Verify the Database filter is applied successfully");
        getscreenshot("Database filter");
    }

    public void applyBaseImageFilter(){
        click(baseImages.get(0),"Click Base Images checkbox to apply the filter");
        verifyElementExistence(appliedFilterPublisherContent,"Verify the Base Images filter is applied successfully");
        getscreenshot("Base Images filter");
    }

    public void removeDatabaseFilter(){
        click(appliedFilterDatabase,"Click 'x' to remove the Database filter");
        isSelected(database.get(0),"Validtae of the Database checkbox is unchecked",false);
        getscreenshot("After removing Database filter");
    }
    public void isElementPresent(List<WebElement> elements, String stepDetails){
        if(elements.size()>0){
            test.log(LogStatus.PASS, stepDetails, "Element is present");
        }else{
            test.log(LogStatus.FAIL, stepDetails, "Element is not present");
        }
    }
}
