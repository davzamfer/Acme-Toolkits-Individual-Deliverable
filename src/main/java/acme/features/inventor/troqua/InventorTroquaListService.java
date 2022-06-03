package acme.features.inventor.troqua;


import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.troqua.Troqua;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Inventor;

@Service
public class InventorTroquaListService implements AbstractListService<Inventor, Troqua>{

	// Internal state -------------------------------------------------------------------

	@Autowired
	protected InventorTroquaRepository repository;

	// AbstractListService<Inventor, Patronage> interface ---------------------------------

	@Override
	public boolean authorise(final Request<Troqua> request) {
		assert request != null;

		return true;
	}

	@Override
	public Collection<Troqua> findMany(final Request<Troqua> request) {
		assert request != null;

		return this.repository.findAllTroquas();
	}

	@Override
	public void unbind(final Request<Troqua> request, final Troqua entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "theme", "statement");
	}

}