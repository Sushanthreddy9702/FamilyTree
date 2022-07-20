package uk.trading.tribeteck.familytree.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import lombok.Data;
import lombok.Getter;
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
public class FamilyTree implements Serializable {

	private static final long serialVersionUID = 8381641673463276543L;

	private Person root;

	private Map<String, Boolean> visted = new HashMap<>();

	public Person getRoot() {
		return root;
	}

	public void setRoot(Person root) {
		this.root = root;
	}

	public Map<String, Boolean> getVisted() {
		return visted;
	}

	public void setVisted(Map<String, Boolean> visted) {
		this.visted = visted;
	}
}
