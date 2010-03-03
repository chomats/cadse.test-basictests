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

	protected final String cadse_name = "CADSE_CannotBeUndefined";

	protected final GTTreePath cadse_model = new GTTreePath(cadse_name);
	protected final GTTreePath build_model = cadse_model.concat(CadseDefinitionManager.BUILD_MODEL);
	protected final GTTreePath data_model = cadse_model.concat(CadseDefinitionManager.DATA_MODEL);
	protected final GTTreePath mapping_model = cadse_model.concat(CadseDefinitionManager.MAPPING);

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

	@Test
	public void test_cadse_creation() throws Exception {
		createCadseDefinition(cadse_name, "model." + cadse_name);
	}

	@Test
	public void test_attr_bool() throws Exception {

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

		createItems(CadseGCST.BOOLEAN, "bool", tabUndef, tabDefVal, tabInit);
	}

	@Test
	public void test_numerical_attr() throws Exception {

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

		boolean[] tabUndef = { true, false };
		String[] tabDefVal = { "123", "" };
		boolean[] tabInit = { true, false };

		createItems(CadseGCST.DOUBLE, "double", tabUndef, tabDefVal, tabInit);
		createItems(CadseGCST.INTEGER, "int", tabUndef, tabDefVal, tabInit);
		createItems(CadseGCST.LONG, "long", tabUndef, tabDefVal, tabInit);
	}

	@Test
	public void test_attr_string() throws Exception {

		// _______=============================================
		// _______| cannot be undef | def value | must be init
		// ====================================================
		// = str1 | _____true______ | ___str___ | true
		// ====================================================
		// = str2 | _____true______ | ___str___ | false
		// ====================================================
		// = str3 | _____true______ | ___""____ | true
		// ====================================================
		// = str4 | _____true______ | ___""____ | false
		// ====================================================
		// = str5 | _____false_____ | ___str___ | true
		// ====================================================
		// = str6 | _____false_____ | ___str___ | false
		// ====================================================
		// = str7 | _____false_____ | ___""____ | true
		// ====================================================
		// = str8 | _____false_____ | ___""____ | false
		// ====================================================

		boolean[] tabUndef = { true, false };
		String[] tabDefVal = { "str", "" };
		boolean[] tabInit = { true, false };

		createItems(CadseGCST.STRING, "str", tabUndef, tabDefVal, tabInit);
	}

	private void createItems(ItemType attr, String prefix, boolean[] tabUndef, String[] tabDefVal, boolean[] tabInit) {

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
					createBasicAttribute(it_path, attr, attr_name, null, defVal, notHidden, init, notList);

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
