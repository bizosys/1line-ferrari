/*
* Copyright 2010 Bizosys Technologies Limited
*
* Licensed to the Bizosys Technologies Limited (Bizosys) under one
* or more contributor license agreements.  See the NOTICE file
* distributed with this work for additional information
* regarding copyright ownership.  The Bizosys licenses this file
* to you under the Apache License, Version 2.0 (the
* "License"); you may not use this file except in compliance
* with the License.  You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.oneline.ferrari;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;
import junit.framework.TestFerrari;
import junit.framework.TestRandomValue;

public class TestAll extends TestCase {

	public static void run(TestCase[] testCases) throws Exception {
		Map<String, String> failed = new HashMap<String, String>();;
		
		/**  "random" , "special"  , "i18n"  , "response" , "maxmin" , "memory" ,  "null"  ,  "empty"  ,   "parallel"*/
		
		String[] testmodes = new String[] {
			"random" , "special", "response" , "maxmin" , "memory" ,  "null"  ,  "empty" ,   "parallel"};
		
		int totalRun = 0;
		int totalSucess = 0;
		int totalFailure = 0;

		for (String mode : testmodes) {
			for (TestCase case1 : testCases) {
				String testClazz = case1.getClass().getName() + "/";
				try {
					List<TestRandomValue> testResults = new ArrayList<TestRandomValue>();
					if ( "random".equals(mode) ) testResults.add(TestFerrari.testRandom(case1));
					else if ( "special".equals(mode) ) testResults.add(TestFerrari.testSpecial(case1));
					else if ( "i18n".equals(mode) ) testResults.add(TestFerrari.testI18N(case1));
					else if ( "response".equals(mode) ) testResults.add(TestFerrari.testResponse(case1));
					else if ( "maxmin".equals(mode) ) testResults.add(TestFerrari.testMaxMin(case1));
					else if ( "memory".equals(mode) ) testResults.add(TestFerrari.testMemory(case1));
					else if ( "null".equals(mode) ) testResults.add(TestFerrari.testNull(case1));
					else if ( "empty".equals(mode) ) testResults.add(TestFerrari.testEmpty(case1));
					
					else if ( "parallel".equals(mode) ) testResults.addAll(TestFerrari.testParallel(case1));
					else throw new Exception("Unknown mode :" + mode);
					
					for (TestRandomValue testResult : testResults) {
						totalRun = totalRun + testResult.getRuns();
						totalSucess = totalSucess + testResult.getSucesses();
						totalFailure = totalFailure + testResult.getFailures();
						failed.put(testClazz + mode, testResult.getFailedFunctions());
					}
					testResults.clear();
					
				} catch (Exception ex) {
					ex.printStackTrace(System.err);
				}
			}
		}
		
		System.out.println("############# Run Report ################");
		System.out.println("Total Run:" + totalRun);
		System.out.println("Total Failures:" + totalFailure);
		
		for (String testC : failed.keySet()) {
			if ( failed.get(testC).toString() == "None") continue;
			System.out.println("Failed : " + testC + " = " + failed.get(testC).toString());
		}
	}
	

}
