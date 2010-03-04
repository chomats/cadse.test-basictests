package fr.imag.adele.cadse.test.basictests.basicproperties;

import org.junit.Test;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseFactory;
import fr.imag.adele.graphictests.cadse.test.GTCadseTestCase;
import fr.imag.adele.graphictests.gtworkbench_part.GTShell;

/**
 * Tests basic properties
 * <ul>
 * <li>name</li>
 * <li>default value</li>
 * <li>hidden in computed pages</li>
 * <li>must be initialized</li>
 * <li>is list</li>
 * </ul>
 */
public class BasicProperties_tc_execution extends GTCadseTestCase {

	/**
	 * Selects the CADSE to be executed and closes unless views.
	 */
	@Test
	public void test_preparation() throws Exception {

		selectCadses("Cadse Model.Workspace.CADSE_BasicProperties");
		welcomeView.close();
		workspaceView.show();
	}

	/**
	 * Tests boolean basic properties.
	 * <ul>
	 * <li>name</li>
	 * <li>default value</li>
	 * <li>hidden in computed pages</li>
	 * <li>must be initialized</li>
	 * <li>is list</li>
	 * </ul>
	 * 
	 * @throws Exception
	 */
	@Test
	public void test_bool() throws Exception {
		commonAttributeTest("my_bool_type", "my_bool_instance", true, false, "true", true);
	}

	/**
	 * Tests double basic properties.
	 * <ul>
	 * <li>name</li>
	 * <li>default value</li>
	 * <li>hidden in computed pages</li>
	 * <li>must be initialized</li>
	 * <li>is list</li>
	 * </ul>
	 * 
	 * @throws Exception
	 */
	@Test
	public void test_double() throws Exception {
		commonAttributeTest("my_double_type", "my_double_instance", "123.0", "", "456.0", "789.0");
	}

	/**
	 * Tests integer basic properties.
	 * <ul>
	 * <li>name</li>
	 * <li>default value</li>
	 * <li>hidden in computed pages</li>
	 * <li>must be initialized</li>
	 * <li>is list</li>
	 * </ul>
	 * 
	 * @throws Exception
	 */
	@Test
	public void test_integer() throws Exception {
		commonAttributeTest("my_int_type", "my_int_instance", "123", "", "456", "789");
	}

	/**
	 * Tests long basic properties.
	 * <ul>
	 * <li>name</li>
	 * <li>default value</li>
	 * <li>hidden in computed pages</li>
	 * <li>must be initialized</li>
	 * <li>is list</li>
	 * </ul>
	 * 
	 * @throws Exception
	 */
	@Test
	public void test_long() throws Exception {
		commonAttributeTest("my_long_type", "my_long_instance", "123.0", "", "456.0", "789.0");
	}

	/**
	 * Tests string basic properties.
	 * <ul>
	 * <li>name</li>
	 * <li>default value</li>
	 * <li>hidden in computed pages</li>
	 * <li>must be initialized</li>
	 * <li>is list</li>
	 * </ul>
	 * 
	 * @throws Exception
	 */
	@Test
	public void test_string() throws Exception {
		commonAttributeTest("my_string_type", "my_string_instance", "def_val", "", "value1", "new_value");
	}

	/**
	 * Tests enum basic properties.
	 * <ul>
	 * <li>name</li>
	 * <li>default value</li>
	 * <li>hidden in computed pages</li>
	 * <li>must be initialized</li>
	 * <li>is list</li>
	 * </ul>
	 * 
	 * @throws Exception
	 */
	@Test
	public void test_enum() throws Exception {
		enumAttributeTest("my_enum_type", "my_enum_instance", "two", "", "three", "three");
	}

	/**
	 * A common method used by all attributes tests.
	 * 
	 * @param typeName
	 *            the name of the item type.
	 * @param instanceName
	 *            the name for the new instance.
	 * @param defaultValue
	 *            the default value which should be found
	 * @param emptyValue
	 *            the value to match an empty value
	 * @param listValue
	 *            a value to be added in a list
	 * @param newValue
	 *            a new value
	 */
	private void commonAttributeTest(String typeName, String instanceName, Object defaultValue, Object emptyValue,
			String listValue, Object newValue) {

		/* ============== */
		/* Creation page */
		/* ============== */

		workspaceView.contextMenuNew(typeName).click();
		GTShell shell = new GTShell(typeName);

		// name
		GTCadseFactory.findCadseField(shell, CadseGCST.ITEM_at_NAME_).typeText(instanceName);

		// Default value
		assertEquals(emptyValue, GTCadseFactory.findCadseField(shell, "no_default_value").getValue());
		assertEquals(defaultValue, GTCadseFactory.findCadseField(shell, "with_default_value").getValue());

		// Hidden in computed pages
		// Must be initialized
		// Is List
		commonCreationHiddenMustBeIsList(shell, listValue, newValue);

		shell.close();

		/* ============= */
		/* Property page */
		/* ============= */

		workspaceView.selectNode(instanceName);
		propertiesView.showTab(typeName);

		// Name
		assertEquals(instanceName, GTCadseFactory.findCadseField(propertiesView, CadseGCST.ITEM_at_NAME_).getText());

		// Default value
		assertEquals(emptyValue, GTCadseFactory.findCadseField(propertiesView, "no_default_value").getValue());
		assertEquals(defaultValue, GTCadseFactory.findCadseField(propertiesView, "with_default_value").getValue());

		// Hidden in computed pages
		// Must be initialized
		// Is List
		commonModificationHiddenMustBeIsList(listValue, newValue);
	}

	/**
	 * Performs a set of tests on the enum attribute.
	 * 
	 * @param typeName
	 *            the name of the item type.
	 * @param instanceName
	 *            the name for the new instance.
	 * @param defaultValue
	 *            the default value which should be found
	 * @param emptyValue
	 *            the value to match an empty value
	 * @param listValue
	 *            a value to be added in a list
	 * @param newValue
	 *            a new value
	 */
	private void enumAttributeTest(String typeName, String instanceName, Object defaultValue, Object emptyValue,
			String listValue, Object newValue) {

		/* ============== */
		/* Creation page */
		/* ============== */

		workspaceView.contextMenuNew(typeName).click();
		GTShell shell = new GTShell(typeName);

		// name
		GTCadseFactory.findCadseField(shell, CadseGCST.ITEM_at_NAME_).typeText(instanceName);

		// Hidden in computed pages
		// Must be initialized
		// Is List
		commonCreationHiddenMustBeIsList(shell, listValue, newValue);

		shell.close();

		/* ============= */
		/* Property page */
		/* ============= */

		workspaceView.selectNode(instanceName);
		propertiesView.showTab(typeName);

		// Name
		assertEquals(instanceName, GTCadseFactory.findCadseField(propertiesView, CadseGCST.ITEM_at_NAME_).getText());

		// Hidden in computed pages
		// Must be initialized
		// Is List
		commonModificationHiddenMustBeIsList(listValue, newValue);
	}

	/**
	 * Performs tests with Hidden in computed pages, must be initialized and isList attributes in creation pages.
	 * 
	 * @param listValue
	 *            the list value
	 * @param newValue
	 *            the new value
	 * @param shell
	 *            the shell
	 */
	private void commonCreationHiddenMustBeIsList(GTShell shell, String listValue, Object newValue) {

		// Hidden in computed pages and must be initialized
		GTCadseFactory.findCadseField(shell, "notHid_beInit").setValue(newValue);
		assertCadseFieldDoesNotExist(shell, "notHid_notInit", failingAssertTimeout);
		assertCadseFieldDoesNotExist(shell, "hid_notInit", failingAssertTimeout);
		assertCadseFieldDoesNotExist(shell, "hid_beInit", failingAssertTimeout);

		// List
		shell.findButton("Add...").click();
		GTShell shell2 = new GTShell("");
		shell2.findText().typeText(listValue);
		shell2.close();

		GTCadseFactory.findCadseField(shell, "not_list").setValue(newValue);
	}

	/**
	 * Performs tests with Hidden in computed pages, must be initialized and isList attributes in modification pages.
	 * 
	 * @param listValue
	 *            a value to be found in the list
	 * @param newValue
	 *            a new value
	 */
	private void commonModificationHiddenMustBeIsList(String listValue, Object newValue) {

		// Hidden in computed pages and must be initialized
		assertEquals(newValue, GTCadseFactory.findCadseField(propertiesView, "notHid_beInit").getValue());
		GTCadseFactory.findCadseField(propertiesView, "notHid_notInit").setValue(newValue);
		assertCadseFieldDoesNotExist(propertiesView, "hid_notInit", failingAssertTimeout);
		assertCadseFieldDoesNotExist(propertiesView, "hid_beInit", failingAssertTimeout);

		// List
		assertEquals(newValue, GTCadseFactory.findCadseField(propertiesView, "not_list").getValue());
		propertiesView.findTree().selectNode(listValue);
	}
}
