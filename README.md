
This project highlights Swagger API, DAO and JPA Repository patterns and how to handle exceptions with the 
spring controller advice. We do not catch runtime exceptions in the normal cases. It uses integrated
tests to demonstrate the goals. 



ToDo List
- Add examples for JPA Specification and QueryDSL.  These provide dynamic sql where clause 
generation. This avoids writing lots of unique but hardly used DAO methods and overly verbose
 and hard to test stringbuilder type sql statements.

- Do an example using JPA @MappedSuperClass for entities with the same columns but are in
 different schemas.
 
- Add OAuth permissions to swagger and rest endpoints so that the swagger UI can be used 
with authentication and the endpoints give good feedback with access denied messages.

- HSQL and Java 8 Date Time don't seem to jive well.  Need to fix or maybe move to another 
embedded database. 

- Add some documentation to the rest endpoints.  There is an annotation for it. 