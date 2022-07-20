package uk.trading.tribeteck.familytree.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum TreeRelationType {

	SPOUSE("SPOUSE"), PARENT("PARENT"), CHILD("CHILD");

	private String valueInJson;

	private TreeRelationType(String valueInJson) {
		this.valueInJson = valueInJson;
	}

	@JsonCreator
	public static TreeRelationType getEnumFromValue(String value) {
		for (TreeRelationType testEnum : values()) {
			if (testEnum.valueInJson.equalsIgnoreCase(value)) {
				return testEnum;
			}
		}
		return null;
	}
}
