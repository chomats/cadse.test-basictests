package fr.imag.adele.cadse.test.basictests.cannotbeundefined;

import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;
import org.junit.Test;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseFactory;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseShell;
import fr.imag.adele.graphictests.cadse.test.GTCadseTestCase;
import fr.imag.adele.graphictests.gttree.GTTreePath;

/**
 * Performs a test on the attributes, combining the following properties.
 * <ol>
 * <li>Cannot be undefined</li>
 * <li>Default value</li>
 * <li>Must be initialized</li>
 * </ol>
 */
public class CannotBeUndefined_tc_execution extends GTCadseTestCase {

	/**
	 * Selects the CADSE to be executed and closes unless views.
	 */
	@Test
	public void test_preparation() throws Exception {

		selectCadses("Cadse Model.Workspace.CADSE_CannotBeUndefined");
		welcomeView.close();
		workspaceView.show();
	}

	@Test
	public void test_() throws Exception {
		numericalAttrCommon("long");
	}

	/**
	 * Performs the test on the boolean attribute.
	 * 
	 * @throws Exception
	 */
	@Test
	public void test_bool() throws Exception {
		verif_test_ok("bool", 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
	}

	/**
	 * Performs the test on the double attribute.
	 * 
	 * @throws Exception
	 */
	@Test
	public void test_double() throws Exception {
		numericalAttrCommon("double");
	}

	/**
	 * Performs the test on the int attribute.
	 * 
	 * @throws Exception
	 */
	@Test
	public void test_int() throws Exception {
		numericalAttrCommon("int");
	}

	/**
	 * Performs the test on the long attribute.
	 * 
	 * @throws Exception
	 */
	@Test
	public void test_long() throws Exception {
		numericalAttrCommon("long");
	}

	/**
	 * Performs the test on the string attribute.
	 * 
	 * @throws Exception
	 */
	@Test
	public void test_string() throws Exception {
		// FIXME to be implemented
	}

	/**
	 * Performs the test on the enum attribute.
	 * 
	 * @throws Exception
	 */
	@Test
	public void test_enum() throws Exception {
		// FIXME to be implemented
	}

	/**
	 * Assert a set of basic items can be created.
	 * 
	 * @param prefix
	 *            the prefix used to compute the name of the item.
	 * @param indexes
	 *            the indexes used to compute the name of the item.
	 * @throws Exception
	 */
	private void verif_test_ok(String prefix, int... indexes) {
		for (int i : indexes) {
			createBasicItem(workspaceView, null, "my_" + prefix + i, "instance_" + prefix + i, new GTTreePath(
					"instance_" + prefix + i));
		}
	}

	/**
	 * Performs a set of tests on a numerical attribute.
	 * 
	 * @param typePrefix
	 *            the prefix used to match item type.
	 */
	private void numericalAttrCommon(String typePrefix) {

		String str123;
		String str456;

		if (typePrefix.equals("int") || typePrefix.equals("long")) {
			str123 = "123";
			str456 = "456";
		}
		else if (typePrefix.equals("str")) {
			str123 = "str";
			str456 = "hello world!";
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
			String currentValue = shell.findTextWithLabel(attrName).getText();
			assertEquals("Assert failed : Type=" + typeName + " instance=" + instanceName, fieldValue, currentValue);
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
