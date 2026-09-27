# API Reference

## Class nDatabase 
Main class to interact with database.<br>
Location
```bash
id.nodynamic.ndb.nDatabase
```
#### Constructor
```java
nDatabase();
```
Create a instance of nDatabase

#### Method run()
Execute database query.<br>
Return ResultQuery
```java
ResultQuery run(Query query);
``` 

Parameters
|name|type|description|
|:-:|:-:|:-:|
|query|Query|The query to execute|


## Class Query 
Represent of database query.<br>
Location
```bash
id.nodynamic.ndb.Query
```
#### Constructor 
Create a instance of Query 

```java
Query(String query);
```
Parameters
|name|type|description|
|:-:|:-:|:-:|
|query|String|Query from user|


#### Method of()
Create a instance of Query.<br>
Return Query
```java
Query of(String query)
```

Parameters
|name|type|description|
|:-:|:-:|:-:|
|query|String|Query from user|


Example
```java
Query query = Query.of("read person 10");
```

## Class ResultQuery
Represent the result of operation.<br>
Location
```bash
id.nodynamic.ndb.ResultQuery
```
#### Constructor
create instance of ResultQuery
```java
ResultQuery();
```

#### Method getCreateResult()
Get the result of the create operation.<br>
Return boolean
```java
boolean getCreateResult();
```
Example
```java
ResultQuery result = ndb.run("create person name age");
boolean status = result.getCreateResult();
```
#### Method getReadValue()
Get the value in database based on column name.<br>
Return String
```java
String getReadValue(String key);
```
Parameters
|name|type|description|
|:-:|:-:|:-:|
|key|String|column name|


Example
```java
ResultQuery result = ndb.run("read person 1");
String age = result.getReadValue("age");
```

#### Method getReadResult()
Get the record in database.<br>
Return Record
```java
Record getReadResult();
```
Example
```java
ResultQuery result = ndb.run("read person 11");
Record record = result.getReadResult();
```

#### Method getUpdateResult()
Get the result of the update operation.<br>
Return boolean
```java
boolean getUpdateResult();
```
Example
```java
ResultQuery result = ndb.run("update person 10 name=person1 age=20");
boolean status = result.getUpdateResult();
```

#### Method getDeleteResult()
Get the result of the delete operation.<br>
Return boolean
```java
getDeleteResult();
```
Example
```java
ResultQuery result = ndb.run("delete person");
boolean status = result.getDeleteResult();
```

#### Method getFindResult()
Get the result of the find operation.<br>
Return int 
```java
int getFindResult();
```
Example
```java
ResultQuery result = ndb.run("find person name=person1 age=20");
int index = result.getFindResult();
```

#### Method getInsertResult()
Get the result of the insert operation.<br>
Return boolean
```java
boolean getFindResult();
```
Example
```java
ResultQuery result = ndb.run("insert person name=person1 age=20");
boolean status = result.getInsertResult();
```

#### Method getSearchResult()
Get the result of the Search operation.<br>
Return int[]
```java
int[] getFindResult();
```
Example
```java
ResultQuery result = ndb.run("search person age=20");
int[] index = result.getSearchResult();
```

#### Method getRemoveResult()
Get the result of the remove operation.<br>
Return boolean
```java
bollean getRemoveResult();
```
Example
```java
ResultQuery result = ndb.run("remove person 10");
boolean status = result.getRemoveResult();
```

#### Method getSizeResult()
Get the result of the size operation.<br>
Return int
```java
int getSizeResult();
```
Example
```java
ResultQuery result = ndb.run("size person");
int size = result.getSizeResult();
```

#### Method getExistsResult()
Get the result of the exists operation.<br>
Return boolean
```java
boolean getExistsResult();
```
Example
```java
ResultQuery result = ndb.run("exists person");
boolean status = result.getExistsResult();
```

#### Method getPrintResult()
Get the result of the print operation.<br>
Return String
```java
String getPrintResult();
```
Example
```java
ResultQuery result = ndb.run("print person");
String print = result.getPrintResult();
```

## Class Record
Represents a row in the database.<br>
Location
```bash
id.nodynamic.ndb.model.Record
```
#### Constructor
Create instance of Record 
```java 
Record(String[] key, String[] value);
```
Parameters
|name|type|description|
|:-:|:-:|:-:|
|key|String[]|list column name|
|value|String[]|list column value|


Example
```java
String[] keys = {"name", "age"};
String[] values = {"person1", "20"};
Record record = new Record(keys, values);
```

#### Method get()
Get a value based on key.<br>
Return String 
```java
String get(String key);
```
Parameters
|name|type|description|
|:-:|:-:|:-:|
|key|String|column name|


Example
```java
String age = record.get("age");
```
#### Method getKeys()
Get all the keys.<br>
Return String[] 
```java
String[] getKeys();
```

Example
```java 
String[] keys = record.getKeys();
```

#### Method getKeys()
Get all the values.<br>
Return String[] 
```java
String[] getValues();
```

Example
```java 
String[] values = record.getValue();
```
