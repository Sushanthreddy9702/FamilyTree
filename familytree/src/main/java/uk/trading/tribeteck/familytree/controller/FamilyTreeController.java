package uk.trading.tribeteck.familytree.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import uk.trading.tribeteck.familytree.model.CreateRelationRequest;
import uk.trading.tribeteck.familytree.model.RelationRequest;
import uk.trading.tribeteck.familytree.model.Response;
import uk.trading.tribeteck.familytree.services.FamilyTreeService;

/**
 * @author
 *
 */

@RestController
@Tag(name = "FamilyTreeController", description = "FamilyTree API")
public class FamilyTreeController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private FamilyTreeService familyTreeService;

	@Operation(description = "FamilyTreeController", summary = "FamilyTree Controller Summary", tags = {
			"FamilyTreeController" })

	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "FamilyTreeController Response", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Response.class)) }) })

	@GetMapping(value = "/relations", produces = { "application/json", "application/xml" })
	public ResponseEntity<Response> findRelations(
			@Parameter(description = "Family Tree.", required = true) @Valid @RequestBody RelationRequest relationRequest) {

		logger.info("Find Relation: Start");
		ResponseEntity<Response> responseEntity = null;

		responseEntity = familyTreeService.findRelations(relationRequest);

		logger.info("Find Relation Response Entity: {}", responseEntity);

		logger.info("Find Relation: END");
		return responseEntity;
	}

	@PostMapping(value = "/relations", produces = { "application/json", "application/xml" })
	public ResponseEntity<Response> createRelations(
			@Parameter(description = "Create Relation Request.", required = true) @Valid @RequestBody CreateRelationRequest createRelationRequest) {

		logger.info("Create Relation: Start");
		ResponseEntity<Response> responseEntity = null;

		responseEntity = familyTreeService.createRelations(createRelationRequest);

		logger.info("Create Relation Response Entity: {}", responseEntity);

		logger.info("Create Relation: END");
		return responseEntity;

	}

	@DeleteMapping(value = "/relations", produces = { "application/json", "application/xml" })
	public ResponseEntity<Response> clearRelations() {

		logger.info("Create Relation: Start");
		ResponseEntity<Response> responseEntity = null;

		responseEntity = familyTreeService.clearRelations();

		logger.info("Create Relation Response Entity: {}", responseEntity);

		logger.info("Create Relation: END");
		return responseEntity;

	}
}
