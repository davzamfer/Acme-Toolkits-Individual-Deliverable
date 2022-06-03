package acme.testing.inventor.troqua;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorTroquaDeleteTest extends TestHarness {
	
	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/troqua/list-troqua.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positive() {
		
		super.signIn("inventor1", "inventor1");
		
		super.clickOnMenu("Inventor", "List of Troquas");
		super.checkListingExists();

		super.clickOnListingRecord(0);	

		super.clickOnSubmit("Delete");

		super.checkNotErrorsExist();
		
		super.signOut();
	}
	

	
	// Ancillary methods ------------------------------------------------------
}
