package uk.trading.tribeteck.familytree.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import uk.trading.tribeteck.familytree.constants.ApplicationConstants;
import uk.trading.tribeteck.familytree.exceptions.InvalidInputException;
import uk.trading.tribeteck.familytree.model.CreateRelationRequest;
import uk.trading.tribeteck.familytree.model.FamilyTree;
import uk.trading.tribeteck.familytree.model.Gender;
import uk.trading.tribeteck.familytree.model.Person;
import uk.trading.tribeteck.familytree.model.RelationRequest;
import uk.trading.tribeteck.familytree.model.RelationResponse;
import uk.trading.tribeteck.familytree.model.RelationType;
import uk.trading.tribeteck.familytree.model.Response;
import uk.trading.tribeteck.familytree.repository.FamilyTreeRepository;
import uk.trading.tribeteck.familytree.utils.FamilyTreeUtils;

/**
 * @author
 *
 */
@Service
public class FamilyTreeService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private FamilyTreeRepository repository;

	public ResponseEntity<Response> findRelations(RelationRequest relationRequest) {
		logger.info("Find Relations: Start");
		ResponseEntity<Response> responseEntity = null;

		Response response = getPersonByRelationType(relationRequest);

		if ((Objects.nonNull(response.getResults())) && response.getResults().getPersons().isEmpty()) {
			response.setResponseCode(ApplicationConstants.ERROR_01);
			response.setResponseMessage(ApplicationConstants.FAILURE);
			responseEntity = new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		} else {
			response.setResponseCode(ApplicationConstants.SUCCESS_CODE);
			response.setResponseMessage(ApplicationConstants.SUCCESS);
			responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
		}
		logger.info("Find Relations: End");
		return responseEntity;

	}

	private Response getPersonByRelationType(RelationRequest relationRequest) {
		logger.info("Get Person By RelationType: Start");

		Response response = new Response();

		Person person = null;
		final List<Person> persons = new ArrayList<>();
		try {
			FamilyTree tree = repository.getTree();
			logger.info("RelationType: {}", relationRequest.getRelationType());
			RelationResponse relationResponse = new RelationResponse();
			relationResponse.setName(relationRequest.getName());
			relationResponse.setRelationType(relationRequest.getRelationType().toString());

			switch (relationRequest.getRelationType()) {
			case FATHER:
				person = FamilyTreeUtils
						.getPerson(FamilyTreeUtils.fetchParent(relationRequest.getName(), Gender.MALE, tree));
				break;
			case MOTHER:
				person = FamilyTreeUtils
						.getPerson(FamilyTreeUtils.fetchParent(relationRequest.getName(), Gender.FEMALE, tree));
				break;
			case BROTHER:
				FamilyTreeUtils.fetchSiblings(relationRequest.getName(), Gender.MALE, tree)
						.forEach(p -> persons.add(p));
				break;
			case SISTER:
				FamilyTreeUtils.fetchSiblings(relationRequest.getName(), Gender.FEMALE, tree)
						.forEach(p -> persons.add(p));
				break;
			case SON:
				FamilyTreeUtils.fetchChildrens(relationRequest.getName(), Gender.MALE, tree)
						.forEach(p -> persons.add(p));

				break;
			case DAUGHTER:
				FamilyTreeUtils.fetchChildrens(relationRequest.getName(), Gender.FEMALE, tree)
						.forEach(p -> persons.add(p));
				break;
			case COUSIN:
				FamilyTreeUtils.fetchCousins(relationRequest.getName(), tree).forEach(p -> persons.add(p));
				break;
			case GRANDMOTHER:
				person = FamilyTreeUtils
						.getPerson(FamilyTreeUtils.fetchGrandParent(relationRequest.getName(), Gender.FEMALE, tree));
				break;
			case GRANDFATHER:
				person = FamilyTreeUtils
						.getPerson(FamilyTreeUtils.fetchGrandParent(relationRequest.getName(), Gender.MALE, tree));
				break;
			case GRANDSON:
				FamilyTreeUtils.fetchGrandChildrens(relationRequest.getName(), Gender.MALE, tree)
						.forEach(p -> persons.add(p));
				break;
			case GRANDDAUGHTER:
				FamilyTreeUtils.fetchGrandChildrens(relationRequest.getName(), Gender.FEMALE, tree)
						.forEach(p -> persons.add(p));
				break;
			case AUNT:
				FamilyTreeUtils.fetchUnclesOrAunts(relationRequest.getName(), Gender.FEMALE, tree)
						.forEach(p -> persons.add(p));
				break;
			case UNCLE:
				FamilyTreeUtils.fetchUnclesOrAunts(relationRequest.getName(), Gender.MALE, tree)
						.forEach(p -> persons.add(p));
				break;
			case HUSBAND:
				// person =
				// FamilyTreeUtils.getPerson(FamilyTreeUtils.fetchSpouce(relationRequest.getName(),
				// tree));
				// break;

			case WIFE:
				person = FamilyTreeUtils.getPerson(FamilyTreeUtils.fetchSpouce(relationRequest.getName(), tree));
				break;
			}

			if (Objects.nonNull(person))
				persons.add(person);

			relationResponse.setPersons(persons.stream().map(Person::getName).collect(Collectors.toList()));
			logger.info("RelationType: {}, Person Name: {}", relationRequest.getRelationType(),
					relationResponse.getPersons());
			response.setResults(relationResponse);

		} catch (InvalidInputException ie) {
			logger.error("Error while Getting Person By RelationType: {}", ie.getMessage());
		}

		logger.info("Get Person By RelationType: End");
		return response;
	}

	public ResponseEntity<Response> createRelations(CreateRelationRequest createRelationRequest) {
		logger.info("Create Relations: Start");
		ResponseEntity<Response> responseEntity = null;
		Response response = new Response();
		try {
			RelationType type1 = createRelationRequest.getPerson1().getRelationType();
			RelationType type2 = createRelationRequest.getPerson2().getRelationType();

			if (Objects.isNull(type1) || Objects.isNull(type2))
				throw new InvalidInputException(
						"Relation Type must be any one of " + Arrays.asList(RelationType.values()).stream()
								.map(RelationType::toString).collect(Collectors.toList()));

			FamilyTreeUtils.addPerson(createRelationRequest.getPerson1().getName(), type1,
					createRelationRequest.getPerson2().getName(), type2, repository.getTree());
			logger.info("Welcome to the family : {}", createRelationRequest.getPerson2().getName());

			RelationRequest relationRequest = new RelationRequest();
			relationRequest.setName(createRelationRequest.getPerson1().getName());
			relationRequest.setRelationType(type2);
			response = getPersonByRelationType(relationRequest);

			if ((Objects.nonNull(response.getResults())) && response.getResults().getPersons().isEmpty()) {
				response.setResponseCode(ApplicationConstants.ERROR_01);
				response.setResponseMessage(ApplicationConstants.FAILURE);
				responseEntity = new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
			} else {
				response.setResponseCode(ApplicationConstants.SUCCESS_CODE);
				response.setResponseMessage(ApplicationConstants.CREATE_RELATION_SUCCESS);
				responseEntity = new ResponseEntity<>(response, HttpStatus.CREATED);
			}

		} catch (InvalidInputException ie) {
			logger.error("Error while Creating the Relations: {}", ie.getMessage());
			response.setResponseCode(ApplicationConstants.ERROR_02);
			response.setResponseMessage(ie.getMessage());
			responseEntity = new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		logger.info("Create Relations: End");
		return responseEntity;
	}

	public ResponseEntity<Response> clearRelations() {
		logger.info("Clear Relations: Start");
		ResponseEntity<Response> responseEntity = null;

		repository.clearDate();
		Response response = new Response();
		response.setResponseCode(ApplicationConstants.SUCCESS_CODE);
		response.setResponseMessage(ApplicationConstants.CLEAR_RELATION_SUCCESS);
		responseEntity = new ResponseEntity<>(response, HttpStatus.OK);

		logger.info("Clear Relations: End");
		return responseEntity;

	}
}
