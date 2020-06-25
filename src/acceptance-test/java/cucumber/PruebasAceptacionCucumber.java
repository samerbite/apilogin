package cl.titicoctel.apilogin.cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources",
        tags = "~@ignoreScenario")
public class PruebasAceptacionCucumber {
}
