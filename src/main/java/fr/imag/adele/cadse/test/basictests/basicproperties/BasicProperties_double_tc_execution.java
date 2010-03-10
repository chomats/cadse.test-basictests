package fr.imag.adele.cadse.test.basictests.basicproperties;

import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;
import org.junit.Test;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseFactory;
import fr.imag.adele.graphictests.gtworkbench_part.GTShell;

/**
 */
public class BasicProperties_double_tc_execution extends BasicProperties_number_tc_execution {

	/**
	 * Selects the CADSE to be executed and closes unless views.
	 */
	@Test
	public void test_preparation() throws Exception {

		selectCadses("Cadse Model.Workspace.CADSE_BasicProperties_double");
		welcomeView.close();
		workspaceView.show();
	}

	@Test
	public void test_double() throws Exception {

		int instanceNuumber = 1;
		for (int typeNumber = 1; typeNumber <= 16; typeNumber++) {
			System.out.println("Starting Double test part 1 with type number " + typeNumber);
			doubleTest(instanceNuumber++, typeNumber, "456.0", 456d);
			System.out.println("Starting Double test part 2 with type number " + typeNumber);
			doubleTest(instanceNuumber++, typeNumber, "", null);
		}
	}

	private void doubleTest(int instanceNumber, int typeNumber, String newVisualValue, Double newModelValue) {

		String type = "double";

		// The name of the instance to be created
		String instanceName = "instance_" + instanceNumber;

		// The name of the type of the instance
		String typeName = "my_" + type + typeNumber;

		// The attribute field constant name
		String fieldName = type + "_attr";

		// True if the attribute field should be displayed in the creation page
		boolean fieldInCP = (typeNumber <= 4) || (typeNumber >= 9 && typeNumber <= 12);

		// The value visible in the text field when creation dialog starts
		String initialVisualValue = typeNumber <= 8 ? "" : "123.0";

		// The value in the model when creation dialog starts
		Double initialModelValue = typeNumber <= 8 ? null : 123d;

		// The final modeled value
		Double fmv = fieldInCP ? newModelValue : initialModelValue;

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
		Double propertiesModelValue = fieldInCP ? newModelValue : initialModelValue;

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
	private void commonAttributeTest(String instanceName, String typeName, String fieldName, boolean fieldInCP,
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

		// final model value
		if (fieldInCP && newVisualValue != null) {
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
