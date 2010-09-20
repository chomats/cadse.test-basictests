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
package fr.imag.adele.cadse.test.basictests.common;

import fr.imag.adele.cadse.test.basictests.common.type.GTTypeParameter;

public abstract class GTCommonParameters {

	/** The GTCollectionTestParameter, containing all the tests */
	protected GTCollectionTestParameter ctp;

	/** The type parameter */
	protected GTTypeParameter typeParameter;

	/** Attribute prefix used to compute the attributes names. */
	public final String attrPrefix = "attr";

	/**
	 * Performs ctp initialization.
	 * 
	 * @return the ctp
	 */
	protected abstract GTCollectionTestParameter initCTP();

	/**
	 * The test name. Used to compute CADSE definition name
	 * 
	 * @return the test name
	 */
	protected abstract String getTestName();

	/**
	 * The number of tests used to test the attribute.
	 * 
	 * @return the number of tests
	 */
	public int numberOfTests() {
		return ctp.numberOfTests();
	}

	/**
	 * gets the GTTestParameter from the instance number.
	 * 
	 * @param instanceNumber
	 *            the instance number
	 * @return the test parameters
	 */
	public GTTestParameter getTestParameters(int instanceNumber) {
		return ctp.getTestParameters(instanceNumber);
	}

	/**
	 * Gets the attribute name.
	 * 
	 * @return the attribute name
	 */
	protected String getAttributeName() {
		return attrPrefix + typeParameter.getAttributeName();
	}
}
