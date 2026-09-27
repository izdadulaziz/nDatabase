package id.nodynamic.ndb;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalTime;


import id.nodynamic.ndb.model.Record;
import id.nodynamic.ndb.model.Tabel;
import id.nodynamic.ndb.model.Manifest;

public class Utils{

    public static boolean checkFile(String path){
        return Files.isRegularFile(Path.of(path));
    }

    public static boolean checkDir(String path){
        return Files.isDirectory(Path.of(path));
    }

    public static boolean createDir(String path){
        try{
            Files.createDirectory(Path.of(path));
            return true;
        }catch(Exception e){
            return false;
        }
    }

    public static boolean deleteDir(String path) {
        try {
            Files.delete(Path.of(path));
            return true;
        } catch(Exception e) {
            return false;
        }
    }

    public static boolean deleteFile(String path){
        return Utils.deleteDir(path);
    }
    
    public static String getDate(){
        return LocalDate.now().toString();
    }

    public static String getTime(){
        return LocalTime.now().toString();
    }

    public static String getTimestamp(){
        return Utils.getDate() + " " + Utils.getTime();
    }

    public static boolean serializeManifest(Manifest manifest, String path){
        try{

            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));

            oos.writeObject(manifest);
            oos.close();
    
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    
    public static boolean serializeRecord (Record r, String path){
        try{

            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));

            oos.writeObject(r);
            oos.close();
    
            return true;
        }catch(Exception e){
            return false;
        }
    }
    
    public static boolean serializeTabel(Tabel tabel, String path){
        try{

            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));

            oos.writeObject(tabel);
            oos.close();
    
            return true;
        }catch(Exception e){
            return false;
        }
    }

    public static Manifest deserializeManifest(String path){
        try{

            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));

            Manifest manifest = (Manifest) ois.readObject();
            ois.close();
            return manifest;
        }catch(Exception e){
            return null;
        }
    }
    
    public static Record deserializeRecord (String path){
        try{

            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));

            Record r  = (Record) ois.readObject();
            ois.close();
            return r;
        }catch(Exception e){
            return null;
        }
    }
    
    public static Tabel deserializeTabel(String path){
        try{

            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));

            Tabel tabel = (Tabel) ois.readObject();
            ois.close();
            return tabel;
        }catch(Exception e){
            return null;
        }
    }
    
}
