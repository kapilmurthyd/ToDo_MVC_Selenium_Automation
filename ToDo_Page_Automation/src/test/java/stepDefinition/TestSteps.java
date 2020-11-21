package stepDefinition;

import com.ToDoList.Pages.ToDoList_HomePage;

import base.BaseUtil;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class TestSteps extends BaseUtil{
	
	//***************************************************** Step Definitions *****************************************************
	
	@Given("^The ToDo MVC List Page is launched$")
	public void the_ToDo_MVC_List_Page_is_launched() throws Throwable {
		ToDoList_HomePage.Launch_Todo_Page();
	}
	
	@Given("^The ToDo List Page is open$")
	public void the_ToDo_List_Page_is_open() throws Throwable {
		ToDoList_HomePage.Validate_Page_Exists();
	}
	
	@When("^I add the \"(.*)\" to the list$")
	public void I_add_the_items_to_the_list(String items) throws Throwable {
		String[] arrSplit = items.split(",");
		for(String add_item : arrSplit) {
			ToDoList_HomePage.AddItemsToList(add_item.trim());
		}
	    
	}
	
	@Then("^I verify the \"(.*)\" has been added in the list$")
	public void I_verify_the_items_has_been_added_in_the_list(String items) throws Throwable {
		ToDoList_HomePage.VerifyItemsAddedToList(items);
		    
	}
		
	@When("^I remove the \"([^\"]*)\" from the list$")
	public void I_remove_the_item_from_the_list(String items) throws Throwable {
		ToDoList_HomePage.Get_Todo_List_Count_Before_Modify();
		String[] arrSplit = items.split(",");
		for(String del_item : arrSplit) {
			ToDoList_HomePage.DeleteItemsFromList(del_item.trim());
		}
	}
	
	@Then("^I verify the \"([^\"]*)\" has been removed from the list$")
	public void I_verify_the_item_has_been_removed_from_the_list(String items) throws Throwable {
		String[] arrSplit = items.split(",");
		int No_Of_Items_Deleted = arrSplit.length;
		ToDoList_HomePage.VerifyTodoListCountAfterDelete(No_Of_Items_Deleted);
	}
	
	
	@When("^I modify the \"([^\"]*)\" on the list$")
	public void I_modify_the_item_on_the_list(String items) throws Throwable {
		String[] arr1Split = items.split(",");
		for(String mod_item : arr1Split) {
			String[] arr2Split = mod_item.split("as");
			String SearchString = arr2Split[0];
			String Modify_String = arr2Split[1];
				ToDoList_HomePage.ModifyItemsOnList(SearchString.trim(), Modify_String.trim());	
		}
	}
	
	@Then("^I verify the \"([^\"]*)\" has been modified on the list$")
	public void I_verify_the_item_has_been_modified_on_the_list(String items) throws Throwable {
		String[] arr1Split = items.split(",");
		for(String mod_item : arr1Split) {
			String[] arr2Split = mod_item.split("as");
			String ModifiedAs_String = arr2Split[1];
			ToDoList_HomePage.VerifyItemsAddedToList(ModifiedAs_String.trim());	
		}		
	}
		
	
	@When("^I Mark the \"([^\"]*)\" on the list$")
	public void I_Mark_the_item_on_the_list(String items) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		ToDoList_HomePage.Get_Todo_List_Count_Before_Modify();
		String[] arrSplit = items.split(",");
		for(String completed_item : arrSplit) {
			ToDoList_HomePage.MarkItemAsCompletedInList(completed_item.trim());	
		}		
	}
	
	@Then("^I verify the \"([^\"]*)\" has been crossed on the list$")
	public void I_verify_the_item_has_been_crossed_on_the_list(String items) throws Throwable {
		String[] arrSplit = items.split(",");
		int No_Of_Items_Completed = arrSplit.length;
		ToDoList_HomePage.VerifyTodoListCountAfterDelete(No_Of_Items_Completed);
	
		for(String completed_item : arrSplit) {
			ToDoList_HomePage.VerifyItemMarkedAsCompletedInList(completed_item.trim());	
		}		
	}

	@When("^I click on the Active button in the ToDo list$")
	public void I_click_on_the_Active_button_in_the_ToDo_list() throws Throwable {
		ToDoList_HomePage.ClickOnActiveButton();
	}
	
	@Then("^Only the Active items in the list must be displayed$")
	public void Only_the_Active_items_in_the_list_must_be_displayed() throws Throwable {
		ToDoList_HomePage.Validate_OnlyActiveItemsAreDisplayedInTheList();
	}

	@When("^I click on the Completed button in the ToDo list$")
	public void I_click_on_the_Completed_button_in_the_ToDo_list() throws Throwable {
		ToDoList_HomePage.ClickOnCompletedButton();
	}
	
	@Then("^Only the Completed items in the list must be displayed$")
	public void Only_the_Completed_items_in_the_list_must_be_displayed() throws Throwable {
		ToDoList_HomePage.Validate_OnlyCompletedItemsAreDisplayedInTheList();
	}
	
	@When("^I click on the Clear-Completed button in the ToDo list$")
	public void I_click_on_the_ClearCompleted_button_in_the_ToDo_list() throws Throwable {
		ToDoList_HomePage.ClickOnClearCompletedButton();
	}
	
	@Then("^The Clear-Completed button is not displayed$")
	public void the_Clear_Completed_button_is_not_displayed() throws Throwable {
		ToDoList_HomePage.Validate_ClearCompletedButtonIsNotDisplayed();
	}

	@Then("^Verify All, Active and Completed buttons are not displayed$")
	public void verify_All_Active_and_Completed_buttons_are_not_displayed() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		ToDoList_HomePage.Validate_All_Active_And_Completed_Buttons_Are_Not_Displayed();
	}
	
}
