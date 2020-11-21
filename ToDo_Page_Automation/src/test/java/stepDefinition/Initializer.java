package stepDefinition;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Reporter;

import com.ToDoList.Pages.ToDoList_HomePage;

import base.BaseUtil;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;


public class Initializer extends BaseUtil{

	//***************************************************** Test Level Initializations *****************************************************
	public static Logger logger=Logger.getLogger(Logger.class.getName());
	public static String current_ScenarioName;
	public static String current_FeatureName;
	public static final String Default_Download_Path = System.getProperty("user.home")+"\\Downloads";
	
	
	@SuppressWarnings("unused")
	@Before
	public void Initialize(Scenario scenario) throws Throwable {
		//Associate current Feature name and Scenario name currently being taken for execution
		//current_FeatureName = feature.getName();
		current_ScenarioName = scenario.getName();
		
		// configure log4j properties file
	    PropertyConfigurator.configure("Log4j.properties");
	    
	    //Log in the current Feature name and Scenario names currently being executed
	    //Initializer.logger.info("*********************Feature Name: "+current_FeatureName+"*********************");
	    Initializer.logger.info("\n\n");
	    Initializer.logger.info("*********************Scenario Name: "+current_ScenarioName+"*********************");
		//Reporter.log("*********************Feature Name: "+current_FeatureName+"*********************");
	    Reporter.log("                                                                                              ");
		Reporter.log("*********************Scenario Name: "+current_ScenarioName+"*********************");
	    
		//Setup Driver
		System.setProperty("webdriver.chrome.driver", ".\\src\\test\\resources\\chromedriver.exe");
		//driver.manage().window().maximize();
		
		//Set Browser Capabilities
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("download.default_directory", Default_Download_Path);
		ChromeOptions options = new ChromeOptions();
		options.addArguments("disable-infobars");
	    options.addArguments("--start-maximized");
		options.setExperimentalOption("prefs", chromePrefs);
		/*DesiredCapabilities cap = DesiredCapabilities.chrome();
		cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		cap.setCapability(ChromeOptions.CAPABILITY, options);*/
		driver = new ChromeDriver(options);
		
		//ChromeOptions options = new ChromeOptions();
		
        //driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
        
        //Setup Event Driver
        EventFiringWebDriver driver = new EventFiringWebDriver(BaseUtil.driver);        
        EventHandler handler = new EventHandler();
		driver.register(handler);
		BaseUtil.driver = driver;
		
        //SanityTest SanityTest = new SanityTest(base.driver);
        //LoginPage LoginPage = new LoginPage(driver);

        ToDoList_HomePage ToDoList_HomePage = new ToDoList_HomePage(driver);
	}
	
	@SuppressWarnings("unused")
	@After
	public void Cleanup() {		
		ToDoList_HomePage ToDoList_HomePage = new ToDoList_HomePage();
		
		driver.close();
		driver.quit();
	}
	
}

