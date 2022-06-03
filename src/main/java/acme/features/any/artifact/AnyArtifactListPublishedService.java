package acme.features.any.artifact;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.artifacts.Artifact;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractListService;

@Service
public class AnyArtifactListPublishedService implements AbstractListService<Any, Artifact> {
	
	// Internal state ---------------------------------------------------------

	@Autowired
	protected AnyArtifactRepository repository;

	// AbstractListService<Any, Artifact> interface --------------

	@Override
	public boolean authorise(final Request<Artifact> request) {
		assert request != null;
		
	/*	if(request.getModel().hasAttribute("chimpumId")) {
			
			final Integer activeId = request.getPrincipal().getActiveRoleId();		
			final Collection<Integer> existingInventors = this.repository.findAllInventorId();		
			return  existingInventors.stream().anyMatch(x -> Objects.equals(x, activeId));
		}*/
		
		return true;
	}

	@Override
	public Collection<Artifact> findMany(final Request<Artifact> request) {
		assert request != null;
		
		Collection<Artifact> result;		
		final String type=request.getModel().getString("type");
		
		if(request.getModel().hasAttribute("chimpumId")) {
			final Integer chimpumId = request.getModel().getInteger("chimpumId");
			if(type.equals("component")) {
				result = this.repository.findAllComponentsPublishedByTroquaId(chimpumId);
				
			}else {
				result = this.repository.findAllToolsPublishedByTroquaId(chimpumId);
			}
		}
		
		else {
			if(type.equals("component")) {
					result = this.repository.findAllComponentsPublished();
			}else {
					result = this.repository.findAllToolsPublished();					
			}
		}
		return result;
	}
	
	@Override
	public void unbind(final Request<Artifact> request, final Artifact entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "name", "retailPrice", "artifactType");

	}
}
