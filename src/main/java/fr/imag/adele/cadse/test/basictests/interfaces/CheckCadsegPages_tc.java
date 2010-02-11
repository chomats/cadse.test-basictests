package fr.imag.adele.cadse.test.basictests.interfaces;

import java.util.List;

import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.junit.Test;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.attribute.IAttributeType;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseFactory;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseShell;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseWorkbenchPart;
import fr.imag.adele.graphictests.cadse.test.GTCadseRTConstants;
import fr.imag.adele.graphictests.cadse.test.GTCadseTestCase;
import fr.imag.adele.graphictests.gttree.GTTreePath;
import fr.imag.adele.graphictests.gtworkbench_part.GTWorkbenchPart;
import fr.imag.adele.cadse.cadseg.managers.CadseDefinitionManager;


/**
 * Checks whereas the creation and modification pages have the correct layout.
 */
public class CheckCadsegPages_tc extends GTCadseTestCase {

	protected final String cadse_name = "my_CADSE";
	protected final String item_type_name = "my_item_type";
	protected GTTreePath cadse_model = new GTTreePath(cadse_name);
	protected GTTreePath build_model = cadse_model.concat(CadseDefinitionManager.BUILD_MODEL);
	protected GTTreePath data_model  = cadse_model.concat(CadseDefinitionManager.DATA_MODEL);
	protected GTTreePath mapping  = cadse_model.concat(CadseDefinitionManager.MAPPING);
	protected GTTreePath it_mit  = data_model.concat(item_type_name);
	
	/**
	 * Selects CADSEg in the launcher, and closes useless views. 
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void test_selection() throws Exception {

		shell = new GTCadseShell(GTCadseRTConstants.CADSE_SELECTOR_SHELL_TITLE);
		shell.selectCadses(GTCadseRTConstants.CADSEG_MODEL);
		shell.close();
		welcomeView.close();
		workspaceView.show();
	}

	@Test
	public void test_CADSE_Definition() throws Exception {
		String[] expected_creationCST = {"ITEM_at_NAME_", "CADSE_DEFINITION_at_PACKAGENAME_", "CADSE_lt_EXTENDS"};
		String[] expected_modifCST = {"ITEM_at_NAME_", "ITEM_at_DISPLAY_NAME_", "ITEM_at_QUALIFIED_NAME_", "CADSE_DEFINITION_at_PACKAGENAME_", "CADSE_DEFINITION_at_CADSE_NAME_", "CADSE_at_DESCRIPTION_", "CADSE_DEFINITION_at_COMMENTARY_", "CADSE_DEFINITION_at_VENDOR_NAME_", "CADSE_DEFINITION_at_VERSION_CADSE_", "CADSE_at_ITEM_REPO_LOGIN_", "CADSE_at_ITEM_REPO_PASSWD_", "CADSE_at_ITEM_REPO_URL_", "CADSE_at_DEFAULT_CONTENT_REPO_URL_", "CADSE_DEFINITION_at_IMPORTS_", "CADSE_lt_EXTENDS"};
		itemCreationTest(null, cadse_name, CadseGCST.CADSE_DEFINITION, expected_creationCST, expected_modifCST);
	}
	
	@Test
	public void test_Item_Type() throws Exception {
		String[] expected_creationCST = {"ITEM_at_NAME_", "ITEM_TYPE_lt_SUPER_TYPE", "ITEM_TYPE_at_IS_INSTANCE_ABSTRACT_", "ITEM_TYPE_at_IS_ROOT_ELEMENT_", "ITEM_TYPE_at_HAS_CONTENT_"};
		String[] expected_modifCST = {"ITEM_at_NAME_", "ITEM_at_DISPLAY_NAME_", "ITEM_at_QUALIFIED_NAME_", "ITEM_TYPE_lt_SUPER_TYPE", "ITEM_TYPE_at_ICON_", "ITEM_TYPE_at_PACKAGE_NAME_", "ITEM_TYPE_at_ITEM_FACTORY_", "ITEM_TYPE_at_IS_INSTANCE_ABSTRACT_", "ITEM_TYPE_at_IS_INSTANCE_HIDDEN_", "ITEM_TYPE_at_IS_META_ITEM_TYPE_", "ITEM_TYPE_at_IS_ROOT_ELEMENT_", "ITEM_TYPE_at_HAS_CONTENT_"};
		itemCreationTest(data_model, item_type_name ,CadseGCST.ITEM_TYPE, expected_creationCST, expected_modifCST);
	}
		
	@Test
	public void test_Boolean_Attribute() throws Exception {
		String[] expected_creationCST = {"ITEM_at_NAME_", "ATTRIBUTE_at_DEFAULT_VALUE_", "ATTRIBUTE_at_HIDDEN_IN_COMPUTED_PAGES_", "ATTRIBUTE_at_MUST_BE_INITIALIZED_", "ATTRIBUTE_at_IS_LIST_", "ATTRIBUTE_at_TWEVOL_", "ATTRIBUTE_at_TWCOMMIT_KIND_", "ATTRIBUTE_at_TWREV_SPECIFIC_", "ATTRIBUTE_at_TWUPDATE_KIND_"};
		String[] expected_modifCST = {"ITEM_at_NAME_", "ITEM_at_DISPLAY_NAME_", "ITEM_at_QUALIFIED_NAME_", "ATTRIBUTE_at_DEFAULT_VALUE_", "ATTRIBUTE_at_HIDDEN_IN_COMPUTED_PAGES_", "ATTRIBUTE_at_MUST_BE_INITIALIZED_", "ATTRIBUTE_at_IS_LIST_", "ATTRIBUTE_at_TWEVOL_", "ATTRIBUTE_at_TWCOMMIT_KIND_", "ATTRIBUTE_at_TWREV_SPECIFIC_", "ATTRIBUTE_at_TWUPDATE_KIND_", "ATTRIBUTE_at_CANNOT_BE_UNDEFINED_", "ATTRIBUTE_at_FINAL_", "ATTRIBUTE_at_NATIF_", "ATTRIBUTE_at_REQUIRE_", "ATTRIBUTE_at_TRANSIENT_"};
		itemCreationTest(it_mit, "my_boolean", CadseGCST.BOOLEAN, expected_creationCST, expected_modifCST);
	}
	
	@Test
	public void test_Double_Attribute() throws Exception {
		String[] expected_creationCST = {"ITEM_at_NAME_", "ATTRIBUTE_at_DEFAULT_VALUE_", "ATTRIBUTE_at_HIDDEN_IN_COMPUTED_PAGES_", "ATTRIBUTE_at_MUST_BE_INITIALIZED_", "ATTRIBUTE_at_IS_LIST_", "ATTRIBUTE_at_TWEVOL_", "ATTRIBUTE_at_TWCOMMIT_KIND_", "ATTRIBUTE_at_TWREV_SPECIFIC_", "ATTRIBUTE_at_TWUPDATE_KIND_"};
		String[] expected_modifCST = {"ITEM_at_NAME_", "ITEM_at_DISPLAY_NAME_", "ITEM_at_QUALIFIED_NAME_", "ATTRIBUTE_at_DEFAULT_VALUE_", "ATTRIBUTE_at_HIDDEN_IN_COMPUTED_PAGES_", "ATTRIBUTE_at_MUST_BE_INITIALIZED_", "ATTRIBUTE_at_IS_LIST_", "ATTRIBUTE_at_TWEVOL_", "ATTRIBUTE_at_TWCOMMIT_KIND_", "ATTRIBUTE_at_TWREV_SPECIFIC_", "ATTRIBUTE_at_TWUPDATE_KIND_", "ATTRIBUTE_at_CANNOT_BE_UNDEFINED_", "ATTRIBUTE_at_FINAL_", "ATTRIBUTE_at_NATIF_", "ATTRIBUTE_at_REQUIRE_", "ATTRIBUTE_at_TRANSIENT_"};
		itemCreationTest(it_mit, "my_double", CadseGCST.DOUBLE, expected_creationCST, expected_modifCST);
	}
	
	@Test
	public void test_Integer_Attribute() throws Exception {
		String[] expected_creationCST = {"ITEM_at_NAME_", "ATTRIBUTE_at_DEFAULT_VALUE_", "ATTRIBUTE_at_HIDDEN_IN_COMPUTED_PAGES_", "ATTRIBUTE_at_MUST_BE_INITIALIZED_", "ATTRIBUTE_at_IS_LIST_", "ATTRIBUTE_at_TWEVOL_", "ATTRIBUTE_at_TWCOMMIT_KIND_", "ATTRIBUTE_at_TWREV_SPECIFIC_", "ATTRIBUTE_at_TWUPDATE_KIND_"};
		String[] expected_modifCST = {"ITEM_at_NAME_", "ITEM_at_DISPLAY_NAME_", "ITEM_at_QUALIFIED_NAME_", "ATTRIBUTE_at_DEFAULT_VALUE_", "ATTRIBUTE_at_HIDDEN_IN_COMPUTED_PAGES_", "ATTRIBUTE_at_MUST_BE_INITIALIZED_", "ATTRIBUTE_at_IS_LIST_", "ATTRIBUTE_at_TWEVOL_", "ATTRIBUTE_at_TWCOMMIT_KIND_", "ATTRIBUTE_at_TWREV_SPECIFIC_", "ATTRIBUTE_at_TWUPDATE_KIND_", "ATTRIBUTE_at_CANNOT_BE_UNDEFINED_", "ATTRIBUTE_at_FINAL_", "ATTRIBUTE_at_NATIF_", "ATTRIBUTE_at_REQUIRE_", "ATTRIBUTE_at_TRANSIENT_"};
		itemCreationTest(it_mit, "my_integer", CadseGCST.INTEGER, expected_creationCST, expected_modifCST);
	}
	
	@Test
	public void test_Long_Attribute() throws Exception {
		String[] expected_creationCST = {"ITEM_at_NAME_", "ATTRIBUTE_at_DEFAULT_VALUE_", "ATTRIBUTE_at_HIDDEN_IN_COMPUTED_PAGES_", "ATTRIBUTE_at_MUST_BE_INITIALIZED_", "ATTRIBUTE_at_IS_LIST_", "ATTRIBUTE_at_TWEVOL_", "ATTRIBUTE_at_TWCOMMIT_KIND_", "ATTRIBUTE_at_TWREV_SPECIFIC_", "ATTRIBUTE_at_TWUPDATE_KIND_"};
		String[] expected_modifCST = {"ITEM_at_NAME_", "ITEM_at_DISPLAY_NAME_", "ITEM_at_QUALIFIED_NAME_", "ATTRIBUTE_at_DEFAULT_VALUE_", "ATTRIBUTE_at_HIDDEN_IN_COMPUTED_PAGES_", "ATTRIBUTE_at_MUST_BE_INITIALIZED_", "ATTRIBUTE_at_IS_LIST_", "ATTRIBUTE_at_TWEVOL_", "ATTRIBUTE_at_TWCOMMIT_KIND_", "ATTRIBUTE_at_TWREV_SPECIFIC_", "ATTRIBUTE_at_TWUPDATE_KIND_", "ATTRIBUTE_at_CANNOT_BE_UNDEFINED_", "ATTRIBUTE_at_FINAL_", "ATTRIBUTE_at_NATIF_", "ATTRIBUTE_at_REQUIRE_", "ATTRIBUTE_at_TRANSIENT_"};
		itemCreationTest(it_mit, "my_long", CadseGCST.LONG, expected_creationCST, expected_modifCST);
	}
	
	@Test
	public void test_String_Attribute() throws Exception {
		String[] expected_creationCST = {"ITEM_at_NAME_", "ATTRIBUTE_at_DEFAULT_VALUE_", "ATTRIBUTE_at_HIDDEN_IN_COMPUTED_PAGES_", "ATTRIBUTE_at_MUST_BE_INITIALIZED_", "ATTRIBUTE_at_IS_LIST_", "ATTRIBUTE_at_TWEVOL_", "ATTRIBUTE_at_TWCOMMIT_KIND_", "ATTRIBUTE_at_TWREV_SPECIFIC_", "ATTRIBUTE_at_TWUPDATE_KIND_"};
		String[] expected_modifCST = {"ITEM_at_NAME_", "ITEM_at_DISPLAY_NAME_", "ITEM_at_QUALIFIED_NAME_", "ATTRIBUTE_at_DEFAULT_VALUE_", "ATTRIBUTE_at_HIDDEN_IN_COMPUTED_PAGES_", "ATTRIBUTE_at_MUST_BE_INITIALIZED_", "ATTRIBUTE_at_IS_LIST_", "ATTRIBUTE_at_TWEVOL_", "ATTRIBUTE_at_TWCOMMIT_KIND_", "ATTRIBUTE_at_TWREV_SPECIFIC_", "ATTRIBUTE_at_TWUPDATE_KIND_", "ATTRIBUTE_at_CANNOT_BE_UNDEFINED_", "ATTRIBUTE_at_FINAL_", "ATTRIBUTE_at_NATIF_", "ATTRIBUTE_at_REQUIRE_", "ATTRIBUTE_at_TRANSIENT_", "STRING_at_NOT_EMPTY_"};
		itemCreationTest(it_mit, "my_string", CadseGCST.STRING, expected_creationCST, expected_modifCST);
	}
	
	@Test
	public void test_LinkType_Attribute() throws Exception {
		String[] expected_creationCST = {"ITEM_at_NAME_", "LINK_TYPE_lt_DESTINATION", "LINK_TYPE_at_ANNOTATION_", "LINK_TYPE_at_AGGREGATION_", "LINK_TYPE_at_COMPOSITION_", "LINK_TYPE_at_PART_", "LINK_TYPE_at_REQUIRE_", "LINK_TYPE_at_MAPPING_", "LINK_TYPE_at_GROUP_", "LINK_TYPE_at_HIDDEN_", "LINK_TYPE_at_MIN_", "LINK_TYPE_at_MAX_", "ATTRIBUTE_at_HIDDEN_IN_COMPUTED_PAGES_", "ATTRIBUTE_at_MUST_BE_INITIALIZED_", "ATTRIBUTE_at_TWEVOL_", "ATTRIBUTE_at_TWCOMMIT_KIND_", "ATTRIBUTE_at_TWREV_SPECIFIC_", "ATTRIBUTE_at_TWUPDATE_KIND_", "LINK_TYPE_at_TWCOUPLED_", "LINK_TYPE_at_TWDEST_EVOL_"};
		String[] expected_modifCST = {"ITEM_at_NAME_", "ITEM_at_DISPLAY_NAME_", "ITEM_at_QUALIFIED_NAME_", "LINK_TYPE_lt_SOURCE", "LINK_TYPE_lt_DESTINATION", "LINK_TYPE_lt_INVERSE_LINK", "LINK_TYPE_at_ANNOTATION_", "LINK_TYPE_at_AGGREGATION_", "LINK_TYPE_at_COMPOSITION_", "LINK_TYPE_at_PART_", "LINK_TYPE_at_REQUIRE_", "LINK_TYPE_at_MAPPING_", "LINK_TYPE_at_GROUP_", "LINK_TYPE_at_HIDDEN_", "LINK_TYPE_at_SELECTION_", "LINK_TYPE_at_LINK_MANAGER_", "LINK_TYPE_at_MIN_", "LINK_TYPE_at_MAX_", "ATTRIBUTE_at_HIDDEN_IN_COMPUTED_PAGES_", "ATTRIBUTE_at_MUST_BE_INITIALIZED_", "ATTRIBUTE_at_TWEVOL_", "ATTRIBUTE_at_TWCOMMIT_KIND_", "ATTRIBUTE_at_TWREV_SPECIFIC_", "ATTRIBUTE_at_TWUPDATE_KIND_", "LINK_TYPE_at_TWCOUPLED_", "LINK_TYPE_at_TWDEST_EVOL_", "ATTRIBUTE_at_TRANSIENT_"};
		itemCreationTest(it_mit, "my_link_type", CadseGCST.LINK_TYPE, expected_creationCST, expected_modifCST);
	}
	
	@Test
	public void test_Enum_Type() throws Exception {
		String[] expected_creationCST = {"ITEM_at_NAME_", "ENUM_TYPE_at_VALUES_"};
		String[] expected_modifCST = {"ITEM_at_NAME_", "ITEM_at_DISPLAY_NAME_", "ITEM_at_QUALIFIED_NAME_", "ENUM_TYPE_at_JAVA_CLASS_", "ENUM_TYPE_at_MUST_BE_GENERATED_", "ENUM_TYPE_at_VALUES_"};		
		itemCreationTest(data_model, "my_enum_type", CadseGCST.ENUM_TYPE, expected_creationCST, expected_modifCST);
	}
	
	@Test
	public void test_Enum_Attribute() throws Exception {
		String[] expected_creationCST = {"ITEM_at_NAME_", "ENUM_lt_ENUM_TYPE", "ATTRIBUTE_at_DEFAULT_VALUE_", "ATTRIBUTE_at_HIDDEN_IN_COMPUTED_PAGES_", "ATTRIBUTE_at_MUST_BE_INITIALIZED_", "ATTRIBUTE_at_IS_LIST_", "ATTRIBUTE_at_TWEVOL_", "ATTRIBUTE_at_TWCOMMIT_KIND_", "ATTRIBUTE_at_TWREV_SPECIFIC_", "ATTRIBUTE_at_TWUPDATE_KIND_"};
		String[] expected_modifCST = {"ITEM_at_NAME_", "ITEM_at_DISPLAY_NAME_", "ITEM_at_QUALIFIED_NAME_", "ENUM_lt_ENUM_TYPE", "ATTRIBUTE_at_DEFAULT_VALUE_", "ATTRIBUTE_at_HIDDEN_IN_COMPUTED_PAGES_", "ATTRIBUTE_at_MUST_BE_INITIALIZED_", "ATTRIBUTE_at_IS_LIST_", "ATTRIBUTE_at_TWEVOL_", "ATTRIBUTE_at_TWCOMMIT_KIND_", "ATTRIBUTE_at_TWREV_SPECIFIC_", "ATTRIBUTE_at_TWUPDATE_KIND_", "ATTRIBUTE_at_CANNOT_BE_UNDEFINED_", "ATTRIBUTE_at_FINAL_", "ATTRIBUTE_at_NATIF_", "ATTRIBUTE_at_REQUIRE_", "ATTRIBUTE_at_TRANSIENT_"};
		itemCreationTest(it_mit, "my_enum", CadseGCST.ENUM, expected_creationCST, expected_modifCST);
	}
	
	@Test
	public void test_Manager() throws Exception {
		/* FIXME MANAGER_at_HUMAN_NAME_ should be removed soon from the modification page */
		String[] expected_modifCST = {"ITEM_at_NAME_", "ITEM_at_DISPLAY_NAME_", "ITEM_at_QUALIFIED_NAME_", "MANAGER_at_HUMAN_NAME_"};;
		checkModificationPage(mapping.concat(item_type_name + "-manager"), CadseGCST.MANAGER, expected_modifCST);
	}
	
	/**
	 * Attribute creation test.
	 * 
	 * @param path                     the path to an item in the workspace view which will be the source for the creation
	 * @param attributeTypeName        the name of the attribute type as it appears in the contextual new menu
	 * @param attributeName            the name of the new item
	 * @param itConstant               the CADSEG item type constant
	 * @param expected_creationCST     the expected creation page attributes constants
	 * @param expected_modifCST        the expected modification page attributes constants
	 * 
	 * @throws WidgetNotFoundException the widget not found exception
	 */
	private void itemCreationTest(GTTreePath path, String attributeName, ItemType itConstant, String[] expected_creationCST, String[] expected_modifCST)
	throws WidgetNotFoundException {
		
		// Attribute creation and creation page checking
		GTTreePath completePath = checkCreationPage(path, attributeName, itConstant, expected_creationCST);
		
		// The creation process shouldn't have induced compilation error/
		checkCompilationErrors(workspaceView, cadse_model); 
		
		// Modification page
		checkModificationPage(completePath, itConstant, expected_modifCST);		
	}
	
	/**
	 * Creates an item and checks if the creation page displays the correct set of fields.
	 * 
	 * @param path                   The path in the workspace view used for item creation
	 * @param attributeName          The name of the new attribute
	 * @param itConstant             The ItemType constant of this attribute
	 * @param expected_creationCST   The list of expected fields attributes constants.
	 * 
	 * @return the tree path to find the created item in the workspace view
	 */
	private GTTreePath checkCreationPage(GTTreePath path, String attributeName, ItemType itConstant, String[] expected_creationCST)
	{
		// Creation
		workspaceView.contextMenuNew(path, itConstant).click();
		GTCadseShell shell = new GTCadseShell(itConstant);
		String[] creationCST = GTCadseFactory.findCadseWorkbenchPart(shell).findAttributeConstants();
	    String creationStr = getStringDef(shell);
	    GTCadseFactory.findCadseField(shell, CadseGCST.ITEM_at_NAME_).typeText(attributeName);
	    if (itConstant == CadseGCST.LINK_TYPE)
	    	GTCadseFactory.findCadseField(shell, CadseGCST.LINK_TYPE_lt_DESTINATION).browser(cadse_name, CadseDefinitionManager.DATA_MODEL, item_type_name);
	    else if (itConstant == CadseGCST.CADSE_DEFINITION)
	    	GTCadseFactory.findCadseField(shell, CadseGCST.CADSE_DEFINITION_at_PACKAGENAME_).typeText("model.myCadse");
	    else if (itConstant == CadseGCST.ENUM_TYPE)
	    	GTCadseFactory.findCadseField(shell, CadseGCST.ENUM_TYPE_at_VALUES_).add("one", "two", "three");
	    else if (itConstant == CadseGCST.ENUM)
	    	GTCadseFactory.findCadseField(shell, CadseGCST.ENUM_lt_ENUM_TYPE).browser("my_enum_type");
	    shell.close();
		
	    // Compute complete path
	    GTTreePath completePath = null;
	    if (path != null)
	    	completePath = path.concat(attributeName);
	    else
	    	completePath = new GTTreePath(attributeName);
	    	    
		// FIXME next line will have to be removed as soon as the bug will be corrected	with Enum Type Refresh
		workspaceView.menu("refresh").click();
	    
	    // Assert item has been created
	    workspaceView.selectNode(completePath);
	    
	    
		// Performs test
		if (attributesCSTEquals(creationCST, expected_creationCST) == false)
			throw new WidgetNotFoundException("The workbench part doesn't contains expected attributes. Expected String : " + creationStr);
		
		return completePath;
	}
	
	/**
	 *  Checks if a modification page displays correct fields in a given section for a given item.
	 * 
	 * @param path       The item path in the workspace view 
	 * @param tab        The tab of the property page. Can be null.
	 * @param section    The section where to look for constants. Can be null.
	 * @param expected   The list of expected fields attributes constants.
	 */
	private void checkModificationPage(GTTreePath path, ItemType itConstant, String[] expected)
	{
		String tab = tabLabel(itConstant);
		String section = sectionLabel(itConstant);
		
		try {
			// Selects node into the tree
			workspaceView.selectNode(path);
			
			// Displays property view
			if (tab != null)
				propertiesView.showTab(tab);
			else
				propertiesView.show();
		} catch (Exception e) {
			// Selects node into the tree
			workspaceView.selectNode(path);
			
			// Displays property view
			if (tab != null)
				propertiesView.showTab(tab);
			else
				propertiesView.show();
		}
		
		// Section decoding
		GTCadseWorkbenchPart wp;
		if (section != null)
			wp = GTCadseFactory.findCadseWorkbenchPart(propertiesView.findSection(section));
		else
			wp = GTCadseFactory.findCadseWorkbenchPart(propertiesView);
		
		// Gets attributes constants
		String[] modifCST = wp.findAttributeConstants();
		String modifStr = getStringDef(propertiesView.findSection(section));
		
		// Attribute comparison
		if (attributesCSTEquals(modifCST, expected) == false)
			throw new WidgetNotFoundException("The workbench part doesn't contains expected attributes. Expected String : " + modifStr);
	}

	/**
	 * Tests is two string arrays are equal.
	 * 
	 * @param provided   The first string array
	 * @param expected   The second string array
	 * 
	 * @return a boolean
	 */
	private boolean attributesCSTEquals(String[] provided, String[] expected) {
		
		if (provided.length != expected.length)
			return false;
		
		for (int i=0; i< provided.length; i++)
			if (! provided[i].equals(expected[i]))
				return false;	
		
		return true;
	}
		
	/**
	 * Return a java piece of code with the definition of a list of strings 
	 * which ate the constants of fields found in the workbench part given into
	 * parameter. For debug purpose only.
	 * 
	 * @param    wp the workbench part for searching fields 
	 * 
	 * @return   the java code   
	 */
	private String getStringDef(GTWorkbenchPart wp) {
		
		List<IAttributeType<?>> attrlist = GTCadseFactory.findCadseWorkbenchPart(wp).findAttributeDefinition();
				
		StringBuilder sb = new StringBuilder();		
		boolean begin = true;
		
		sb.append("{");
		for (IAttributeType<?> attr : attrlist) {
			if (begin)
				begin = false;
			else
				sb.append(", ");
			sb.append("\"");
			sb.append(attr.getCSTName());
			sb.append("\"");
		}
		sb.append("};");
		
		return sb.toString();
	}
}
