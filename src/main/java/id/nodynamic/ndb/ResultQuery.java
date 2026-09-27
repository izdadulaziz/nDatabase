package id.nodynamic.ndb;

import id.nodynamic.ndb.model.Record;


public class ResultQuery{

    private boolean createResult;
    private Record readResult;
    private boolean updateResult;
    private boolean deleteResult;
    private int findResult;
    private boolean insertResult;
    private int[] searchResult;
    private boolean removeResult;
    private int sizeResult;
    private boolean existsResult;
    private String printResult;

    public ResultQuery(){
        this.createResult = false;
        this.readResult = null;
        this.updateResult = false;
        this.deleteResult = false;
        this.findResult = -1;
        this.insertResult = false;
        this.searchResult = null;
        this.removeResult = false;
        this.sizeResult = -1;
        this.existsResult = false;
        this.printResult = null;
    }

    public ResultQuery setCreateResult(boolean result){
        this.createResult = result;
        return this;
    }
    
    public ResultQuery setReadResult(Record record){
        this.readResult = record;
        return this;
    }

    public ResultQuery setUpdateResult(boolean result){
        this.updateResult = result;
        return this;
    }

    public ResultQuery setDeleteResult(boolean result){
        this.deleteResult = result;
        return this;
    }

    public ResultQuery setFindResult(int find){
        this.findResult = find;
        return this;
    }

    public ResultQuery setInsertResult(boolean result){
        this.insertResult = result;
        return this;
    }

    public ResultQuery setSearchResult(int[] search){
        this.searchResult = search;
        return this;
    }

    public ResultQuery setRemoveResult(boolean result){
        this.removeResult = result;
        return this;
    }

    public ResultQuery setSizeResult(int size){
        this.sizeResult = size;
        return this;
    }

    public ResultQuery setExistsResult(boolean result){
        this.existsResult = result;
        return this;
    }

    public ResultQuery setPrintResult(String print){
        this.printResult = print;
        return this;
    }
    

    public boolean getCreateResult(){
        return this.createResult;
     }

    public String getReadValue(String key){
        String value = this.readResult.get(key);
        return value;
    }

    public Record getReadResult(){
        return this.readResult;
    }

    public boolean getUpdateResult(){
        return this.updateResult;
    }

    public boolean getDeleteResult(){
        return this.deleteResult;
    }

    public int getFindResult(){
        return this.findResult;
    }

    public boolean getInsertResult(){
        return this.insertResult;
    }

    public int[] getSearchResult(){
        return this.searchResult;
    }

    public boolean getRemoveResult(){
        return this.removeResult;
    }

    public int getSizeResult(){
        return this.sizeResult;
    }

    public boolean getExistsResult(){
        return this.existsResult;
    }

    public String getPrintResult(){
        return this.printResult;
    }

    
}