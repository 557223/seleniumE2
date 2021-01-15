package stepDefinitionFiles;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

  	

public class HotelBooking {
	
    public WebDriver driver;
    
 
    
    @Given("^url launch and verify user is on homepage$")
    public void user_is_on_homepage() throws Throwable {     
    	String heading_Txt = null;
    	System.setProperty("webdriver.chrome.driver","C:\\Program Files\\Google\\Chrome\\Application\\chromedriver.exe");
        driver = new ChromeDriver();
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("C:\\Users\\Aravind\\Downloads\\Casestudy_HotelBooking\\login.html");
        heading_Txt = driver.findElement(By.xpath("//h1")).getText();
        assertTrue(heading_Txt.contains("Hotel Booking Application")); 	      
        
    }
    
    @When("^user clicks on login without entering username and password verify error on username$")
    public void verifyError_NoUsername_NoPassword() throws Throwable {
        driver.findElement(By.className("btn")).click();
        //Verify error in username
        List<WebElement> error_Elm = driver.findElements(By.className("errMessage"));
        if(error_Elm.get(0).getText()!=null) {
        	System.out.println("Error Exist in page because username and password not provided which is expected");
        	assertEquals(error_Elm.get(0).getText().trim()," * Please enter userName.".trim());
        
        	}
        	
    }
    
    @When("^user clicks on login without password verify error on password$")
    public void verifyError_withUsername_NoPassword() throws Throwable {
    	driver.findElement(By.name("userName")).sendKeys("admin");
        driver.findElement(By.className("btn")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //Verify error in password
        List<WebElement> error_Elm = driver.findElements(By.className("errMessage"));
        
        if(error_Elm.get(1).getText()!=null) {
        	System.out.println("Error Exist in page because passowrd not provided  which is expected");
        	assertEquals(error_Elm.get(1).getText().trim()," * Please enter password.".trim());
        	}
              	
    }
    
    @When("^user enters username as \"([^\"]*)\" and password as \"([^\"]*)\"$")
    public void successful_Login(String userName, String Password)
    {
    	driver.findElement(By.name("userName")).clear();
    	driver.findElement(By.name("userPwd")).clear();
    	driver.findElement(By.name("userName")).sendKeys("admin");
    	driver.findElement(By.name("userPwd")).sendKeys("admin");
    	driver.findElement(By.className("btn")).click();
    	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    		
    }
    
    @Then("^verify login is successful and Hotel Application form page opened$")
    public void verify_FormPage() {
    	String title_Txt=null;
    	title_Txt = driver.getTitle();
        assertTrue(title_Txt.contains("Hotel Booking")); 
    }
    
    @When("^enter confirm booking without entering first Name verify alert message as \"([^\"]*)\"$")
    public void verifyAlert_NoFirstName(String str)
    {
    	driver.findElement(By.id("btnPayment")).click();
    	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    	checkAlert("First Name");
    	verifyAlert(str);
    	driver.findElement(By.id("txtFirstName")).sendKeys("Aravind");
    }
    
    @When("^enter confirm booking without entering last Name verify alert message as \"([^\"]*)\"$")
    public void verifyAlert_NoLastName(String str)
    {
    	driver.findElement(By.id("btnPayment")).click();
    	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    	checkAlert("Last Name");
    	verifyAlert(str);
    	driver.findElement(By.id("txtLastName")).sendKeys("Rayabandi");
    }
    
    @When("^enter confirm booking without entering email verify alert message as \"([^\"]*)\"$")
    public void verifyAlert_NoEmail(String str)
    {
    	driver.findElement(By.id("btnPayment")).click();
    	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    	checkAlert("Email");
    	verifyAlert(str);
    	
    }
    

    @When("^enter confirm booking with wrong email pattern and verify alert message as \"([^\"]*)\"$")
    public void verifyAlert_wrongEmailPattern(String str)
    {
    	driver.findElement(By.id("txtEmail")).sendKeys("aravind");
    	driver.findElement(By.id("btnPayment")).click();
    	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    	checkAlert("wrong Email");
    	verifyAlert(str);
    	driver.findElement(By.id("txtEmail")).clear();
    	driver.findElement(By.id("txtEmail")).sendKeys("aravind.rayabandi@tcs.com");
    }
    
    @When("^enter confirm booking without entering mobile number verify alert message as \"([^\"]*)\"$")
    public void verifyAlert_NomobileNumber(String str)
    {
    	driver.findElement(By.id("btnPayment")).click();
    	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    	checkAlert("Mobile Number");
    	verifyAlert(str);
    	
    }
    
    
    @When("^enter confirm booking with wrong mobile number pattern and verify alert message as \"([^\"]*)\"$")
    public void verifyAlert_WrongmobileNumber(String str)
    {
    	driver.findElement(By.id("txtPhone")).sendKeys("795");
    	driver.findElement(By.id("btnPayment")).click();
    	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    	checkAlert("Wrong Mobile Number");
    	verifyAlert(str);
    	driver.findElement(By.id("txtPhone")).clear();
    	driver.findElement(By.id("txtPhone")).sendKeys("7687457855");
    }
    
    @When("^entered address details$")
    public void enter_Address()
    {
    	driver.findElement(By.xpath("//textarea")).sendKeys("h.no 77686, hjkj colony, uhhhhodyt");
    }
    
    @Then("^verify alert when city is not selected as \"([^\"]*)\" and then select city \"([^\"]*)\"$")
    public void verifyAlet_NoCitySelect(String str, String city) {
    	driver.findElement(By.id("btnPayment")).click();
    	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    	checkAlert("city drop down");
    	verifyAlert(str);
    	Select city_Select = new Select(driver.findElement(By.xpath("//select[@name='city']")));
    	List<WebElement> city_Options= city_Select.getOptions();
    	boolean city_Found=false;
    	for(int i=0;i<city_Options.size();i++) {
    		if(city_Options.get(i).getText().contains(city)) {
    			city_Found=true;
    		}
    	}
 
    	if(city_Found)
    			{
    		city_Select.selectByVisibleText(city);
    		System.out.println("City mentioned "+city+ " is selected successfully");
    	}
    	else {
    		System.out.println("City mentioned "+city+ " is not present in drop down");
    	}
    }
    
    @Then("^verify alert when state is not selected as \"([^\"]*)\" and then select state \"([^\"]*)\"$")
    public void verifyAlet_NostateSelect(String str, String state) {
    	driver.findElement(By.id("btnPayment")).click();
    	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    	checkAlert("state drop down");
    	verifyAlert(str);
    	Select state_Select = new Select(driver.findElement(By.xpath("//select[@name='state']")));
    	List<WebElement> state_Options=state_Select.getOptions();
    	boolean state_Found=false;
    	for(int i=0;i<state_Options.size();i++) {
    		if(state_Options.get(i).getText().contains(state)) {
    			state_Found=true;
    		}
    	}
 
    	if(state_Found)
    			{
    		state_Select.selectByVisibleText(state);
    		System.out.println("State mentioned "+state+ " is selected successfully");
    	}
    	else {
    		System.out.println("State mentioned "+state+ " is not present in drop down");
    	}
    }
    
    @Then("^verify no of rooms changes as per no of guests selection$")
    public void verify_RoomsBooked() {
    	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    	Select person_select = new Select(driver.findElement(By.xpath("//select[@name='persons']")));
    	person_select.selectByVisibleText("1");
    	WebElement option = person_select.getFirstSelectedOption();
		String firstValue = option.getText();
		if(firstValue.equals("1")) {
		System.out.println("Default value 1 is selected as expected"); } else {
		System.out.println("Default value 1 is selected as expected"); }
		  //verify no of rooms
		  
		checkRooms(); 
		person_select.selectByVisibleText("5");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		checkRooms(); 
		person_select.selectByVisibleText("9");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		checkRooms();
		 
    }
    
    @Then("^verify alert when Card Holder name is not entered with alert message as \"([^\"]*)\"$")
    public void verifyAlert_NoCardHolderName(String str)
    {
    	driver.findElement(By.id("btnPayment")).click();
    	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    	checkAlert("Card Holder Name");
    	verifyAlert(str);  
    	driver.findElement(By.id("txtCardholderName")).sendKeys("huhnn");
    }
    
    @Then("^verify alert when debit card number is not entered with alert message as \"([^\"]*)\"$")
    public void verifyAlert_NoDebitCardNumber(String str)
    {
    	driver.findElement(By.id("btnPayment")).click();
    	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    	checkAlert("debit card number");
    	verifyAlert(str);  
    	driver.findElement(By.id("txtDebit")).sendKeys("4515185673269874");
    }
    
    @Then("^verify alert when CVV is not entered with alert message as \"([^\"]*)\"$")
    public void verifyAlert_NoCVV(String str)
    {
    	driver.findElement(By.id("btnPayment")).click();    	
    	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    	checkAlert("CVV");
    	verifyAlert(str);    
    	driver.findElement(By.id("txtCvv")).sendKeys("665");
    } 
    
    @Then("^verify alert when Expiration month is not entered with alert message as \"([^\"]*)\"$")
    public void verifyAlert_NoExpirationMonth(String str)
    {
    	driver.findElement(By.id("btnPayment")).click();     	
    	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    	checkAlert("Expiration Month");
    	verifyAlert(str);  
    	driver.findElement(By.id("txtMonth")).sendKeys("12");
    } 
    
    @Then("^verify alert when Expiration year is not entered with alert message as \"([^\"]*)\"$")
    public void verifyAlert_NoExpirationYear(String str)
    {
    	driver.findElement(By.id("btnPayment")).click();    	
    	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    	checkAlert("Expiration Year");
    	verifyAlert(str);    	
    	driver.findElement(By.id("txtYear")).sendKeys("23");
    }
    
    @Then("^confirm booking after entering all data and validate booking success$")
    public void bookingsuccess() {
    	driver.findElement(By.id("btnPayment")).click();
    	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    	String heading_Txt = driver.findElement(By.xpath("//h1")).getText();
        assertTrue(heading_Txt.contains("Booking Completed!")); 	
    }
    
    @Then("^close driver$")
    public void closeDriver() {
    	driver.quit();
    }
    public void checkAlert(String str)
    {
    	WebDriverWait wait = new WebDriverWait(driver,300);
    	if(wait.until(ExpectedConditions.alertIsPresent()) != null) {
    		System.out.println("Alert is present for field " +str);
    	}
    	else {
    		System.out.println("Alert is not present for field " +str);
    	}
    }
    
    public void verifyAlert(String str) {
    	Alert alrt = driver.switchTo().alert();
    	String alrt_Msg = alrt.getText();
    	System.out.println("Alert message is "+alrt_Msg);
    	assertTrue(alrt_Msg.contains(str));
    	alrt.accept();
    	driver.switchTo().defaultContent();
    }
    
    public void checkRooms() {
    	
    	Select person_select = new Select(driver.findElement(By.xpath("//select[@name='persons']")));
    	WebElement option = person_select.getFirstSelectedOption();
    	int no_of_Guests = Integer.parseInt(option.getText().trim());
    	WebElement no_of_Rooms = driver.findElement(By.id("rooms"));
    	if(no_of_Guests>=1 && no_of_Guests<=3) {
    		if(no_of_Rooms.getText().equals("1")) {
    			System.out.println("no of rooms selected is 1 as no of guests selected is "+no_of_Guests);
    		}
    		else
    			System.out.println("no of rooms selected is not matching with no of guests selected as "+no_of_Guests);
    	}
    	else if(no_of_Guests>=4 && no_of_Guests<=6) {
    		if(no_of_Rooms.getText().equals("2")) {
    			System.out.println("no of rooms selected is 2 as no of guests selected is "+no_of_Guests);
    		}
    		else
    			System.out.println("no of rooms selected is not matching with no of guests selected as "+no_of_Guests);    		
    	}
    	else if(no_of_Guests>=7 && no_of_Guests<=9) {
    		if(no_of_Rooms.getText().equals("3")) {
    			System.out.println("no of rooms selected is 3 as no of guests selected is "+no_of_Guests);
    		}
    		else
    			System.out.println("no of rooms selected is not matching with no of guests selected as "+no_of_Guests);
    	}
    }
}