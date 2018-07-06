import TestObjects.World;
import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import static TestObjects.World.testSession;

@RunWith(Cucumber.class)

@CucumberOptions(
        features = "src/test/features",
        glue = "TestObjects/StepDefinitions",
        //tags = "@regression",
        tags = "@debug1",
        dryRun = false,
        strict = false,
        snippets = SnippetType.UNDERSCORE,
        plugin = {"json:target/cucumber.json"})
public class DebugTestRunner {
    @BeforeClass
    public static void start() {
    }

    @AfterClass
    public static void tearDown() {
        if (testSession != null) {
            testSession.quit();
        }
        if (World.service != null) {
            World.service.stop();
        }
    }
}
