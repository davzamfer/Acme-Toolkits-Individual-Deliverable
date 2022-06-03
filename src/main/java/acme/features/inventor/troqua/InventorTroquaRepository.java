package acme.features.inventor.troqua;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.artifacts.Artifact;
import acme.entities.troqua.Troqua;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorTroquaRepository extends AbstractRepository{


	@Query("SELECT c FROM Troqua c WHERE c.id = :id")
	Troqua findTroquaById(int id);

	@Query("SELECT c FROM Troqua c")
	Collection<Troqua> findAllTroquas();

	@Query("SELECT a FROM Artifact a WHERE a.troqua.id = :id")
	List<Artifact> findAllArtifactsByTroquaId(int id);

	@Query("select config.strongSpamTerms from ConfigData config")
	String findStrongSpamTerms();
	
	@Query("select config.weakSpamTerms from ConfigData config")
	String findWeakSpamTerms();
	
	@Query("select config.strongSpamTreshold from ConfigData config")
	int findStrongSpamTreshold();
	
	@Query("select config.weakSpamTreshold from ConfigData config")
	int findWeakSpamTreshold();

	@Query("select cd.acceptedCurrencies from ConfigData cd")
	String acceptedCurrencies();
}
