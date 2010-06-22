package fr.imag.adele.cadse.test.basictests.group.test1;

import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseView.propertiesView;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseView.workspaceView;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.notAbstractKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.rootKv;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.createItemType;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.createLinkType;

import java.util.ArrayList;

import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;

import fr.imag.adele.cadse.test.basictests.testdriver.GTCommonTestDriver;
import fr.imag.adele.cadse.test.basictests.testdriver.GTTestParameter;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseShell;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue;
import fr.imag.adele.graphictests.gttree.GTTreePath;
import fr.imag.adele.graphictests.test.GTEclipseConstants;
import fr.imag.adele.graphictests.test.GTPreferences;

public abstract class Test1_Common_testDriver extends GTCommonTestDriver {

	/** Link prefix used to compute the names */
	protected final String linkName = "link";

	/*
	 * (non-Javadoc)
	 * @see fr.imag.adele.cadse.test.basictests.testdriver.GTCommonTestDriver#getTestName()
	 */
	@Override
	public String getTestName() {
		return "group_test1";
	}

	/**
	 * Gets the source it name, for a given instance.
	 * 
	 * @param tp
	 *        the test parameter
	 * @return the source it name
	 */
	protected String getItSrcName(GTTestParameter tp) {
		return itPrefix + "Src" + tp.testNumber;
	}

	/**
	 * Gets the link name, for a given instance
	 * 
	 * @param tp
	 *        the test parameter
	 * @return the link name
	 */
	protected String getLinkName(GTTestParameter tp) {
		return linkName;
	}

	/**
	 * Gets the it destination name, for a given instance
	 * 
	 * @param tp
	 *        the test parameter
	 * @return the destination it name
	 */
	protected String getItDstName(GTTestParameter tp) {
		return itPrefix + "Dst" + tp.testNumber;
	}

	/**
	 * Gets the source instance name.
	 * 
	 * @param tp
	 *        the test parameter
	 * @return the source instance name
	 */
	protected String getInstanceSrcName(GTTestParameter tp) {
		return instancePrefix + "Src" + tp.testNumber;
	}

	/**
	 * Gets the destination instance name.
	 * 
	 * @param tp
	 *        the test parameter
	 * @return the destination instance name
	 */
	protected String getInstanceDstName(GTTestParameter tp) {
		return instancePrefix + "Dst" + tp.testNumber;
	}

	/**
	 * Gets the default value.
	 * 
	 * @param tp
	 *        the test parameter
	 * @return the default value
	 */
	protected KeyValue getDefaultValue(GTTestParameter tp) {

		boolean isList = tp.getBoolean("list");

		if (isList) {
			return new KeyValue(getAttributeName(), new String[] {});
		}
		else {
			return new KeyValue(getAttributeName(), tp.getValue("defVal").value);
		}
	}

	/**
	 * Gets the first new value.
	 * 
	 * @param tp
	 *        the test parameter
	 * @return the first new value
	 */
	protected KeyValue getNewValue1(GTTestParameter tp) {

		boolean isList = tp.getBoolean("list");
		boolean sicp = tp.getBoolean("sicp");

		if (sicp) {
			if (isList) {
				return new KeyValue(getAttributeName(), new String[] { tp.getValue("newValue1").value.toString() });
			}
			else {
				return tp.getValue("newValue1");
			}
		}
		else {
			return getDefaultValue(tp);
		}
	}

	/**
	 * Gets the second new value.
	 * 
	 * @param tp
	 *        the test parameter
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

			return new KeyValue(getAttributeName(), visual.toArray(new String[] {}));
		}

		// not list
		else {
			if (simp) {
				if (isValidValue(tp, val2)) {
					return new KeyValue(getAttributeName(), val2);
				}
				else {
					return getNewValue1(tp);
				}
			}
			else {
				return getNewValue1(tp);
			}
		}
	}

	/**
	 * Gets the third new value.
	 * 
	 * @param tp
	 *        the test parameter
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

			return new KeyValue(getAttributeName(), visual.toArray(new String[] {}));
		}

		// not list
		else {
			if (simp) {
				if (isValidValue(tp, newVal)) {
					return new KeyValue(getAttributeName(), newVal);
				}
				else {
					return getNewValue2(tp);
				}
			}
			else {
				return getNewValue2(tp);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * fr.imag.adele.cadse.test.basictests.testdriver.GTCommonTestDriver#createTypes(fr.imag.adele.cadse.test.basictests
	 * .testdriver.GTTestParameter)
	 */
	@Override
	protected GTTreePath createTypes(GTTestParameter tp) {
		GTTreePath src = dataModel.concat(getItSrcName(tp));
		GTTreePath dst = dataModel.concat(getItDstName(tp));

		createItemType(dataModel, src.getDestinationName(), notAbstractKv, rootKv);
		createItemType(dataModel, dst.getDestinationName(), notAbstractKv, rootKv);
		createLinkType(getLinkName(tp), src, dst, null, null, KeyValue.groupKv);

		return src;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * fr.imag.adele.cadse.test.basictests.testdriver.GTCommonTestDriver#getCreationKeyValues(fr.imag.adele.cadse.test
	 * .basictests.testdriver.GTTestParameter)
	 */
	@Override
	protected KeyValue[] getCreationKeyValues(GTTestParameter tp) {
		return tp.getValues("sicp", "simp", "cbu", "list", "defVal");
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * fr.imag.adele.cadse.test.basictests.testdriver.GTCommonTestDriver#isOkButtonActivated(fr.imag.adele.cadse.test
	 * .basictests.testdriver.GTTestParameter)
	 */
	@Override
	protected boolean isOkButtonActivated(GTTestParameter tp) {
		if (isAttributeCreationSuccess(tp)) {
			return isValidValue(tp, tp.getValue("newValue1").value);
		}
		else {
			return true;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * fr.imag.adele.cadse.test.basictests.testdriver.GTCommonTestDriver#testExecution(fr.imag.adele.cadse.test.basictests
	 * .testdriver.GTTestParameter)
	 */
	@Override
	protected void testExecution(GTTestParameter tp) {

		boolean sicp = tp.getBoolean("sicp");
		boolean simp = tp.getBoolean("simp");

		boolean fieldInCP = sicp && isAttributeCreationSuccess(tp);
		boolean fieldInMP = simp && isAttributeCreationSuccess(tp);

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
		boolean isFieldPresent = shell.fieldExists(getAttributeName());
		assertEquals("Presence of the attribute field is not as expected for #" + tp.testNumber, fieldInCP,
				isFieldPresent);

		// GET initial value
		if (fieldInCP) {
			KeyValue expected = getDefaultValue(tp);

			Object actualVisual = shell.findCadseField(getAttributeName()).getValue();
			assertEqualsListValues("Initial visual value error for #" + tp.testNumber, expected.value, actualVisual);

			Object actualModel = shell.findCadseField(getAttributeName()).getModelValue();
			assertEqualsListValues("Initial model value error for #" + tp.testNumber, expected.value, actualModel);
		}

		// SET Value1
		if (fieldInCP && val1 != null) {
			boolean actualSuccess = setValues(shell, val1);
			boolean expectedSuccess = isSettableValue(tp, val1);
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
		}
		else {
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
		isFieldPresent = propertiesView.fieldExists(getAttributeName());
		assertEquals("Presence of the attribute field is not as expected for #" + tp.testNumber, fieldInMP,
				isFieldPresent);

		// GET New Value 1
		if (fieldInMP) {
			KeyValue expected = getNewValue1(tp);

			Object actualVisual = propertiesView.findCadseField(getAttributeName()).getValue();
			assertEqualsListValues("Error with final visual value for #" + tp.testNumber, expected.value, actualVisual);

			Object actualModel = propertiesView.findCadseField(getAttributeName()).getModelValue();
			assertEqualsListValues("Error with final visual value for #" + tp.testNumber, expected.value, actualModel);
		}

		// SET New Value 2
		if (fieldInMP && val2 != null) {
			boolean expectedSuccess = isSettableValue(tp, val2);
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

			Object actualVisual = shell.findCadseField(getAttributeName()).getValue();
			assertEqualsListValues("Error with final visual value for #" + tp.testNumber, expected.value, actualVisual);

			Object actualModel = shell.findCadseField(getAttributeName()).getModelValue();
			assertEqualsListValues("Error with final visual value for #" + tp.testNumber, expected.value, actualModel);
		}
		else {
			assertCadseFieldDoesNotExist(shell, getAttributeName(), 0);
			assertButtonDoesNotExist(shell, GTEclipseConstants.NEXT_BUTTON, 0);
		}

		shell.close();

		/* ======================= */
		/* Head value modification */
		/* ======================= */

		// is field present
		isFieldPresent = propertiesView.fieldExists(getAttributeName());
		assertEquals("Presence of the attribute field is not as expected for #" + tp.testNumber, fieldInMP,
				isFieldPresent);

		// SET New Value 2
		if (fieldInMP && val3 != null) {
			boolean expectedSuccess = isSettableValue(tp, val3);
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
		isFieldPresent = propertiesView.fieldExists(getAttributeName());
		assertEquals("Presence of the attribute field is not as expected for #" + tp.testNumber, fieldInMP,
				isFieldPresent);

		// GET New Value 3
		if (fieldInMP) {

			workspaceView.selectNode(getInstanceDstName(tp));
			propertiesView.showTab(getInstanceSrcName(tp));
			KeyValue expected = getNewValue3(tp);

			Object actualVisual = propertiesView.findCadseField(getAttributeName()).getValue();
			assertEqualsListValues("Error with final visual value for #" + tp.testNumber, expected.value, actualVisual);

			Object actualModel = propertiesView.findCadseField(getAttributeName()).getModelValue();
			assertEqualsListValues("Error with final visual value for #" + tp.testNumber, expected.value, actualModel);
		}
	}
}
