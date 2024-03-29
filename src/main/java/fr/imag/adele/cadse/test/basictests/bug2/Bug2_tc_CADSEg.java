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
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.groupKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.notAbstractKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.rootKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.sicpKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.simpKv;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.checkCompilationErrors;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.createCadseDefinition;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.createItemType;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.createLinkType;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.createString;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.selectCadses;
import static fr.imag.adele.graphictests.gtworkbench_part.GTView.welcomeView;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import fr.imag.adele.cadse.cadseg.managers.CadseDefinitionManager;
import fr.imag.adele.graphictests.cadse.test.GTCadseRTConstants;
import fr.imag.adele.graphictests.gttree.GTTreePath;

@RunWith(JUnit4.class)
public class Bug2_tc_CADSEg extends Bug2_Common {

	/** A path to the CADSE definition. */
	public final GTTreePath cadseModel = new GTTreePath(cadseName);
	/** A path to the data model. */
	public final GTTreePath dataModel = cadseModel.concat(CadseDefinitionManager.DATA_MODEL);

	@Test
	public void testCADSEg() throws Exception {

		// Starts CADSEg
		selectCadses(GTCadseRTConstants.CADSEG_MODEL);
		welcomeView.close();
		workspaceView.show();

		// Populates DataModel
		createCadseDefinition(cadseName, "model." + cadseName);

		GTTreePath it1Path = dataModel.concat(it1Name);
		GTTreePath it2Path = dataModel.concat(it2Name);
		GTTreePath it3Path = dataModel.concat(it3Name);

		createItemType(dataModel, it1Name, notAbstractKv(), rootKv());
		createItemType(dataModel, it2Name, notAbstractKv(), rootKv());
		createItemType(dataModel, it3Name, notAbstractKv(), rootKv());

		createString(it1Path, attr1Name, sicpKv(), simpKv());
		createString(it2Path, attr2Name, sicpKv(), simpKv());
		createString(it3Path, attr3Name, sicpKv(), simpKv());

		createLinkType("groupLink1to2", it1Path, it2Path, null, null, groupKv());
		createLinkType("groupLink2to3", it2Path, it3Path, null, null, groupKv());

		checkCompilationErrors(workspaceView, cadseModel);
	}
}
