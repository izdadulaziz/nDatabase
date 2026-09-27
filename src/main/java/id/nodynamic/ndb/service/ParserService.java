package id.nodynamic.ndb.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.ArrayList;

import id.nodynamic.ndb.Query;
import id.nodynamic.ndb.model.Commands;
import id.nodynamic.ndb.model.Record;
import id.nodynamic.ndb.model.Manifest;
import id.nodynamic.ndb.service.DatabaseService;

public class ParserService{

    private DatabaseService service = new DatabaseService();

    /*
    parse a query 
    @param Query <= query
    @return String <= command 
    */
    public String parse(Query query){
        if(query == null){
            return Commands.INV;
        }

        if(query.length() == 0){
            return Commands.INV;
        }

        String command = query.getQueries()[0];

        switch(command){
            case Commands.CREATE:
                return Commands.CREATE;
            case Commands.READ:
                return Commands.READ;
            case Commands.UPDATE:
                return Commands.UPDATE;
            case Commands.DELETE:
                return Commands.DELETE;
            case Commands.FIND:
                return Commands.FIND;
            case Commands.INSERT:
                return Commands.INSERT;
            case Commands.REMOVE:
                return Commands.REMOVE;
            case Commands.SEARCH:
                return Commands.SEARCH;
            case Commands.SIZE:
                return Commands.SIZE;
            case Commands.EXISTS:
                return Commands.EXISTS;
            case Commands.PRINT:
                return Commands.PRINT;
            default:
                return Commands.INV;
                
        }
    }

    /*
    parse a query to a map 
    @param int <= skip 
    @param Query <= query 
    @return HashMap<String, String> 
    */
    public HashMap<String, String> parseToMap(int skip, Query query){

        HashMap<String, String> out = new HashMap<>();

        for(int i = skip; i < query.length(); i++){
            String[] arr = query.getQueries()[i].split("=");
            if(arr.length != 2) return null;
            out.put(arr[0], arr[1]);

        }

        
        return out;
        
    }

    /*
    parse a map to Record 
    @param String <= dbname
    @param HashMap<String, String> <= map 
    @return Record*/
    public Record parseToRecord(String dbname, HashMap<String, String> map){

        Manifest manifest = service.getManifest(dbname);   
        if(manifest == null) return null;

        if(map == null){     
            return null;
        }

        String[] manifestKeys = manifest.getKeys();
        ArrayList<String> mapKeys = new ArrayList<>(map.keySet());

        if(manifestKeys.length != mapKeys.size()) return null;

        String[] keys = new String[manifestKeys.length];
        String[] values = new String[manifestKeys.length];

        for(int i = 0; i < mapKeys.size(); i++){

        
            if(!(mapKeys.contains(manifestKeys[i]))) return null;
        
            keys[i]  = manifestKeys[i];
            values[i] = map.get(manifestKeys[i]);
            
        }

        return new Record(keys, values);
    }

    /*
    parse a query to array String 
    @param int <= skip 
    @param Query <= query 
    @return String[] 
    */
    public String[] parseToArr(int skip, Query query){

        String[] queries = query.getQueries();
        String[] out = new String[query.length() - skip];

        for(int i = 0; i < query.length(); i++){
            if((i+skip) == query.length()) break;
            out[i] = queries[i + skip];
        }

        return out;
    }
}
