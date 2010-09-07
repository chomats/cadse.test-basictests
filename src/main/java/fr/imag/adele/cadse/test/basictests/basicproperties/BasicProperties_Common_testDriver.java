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
package fr.imag.adele.cadse.test.basictests.basicproperties;

import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseView.workspaceView;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.notAbstractKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.rootKv;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.createItemType;

import java.util.UUID;

import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.attribute.IAttributeType;
import fr.imag.adele.cadse.core.impl.CadseCore;
import fr.imag.adele.cadse.test.basictests.common.GTSimpleTestDriver;
import fr.imag.adele.cadse.test.basictests.common.GTTestParameter;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseShell;
import fr.imag.adele.graphictests.gttree.GTTreePath;

public class BasicProperties_Common_testDriver extends GTSimpleTestDriver {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.imag.adele.cadse.test.basictests.common.GTCommonTestDriver#createTypes
	 * (fr.imag.adele.cadse.test.basictests.common.GTTestParameter)
	 */
	@Override
	protected GTTreePath createTypes(GTTestParameter tp) {
		createItemType(getDataModel(tp), getItName(tp), notAbstractKv(), rootKv());
		return getDataModel(tp).concat(getItName(tp));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seefr.imag.adele.cadse.test.basictests.common.GTSimpleTestDriver#
	 * initCreationPage
	 * (fr.imag.adele.cadse.test.basictests.common.GTTestParameter)
	 */
	@Override
	protected GTCadseShell initCreationPage(GTTestParameter tp) {
		workspaceView.contextMenuNew(getItName(tp)).click();
		GTCadseShell shell = new GTCadseShell(getItName(tp));

		shell.findCadseFieldName().typeText(getInstanceName(tp));

		return shell;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.imag.adele.cadse.test.basictests.common.GTSimpleTestDriver#modelChecking
	 * (fr.imag.adele.cadse.test.basictests.common.GTTestParameter,
	 * java.util.UUID)
	 */
	@Override
	protected void modelChecking(GTTestParameter tp, UUID id) {
		Item item = CadseCore.getLogicalWorkspace().getItem(id);
		assertNotNull(item);
		IAttributeType<?> attr = item.getType().getAttributeType(getAttributeName(tp));
		Object actualModel = item.getAttribute(attr);
		Object expectedModel = getFinalValue(tp);
		assertEqualsListValues("Error in model checking for #" + tp.testNumber, expectedModel, actualModel);
	}
}
