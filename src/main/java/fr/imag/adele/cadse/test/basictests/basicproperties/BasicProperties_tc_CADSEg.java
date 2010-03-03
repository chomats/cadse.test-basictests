package fr.imag.adele.cadse.test.basictests.basicproperties;

import org.junit.Test;

import fr.imag.adele.cadse.cadseg.managers.CadseDefinitionManager;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.graphictests.cadse.test.GTCadseRTConstants;
import fr.imag.adele.graphictests.cadse.test.GTCadseTestCase;
import fr.imag.adele.graphictests.gttree.GTTreePath;

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
public class BasicProperties_tc_CADSEg extends GTCadseTestCase {

	protected final String cadse_name = "CADSE_BasicProperties";
	protected final GTTreePath cadse_model = new GTTreePath(cadse_name);
	protected final GTTreePath data_model = cadse_model.concat(CadseDefinitionManager.DATA_MODEL);

	/**
	 * Selects CADSEg in the launcher, and closes useless views.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void test_selection() throws Exception {

		selectCadses(GTCadseRTConstants.CADSEG_MODEL);
		welcomeView.close();
		workspaceView.show();
	}

	/**
	 * Creates a new CADSE definition.
	 * 
	 * @throws Exception
	 */
	@Test
	public void test_cadse_creation() throws Exception {
		createCadseDefinition(cadse_name, "model." + cadse_name);
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
	public void test_bool_basic_properties() throws Exception {
		typeTest("my_bool_type", CadseGCST.BOOLEAN, "true");
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
	public void test_double_basic_properties() throws Exception {
		typeTest("my_double_type", CadseGCST.DOUBLE, "123");
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
	public void test_int_basic_properties() throws Exception {
		typeTest("my_int_type", CadseGCST.INTEGER, "123");
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
	public void test_long_basic_properties() throws Exception {
		typeTest("my_long_type", CadseGCST.LONG, "123");
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
	public void test_string_basic_properties() throws Exception {
		typeTest("my_string_type", CadseGCST.STRING, "def_val");
	}

	/**
	 * Creates an item type and a set of attributes.
	 * 
	 * @param it_name
	 *            the item type name.
	 * @param attr_type
	 *            the kind of attributes to be created.
	 * @param defaultValue
	 *            the default value
	 */
	private void typeTest(String it_name, ItemType attr_type, String defaultValue) {

		final GTTreePath it_path = data_model.concat(it_name);

		createItemType(data_model, it_name, null, notAbstract, root, defaultContent);
		commonAttributesCreation(it_path, attr_type, defaultValue);
	}

	/**
	 * Creates a set of attributes of a given type.
	 * 
	 * @param it_path
	 *            the itemType on which attributes should be created.
	 * @param attr
	 *            The kind of attributes to be created.
	 * @param defaultValue
	 *            the default value
	 */
	private void commonAttributesCreation(GTTreePath it_path, ItemType attr, String defaultValue) {

		// Default value
		createBasicAttribute(it_path, attr, "no_default_value", null, null, notHidden, mustBeInitialized, notList);
		createBasicAttribute(it_path, attr, "with_default_value", null, defaultValue, notHidden, mustBeInitialized,
				notList);

		// Hidden in computed pages and must be initialized
		createBasicAttribute(it_path, attr, "notHid_beInit", null, null, notHidden, mustBeInitialized, notList);
		createBasicAttribute(it_path, attr, "notHid_notInit", null, null, notHidden, notInitialized, notList);
		createBasicAttribute(it_path, attr, "hid_beInit", null, null, hidden, mustBeInitialized, notList);
		createBasicAttribute(it_path, attr, "hid_notInit", null, null, hidden, notInitialized, notList);

		// List
		createBasicAttribute(it_path, attr, "list", null, null, notHidden, mustBeInitialized, isList);
		createBasicAttribute(it_path, attr, "not_list", null, null, notHidden, mustBeInitialized, notList);
	}
}
