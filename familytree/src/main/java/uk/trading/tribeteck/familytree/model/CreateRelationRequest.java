
package uk.trading.tribeteck.familytree.model;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
@Setter
@Getter
@Data
@Validated
@ConfigurationProperties("messages")
public class CreateRelationRequest implements Serializable {
	
	private static final long serialVersionUID = -7515875932084425309L;

	@JsonProperty("person1")
	@Valid
	@NotNull(message="{person1.notnull}")
	@NotEmpty(message="{person1.notempty}")
	private RelationRequest person1;
	
	@JsonProperty("person2")
	@Valid
	@NotNull(message="{person2.notnull}")
	@NotEmpty(message="{person1.notempty}")
	private  RelationRequest person2;

	/**
	 * @return the person1
	 */
	public RelationRequest getPerson1() {
		return person1;
	}

	/**
	 * @param person1 the person1 to set
	 */
	public void setPerson1(RelationRequest person1) {
		this.person1 = person1;
	}

	/**
	 * @return the person2
	 */
	public RelationRequest getPerson2() {
		return person2;
	}

	/**
	 * @param person2 the person2 to set
	 */
	public void setPerson2(RelationRequest person2) {
		this.person2 = person2;
	}
	
	
}
