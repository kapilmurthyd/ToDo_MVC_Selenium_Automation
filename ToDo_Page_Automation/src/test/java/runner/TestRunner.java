package runner;

import java.io.File;
import java.io.IOException;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import com.google.common.io.PatternFilenameFilter;

import base.BaseUtil;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;


//@RunWith(Cucumber.class)
@CucumberOptions(
		features="features",
		glue=("stepDefinition"),
		plugin = { "pretty", "html:target/cucumber-reports" },
		monochrome = true,
		tags= {"@"+constants.Config.var_ENV, "~@Ignore"})

public class TestRunner extends AbstractTestNGCucumberTests {
	
	@BeforeClass
	private void ClearOldLogs() throws IOException {
		final File folder = new File(System.getProperty("user.dir")+"\\log\\"); 
		final File[] files = folder.listFiles(new PatternFilenameFilter("testlog.*\\.*"));
		
		// loop through the files
		for ( final File file : files ) {
			String currentFileName = file.getName();
			//System.out.println(currentFileName);
		    if ( !file.delete() ) {
		        System.err.println("Cannot delete the file -->" + file.getAbsolutePath());
		    }	
		    else {
		    	File logFile = new File(folder.getPath()+"//"+currentFileName);
				logFile.createNewFile();
		    }
		    
		}
		
		System.out.println("Old Log files cleared successfully");
		System.out.println("----------------------------------");
		
		
	}
	
	@AfterClass
    public void tearDown(){

    closeBrowser();

}

	private void closeBrowser() {
		BaseUtil.driver.close();
		BaseUtil.driver.quit();
	}
}