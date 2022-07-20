
package uk.trading.tribeteck.familytree.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author
 *
 */
@ToString
@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Person implements Serializable {

	private static final long serialVersionUID = 1073370614633963673L;

	@JsonProperty("name")
	private String name;

	@JsonProperty("gender")
	private Gender gender;

	@JsonProperty("relations")
	private List<Relation> relations = new ArrayList<>();

	public Person(String name, Gender gender) {
		this.name = name;
		this.gender = gender;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Relation> getRelations() {
		return relations;
	}

	public void setRelations(List<Relation> relations) {
		this.relations = relations;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public void addRelation(Relation relation) {
		relations.add(relation);
	}
}
