For database:
1. Run Application
2. Look for "database available"
Sample:
H2 console available at '/h2-console'. Database available at 'jdbc:h2:mem:e70d25fc-24ff-4053-b77d-c58d7c7df8d7'
3. Visit http://localhost:8080/h2-console/, On JDBC URL, put jdbc:h2:mem:e70d25fc-24ff-4053-b77d-c58d7c7df8d7, click connect

For creating new book:
POST: http://localhost:8080/api/books
{
    "author": "author999",
    "title": "title999"
}

For getting all books:
GET: http://localhost:8080/api/books

For getting book by ID:
GET: http://localhost:8080/api/books/2

For updating a book:
PUT: http://localhost:8080/api/books/2
{
    "author": "author999",
    "title": "title999"
}

For deleting a book:
DELETE: http://localhost:8080/api/books/2

To sort by authorName:
http://localhost:8080/api/books/sort?field=authorName

To sort by authorId:
http://localhost:8080/api/books/sort?field=author

To sort by Id with pagination:
http://localhost:8080/api/books/pagination/0/5

To sort by field with pagination
http://localhost:8080/api/books/paginationAndSort/0/3?field=title
http://localhost:8080/api/books/paginationAndSort/2/3?field=authorName

@Valid - so that there's a validation that it should be NotNull

Branch: 
feature/crud/oneToMany_ManyToOne_DB
feature/crud/withTDD




Errors Encountered:

Error 1: detached entity passed to persist
Solution 1: convert CascadeType.ALL to CascadeType.MERGE (https://www.javafixing.com/2022/01/fixed-why-am-i-obtaining-this-entity.html)

