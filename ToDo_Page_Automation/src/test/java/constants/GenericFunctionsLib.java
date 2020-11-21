package constants;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import base.BaseUtil;
import stepDefinition.Initializer;

public class GenericFunctionsLib extends BaseUtil{

	//***************************************************** Assign Variables ****************************************************************
	
	
	
	//***************************************************** Initialize the page objects *****************************************************
	
	public GenericFunctionsLib(WebDriver driver) {
		PageFactory.initElements(driver, this);		
	}
	
	//***************************************************** Function Definitions ************************************************************

	//Function to wait for the page to load	
	public static void WaitForPageToLoad() {
		Initializer.logger.info("...........Waiting For Page to load..........");
		{
            boolean pageComplete = false;
            try
            {
                do
                {
                    pageComplete = ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("loaded") ||
                    		((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
                } while (!pageComplete);
            }
            catch (Exception e)
            {
            	Initializer.logger.error("Page never loaded");
            	Initializer.logger.fatal("Exception occured while page loading: " + e);
            }
        }
    }
	
	public static void Sleep(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}	
	

	public static boolean isElementVisible(String xpath_locator){
	    try {
	    	driver.findElement(By.xpath(xpath_locator));
	        return true;
	    } catch (NoSuchElementException e) {
	        return false;
	    }
	}
	
}
