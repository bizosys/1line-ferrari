import com.oneline.ferrari.TestAll;

import junit.framework.TestCase;
import junit.framework.TestFerrari;


public class ExampleTest extends TestCase {
	public static String[] modes = new String[] { "all", "random", "method"};
	public static String mode = modes[0];  
	
	public static void main(String[] args) throws Exception {
		ExampleTest t = new ExampleTest();
		
		if ( modes[0].equals(mode) ) {
			TestAll.run(new TestCase[]{t});
		} else if  ( modes[1].equals(mode) ) {
	        TestFerrari.testRandom(t);
	        
		} else if  ( modes[2].equals(mode) ) {
			t.setUp();
			t.testA(null, null, null);
			t.tearDown();
		}
	}

	@Override
	protected void setUp() throws Exception {
	}
	
	@Override
	protected void tearDown() throws Exception {
	}
	
	public void testA(String arg1, String arg2, String arg3) throws Exception {
		assertEquals("bizosys", "bizosys");
	}
}
