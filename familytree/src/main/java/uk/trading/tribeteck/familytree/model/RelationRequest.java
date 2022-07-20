package uk.trading.tribeteck.familytree.model;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
public class RelationRequest implements Serializable {

	private static final long serialVersionUID = 4237104130765546615L;

	@NotEmpty(message = "{name.notempty}")
	@NotNull(message = "{name.notnull}")
	@JsonProperty("name")
	private String name;

	@Valid
	@NotNull(message = "{relationType.notnull}")
	@JsonProperty("relationType")
	private RelationType relationType;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the relationType
	 */
	public RelationType getRelationType() {
		return relationType;
	}

	/**
	 * @param relationType the relationType to set
	 */
	public void setRelationType(RelationType relationType) {
		this.relationType = relationType;
	}

}
