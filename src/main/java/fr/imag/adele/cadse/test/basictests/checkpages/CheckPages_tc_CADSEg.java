package fr.imag.adele.cadse.test.basictests.checkpages;

import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseView.propertiesView;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseView.workspaceView;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.notAbstractKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.rootKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.withContentKv;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.checkCompilationErrors;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.createItemType;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.sectionLabel;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.selectCadses;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.tabLabel;
import static fr.imag.adele.graphictests.gtworkbench_part.GTView.welcomeView;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import fr.imag.adele.cadse.cadseg.managers.CadseDefinitionManager;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.attribute.IAttributeType;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseField;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseShell;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseWorkbenchPart;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue;
import fr.imag.adele.graphictests.cadse.test.GTCadseRTConstants;
import fr.imag.adele.graphictests.cadse.test.GTCadseTestCase;
import fr.imag.adele.graphictests.gttree.GTTreePath;
import fr.imag.adele.graphictests.test.GTPreferences;

/**
 * Checks whereas the creation and modification pages have the correct layout.
 * The following items are tested:
 * <ul>
 * <li>CADSE definition</li>
 * <li>Item Type</li>
 * <li>Boolean attribute</li>
 * <li>Double attribute</li>
 * <li>Integer attribute</li>
 * <li>Long attribute</li>
 * <li>String attribute</li>
 * <li>Enum Type</li>
 * <li>Enum attribute</li>
 * <li><Link Type/li>
 * <li>Manager</li>
 * <li>Java Project Content Model</li>
 * <li>Java File Content Model</li>
 * <li>Folder Content Model</li>
 * <li>File Content Model</li>
 * </ul>
 */
@RunWith(JUnit4.class)
public class CheckPages_tc_CADSEg extends GTCadseTestCase {

	protected final String cadse_name = "CADSE_CheckPages";
	protected final String item_type_name = "my_item_type";
	protected GTTreePath cadse_model = new GTTreePath(cadse_name);
	protected GTTreePath build_model = cadse_model.concat(CadseDefinitionManager.BUILD_MODEL);
	protected GTTreePath data_model = cadse_model.concat(CadseDefinitionManager.DATA_MODEL);
	protected GTTreePath mapping = cadse_model.concat(CadseDefinitionManager.MAPPING);
	protected GTTreePath it_mit = data_model.concat(item_type_name);

	/**
	 * Makes a few things before the test starts.
	 * <ul>
	 * <li>Starts CADSEg</li>
	 * <li>Closes unless views</li>
	 * </ul>
	 * 
	 * @throws Exception
	 *             the exception
	 */
	@BeforeClass
	public static void createContext() {
		// Starts CADSEg
		selectCadses(GTCadseRTConstants.CADSEG_MODEL);
		welcomeView.close();
		workspaceView.show();
	}

	/**
	 * Tests if the CADSE definition pages (creation and modification) have the
	 * correct layout.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCADSEDefinition() throws Exception {
		String[] expected_creationCST = { "ITEM_at_NAME_", "CADSE_DEFINITION_at_PACKAGENAME_", "CADSE_lt_EXTENDS" };
		Object[] expected_creationVal = { "", "", new Object[] {} };
		String[] expected_modifCST = { "ITEM_at_NAME_", "ITEM_at_DISPLAY_NAME_", "ITEM_at_QUALIFIED_NAME_",
				"CADSE_DEFINITION_at_PACKAGENAME_", "CADSE_DEFINITION_at_CADSE_NAME_", "CADSE_at_DESCRIPTION_",
				"CADSE_DEFINITION_at_COMMENTARY_", "CADSE_DEFINITION_at_VENDOR_NAME_",
				"CADSE_DEFINITION_at_VERSION_CADSE_", "CADSE_at_ITEM_REPO_LOGIN_", "CADSE_at_ITEM_REPO_PASSWD_",
				"CADSE_at_ITEM_REPO_URL_", "CADSE_at_DEFAULT_CONTENT_REPO_URL_", "CADSE_DEFINITION_at_IMPORTS_",
				"CADSE_lt_EXTENDS" };
		itemCreationTest(null, cadse_name, CadseGCST.CADSE_DEFINITION, expected_creationCST, expected_creationVal,
				expected_modifCST);
	}

	/**
	 * Tests if the Item type pages (creation and modification) have the correct
	 * layout.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testItemType() throws Exception {
		String[] expected_creationCST = { "ITEM_at_NAME_", "ITEM_TYPE_lt_SUPER_TYPE",
				"ITEM_TYPE_at_IS_INSTANCE_ABSTRACT_", "ITEM_TYPE_at_IS_ROOT_ELEMENT_", "ITEM_TYPE_at_HAS_CONTENT_" };

		Object[] expected_creationVal = { "", "Item", false, true, true };
		String[] expected_modifCST = { "ITEM_at_NAME_", "ITEM_at_DISPLAY_NAME_", "ITEM_at_QUALIFIED_NAME_",
				"ITEM_TYPE_at_DEFAULT_INSTANCE_NAME_", "ITEM_TYPE_lt_SUPER_TYPE", "ITEM_TYPE_at_ICON_",
				"ITEM_TYPE_at_PACKAGE_NAME_", "ITEM_TYPE_at_ITEM_FACTORY_", "ITEM_TYPE_at_IS_INSTANCE_ABSTRACT_",
				"ITEM_TYPE_at_IS_INSTANCE_HIDDEN_", "ITEM_TYPE_at_IS_ROOT_ELEMENT_", "ITEM_TYPE_at_HAS_CONTENT_" };
		itemCreationTest(data_model, item_type_name, CadseGCST.ITEM_TYPE, expected_creationCST, expected_creationVal,
				expected_modifCST);
	}

	/**
	 * Tests if the boolean attribute pages (creation and modification) have the
	 * correct layout.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testBooleanAttribute() throws Exception {
		String[] expected_creationCST = { "ITEM_at_NAME_", "ATTRIBUTE_at_DEFAULT_VALUE_",
				"ATTRIBUTE_at_SHOW_IN_DEFAULT_CP_", "ATTRIBUTE_at_SHOW_IN_DEFAULT_MP_",
				"ATTRIBUTE_at_CANNOT_BE_UNDEFINED_", "ATTRIBUTE_at_IS_LIST_", "ATTRIBUTE_at_TWEVOL_",
				"ATTRIBUTE_at_TWCOMMIT_KIND_", "ATTRIBUTE_at_TWREV_SPECIFIC_", "ATTRIBUTE_at_TWUPDATE_KIND_" };

		Object[] expected_creationVal = { "", false, false, true, true, false, "Create new revision", "Abort", true,
				"Generic merge values" };

		String[] expected_modifCST = { "ITEM_at_NAME_", "ITEM_at_DISPLAY_NAME_", "ITEM_at_QUALIFIED_NAME_",
				"ATTRIBUTE_at_DEFAULT_VALUE_", "ATTRIBUTE_at_SHOW_IN_DEFAULT_CP_", "ATTRIBUTE_at_SHOW_IN_DEFAULT_MP_",
				"ATTRIBUTE_at_CANNOT_BE_UNDEFINED_", "ATTRIBUTE_at_IS_LIST_", "ATTRIBUTE_at_TWEVOL_",
				"ATTRIBUTE_at_TWCOMMIT_KIND_", "ATTRIBUTE_at_TWREV_SPECIFIC_", "ATTRIBUTE_at_TWUPDATE_KIND_",
				"ATTRIBUTE_at_NATIF_", "ATTRIBUTE_at_TRANSIENT_" };
		itemCreationTest(it_mit, "my_boolean", CadseGCST.BOOLEAN, expected_creationCST, expected_creationVal,
				expected_modifCST);
	}

	/**
	 * Tests if the double attribute pages (creation and modification) have the
	 * correct layout.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testDoubleAttribute() throws Exception {
		String[] expected_creationCST = { "ITEM_at_NAME_", "ATTRIBUTE_at_DEFAULT_VALUE_",
				"ATTRIBUTE_at_SHOW_IN_DEFAULT_CP_", "ATTRIBUTE_at_SHOW_IN_DEFAULT_MP_",
				"ATTRIBUTE_at_CANNOT_BE_UNDEFINED_", "ATTRIBUTE_at_IS_LIST_", "ATTRIBUTE_at_TWEVOL_",
				"ATTRIBUTE_at_TWCOMMIT_KIND_", "ATTRIBUTE_at_TWREV_SPECIFIC_", "ATTRIBUTE_at_TWUPDATE_KIND_" };
		Object[] expected_creationVal = { "", "", false, true, false, false, "Create new revision", "Abort", true,
				"Generic merge values" };
		String[] expected_modifCST = { "ITEM_at_NAME_", "ITEM_at_DISPLAY_NAME_", "ITEM_at_QUALIFIED_NAME_",
				"ATTRIBUTE_at_DEFAULT_VALUE_", "ATTRIBUTE_at_SHOW_IN_DEFAULT_CP_", "ATTRIBUTE_at_SHOW_IN_DEFAULT_MP_",
				"ATTRIBUTE_at_CANNOT_BE_UNDEFINED_", "ATTRIBUTE_at_IS_LIST_", "ATTRIBUTE_at_TWEVOL_",
				"ATTRIBUTE_at_TWCOMMIT_KIND_", "ATTRIBUTE_at_TWREV_SPECIFIC_", "ATTRIBUTE_at_TWUPDATE_KIND_",
				"ATTRIBUTE_at_NATIF_", "ATTRIBUTE_at_TRANSIENT_" };
		itemCreationTest(it_mit, "my_double", CadseGCST.DOUBLE, expected_creationCST, expected_creationVal,
				expected_modifCST);
	}

	/**
	 * Tests if the integer attribute pages (creation and modification) have the
	 * correct layout.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testIntegerAttribute() throws Exception {
		String[] expected_creationCST = { "ITEM_at_NAME_", "ATTRIBUTE_at_DEFAULT_VALUE_",
				"ATTRIBUTE_at_SHOW_IN_DEFAULT_CP_", "ATTRIBUTE_at_SHOW_IN_DEFAULT_MP_",
				"ATTRIBUTE_at_CANNOT_BE_UNDEFINED_", "ATTRIBUTE_at_IS_LIST_", "ATTRIBUTE_at_TWEVOL_",
				"ATTRIBUTE_at_TWCOMMIT_KIND_", "ATTRIBUTE_at_TWREV_SPECIFIC_", "ATTRIBUTE_at_TWUPDATE_KIND_" };
		Object[] expected_creationVal = { "", "", false, true, false, false, "Create new revision", "Abort", true,
				"Generic merge values" };
		String[] expected_modifCST = { "ITEM_at_NAME_", "ITEM_at_DISPLAY_NAME_", "ITEM_at_QUALIFIED_NAME_",
				"ATTRIBUTE_at_DEFAULT_VALUE_", "ATTRIBUTE_at_SHOW_IN_DEFAULT_CP_", "ATTRIBUTE_at_SHOW_IN_DEFAULT_MP_",
				"ATTRIBUTE_at_CANNOT_BE_UNDEFINED_", "ATTRIBUTE_at_IS_LIST_", "ATTRIBUTE_at_TWEVOL_",
				"ATTRIBUTE_at_TWCOMMIT_KIND_", "ATTRIBUTE_at_TWREV_SPECIFIC_", "ATTRIBUTE_at_TWUPDATE_KIND_",
				"ATTRIBUTE_at_NATIF_", "ATTRIBUTE_at_TRANSIENT_" };
		itemCreationTest(it_mit, "my_integer", CadseGCST.INTEGER, expected_creationCST, expected_creationVal,
				expected_modifCST);
	}

	/**
	 * Tests if the long attribute pages (creation and modification) have the
	 * correct layout.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testLongAttribute() throws Exception {
		String[] expected_creationCST = { "ITEM_at_NAME_", "ATTRIBUTE_at_DEFAULT_VALUE_",
				"ATTRIBUTE_at_SHOW_IN_DEFAULT_CP_", "ATTRIBUTE_at_SHOW_IN_DEFAULT_MP_",
				"ATTRIBUTE_at_CANNOT_BE_UNDEFINED_", "ATTRIBUTE_at_IS_LIST_", "ATTRIBUTE_at_TWEVOL_",
				"ATTRIBUTE_at_TWCOMMIT_KIND_", "ATTRIBUTE_at_TWREV_SPECIFIC_", "ATTRIBUTE_at_TWUPDATE_KIND_" };
		Object[] expected_creationVal = { "", "", false, true, false, false, "Create new revision", "Abort", true,
				"Generic merge values" };
		String[] expected_modifCST = { "ITEM_at_NAME_", "ITEM_at_DISPLAY_NAME_", "ITEM_at_QUALIFIED_NAME_",
				"ATTRIBUTE_at_DEFAULT_VALUE_", "ATTRIBUTE_at_SHOW_IN_DEFAULT_CP_", "ATTRIBUTE_at_SHOW_IN_DEFAULT_MP_",
				"ATTRIBUTE_at_CANNOT_BE_UNDEFINED_", "ATTRIBUTE_at_IS_LIST_", "ATTRIBUTE_at_TWEVOL_",
				"ATTRIBUTE_at_TWCOMMIT_KIND_", "ATTRIBUTE_at_TWREV_SPECIFIC_", "ATTRIBUTE_at_TWUPDATE_KIND_",
				"ATTRIBUTE_at_NATIF_", "ATTRIBUTE_at_TRANSIENT_" };
		itemCreationTest(it_mit, "my_long", CadseGCST.LONG, expected_creationCST, expected_creationVal,
				expected_modifCST);
	}

	/**
	 * Tests if the string attribute pages (creation and modification) have the
	 * correct layout.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testStringAttribute() throws Exception {
		String[] expected_creationCST = { "ITEM_at_NAME_", "ATTRIBUTE_at_DEFAULT_VALUE_",
				"ATTRIBUTE_at_SHOW_IN_DEFAULT_CP_", "ATTRIBUTE_at_SHOW_IN_DEFAULT_MP_",
				"ATTRIBUTE_at_CANNOT_BE_UNDEFINED_", "ATTRIBUTE_at_IS_LIST_", "ATTRIBUTE_at_TWEVOL_",
				"ATTRIBUTE_at_TWCOMMIT_KIND_", "ATTRIBUTE_at_TWREV_SPECIFIC_", "ATTRIBUTE_at_TWUPDATE_KIND_" };
		Object[] expected_creationVal = { "", "", false, true, false, false, "Create new revision", "Abort", true,
				"Generic merge values" };
		String[] expected_modifCST = { "ITEM_at_NAME_", "ITEM_at_DISPLAY_NAME_", "ITEM_at_QUALIFIED_NAME_",
				"ATTRIBUTE_at_DEFAULT_VALUE_", "ATTRIBUTE_at_SHOW_IN_DEFAULT_CP_", "ATTRIBUTE_at_SHOW_IN_DEFAULT_MP_",
				"ATTRIBUTE_at_CANNOT_BE_UNDEFINED_", "STRING_at_NOT_EMPTY_", "ATTRIBUTE_at_IS_LIST_",
				"ATTRIBUTE_at_TWEVOL_", "ATTRIBUTE_at_TWCOMMIT_KIND_", "ATTRIBUTE_at_TWREV_SPECIFIC_",
				"ATTRIBUTE_at_TWUPDATE_KIND_", "ATTRIBUTE_at_NATIF_", "ATTRIBUTE_at_TRANSIENT_" };
		itemCreationTest(it_mit, "my_string", CadseGCST.STRING, expected_creationCST, expected_creationVal,
				expected_modifCST);
	}

	/**
	 * Tests if the enum type pages (creation and modification) have the correct
	 * layout.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testEnumType() throws Exception {
		String[] expected_creationCST = { "ITEM_at_NAME_", "ENUM_TYPE_at_VALUES_" };
		Object[] expected_creationVal = { "", new Object[] {} };
		String[] expected_modifCST = { "ITEM_at_NAME_", "ITEM_at_DISPLAY_NAME_", "ITEM_at_QUALIFIED_NAME_",
				"ENUM_TYPE_at_JAVA_CLASS_", "ENUM_TYPE_at_MUST_BE_GENERATED_", "ENUM_TYPE_at_VALUES_" };
		itemCreationTest(data_model, "my_enum_type", CadseGCST.ENUM_TYPE, expected_creationCST, expected_creationVal,
				expected_modifCST);
	}

	/**
	 * Tests if the enum attribute pages (creation and modification) have the
	 * correct layout.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testEnumAttribute() throws Exception {

		String[] expected_creationCST = { "ITEM_at_NAME_", "ENUM_lt_ENUM_TYPE", "ATTRIBUTE_at_DEFAULT_VALUE_",
				"ATTRIBUTE_at_SHOW_IN_DEFAULT_CP_", "ATTRIBUTE_at_SHOW_IN_DEFAULT_MP_", "ATTRIBUTE_at_IS_LIST_",
				"ATTRIBUTE_at_TWEVOL_", "ATTRIBUTE_at_TWCOMMIT_KIND_", "ATTRIBUTE_at_TWREV_SPECIFIC_",
				"ATTRIBUTE_at_TWUPDATE_KIND_" };
		Object[] expected_creationVal = { "", null, "", false, true, false, "Create new revision", "Abort", true,
				"Generic merge values" };
		String[] expected_modifCST = { "ITEM_at_NAME_", "ITEM_at_DISPLAY_NAME_", "ITEM_at_QUALIFIED_NAME_",
				"ENUM_lt_ENUM_TYPE", "ATTRIBUTE_at_DEFAULT_VALUE_", "ATTRIBUTE_at_SHOW_IN_DEFAULT_CP_",
				"ATTRIBUTE_at_SHOW_IN_DEFAULT_MP_", "ATTRIBUTE_at_IS_LIST_", "ATTRIBUTE_at_TWEVOL_",
				"ATTRIBUTE_at_TWCOMMIT_KIND_", "ATTRIBUTE_at_TWREV_SPECIFIC_", "ATTRIBUTE_at_TWUPDATE_KIND_",
				"ATTRIBUTE_at_NATIF_", "ATTRIBUTE_at_TRANSIENT_" };
		itemCreationTest(it_mit, "my_enum", CadseGCST.ENUM, expected_creationCST, expected_creationVal,
				expected_modifCST);
	}

	/**
	 * Tests if the List of Boolean attribute pages (creation and modification)
	 * have the correct layout.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testListBooleanAttribute() throws Exception {
		String[] expected_typeCreationCST = { "ATTRIBUTE_at_DEFAULT_VALUE_", "ATTRIBUTE_at_CANNOT_BE_UNDEFINED_" };
		Object[] expected_typeCreationVal = { false, true };
		String[] expected_typeModifCST = { "ATTRIBUTE_at_DEFAULT_VALUE_", "ATTRIBUTE_at_CANNOT_BE_UNDEFINED_" };

		itemListCreationTest(it_mit, "myListBoolean", CadseGCST.BOOLEAN, expected_typeCreationCST,
				expected_typeCreationVal, expected_typeModifCST);
	}

	/**
	 * Tests if the List of Double attribute pages (creation and modification)
	 * have the correct layout.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testListDoubleAttribute() throws Exception {
		String[] expected_typeCreationCST = { "ATTRIBUTE_at_DEFAULT_VALUE_", "ATTRIBUTE_at_CANNOT_BE_UNDEFINED_" };
		Object[] expected_typeCreationVal = { "", true };
		String[] expected_typeModifCST = { "ATTRIBUTE_at_DEFAULT_VALUE_", "ATTRIBUTE_at_CANNOT_BE_UNDEFINED_" };

		itemListCreationTest(it_mit, "myListDouble", CadseGCST.DOUBLE, expected_typeCreationCST,
				expected_typeCreationVal, expected_typeModifCST);
	}

	/**
	 * Tests if the List of Enum attribute pages (creation and modification)
	 * have the correct layout.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testListEnumAttribute() throws Exception {
		String[] expected_typeCreationCST = { "ENUM_lt_ENUM_TYPE", "ATTRIBUTE_at_DEFAULT_VALUE_" };
		Object[] expected_typeCreationVal = { null, "" };
		String[] expected_typeModifCST = { "ENUM_lt_ENUM_TYPE", "ATTRIBUTE_at_DEFAULT_VALUE_" };

		itemListCreationTest(it_mit, "myListEnum", CadseGCST.ENUM, expected_typeCreationCST, expected_typeCreationVal,
				expected_typeModifCST);
	}

	/**
	 * Tests if the List of Double attribute pages (creation and modification)
	 * have the correct layout.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testListIntegerAttribute() throws Exception {
		String[] expected_typeCreationCST = { "ATTRIBUTE_at_DEFAULT_VALUE_", "ATTRIBUTE_at_CANNOT_BE_UNDEFINED_" };
		Object[] expected_typeCreationVal = { "", true };
		String[] expected_typeModifCST = { "ATTRIBUTE_at_DEFAULT_VALUE_", "ATTRIBUTE_at_CANNOT_BE_UNDEFINED_" };

		itemListCreationTest(it_mit, "myListInteger", CadseGCST.INTEGER, expected_typeCreationCST,
				expected_typeCreationVal, expected_typeModifCST);
	}

	/**
	 * Tests if the List of Double attribute pages (creation and modification)
	 * have the correct layout.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testListLongAttribute() throws Exception {
		String[] expected_typeCreationCST = { "ATTRIBUTE_at_DEFAULT_VALUE_", "ATTRIBUTE_at_CANNOT_BE_UNDEFINED_" };
		Object[] expected_typeCreationVal = { "", true };
		String[] expected_typeModifCST = { "ATTRIBUTE_at_DEFAULT_VALUE_", "ATTRIBUTE_at_CANNOT_BE_UNDEFINED_" };

		itemListCreationTest(it_mit, "myListLong", CadseGCST.LONG, expected_typeCreationCST, expected_typeCreationVal,
				expected_typeModifCST);
	}

	/**
	 * Tests if the List of Double attribute pages (creation and modification)
	 * have the correct layout.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testListStringAttribute() throws Exception {
		String[] expected_typeCreationCST = { "ATTRIBUTE_at_DEFAULT_VALUE_", "ATTRIBUTE_at_CANNOT_BE_UNDEFINED_" };
		Object[] expected_typeCreationVal = { "", true };
		String[] expected_typeModifCST = { "ATTRIBUTE_at_DEFAULT_VALUE_", "ATTRIBUTE_at_CANNOT_BE_UNDEFINED_",
				"STRING_at_NOT_EMPTY_" };

		itemListCreationTest(it_mit, "myListString", CadseGCST.STRING, expected_typeCreationCST,
				expected_typeCreationVal, expected_typeModifCST);
	}

	/**
	 * Tests if the link type attribute pages (creation and modification) have
	 * the correct layout.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testLinkTypeAttribute() throws Exception {
		String[] expected_creationCST = { "ITEM_at_NAME_", "LINK_TYPE_lt_DESTINATION", "LINK_TYPE_at_ANNOTATION_",
				"LINK_TYPE_at_AGGREGATION_", "LINK_TYPE_at_COMPOSITION_", "LINK_TYPE_at_PART_",
				"LINK_TYPE_at_REQUIRE_", "LINK_TYPE_at_MAPPING_", "LINK_TYPE_at_GROUP_", "LINK_TYPE_at_HIDDEN_",
				"LINK_TYPE_at_MIN_", "LINK_TYPE_at_MAX_", "ATTRIBUTE_at_SHOW_IN_DEFAULT_CP_",
				"ATTRIBUTE_at_SHOW_IN_DEFAULT_MP_", "ATTRIBUTE_at_TWEVOL_", "ATTRIBUTE_at_TWCOMMIT_KIND_",
				"ATTRIBUTE_at_TWREV_SPECIFIC_", "ATTRIBUTE_at_TWUPDATE_KIND_", "LINK_TYPE_at_TWCOUPLED_",
				"LINK_TYPE_at_TWDEST_EVOL_" };
		Object[] expected_creationVal = { "", null, false, true, false, false, false, false, false, false, "0",
				"unbounded", false, true, "Create new revision", "Abort", true, "Generic merge values", false,
				"Create new revision" };
		String[] expected_modifCST = { "ITEM_at_NAME_", "ITEM_at_DISPLAY_NAME_", "ITEM_at_QUALIFIED_NAME_",
				"LINK_TYPE_lt_SOURCE", "LINK_TYPE_lt_DESTINATION", "LINK_TYPE_lt_INVERSE_LINK",
				"LINK_TYPE_at_ANNOTATION_", "LINK_TYPE_at_AGGREGATION_", "LINK_TYPE_at_COMPOSITION_",
				"LINK_TYPE_at_PART_", "LINK_TYPE_at_REQUIRE_", "LINK_TYPE_at_MAPPING_", "LINK_TYPE_at_GROUP_",
				"LINK_TYPE_at_HIDDEN_", "LINK_TYPE_at_SELECTION_", "LINK_TYPE_at_LINK_MANAGER_", "LINK_TYPE_at_MIN_",
				"LINK_TYPE_at_MAX_", "ATTRIBUTE_at_SHOW_IN_DEFAULT_CP_", "ATTRIBUTE_at_SHOW_IN_DEFAULT_MP_",
				"ATTRIBUTE_at_TWEVOL_", "ATTRIBUTE_at_TWCOMMIT_KIND_", "ATTRIBUTE_at_TWREV_SPECIFIC_",
				"ATTRIBUTE_at_TWUPDATE_KIND_", "LINK_TYPE_at_TWCOUPLED_", "LINK_TYPE_at_TWDEST_EVOL_",
				"ATTRIBUTE_at_TRANSIENT_" };
		itemCreationTest(it_mit, "my_link_type", CadseGCST.LINK_TYPE, expected_creationCST, expected_creationVal,
				expected_modifCST);
	}

	/**
	 * Tests if the manager pages (creation and modification) have the correct
	 * layout.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testManager() throws Exception {
		String[] expected_modifCST = { "ITEM_at_NAME_", "ITEM_at_DISPLAY_NAME_", "ITEM_at_QUALIFIED_NAME_",
				"MANAGER_at_HUMAN_NAME_" };
		checkModificationPage(mapping.concat(item_type_name + "-manager"), CadseGCST.MANAGER, expected_modifCST);
	}

	/**
	 * Tests if the Java Project Content Model mapping pages (creation and
	 * modification) have the correct layout.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testJavaProjectContentModel() throws Exception {
		String[] expected_creationCST = { "PROJECT_CONTENT_MODEL_at_PROJECT_NAME_",
				"JAVA_PROJECT_CONTENT_MODEL_at_HAS_SOURCE_FOLDER_", "CONTENT_ITEM_TYPE_at_EXTENDS_CLASS_" };
		Object[] expected_creationVal = { "${#qualified-name}", true, false };
		String[] expected_modifCST = { "PROJECT_CONTENT_MODEL_at_PROJECT_NAME_",
				"JAVA_PROJECT_CONTENT_MODEL_at_HAS_SOURCE_FOLDER_", "CONTENT_ITEM_TYPE_at_EXTENDS_CLASS_" };

		createItemType(data_model, "mapping_javaProject", notAbstractKv(), rootKv(), withContentKv());

		checkCreationContentModel(expected_creationCST, expected_creationVal, mapping
				.concat("mapping_javaProject-manager"), "JavaProjectContentModel", "${#qualified-name}_test", null,
				null, null, null, null, null);
		checkModificationPage(mapping.concat("mapping_javaProject-manager").concat("content-item"),
				CadseGCST.JAVA_PROJECT_CONTENT_MODEL, expected_modifCST);
		checkCompilationErrors(workspaceView, cadse_model);
	}

	/**
	 * Tests if the Java File Content Model mapping pages (creation and
	 * modification) have the correct layout.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testJavaFileContentModel() throws Exception {
		String[] expected_creationCST = { "JAVA_FILE_CONTENT_MODEL_at_CLASS_NAME_",
				"JAVA_FILE_CONTENT_MODEL_at_PACKAGE_NAME_", "CONTENT_ITEM_TYPE_at_EXTENDS_CLASS_" };
		Object[] expected_creationVal = { "", "", true };
		String[] expected_modifCST = { "JAVA_FILE_CONTENT_MODEL_at_CLASS_NAME_",
				"JAVA_FILE_CONTENT_MODEL_at_PACKAGE_NAME_", "CONTENT_ITEM_TYPE_at_EXTENDS_CLASS_" };

		createItemType(data_model, "mapping_JavaFile", notAbstractKv(), rootKv(), withContentKv());

		checkCreationContentModel(expected_creationCST, expected_creationVal, mapping
				.concat("mapping_JavaFile-manager"), "JavaFileContentModel", null, null, "${#short-name}",
				"fr.imag.adele.${#short-name}", null, null, null);
		checkModificationPage(mapping.concat("mapping_JavaFile-manager").concat("content-item"),
				CadseGCST.JAVA_FILE_CONTENT_MODEL, expected_modifCST);
		checkCompilationErrors(workspaceView, cadse_model);
	}

	/**
	 * Tests if the Folder Content Model mapping pages (creation and
	 * modification) have the correct layout.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testFolderContentModel() throws Exception {
		String[] expected_creationCST = { "FOLDER_CONTENT_MODEL_at_FOLDER_PATH_", "CONTENT_ITEM_TYPE_at_EXTENDS_CLASS_" };
		Object[] expected_creationVal = { "", false };
		String[] expected_modifCST = { "FOLDER_CONTENT_MODEL_at_FOLDER_PATH_", "CONTENT_ITEM_TYPE_at_EXTENDS_CLASS_",
				"FOLDER_CONTENT_MODEL_at_REQUIRE_FOLDER_" };

		createItemType(data_model, "mapping_folder", notAbstractKv(), rootKv(), withContentKv());

		checkCreationContentModel(expected_creationCST, expected_creationVal, mapping.concat("mapping_folder-manager"),
				"FolderContentModel", null, null, null, null, "${#short-name}", null, null);
		checkModificationPage(mapping.concat("mapping_folder-manager").concat("content-item"),
				CadseGCST.FOLDER_CONTENT_MODEL, expected_modifCST);
		checkCompilationErrors(workspaceView, cadse_model);
	}

	/**
	 * Tests if the File Content Model mapping pages (creation and modification)
	 * have the correct layout.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testFileContentModel() throws Exception {
		String[] expected_creationCST = { "FILE_CONTENT_MODEL_at_FILE_PATH_", "CONTENT_ITEM_TYPE_at_EXTENDS_CLASS_" };
		Object[] expected_creationVal = { "", true };
		String[] expected_modifCST = { "FILE_CONTENT_MODEL_at_FILE_PATH_", "CONTENT_ITEM_TYPE_at_EXTENDS_CLASS_" };

		createItemType(data_model, "mapping_file", notAbstractKv(), rootKv(), withContentKv());

		checkCreationContentModel(expected_creationCST, expected_creationVal, mapping.concat("mapping_file-manager"),
				"FileContentModel", null, null, null, null, null, "${#short-name}.txt", null);
		checkModificationPage(mapping.concat("mapping_file-manager").concat("content-item"),
				CadseGCST.FILE_CONTENT_MODEL, expected_modifCST);
		checkCompilationErrors(workspaceView, cadse_model);
	}

	/**
	 * Checks that the CADSE generated by this test has no compilation error.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCheckCompilation() throws Exception {
		checkCompilationErrors(workspaceView, cadse_model, GTPreferences.TIMEOUT);
	}

	/**
	 * Creates generic file content model.
	 * 
	 * @param expected_creationCST
	 *            the expected item type constants for the creation page
	 * @param expected_creationVal
	 *            the expected default values for the creation page
	 * @param sourceNode
	 *            The path to the mapping entry of a specific item type.
	 * @param typeName
	 *            The name of the type to be created
	 * @param projectName
	 *            The project name
	 * @param hasSourceFolder
	 *            If this mapping gets a source folder
	 * @param className
	 *            The name of the implementation class
	 * @param packageName
	 *            The package name
	 * @param folderPath
	 *            The folder path
	 * @param filePath
	 *            The path of the file which will be associated to the item
	 * @param extendsClass
	 *            The value of the extends class attribute. Null for default.
	 */
	private void checkCreationContentModel(String[] expected_creationCST, Object[] expected_creationVal,
			GTTreePath sourceNode, String typeName, String projectName, Boolean hasSourceFolder, String className,
			String packageName, String folderPath, String filePath, Boolean extendsClass) {

		/* Creates content */
		workspaceView.contextMenuNew(sourceNode, typeName).click();
		GTCadseShell shell = new GTCadseShell(typeName);
		String[] creationCST = shell.findAttributeConstants();
		Object[] creationVal = shell.findAttributeValues();
		String creationCST_str = getStringDef(shell);
		String creationVal_str = getStringVal(shell);

		// Checkboxes checking
		booleanFieldChecker.check(shell);

		if (projectName != null && !projectName.isEmpty()) {
			shell.findCadseField(CadseGCST.PROJECT_CONTENT_MODEL_at_PROJECT_NAME_).typeText(projectName);
		}
		if (hasSourceFolder != null) {
			shell.findCadseField(CadseGCST.JAVA_PROJECT_CONTENT_MODEL_at_HAS_SOURCE_FOLDER_).check(hasSourceFolder);
		}
		if (className != null && !className.isEmpty()) {
			shell.findCadseField(CadseGCST.JAVA_FILE_CONTENT_MODEL_at_CLASS_NAME_).typeText(className);
		}
		if (packageName != null && !packageName.isEmpty()) {
			shell.findCadseField(CadseGCST.JAVA_FILE_CONTENT_MODEL_at_PACKAGE_NAME_).typeText(packageName);
		}
		if (folderPath != null && !folderPath.isEmpty()) {
			shell.findCadseField(CadseGCST.FOLDER_CONTENT_MODEL_at_FOLDER_PATH_).typeText(folderPath);
		}
		if (filePath != null && !filePath.isEmpty()) {
			shell.findCadseField(CadseGCST.FILE_CONTENT_MODEL_at_FILE_PATH_).typeText(filePath);
		}
		if (extendsClass != null) {
			shell.findCadseField(CadseGCST.CONTENT_ITEM_TYPE_at_EXTENDS_CLASS_).check(extendsClass);
		}
		shell.close();

		/* Assert item has been displayed */
		workspaceView.selectNode(sourceNode.concat("content-item"));

		// Performs test
		if (isSameValues(creationCST, expected_creationCST) == false) {
			throw new WidgetNotFoundException(
					"The workbench part doesn't contains expected attributes. Expected String : " + creationCST_str);
		}

		if (isSameValues(creationVal, expected_creationVal) == false) {
			throw new WidgetNotFoundException("The defaul values are not those which were expected. Expected String : "
					+ creationVal_str);
		}
	}

	/**
	 * Attribute creation test.
	 * 
	 * @param path
	 *            the path to an item in the workspace view which will be the
	 *            source for the creation
	 * @param attributeName
	 *            the name of the new item
	 * @param itConstant
	 *            the CADSEG item type constant
	 * @param expected_creationCST
	 *            the expected creation page attributes constants
	 * @param expected_creationVal
	 *            the expected default values for the creation dialog
	 * @param expected_modifCST
	 *            the expected modification page attributes constants
	 * @throws WidgetNotFoundException
	 *             the widget not found exception
	 */
	private void itemCreationTest(GTTreePath path, String attributeName, ItemType itConstant,
			String[] expected_creationCST, Object[] expected_creationVal, String[] expected_modifCST)
			throws WidgetNotFoundException {

		// Attribute creation and creation page checking
		GTTreePath completePath = checkCreationPage(path, attributeName, itConstant, expected_creationCST,
				expected_creationVal);

		// Modification page
		checkModificationPage(completePath, itConstant, expected_modifCST);

		// The creation process shouldn't have induced compilation error/
		checkCompilationErrors(workspaceView, cadse_model);
	}

	/**
	 * Attribute creation test.
	 * 
	 * @param path
	 *            the path to an item in the workspace view which will be the
	 *            source for the creation
	 * @param attributeName
	 *            the name of the new item
	 * @param itConstant
	 *            the CADSEG item type constant
	 * @param expected_creationCST
	 *            the expected creation page attributes constants
	 * @param expected_creationVal
	 *            the expected default values for the creation dialog
	 * @param expected_modifCST
	 *            the expected modification page attributes constants
	 * @throws WidgetNotFoundException
	 *             the widget not found exception
	 */
	private void itemListCreationTest(GTTreePath path, String attributeName, ItemType itConstant,
			String[] expected_typeCreationCST, Object[] expected_typeCreationVal, String[] expected_typeModifCST)
			throws WidgetNotFoundException {

		String[] expected_listCreationCST = { "ITEM_at_NAME_", "LIST_lt_SUB_TYPE", "ATTRIBUTE_at_SHOW_IN_DEFAULT_CP_",
				"ATTRIBUTE_at_SHOW_IN_DEFAULT_MP_", "ATTRIBUTE_at_TWEVOL_", "ATTRIBUTE_at_TWCOMMIT_KIND_",
				"ATTRIBUTE_at_TWREV_SPECIFIC_", "ATTRIBUTE_at_TWUPDATE_KIND_" };
		Object[] expected_listCreationVal = { "", "", false, true, "Create new revision", "Abort", true,
				"Generic merge values" };
		String[] expected_listModifCST = { "ITEM_at_NAME_", "ITEM_at_DISPLAY_NAME_", "ITEM_at_QUALIFIED_NAME_",
				"LIST_lt_SUB_TYPE", "ATTRIBUTE_at_SHOW_IN_DEFAULT_CP_", "ATTRIBUTE_at_SHOW_IN_DEFAULT_MP_",
				"ATTRIBUTE_at_TWEVOL_", "ATTRIBUTE_at_TWCOMMIT_KIND_", "ATTRIBUTE_at_TWREV_SPECIFIC_",
				"ATTRIBUTE_at_TWUPDATE_KIND_", "ATTRIBUTE_at_NATIF_", "ATTRIBUTE_at_TRANSIENT_" };

		// Attribute creation and creation page checking
		GTTreePath completePath = checkListCreationPage(path, attributeName, itConstant, expected_listCreationCST,
				expected_listCreationVal, expected_typeCreationCST, expected_typeCreationVal);

		// Modification page
		checkModificationPage(completePath, CadseGCST.LIST, expected_listModifCST);
		checkModificationPage(completePath.concat("sub-element"), itConstant, expected_typeModifCST);

		// The creation process shouldn't have induced compilation error/
		checkCompilationErrors(workspaceView, cadse_model);
	}

	/**
	 * Creates an item and checks if the creation page displays the correct set
	 * of fields.
	 * 
	 * @param path
	 *            The path in the workspace view used for item creation
	 * @param attributeName
	 *            The name of the new attribute
	 * @param itConstant
	 *            The ItemType constant of this attribute
	 * @param expected_creationCST
	 *            The list of expected fields attributes constants.
	 * @param expected_creationVal
	 *            the expected default values for the creation dialog
	 * @return the tree path to find the created item in the workspace view
	 */
	private GTTreePath checkCreationPage(GTTreePath path, String attributeName, ItemType itConstant,
			String[] expected_creationCST, Object[] expected_creationVal) {

		// Creation
		workspaceView.contextMenuNew(path, itConstant).click();
		GTCadseShell shell = new GTCadseShell(itConstant);
		String[] creationCST = shell.findAttributeConstants();
		Object[] creationVal = shell.findAttributeValues();
		String creationCST_str = getStringDef(shell);
		String creationVal_str = getStringVal(shell);

		// Checkboxes checking
		booleanFieldChecker.check(shell);

		shell.findCadseFieldName().typeText(attributeName);
		if (itConstant == CadseGCST.LINK_TYPE) {
			shell.findCadseField(CadseGCST.LINK_TYPE_lt_DESTINATION).browser(cadse_name,
					CadseDefinitionManager.DATA_MODEL, item_type_name);
		} else if (itConstant == CadseGCST.CADSE_DEFINITION) {
			shell.findCadseField(CadseGCST.CADSE_DEFINITION_at_PACKAGENAME_).typeText("model.myCadse");
		} else if (itConstant == CadseGCST.ENUM_TYPE) {
			shell.findCadseField(CadseGCST.ENUM_TYPE_at_VALUES_).addBrowser("one", "two", "three");
		} else if (itConstant == CadseGCST.ENUM) {
			shell.findCadseField(CadseGCST.ENUM_lt_ENUM_TYPE).browser("my_enum_type");
		}
		shell.close(GTPreferences.TIMEOUT); /* For CADSE Definition */

		// Compute complete path
		GTTreePath completePath = null;
		if (path != null) {
			completePath = path.concat(attributeName);
		} else {
			completePath = new GTTreePath(attributeName);
		}

		// Assert item has been created
		workspaceView.selectNode(completePath, GTPreferences.TIMEOUT);

		// Performs test
		if (isSameValues(creationCST, expected_creationCST) == false) {
			throw new WidgetNotFoundException(
					"The workbench part doesn't contains expected attributes. Expected String : " + creationCST_str);
		}
		if (isSameValues(creationVal, expected_creationVal) == false) {
			throw new WidgetNotFoundException(
					"The default values are not those which were expected. Expected String : " + creationVal_str);
		}

		return completePath;
	}

	/**
	 * Creates an item and checks if the creation page displays the correct set
	 * of fields.
	 * 
	 * @param path
	 *            The path in the workspace view used for item creation
	 * @param attributeName
	 *            The name of the new attribute
	 * @param itConstant
	 *            The ItemType constant of this attribute
	 * @param expected_listCreationCST
	 *            The list of expected fields attributes constants.
	 * @param expected_listCreationVal
	 *            the expected default values for the creation dialog
	 * @return the tree path to find the created item in the workspace view
	 */
	private GTTreePath checkListCreationPage(GTTreePath path, String attributeName, ItemType itConstant,
			String[] expected_listCreationCST, Object[] expected_listCreationVal, String[] expected_typeCreationCST,
			Object[] expected_typeCreationVal) {

		// Creation
		workspaceView.contextMenuNew(path, CadseGCST.LIST).click();
		GTCadseShell shell = new GTCadseShell(CadseGCST.LIST);
		String[] listCreationCST = shell.findAttributeConstants();
		Object[] listCreationVal = shell.findAttributeValues();
		String listCreationCST_str = getStringDef(shell);
		String listCreationVal_str = getStringVal(shell);

		// Checkboxes checking
		booleanFieldChecker.check(shell);
		shell.findCadseFieldName().typeText(attributeName);

		shell.findCadseField(CadseGCST.LIST_lt_SUB_TYPE).setSelection(itConstant.getDisplayName());
		GTCadseShell shell2 = new GTCadseShell(itConstant);
		String[] typeCreationCST = shell2.findAttributeConstants();
		Object[] typeCreationVal = shell2.findAttributeValues();
		String typeCreationCST_str = getStringDef(shell2);
		String typeCreationVal_str = getStringVal(shell2);
		if (itConstant == CadseGCST.ENUM)
			shell2.findCadseField(CadseGCST.ENUM_lt_ENUM_TYPE).browser("my_enum_type");
		booleanFieldChecker.check(shell2);
		shell2.close();

		shell.close();

		// Compute complete path
		GTTreePath completePath = null;
		if (path != null) {
			completePath = path.concat(attributeName);
		} else {
			completePath = new GTTreePath(attributeName);
		}

		// Assert item has been created
		workspaceView.selectNode(completePath, GTPreferences.TIMEOUT);

		// Performs test
		if (isSameValues(listCreationCST, expected_listCreationCST) == false) {
			throw new WidgetNotFoundException(
					"The workbench part doesn't contains expected attributes. Expected String : " + listCreationCST_str);
		}
		if (isSameValues(listCreationVal, expected_listCreationVal) == false) {
			throw new WidgetNotFoundException(
					"The default values are not those which were expected. Expected String : " + listCreationVal_str);
		}
		if (isSameValues(typeCreationCST, expected_typeCreationCST) == false) {
			throw new WidgetNotFoundException(
					"The workbench part doesn't contains expected attributes. Expected String : " + typeCreationCST_str);
		}
		if (isSameValues(typeCreationVal, expected_typeCreationVal) == false) {
			throw new WidgetNotFoundException(
					"The default values are not those which were expected. Expected String : " + typeCreationVal_str);
		}

		return completePath;
	}

	/**
	 * Checks if a modification page displays correct fields in a given section
	 * for a given item.
	 * 
	 * @param path
	 *            The item path in the workspace view
	 * @param tab
	 *            The tab of the property page. Can be null.
	 * @param section
	 *            The section where to look for constants. Can be null.
	 * @param expected
	 *            The list of expected fields attributes constants.
	 */
	private void checkModificationPage(GTTreePath path, ItemType itConstant, String[] expected) {
		String tab = tabLabel(itConstant);
		String section = sectionLabel(itConstant);

		try {
			// Selects node into the tree
			workspaceView.selectNode(path);

			// Displays property view
			if (tab != null) {
				propertiesView.showTab(tab);
			} else {
				propertiesView.show();
			}
		} catch (Exception e) {
			// Selects node into the tree
			workspaceView.selectNode(path);

			// Displays property view
			if (tab != null) {
				propertiesView.showTab(tab);
			} else {
				propertiesView.show();
			}
		}

		// Section decoding
		String[] modifCST;
		if (section != null) {
			modifCST = propertiesView.findSection(section).findAttributeConstants();
		} else {
			modifCST = propertiesView.findAttributeConstants();
		}

		// Gets attributes constants
		String modifStr = getStringDef(propertiesView.findSection(section));

		// Attribute comparison
		if (isSameValues(modifCST, expected) == false) {
			throw new WidgetNotFoundException(
					"The workbench part doesn't contains expected attributes. Expected String : " + modifStr);
		}

		// Check boolean field
		if (!path.getDestinationName().equals("sub-element"))
			booleanFieldChecker.check(propertiesView);
	}

	/**
	 * Tests is two string arrays are equal.
	 * 
	 * @param provided
	 *            The first string array
	 * @param expected
	 *            The second string array
	 * @return a boolean
	 */
	private boolean isSameValues(Object[] provided, Object[] expected) {

		if (provided.length != expected.length) {
			return false;
		}

		for (int i = 0; i < provided.length; i++) {

			if (provided[i] == null && expected[i] != null || provided[i] != null && expected[i] == null) {
				return false;
			} else if (provided[i] instanceof Object[] && expected[i] instanceof Object[]) {
				Object[] tab1 = (Object[]) provided[i];
				Object[] tab2 = (Object[]) expected[i];
				if (isSameValues(tab1, tab2) == false) {
					return false;
				}
			} else if (provided[i] != null && !provided[i].equals(expected[i])) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Return a java piece of code with the definition of a list of strings
	 * which are the constants of fields found in the workbench part given into
	 * parameter. For debug purpose only.
	 * 
	 * @param wp
	 *            the workbench part for searching fields
	 * @return the java code
	 */
	private String getStringDef(GTCadseWorkbenchPart wp) {

		List<IAttributeType<?>> attrlist = wp.findAttributeDefinition();

		StringBuilder sb = new StringBuilder();
		boolean begin = true;

		sb.append("{");
		for (IAttributeType<?> attr : attrlist) {
			if (begin) {
				begin = false;
			} else {
				sb.append(", ");
			}
			sb.append("\"");
			sb.append(attr.getCSTName());
			sb.append("\"");
		}
		sb.append("};");

		return sb.toString();
	}

	/**
	 * Return a java piece of code with the definition of a list of strings
	 * which are the constants of fields found in the workbench part given into
	 * parameter. For debug purpose only.
	 * 
	 * @param wp
	 *            the workbench part for searching fields
	 * @return the java code
	 */
	private String getStringVal(GTCadseWorkbenchPart wp) {

		Object[] values = wp.findAttributeValues();

		StringBuilder sb = new StringBuilder();
		boolean begin = true;

		sb.append("{");
		for (Object value : values) {
			if (begin) {
				begin = false;
			} else {
				sb.append(", ");
			}

			if (value == null) {
				sb.append("null");
			} else if (value instanceof String) {
				sb.append("\"");
				sb.append(value);
				sb.append("\"");
			} else if (value instanceof Boolean) {
				sb.append(value.toString());
			} else if (value instanceof String[]) {
				sb.append("new Object[]{");
				boolean start = true;
				for (String str : (String[]) value) {
					if (start) {
						start = false;
					} else {
						sb.append(", ");
					}
					sb.append(str);
				}
				sb.append("}");

			} else {
				throw new WidgetNotFoundException("Unsupported element of class : " + value.getClass().getName());
			}
		}
		sb.append("};");

		return sb.toString();
	}

	private abstract static class booleanFieldChecker {

		private static List<KeyValue> list = initList();
		private final static int defaultStateNumber = 2;

		/**
		 * Performs initialization with expected result.
		 * 
		 * @return
		 */
		private static List<KeyValue> initList() {

			List<KeyValue> list = new ArrayList<KeyValue>();

			list.add(new KeyValue(CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE_, 3)); // boolean
			// attr
			// can
			// have
			// a
			// null
			// dv

			return list;
		}

		/**
		 * Check all the checkboxes of a workbench part.
		 * 
		 * @param wp
		 */
		public static void check(GTCadseWorkbenchPart wp) {

			List<IAttributeType<?>> attrList = wp.findAttributeDefinition();

			for (IAttributeType<?> attr : attrList) {
				GTCadseField field = wp.findCadseField(attr);

				if (field.getUIfield().getType() == CadseGCST.DCHECK_BOX) {
					assertCheckBoxHasCorrectStateNumber(field, attr);
				}
			}
		}

		/**
		 * Assert check box has correct state number.
		 * 
		 * @param field
		 *            the field
		 * @param attr
		 *            the attr
		 */
		static void assertCheckBoxHasCorrectStateNumber(GTCadseField field, IAttributeType<?> attr) {

			int actual = field.getCheckBoxNumberOfStates();

			for (KeyValue kv : list) {
				if (kv.getAttributeType().equals(attr)) {
					int expected = kv.getInteger();
					assertEquals("States number for attribute " + attr.getCSTName(), expected, actual);
					return;
				}
			}

			int expected = defaultStateNumber;
			assertEquals("States number for attribute " + attr.getCSTName(), expected, actual);
		}
	}
}
