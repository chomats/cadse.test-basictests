package fr.imag.adele.cadse.test.basictests.basicproperties;

import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseView.propertiesView;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseView.workspaceView;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.notAbstractKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.rootKv;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.createItemType;

import java.util.UUID;

import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;

import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.attribute.IAttributeType;
import fr.imag.adele.cadse.core.impl.CadseCore;
import fr.imag.adele.cadse.test.basictests.testdriver.GTCommonTestDriver;
import fr.imag.adele.cadse.test.basictests.testdriver.GTTestParameter;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseShell;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue;
import fr.imag.adele.graphictests.gttree.GTTreePath;
import fr.imag.adele.graphictests.test.GTEclipseConstants;
import fr.imag.adele.graphictests.test.GTPreferences;

public abstract class BasicProperties_Common_testDriver extends GTCommonTestDriver {

	/*
	 * (non-Javadoc)
	 * @see fr.imag.adele.cadse.test.basictests.testdriver.GTCommonTestDriver#getTestName()
	 */
	@Override
	protected String getTestName() {
		return "BasicProperties";
	}

	/**
	 * Gets the it name, for a given instance
	 * 
	 * @param tp
	 *        the test parameter
	 * @return the it name
	 */
	protected String getItName(GTTestParameter tp) {
		return itPrefix + tp.testNumber;
	}

	/**
	 * Gets the instance name.
	 * 
	 * @param tp
	 *        the test parameter
	 * @return the instance name
	 */
	protected String getInstanceName(GTTestParameter tp) {
		return instancePrefix + tp.testNumber;
	}

	/**
	 * Gets the the default value as it appears in the creation page. This value is the default value, corrected by
	 * CADSEg to match all the constraints.
	 * 
	 * @param tp
	 *        the test parameter
	 * @return the corrected def val
	 */
	protected KeyValue getCorrectedDefVal(GTTestParameter tp) {
		return adaptedValue(tp, tp.getValue("defVal"));
	}

	/**
	 * Gets the the new value as it appears in the modification page. This value is the default value, corrected by
	 * CADSEg to match all the constraints.
	 * 
	 * @param tp
	 *        the test parameter
	 * @return the corrected def val
	 */
	protected KeyValue getCorrectedNewVal(GTTestParameter tp) {
		return adaptedValue(tp, tp.getValue("newValue"));
	}

	protected KeyValue adaptedValue(GTTestParameter tp, KeyValue kv) {

		if (kv.value instanceof String && kv.getString().isEmpty()) {
			return new KeyValue(kv, null);
		}
		else {
			return kv;
		}
	}

	/**
	 * Gets the initial visual value.
	 * 
	 * @param tp
	 *        the test parameter
	 * @return a string or a list of string
	 */
	protected Object getInitialValue(GTTestParameter tp) {

		boolean isList = tp.getBoolean("list");

		if (isList) {
			return new Object[] {};
		}
		else {
			return getCorrectedDefVal(tp).value;
		}
	}

	/**
	 * Gets the final model value.
	 * 
	 * @param tp
	 *        the test parameter
	 * @return the final model value
	 */
	protected Object getFinalValue(GTTestParameter tp) {

		boolean sicp = tp.getBoolean("sicp");
		boolean isList = tp.getBoolean("list");
		KeyValue newKv = tp.getValue("newValue");

		if (isList) { // def val is ignored with list attributes
			if (sicp && newKv != null && isValidValue(tp, newKv.value)) {
				return new Object[] { newKv.value };
			}
			else {
				return new Object[] {};
			}
		}
		else {
			if (sicp && newKv != null && isValidValue(tp, newKv.value)) {
				return getCorrectedNewVal(tp).value;
			}
			else {
				return getCorrectedDefVal(tp).value;
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
		createItemType(dataModel, getItName(tp), notAbstractKv(), rootKv());
		return dataModel.concat(getItName(tp));
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * fr.imag.adele.cadse.test.basictests.testdriver.GTCommonTestDriver#getCreationKeyValues(fr.imag.adele.cadse.test
	 * .basictests.testdriver.GTTestParameter)
	 */
	@Override
	protected KeyValue[] getCreationKeyValues(GTTestParameter tp) {
		return tp.getValues("defVal", "sicp", "simp", "cbu", "list");
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * fr.imag.adele.cadse.test.basictests.testdriver.GTCommonTestDriver#isOkButtonActivated(fr.imag.adele.cadse.test
	 * .basictests.testdriver.GTTestParameter)
	 */
	@Override
	protected boolean isOkButtonActivated(GTTestParameter tp) {
		boolean sicp = tp.getBoolean("sicp");

		if (isAttributeCreationSuccess(tp)) {
			if (sicp) {
				return isValidValue(tp, tp.getValue("newValue").value);
			}
			else {
				return isValidValue(tp, tp.getValue("defVal").value);
			}
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
	public void testExecution(GTTestParameter tp) {

		boolean sicp = tp.getBoolean("sicp");
		boolean simp = tp.getBoolean("simp");

		boolean fieldInCP = sicp && isAttributeCreationSuccess(tp);
		boolean fieldInMP = simp && isAttributeCreationSuccess(tp);

		KeyValue newValue = tp.getValue("newValue");

		/* ============= */
		/* Creation page */
		/* ============= */

		workspaceView.contextMenuNew(getItName(tp)).click();
		GTCadseShell shell = new GTCadseShell(getItName(tp));

		// is field present
		boolean isFieldPresent = shell.fieldExists(getAttributeNameUnderTest());
		assertEquals("Presence of the attribute field is not as expected for #" + tp.testNumber, fieldInCP,
				isFieldPresent);

		// initial value
		if (fieldInCP) {
			Object expected = getInitialValue(tp);

			Object attrDefVal = shell.findCadseField(getAttributeNameUnderTest()).getAttribute().getDefaultValue();
			assertEqualsListValues("Initial default value error for #" + tp.testNumber, expected, attrDefVal);

			Object actualVisual = shell.findCadseField(getAttributeNameUnderTest()).getValue();
			assertEqualsListValues("Initial visual value error for #" + tp.testNumber, expected, actualVisual);

			Object actualModel = shell.findCadseField(getAttributeNameUnderTest()).getModelValue();
			assertEqualsListValues("Initial model value error for #" + tp.testNumber, expected, actualModel);
		}

		// New Attribute Value
		if (fieldInCP && newValue != null) {

			boolean actualSuccess = setValues(shell, newValue);
			boolean expectedSuccess = isSettableValue(tp, newValue);
			assertEquals("Success or failure is not as expected for #" + tp.testNumber, expectedSuccess, actualSuccess);

			// setting the new value has failed, as expected
			if (!actualSuccess) {
				shell.close(GTEclipseConstants.CANCEL_BUTTON);
				return;
			}
		}

		// name + CHANGES FOCUS!!!
		shell.findCadseFieldName().typeText(getInstanceName(tp));

		// final model value (okButtonActivated is important! if the value is not correct, the previous correct
		// model value (default value) is locked even if the field displays another value.
		if (fieldInCP && newValue != null && isValidValue(tp, getFinalValue(tp))) {
			Object expected = getFinalValue(tp);
			Object actual = shell.findCadseField(getAttributeNameUnderTest()).getModelValue();
			assertEqualsListValues("Final model value error for #" + tp.testNumber, expected, actual);
		}

		// Waits until refresh
		GTPreferences.sleep(SWTBotPreferences.DEFAULT_POLL_DELAY);

		// Gets the UUID
		UUID id = shell.findCadseFieldName().getRunningField().getSwtUiplatform().getItem().getId();

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

		/* ============== */
		/* Model checking */
		/* ============== */

		if (isAttributeCreationSuccess(tp)) {
			Item item = CadseCore.getLogicalWorkspace().getItem(id);
			assertNotNull(item);
			IAttributeType<?> attr = item.getType().getAttributeType(getAttributeNameUnderTest());
			assertNotNull(attr);
			Object actualModel = item.getAttribute(attr);
			Object expectedModel = getFinalValue(tp);
			assertEqualsListValues("Error in model checking for #" + tp.testNumber, expectedModel, actualModel);
		}

		/* ============= */
		/* Property page */
		/* ============= */

		workspaceView.selectNode(getInstanceName(tp));
		propertiesView.showTab(getItName(tp));

		// Name
		assertEquals(getInstanceName(tp), propertiesView.findCadseFieldName().getText());

		// Field value
		if (fieldInMP) {
			Object expectedGraphical = getFinalValue(tp);
			Object actualGraphical = propertiesView.findCadseField(getAttributeNameUnderTest()).getValue();
			assertEqualsListValues("Error in graphical modification page for #" + tp.testNumber, expectedGraphical,
					actualGraphical);

			Object expectedModel = getFinalValue(tp);
			Object actualModel = propertiesView.findCadseField(getAttributeNameUnderTest()).getModelValue();
			assertEqualsListValues("Error in model modification page for #" + tp.testNumber, expectedModel, actualModel);
		}
	}
}
