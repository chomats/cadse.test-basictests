package fr.imag.adele.cadse.test.basictests.basicproperties;

import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseView.propertiesView;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseView.workspaceView;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.notAbstractKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.rootKv;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.createItemType;

import java.util.ArrayList;
import java.util.UUID;

import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
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
	 * Gets the the default value as it appears in the creation page. This value is the default value, corrected by
	 * CADSEg to batch all the constraints.
	 * 
	 * @param tp
	 *            the test parameter
	 * @return the corrected def val
	 */
	protected KeyValue getCorrectedDefVal(GTTestParameter tp) {
		return tp.getValue("defVal");
	}

	/**
	 * Gets the initial visual value.
	 * 
	 * @param tp
	 *            the test parameter
	 * @return a string or a list of string
	 */
	protected KeyValue getInitialValue(GTTestParameter tp) {

		boolean isList = tp.getBoolean("list");
		KeyValue defVal = getCorrectedDefVal(tp);

		if (isList) {
			return new KeyValue(getAttributeName(), new String[] {}, new ArrayList<Object>());
		}
		else {
			return defVal;
		}
	}

	/**
	 * Sets the new graphical value.
	 * 
	 * @param tp
	 *            the test parameter
	 * @param shell
	 *            the shell
	 */
	protected boolean setNewGraphicalValue(GTTestParameter tp, GTCadseShell shell) {

		String newValue = (String) tp.getValue("newValue").visualValue;
		boolean isList = tp.getBoolean("list");

		if (isList) {

			boolean expectedSuccess = !newValue.equals("");

			if (expectedSuccess) {
				shell.findCadseField(getAttributeName()).addValue(newValue);
			}
			else {
				try {
					shell.findCadseField(getAttributeName()).addValue(newValue, GTPreferences.FAILING_ASSERT_TIMEOUT);
					fail("It should be impossible to fill \"" + newValue + "\" for #" + tp.testNumber);
				}
				catch (Exception e) {
					return false; // success : it's impossible to fill a non allowed value
				}
			}
		}
		else {
			shell.findCadseField(getAttributeName()).typeText(newValue);
		}

		return true; // success
	}

	/**
	 * Gets the final model value.
	 * 
	 * @param tp
	 *            the test parameter
	 * @return the final model value
	 */
	protected Object getFinalModelValue(GTTestParameter tp) {

		boolean sicp = tp.getBoolean("sicp");
		boolean isList = tp.getBoolean("list");
		KeyValue newKv = tp.getValue("newValue");
		Object newModelValue = (newKv == null) ? null : newKv.modelValue;

		if (isList) { // def val is ignored with list attributes
			if (sicp && newKv != null && newModelValue != null) {
				return new Object[] { newModelValue };
			}
			else {
				return new Object[] {};
			}
		}
		else {
			if (sicp) {
				if (newKv != null) { // in case graphic = "" and model = null
					return newModelValue;
				}
				else {
					return getCorrectedDefVal(tp).modelValue;
				}
			}
			else {
				return getCorrectedDefVal(tp).modelValue;
			}
		}
	}

	/**
	 * Gets the final graphical value.
	 * 
	 * @param tp
	 *            the test parameter
	 * @return the final graphical value
	 */
	protected Object getFinalGraphicalValue(GTTestParameter tp) {

		boolean sicp = tp.getBoolean("sicp");
		boolean isList = tp.getBoolean("list");

		KeyValue newKv = tp.getValue("newValue");
		Object newGraphicalValue = (newKv == null) ? null : newKv.visualValue;

		if (sicp) {
			if (isList) { // default value is ignored with list
				if (newKv != null && newGraphicalValue != null && !newGraphicalValue.toString().equals("")) {
					return new String[] { newGraphicalValue.toString() };
				}
				else {
					return new String[] {};
				}
			}
			else {
				if (newKv != null) {
					return newGraphicalValue;
				}
				else {
					return getCorrectedDefVal(tp).visualValue;
				}
			}
		}
		else {
			throw new WidgetNotFoundException("No field in this dialog");
		}
	}

	/**
	 * Checks if is ok button is activated.
	 * 
	 * @param tp
	 *            the test parameter
	 * @return true, if is ok button is activated
	 */
	protected boolean isOkButtonActivated(GTTestParameter tp) {

		boolean cbu = tp.getBoolean("cbu");
		boolean isList = tp.getBoolean("list");

		if (isList) {
			return true;
		}
		else {
			return cbu ? (getFinalModelValue(tp) != null) : true;
		}
	}

	/**
	 * Gets the attribute graphical value in the property view.
	 * 
	 * @param tp
	 *            the test parameter
	 * @return the attribute graphical value in the property view.
	 */
	protected Object getPropertiesGraphicalValue(GTTestParameter tp) {

		boolean sicp = tp.getBoolean("sicp");
		boolean simp = tp.getBoolean("simp");
		boolean isList = tp.getBoolean("list");

		if (simp) {
			if (isList) {
				if (sicp) {
					return getFinalGraphicalValue(tp);
				}
				else {
					return new String[] {}; // the default value is ignored for list attributes
				}
			}
			else {
				if (sicp) {
					return getFinalGraphicalValue(tp);
				}
				else {
					return getCorrectedDefVal(tp).visualValue;
				}
			}
		}
		else {
			throw new WidgetNotFoundException("No field in this dialog");
		}
	}

	/**
	 * Gets the attribute model value in the property view.
	 * 
	 * @param tp
	 *            the test parameter
	 * @return the attribute model value in the property view.
	 */
	protected Object getPropertiesModelValue(GTTestParameter tp) {

		boolean sicp = tp.getBoolean("sicp");
		boolean isList = tp.getBoolean("list");

		if (sicp) {
			return getFinalModelValue(tp);
		}
		else {
			if (isList) {
				return new Object[] {}; // default value is ignored with list attributes
			}
			else {
				return getCorrectedDefVal(tp).modelValue;
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
		createItemType(dataModel, getItName(tp), notAbstractKv, rootKv);
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
	 * fr.imag.adele.cadse.test.basictests.testdriver.GTCommonTestDriver#testExecution(fr.imag.adele.cadse.test.basictests
	 * .testdriver.GTTestParameter)
	 */
	@Override
	protected void testExecution(GTTestParameter tp) {

		boolean sicp = tp.getBoolean("sicp");
		boolean simp = tp.getBoolean("simp");

		boolean fieldInCP = sicp && attributeCreationSuccess(tp);
		boolean fieldInMP = simp && attributeCreationSuccess(tp);

		KeyValue newValue = tp.getValue("newValue");

		/* ============= */
		/* Creation page */
		/* ============= */

		workspaceView.contextMenuNew(getItName(tp)).click();
		GTCadseShell shell = new GTCadseShell(getItName(tp));

		// is field present
		boolean isFieldPresent = shell.fieldExists(getAttributeName());
		assertEquals("Presence of the attribute field is not as expected for #" + tp.testNumber, fieldInCP,
				isFieldPresent);

		// initial value
		if (fieldInCP) {
			KeyValue expected = getInitialValue(tp);

			final Object attributeDefaultvalue = shell.findCadseField(getAttributeName()).getAttribute().getDefaultValue();
			assertEqualsListValues("Initial default value error for #" + tp.testNumber, expected.modelValue,
					attributeDefaultvalue);

			Object actualVisual = shell.findCadseField(getAttributeName()).getValue();
			assertEqualsListValues("Initial visual value error for #" + tp.testNumber, expected.visualValue,
					actualVisual);

			Object actualModel = shell.findCadseField(getAttributeName()).getModelValue();
			assertEqualsListValues("Initial model value error for #" + tp.testNumber, expected.modelValue, actualModel);
		}

		// New Attribute Value
		if (fieldInCP && newValue != null) {
			if (!setNewGraphicalValue(tp, shell)) {
				// setting the new value has failed, as expected
				shell.close(GTEclipseConstants.CANCEL_BUTTON);
				return;
			}
		}

		// name + CHANGES FOCUS!!!
		shell.findCadseFieldName().typeText(getInstanceName(tp));

		// final visual value
		if (fieldInCP && newValue != null) {
			Object expected = getFinalGraphicalValue(tp);
			Object actual = shell.findCadseField(getAttributeName()).getValue();
			assertEqualsListValues("Error with final visual value for #" + tp.testNumber, expected, actual);
		}

		// final model value (okButtonActivated is important! if the value is not correct, the previous correct
		// model value (default value) is locked even if the field displays another value.
		if (fieldInCP && newValue != null && isOkButtonActivated(tp)) {
			Object expected = getFinalModelValue(tp);
			Object actual = shell.findCadseField(getAttributeName()).getModelValue();
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
			}
			catch (Exception e) {
				// SUCCESS
			}
			return;
		}

		/* ============== */
		/* Model checking */
		/* ============== */

		if (attributeCreationSuccess(tp)) {
			Item item = CadseCore.getLogicalWorkspace().getItem(id);
			assertNotNull(item);
			IAttributeType<?> attr = item.getType().getAttributeType(getAttributeName());
			assertNotNull(attr);
			Object actualModel = item.getAttribute(attr);
			Object expectedModel = getFinalModelValue(tp);
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
			Object expectedGraphical = getPropertiesGraphicalValue(tp);
			Object actualGraphical = propertiesView.findCadseField(getAttributeName()).getValue();
			assertEqualsListValues("Error in graphical modification page value for #" + tp.testNumber,
					expectedGraphical, actualGraphical);

			Object expectedModel = getPropertiesModelValue(tp);
			Object actualModel = propertiesView.findCadseField(getAttributeName()).getModelValue();
			assertEqualsListValues("Error in model modification page value for #" + tp.testNumber, expectedModel,
					actualModel);
		}
	}
}
