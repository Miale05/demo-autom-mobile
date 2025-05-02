package org.mikidev;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features",
    glue = "org.mikidev.step",
    plugin = {"pretty", "html:target/cucumber-reports.html"}
)
public class TestRunner {
    // This class is used to run the Cucumber tests
    // The @RunWith annotation tells JUnit to use Cucumber as the test runner
    // The @CucumberOptions annotation allows you to specify options for the Cucumber test runner
    // In this case, we specify the location of the feature files and step definitions
    // We also specify a plugin to generate an HTML report of the test results
}