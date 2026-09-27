package id.nodynamic.ndb;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;


import id.nodynamic.ndb.nDatabase;
import id.nodynamic.ndb.Query;
import id.nodynamic.ndb.ResultQuery;
import id.nodynamic.ndb.model.Record;

public class DatabaseTest{

    nDatabase ndb = new nDatabase();

    @AfterEach
    public void clean(){
        Query q = Query.of("delete person");
        ResultQuery rq = ndb.run(q);
        assertTrue(rq.getDeleteResult());
    }

   @Test
    public void testCreate(){
        Query q = Query.of("create person name age");
        ResultQuery rq = ndb.run(q);
        assertTrue(rq.getCreateResult());
    }
    
    @Test
    public void testCreateFalse(){
        Query q = Query.of("create person");
        ResultQuery rq = ndb.run(q);
        assertFalse(rq.getCreateResult());
        
        Query q1 = Query.of("create person name age");
        ResultQuery rq1 = ndb.run(q1);
        assertTrue(rq1.getCreateResult());
    }

    @Test 
    public void testRead(){
        Query q1 = Query.of("create person name age");
        ResultQuery rq1 = ndb.run(q1);
        assertTrue(rq1.getCreateResult());

        Query q2 = Query.of("insert person name=test age=19");
        ResultQuery rq2 = ndb.run(q2);
        assertTrue(rq2.getInsertResult());

        Query q3 = Query.of("read person 0");
        ResultQuery rq3 = ndb.run(q3);
        assertNotNull(rq3);

        assertEquals("test", rq3.getReadValue("name"));
        assertEquals("19", rq3.getReadValue("age"));
        
    }
    
    @Test 
    public void testReadFalse(){
        Query q1 = Query.of("create person name age");
        ResultQuery rq1 = ndb.run(q1);
        assertTrue(rq1.getCreateResult());

        Query q2 = Query.of("insert person name=test age=19");
        ResultQuery rq2 = ndb.run(q2);
        assertTrue(rq2.getInsertResult());

        Query q3 = Query.of("read person 2");
        ResultQuery rq3 = ndb.run(q3);
        assertNull(rq3.getReadResult());
        
    }

    @Test
    public void testUpdate(){
        Query q1 = Query.of("create person name age");
        ResultQuery rq1 = ndb.run(q1);
        assertTrue(rq1.getCreateResult());

        Query q2 = Query.of("insert person name=test age=19");
        ResultQuery rq2 = ndb.run(q2);
        assertTrue(rq2.getInsertResult());

        Query q3 = Query.of("update person 0 name=test2 age=20");
        ResultQuery rq3 = ndb.run(q3);
        assertTrue(rq3.getUpdateResult());
    }
    
    @Test
    public void testUpdateFalse(){
        Query q1 = Query.of("create person name age");
        ResultQuery rq1 = ndb.run(q1);
        assertTrue(rq1.getCreateResult());

        Query q2 = Query.of("insert person name=test age=19");
        ResultQuery rq2 = ndb.run(q2);
        assertTrue(rq2.getInsertResult());

        Query q3 = Query.of("update person 1 name=test2 aga=20");
        ResultQuery rq3 = ndb.run(q3);
        assertFalse(rq3.getUpdateResult());
    }

    @Test
    public void testDelete(){
        Query q1 = Query.of("create person name age");
        ResultQuery rq1 = ndb.run(q1);
        assertTrue(rq1.getCreateResult());

        Query q2 = Query.of("delete person");
        ResultQuery rq2 = ndb.run(q2);
        assertTrue(rq2.getDeleteResult());
        
        Query q3 = Query.of("create person name age");
        ResultQuery rq3 = ndb.run(q3);
        assertTrue(rq3.getCreateResult());
    }
    
    @Test
    public void testDeleteFalse(){
        Query q1 = Query.of("create person name age");
        ResultQuery rq1 = ndb.run(q1);
        assertTrue(rq1.getCreateResult());

        Query q2 = Query.of("delete persoo");
        ResultQuery rq2 = ndb.run(q2);
        assertFalse(rq2.getDeleteResult());
    }

    @Test 
    public void testRemove(){
        Query q1 = Query.of("create person name age");
        ResultQuery rq1 = ndb.run(q1);
        assertTrue(rq1.getCreateResult());

        Query q2 = Query.of("insert person name=test age=19");
        ResultQuery rq2 = ndb.run(q2);
        assertTrue(rq2.getInsertResult());
        
        Query q3 = Query.of("insert person name=test age=20");
        ResultQuery rq3 = ndb.run(q3);
        assertTrue(rq3.getInsertResult());

        Query q4 = Query.of("remove person 0");
        ResultQuery rq4 = ndb.run(q4);
        assertTrue(rq4.getRemoveResult());
    }
    
    @Test 
    public void testRemoveFalse(){
        Query q1 = Query.of("create person name age");
        ResultQuery rq1 = ndb.run(q1);
        assertTrue(rq1.getCreateResult());

        Query q2 = Query.of("insert person name=test age=19");
        ResultQuery rq2 = ndb.run(q2);
        assertTrue(rq2.getInsertResult());
        
        Query q3 = Query.of("insert person name=test age=20");
        ResultQuery rq3 = ndb.run(q3);
        assertTrue(rq3.getInsertResult());

        Query q4 = Query.of("remove person 20");
        ResultQuery rq4 = ndb.run(q4);
        assertFalse(rq4.getRemoveResult());
    }

    @Test
    public void testInsert(){
        Query q1 = Query.of("create person name age");
        ResultQuery rq1 = ndb.run(q1);
        assertTrue(rq1.getCreateResult());

        Query q2 = Query.of("insert person name=test age=19");
        ResultQuery rq2 = ndb.run(q2);
        assertTrue(rq2.getInsertResult());
    }
    
    @Test
    public void testInsertFalse(){
        Query q1 = Query.of("create person name age");
        ResultQuery rq1 = ndb.run(q1);
        assertTrue(rq1.getCreateResult());

        Query q2 = Query.of("insert person nsme=test age=19");
        ResultQuery rq2 = ndb.run(q2);
        assertFalse(rq2.getInsertResult());
    }

    @Test 
    public void testFind(){
        Query q1 = Query.of("create person name age");
        ResultQuery rq1 = ndb.run(q1);
        assertTrue(rq1.getCreateResult());

        Query q2 = Query.of("insert person name=test age=19");
        ResultQuery rq2 = ndb.run(q2);
        assertTrue(rq2.getInsertResult());
        
        Query q3 = Query.of("insert person name=find age=20");
        ResultQuery rq3 = ndb.run(q3);
        assertTrue(rq3.getInsertResult());
        
        Query q4 = Query.of("insert person name=mane age=89");
        ResultQuery rq4 = ndb.run(q4);
        assertTrue(rq4.getInsertResult());

        Query q5 = Query.of("find person name=find age=20");
        ResultQuery rq5 = ndb.run(q5);
        assertEquals(1, rq5.getFindResult());
    }
    
    @Test 
    public void testFindFalse(){
        Query q1 = Query.of("create person name age");
        ResultQuery rq1 = ndb.run(q1);
        assertTrue(rq1.getCreateResult());

        Query q2 = Query.of("insert person name=test age=19");
        ResultQuery rq2 = ndb.run(q2);
        assertTrue(rq2.getInsertResult());
        
        Query q3 = Query.of("insert person name=find age=20");
        ResultQuery rq3 = ndb.run(q3);
        assertTrue(rq3.getInsertResult());
        
        Query q4 = Query.of("insert person name=mane age=89");
        ResultQuery rq4 = ndb.run(q4);
        assertTrue(rq4.getInsertResult());

        Query q5 = Query.of("find person name=fi age=20");
        ResultQuery rq5 = ndb.run(q5);
        assertEquals(-1, rq5.getFindResult());
    }

    @Test
    public void testSearch(){
        Query q1 = Query.of("create person name age");
        ResultQuery rq1 = ndb.run(q1);
        assertTrue(rq1.getCreateResult());

        int[] ages = {19, 25, 25, 20, 40, 90};

        for(int i = 0; i < ages.length; i++){
            Query q = Query.of("insert person name=test" + i + " age=" + ages[i]);
            ResultQuery rq = ndb.run(q);
            assertTrue(rq.getInsertResult());
        }

        Query q2 = Query.of("search person age=25");
        ResultQuery rq2 = ndb.run(q2);
        int[] result = rq2.getSearchResult();
        int[] expect = {1,2};

        for(int i = 0; i < result.length; i++){
            assertEquals(expect[i], result[i]);
        }
    }

    @Test 
    public void testSize(){
        Query q1 = Query.of("create person name age");
        ResultQuery rq1 = ndb.run(q1);
        assertTrue(rq1.getCreateResult());

        for(int i = 0; i < 20; i++){
            Query q = Query.of("insert person name=test age=19");
            ResultQuery rq = ndb.run(q);
            assertTrue(rq.getInsertResult());
        }

        Query q2 = Query.of("size person");
        ResultQuery rq2 = ndb.run(q2);
        assertEquals(20, rq2.getSizeResult());
    }

    @Test 
    public void testExists(){
        Query q1 = Query.of("create person name age");
        ResultQuery rq1 = ndb.run(q1);
        assertTrue(rq1.getCreateResult());

        Query q2 = Query.of("exists person");
        ResultQuery rq2 = ndb.run(q2);
        assertTrue(rq2.getExistsResult());
    }

    @Test 
    public void testPrint(){
        Query q1 = Query.of("create person name age");
        ResultQuery rq1 = ndb.run(q1);
        assertTrue(rq1.getCreateResult());

        Query q2 = Query.of("insert person name=test age=19");
        ResultQuery rq2 = ndb.run(q2);
        assertTrue(rq2.getInsertResult());

        Query q3 = Query.of("print person");
        ResultQuery rq3 = ndb.run(q3);
        assertNotNull(rq3.getPrintResult());
    }
    
    
}