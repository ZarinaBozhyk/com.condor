package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

/**
 * #Summary:
 * #Author: Zarina_Bozhyk
 * #Author’s Email:
 * #Creation Date: 5/10/2023
 * #Comments:
 */
@CucumberOptions(features = "src/test/resources/features",
        glue = {"stepDefinitions"},
        tags = "@api or @ui",
        plugin = {"pretty",
                "html:target/cucumber-Report.html", "json:target/report.json"},
        monochrome = true)
public class TestNGRunner extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}