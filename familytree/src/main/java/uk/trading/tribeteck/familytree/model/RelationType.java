package uk.trading.tribeteck.familytree.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum RelationType {
	FATHER("FATHER"), MOTHER("MOTHER"), BROTHER("BROTHER"), SISTER("SISTER"), SON("SON"), DAUGHTER("DAUGHTER"),
	COUSIN("COUSIN"), GRANDMOTHER("GRANDMOTHER"), GRANDFATHER("GRANDFATHER"), GRANDSON("GRANDSON"),
	GRANDDAUGHTER("GRANDDAUGHTER"), AUNT("AUNT"), UNCLE("UNCLE"), HUSBAND("HUSBAND"), WIFE("WIFE");

	private String value;

	RelationType(String value) {
		this.value = value;
	}

	@Override
	@JsonValue
	public String toString() {
		return String.valueOf(value);
	}

	@JsonCreator
	public static RelationType getEnumFromValue(String value) {
		for (RelationType testEnum : values()) {
			if (testEnum.value.equalsIgnoreCase(value)) {
				return testEnum;
			}
		}
		return null;
	}
}
