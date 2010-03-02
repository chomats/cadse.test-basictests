package fr.imag.adele.cadse.test.basictests.cannotbeundefined;

import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;
import org.junit.Test;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseFactory;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseShell;
import fr.imag.adele.graphictests.cadse.test.GTCadseTestCase;
import fr.imag.adele.graphictests.gttree.GTTreePath;

public class CannotBeUndefined_tc_execution extends GTCadseTestCase {

	@Test
	public void test_preparation() throws Exception {

		selectCadses("Cadse Model.Workspace.CADSE_CannotBeUndefined");
		welcomeView.close();
		workspaceView.show();
	}

	@Test
	public void test_attr_bool() throws Exception {
		verif_test_ok("bool", 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
	}

	@Test
	public void test_numerical_attr() throws Exception {

		numericalAttrCommon("int");
		numericalAttrCommon("double");
		numericalAttrCommon("long");
	}

	private void verif_test_ok(String prefix, int... indexes) {
		for (int i : indexes) {
			createBasicItem(workspaceView, null, "my_" + prefix + i, "instance_" + prefix + i, new GTTreePath(
					"instance_" + prefix + i));
		}
	}

	private void numericalAttrCommon(String typePrefix) {

		String str123;
		String str456;

		if (typePrefix.equals("int")) {
			str123 = "123";
			str456 = "456";
		}
		else {
			str123 = "123.0";
			str456 = "456.0";
		}

		int i = 0;
		createNumericalAttr(typePrefix, 1, ++i, str123, str456, true, str456, failingAssertTimeout);
		createNumericalAttr(typePrefix, 1, ++i, str123, "", false, null, failingAssertTimeout);
		createNumericalAttr(typePrefix, 2, ++i, null, null, true, str123, failingAssertTimeout);
		createNumericalAttr(typePrefix, 3, ++i, "", str456, true, str456, failingAssertTimeout);
		createNumericalAttr(typePrefix, 3, ++i, "", "", false, null, failingAssertTimeout);
		createNumericalAttr(typePrefix, 4, ++i, null, null, false, null, failingAssertTimeout);
		createNumericalAttr(typePrefix, 5, ++i, str123, str456, true, str456, failingAssertTimeout);
		createNumericalAttr(typePrefix, 5, ++i, str123, "", true, "", failingAssertTimeout);
		createNumericalAttr(typePrefix, 6, ++i, null, null, true, str123, failingAssertTimeout);
		createNumericalAttr(typePrefix, 7, ++i, "", str456, true, str456, failingAssertTimeout);
		createNumericalAttr(typePrefix, 7, ++i, "", "", true, "", failingAssertTimeout);
		createNumericalAttr(typePrefix, 8, ++i, null, null, true, "", failingAssertTimeout);
	}

	/**
	 * Test for integer attributes.
	 * 
	 * @param typeNumber
	 *            the number of the int type to be created
	 * @param instanceNumber
	 *            the number of this instance
	 * @param fieldValue
	 *            the value which should be in the field (including ""), or null, if no field
	 * @param newValue
	 *            the new value for the field, including ""
	 * @param finishOK
	 *            if the ok button should be available
	 * @param modifPageValue
	 *            the value of the integer attribute, in the modification page
	 * @param timeout
	 *            the timeout for the actions which are supposed to fail
	 */
	private void createNumericalAttr(String typePrefix, int typeNumber, int instanceNumber, String fieldValue,
			String newValue, boolean finishOK, String modifPageValue, long timeout) {

		String typeName = "my_" + typePrefix + typeNumber;
		String instanceName = "instance_" + typePrefix + instanceNumber;
		String attrName = typePrefix + "_attr";
		long curentTimeout = SWTBotPreferences.TIMEOUT;

		workspaceView.contextMenuNew(typeName).click();
		GTCadseShell shell = new GTCadseShell(typeName);
		GTCadseFactory.findCadseField(shell, CadseGCST.ITEM_at_NAME_).typeText(instanceName);

		/* Field displayed */
		if (fieldValue != null) {
			assertEquals(fieldValue, shell.findTextWithLabel(attrName).getText());
		}
		else {
			assertTextFieldDoesNotExist(shell, attrName, timeout);
		}

		/* New value */
		if (newValue != null) {
			shell.findTextWithLabel(attrName).typeText(newValue);
		}

		/* FinishOK */
		try {
			Thread.sleep(200);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}

		if (finishOK == true) {
			shell.close();
			workspaceView.selectNode(instanceName);
		}
		else {
			try {
				SWTBotPreferences.TIMEOUT = timeout;
				shell.close();
				SWTBotPreferences.TIMEOUT = curentTimeout;
				fail("The OK button shouldn't be activated.");
			}
			catch (Exception e) {
				// success
				SWTBotPreferences.TIMEOUT = curentTimeout;
			}
		}

		/* Value in property page */
		if (modifPageValue != null) {
			workspaceView.selectNode(instanceName);
			propertiesView.showTab(typeName);
			assertEquals(modifPageValue, propertiesView.findTextWithLabel(attrName).getText());
		}
	}
}
