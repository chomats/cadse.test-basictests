package fr.imag.adele.cadse.test.basictests.cannotbeundefined;

import org.junit.Test;

import fr.imag.adele.cadse.cadseg.managers.CadseDefinitionManager;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseFactory;
import fr.imag.adele.graphictests.cadse.test.GTCadseRTConstants;
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
public class CannotBeUndefined_tc_CADSEg extends GTCadseTestCase {

	protected final String cadse_name = "CADSE_CannotBeUndefined";
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
	 * Performs a test on boolean attribute, combining the following properties.
	 * <ol>
	 * <li>Cannot be undefined</li>
	 * <li>Default value</li>
	 * <li>Must be initialized</li>
	 * </ol>
	 */
	@Test
	public void test_bool() throws Exception {

		// ________==============================================
		// ________| cannot be undef | def value | must be init
		// ======================================================
		// = bool1 | ______true_____ | ___true__ | true
		// ======================================================
		// = bool2 | ______true_____ | ___true__ | false
		// ======================================================
		// = bool3 | ______true_____ | __false__ | true
		// ======================================================
		// = bool4 | ______true_____ | __false__ | false
		// ======================================================
		// = bool5 | ______true_____ | _________ | true
		// ======================================================
		// = bool6 | ______true_____ | _________ | false
		// ======================================================
		// = bool7 | ______false____ | ___true__ | true
		// ======================================================
		// = bool8 | ______false____ | ___true__ | false
		// ======================================================
		// = bool9 | ______false____ | __false__ | true
		// ======================================================
		// = bool10 | ______false___ | __false__ | false
		// ======================================================
		// = bool11 | ______false___ | _________ | true
		// ======================================================
		// = bool12 | ______false___ | _________ | false
		// ======================================================

		boolean[] tabUndef = { true, false };
		String[] tabDefVal = { "true", "false", "" };
		boolean[] tabInit = { true, false };

		createItems(CadseGCST.BOOLEAN, "bool", null, tabUndef, tabDefVal, tabInit);
	}

	/**
	 * Performs a test on double attribute, combining the following properties.
	 * <ol>
	 * <li>Cannot be undefined</li>
	 * <li>Default value</li>
	 * <li>Must be initialized</li>
	 * </ol>
	 */
	@Test
	public void test_double() throws Exception {
		common_attr_test(CadseGCST.DOUBLE, "double");
	}

	/**
	 * Performs a test on integer attribute, combining the following properties.
	 * <ol>
	 * <li>Cannot be undefined</li>
	 * <li>Default value</li>
	 * <li>Must be initialized</li>
	 * </ol>
	 */
	@Test
	public void test_int() throws Exception {
		common_attr_test(CadseGCST.INTEGER, "int");
	}

	/**
	 * Performs a test on long attribute, combining the following properties.
	 * <ol>
	 * <li>Cannot be undefined</li>
	 * <li>Default value</li>
	 * <li>Must be initialized</li>
	 * </ol>
	 */
	@Test
	public void test_long() throws Exception {
		common_attr_test(CadseGCST.LONG, "long");
	}

	/**
	 * Performs a test on string attribute, combining the following properties.
	 * <ol>
	 * <li>Cannot be undefined</li>
	 * <li>Default value</li>
	 * <li>Must be initialized</li>
	 * </ol>
	 */
	@Test
	public void test_string() throws Exception {
		common_attr_test(CadseGCST.STRING, "str");
	}

	/**
	 * Performs a test on enum attribute, combining the following properties.
	 * <ol>
	 * <li>Cannot be undefined</li>
	 * <li>Default value = must be always set! (test performed by the basic properties test)</li>
	 * <li>Must be initialized</li>
	 * </ol>
	 */
	@Test
	public void test_enum() throws Exception {
		String[] tabDefVal = { "two" };
		boolean[] tabUndef = { true, false };
		boolean[] tabInit = { true, false };

		createEnumType(data_model, "my_enum", "one", "two", "three");
		createItems(CadseGCST.ENUM, "enum", "my_enum", tabUndef, tabDefVal, tabInit);
	}

	/**
	 * Checks that the CADSE generated by this test has no compilation error.
	 * 
	 * @throws Exception
	 */
	@Test
	public void test_check_compilation() throws Exception {
		checkCompilationErrors(workspaceView, cadse_model);
	}

	/**
	 * Common method to perform test on numerical attributes and string attribute.
	 */
	@Test
	private void common_attr_test(ItemType attr, String prefix) throws Exception {

		// _______=============================================
		// _______| cannot be undef | def value | must be init
		// ====================================================
		// = num1 | _____true______ | ___123___ | true
		// ====================================================
		// = num2 | _____true______ | ___123___ | false
		// ====================================================
		// = num3 | _____true______ | ____""___ | true
		// ====================================================
		// = num4 | _____true______ | ____""___ | false
		// ====================================================
		// = num5 | _____false_____ | ___123___ | true
		// ====================================================
		// = num6 | _____false_____ | ___123___ | false
		// ====================================================
		// = num7 | _____false_____ | ____""___ | true
		// ====================================================
		// = num8 | _____false_____ | ____""___ | false
		// ====================================================

		String[] tabDefVal = { "", "" };
		if (attr == CadseGCST.STRING) {
			tabDefVal[0] = "str";
		}
		else {
			tabDefVal[0] = "123";
		}

		boolean[] tabUndef = { true, false };
		boolean[] tabInit = { true, false };

		createItems(attr, prefix, null, tabUndef, tabDefVal, tabInit);
	}

	/**
	 * A very powerful method used to create attributes combining values available in tables.
	 * 
	 * @param attr
	 *            the type of the attribute to be created
	 * @param prefix
	 *            prefix, used to compute the attribute name.
	 * @param tabUndef
	 *            values for the can be undefined parameter
	 * @param tabDefVal
	 *            values for the default value parameter
	 * @param tabInit
	 *            values for the must be initialized parameter
	 * @param enumType
	 *            the enum type
	 */
	private void createItems(ItemType attr, String prefix, String enumType, boolean[] tabUndef, String[] tabDefVal,
			boolean[] tabInit) {

		int i = 1;
		for (boolean undef : tabUndef) {
			for (String defVal : tabDefVal) {
				for (boolean init : tabInit) {

					/* Item type creation */
					String it_name = "my_" + prefix + i;
					GTTreePath it_path = data_model.concat(it_name);
					createItemType(data_model, it_name, null, notAbstract, root, defaultContent);

					/* Attribute creation */
					String attr_name = prefix + "_attr";
					GTTreePath attr_path = it_path.concat(attr_name);

					// FIXME!!!!!!!
					// createBasicAttribute(it_path, attr, attr_name, enumType, defVal, notHidden, init, notList);

					/* Cannot be undefined attribute */
					try {
						propertiesView.showTab(attr.getDisplayName());
					}
					catch (Exception e) {
						workspaceView.selectNode(attr_path);
						propertiesView.showTab(attr.getDisplayName());
					}
					GTCadseFactory.findCadseField(propertiesView, CadseGCST.ATTRIBUTE_at_CANNOT_BE_UNDEFINED_).check(
							undef);

					/* Increment */
					i++;
				}
			}
		}
	}
}
