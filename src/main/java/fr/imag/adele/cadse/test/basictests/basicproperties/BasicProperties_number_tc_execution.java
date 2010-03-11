package fr.imag.adele.cadse.test.basictests.basicproperties;

import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseFactory;
import fr.imag.adele.graphictests.cadse.test.GTCadseTestCase;
import fr.imag.adele.graphictests.gtworkbench_part.GTShell;

/**
 * Helper class for attribute testing during execution.
 */
public class BasicProperties_number_tc_execution extends GTCadseTestCase {

	/**
	 * Compute parameters to perform a test with numerical attributes
	 * 
	 * @param type
	 *            the name of the numerical typeto be tested
	 * @param instanceNumber
	 *            the instance number
	 * @param typeNumber
	 *            the type number
	 * @param initialVisualValueNotNull
	 *            the not null default value used in the test
	 * @param initialModelValueNotNull
	 *            the model value, corresponding to the initialVisualValueNotNull
	 * @param newVisualValue
	 *            a new visual value
	 * @param newModelValue
	 *            the model value, corresponding to the newVisualValue
	 */
	protected void computeParameters(String type, int instanceNumber, int typeNumber, String initialVisualValueNotNull,
			Object initialModelValueNotNull, String newVisualValue, Double newModelValue) {

		// The name of the instance to be created
		String instanceName = "instance_" + instanceNumber;

		// The name of the type of the instance
		String typeName = "my_" + type + typeNumber;

		// The attribute field constant name
		String fieldName = type + "_attr";

		// True if the attribute field should be displayed in the creation page
		boolean fieldInCP = (typeNumber <= 4) || (typeNumber >= 9 && typeNumber <= 12);

		// The value visible in the text field when creation dialog starts
		String initialVisualValue = typeNumber <= 8 ? "" : initialVisualValueNotNull;

		// The value in the model when creation dialog starts
		Object initialModelValue = typeNumber <= 8 ? null : initialModelValueNotNull;

		// The final modeled value
		Object fmv = fieldInCP ? newModelValue : initialModelValue;

		// The cannot be undefined / null flag
		boolean cbu = (typeNumber % 2) == 1;

		// True if the ok/finish button is activated
		boolean okButtonActivated = cbu ? (fmv != null) : true;

		// True if the attribute field should be displayed in the modification page
		int tmp = (typeNumber + 1) / 2;
		boolean fieldInMP = (tmp == 1);

		// The value displayed in the properties page
		String propertiesValue = fieldInCP ? newVisualValue : initialVisualValue;

		// The model value in the properties page
		Object propertiesModelValue = fieldInCP ? newModelValue : initialModelValue;

		// Here we are! Let's call the test himself :-)
		commonAttributeTest(instanceName, typeName, fieldName, fieldInCP, initialVisualValue, initialModelValue,
				newVisualValue, newModelValue, okButtonActivated, fieldInMP, propertiesValue, propertiesModelValue);
	}

	/**
	 * @param instanceName
	 *            The name of the instance to be created
	 * @param typeName
	 *            The name of the type of the instance
	 * @param fieldName
	 *            The attribute field constant name
	 * @param fieldInCP
	 *            True if the attribute field should be displayed in the creation page
	 * @param initialVisualValue
	 *            The value visible in the text field when creation dialog starts
	 * @param initialModelValue
	 *            The value in the model when creation dialog starts
	 * @param newVisualValue
	 *            A new value for the field attribute
	 * @param newModelValue
	 *            The new value, in the model
	 * @param okButtonActivated
	 *            True if the ok/finish button is activated
	 * @param fieldInMP
	 *            True if the attribute field should be displayed in the modification page
	 * @param propertiesValue
	 *            The value displayed in the properties page
	 * @param propertiesModelValue
	 *            The model value in the properties page
	 */
	protected void commonAttributeTest(String instanceName, String typeName, String fieldName, boolean fieldInCP,
			String initialVisualValue, Object initialModelValue, String newVisualValue, Object newModelValue,
			boolean okButtonActivated, Boolean fieldInMP, String propertiesValue, Object propertiesModelValue) {

		/* ============== */
		/* Creation page */
		/* ============== */

		workspaceView.contextMenuNew(typeName).click();
		GTShell shell = new GTShell(typeName);

		// is field present
		boolean isFieldPresent = true;
		try {
			GTCadseFactory.findCadseField(shell, fieldName);
		}
		catch (Exception e) {
			isFieldPresent = false;
		}
		assertEquals("Presence of the attribute field is not as expected", fieldInCP, isFieldPresent);

		// initial visual value
		if (fieldInCP) {
			assertEquals("Initial visual value error", initialVisualValue, GTCadseFactory.findCadseField(shell,
					fieldName).getValue());
		}

		// initial model value
		if (fieldInCP) {
			assertEquals("Initial model value error", initialModelValue, GTCadseFactory
					.findCadseField(shell, fieldName).getModelValue());
		}

		// New Attribute Value
		if (fieldInCP && newVisualValue != null) {
			GTCadseFactory.findCadseField(shell, fieldName).typeText(newVisualValue);
		}

		// name + CHANGES FOCUS!!!
		GTCadseFactory.findCadseField(shell, CadseGCST.ITEM_at_NAME_).typeText(instanceName);

		// final visual value
		if (fieldInCP && newVisualValue != null) {
			assertEquals("Error with final visual value", newVisualValue, GTCadseFactory.findCadseField(shell,
					fieldName).getText());
		}

		// final model value (okButtonActivated is important! if the value is not correct, the previous correct model
		// value (default value) is locked even if the field displays another value.
		if (fieldInCP && newVisualValue != null && okButtonActivated) {
			assertEquals("Final model value error", newModelValue, GTCadseFactory.findCadseField(shell, fieldName)
					.getModelValue());
		}

		// Waits until refresh
		try {
			Thread.sleep(SWTBotPreferences.DEFAULT_POLL_DELAY);
		}
		catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		// Closes shell
		if (okButtonActivated) {
			shell.close();
		}
		else {
			try {
				shell.close(failingAssertTimeout);
				fail("OK button is activated whereas it shouldn't");
			}
			catch (Exception e) {
				// SUCCESS
			}
			return;
		}

		/* ============= */
		/* Property page */
		/* ============= */

		workspaceView.selectNode(instanceName);
		propertiesView.showTab(typeName);

		// Name
		assertEquals(instanceName, GTCadseFactory.findCadseField(propertiesView, CadseGCST.ITEM_at_NAME_).getText());

		// Field value
		if (fieldInMP) {
			assertEquals(propertiesValue, GTCadseFactory.findCadseField(propertiesView, fieldName).getText());
			assertEquals(propertiesModelValue, GTCadseFactory.findCadseField(propertiesView, fieldName).getModelValue());
		}
	}
}
