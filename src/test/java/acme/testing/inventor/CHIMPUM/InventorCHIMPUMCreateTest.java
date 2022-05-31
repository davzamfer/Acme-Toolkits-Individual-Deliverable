package acme.testing.inventor.CHIMPUM;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorCHIMPUMCreateTest extends TestHarness {
	
	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/chimpum/create-positive-chimpum.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positive(final int recordIndex, final String title, final String description, final String pattern, 
			final String startDate, final String finishDate, final String budget, final String link) {
		
		super.signIn("inventor1", "inventor1");
		
		super.clickOnMenu("Inventor", "List of CHIMPUMS");
		super.checkListingExists();

		super.clickOnButton("Create");
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("startDate", startDate);
		super.fillInputBoxIn("finishDate", finishDate);
		super.fillInputBoxIn("budget", budget);
		super.fillInputBoxIn("link", link);
		super.fillInputBoxIn("pattern", pattern);
		super.clickOnSubmit("Create");
		
		super.clickOnMenu("Inventor", "List of CHIMPUMS");
		super.checkListingExists();
		super.sortListing(0, "desc");
		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, description);	
		
		super.clickOnListingRecord(0);
		super.checkFormExists();
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("startDate", startDate);
		super.checkInputBoxHasValue("finishDate", finishDate);
		super.checkInputBoxHasValue("budget", budget);
		super.checkInputBoxHasValue("link", link);

		super.clickOnButton("See Artifacts");

		super.checkListingExists();
		super.checkListingEmpty();

		super.signOut();
		
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/chimpum/create-negative-chimpum.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void negative(final int recordIndex, final String title, final String description, final String pattern, 
		final String startDate, final String finishDate, final String budget, final String link) {
		
		super.signIn("inventor1", "inventor1");
		
		super.clickOnMenu("Inventor", "List of CHIMPUMS");
		super.checkListingExists();
		
		super.clickOnButton("Create");
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("startDate", startDate);
		super.fillInputBoxIn("finishDate", finishDate);
		super.fillInputBoxIn("budget", budget);
		super.fillInputBoxIn("link", link);
		super.fillInputBoxIn("pattern", pattern);
		super.clickOnSubmit("Create");
		
		super.checkErrorsExist();
	
		super.signOut();
	}
	
	// Ancillary methods ------------------------------------------------------
}
