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
package fr.imag.adele.cadse.test.basictests.metrics;

import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseView.workspaceView;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.createCadseDefinition;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.createInteger;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.createItemType;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.selectCadses;
import static fr.imag.adele.graphictests.gtworkbench_part.GTView.welcomeView;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import fr.imag.adele.cadse.cadseg.managers.CadseDefinitionManager;
import fr.imag.adele.graphictests.cadse.test.GTCadseRTConstants;
import fr.imag.adele.graphictests.gttree.GTTreePath;

@RunWith(value = Parameterized.class)
public class Metrics_Integer extends Metrics_common {

	/** Test name */
	protected final static String testName = "MetricsInteger";
	/** The CADSE name. */
	protected final static String cadseName = "CADSE" + testName;
	/** The Item Type name */
	protected final static String itemTypeName = "ItemType";
	/** A path to the CADSE definition. */
	protected final static GTTreePath cadseModel = new GTTreePath(cadseName);
	/** A path to the data model. */
	protected final static GTTreePath dataModel = cadseModel.concat(CadseDefinitionManager.DATA_MODEL);
	/** A path to the ItemType */
	protected final static GTTreePath itemType = dataModel.concat(itemTypeName);

	/** Number of runs */
	protected final static int numberOfRuns = 10; // 00;
	/** Instance number */
	protected final int instanceNumber;

	@Parameters
	public static Collection<Object[]> data() {

		ArrayList<Object[]> params = new ArrayList<Object[]>();
		for (int i = 0; i < numberOfRuns; i++) {
			params.add(new Object[] { i });
		}
		return params;
	}

	/**
	 * Constructor.
	 * 
	 * @param number
	 *        the instance number.
	 */
	public Metrics_Integer(int number) {
		instanceNumber = number;
	}

	/**
	 * Creates the test context.
	 * <ul>
	 * <li>Creates the CADSE</li>
	 * <li>Creates the item type</li>
	 * </ul>
	 */
	@BeforeClass
	public static void createContext() {
		/* CADSEg initializations */
		selectCadses(GTCadseRTConstants.CADSEG_MODEL);
		welcomeView.close();
		workspaceView.show();

		/* Test initializations */
		createCadseDefinition(cadseName, "model." + cadseName);
		createItemType(dataModel, itemTypeName);
	}

	/**
	 * Performs the test itself.
	 */
	@Test
	public void testRunner() {
		createInteger(itemType, "IntegerAttr" + instanceNumber);
	}
}
