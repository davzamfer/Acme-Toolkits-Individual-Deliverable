package acme.features.inventor.troqua;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.artifacts.Artifact;
import acme.entities.troqua.Troqua;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractDeleteService;
import acme.roles.Inventor;

@Service
public class InventorTroquaDeleteService implements AbstractDeleteService<Inventor, Troqua> {

	
	
	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorTroquaRepository repository;

	// AbstractDeleteService<Inventor, Artifact> interface ---------------------------
	
	@Override
	public boolean authorise(final Request<Troqua> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Troqua> request, final Troqua entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "code", "theme", "statement", "startDate", "finishDate","quota", "additionalInfo");
	}

	@Override
	public void unbind(final Request<Troqua> request, final Troqua entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "creationMoment", "code", "theme", "statement", "startDate", "finishDate","quota", "additionalInfo");
	}

	@Override
	public Troqua findOne(final Request<Troqua> request) {
		assert request != null;
		
		Integer id;
		Troqua troqua;
		id = request.getModel().getInteger("id");
		troqua = this.repository.findTroquaById(id);
		
		return troqua;
	}

	@Override
	public void validate(final Request<Troqua> request, final Troqua entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
	}

	@Override
	public void delete(final Request<Troqua> request, final Troqua entity) {
		assert request != null;
		assert entity != null;
		
		final List<Artifact> artifacts = this.repository.findAllArtifactsByTroquaId(entity.getId());
		if(!artifacts.isEmpty()) {
			for(final Artifact a:artifacts) {
				a.setTroqua(null);
				this.repository.save(a);
			}
		}

		this.repository.delete(entity);
		
	}

}
