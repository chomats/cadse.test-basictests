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
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.sicpKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.simpKv;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.createString;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.selectCadses;
import static fr.imag.adele.graphictests.gtworkbench_part.GTView.welcomeView;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseShell;
import fr.imag.adele.graphictests.gttree.GTTreePath;
import fr.imag.adele.graphictests.test.GTEclipseConstants;

@RunWith(JUnit4.class)
public class Bug2_tc_execution extends Bug2_Common {

	@Test
	public void test_execution() throws Exception {
		selectCadses("Cadse Model.Workspace." + cadseName);
		welcomeView.close();
		workspaceView.show();

		String instance1Name = "instance1";
		String instance2Name = "instance2";
		String instance3Name = "instance3";

		GTTreePath instance1Path = new GTTreePath(instance1Name);
		GTTreePath instance2Path = new GTTreePath(instance2Name);
		GTTreePath instance3Path = new GTTreePath(instance3Name);

		String attr1Value = "value1";
		String attr2Value = "value2";
		String attr3Value = "value3";

		String attrInst1Name = "attrInst1";
		String attrInst2Name = "attrInst2";
		String attrInst3Name = "attrInst3";

		String attrInst1Value = "attrInstValue1";
		String attrInst2Value = "attrInstValue2";
		String attrInst3Value = "attrInstValue3";

		GTTreePath attrInst1Path = instance1Path.concat(attrInst1Name);
		GTTreePath attrInst2Path = instance2Path.concat(attrInst2Name);

		// creates instance1
		workspaceView.contextMenuNewHead(null, it1Name).click();
		GTCadseShell shell = new GTCadseShell(it1Name);
		shell.findCadseFieldName().typeText(instance1Name);
		shell.findCadseField(attr1Name).typeText(attr1Value);
		shell.waitUntilButtonEnabled(GTEclipseConstants.FINISH_BUTTON);
		shell.close();
		workspaceView.selectNode(instance1Name);

		// creates attribute attrInst1
		createString(instance1Path, attrInst1Name, sicpKv(), simpKv());
		workspaceView.selectNode(attrInst1Path);

		// creates member instance2
		workspaceView.contextMenuNewMember(instance1Path, it2Name).click();
		shell = new GTCadseShell(it2Name);
		shell.findCadseFieldName().typeText(instance2Name);
		shell.findCadseField(attr2Name).typeText(attr2Value);
		shell.next();
		shell.findCadseField(attrInst1Name).typeText(attrInst1Value);
		assertEquals(attr1Value, shell.findCadseField(attr1Name).getText());
		shell.waitUntilButtonEnabled(GTEclipseConstants.FINISH_BUTTON);
		shell.close();
		workspaceView.selectNode(instance2Name);

		// creates attribute attrInst1
		createString(instance2Path, attrInst2Name, sicpKv(), simpKv());
		workspaceView.selectNode(attrInst2Path);

		// creates member instance3
		workspaceView.contextMenuNewMember(instance2Path, it3Name).click();
		shell = new GTCadseShell(it3Name);
		shell.findCadseFieldName().typeText(instance3Name);
		shell.findCadseField(attr3Name).typeText(attr3Value);
		shell.next();
		assertEquals(attr1Value, shell.findCadseField(attr1Name).getText());
		assertEquals(attr2Value, shell.findCadseField(attr2Name).getText());
		assertEquals(attrInst1Value, shell.findCadseField(attrInst1Name).getText());
		shell.findCadseField(attrInst2Name).typeText(attrInst2Value);
		shell.waitUntilButtonEnabled(GTEclipseConstants.FINISH_BUTTON);
		shell.close();
		workspaceView.selectNode(instance2Name);

	}
}
