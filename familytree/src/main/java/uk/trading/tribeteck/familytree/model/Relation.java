package uk.trading.tribeteck.familytree.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@Data

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Relation implements Serializable {

	private static final long serialVersionUID = -6177732418660730414L;

	@JsonProperty("treeRelationType")
	private TreeRelationType treeRelationType;

	@JsonProperty("person1")
	private Person person1;

	@JsonProperty("person2")
	private Person person2;

	public Relation(TreeRelationType treeRelationType, Person person1, Person person2) {
		this.treeRelationType = treeRelationType;
		this.person1 = person1;
		this.person2 = person2;
	}

	public TreeRelationType getType() {
		return treeRelationType;
	}

	public void setType(TreeRelationType treeRelationType) {
		this.treeRelationType = treeRelationType;
	}

	public Person getPerson1() {
		return person1;
	}

	public void setPerson1(Person person1) {
		this.person1 = person1;
	}

	public Person getPerson2() {
		return person2;
	}

	public void setPerson2(Person person2) {
		this.person2 = person2;
	}

}
