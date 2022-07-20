/**
 * 
 */
package uk.trading.tribeteck.familytree.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import uk.trading.tribeteck.familytree.constants.ApplicationConstants;
import uk.trading.tribeteck.familytree.exceptions.InvalidInputException;
import uk.trading.tribeteck.familytree.model.FamilyTree;
import uk.trading.tribeteck.familytree.model.Gender;
import uk.trading.tribeteck.familytree.model.Person;
import uk.trading.tribeteck.familytree.model.Relation;
import uk.trading.tribeteck.familytree.model.RelationType;
import uk.trading.tribeteck.familytree.model.TreeRelationType;

/**
 * @author:
 *
 */
public class FamilyTreeUtils {

	public static Gender fetchGender(RelationType type) {
		if (RelationType.MOTHER.equals(type) || RelationType.DAUGHTER.equals(type) || RelationType.WIFE.equals(type))
			return Gender.FEMALE;
		else
			return Gender.MALE;
	}

	public static TreeRelationType fetchTreeRelationType(RelationType type) {
		if (RelationType.MOTHER.equals(type) || RelationType.FATHER.equals(type))
			return TreeRelationType.CHILD;
		else if (RelationType.HUSBAND.equals(type) || RelationType.WIFE.equals(type))
			return TreeRelationType.SPOUSE;
		else
			return TreeRelationType.PARENT;
	}

	public static void addRelation(TreeRelationType treeRelationType1, Person person1,
			TreeRelationType treeRelationType2, Person person2) {
		Relation relation1 = new Relation(treeRelationType1, person1, person2);
		person1.addRelation(relation1);
		Relation relation2 = new Relation(treeRelationType2, person2, person1);
		person2.addRelation(relation2);
	}

	public static Person findPerson(Person cur, String name, FamilyTree familyTree) {
		familyTree.getVisted().put(cur.getName(), Boolean.TRUE);
		if ((Objects.nonNull(cur)) && cur.getName().equals(name)) {
			familyTree.getVisted().clear();
			return cur;
		} else {
			for (Relation relation : cur.getRelations()) {
				Person person2 = relation.getPerson2();
				if (!familyTree.getVisted().containsKey(person2.getName())) {
					Person person = findPerson(person2, name, familyTree);
					if (person != null) {
						return person;
					}
				}
			}
		}
		return null;
	}

	public static void addPerson(String name1, RelationType type1, String name2, RelationType type2,
			FamilyTree familyTree) throws InvalidInputException {
		TreeRelationType treeRelationType1 = fetchTreeRelationType(type1);
		TreeRelationType treeRelationType2 = fetchTreeRelationType(type2);
		Gender gender1 = fetchGender(type1);
		Gender gender2 = fetchGender(type2);
		if (familyTree.getRoot() == null) {
			Person person1 = new Person(name1, gender1);
			Person person2 = new Person(name2, gender2);
			familyTree.setRoot(person1);
			addRelation(treeRelationType1, person1, treeRelationType2, person2);
		} else {
			Person person1 = findPerson(familyTree.getRoot(), name1, familyTree);
			if (person1 == null) {
				throw new InvalidInputException(ApplicationConstants.INVALID_INPUT);
			}
			Person person2 = new Person(name2, gender2);
			addRelation(treeRelationType1, person1, treeRelationType2, person2);
			if (TreeRelationType.CHILD.equals(treeRelationType1)) {
				for (Relation relation : person1.getRelations()) {
					if (TreeRelationType.SPOUSE.equals(relation.getType())) {
						person1 = relation.getPerson2();
						break;
					}

				}
				addRelation(treeRelationType1, person1, treeRelationType2, person2);
			}

		}

	}

	public static List<Person> fetchChildrens(String name, FamilyTree familyTree) throws InvalidInputException {
		List<Person> children = new ArrayList<>();
		Person person = findPerson(familyTree.getRoot(), name, familyTree);
		if (person == null) {
			throw new InvalidInputException(ApplicationConstants.INVALID_INPUT);
		}
		for (Relation relation : person.getRelations()) {
			if (TreeRelationType.CHILD.equals(relation.getType())) {
				children.add(relation.getPerson2());

			}
		}
		return children;
	}

	public static List<Person> fetchParents(String name, FamilyTree familyTree) throws InvalidInputException {
		List<Person> parents = new ArrayList<>();
		Person person = findPerson(familyTree.getRoot(), name, familyTree);
		if (person == null) {
			throw new InvalidInputException(ApplicationConstants.INVALID_INPUT);
		}
		for (Relation relation : person.getRelations()) {
			if (TreeRelationType.PARENT.equals(relation.getType())) {
				parents.add(relation.getPerson2());

			}
		}
		return parents;
	}

	public static List<Person> fetchSiblings(String name, FamilyTree familyTree) throws InvalidInputException {
		List<Person> siblings = new ArrayList<>();
		Person father = fetchParent(name, Gender.MALE, familyTree);
		if (father != null) {
			List<Person> children = fetchChildrens(father.getName(), familyTree);
			for (Person person : children) {
				if (!person.getName().equals(name)) {
					siblings.add(person);
				}
			}
		}
		return siblings;
	}

	public static List<Person> fetchCousins(String name, FamilyTree familyTree) throws InvalidInputException {
		List<Person> cousins = new ArrayList<>();
		List<Person> parents = fetchParents(name, familyTree);
		for (Person person : parents) {
			List<Person> siblings = fetchSiblings(person.getName(), familyTree);
			for (Person sibling : siblings) {
				List<Person> children = fetchChildrens(sibling.getName(), familyTree);
				cousins.addAll(children);

			}

		}
		return cousins;
	}

	public static List<Person> fetchGrandParents(String name, FamilyTree familyTree) throws InvalidInputException {
		List<Person> grandParents = new ArrayList<>();
		List<Person> parents = fetchParents(name, familyTree);
		for (Person person : parents) {
			grandParents.addAll(fetchParents(person.getName(), familyTree));
		}
		return grandParents;
	}

	public static List<Person> fetchGrandChildrens(String name, FamilyTree familyTree) throws InvalidInputException {
		List<Person> children = fetchChildrens(name, familyTree);
		List<Person> grandChildren = new ArrayList<>();
		for (Person person : children) {
			grandChildren.addAll(fetchChildrens(person.getName(), familyTree));
		}

		return grandChildren;
	}

	public static Person fetchSpouce(String name, FamilyTree familyTree) throws InvalidInputException {
		Person spouce = null;
		Person person = findPerson(familyTree.getRoot(), name, familyTree);
		if (person == null) {
			throw new InvalidInputException(ApplicationConstants.INVALID_INPUT);
		}
		for (Relation relation : person.getRelations()) {
			if (TreeRelationType.SPOUSE.equals(relation.getType())) {
				spouce = relation.getPerson2();
				break;
			}
		}
		return spouce;
	}

	public static Person fetchPerson(String name, Gender gender, FamilyTree familyTree) throws InvalidInputException {
		Person person = null;
		List<Person> parents = fetchParents(name, familyTree);
		for (Person p : parents) {
			if (gender.equals(p.getGender()))
				person = p;
		}
		return person;
	}

	public static Person fetchParent(String name, Gender gender, FamilyTree familyTree) throws InvalidInputException {
		return fetchParents(name, familyTree).stream().filter((parents) -> parents.getGender().equals(gender)).findAny()
				.get();
	}

	public static List<Person> fetchSiblings(String name, Gender gender, FamilyTree familyTree)
			throws InvalidInputException {
		return fetchSiblings(name, familyTree).stream().filter(person -> person.getGender().equals(gender))
				.collect(Collectors.toList());
	}

	public static List<Person> fetchChildrens(String name, Gender gender, FamilyTree familyTree)
			throws InvalidInputException {
		return fetchChildrens(name, familyTree).stream().filter(person -> person.getGender().equals(gender))
				.collect(Collectors.toList());
	}

	public static Person fetchGrandParent(String name, Gender gender, FamilyTree familyTree)
			throws InvalidInputException {
		return fetchGrandParents(name, familyTree).stream().filter((parents) -> parents.getGender().equals(gender))
				.findAny().get();
	}

	public static List<Person> fetchGrandChildrens(String name, Gender gender, FamilyTree familyTree)
			throws InvalidInputException {
		return fetchGrandChildrens(name, familyTree).stream().filter(person -> person.getGender().equals(gender))
				.collect(Collectors.toList());
	}

	public static List<Person> fetchUnclesOrAunts(String name, Gender gender, FamilyTree familyTree)
			throws InvalidInputException {
		List<Person> persons = new ArrayList<>();
		fetchParents(name, familyTree).stream().forEach((parent) -> {
			try {
				fetchSiblings(parent.getName(), familyTree).stream().forEach((sibling) -> {
					if (gender.equals(sibling.getGender())) {
						persons.add(sibling);
					} else {
						Optional<Person> spouce;
						try {
							spouce = Optional.ofNullable(fetchSpouce(sibling.getName(), familyTree));
							if (spouce.isPresent()) {
								persons.add(spouce.get());
							}
						} catch (InvalidInputException e) {
							e.printStackTrace();
						}
					}
				});
			} catch (InvalidInputException e) {
				e.printStackTrace();
			}
		});

		return persons;
	}

	public static Person getPerson(Person person) {
		Optional<Person> p = Optional.ofNullable(person);
		if (p.isPresent())
			return p.get();
		else
			return null;
	}
}
