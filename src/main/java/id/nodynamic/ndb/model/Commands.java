package id.nodynamic.ndb.model;

public class Commands{
    
    public static final String CREATE = "create"; // create product name price expired
    public static final String READ = "read"; // read product 1
    public static final String UPDATE = "update"; // update product 1 price=new_value 
    public static final String DELETE = "delete"; // delete product
    public static final String FIND = "find"; // find product name=value price=value expired=value
    public static final String INSERT = "insert"; // insert product name=value price=value expired=value
    public static final String REMOVE = "remove"; // remove product 4
    public static final String SEARCH = "search"; // search product price=value
    public static final String SIZE = "size"; // size product 
    public static final String EXISTS = "exists"; // exists product
    public static final String PRINT = "print"; // print product 
    public static final String INV = "inv"; // invalid command


    
}
