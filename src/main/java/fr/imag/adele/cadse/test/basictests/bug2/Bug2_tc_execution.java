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
package fr.imag.adele.cadse.test.basictests.bug2;

import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseView.workspaceView;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.createBasicHead;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.createInteger;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.selectCadses;
import static fr.imag.adele.graphictests.gtworkbench_part.GTView.welcomeView;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import fr.imag.adele.graphictests.gttree.GTTreePath;

@RunWith(JUnit4.class)
public class Bug2_tc_execution extends Bug2_Common {

	@Test
	public void test_execution() throws Exception {
		selectCadses("Cadse Model.Workspace." + cadseName);
		welcomeView.close();
		workspaceView.show();

		createBasicHead(workspaceView, null, "ItSrc", "s1", new GTTreePath("s1"));
		createBasicHead(workspaceView, null, "ItSrc", "s2", new GTTreePath("s2"));

		createInteger(new GTTreePath("s1"), "intAttr");
		createInteger(new GTTreePath("s2"), "intAttr");
	}
}
