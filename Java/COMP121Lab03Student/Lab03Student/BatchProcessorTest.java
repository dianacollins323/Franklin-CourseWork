import java.io.*;

/**
 * The test class BatchProcessorTest.
 *
 * @author  Diana Collins
 * @version 1.0
 */
public class BatchProcessorTest extends junit.framework.TestCase
{
    /**
     * Default constructor for test class BatchProcessorTest.
     */
    public BatchProcessorTest()
    {
        // Default constructor
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    protected void setUp()
    {
        // Set up
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    protected void tearDown()
    {
        // Tear down
    }
    
    /**
     * Test process reader
     */
    public void testProcess()
    {
        BatchProcessor processor = new BatchProcessor(new ABCRentals());
        
        //test add command
        String testerCommand1 = "add DVD desc=dvd player,rented=t\n" +
            "ADD other desc=another dvd player,rented=f,wkRate=12.29\n" +
            "add TV desc=tv,rented=t,type=plasma,size=37,monRate=20.35\n" +
            "add Furniture desc=table,rented=f,wkRate=12.22,monRate=20.23\n" +
            "remove 3\n" + "rent 2 3\n" + "restock 2\n" + "add Furniture\n" +
            "update 2 desc=dvd player 2\n" + "aDD DVD\n" + "add TV\n";
        assertEquals(11, processor.process(new StringReader(testerCommand1)));
        
        
    }
    
    /**
     * test process string with GoodCommands.txt
     */
    public void testStringProcess()
    {
        BatchProcessor processor = new BatchProcessor(new ABCRentals());
        assertEquals(8, processor.process("GoodCommands.txt"));
    }
}
