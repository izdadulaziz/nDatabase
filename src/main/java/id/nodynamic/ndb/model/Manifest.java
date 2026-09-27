package id.nodynamic.ndb.model;

import java.io.Serializable;

public class Manifest implements Serializable{
    private static final long serialVersionUID = 1L;
    
    // version 
    private String version;
    // buildnumber
    private String buildnumber;
    // timestamp
    private String timestamp;
    // name tabel
    private String name;
    // size tabel
    private int size;
    // list of keys tabel
    private String[] keys;

    // constructor
    public Manifest(String version, String buildnumber, String timestamp, String name, int size, String[] keys){
        this.version = version;
        this.buildnumber = buildnumber;
        this.timestamp = timestamp;
        this.name = name;
        this.size = size;
        this.keys = keys;
    }

    /*
    get tabel name 
    @return  String <= tabel name*/
    public String getName(){
        return this.name;
    }

    /*
    increment size 
    @return void 
    */
    public void incSize(){
        size++;
    }

    /*decrement size
    @return void 
    */
    public void decSize(){
        size--;
    }

    /*
    get size of tabel
    @return int <= size tabel
    */
    public int getSize(){
        return this.size;
    }

    /*
    get list keys tabel
    @return String[] <= list keys*/
    public String[] getKeys(){
        return this.keys;
    }

    /*
    Override toString()
    @return String
    */
    @Override
    public String toString(){
        return "Manifest{" + this.version + ", " + this.buildnumber + ", " + this.timestamp + ", " + this.name + ", " + this.size + ", " + this.keys.length + "}";
    }
}
