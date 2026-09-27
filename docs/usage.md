# Usage

## Create Database

Create a database if not exists

 - name => Database name
 - colum => Database column name 


```java 
Query query = Query.of("create <name> <column> <column>");
ResultQuery result = ndb.run(query);
System.out.println("Result : " + result.getCreateResult());
```

## Read Record 

Read a record in database

 - name => Database name
 - index => Index record in database
 - column => Column name in database 

```java
Query query = Query.of("read <name> <index>");
ResultQuery result = ndb.run(query);
System.out.println("Value :" + result.getReadValue(<column>));
```

## Update Record 

Update a record in database 

 - name => Database name
 - column => Column name in database
 - value => New value 

```java
Query query = Query.of("update <name> <column=value> <column=value");
ResultQuery result = ndb.run(query);
System.out.println("Result :" + result.getUpdateResult());
```


## Delete Database 

Delete a exists database 

 - name => Database name

```java
Query query = Query.of("delete <name>");
ResultQuery result = ndb.run(query);
System.out.println("Result :" + result.getDeleteResult());
```

## Find Record 

Find record in database and return a index of record 

 - name => Database name
 - column => Column name in database
 - value => Expected value

```java
Query query = Query.of("find <name> <column=value> <column=value>");
ResultQuery result = ndb.run(query);
System.out.println("Index :" + result.getFindResult());
```

## Insert Record 

Insert a new record to database 

 - name => Database name
 - column => Column name in database
 - value => Column value

```java
Query query = Query.of("insert <name> <column=value> <column=value>");
ResultQuery result = ndb.run(query);
System.out.println("Result :" + result.getInsertResult());
```

## Search Record 

Searches for all records in the database that match the value 

 - name => Database name
 - Columm => Column name in database
 - value => Expected value 

```java
Query query = Query.of("search <name> <column=value>");
ResultQuery result = ndb.run(query);
System.out.println("Index :" + Arrays.toString(result.getSearchResult()));
```

## Remove Record 

Remove a record from the database based on the index

 - name => Database name
 - index => Index record in database 

```java
Query query = Query.of("remove <name> <index>");
ResultQuery result = ndb.run(query);
System.out.println("Result : " + result.getRemoveResult());
```

## Check Size Database

Checking the size of a database

 - name => Database name

```java
Query query = Query.of("size <name>");
ResultQuery result = ndb.run(query);
System.out.println("Size : " + result.getSizeResult());
```

## Exists Database

Check database exists or not

 - name => Database name

```java
Query query = Query.of("exists <name>");
ResultQuery result = ndb.run(query);
System.out.println("Exists : " + result.getExistsResult());
```

## Print Database

Print all record in database

 - name => Database name

```java
Query query = Query.of("print <name>");
ResultQuery result = ndb.run(query);
System.out.println("Result : " + result.getPrintResult());
```

