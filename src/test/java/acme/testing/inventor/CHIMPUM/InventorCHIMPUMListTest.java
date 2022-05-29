package acme.testing.inventor.CHIMPUM;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorCHIMPUMListTest extends TestHarness {
	
	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/chimpum/list-chimpum.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positive(final int recordIndex, final String title, final String description) {
		super.signIn("inventor1", "inventor1");
		
		super.clickOnMenu("Inventor", "List of CHIMPUMS");
		super.checkListingExists();

		super.sortListing(0, "asc");
		
		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, description);	

		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/toolkit/list-chimpum.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void negative(final int recordIndex, final String title, final String description) {
	super.signIn("patron2", "patron2");
		
		super.navigate("/inventor/chimpum/list");
		super.checkErrorsExist();
		
		super.signOut();
	}
	
	// Ancillary methods ------------------------------------------------------
}
