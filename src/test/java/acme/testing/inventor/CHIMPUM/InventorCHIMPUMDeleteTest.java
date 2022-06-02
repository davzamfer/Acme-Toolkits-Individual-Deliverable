package acme.testing.inventor.CHIMPUM;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorCHIMPUMDeleteTest extends TestHarness {
	
	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/chimpum/list-chimpum.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positive() {
		
		super.signIn("inventor1", "inventor1");
		
		super.clickOnMenu("Inventor", "List of CHIMPUMS");
		super.checkListingExists();

		super.clickOnListingRecord(0);	

		super.clickOnSubmit("Delete");

		super.checkNotErrorsExist();
		
		super.signOut();
	}
	

	
	// Ancillary methods ------------------------------------------------------
}
