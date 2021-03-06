package acme.features.inventor.CHIMPUM;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.CHIMPUM.CHIMPUM;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;
import features.SpamDetector;

@Service
public class InventorCHIMPUMUpdateService implements AbstractUpdateService<Inventor, CHIMPUM> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorCHIMPUMRepository repository;
	
	// AbstractUpdateService<Inventor, Artifact> interface ---------------------------
	
	
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

		request.bind(entity, errors, "title", "description", "startDate", "finishDate","budget", "link");
	}

	@Override
	public void unbind(final Request<CHIMPUM> request, final CHIMPUM entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		model.setAttribute("code", entity.getCode());
		request.unbind(entity, model, "creationMoment", "pattern", "title", "description", "startDate", "finishDate","budget", "link");
	}

	@Override
	public CHIMPUM findOne(final Request<CHIMPUM> request) {
		assert request != null;
		
		Integer id;
		CHIMPUM chimpum;
		id = request.getModel().getInteger("id");
		chimpum = this.repository.findCHIMPUMById(id);
		
		return chimpum;
	}

	@Override
	public void validate(final Request<CHIMPUM> request, final CHIMPUM entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		SpamDetector spamDetector;
		String strongSpamTerms;
		String weakSpamTerms;
		int strongSpamThreshold;
		int weakSpamThreshold;
		
		spamDetector = new SpamDetector();
		strongSpamTerms = this.repository.findStrongSpamTerms();
		weakSpamTerms = this.repository.findWeakSpamTerms();
		strongSpamThreshold = this.repository.findStrongSpamTreshold();
		weakSpamThreshold = this.repository.findWeakSpamTreshold();
		
		if(!errors.hasErrors("title")) {
			errors.state(request, !spamDetector.containsSpam(weakSpamTerms.split(","), weakSpamThreshold, entity.getTitle())
				&& !spamDetector.containsSpam(strongSpamTerms.split(","), strongSpamThreshold, entity.getTitle()),
				"title", "inventor.toolkit.form.error.spam");
		}
		
		if(!errors.hasErrors("description")) {
			errors.state(request, !spamDetector.containsSpam(weakSpamTerms.split(","), weakSpamThreshold, entity.getDescription())
				&& !spamDetector.containsSpam(strongSpamTerms.split(","), strongSpamThreshold, entity.getDescription()),
				"description", "inventor.toolkit.form.error.spam");
		}
		
		if(!errors.hasErrors("startDate")) {
			Calendar calendar;
			
			calendar = new GregorianCalendar();
			calendar.add(Calendar.MONTH, 1);
			calendar.add(Calendar.DAY_OF_MONTH, -1);
			
			errors.state(request, entity.getStartDate().after(calendar.getTime()), "startDate", "inventor.CHIMPUM.form.error.startDate");
		}
		
		if(!errors.hasErrors("finishDate")) {
			Calendar calendar;
			
			boolean errorState = true;
			
			if (entity.getStartDate() != null) {		
				calendar = new GregorianCalendar();
				calendar.setTime(entity.getStartDate());
				calendar.add(Calendar.WEEK_OF_MONTH, 1);
				calendar.add(Calendar.DAY_OF_MONTH, -1);
				errorState = entity.getFinishDate().after(calendar.getTime());
			}
			
			errors.state(request, errorState, "finishDate", "inventor.CHIMPUM.form.error.finishDate");
		}
		
		if (!errors.hasErrors("budget")) {
			final String currency = entity.getBudget().getCurrency();
			final String currencyAvaliable = this.repository.acceptedCurrencies();
			boolean acceptedCurrency = false;
			
			for(final String cur: currencyAvaliable.split(",")) {
				acceptedCurrency = cur.trim().equalsIgnoreCase(currency);
				if(acceptedCurrency)
					break;
			}
			errors.state(request, entity.getBudget().getAmount() > 0 , "budget", "inventor.CHIMPUM.form.error.negative-budget");
			errors.state(request,acceptedCurrency, "budget", "inventor.CHIMPUM.form.error.negative-currency");
		}

	}

	@Override
	public void update(final Request<CHIMPUM> request, final CHIMPUM entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
		
	}
	

}
