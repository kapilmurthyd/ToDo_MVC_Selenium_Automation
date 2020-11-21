package com.ToDoList.Pages;

import java.lang.reflect.Array;
import java.lang.reflect.Field;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import base.BaseUtil;
import constants.GenericFunctionsLib;
import stepDefinition.Initializer;

public class ToDoList_HomePage extends BaseUtil{

	@FindBy(xpath = ("//h1[text()='todos']"))
	static WebElement Todos_Header_Label;
		
	@FindBy(xpath = ("//input[@placeholder='What needs to be done?']"))
	static WebElement TodoText_Edit;
	
	@FindBy(xpath = ("//a[@href='#/all']"))
	static WebElement All_Link;

	@FindBy(xpath = ("//a[@href='#/active']"))
	static WebElement Active_Link;

	@FindBy(xpath = ("//a[@href='#/completed']"))
	static WebElement Completed_Link;
	
	@FindBy(xpath = ("//button[@class='clear-completed']"))
	static WebElement ClearCompleted_Button;
	
	@FindBy(xpath = ("//span[@class='todo-count']"))
	static WebElement TodoCount_Element;
	
	
	//***************************************************** Assign Variables ****************************************************************
	
	public static int TodoListCount_BeforeDelete;
	
	
	//***************************************************** Initialize the page objects *****************************************************
	
	public ToDoList_HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	//***************************************************** Reset Variables Value ***********************************************************	
	
		public ToDoList_HomePage() {		
	        reset();
	    }
		
		public void reset() {
			try {
				doReset();
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}		

		private void doReset() throws IllegalArgumentException, IllegalAccessException {

			Field[] fields = ToDoList_HomePage.class.getDeclaredFields();

		    for ( int i = 0; i < fields.length; i++ ) {
		        Field field = fields[i];
		        Class<?> type = field.getType();
		        if ( type.isPrimitive() ) {
		            field.set( this, 0 );
		        }
		        else if ( type.isArray() ) {
		            Object temp;
		            temp = Array.newInstance( type.getComponentType(), Array.getLength( field.get( this ) ) );
		            field.set( this, temp );
		        } else
		            field.set( this, null );
		    }
		}
		
		//***************************************************** Function Definitions *****************************************************
		
	
	public static void Launch_Todo_Page() {
		Initializer.logger.info("Launching ToDo MVC Page");
		
		String get_URL = "";
		switch (constants.Config.var_ENV) {
			case "ENV1" : get_URL = constants.Config.URL_ToDo_ENV1;
			break;
		}
		Initializer.logger.info("Current Test Scenario is being executed in "+constants.Config.var_ENV+" environment");
		System.out.println("URL to be launched is -->"+get_URL);
		driver.get(get_URL);
		GenericFunctionsLib.WaitForPageToLoad();
	}
	
	public static void Validate_Page_Exists() {
		Initializer.logger.info("Inside Validate_Page_Exists -->>>");
		
		GenericFunctionsLib.WaitForPageToLoad();
		Assert.assertTrue("Todos Header ad text box is displayed", (Todos_Header_Label.isDisplayed() && TodoText_Edit.isDisplayed() && TodoText_Edit.isEnabled()));
		
		Initializer.logger.info("Todo MVC Page was launched successfully");
	}

	public static void AddItemsToList(String add_item) {
		Initializer.logger.info("Inside AddItemsToList -->>>");
		TodoText_Edit.click();
		TodoText_Edit.clear();
		TodoText_Edit.sendKeys(add_item);
		TodoText_Edit.sendKeys(Keys.ENTER);
		
		Initializer.logger.info("Add Item Action completed -->>>");
	}

	public static void VerifyItemsAddedToList(String item) {
		Initializer.logger.info("Inside VerifyItemsAddedToList -->>>");
		Assert.assertTrue(driver.findElement(By.xpath("//label[text()='"+item+"']")).isDisplayed());
		Assert.assertTrue("All, Active and Completed links are displayed", (All_Link.isDisplayed() && Active_Link.isDisplayed() && Completed_Link.isDisplayed()));
				
		Initializer.logger.info("Item Added to Todo list successfully -->>>");		
	}
	
	public static void Validate_All_Active_And_Completed_Buttons_Are_Displayed() {
		Initializer.logger.info("Inside Validate_All_Active_And_Completed_Buttons_Are_Displayed -->>>");
		Assert.assertTrue("All, Active and Completed buttons are displayed", (All_Link.isDisplayed() && Active_Link.isDisplayed() && Completed_Link.isDisplayed()));
				
		Initializer.logger.info("All, Active and Completed buttons are displayed -->>>");		
	}
	
	public static void DeleteItemsFromList(String del_item) throws InterruptedException {
		Initializer.logger.info("Inside DeleteItemsFromList -->>>");
						
		new Actions(driver).moveToElement(driver.findElement(By.xpath("(//label[text()='"+del_item+"'])[1]"))).build().perform();
		GenericFunctionsLib.Sleep(1000);
		driver.findElement(By.xpath("(//label[text()='"+del_item+"']/parent::div/button[@class='destroy'])[1]")).click();
		
		Initializer.logger.info("Delete Item Action completed -->>>");
	}

	public static void Get_Todo_List_Count_Before_Modify() {
		if (TodoCount_Element.getText().trim() != "" ){		
			String[] arrSplit = TodoCount_Element.getText().trim().split(" ");
			TodoListCount_BeforeDelete = Integer.parseInt(arrSplit[0].trim());
		}		
	}

	public static void VerifyTodoListCountAfterDelete(int no_Of_Items_Deleted) {
		if (TodoListCount_BeforeDelete > 1) {	
			String[] arrSplit = TodoCount_Element.getText().trim().split(" ");
			int TodoListCount_AfterDelete = Integer.parseInt(arrSplit[0].trim());
			Assert.assertTrue(TodoListCount_AfterDelete == TodoListCount_BeforeDelete - no_Of_Items_Deleted);
		}
	}

	public static void ModifyItemsOnList(String Search_Item, String Modify_Item) {
		Initializer.logger.info("Inside ModifyItemsOnList -->>>");
		
		new Actions(driver).moveToElement(driver.findElement(By.xpath("(//label[text()='"+Search_Item+"'])[1]"))).doubleClick().build().perform();
		GenericFunctionsLib.Sleep(1000);
		WebElement ModifyItem_Edit = driver.findElement(By.xpath("//li[@class='todo editing']/input[@type='text']"));
		
		ModifyItem_Edit.click();
		ModifyItem_Edit.clear();
		ModifyItem_Edit.sendKeys(Modify_Item);
		ModifyItem_Edit.sendKeys(Keys.ENTER);
		
		Initializer.logger.info("Modify Item Action completed -->>>");
	}

	public static void MarkItemAsCompletedInList(String Completed_Item) {
		Initializer.logger.info("Inside MarkItemAsCompletedInList -->>>");
		
		new Actions(driver).moveToElement(driver.findElement(By.xpath("(//li[@class='todo']//label[text()='"+Completed_Item+"']/../input[@type='checkbox'])[1]"))).click().build().perform();
		GenericFunctionsLib.Sleep(1000);
		
		Initializer.logger.info("Mark Item as Completed, Action completed -->>>");
	}

	public static void VerifyItemMarkedAsCompletedInList(String Completed_Item) {
		Initializer.logger.info("Inside VerifyItemMarkedAsCompletedInList -->>>");
		
		Assert.assertTrue(driver.findElement(By.xpath("(//li[@class='todo completed']//label[text()='"+Completed_Item+"'])[1]")).isDisplayed());
		Assert.assertTrue("All, Active and Completed links are displayed", (ClearCompleted_Button.isDisplayed()));
		
		Initializer.logger.info("Item is marked as Completed in the ToDo List as expected -->>>");
	}

	public static void ClickOnActiveButton() {
		Initializer.logger.info("Inside ClickOnActiveButton -->>>");
		Active_Link.click();
		
		Initializer.logger.info("Clicked on Active button -->>>");
	}

	public static void Validate_OnlyActiveItemsAreDisplayedInTheList() {
		Initializer.logger.info("Inside Validate_OnlyActiveItemsAreDisplayedInTheList -->>>");

		Assert.assertFalse(GenericFunctionsLib.isElementVisible("(//li[@class='todo completed'])[1]"));
		Initializer.logger.info("Only Active Items are displayed in the list -->>>");

	}
	
	public static void ClickOnCompletedButton() {
		Initializer.logger.info("Inside ClickOnCompletedButton -->>>");
		Completed_Link.click();
		
		Initializer.logger.info("Clicked on Completed button -->>>");
	}

	public static void Validate_OnlyCompletedItemsAreDisplayedInTheList() {
		Initializer.logger.info("Inside Validate_OnlyCompletedItemsAreDisplayedInTheList -->>>");

		Assert.assertFalse(GenericFunctionsLib.isElementVisible("(//li[@class='todo'])[1]"));
		Initializer.logger.info("Only Completed Items are displayed in the list -->>>");
	}
	
	public static void ClickOnClearCompletedButton() {
		// TODO Auto-generated method stub
		Initializer.logger.info("Inside ClickOnClearCompletedButton -->>>");
		ClearCompleted_Button.click();
		
		Initializer.logger.info("Clicked on Clear-Completed button -->>>");
	}
	

	public static void Validate_ClearCompletedButtonIsNotDisplayed() {
		// TODO Auto-generated method stub
		Initializer.logger.info("Inside Validate_ClearCompletedButtonIsNotDisplayed -->>>");
		
		Assert.assertTrue(GenericFunctionsLib.isElementVisible("(//button[@class='clear-completed'][@style='display: none;'])[1]"));
		Initializer.logger.info("Clear-Completed button is Not displayed -->>>");
	}

	public static void Validate_All_Active_And_Completed_Buttons_Are_Not_Displayed() {
		Initializer.logger.info("Inside Validate_All_Active_And_Completed_Buttons_Are_Not_Displayed -->>>");
			
		Assert.assertTrue(GenericFunctionsLib.isElementVisible("(//footer[@style='display: none;']//a[@href='#/all'])[1]"));
		Assert.assertTrue(GenericFunctionsLib.isElementVisible("(//footer[@style='display: none;']//a[@href='#/active'])[1]"));
		Assert.assertTrue(GenericFunctionsLib.isElementVisible("(//footer[@style='display: none;']//a[@href='#/completed'])[1]"));
				
		Initializer.logger.info("All, Active and Completed buttons are Not displayed -->>>");		
	}
		
}
