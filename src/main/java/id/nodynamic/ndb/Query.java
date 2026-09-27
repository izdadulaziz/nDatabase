package id.nodynamic.ndb;

public class Query{

    private String[] queries;
    private int length;

    public Query(String query){
        this.queries = query.split(" ");
        this.length = this.queries.length;
    }

    public static Query of(String query){
        return new Query(query);
    }

    public String[] getQueries(){
        return this.queries;
    }

    public int length(){
        return this.length;
    }
    
}
