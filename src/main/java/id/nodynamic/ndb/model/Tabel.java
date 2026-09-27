package id.nodynamic.ndb.model;

import java.util.ArrayList;
import java.io.Serializable;

import id.nodynamic.ndb.model.Record;

public class Tabel implements Serializable{
    private static final long serialVersionUID = 2L;
    
    // name tabel 
    private String name;
    // list of Records
    private ArrayList<Record> db;

    // costructor
    public Tabel(String name, ArrayList<Record> db){
        this.name = name;
        this.db = db;
    }

    /*
    add record to list
    @param Record <- record to add
    @return void 
    */
    public void add(Record record){
        this.db.add(record);
    }

    /*
    get Record 
    @param int <- index 
    @return Record at index 
    */
    public Record get(int index){
        return this.db.get(index);
    }

    /*
    set Record at spesific index 
    @param int <= index 
    @param Record <= new Record
    @return void 
    */
    public void set(int index, Record newRecord){
        this.db.set(index, newRecord);
    }

    /*
    remove Record at spesific index 
    @param int <= index 
    @return void
    */
    public void remove(int index){
        this.db.remove(index);
    }

    /*
    get size of list
    @return int <= size of list 
    */
    public int size(){
        return this.db.size();
    }

    /*
    get all list
    @return ArrayList<Record> <= list*/
    public ArrayList<Record> getTabel(){
        return this.db;
    }

    /*
    print this
    @return String <= print of all list
    */
    public String print(String dbname, String[] keys){

        StringBuilder sb = new StringBuilder();
        
        sb.append(dbname + "\n");
        sb.append("index | ");
        for(int i = 0; i < keys.length; i++){
            sb.append(keys[i] + " | ");
        }

        sb.append("\n");

        for(int i = 0; i < this.db.size(); i++){
            String[] dataValues = get(i).getValues();
            
            sb.append(i + "  ");
            
            for(int j = 0; j < dataValues.length; j++){
                sb.append(dataValues[j] + ", ");
            }
            sb.append("\n");
        }

        return sb.toString();
        
    }

    /*
    Override toString()
    @return String 
    */
    @Override
    public String toString(){
        return "Tabel{" + this.name + ", " + this.db.size() + "}";
    }

        /*
    tabel <dbname>
    index dbname-key dbname-key 
          1
    */
}
