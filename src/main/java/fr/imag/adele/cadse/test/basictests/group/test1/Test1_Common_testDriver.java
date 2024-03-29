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
package fr.imag.adele.cadse.test.basictests.group.test1;

import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseView.propertiesView;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseView.workspaceView;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.groupKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.notAbstractKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.rootKv;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.createItemType;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.createLinkType;

import java.util.ArrayList;

import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;

import fr.imag.adele.cadse.test.basictests.common.GTCommonTestDriver;
import fr.imag.adele.cadse.test.basictests.common.GTTestParameter;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseShell;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue;
import fr.imag.adele.graphictests.gttree.GTTreePath;
import fr.imag.adele.graphictests.test.GTEclipseConstants;
import fr.imag.adele.graphictests.test.GTPreferences;

public class Test1_Common_testDriver extends GTCommonTestDriver {

	/** Link prefix used to compute the names */
	protected final String linkName = "link";

	/**
	 * Gets the source it name, for a given instance.
	 * 
	 * @param tp
	 *            the test parameter
	 * @return the source it name
	 */
	protected String getItSrcName(GTTestParameter tp) {
		return itPrefix + "Src" + tp.testNumber;
	}

	/**
	 * Gets the link name, for a given instance
	 * 
	 * @param tp
	 *            the test parameter
	 * @return the link name
	 */
	protected String getLinkName(GTTestParameter tp) {
		return linkName;
	}

	/**
	 * Gets the it destination name, for a given instance
	 * 
	 * @param tp
	 *            the test parameter
	 * @return the destination it name
	 */
	protected String getItDstName(GTTestParameter tp) {
		return itPrefix + "Dst" + tp.testNumber;
	}

	/**
	 * Gets the source instance name.
	 * 
	 * @param tp
	 *            the test parameter
	 * @return the source instance name
	 */
	protected String getInstanceSrcName(GTTestParameter tp) {
		return instancePrefix + "Src" + tp.testNumber;
	}

	/**
	 * Gets the destination instance name.
	 * 
	 * @param tp
	 *            the test parameter
	 * @return the destination instance name
	 */
	protected String getInstanceDstName(GTTestParameter tp) {
		return instancePrefix + "Dst" + tp.testNumber;
	}

	/**
	 * Gets the default value.
	 * 
	 * @param tp
	 *            the test parameter
	 * @return the default value
	 */
	protected KeyValue getDefaultValue(GTTestParameter tp) {

		boolean isList = tp.getBoolean("list");

		if (isList) {
			return new KeyValue(getAttributeName(tp), new String[] {});
		} else {
			return new KeyValue(getAttributeName(tp), tp.getValue("defVal").value);
		}
	}

	/**
	 * Gets the first new value.
	 * 
	 * @param tp
	 *            the test parameter
	 * @return the first new value
	 */
	protected KeyValue getNewValue1(GTTestParameter tp) {

		boolean isList = tp.getBoolean("list");
		boolean sicp = tp.getBoolean("sicp");

		if (sicp) {
			if (isList) {
				return new KeyValue(getAttributeName(tp), new String[] { tp.getValue("newValue1").value.toString() });
			} else {
				return tp.getValue("newValue1");
			}
		} else {
			return getDefaultValue(tp);
		}
	}

	/**
	 * Gets the second new value.
	 * 
	 * @param tp
	 *            the test parameter
	 * @return the second new value
	 */
	protected KeyValue getNewValue2(GTTestParameter tp) {

		boolean isList = tp.getBoolean("list");
		boolean simp = tp.getBoolean("simp");
		Object val2 = tp.getValue("newValue2").value;

		if (isList) {

			KeyValue kv = getNewValue1(tp);

			ArrayList<String> visual = new ArrayList<String>();
			ArrayList<String> model = new ArrayList<String>();

			for (int j = 0; j < ((String[]) kv.value).length; j++) {
				visual.add(((String[]) kv.value)[j]);
				model.add(((String[]) kv.value)[j]);
			}

			if (simp) {
				visual.add(val2.toString());
				model.add(val2.toString());
			}

			return new KeyValue(getAttributeName(tp), visual.toArray(new String[] {}));
		}

		// not list
		else {
			if (simp) {
				if (tp.isValidValue(val2)) {
					return new KeyValue(getAttributeName(tp), val2);
				} else {
					return getNewValue1(tp);
				}
			} else {
				return getNewValue1(tp);
			}
		}
	}

	/**
	 * Gets the third new value.
	 * 
	 * @param tp
	 *            the test parameter
	 * @return the third new value
	 */
	protected KeyValue getNewValue3(GTTestParameter tp) {

		boolean isList = tp.getBoolean("list");
		boolean simp = tp.getBoolean("simp");
		Object newVal = tp.getValue("newValue3").value;

		if (isList) {

			KeyValue kv = getNewValue2(tp);

			ArrayList<String> visual = new ArrayList<String>();
			ArrayList<String> model = new ArrayList<String>();

			for (int j = 0; j < ((String[]) kv.value).length; j++) {
				visual.add(((String[]) kv.value)[j]);
				model.add(((String[]) kv.value)[j]);
			}

			if (simp) {
				if (!visual.contains(newVal.toString())) {
					visual.add(newVal.toString());
				}
				if (!model.contains(newVal.toString())) {
					model.add(newVal.toString());
				}
			}

			return new KeyValue(getAttributeName(tp), visual.toArray(new String[] {}));
		}

		// not list
		else {
			if (simp) {
				if (tp.isValidValue(newVal)) {
					return new KeyValue(getAttributeName(tp), newVal);
				} else {
					return getNewValue2(tp);
				}
			} else {
				return getNewValue2(tp);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.imag.adele.cadse.test.basictests.common.GTCommonTestDriver#createTypes
	 * (fr.imag.adele.cadse.test.basictests.common.GTTestParameter)
	 */
	@Override
	protected GTTreePath createTypes(GTTestParameter tp) {
		GTTreePath src = getDataModel(tp).concat(getItSrcName(tp));
		GTTreePath dst = getDataModel(tp).concat(getItDstName(tp));

		createItemType(getDataModel(tp), src.getDestinationName(), notAbstractKv(), rootKv());
		createItemType(getDataModel(tp), dst.getDestinationName(), notAbstractKv(), rootKv());
		createLinkType(getLinkName(tp), src, dst, null, null, groupKv());

		return src;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seefr.imag.adele.cadse.test.basictests.common.GTCommonTestDriver#
	 * isOkButtonActivated
	 * (fr.imag.adele.cadse.test.basictests.common.GTTestParameter)
	 */
	@Override
	protected boolean isOkButtonActivated(GTTestParameter tp) {

		boolean sicp = tp.getBoolean("sicp");

		if (tp.isAttributeCreationSuccess()) {
			if (sicp) {
				return tp.isValidValue(tp.getValue("newValue1").value);
			} else {
				return tp.isValidValue(tp.getValue("defVal").value);
			}
		} else {
			return true;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.imag.adele.cadse.test.basictests.common.GTCommonTestDriver#testExecution
	 * (fr.imag.adele.cadse.test.basictests.common.GTTestParameter)
	 */
	@Override
	public void testExecution(GTTestParameter tp) {

		boolean sicp = tp.getBoolean("sicp");
		boolean simp = tp.getBoolean("simp");

		boolean fieldInCP = sicp && tp.isAttributeCreationSuccess();
		boolean fieldInMP = simp && tp.isAttributeCreationSuccess();

		// Values used to set fields
		KeyValue val1 = tp.getValue("newValue1");
		KeyValue val2 = tp.getValue("newValue2");
		KeyValue val3 = tp.getValue("newValue3");

		/* ============= */
		/* Head creation */
		/* ============= */

		workspaceView.contextMenuNewHead(getItSrcName(tp)).click();
		GTCadseShell shell = new GTCadseShell(getItSrcName(tp));

		// is field present
		boolean isFieldPresent = shell.fieldExists(getAttributeName(tp));
		assertEquals("Presence of the attribute field is not as expected for #" + tp.testNumber, fieldInCP,
				isFieldPresent);

		// GET initial value
		if (fieldInCP) {
			KeyValue expected = getDefaultValue(tp);

			Object actualVisual = shell.findCadseField(getAttributeName(tp)).getValue();
			assertEqualsListValues("Initial visual value error for #" + tp.testNumber, expected.value, actualVisual);

			Object actualModel = shell.findCadseField(getAttributeName(tp)).getModelValue();
			assertEqualsListValues("Initial model value error for #" + tp.testNumber, expected.value, actualModel);
		}

		// SET Value1
		if (fieldInCP && val1 != null) {
			boolean actualSuccess = setValues(shell, val1);
			boolean expectedSuccess = tp.isSettableValue(val1);
			assertEquals("Success or failure is not as expected for #" + tp.testNumber, expectedSuccess, actualSuccess);

			// setting the new value has failed, as expected
			if (!actualSuccess) {
				shell.close(GTEclipseConstants.CANCEL_BUTTON);
				return;
			}
		}

		// name + CHANGES FOCUS!!!
		shell.findCadseFieldName().typeText(getInstanceSrcName(tp));

		// Waits until refresh
		GTPreferences.sleep(SWTBotPreferences.DEFAULT_POLL_DELAY);

		// Closes shell
		if (isOkButtonActivated(tp)) {
			shell.close();
		} else {
			try {
				shell.close(GTPreferences.FAILING_ASSERT_TIMEOUT);
				fail("OK button is activated whereas it shouldn't for #" + tp.testNumber);
			} catch (Exception e) {
				// SUCCESS
				return;
			}
		}

		/* ============= */
		/* Property page */
		/* ============= */

		workspaceView.selectNode(getInstanceSrcName(tp));
		propertiesView.showTab(getItSrcName(tp));

		// Name
		assertEquals(getInstanceSrcName(tp), propertiesView.findCadseFieldName().getText());

		// is field present
		isFieldPresent = propertiesView.fieldExists(getAttributeName(tp));
		assertEquals("Presence of the attribute field is not as expected for #" + tp.testNumber, fieldInMP,
				isFieldPresent);

		// GET New Value 1
		if (fieldInMP) {
			KeyValue expected = getNewValue1(tp);

			Object actualVisual = propertiesView.findCadseField(getAttributeName(tp)).getValue();
			assertEqualsListValues("Error with final visual value for #" + tp.testNumber, expected.value, actualVisual);

			Object actualModel = propertiesView.findCadseField(getAttributeName(tp)).getModelValue();
			assertEqualsListValues("Error with final visual value for #" + tp.testNumber, expected.value, actualModel);
		}

		// SET New Value 2
		if (fieldInMP && val2 != null) {
			boolean expectedSuccess = tp.isSettableValue(val2);
			boolean actualSuccess = setValues(propertiesView, val2);
			assertEquals("Success or failure is not as expected for #" + tp.testNumber, expectedSuccess, actualSuccess);

			// setting the new value has failed, as expected
			if (!actualSuccess) {
				return;
			}
		}

		/* =============== */
		/* Member creation */
		/* =============== */

		workspaceView.contextMenuNewMember(new GTTreePath(getInstanceSrcName(tp)), getItDstName(tp)).click();
		shell = new GTCadseShell(getItDstName(tp));
		shell.findCadseFieldName().typeText(getInstanceDstName(tp));

		// GET NewValue 2
		if (fieldInCP) {

			shell.next();

			KeyValue expected = getNewValue2(tp);

			Object actualVisual = shell.findCadseField(getAttributeName(tp)).getValue();
			assertEqualsListValues("Error with final visual value for #" + tp.testNumber, expected.value, actualVisual);

			Object actualModel = shell.findCadseField(getAttributeName(tp)).getModelValue();
			assertEqualsListValues("Error with final visual value for #" + tp.testNumber, expected.value, actualModel);
		} else {
			assertCadseFieldDoesNotExist(shell, getAttributeName(tp), 0);
			assertButtonDoesNotExist(shell, GTEclipseConstants.NEXT_BUTTON, 0);
		}

		shell.close();

		/* ======================= */
		/* Head value modification */
		/* ======================= */

		// is field present
		isFieldPresent = propertiesView.fieldExists(getAttributeName(tp));
		assertEquals("Presence of the attribute field is not as expected for #" + tp.testNumber, fieldInMP,
				isFieldPresent);

		// SET New Value 2
		if (fieldInMP && val3 != null) {
			boolean expectedSuccess = tp.isSettableValue(val3);
			workspaceView.selectNode(getInstanceSrcName(tp));
			propertiesView.showTab(getItSrcName(tp));
			boolean actualSuccess = setValues(propertiesView, val3);
			assertEquals("Success or failure is not as expected for #" + tp.testNumber, expectedSuccess, actualSuccess);

			// setting the new value has failed, as expected
			if (!actualSuccess) {
				return;
			}
		}

		/* ================================= */
		/* Member modification page checking */
		/* ================================= */

		// is field present
		isFieldPresent = propertiesView.fieldExists(getAttributeName(tp));
		assertEquals("Presence of the attribute field is not as expected for #" + tp.testNumber, fieldInMP,
				isFieldPresent);

		// GET New Value 3
		if (fieldInMP) {

			workspaceView.selectNode(getInstanceDstName(tp));
			propertiesView.showTab(getInstanceSrcName(tp));
			KeyValue expected = getNewValue3(tp);

			Object actualVisual = propertiesView.findCadseField(getAttributeName(tp)).getValue();
			assertEqualsListValues("Error with final visual value for #" + tp.testNumber, expected.value, actualVisual);

			Object actualModel = propertiesView.findCadseField(getAttributeName(tp)).getModelValue();
			assertEqualsListValues("Error with final visual value for #" + tp.testNumber, expected.value, actualModel);
		}
	}
}
