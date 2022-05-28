package acme.features.inventor.CHIMPUM;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.CHIMPUM.CHIMPUM;
import acme.entities.artifacts.Artifact;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorCHIMPUMRepository extends AbstractRepository{


	@Query("SELECT c FROM CHIMPUM c WHERE c.id = :id")
	CHIMPUM findCHIMPUMById(int id);

	@Query("SELECT c FROM CHIMPUM c")
	Collection<CHIMPUM> findAllCHIMPUMS();

	@Query("SELECT a FROM Artifact a WHERE a.chimpum.id = :id")
	List<Artifact> findAllArtifactsByCHIMPUMId(int id);

}
