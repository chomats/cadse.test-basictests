/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
 * Copyright (C) 2006-2010 Adele Team/LIG/Grenoble University, France
 */
package fr.imag.adele.cadse.test.basictests.group.test2;

import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseView.workspaceView;
import static fr.imag.adele.graphictests.gtworkbench_part.GTView.welcomeView;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import fr.imag.adele.cadse.test.basictests.common.GTCommonParameters;
import fr.imag.adele.cadse.test.basictests.common.GTCommonTestDriver;
import fr.imag.adele.cadse.test.basictests.common.GTTestParameter;
import fr.imag.adele.graphictests.cadse.test.GTCadseTestCase;

@RunWith(value = Parameterized.class)
public class Test2_String_tc_execution extends GTCadseTestCase {

	/** The test instance number. (Test is parameterized) */
	protected int instanceNumber;

	/** Performs initializations */
	@BeforeClass
	public static void createContext() throws Exception {
		GTTestParameter param = getParameters().getTestParameters(0);
		getDriver().selectCadse(param);
		welcomeView.close();
		workspaceView.show();
	}

	/**
	 * The test itself.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testExecution() throws Exception {
		GTTestParameter param = getParameters().getTestParameters(instanceNumber);
		getDriver().testExecution(param);
	}

	/**
	 * Constructor.
	 * 
	 * @param i
	 *            the instance number.
	 */
	public Test2_String_tc_execution(int i) {
		instanceNumber = i;
	}

	/**
	 * Compute parameters for this test.
	 * 
	 * @return an array with all the parameters.
	 */
	@Parameters
	public static Collection<Object[]> data() {
		GTCommonParameters param = getParameters();
		ArrayList<Object[]> params = new ArrayList<Object[]>();
		for (int i = 0; i < param.numberOfTests(); i++) {
			params.add(new Object[] { i });
		}
		return params;
	}

	/**
	 * Gets the test driver.
	 * 
	 * @return the test driver
	 */
	protected static GTCommonTestDriver getDriver() {
		return new Test2_Common_testDriver();
	}

	/**
	 * Gets the test parameters.
	 * 
	 * @return the test parameters
	 */
	protected static GTCommonParameters getParameters() {
		return new Test2_String_parameters();
	}
}
