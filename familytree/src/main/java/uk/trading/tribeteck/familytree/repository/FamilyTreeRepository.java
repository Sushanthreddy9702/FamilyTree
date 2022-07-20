/**
 * 
 */
package uk.trading.tribeteck.familytree.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import uk.trading.tribeteck.familytree.exceptions.InvalidInputException;
import uk.trading.tribeteck.familytree.model.FamilyTree;
import uk.trading.tribeteck.familytree.model.RelationType;
import uk.trading.tribeteck.familytree.utils.FamilyTreeUtils;

/**
 * @author
 *
 */

@Repository
public class FamilyTreeRepository {

	private Logger logger = LoggerFactory.getLogger(FamilyTreeRepository.class);

	private FamilyTree tree = new FamilyTree();

	public FamilyTreeRepository() {
		createMockData();
	}

	public FamilyTree getTree() {
		return tree;
	}

	private FamilyTree createMockData() {
		logger.info("Create Mock Data: Start");
		try {

			FamilyTreeUtils.addPerson("Evan", RelationType.HUSBAND, "Diana", RelationType.WIFE, tree);
			FamilyTreeUtils.addPerson("Evan", RelationType.FATHER, "John", RelationType.SON, tree);
			FamilyTreeUtils.addPerson("Evan", RelationType.FATHER, "Alex", RelationType.SON, tree);
			FamilyTreeUtils.addPerson("Evan", RelationType.FATHER, "Joe", RelationType.SON, tree);
			FamilyTreeUtils.addPerson("Evan", RelationType.FATHER, "Nisha", RelationType.DAUGHTER, tree);

			FamilyTreeUtils.addPerson("Alex", RelationType.HUSBAND, "Nancy", RelationType.WIFE, tree);
			FamilyTreeUtils.addPerson("Alex", RelationType.FATHER, "Jacob", RelationType.SON, tree);
			FamilyTreeUtils.addPerson("Alex", RelationType.FATHER, "Shaun", RelationType.SON, tree);

			FamilyTreeUtils.addPerson("Joe", RelationType.HUSBAND, "Niki", RelationType.WIFE, tree);
			FamilyTreeUtils.addPerson("Joe", RelationType.FATHER, "Sally", RelationType.DAUGHTER, tree);
			FamilyTreeUtils.addPerson("Joe", RelationType.FATHER, "Piers", RelationType.SON, tree);

			FamilyTreeUtils.addPerson("Piers", RelationType.HUSBAND, "Pippa", RelationType.WIFE, tree);
			FamilyTreeUtils.addPerson("Piers", RelationType.FATHER, "Sarah", RelationType.DAUGHTER, tree);

			FamilyTreeUtils.addPerson("Nisha", RelationType.WIFE, "Adam", RelationType.HUSBAND, tree);
			FamilyTreeUtils.addPerson("Adam", RelationType.FATHER, "Ruth", RelationType.DAUGHTER, tree);
			FamilyTreeUtils.addPerson("Adam", RelationType.FATHER, "Paul", RelationType.SON, tree);
			FamilyTreeUtils.addPerson("Adam", RelationType.FATHER, "William", RelationType.SON, tree);

			FamilyTreeUtils.addPerson("Sally", RelationType.WIFE, "Owen", RelationType.HUSBAND, tree);
			FamilyTreeUtils.addPerson("Ruth", RelationType.WIFE, "Neil", RelationType.HUSBAND, tree);

			FamilyTreeUtils.addPerson("Paul", RelationType.HUSBAND, "Zoe", RelationType.WIFE, tree);
			FamilyTreeUtils.addPerson("Paul", RelationType.FATHER, "Roger", RelationType.SON, tree);

			FamilyTreeUtils.addPerson("William", RelationType.HUSBAND, "Rose", RelationType.WIFE, tree);
			FamilyTreeUtils.addPerson("William", RelationType.FATHER, "Steve", RelationType.SON, tree);

			FamilyTreeUtils.addPerson("Jacob", RelationType.HUSBAND, "Rufi", RelationType.WIFE, tree);
			FamilyTreeUtils.addPerson("Jacob", RelationType.FATHER, "Bern", RelationType.SON, tree);
			FamilyTreeUtils.addPerson("Jacob", RelationType.FATHER, "Sophia", RelationType.DAUGHTER, tree);

			FamilyTreeUtils.addPerson("Sophia", RelationType.WIFE, "George", RelationType.HUSBAND, tree);

		} catch (InvalidInputException e) {
			e.printStackTrace();
		}

		logger.info("Create Mock Data: END");
		return tree;
	}

	public void clearDate() {
		tree.setRoot(null);
		tree.getVisted().clear();
	}
}
