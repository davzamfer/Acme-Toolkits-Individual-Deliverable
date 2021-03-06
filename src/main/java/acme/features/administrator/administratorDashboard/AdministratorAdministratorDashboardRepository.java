package acme.features.administrator.administratorDashboard;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorAdministratorDashboardRepository extends AbstractRepository {
	
	//Methods for components --------------------------------------------
	
	@Query("select count(a) from Artifact a where a.artifactType = 1")
	int totalNumberOfComponents();
	
	@Query("select a.technology, a.retailPrice.currency, avg(a.retailPrice.amount) from Artifact a where a.artifactType = 1 group by a.technology, a.retailPrice.currency")
	List<String> averageRetailPriceOfComponentsGroupedByTechnologyAndCurrency();
	
	@Query("select a.technology, a.retailPrice.currency, stddev(a.retailPrice.amount) from Artifact a where a.artifactType = 1 group by a.technology, a.retailPrice.currency")
	List<String> deviationRetailPriceOfComponentsGroupedByTechnologyAndCurrency();
	
	@Query("select a.technology, a.retailPrice.currency, min(a.retailPrice.amount) from Artifact a where a.artifactType = 1 group by a.technology, a.retailPrice.currency")
	List<String> minimumRetailPriceOfComponentsGroupedByTechnologyAndCurrency();
	
	@Query("select a.technology, a.retailPrice.currency, max(a.retailPrice.amount) from Artifact a where a.artifactType = 1 group by a.technology, a.retailPrice.currency")
	List<String> maximumRetailPriceOfComponentsGroupedByTechnologyAndCurrency();
	
	//Methods for tools --------------------------------------------
	
	@Query("select count(a) from Artifact a where a.artifactType = 0")
	int totalNumberOfTools();
	
	@Query("select a.retailPrice.currency, avg(a.retailPrice.amount) from Artifact a where a.artifactType = 0 group by a.retailPrice.currency")
	List<String> averageRetailPriceOfToolsGroupedByCurrency();
	
	@Query("select a.retailPrice.currency, stddev(a.retailPrice.amount) from Artifact a where a.artifactType = 0 group by a.retailPrice.currency")
	List<String> deviationRetailPriceOfToolsGroupedByCurrency();
	
	@Query("select a.retailPrice.currency, min(a.retailPrice.amount) from Artifact a where a.artifactType = 0 group by a.retailPrice.currency")
	List<String> minimumRetailPriceOfToolsGroupedByCurrency();
	
	@Query("select a.retailPrice.currency, max(a.retailPrice.amount) from Artifact a where a.artifactType = 0 group by a.retailPrice.currency")
	List<String> maximumRetailPriceOfToolsGroupedByCurrency();
	
	//Methods for patronages --------------------------------------------
	
	@Query("select p.status, count(p) from Patronage p group by p.status")
	List<String> totalNumberOfPatronagesGroupedByStatus();
	
	@Query("select p.status, avg(p.budget.amount) from Patronage p group by p.status")
	List<String> averageBudgetOfPatronagesGroupedByStatus();
	
	@Query("select p.status, stddev(p.budget.amount) from Patronage p group by p.status")
	List<String> deviationBudgetOfPatronagesGroupedByStatus();
	
	@Query("select p.status, min(p.budget.amount) from Patronage p group by p.status")
	List<String> minimumBudgetOfPatronagesGroupedByStatus();
	
	@Query("select p.status, max(p.budget.amount) from Patronage p group by p.status")
	List<String> maximumBudgetOfPatronagesGroupedByStatus();
	
	// Methods for CHIMPUM ---------------------------------------------

	@Query("select count(a) from Artifact a")
	int numberOfArtefacts();
	
	@Query("select count(a) from Artifact a where a.chimpum != null")
	int numberOfArtefactWithCHIMPUM();

	@Query("select c.budget.currency, avg(c.budget.amount) from CHIMPUM c group by c.budget.currency")
	List<String> averageBudgetCHIMPUMGroupedByCurrency();

	@Query("select c.budget.currency, stddev(c.budget.amount) from CHIMPUM c group by c.budget.currency")
	List<String> deviationBudgetCHIMPUMGroupedByCurrency();

	@Query("select c.budget.currency, min(c.budget.amount) from CHIMPUM c group by c.budget.currency")
	List<String> minimumBudgetCHIMPUMGroupedByCurrency();

	@Query("select c.budget.currency, max(c.budget.amount) from CHIMPUM c group by c.budget.currency")
	List<String> maximumBudgetCHIMPUMGroupedByCurrency();
	
	
	// Others -------------------------------
	
	@Query("select cd.acceptedCurrencies from ConfigData cd")
	String acceptedCurrencies();
	
	@Query("select distinct(a.technology) from Artifact a")
	List<String> allTechnologies();

}
