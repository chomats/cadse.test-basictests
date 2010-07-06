package fr.imag.adele.cadse.test.basictests.common;

import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseView.propertiesView;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseView.workspaceView;

import java.util.UUID;

import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;

import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseShell;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue;
import fr.imag.adele.graphictests.test.GTEclipseConstants;
import fr.imag.adele.graphictests.test.GTPreferences;

public abstract class GTSimpleTestDriver extends GTCommonTestDriver {

	/**
	 * Gets the it name, for a given instance
	 * 
	 * @param tp
	 *            the test parameter
	 * @return the it name
	 */
	protected String getItName(GTTestParameter tp) {
		return itPrefix + tp.testNumber;
	}

	/**
	 * Gets the instance name.
	 * 
	 * @param tp
	 *            the test parameter
	 * @return the instance name
	 */
	protected String getInstanceName(GTTestParameter tp) {
		return instancePrefix + tp.testNumber;
	}

	/**
	 * Gets the the default value as it appears in the creation page. This value
	 * is the default value, corrected by CADSEg to match all the constraints.
	 * 
	 * @param tp
	 *            the test parameter
	 * @return the corrected def val
	 */
	protected KeyValue getCorrectedDefVal(GTTestParameter tp) {
		return tp.adaptedValue(tp.getValue("defVal"));
	}

	/**
	 * Gets the the new value as it appears in the modification page. This value
	 * is the default value, corrected by CADSEg to match all the constraints.
	 * 
	 * @param tp
	 *            the test parameter
	 * @return the corrected def val
	 */
	protected KeyValue getCorrectedNewVal(GTTestParameter tp) {
		return tp.adaptedValue(tp.getValue("newValue"));
	}

	/**
	 * Gets the initial visual value.
	 * 
	 * @param tp
	 *            the test parameter
	 * @return a string or a list of string
	 */
	protected Object getInitialValue(GTTestParameter tp) {

		boolean isList = tp.getBoolean("list");

		if (isList) {
			return new Object[] {};
		} else {
			return getCorrectedDefVal(tp).value;
		}
	}

	/**
	 * Gets the final model value.
	 * 
	 * @param tp
	 *            the test parameter
	 * @return the final model value
	 */
	protected Object getFinalValue(GTTestParameter tp) {

		boolean sicp = tp.getBoolean("sicp");
		boolean isList = tp.getBoolean("list");
		KeyValue newKv = tp.getValue("newValue");

		if (isList) { // def val is ignored with list attributes
			if (sicp && newKv != null && tp.isValidValue(newKv.value)) {
				return new Object[] { newKv.value };
			} else {
				return new Object[] {};
			}
		} else {
			if (sicp && newKv != null && tp.isValidValue(newKv.value)) {
				return getCorrectedNewVal(tp).value;
			} else {
				return getCorrectedDefVal(tp).value;
			}
		}
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
				return tp.isValidValue(tp.getValue("newValue").value);
			} else {
				return tp.isValidValue(tp.getValue("defVal").value);
			}
		} else {
			return true;
		}
	}

	/**
	 * Method called at the beginning of a test in execution phase.
	 * 
	 * @param tp
	 *            the test parameter
	 */
	protected void preExecute(GTTestParameter tp) {
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

		KeyValue newValue = tp.getValue("newValue");

		preExecute(tp);

		/* ============= */
		/* Creation page */
		/* ============= */

		GTCadseShell shell = initCreationPage(tp);
		shell.findCadseFieldName().typeText(getInstanceName(tp));
		UUID id = shell.findCadseFieldName().getRunningField().getSwtUiplatform().getItem().getId();
		displayAttributeCreationPage(tp, shell);

		// is field present
		boolean isFieldPresent = shell.fieldExists(getAttributeName(tp));
		assertEquals("Presence of the attribute field is not as expected for #" + tp.testNumber, fieldInCP,
				isFieldPresent);

		// initial value
		if (fieldInCP) {
			Object expected = getInitialValue(tp);

			Object attrDefVal = shell.findCadseField(getAttributeName(tp)).getAttribute().getDefaultValue();
			assertEqualsListValues("Initial default value error for #" + tp.testNumber, expected, attrDefVal);

			Object actualVisual = shell.findCadseField(getAttributeName(tp)).getValue();
			assertEqualsListValues("Initial visual value error for #" + tp.testNumber, expected, actualVisual);

			Object actualModel = shell.findCadseField(getAttributeName(tp)).getModelValue();
			assertEqualsListValues("Initial model value error for #" + tp.testNumber, expected, actualModel);
		}

		// New Attribute Value
		if (fieldInCP && newValue != null) {

			boolean actualSuccess = setValues(shell, newValue);
			boolean expectedSuccess = tp.isSettableValue(newValue);
			assertEquals("Success or failure is not as expected for #" + tp.testNumber, expectedSuccess, actualSuccess);

			// setting the new value has failed, as expected
			if (!actualSuccess) {
				shell.close(GTEclipseConstants.CANCEL_BUTTON);
				return;
			}
		}

		// CHANGES FOCUS!!!
		shell.findButton("Cancel").setFocus();

		// final model value (okButtonActivated is important! if the value is
		// not correct, the previous correct
		// model value (default value) is locked even if the field displays
		// another value.
		if (fieldInCP && newValue != null && tp.isValidValue(getFinalValue(tp))) {
			Object expected = getFinalValue(tp);
			Object actual = shell.findCadseField(getAttributeName(tp)).getModelValue();
			assertEqualsListValues("Final model value error for #" + tp.testNumber, expected, actual);
		}

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

		/* ============== */
		/* Model checking */
		/* ============== */

		if (tp.isAttributeCreationSuccess()) {
			modelChecking(tp, id);
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
			displayAttributeModificationPage(tp);
			Object expectedGraphical = getFinalValue(tp);
			Object actualGraphical = propertiesView.findCadseField(getAttributeName(tp)).getValue();
			assertEqualsListValues("Error in graphical modification page for #" + tp.testNumber, expectedGraphical,
					actualGraphical);

			Object expectedModel = getFinalValue(tp);
			Object actualModel = propertiesView.findCadseField(getAttributeName(tp)).getModelValue();
			assertEqualsListValues("Error in model modification page for #" + tp.testNumber, expectedModel, actualModel);
		}
	}

	/**
	 * Check the model value using API. To be used after instance creation.
	 * 
	 * @param tp
	 *            the Test Parameter
	 * @param id
	 *            the instance id
	 */
	protected abstract void modelChecking(GTTestParameter tp, UUID id);

	/**
	 * Displays the attribute creation page when creation wizard is shown.
	 * 
	 * @param tp
	 *            the Test Parameter
	 * @param shell
	 */
	protected void displayAttributeCreationPage(GTTestParameter tp, GTCadseShell shell) {
		// already displayed by default!
	}

	/**
	 * Displays the attribute modification page when property page is shown.
	 * 
	 * @param tp
	 *            the Test Parameter
	 */
	protected void displayAttributeModificationPage(GTTestParameter tp) {
		// already displayed by default!
	}

	/**
	 * Starts instance creation by displaying creation page and filling the
	 * instance name.
	 * 
	 * @param tp
	 * @return
	 */
	protected abstract GTCadseShell initCreationPage(GTTestParameter tp);
}
