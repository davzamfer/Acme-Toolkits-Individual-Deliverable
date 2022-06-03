package acme.features.inventor.troqua;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.troqua.Troqua;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class InventorTroquaShowService implements AbstractShowService<Inventor, Troqua>{
	
	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorTroquaRepository repository;

	// AbstractListService<Inventor, Artifact> interface ---------------------------
	
	@Override
	public boolean authorise(final Request<Troqua> request) {
		assert request != null;
		
	
		return true;
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
	public void unbind(final Request<Troqua> request, final Troqua entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		model.setAttribute("code", entity.getCode());
		request.unbind(entity, model, "pattern", "creationMoment", "theme", "statement", "startDate", "finishDate","quota", "additionalInfo");

	}
	
}
