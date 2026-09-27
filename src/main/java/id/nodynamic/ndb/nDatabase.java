package id.nodynamic.ndb;

import java.util.HashMap;

import id.nodynamic.ndb.Query;
import id.nodynamic.ndb.AppInfo;
import id.nodynamic.ndb.ResultQuery;
import id.nodynamic.ndb.model.Commands;
import id.nodynamic.ndb.model.Record;
import id.nodynamic.ndb.service.DatabaseService;
import id.nodynamic.ndb.service.ParserService;

public class nDatabase{


    private ParserService parser;
    private DatabaseService db;

    public nDatabase(){
        this.parser = new ParserService();
        this.db = new DatabaseService();
    }

    public String name(){
        return AppInfo.APP_NAME;
    }

    public String v(){
        return AppInfo.VERSION;
    }

    public String bn(){
        return AppInfo.BUILDNUMBER;
    }

    private ResultQuery createDatabase(String dbname, String[] keys){
        if(dbname == null || keys.length == 0) return new ResultQuery().setCreateResult(false);
        
        boolean result = db.create(dbname, keys);
        return result ? new ResultQuery().setCreateResult(true): new ResultQuery().setCreateResult(false);
    }

    private ResultQuery readDatabase(String dbname, int index){
        if(dbname == null || index < 0) return new ResultQuery().setReadResult(null);

        Record record = db.read(dbname, index);

        if(record == null) return new ResultQuery().setReadResult(null);

        return new ResultQuery().setReadResult(record);
    }

    private ResultQuery updateDatabase(String dbname, int index, Record record){
        if(dbname == null || index < 0 || record == null) return new ResultQuery().setUpdateResult(false);

        boolean result = db.update(dbname, index, record);

        return result ? new ResultQuery().setUpdateResult(true) : new ResultQuery().setUpdateResult(false);
    }

    private ResultQuery deleteDatabase(String dbname){
        if(dbname == null) return new ResultQuery().setDeleteResult(false);

        boolean result = db.delete(dbname);

        return result ? new ResultQuery().setDeleteResult(true): new ResultQuery().setDeleteResult(false);
    }

    private ResultQuery findRecord(String dbname, Record record){
        if(dbname == null || record == null) return new ResultQuery().setFindResult(-1);

        int result = db.find(dbname, record);

        return result != -1 ? new ResultQuery().setFindResult(result) : new ResultQuery().setFindResult(-1);
    }

    private ResultQuery insertRecord(String dbname, Record record){
        if(dbname == null || record == null) new ResultQuery().setInsertResult(false);
        
        boolean result = db.insert(dbname, record);

        return result ? new ResultQuery().setInsertResult(true) : new ResultQuery().setInsertResult(false);
    }

    private ResultQuery searchRecord(String dbname, HashMap<String, String> map){
        if(dbname == null || map == null) new ResultQuery().setSearchResult(null);

        int[] result = db.search(dbname, map);

        if(result == null) return new ResultQuery().setSearchResult(null);

        return new ResultQuery().setSearchResult(result);
    }

    private ResultQuery removeRecord(String dbname, int index){
        if(dbname == null || index < 0) return new ResultQuery().setRemoveResult(false);

        boolean result = db.remove(dbname, index);

        return result ? new ResultQuery().setRemoveResult(true) : new ResultQuery().setRemoveResult(false);
        
    }

    private ResultQuery sizeDatabase(String dbname){
        if(dbname == null) return new ResultQuery().setSizeResult(-1);

        int size = db.size(dbname);

        if(size == -1) return new ResultQuery().setSizeResult(-1);

        return new ResultQuery().setSizeResult(size);
    }

    private ResultQuery existsDatabase(String dbname){
        if(dbname == null) return new ResultQuery().setExistsResult(false);

        boolean result = db.exists(dbname);

        return result ? new ResultQuery().setExistsResult(true) : new ResultQuery().setExistsResult(false);
    }

    private ResultQuery printDatabase(String dbname){
        if(dbname == null) return new ResultQuery().setPrintResult(null);

        String data = db.print(dbname);

        if(data == null) return new ResultQuery().setPrintResult(null);

        return new ResultQuery().setPrintResult(data);
        
    }

    
    public ResultQuery run(Query query){

        String command = parser.parse(query);
        String[] queries = query.getQueries();
        
        if(queries.length <= 1) return null;
        String dbname = queries[1];

        switch(command){
            case Commands.CREATE:
                String[] keys = parser.parseToArr(2, query);
                return createDatabase(dbname, keys);

            case Commands.READ:
                int index = Integer.parseInt(query.getQueries()[2]);
                return readDatabase(dbname, index);

            case Commands.UPDATE:
                int index0 = Integer.parseInt(queries[2]);
                Record record = parser.parseToRecord(dbname, parser.parseToMap(3, query));
                return updateDatabase(dbname, index0, record);

            case Commands.DELETE:
                return deleteDatabase(dbname);

            case Commands.FIND:
                Record record0 = parser.parseToRecord(dbname, parser.parseToMap(2, query));
                return findRecord(dbname, record0);
                
            case Commands.INSERT:
                Record record1 = parser.parseToRecord(dbname, parser.parseToMap(2, query));
                return insertRecord(dbname, record1);

            case Commands.SEARCH:
                HashMap<String, String> map = parser.parseToMap(2, query);
                return searchRecord(dbname, map);

            case Commands.REMOVE:
                if(queries.length != 3) return null;
                int index1 = Integer.parseInt(queries[2]);
                return removeRecord(dbname, index1);

            case Commands.SIZE:
                return sizeDatabase(dbname);

            case Commands.EXISTS:
                return existsDatabase(dbname);

            case Commands.PRINT:
                return printDatabase(dbname);

            default:
                return null;
                
        }
    

            
    
        
    }
    
}
