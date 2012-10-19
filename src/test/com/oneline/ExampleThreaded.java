import junit.framework.TestCase;
import junit.framework.TestFerrari;

public class ExampleThreaded extends TestCase {
	public static void main(String[] args) throws Exception {
        TestFerrari.testParallel(new ExampleThreaded(),10);
	}
	public void testA(String arg1, String arg2, String arg3) throws Exception {
		assertEquals("bizosys", "bizosys");
	}
}
