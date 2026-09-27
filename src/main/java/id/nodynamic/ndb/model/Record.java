package id.nodynamic.ndb.model;

import java.io.Serializable;
import java.util.Arrays;

public class Record implements Serializable{
    private static final long serialVersionUID = 3L;
    // list key
    private String[] keys;
    // list value
    private String[] values;

    // constructor
    public Record(String[] keys, String[] values){
        
        this.keys = keys;
        this.values = values;
    }

    /*
    get value 
    @param String key
    @return String <= value
    */
    public String get(String key){
        int idx = -1;
        for(int i = 0; i < keys.length; i++){
            if(keys[i].equals(key)){
                return values[i];
            }
        }

        return null;
    }

    /*
    set value
    @param String key
    @param String value
    @return void
    */
    public void set(String key, String value){
        int idx = -1;
        for(int i = 0; i < keys.length; i++){
            if(keys[i].equals(key)){
                idx = i;
                break;
            }
        }

        if(idx != -1){
            values[idx] = value;
        }
    }

    /*
    get all keys
    @return String[] <= keys
    */
    public String[] getKeys(){
        return this.keys;
    }

    /*
    get all vakues 
    @return String[] <= values
    */
    public String[] getValues(){
        return this.values;
    }

    /*
    check object is have key value or not
    @param String key 
    @param String value
    @return boolean <= have 
    */
    public boolean have(String key, String value){
        String target = key + "=" + value;

        for(int i = 0; i < keys.length; i++){
            String tmp = keys[i] + "=" + values[i];
            if(tmp.equals(target)){
                return true;
            }
        }

        return false;
    }
    /*
    print object with format key=value
    @return String <= print of this
    */
    public String print(){
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < keys.length; i++){
            sb.append(keys[i] + "=" + values[i] + " ");
        }

        return sb.toString();
    }

    /*
    Override equals
    @return boolean <= equals
    */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Record)) return false;

        Record record = (Record) o;

        String[] recordKeys = record.getKeys();
        String[] recordValues = record.getValues();

        if (keys.length != recordKeys.length ||
            values.length != recordValues.length) {
            return false;
        }

        for (int i = 0; i < keys.length; i++) {
            if (!keys[i].equals(recordKeys[i]) || !values[i].equals(recordValues[i])) {
                return false;
            }
        }

        return true;
    }

    /*
    Override hashCode()
    @return int
    */
    @Override
        public int hashCode() {
        int result = Arrays.hashCode(keys);
        result = 31 * result + Arrays.hashCode(values);
        return result;
    }

    /*
    Override toString()
    @return String 
    */
    @Override
    public String toString(){
        return "Record{" + this.print() + " " +  keys.length + ", " + values.length + print() + "}";
    }
    
}
