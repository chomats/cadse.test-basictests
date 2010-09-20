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
package fr.imag.adele.cadse.test.basictests.isabstract;

import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseView.workspaceView;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.abstractKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.notAbstractKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.notListKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.rootKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.sicpKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.simpKv;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.checkCompilationErrors;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.createCadseDefinition;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.createItemType;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.createString;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.selectCadses;
import static fr.imag.adele.graphictests.gtworkbench_part.GTView.welcomeView;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import fr.imag.adele.cadse.cadseg.managers.CadseDefinitionManager;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue;
import fr.imag.adele.graphictests.cadse.test.GTCadseRTConstants;
import fr.imag.adele.graphictests.cadse.test.GTCadseTestCase;
import fr.imag.adele.graphictests.gttree.GTTreePath;

@RunWith(JUnit4.class)
public class IsAbstract_tc_CADSEg extends GTCadseTestCase {

	protected static final String cadse_name = "CADSE_IsAbstract";
	protected GTTreePath cadse_model = new GTTreePath(cadse_name);
	protected GTTreePath build_model = cadse_model.concat(CadseDefinitionManager.BUILD_MODEL);
	protected GTTreePath data_model = cadse_model.concat(CadseDefinitionManager.DATA_MODEL);

	/** Performs initializations */
	@BeforeClass
	public static void createContext() throws Exception {
		selectCadses(GTCadseRTConstants.CADSEG_MODEL);
		welcomeView.close();
		workspaceView.show();

		// Creates a new CADSE
		createCadseDefinition(cadse_name, "model." + cadse_name);
	}

	@Test
	public void test_item_creation() throws Exception {

		// Key values
		KeyValue superItA = new KeyValue(CadseGCST.ITEM_TYPE_lt_SUPER_TYPE, data_model.concat("itA"));
		KeyValue superItNa = new KeyValue(CadseGCST.ITEM_TYPE_lt_SUPER_TYPE, data_model.concat("itNa"));

		KeyValue defValAKv = new KeyValue(CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE_, "default_value_itA");
		KeyValue defValAAKv = new KeyValue(CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE_, "default_value_itAA");
		KeyValue defValANaKv = new KeyValue(CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE_, "default_value_itANa");

		KeyValue defValNaKv = new KeyValue(CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE_, "default_value_itNa");
		KeyValue defValNaAKv = new KeyValue(CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE_, "default_value_itNaA");
		KeyValue defValNaNaKv = new KeyValue(CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE_, "default_value_itNaNa");

		// Creates item types (A => Abstract, Na => Non abstract)

		// KeyValue abstractKv = new KeyValue(CadseGCST.ITEM_TYPE_at_IS_INSTANCE_ABSTRACT_, true);

		createItemType(data_model, "itA", abstractKv(), rootKv());
		createString(data_model.concat("itA"), "string_attribute_itA", defValAKv, sicpKv(), simpKv(), notListKv());

		createItemType(data_model, "itNa", notAbstractKv(), rootKv());
		createString(data_model.concat("itNa"), "string_attribute_itNa", defValNaKv, sicpKv(), simpKv(), notListKv());

		createItemType(data_model, "itAA", superItA, abstractKv(), rootKv());
		createString(data_model.concat("itAA"), "string_attribute_itAA", defValAAKv, sicpKv(), simpKv(), notListKv());

		createItemType(data_model, "itANa", superItA, notAbstractKv(), rootKv());
		createString(data_model.concat("itANa"), "string_attribute_itANa", defValANaKv, sicpKv(), simpKv(), notListKv());

		createItemType(data_model, "itNaA", superItNa, abstractKv(), rootKv());
		createString(data_model.concat("itNaA"), "string_attribute_itNaA", defValNaAKv, sicpKv(), simpKv(), notListKv());

		createItemType(data_model, "itNaNa", superItNa, notAbstractKv(), rootKv());
		createString(data_model.concat("itNaNa"), "string_attribute_itNaNa", defValNaNaKv, sicpKv(), simpKv(),
				notListKv());
	}

	/**
	 * Checks that the CADSE generated by this test has no compilation error.
	 * 
	 * @throws Exception
	 */
	@Test
	public void test_check_compilation() throws Exception {
		checkCompilationErrors(workspaceView, cadse_model);
	}
}
