package stepDefination; 

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By; 
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Annotation {
	
   WebDriver driver = null; 
   Properties prop;
   @Given("^user open the browser$") 
   public void openBrowser() { 
	   System.setProperty("webdriver.chrome.driver", "F://Data//ChromeDriver//chromedriver.exe");
	   driver = new ChromeDriver();
	   driver.manage().timeouts().implicitlyWait(50,TimeUnit.SECONDS);
	   prop = new Properties();
		try{
			prop.load(new FileInputStream("config.properties"));
			System.out.println("Reading Config.....");
		} 
  catch(Exception e){
	   System.out.println("Unable to Read Config......");
  }
       } 
   
   
   @When("^user opens tdbank site page$") 
   public void tBankSiteOpen() { 
	   String baseUrl= prop.getProperty("baseurl");
	  driver.navigate().to(baseUrl); 
	 String title= driver.getTitle();
	 if(driver.getTitle().contentEquals("TD Bank | Personal Banking, Small Business Banking, Mortgages and Loans, investments")) { 
		 System.out.println("Site Opened sucessfully its title is:" +title);
      } else { 
         System.out.println("Test Fail- Site not opened"); 
      }  
   } 
   
   @When("^mouse over On Banking navigator and click Checking$") 
   public void mouseoverBanking() {   
  	    Actions action = new Actions(driver);
		   	WebElement bankingLink = driver.findElement(By.xpath("//nav[@id='td-nav-level2']/ul/li[2]/span"));
			action.moveToElement(bankingLink).moveToElement(bankingLink).click().build().perform(); //This opens menu list		
		   /* if(driver.findElement(By.xpath("//div[@id='ens_landingCallout']/div/div[2]/a/span")).isDisplayed()){
		        driver.findElement(By.xpath("//div[@id='ens_landingCallout']/div/div[2]/a/span")).click();
		    }
		    else{System.out.println("Landing Call out not found!!");}  */
			String test= driver.findElement(By.xpath("//a[contains(@href, '/business/checking.html')]")).getText();
			driver.findElement(By.xpath("//div[@id='ens_landingCallout']/div/div[2]/a/span")).isDisplayed();
		        driver.findElement(By.xpath("//div[@id='ens_landingCallout']/div/div[2]/a/span")).click();
			System.out.println("find checking link: " +test);
			
			driver.findElement(By.xpath("//a[contains(@href, '/business/checking.html')]")).click();
   }
   
	
	
	@Then("^user Select region$")
	public void selectResgion(){
		String StateResgion= prop.getProperty("stateResgion");
		String City= prop.getProperty("city");
		    new Select(driver.findElement(By.id("state"))).selectByVisibleText(StateResgion);
	        new Select(driver.findElement(By.id("city"))).selectByVisibleText(City);
	}
	
	//user Select New York City
	@Then("^user Select New York City$")
	public void selectCity(){
	        new Select(driver.findElement(By.id("city"))).selectByVisibleText("New York City");
	}
	
	
	@Then("^user click on go$")
	public void click_button_Go(){
		 driver.findElement(By.name("Go")).click();
	}
	
	
	@Then("^user verify page title Commercial Checking Accounts$")
	public void Verify_Page_title(){
		// Commercial Checking Accounts
		String title= driver.getTitle();
		 if(driver.getTitle()=="Commercial Checking Accounts") { 
			 System.out.println("Page title verified sucessfully, its title is:" +title);
	      } else { 
	         System.out.println("Test Fail- Page Title not macth"); 
	      }
				
	}
	
	
	@Then("^user click Get started now$")
	public void clickButton_GetStarted() {
		driver.findElement(By.cssSelector("span.td-button-label")).click();
			
	}
	
	@Then("^User verify that you are on Get Started at TD Bank$")
	public void Verify_GetStartedPage(){
	 if(driver.findElement(By.id("contactName")).isDisplayed() && driver.getTitle()=="Request a call from a Business Banking Specialist") { 
		 System.out.println("You are on Get Started Page");
      } else { 
         System.out.println("Test Fail- Seems you are not on correct location"); 
      }
	}
	
	
	@Then("^user fill every required fields")
	public void fillForm(){
		String contactName= prop.getProperty("contactname");
		String telePhone= prop.getProperty("telephone");
		String State= prop.getProperty("state");
		String emailId=prop.getProperty("email");
		String BusinessName = prop.getProperty("businessName");
		String YearEstablished =prop.getProperty("yearEstablished");
		String dayTime = prop.getProperty("daytime");
		String additionalDetails = prop.getProperty("additionaldetails");	
		driver.findElement(By.id("contactName")).clear();
	    driver.findElement(By.id("contactName")).sendKeys(contactName);
	    driver.findElement(By.id("telephone")).clear();
	    driver.findElement(By.id("telephone")).sendKeys(telePhone);
	    new Select(driver.findElement(By.id("state"))).selectByVisibleText(State);
	    driver.findElement(By.id("email")).clear();
	    driver.findElement(By.id("email")).sendKeys(emailId);
	    driver.findElement(By.id("businessName")).clear();
	    driver.findElement(By.id("businessName")).sendKeys(BusinessName);
	    driver.findElement(By.id("yearEstablished")).clear();
	    driver.findElement(By.id("yearEstablished")).sendKeys(YearEstablished);
	    new Select(driver.findElement(By.id("daytime"))).selectByVisibleText(dayTime);
	    driver.findElement(By.id("customerNo")).click();
	    driver.findElement(By.id("checksav")).click();
	    driver.findElement(By.name("additionaldetails")).clear();
	    driver.findElement(By.name("additionaldetails")).sendKeys(additionalDetails);
	    
	}
	
	
	@Then("^user click Send Request Now$")
	public void Click_Send_RequestNow(){
	    driver.findElement(By.cssSelector("input[name=\"Send Request Now\"]")).click();

	} 
	
	@And("^Close the Browser$")
	public void closeBrowser(){
		driver.close();
	}

}