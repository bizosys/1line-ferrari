package com.oneline.ferrari;
import java.util.Random;

import junit.framework.TestCase;
import junit.framework.TestFerrari;

public class ExampleThreadSafety extends TestCase {
	public static void main(String[] args) throws Exception {
        TestFerrari.testParallel(new ExampleThreadSafety(),100);
	}

	Random random = null; 
	
	@Override
	protected void setUp() throws Exception {
		random = new Random();
	}	
	public void testA() throws Exception {
		int x = random.nextInt();
		SampleJavaClass.singleton.setAge(x);
		assertEquals(x, SampleJavaClass.singleton.getAge());
		
	}
	
	public static class SampleJavaClass {
		public static SampleJavaClass singleton = new SampleJavaClass();
		
		int age=0;
		
		private SampleJavaClass() {
			
		}

		public void setAge(int age) {
			this.age = age;
		}

		
		public int getAge() {
			return this.age;
		}
		
	}
}
