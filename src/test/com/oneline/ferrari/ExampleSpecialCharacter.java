package com.oneline.ferrari;

import junit.framework.TestCase;
import junit.framework.TestFerrari;

public class ExampleSpecialCharacter extends TestCase {

	public static void main(String[] args) throws Exception {
        TestFerrari.testSpecial(new ExampleSpecialCharacter());
	}

	public void testA(String name) throws Exception {
		System.out.println(name);
	}
}