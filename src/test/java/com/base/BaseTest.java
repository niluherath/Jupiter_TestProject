package com.base;
import java.io.File;
import java.io.IOException;

import com.github.javafaker.Faker;
import com.utils.ConfigLoader;
import com.utils.DateUtil;
import com.utils.ExtentManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;

import org.testng.annotations.*;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public abstract class BaseTest {

    protected WebDriver driver;
    protected ExtentSparkReporter spark;
    protected ExtentReports extent;
    protected ExtentTest logger;

    private static String PROPERTY_USER_DIR = "user.dir";
    Faker faker = new Faker();
    protected String name = faker.name().fullName();
    protected String emailAddress = faker.name().firstName()+"@gmail.com";
    protected String message = "this is test automation";


    @BeforeClass
    public void startReport() {
      //  extent = new ExtentReports();
      //  String timeStamp = DateUtil.getDateInyyyyMMddhhmmssFormat();

     //   spark = new ExtentSparkReporter(System.getProperty(PROPERTY_USER_DIR) + "/target/ExtentReport."+timeStamp+".html");
     //   extent.attachReporter(spark);


        extent = ExtentManager.getInstance();
        extent.setSystemInfo("Host Name", "Jupiter toys project");
        extent.setSystemInfo("Environment", "");
        extent.setSystemInfo("User Name", "Nilu Herath");
        //spark.config().setDocumentTitle("Jupiter toys Project");
        //spark.config().setReportName("Jupiter toys Test Project ");
        //spark.config().setTheme(Theme.STANDARD);
    }

    public static String getScreenShot(WebDriver driver, String screenshotName) throws IOException {
        String timeStamp = DateUtil.getDateInyyyyMMddhhmmssFormat();
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String destination = System.getProperty(PROPERTY_USER_DIR) + "/Screenshots/" + screenshotName + timeStamp + ".png";
        File finalDestination = new File(destination);
        FileUtils.copyFile(source, finalDestination);
        return destination;
    }

    @BeforeMethod
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
       // options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.navigate().to(ConfigLoader.getInstance().getBaseURL());
    }

    @AfterMethod
    public synchronized void getResult(ITestResult result) throws Exception{
        if(result.getStatus() == ITestResult.FAILURE){
            logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
            logger.log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));
            String screenshotPath = getScreenShot(driver, result.getName());
            logger.fail("Test Case Failed Snapshot is below " + logger.addScreenCaptureFromPath(screenshotPath));
        }
        else if(result.getStatus() == ITestResult.SKIP){
            logger.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " - Test Case Skipped", ExtentColor.ORANGE));
        }
        else if(result.getStatus() == ITestResult.SUCCESS)
        {
            logger.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" Test Case PASSED", ExtentColor.GREEN));
        }
        driver.quit();
    }

    public void given(String given){
        logger.info("<b>Given</b>"+given);
    }

    public void when(String when){
        logger.info("<b>When</b>"+when);
    }

    public void and(String and){
        logger.info("<b>And</b>"+and);
    }

    public void then(String then){
        logger.info("<b>Then</b>"+then);
    }

    @AfterClass
    public void endReport() {
        extent.flush();
    }

}
