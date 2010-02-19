package fr.imag.adele.cadse.test.basictests.cannotbeundefined;

import org.junit.Test;

import fr.imag.adele.cadse.cadseg.managers.CadseDefinitionManager;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseFactory;
import fr.imag.adele.graphictests.cadse.test.GTCadseRTConstants;
import fr.imag.adele.graphictests.cadse.test.GTCadseTestCase;
import fr.imag.adele.graphictests.gttree.GTTreePath;

public class CannotBeUndefined_tc_CADSEg extends GTCadseTestCase {

	protected final String cadse_name  = "CADSE_CannotBeUndefined";
	
	protected final GTTreePath cadse_model   = new GTTreePath(cadse_name);
	protected final GTTreePath build_model   = cadse_model.concat(CadseDefinitionManager.BUILD_MODEL);
	protected final GTTreePath data_model    = cadse_model.concat(CadseDefinitionManager.DATA_MODEL);
	protected final GTTreePath mapping_model = cadse_model.concat(CadseDefinitionManager.MAPPING);

	/**
	 * Selects CADSEg in the launcher, and closes useless views. 
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void test_selection() throws Exception {

		selectCadses(GTCadseRTConstants.CADSEG_MODEL);
		welcomeView.close();
		workspaceView.show();
	}
	
	@Test
	public void test_cadse_creation() throws Exception {
		createCadseDefinition(cadse_name, "model." + cadse_name);
	}
	
	@Test
	public void test_attr_bool() throws Exception {
	
		//          ==============================================
		//          | cannot be undef | def value | must be init |
		// =======================================================
		// = bool1  |      true       |    true   |    true      |
		// =======================================================
		// = bool2  |      true       |    true   |    false     | 
		// =======================================================
		// = bool3  |      true       |   false   |    true      |
		// =======================================================
		// = bool4  |      true       |   false   |    false     |
		// =======================================================
		// = bool5  |      true       |           |    true      |
		// =======================================================
		// = bool6  |      true       |           |    false     |
		// =======================================================
		// = bool7  |      false      |    true   |    true      |
		// =======================================================
		// = bool8  |      false      |    true   |    false     |
		// =======================================================
		// = bool9  |      false      |   false   |    true      |
		// =======================================================
		// = bool10 |      false      |   false   |    false     |
		// =======================================================
		// = bool11 |      false      |           |    true      |
		// =======================================================
		// = bool12 |      false      |           |    false     |
		// =======================================================

		boolean[] tabUndef = {true, false};
		String[] tabDefVal = {"true", "false", ""};
		boolean[] tabInit  = {true, false};
		
		createItems(CadseGCST.BOOLEAN, "bool", tabUndef, tabDefVal, tabInit);
	}

	@Test
	public void test_attr_integer() throws Exception {
	
		//          ==============================================
		//          | cannot be undef | def value | must be init |
		// =======================================================
		// =  int1  |      true       |    123    |    true      |
		// =======================================================
		// =  int2  |      true       |    123    |    false     |
		// =======================================================
		// =  int3  |      true       |    ""     |    true      |
		// =======================================================
		// =  int4  |      true       |    ""     |    false     |
		// =======================================================
		// =  int5  |      false      |    123    |    true      |
		// =======================================================
		// =  int6  |      false      |    123    |    false     |
		// =======================================================
		// =  int7  |      false      |    ""     |    true      |
		// =======================================================
		// =  int8  |      false      |    ""     |    false     |
		// =======================================================

		boolean[] tabUndef = {true, false};
		String[] tabDefVal = {"123", ""};
		boolean[] tabInit  = {true, false};
		
		createItems(CadseGCST.INTEGER, "int", tabUndef, tabDefVal, tabInit);
	}
	
	@Test
	public void test_attr_string() throws Exception {
	
		//          ==============================================
		//          | cannot be undef | def value | must be init |
		// =======================================================
		// =  str1  |      true       |    str    |    true      |
		// =======================================================
		// =  str2  |      true       |    str    |    false     |
		// =======================================================
		// =  str3  |      true       |    ""     |    true      |
		// =======================================================
		// =  str4  |      true       |    ""     |    false     |
		// =======================================================
		// =  str5  |      false      |    str    |    true      |
		// =======================================================
		// =  str6  |      false      |    str    |    false     |
		// =======================================================
		// =  str7  |      false      |    ""     |    true      |
		// =======================================================
		// =  str8  |      false      |    ""     |    false     |
		// =======================================================

		boolean[] tabUndef = {true, false};
		String[] tabDefVal = {"str", ""};
		boolean[] tabInit  = {true, false};
		
		createItems(CadseGCST.STRING, "str", tabUndef, tabDefVal, tabInit);
	}
		
	private void createItems(ItemType attr, String prefix, boolean[] tabUndef, String[] tabDefVal, boolean[] tabInit) {

		// Values
		final Boolean notAbstract = false;
		final Boolean root = true;
		final Boolean defaultContent = null;
		final Boolean notHidden = false;
		final Boolean notList = false;
		
		
		int i=1;
		for (boolean undef : tabUndef) {
			for (String defVal : tabDefVal) {
				for (boolean init : tabInit) {
					
					/* Item type creation */
					String it_name = "my_" + prefix + i;
					GTTreePath it_path = data_model.concat(it_name);
					createItemType(data_model, it_name, null, notAbstract, root, defaultContent);
					
					/* Attribute creation */
					String attr_name =  prefix + "_attr";
					GTTreePath attr_path = it_path.concat(attr_name);
					createBasicAttribute(it_path, attr, attr_name, null, defVal, notHidden, init, notList);
					
					/* Cannot be undefined attribute */
					try {
						propertiesView.showTab(attr.getDisplayName());
					}
					catch (Exception e) {
						workspaceView.selectNode(attr_path);
						propertiesView.showTab(attr.getDisplayName());
					}
					GTCadseFactory.findCadseField(propertiesView, CadseGCST.ATTRIBUTE_at_CANNOT_BE_UNDEFINED_).check(undef);			
					
					/* Increment */
					i++;
				}
			}
		}
	}	
}