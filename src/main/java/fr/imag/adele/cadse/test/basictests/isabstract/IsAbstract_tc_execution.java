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
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.createBasicItem;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.selectCadses;
import static fr.imag.adele.graphictests.gtworkbench_part.GTView.propertiesView;
import static fr.imag.adele.graphictests.gtworkbench_part.GTView.welcomeView;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import fr.imag.adele.graphictests.cadse.test.GTCadseTestCase;
import fr.imag.adele.graphictests.gttree.GTTreePath;
import fr.imag.adele.graphictests.test.GTPreferences;

@RunWith(JUnit4.class)
public class IsAbstract_tc_execution extends GTCadseTestCase {

	/** Performs initializations */
	@BeforeClass
	public static void createContext() throws Exception {
		selectCadses("Cadse Model.Workspace.CADSE_IsAbstract");
		welcomeView.close();
		workspaceView.show();
	}

	@Test
	public void test_running() throws Exception {

		/* This items creation should be possible */
		createBasicItem(workspaceView, null, "itNa", "instance-itNa", new GTTreePath("instance-itNa"));
		propertiesView.showTab("itNa");
		assertEquals("default_value_itNa", propertiesView.findTextWithLabel("string_attribute_itNa").getText());

		createBasicItem(workspaceView, null, "itANa", "instance-itANa", new GTTreePath("instance-itANa"));
		propertiesView.showTab("itANa");
		assertEquals("default_value_itA", propertiesView.findTextWithLabel("string_attribute_itA").getText());
		assertEquals("default_value_itANa", propertiesView.findTextWithLabel("string_attribute_itANa").getText());

		createBasicItem(workspaceView, null, "itNaNa", "instance-itNaNa", new GTTreePath("instance-itNaNa"));
		propertiesView.showTab("itNaNa");
		assertEquals("default_value_itNa", propertiesView.findTextWithLabel("string_attribute_itNa").getText());
		assertEquals("default_value_itNaNa", propertiesView.findTextWithLabel("string_attribute_itNaNa").getText());

		/* This items creation shouldn't be available */
		assertItemCantbeCreated(workspaceView, null, "itA", GTPreferences.FAILING_ASSERT_TIMEOUT);
		assertItemCantbeCreated(workspaceView, null, "itNaA", GTPreferences.FAILING_ASSERT_TIMEOUT);
		assertItemCantbeCreated(workspaceView, null, "itAA", GTPreferences.FAILING_ASSERT_TIMEOUT);
	}

}
