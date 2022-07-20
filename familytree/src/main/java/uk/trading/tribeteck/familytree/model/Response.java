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
public class Response implements Serializable {

	private static final long serialVersionUID = 6325817597055098475L;

	@JsonProperty("responseCode")
	private String responseCode;

	@JsonProperty("responseMessage")
	private String responseMessage;

	@JsonProperty("results")
	private RelationResponse results;

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public RelationResponse getResults() {
		return results;
	}

	public void setResults(RelationResponse results) {
		this.results = results;
	}

}
