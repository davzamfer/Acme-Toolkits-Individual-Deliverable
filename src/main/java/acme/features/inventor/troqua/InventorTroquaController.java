package acme.features.inventor.troqua;
import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.troqua.Troqua;
import acme.framework.controllers.AbstractController;
import acme.roles.Inventor;


@Controller
public class InventorTroquaController extends AbstractController<Inventor, Troqua>{

	// Internal state -------------------------------------------------------------------

	@Autowired
	protected InventorTroquaListService listService;

	@Autowired
	protected InventorTroquaShowService showService;
	
	@Autowired
	protected InventorTroquaCreateService createService;
		
	@Autowired
	protected InventorTroquaDeleteService deleteService;
	
	@Autowired
	protected InventorTroquaUpdateService updateService;
	
	
	// Constructors ---------------------------------------------------------------------

	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.listService);
		super.addCommand("show", this.showService);
		super.addCommand("create", this.createService);
		super.addCommand("update", this.updateService);
		super.addCommand("delete", this.deleteService);
	}
}