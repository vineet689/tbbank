package runner;
 
import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class) 
//@Cucumber.Options(format = {"pretty", "html:target/cucumber"}) 


@CucumberOptions (
			features= "features",
			glue= "stepDefination",
					plugin = {"html:target/cucumber-html-report"}
			)




public class RunTest2 { }