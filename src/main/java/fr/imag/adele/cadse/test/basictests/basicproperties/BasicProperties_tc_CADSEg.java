package fr.imag.adele.cadse.test.basictests.basicproperties;

import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;
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
	 * Makes a few things before the test starts.
	 * <ul>
	 * <li>Starts CADSEg</li>
	 * <li>Closes unless views</li>
	 * <li>Creates a new CADSE</li>
	 * </ul>
	 * 
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void test_preparation() throws Exception {

		// Starts CADSEg
		selectCadses(GTCadseRTConstants.CADSEG_MODEL);

		// Closes unless views
		welcomeView.close();
		workspaceView.show();

		// Creates a new CADSE
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
		commonAttributesCreation("my_bool_type", CadseGCST.BOOLEAN, "true");
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
		commonAttributesCreation("my_double_type", CadseGCST.DOUBLE, "123");
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
		commonAttributesCreation("my_int_type", CadseGCST.INTEGER, "123");
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
		commonAttributesCreation("my_long_type", CadseGCST.LONG, "123");
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
		commonAttributesCreation("my_string_type", CadseGCST.STRING, "def_val");
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
	public void test_enum_basic_properties() throws Exception {
		enumCreation("my_enum_type", CadseGCST.ENUM, "two");
	}

	/**
	 * Creates an item type with a set of attributes of a given type.
	 * 
	 * @param it_path
	 *            the itemType on which attributes should be created.
	 * @param attr_type
	 *            The kind of attributes to be created.
	 * @param defaultValue
	 *            the default value
	 */
	private void commonAttributesCreation(String it_name, ItemType attr_type, String defaultValue) {

		// item type creation
		final GTTreePath it_path = data_model.concat(it_name);
		createItemType(data_model, it_name, null, notAbstract, root, defaultContent);

		// Default value
		commonAttributesDefaultValue(it_path, attr_type, defaultValue);

		// Hidden in comuted pages
		// Must be initialized
		// Is List
		hiddenBeInitListAttributesCreation(it_path, attr_type, null, null);
	}

	/**
	 * Creates an enum with a set of attributes.
	 * 
	 * @param it_name
	 *            the name of the enum to be created
	 * @param attr_type
	 *            the enum item type
	 * @param defaultValue
	 *            the default value
	 */
	private void enumCreation(String it_name, ItemType attr_type, String defaultValue) {

		// Item Type creation
		final GTTreePath it_path = data_model.concat(it_name);
		createItemType(data_model, it_name, null, notAbstract, root, defaultContent);

		// Enum Type creation
		String enumTypeName = "the_enum";
		createEnumType(data_model, enumTypeName, "one", "two", "three");

		// Default value
		enumDefaultValue(it_path, enumTypeName);

		// Hidden in comuted pages
		// Must be initialized
		// Is List
		hiddenBeInitListAttributesCreation(it_path, attr_type, enumTypeName, "two");
	}

	/**
	 * Creates a set of attributes to perform test on hidden in comuted pages, must be initialized, is list.
	 * 
	 * @param itPath
	 *            the path to the item type
	 * @param attr_type
	 *            the kind of attribute to be created
	 * @param enumType
	 *            the name of the enum type, if the attr_type is enum
	 * @param defaultValue
	 *            the default value, if the attr_type is enum
	 */
	private void hiddenBeInitListAttributesCreation(GTTreePath itPath, ItemType attr_type, String enumType,
			String defaultValue) {

		// Hidden in computed pages and must be initialized
		createBasicAttribute(itPath, attr_type, "notHid_beInit", enumType, defaultValue, notHidden, mustBeInitialized,
				notList);
		createBasicAttribute(itPath, attr_type, "notHid_notInit", enumType, defaultValue, notHidden, notInitialized,
				notList);
		createBasicAttribute(itPath, attr_type, "hid_beInit", enumType, defaultValue, hidden, mustBeInitialized,
				notList);
		createBasicAttribute(itPath, attr_type, "hid_notInit", enumType, defaultValue, hidden, notInitialized, notList);

		// List
		createBasicAttribute(itPath, attr_type, "list", enumType, defaultValue, notHidden, mustBeInitialized, isList);
		createBasicAttribute(itPath, attr_type, "not_list", enumType, defaultValue, notHidden, mustBeInitialized,
				notList);
	}

	/**
	 * Creates attributes with and without default value.
	 * 
	 * @param it_path
	 *            the path to the item type
	 * @param attr_type
	 *            the kind of attribute to be created
	 * @param defaultValue
	 *            the default value
	 */
	private void commonAttributesDefaultValue(GTTreePath it_path, ItemType attr_type, String defaultValue) {
		createBasicAttribute(it_path, attr_type, "no_default_value", null, null, notHidden, mustBeInitialized, notList);
		createBasicAttribute(it_path, attr_type, "with_default_value", null, defaultValue, notHidden,
				mustBeInitialized, notList);
	}

	/**
	 * Checks that an enum must have a default value and the enum type set at creation time.
	 * 
	 * @param it_path
	 *            the item type path
	 * @param enumTypeName
	 *            the name of the enum type
	 */
	private void enumDefaultValue(GTTreePath it_path, String enumTypeName) {

		long old_timeout = SWTBotPreferences.TIMEOUT;
		SWTBotPreferences.TIMEOUT = failingAssertTimeout;

		// default value and enum type
		try {
			createBasicAttribute(it_path, CadseGCST.ENUM, "no_default_value", null, "one", notHidden,
					mustBeInitialized, notList);
			SWTBotPreferences.TIMEOUT = old_timeout;
			fail("This should have failed!");
		}
		catch (Exception e) {
			System.out.println("SUCCESS : can't create enum without enum type");
		}
		try {
			createBasicAttribute(it_path, CadseGCST.ENUM, "no_default_value", enumTypeName, "", notHidden,
					mustBeInitialized, notList);
			SWTBotPreferences.TIMEOUT = old_timeout;
			fail("This should have failed!");
		}
		catch (Exception e) {
			System.out.println("SUCCESS : can't create enum without default value");
		}

		// restore timeout
		SWTBotPreferences.TIMEOUT = old_timeout;
	}
}
