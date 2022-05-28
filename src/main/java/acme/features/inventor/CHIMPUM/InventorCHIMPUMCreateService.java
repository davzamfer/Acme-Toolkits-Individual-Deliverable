package acme.features.inventor.CHIMPUM;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.CHIMPUM.CHIMPUM;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;

@Service
public class InventorCHIMPUMCreateService implements AbstractCreateService<Inventor, CHIMPUM>{

	
	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorCHIMPUMRepository repository;

	// AbstractCreateService<Inventor, Artifact> interface ---------------------------
	
	
	@Override
	public boolean authorise(final Request<CHIMPUM> request) {
		assert request != null;
		
		return true;
	}

	@Override
	public void bind(final Request<CHIMPUM> request, final CHIMPUM entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "code", "title", "description", "startDate", "finishDate","budget", "link");
	}

	@Override
	public void unbind(final Request<CHIMPUM> request, final CHIMPUM entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "code", "title", "description", "startDate", "finishDate","budget", "link");
	}

	@Override
	public CHIMPUM instantiate(final Request<CHIMPUM> request) {
		assert request != null;
		
		CHIMPUM result;
		
		result = new CHIMPUM();
		result.setCreationMoment(new Date());
		

		return result;
	}

	@Override
	public void validate(final Request<CHIMPUM> request, final CHIMPUM entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
	}

	@Override
	public void create(final Request<CHIMPUM> request, final CHIMPUM entity) {
		assert request != null;
		assert entity != null;
		
		this.repository.save(entity);
	}

}
