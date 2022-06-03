package acme.testing.inventor.troqua;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorTroquaCreateTest extends TestHarness {
	
	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/troqua/create-positive-troqua.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positive(final int recordIndex, final String theme, final String statement, final String code, 
			final String startDate, final String finishDate, final String quota, final String additionalInfo) {
		
		super.signIn("inventor1", "inventor1");
		
		super.clickOnMenu("Inventor", "List of Troquas");
		super.checkListingExists();

		super.clickOnButton("Create");
		super.fillInputBoxIn("theme", theme);
		super.fillInputBoxIn("statement", statement);
		super.fillInputBoxIn("startDate", startDate);
		super.fillInputBoxIn("finishDate", finishDate);
		super.fillInputBoxIn("quota", quota);
		super.fillInputBoxIn("additionalInfo", additionalInfo);
		super.fillInputBoxIn("code", code);
		super.clickOnSubmit("Create");
		
		super.clickOnMenu("Inventor", "List of CHIMPUMS");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.checkColumnHasValue(recordIndex, 0, theme);
		super.checkColumnHasValue(recordIndex, 1, statement);	
		
		super.clickOnListingRecord(0);
		super.checkFormExists();
		super.checkInputBoxHasValue("theme", theme);
		super.checkInputBoxHasValue("statement", statement);
		super.checkInputBoxHasValue("startDate", startDate);
		super.checkInputBoxHasValue("finishDate", finishDate);
		super.checkInputBoxHasValue("quota", quota);
		super.checkInputBoxHasValue("additionalInfo", additionalInfo);

		super.clickOnButton("See Artifacts");

		super.checkListingExists();
		super.checkListingEmpty();

		super.signOut();
		
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/troqua/create-negative-troqua.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void negative(final int recordIndex, final String theme, final String statement, final String code, 
		final String startDate, final String finishDate, final String quota, final String additionalInfo) {
		
		super.signIn("inventor1", "inventor1");
		
		super.clickOnMenu("Inventor", "List of Troquas");
		super.checkListingExists();
		
		super.clickOnButton("Create");
		super.fillInputBoxIn("theme", theme);
		super.fillInputBoxIn("statement", statement);
		super.fillInputBoxIn("startDate", startDate);
		super.fillInputBoxIn("finishDate", finishDate);
		super.fillInputBoxIn("quota", quota);
		super.fillInputBoxIn("additionalInfo", additionalInfo);
		super.fillInputBoxIn("code", code);
		super.clickOnSubmit("Create");
		
		super.checkErrorsExist();
	
		super.signOut();
	}
	
	// Ancillary methods ------------------------------------------------------
}
