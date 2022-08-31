package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;


@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/resources/features/",
		glue = "stepdefinitions",//provide the path to the package. It will look for step defs in all the classes in the package.
		plugin = { "pretty", "json:reports/cucumber/Cucumber.json",
				   "html:reports/cucumber/Cucumber.html",
				//"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
				//"timeline:test-output-thread/"
		},
		monochrome = true)
public class TestRunner extends AbstractTestNGCucumberTests {

}