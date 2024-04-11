# JdbcBasics

Statement vs Prepared Statement


Statement :

1. The Statement interface in Java represents a SQL statement that is sent to the database to be executed.
2. When you use a Statement, you typically concatenate values directly into the SQL string.
3. This approach is vulnerable to SQL injection attacks if the values are not properly sanitized or validated.
4. Statement is generally used for executing static SQL queries where the values are known at compile time and don't change frequently.
5. It's less efficient for executing the same query multiple times with different parameters since the entire SQL string needs to be parsed and compiled each time.
   

In the code I have used Statement to create , insert and select employee objects into the employee table.



Prepared Statement :

1. The PreparedStatement interface extends Statement and provides additional features for executing parameterized SQL queries.
2. With PreparedStatement, you create a parameterized SQL query with placeholders (?) for parameters and then set the parameter values separately.
3. This approach helps prevent SQL injection attacks because the parameter values are sent separately from the SQL query and are automatically sanitized by the JDBC driver.
4. PreparedStatement is more efficient for executing the same query multiple times with different parameter values since the SQL query is precompiled by the database server, and only the parameter values need to be sent for each execution.
5. It's suitable for executing dynamic SQL queries where the values are not known until runtime.

In the uploaded code I have used preparedStatement to update existing value from the employee table as we can pass what values we want to change at runtime.
