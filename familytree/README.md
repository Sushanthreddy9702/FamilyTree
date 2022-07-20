# 1.1 Introduction

	Creating an application to model a very basic family tree, application must have the following core requirements:

	•	Initialise the family tree with 2 members, 1 male, 1 female
	•	Add a child; each child must have 1 father and 1 mother. Parents must be a current family member
	•	List both parents for any given family member
	•	List children for any given family member
	•	List all descendants for any given family member
	•	List all ancestors for any given family member
	•	These operations should be exposed through a REST API

This familytree API developed using a java Spring boot, HTTP/JSON Service’s to provide the following functionality:

# 1.2 Deliverables

	•	A service implementation of the Family Tree API requirement. 
	•	Complete API documentation.
	•	Documentation on how to run / test the code / and describing how it works. 
	•	Package all the source code, deployable/s and documentation. 
	
# 1.3 Start and Stop API

	Prerequisite 
	1.	JDK 1.8 Installation
	2.	Maven 
	3.	Postman


	Copy the below jar file to any location. 
	
	Run below command to start the API

	java -jar familytree-0.0.1-SNAPSHOT.jar
  
# 1.4 API Postman Collection:

	Import the below postman collection to test the API endpoints.
	
	familytree-app.postman_collection.json
	
# 1.5 API Resource Details: 


	Before application start up we have creating below mock Family Tree Relations. If we want we can clear/rest by using endpoints.
	
	Relation Types:
	
	FATHER, MOTHER, BROTHER, SISTER, SON, DAUGHTER, COUSIN, GRANDMOTHER, GRANDFATHER, GRANDSON, GRANDDAUGHTER, AUNT, UNCLE, HUSBAND, WIFE

	Tree Relation Type:
	
	SPOUSE, PARENT, CHILD

	Family Tree Mock Data will initialize before application startup.
	
	Person1  Relation Person2 Relation
	--------------------------------
	"Evan", HUSBAND, "Diana", WIFE
	"Evan", FATHER, "John", SON
	"Evan", FATHER, "Alex", SON
	"Evan", FATHER, "Joe", SON
	"Evan", FATHER, "Nisha", DAUGHTER
	"Alex", HUSBAND, "Nancy", WIFE
	"Alex", FATHER, "Jacob", SON
	"Alex", FATHER, "Shaun", SON
	"Joe", HUSBAND, "Niki", WIFE
	"Joe", FATHER, "Sally", DAUGHTER
	"Joe", FATHER, "Piers", SON
	"Piers", HUSBAND, "Pippa", WIFE
	"Piers", FATHER, "Sarah", DAUGHTER
	"Nisha", WIFE, "Adam", HUSBAND
	"Adam", FATHER, "Ruth", DAUGHTER
	"Adam", FATHER, "Paul", SON
	"Adam", FATHER, "William", SON
	"Sally", WIFE, "Owen", HUSBAND
	"Ruth", WIFE, "Neil", HUSBAND
	"Paul", HUSBAND, "Zoe", WIFE
	"Paul", FATHER, "Roger", SON
	"William", HUSBAND, "Rose", WIFE
	"William", FATHER, "Steve", SON
	"Jacob", HUSBAND, "Rufi", WIFE
	"Jacob", FATHER, "Bern", SON
	"Jacob", FATHER, "Sophia", DAUGHTER
	"Sophia", WIFE, "George", HUSBAND
	
	
	1. Get Family Relation Details:
		
		Method: GET
		Endpoint: http://localhost:8080/familytree/v1/relations
		
		sample request:

			{
				"name": "Paul",
				"relationType": "SON"
			}

		sample response:
		
			{
				"responseCode": "FM-00",
				"responseMessage": "Success",
				"results": {
					"name": "Paul",
					"relationType": "SON",
					"persons": [
						"Roger"
					]
				}
			}
			
	2. Create New Family Relation:
		
		Method: POST
		Endpoint: http://localhost:8080/familytree/v1/relations
		
		Sample Request: 
		
			{
				"person1": {
					"name": "Bern",
					"relationType": "Husband" 
				},
				"person2": {
					"name": "Julia",
					"relationType": "Wife"
				}
			}
			
		Sample Response:

			{
				"responseCode": "FM-00",
				"responseMessage": "Success : Welcome to the family",
				"results": {
					"name": "Bern",
					"relationType": "WIFE",
					"persons": [
						"Julia"
					]
				}
			}
			
	3. Clear/ Reset Family Tree Relations:
		
		Method: DELETE
		Endpoint: http://localhost:8080/familytree/v1/relations
		
		Sample Response:
		
		{
			"responseCode": "FM-00",
			"responseMessage": "Success : Familytree Data Cleared."
		}

