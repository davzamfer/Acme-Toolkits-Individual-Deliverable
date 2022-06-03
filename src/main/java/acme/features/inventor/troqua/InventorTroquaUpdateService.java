package acme.features.inventor.troqua;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.troqua.Troqua;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;
import features.SpamDetector;

@Service
public class InventorTroquaUpdateService implements AbstractUpdateService<Inventor, Troqua> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorTroquaRepository repository;
	
	// AbstractUpdateService<Inventor, Artifact> interface ---------------------------
	
	
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

		request.bind(entity, errors, "theme", "statement", "startDate", "finishDate","quota", "additionalInfo");
	}

	@Override
	public void unbind(final Request<Troqua> request, final Troqua entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		model.setAttribute("code", entity.getCode());
		request.unbind(entity, model, "creationMoment", "code", "theme", "statement", "startDate", "finishDate","quota", "additionalInfo");
	}

	@Override
	public Troqua findOne(final Request<Troqua> request) {
		assert request != null;
		
		Integer id;
		Troqua chimpum;
		id = request.getModel().getInteger("id");
		chimpum = this.repository.findTroquaById(id);
		
		return chimpum;
	}

	@Override
	public void validate(final Request<Troqua> request, final Troqua entity, final Errors errors) {
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
		
		if(!errors.hasErrors("theme")) {
			errors.state(request, !spamDetector.containsSpam(weakSpamTerms.split(","), weakSpamThreshold, entity.getTheme())
				&& !spamDetector.containsSpam(strongSpamTerms.split(","), strongSpamThreshold, entity.getTheme()),
				"theme", "inventor.toolkit.form.error.spam");
		}
		
		if(!errors.hasErrors("statement")) {
			errors.state(request, !spamDetector.containsSpam(weakSpamTerms.split(","), weakSpamThreshold, entity.getStatement())
				&& !spamDetector.containsSpam(strongSpamTerms.split(","), strongSpamThreshold, entity.getStatement()),
				"statement", "inventor.toolkit.form.error.spam");
		}
		
		if(!errors.hasErrors("startDate")) {
			Calendar calendar;
			
			calendar = new GregorianCalendar();
			calendar.add(Calendar.MONTH, 1);
			calendar.add(Calendar.DAY_OF_MONTH, -1);
			
			errors.state(request, entity.getStartDate().after(calendar.getTime()), "startDate", "inventor.Troqua.form.error.startDate");
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
			
			errors.state(request, errorState, "finishDate", "inventor.Troqua.form.error.finishDate");
		}
		
		if (!errors.hasErrors("quota")) {
			final String currency = entity.getQuota().getCurrency();
			final String currencyAvaliable = this.repository.acceptedCurrencies();
			boolean acceptedCurrency = false;
			
			for(final String cur: currencyAvaliable.split(",")) {
				acceptedCurrency = cur.trim().equalsIgnoreCase(currency);
				if(acceptedCurrency)
					break;
			}
			errors.state(request, entity.getQuota().getAmount() > 0 , "quota", "inventor.Troqua.form.error.negative-quota");
			errors.state(request,acceptedCurrency, "quota", "inventor.Troqua.form.error.negative-currency");
		}

	}

	@Override
	public void update(final Request<Troqua> request, final Troqua entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
		
	}
	

}
