package id.nodynamic.ndb.service;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;

import id.nodynamic.ndb.model.Manifest;
import id.nodynamic.ndb.model.Tabel;
import id.nodynamic.ndb.model.Record;
import id.nodynamic.ndb.Utils;
import id.nodynamic.ndb.AppInfo;
import id.nodynamic.ndb.Config;

public class DatabaseService{

    /*
    deserialize (get) tabel of db
    @param String <= dbname 
    @return Tabel <= Tabel of db*/
    private Tabel getTabel(String dbname){
        return Utils.deserializeTabel(pathOfTabel(dbname));
    }

    /*
    deserialize (get) manifest of db
    @param String <= dbname
    @return <= Manifest of db
    */
    public Manifest getManifest(String dbname){
        return Utils.deserializeManifest(pathOfManifest(dbname));
    }

    /*
    serialize (set) tabel of db
    @param Tabel <= tabel db 
    @param String <= dbname 
    @return boolean 
    */
    private boolean setTabel(Tabel tabel, String dbname){
        return Utils.serializeTabel(tabel, pathOfTabel(dbname));
    }
    
    /*
    serialize (set) manifest of db
    @param Manifest <= manifest db 
    @param String <= dbname 
    @return boolean 
    */
    private boolean setManifest(Manifest manifest, String dbname){
        return Utils.serializeManifest(manifest, pathOfManifest(dbname));
    }

    /*
    return a path of db 
    @param String <= dbname 
    @return String <= path of db
    */
    private String pathOfDB(String dbname){
        return Config.DEFAULT_DIR + "/" + dbname;
    }

    /*
    return file path tabel.dat of db 
    @param String <= dbname
    @return String <= path tabel.dat
    */
    private String pathOfTabel(String dbname){
        StringBuilder sb = new StringBuilder();
        sb.append(Config.DEFAULT_DIR);
        sb.append("/");
        sb.append(dbname);
        sb.append("/");
        sb.append(Config.DEFAULT_TABEL_NAME);

        return sb.toString();
    }

    /*
    return file path manifest of db 
    @param String <= dbname
    @return String <= path manifest
    */
    private String pathOfManifest(String dbname){
        StringBuilder sb = new StringBuilder();
        sb.append(Config.DEFAULT_DIR);
        sb.append("/");
        sb.append(dbname);
        sb.append("/");
        sb.append(Config.DEFAULT_MANIFEST_NAME);
        
        return sb.toString();
    }

    /*
    create a db 
    @param String <= dbname 
    @param String[] <= keys of db 
    @return boolean
    */
    public boolean create(String dbname, String[] keys){

        boolean exists = Utils.checkDir(Config.DEFAULT_DIR );
        if(!exists){
            boolean root = Utils.createDir(Config.DEFAULT_DIR);
            if(!root){
                return false;
            }
        }


        boolean dir =  Utils.createDir(pathOfDB(dbname));

        if(!dir) return false;

        Manifest manifest = new Manifest(AppInfo.VERSION, AppInfo.BUILDNUMBER, Utils.getTimestamp(), dbname, 0, keys);
        Tabel tabel = new Tabel(dbname, new ArrayList<Record>());
        return setManifest(manifest, dbname) && setTabel(tabel, dbname);
        
        
    }

    /*
    read db at spesific index 
    @param String <= dbname 
    @param int <= index 
    @return Record 
    */
    public Record read(String dbname, int index){
        if(index < 0){
            return null;
        }

        Tabel tabel = getTabel(dbname);
        if(tabel == null){
            return null;
        }

        if(index >= tabel.size()){
            return null;
        }

        return tabel.get(index);
        
    }

    /*
    update db at spesific index with new Record
    @param String <= dbname 
    @param int <= index 
    @param Record <= new Record
    @return boolean
    */
    public boolean update(String dbname, int index, Record record){

        if(index < 0){
            return false;
        }

        if(record == null){
            return false;
        }

        Manifest manifest = getManifest(dbname);
        Tabel tabel = getTabel(dbname);
        if(tabel == null || manifest == null){
            return false;
        }
        
        if(index >= tabel.size()){
            return false;
        }
        
        String[] recordKeys = record.getKeys();
        String[] manifestKeys = manifest.getKeys();

        if(recordKeys.length != manifestKeys.length) return false;

        for(int i = 0; i < recordKeys.length; i++){
            if(!(recordKeys[i].equals(manifest.getKeys()[i]))) return false;
        }

        tabel.set(index, record);

        return setTabel(tabel, dbname);
    }

    /*
    delete a db 
    @param String <= dbname
    @return boolean 
    */
    public boolean delete(String dbname){
        boolean delManifest = Utils.deleteFile(pathOfManifest(dbname));
        boolean delTabel = Utils.deleteFile(pathOfTabel(dbname));
        boolean delDB = Utils.deleteDir(pathOfDB(dbname));

        return delDB && delManifest && delTabel;
        
    }

    /*
    remove record in db at spesific index 
    @param String <= dbname
    @param int <= index 
    @return boolean 
    */
    public boolean remove(String dbname, int index){
        if(index < 0){
            return false;
        }
        Manifest manifest = getManifest(dbname);
        Tabel tabel = getTabel(dbname);
        if(tabel == null && manifest == null ){
            return false;
        }

        if(index >= tabel.size()){
            return false;
        }

        tabel.remove(index);
        manifest.decSize();

        return setManifest(manifest, dbname) && setTabel(tabel, dbname);
        
    }

    /*
    insert new record to db 
    @param String <= dbname 
    @param Record <= record
    @return boolean 
    */
    public boolean insert(String dbname, Record record){
        
        if(record == null){
            return false;
        }
        Manifest manifest = getManifest(dbname);
        Tabel tabel = getTabel(dbname);
        if(tabel == null || manifest == null){
            return false;
        }

        String[] recordKeys = record.getKeys();
        String[] manifestKeys = manifest.getKeys();

        if(!(recordKeys.length == manifestKeys.length)) return false;
        
        for(int i = 0; i < manifestKeys.length; i++){
            if(!(manifestKeys[i].equals(recordKeys[i]))) return false;
        }
        
        tabel.add(record);
        manifest.incSize();

        return setManifest(manifest, dbname) && setTabel(tabel, dbname);
        
    }

    /*
    find record in db 
    @param String <= dbname 
    @param Record <= record
    @return int <= index (frist find)
    */
    public int find(String dbname, Record record){
        if(record == null){
            return -1;
        }
        Tabel tabel = getTabel(dbname);
        if(tabel == null){
            return -1;
        }

        ArrayList<Record> arr = tabel.getTabel();

        for(int i = 0; i < arr.size(); i++){
            if(arr.get(i).equals(record)){
                return i;
            }
        }

        return -1;

        
    }

    /*
    search all record is same with key=value
    @param String <= dbname 
    @param HashMap<String, String> <= map of key=value
    @return int[] <= arr index
    */
    public int[] search(String dbname, HashMap<String, String> map){

        ArrayList<String> mapKeys = new ArrayList<>(map.keySet());
        Tabel tabel = getTabel(dbname);
        if(tabel == null){
            return null;
        }

        ArrayList<Record> recordArr = tabel.getTabel();
        ArrayList<Integer> result = new ArrayList<>();

        for(int i = 0; i < recordArr.size(); i++){
            for(int j = 0; j < mapKeys.size(); j++){
                String mapData = map.get(mapKeys.get(j));
                String arrData = recordArr.get(i).get(mapKeys.get(j));
                if(mapData.equals(arrData)) result.add(i);
            }
        }

        int[] out = new int[result.size()];

        for(int i = 0; i < result.size(); i++){
            out[i] = result.get(i);
        }

        return out;
        
    }

    /*
    return a size of db 
    @param String <= dbname
    @return int 
    */
    public int size(String dbname){
        Manifest manifest = getManifest(dbname);
        if(manifest == null) return -1;

        return manifest.getSize();
    }

    /*
    check db is exists 
    @param String <= dbname
    @return boolean 
    */
    public boolean exists(String dbname){
        String path = Config.DEFAULT_DIR + "/" + dbname;
        return Utils.checkDir(path);
    }

    /*
    print all record in db
    @param String <= dbname
    @return boolean 
    */
    public String print(String dbname){
        if(dbname == null) return null;

        Manifest manifest = getManifest(dbname);
        Tabel tabel = getTabel(dbname);
        if(tabel == null || manifest == null) return null;

        return tabel.print(dbname, manifest.getKeys());
    }

    
}
