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

		final boolean inCP = true;
		final boolean inMP = true;
		final boolean notInCP = false;
		final boolean notInMP = false;
		final boolean closeOK = true;
		final boolean closeCancel = false;

		int i = 0;
		doubleTest(i++, 1, notInCP, "", null, null, null, closeCancel, null);

	}

	private void doubleTest(int instanceNumber, int typeNumber, boolean fieldInCP, String initialVisualValue,
			Double initialModelValue, String newAttributeValue, Double newModelValue, boolean okButtonActivated,
			Boolean fieldInMP) {

		String type = "double";
		String instanceName = "instance_" + instanceNumber;
		String typeName = "my_" + type + typeNumber;
		String fieldName = type + "_attr";

		commonAttributeTest(instanceName, typeName, fieldName, fieldInCP, initialVisualValue, initialModelValue,
				newAttributeValue, newModelValue, okButtonActivated, fieldInMP);
	}

	private void commonAttributeTest(String instanceName, String typeName, String fieldName, boolean fieldInCP,
			String initialVisualValue, Object initialModelValue, String newAttributeValue, Object newModelValue,
			boolean okButtonActivated, Boolean fieldInMP) {

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
		if (fieldInCP && newAttributeValue != null) {
			GTCadseFactory.findCadseField(shell, fieldName).typeText(newAttributeValue);
		}

		// name + CHANGES FOCUS!!!
		GTCadseFactory.findCadseField(shell, CadseGCST.ITEM_at_NAME_).typeText(instanceName);

		// final visual value
		if (fieldInCP && newAttributeValue != null) {
			assertEquals("Error with final visual value", newAttributeValue, GTCadseFactory.findCadseField(shell,
					fieldName).getText());
		}

		// final model value
		if (fieldInCP && newAttributeValue != null) {
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
				shell.close();
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
			assertEquals(newAttributeValue, GTCadseFactory.findCadseField(propertiesView, fieldName).getText());
			assertEquals(newModelValue, GTCadseFactory.findCadseField(propertiesView, fieldName).getModelValue());
		}
	}
}
