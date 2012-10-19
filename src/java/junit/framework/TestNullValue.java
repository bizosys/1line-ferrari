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

package junit.framework;

import java.lang.reflect.Method;
import java.util.Date;

public class TestNullValue extends TestRandomValue{

	@Override
	protected void runMethod(Method runMethod, Test testCase) {
		if ( ! isRunMethod(runMethod) ) return;
		String methodName = runMethod.getName();

		if ( verbose ) System.out.println("\n... Checking Null Value test method >>" +  methodName + "<<" );

		
		Class[] params = runMethod.getParameterTypes();
		Object[] values = new Object[params.length]; 
		
		int counter = 0;
		boolean isString = false;
		for (Class param : params) {
			
			if ( String.class == param) {
				values[counter] = null;
				isString = true;
			} else if (Double.class == param) { 
				values[counter] = 0;
			} else if (Long.class == param) { 
				values[counter] = 0;
			} else if (Integer.class == param) { 
				values[counter] = 0;
			} else if (Float.class == param) { 
				values[counter] = 0.0;
			} else if (Short.class == param) { 
				values[counter] = 0;
			} else if (Boolean.class == param) { 
				values[counter] = false;
			} else if (Date.class == param) { 
				values[counter] = null;
			} 

			counter++;
		}
		if( !isString) return;
		
		try {
			totalRun++;
			invoke(runMethod, testCase, values);
			
		} catch (Exception ex) {
			StringBuilder sb = new StringBuilder();
			sb.append(testCase.getClass().getName() + ":" + runMethod.getName()); 
			if ( null == values) sb.append(" [Params : N/A]");
			else {
				for (Object obj : values ) {
					if ( null == obj) sb.append(" [Param : Null] " );
					else sb.append(" [Param : " + obj.toString() + "]");
				}
			}
			
			System.out.println("########## Failed :" + sb.toString());
			ex.printStackTrace(System.err);
			failedFunctions.add(runMethod.getName());
		}
	}
	
    public static void main(String[] args) throws Exception {
        Test testCase = new DryRunTest();
        TestNullValue tester = new TestNullValue(); 
        tester.run(testCase);
        System.out.println(tester.toString());
    }
	
}
