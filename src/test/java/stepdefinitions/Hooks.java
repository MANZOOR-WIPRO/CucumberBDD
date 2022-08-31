package stepdefinitions;

import factory.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import utils.ConfigReader;

import java.util.Properties;

public class Hooks {
   private DriverFactory driverFactory;
    private WebDriver driver;
    private ConfigReader configReader;
    Properties prop;

    @Before(order=0)//Annotations from Cucumber not Junit or TestNG
    public  void getProperty(){
        System.out.println("In Before hook order 0");
        configReader = new ConfigReader();
        prop = configReader.init_prop();
    }
    @Before(order=1)
    public  void launchBrowser(){
        String browserName = prop.getProperty("browser");
        System.out.println("In Before hook order 1");
        System.out.println("Init driver " + browserName);
        driverFactory = new DriverFactory();
        driver = driverFactory.init_driver(browserName);
        System.out.println("Inited driver " + browserName);
    }

    @After(order = 0)
    public void quitBrowser(){
        driver.quit();
    }
    @After(order = 1)
    public void tearDown(Scenario scenario){
        //take screenshot
        String screenShotName = scenario.getName().replaceAll(" ","_");
        byte [] sourcePath = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES); //also BASE64 inplace of BYTES
        scenario.attach(sourcePath, "image/png", screenShotName);
    }


}
