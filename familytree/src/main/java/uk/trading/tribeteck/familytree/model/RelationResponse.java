package uk.trading.tribeteck.familytree.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
public class RelationResponse implements Serializable {

	private static final long serialVersionUID = -5502306523413240774L;

	@JsonProperty("name")
	private String name;
	
	@JsonProperty("relationType")
	private String relationType;
	
	@JsonProperty("persons")
	private List<String> persons = new ArrayList<>();

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
	public String getRelationType() {
		return relationType;
	}

	/**
	 * @param relationType the relationType to set
	 */
	public void setRelationType(String relationType) {
		this.relationType = relationType;
	}

	/**
	 * @return the persons
	 */
	public List<String> getPersons() {
		return persons;
	}

	/**
	 * @param persons the persons to set
	 */
	public void setPersons(List<String> persons) {
		this.persons = persons;
	}
	
	
}
