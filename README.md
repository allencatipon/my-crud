For database:
1. Run Application
2. Look for "database available"
Sample:
H2 console available at '/h2-console'. Database available at 'jdbc:h2:mem:e70d25fc-24ff-4053-b77d-c58d7c7df8d7'
3. Visit http://localhost:8080/h2-console/, On JDBC URL, put jdbc:h2:mem:e70d25fc-24ff-4053-b77d-c58d7c7df8d7, click connect

Branch: one-to-many relationship db




Errors Encountered:

Error 1: detached entity passed to persist
Solution 1: convert CascadeType.ALL to CascadeType.MERGE (https://www.javafixing.com/2022/01/fixed-why-am-i-obtaining-this-entity.html)