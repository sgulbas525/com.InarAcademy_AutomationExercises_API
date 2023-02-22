package Runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty",
        "html:target/cucumber-reports/TestCase_06.html",
        "json:target/cucumber-reports/TestCase_06.json",
        "rerun:target/rerun.txt"},
        features = "src/test/java/Features",
        glue = "StepDefs",
        tags = "@POSTToSearchProductWithoutSearchProductParameter_TestCase_06",
        dryRun = false)
public class CukesRunner {
}
