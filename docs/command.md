# Command 

## create 
Command to create a database. 
If the database already exists, it will not overwrite the old. 
```bash
create <name>
```
Parameters
 - name => database name

## read 
Command to read a record from database based on the index
```bash
read <name> <index>
```

Parameters
 - name => database name
 - index => index

## update 
Command to update a record from database based on the index. 
The arguments provided must match the columns in the database.
```bash
update <name> <column=value> ... 
```
Parameters
 - name => database name
 - column => column name
 - value => new value

## delete 
Command to delete a database. 
If the databass not exists, will nothing happend.
```bash
delete <name>
```
Parameters
 - name => database name

## find
Command to find a record from database. 
The arguments provided must match the columns in the database.
```bash
find <name> <column=value> ...
```

Parameters
 - name => database name
 - column => column name
 - value => column value

## insert 
Command to insert a new record to database. 
The arguments provided must match the columns in the database.
```bash
insert <name> <column=value> ...
```
Parameters
 - name => database name
 - column => column name
 - value => column value

## search 
Command to search for a record that matches the value.
```bash 
search <name> <column=value> ... 
```
Parameters
 - name => database name
 - column => column name
 - value => column value

## remove
Command to remove a record from database based on the index.
```bash
remove <name> <index>
```
Parameters
 - name => database name
 - index => index

## size
Command to get size of database.
```bash
size <name> 
``` 
Parameters
 - name => database name

## exists 
Command to check the database is exists or not.
```bash 
exists <name> 
```

Parameters
 - name => database name

## print 
Command to print the database.
```bash
print <name>
```
Parameters
 - name => database name

