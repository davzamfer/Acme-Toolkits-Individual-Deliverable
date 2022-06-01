package acme.entities.CHIMPUM;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.datatypes.Money;
import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CHIMPUM extends AbstractEntity{

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------
	
	@NotNull
	@Pattern(regexp = "^[A-Z]{3}-[0-9]{3}$")
	protected String			pattern;

	@Temporal(TemporalType.TIMESTAMP)
	@Past
	@NotNull
	protected Date				creationMoment;
	
	@NotBlank
	@Length(max = 100)
	protected String			title;	

	@NotBlank
	@Length(max = 255)
	protected String			description;
	
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date				startDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date				finishDate;
	
	@Valid
	@NotNull
	protected Money				budget;	
	
	@URL
	protected String			link;
	
	// Derived attributes -----------------------------------------------------
	
	public String getCode() {
		String code;
		code = new SimpleDateFormat("dd/MM/yy").format(this.creationMoment);
		return this.pattern + "-" + code ;
	}
}
