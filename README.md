# Directory-Search-GE-Second-Exercise

Simple Java program to retrieve .sql files from current repository and it's subrepositories.<br><br>
If the file contains a query that modified a column, the program will save every table( except tables, which name's has "dwXX" in it, XX stands for two random numbers) and it's column that was changed into a txt file. 
<br<br>
For example:
```
.sql file has these queries in it:

ALTER TABLE employee ADD COLUMN department CHAR(255);
DROP TABLE categories_archive;
ALTER TABLE client_addresses ORDER BY city, name;

Result.txt:
employee.department

//DROP table not modifies column
//ALTER TABLE client_addresses ORDER BY city, name; --> only modifies table, not specific column

```
