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

import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.createBasicAttribute;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.createCadseDefinition;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.selectCadses;

import java.util.ArrayList;

import fr.imag.adele.cadse.cadseg.managers.CadseDefinitionManager;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseWorkbenchPart;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue;
import fr.imag.adele.graphictests.cadse.test.GTCadseTestCase;
import fr.imag.adele.graphictests.gttree.GTTreePath;

/**
 * The Class GTCommonTestDriver.
 */
public abstract class GTCommonTestDriver extends GTCadseTestCase {

	// ====== //
	// PREFIX //
	// ====== //

	/** CADSE prefix, used to compute CADSE name. */
	public final String CADSEPrefix = "CADSE";

	/** Item type prefix used to compute the item types names. */
	public final String itPrefix = "it";

	/** Instance prefix used to compute the names. */
	public final String instancePrefix = "instance";

	// ================ //
	// CADSE DEFINITION //
	// ================ //

	/**
	 * Gets the name of the CADSE to be created.
	 * 
	 * @param tp
	 *            the test parameter
	 * 
	 * @return the CADSE name
	 */
	protected String getCadseName(GTTestParameter tp) {
		return CADSEPrefix + tp.getTestName() + tp.getAttributeNameUnderTest();
	}

	/**
	 * Gets the path to the CADSE in a tree view.
	 * 
	 * @param tp
	 *            the test parameter
	 * 
	 * @return the CADSE model
	 */
	protected GTTreePath getCadseModel(GTTestParameter tp) {
		return new GTTreePath(getCadseName(tp));
	}

	/**
	 * Gets a path to the data model.
	 * 
	 * @param tp
	 *            the test parameter
	 * 
	 * @return the data model
	 */
	protected GTTreePath getDataModel(GTTestParameter tp) {
		return getCadseModel(tp).concat(CadseDefinitionManager.DATA_MODEL);
	}

	/**
	 * Creates a specific the configuration.
	 * 
	 * @param tp
	 *            the test parameter
	 */
	public void testCreation(GTTestParameter tp) {

		/* Pre create */
		tp.preCreate(getDataModel(tp));

		/* Item type and link creation */
		GTTreePath typePath = createTypes(tp);

		/* Attributes creation */
		boolean success = createAttributes(tp, typePath);

		boolean expected = tp.isAttributeCreationSuccess();
		assertEquals("testCreation error with #" + tp.testNumber, expected, success);

		/* Post create */
		if (success) {
			GTTreePath attrPath = typePath.concat(tp.getAttributeName());
			tp.setAdvancedAttributeProperties(attrPath);
		}
	}

	/**
	 * Performs actions before the item creation.
	 * 
	 * @param tp
	 *            the test parameter
	 */
	protected void preCreate(GTTestParameter tp) {
	}

	/**
	 * Creates the types.
	 * 
	 * @param tp
	 *            the test parameter
	 * @return a path to the type from which attributes will be created
	 */
	abstract protected GTTreePath createTypes(GTTestParameter tp);

	/**
	 * Creates the attributes.
	 * 
	 * @param tp
	 *            the test parameter
	 * @param typePath
	 *            a path to the type on which attributes should be created
	 * @return true, if successful
	 */
	protected boolean createAttributes(GTTestParameter tp, GTTreePath typePath) {
		try {
			createBasicAttribute(typePath, tp.getAttributeTypeUnderTest(), getAttributeName(tp), tp
					.getCreationKeyValues());
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Creates the CADSE.
	 */
	public void createCadse(GTTestParameter tp) {
		createCadseDefinition(getCadseName(tp), "model." + getCadseName(tp));
	}

	/**
	 * Gets the attribute name.
	 * 
	 * @return the attribute name
	 */
	protected String getAttributeName(GTTestParameter tp) {
		return tp.getAttributeName();
	}

	/**
	 * At runtime, test the configuration tp.
	 * 
	 * @param tp
	 *            the test parameter
	 */
	public abstract void testExecution(GTTestParameter tp);

	/**
	 * At startup, selects the CADSE created before.
	 */
	public void selectCadse(GTTestParameter tp) {
		selectCadses("Cadse Model.Workspace." + getCadseName(tp));
	}

	/**
	 * Set values into a GTCadseWorkbenchPart.
	 * 
	 * @param wp
	 *            the GTCadseWorkbenchPart
	 * @param values
	 *            the values
	 * @return true, if sets the values
	 */
	protected boolean setValues(GTCadseWorkbenchPart wp, KeyValue... values) {
		try {
			wp.setValue(values);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * true if the creation page ok button is activated.
	 * 
	 * @param tp
	 * @return
	 */
	protected abstract boolean isOkButtonActivated(GTTestParameter tp);

	/**
	 * Assert values are String or String[] and are equals.
	 * 
	 * @param message
	 *            a failing message
	 * @param expected
	 *            the expected list
	 * @param actual
	 *            the actual list
	 */
	protected void assertEqualsListValues(String message, Object expected, Object actual) {

		ArrayList<Object> tab1 = generateArray(expected);
		ArrayList<Object> tab2 = generateArray(actual);

		if (expected == null && actual == null) {
			return;
		}

		if (tab1.size() != tab2.size()) {
			fail(message);
		}

		for (int i = 0; i < tab1.size(); i++) {
			Object val1 = tab1.get(i);
			Object val2 = tab2.get(i);

			if (val1 instanceof String || val2 instanceof String) {
				val1 = val1.toString();
				val2 = val2.toString();
			}

			assertEquals(message, val1, val2);
		}
	}

	/**
	 * Convert the object into parameter to an Array.
	 * 
	 * @param object
	 *            the source object
	 * @return an array list
	 */
	@SuppressWarnings("unchecked")
	private ArrayList<Object> generateArray(Object object) {

		ArrayList<Object> tab = new ArrayList<Object>();

		if (object == null) {
			return tab;
		}

		if (object instanceof ArrayList<?>) {
			for (Object o : (ArrayList) object) {
				if (o instanceof Enum) {
					tab.add(o.toString());
				} else {
					tab.add(o);
				}
			}
		} else if (object instanceof Object[]) {
			Object[] t = (Object[]) object;
			for (int i = 0; i < t.length; i++) {
				tab.add(t[i]);
			}
		} else if (object instanceof Enum) {
			tab.add(object.toString());
		} else {
			tab.add(object);
		}
		return tab;
	}
}
