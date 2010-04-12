package fr.imag.adele.cadse.test.basictests.link;

import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.checkCompilationErrors;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.createCadseDefinition;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.createItemType;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.createLinkType;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.selectCadses;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.workspaceView;
import static fr.imag.adele.graphictests.cadse.test.KeyValue.notAbstractKv;
import static fr.imag.adele.graphictests.cadse.test.KeyValue.partKv;
import static fr.imag.adele.graphictests.cadse.test.KeyValue.rootKv;

import org.junit.Test;

import fr.imag.adele.cadse.cadseg.managers.CadseDefinitionManager;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.graphictests.cadse.test.GTCadseRTConstants;
import fr.imag.adele.graphictests.cadse.test.KeyValue;
import fr.imag.adele.graphictests.gttree.GTTreePath;
import fr.imag.adele.graphictests.test.GTTestCase;
import fr.imag.adele.graphictests.test.GTTestParameters;

public class Link_tc_CADSEg extends GTTestCase {

	protected final String cadse_name = "CADSE_Link";
	protected GTTreePath cadse_model = new GTTreePath(cadse_name);
	protected GTTreePath build_model = cadse_model.concat(CadseDefinitionManager.BUILD_MODEL);
	protected GTTreePath data_model = cadse_model.concat(CadseDefinitionManager.DATA_MODEL);

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
		createCadseDefinition(cadse_name, "model." + cadse_name, GTTestParameters.TIMEOUT);
	}

	@Test
	public void test_minMax() throws Exception {

		String type_prefix = "min_max_";
		String s1_name = type_prefix + "s1";
		String d1_name = type_prefix + "d1";
		GTTreePath s1_path = data_model.concat(s1_name);
		GTTreePath d1_path = data_model.concat(d1_name);
		String link_name = type_prefix + "link";

		/* Item Type definition */
		createItemType(data_model, s1_name, notAbstractKv, rootKv);
		createItemType(data_model, d1_name, notAbstractKv, rootKv);

		/* Link type definition */
		createLinkType(link_name, s1_path, d1_path, "0", "2");
	}

	@Test
	public void test_src_dst() throws Exception {

		String type_prefix = "src_dst_";
		String s1_name = type_prefix + "s1";
		String s2_name = type_prefix + "s2";
		String d1_name = type_prefix + "d1";
		String d2_name = type_prefix + "d2";
		GTTreePath s1_path = data_model.concat(s1_name);
		GTTreePath s2_path = data_model.concat(s2_name);
		GTTreePath d1_path = data_model.concat(d1_name);
		GTTreePath d2_path = data_model.concat(d2_name);
		String l11_name = type_prefix + "l11";
		String l12_name = type_prefix + "l12";
		String l21_name = type_prefix + "l21";
		String l22_name = type_prefix + "l22";

		/* Item Type definition */
		createItemType(data_model, s1_name, notAbstractKv, rootKv);
		createItemType(data_model, s2_name, new KeyValue(CadseGCST.ITEM_TYPE_lt_SUPER_TYPE, s1_path), notAbstractKv,
				rootKv);
		createItemType(data_model, d1_name, notAbstractKv, rootKv);
		createItemType(data_model, d2_name, new KeyValue(CadseGCST.ITEM_TYPE_lt_SUPER_TYPE, d1_path), notAbstractKv,
				rootKv);

		/* Link type definition */
		createLinkType(l11_name, s1_path, d1_path, "0", "unbounded");
		createLinkType(l12_name, s1_path, d2_path, "0", "unbounded");
		createLinkType(l21_name, s2_path, d1_path, "0", "unbounded");
		createLinkType(l22_name, s2_path, d2_path, "0", "unbounded");
	}

	@Test
	public void test_annotation() throws Exception {

		String type_prefix = "annotation_";
		String src_name = type_prefix + "src";
		String dst_name = type_prefix + "dst";
		String link_name = type_prefix + "link";
		GTTreePath src_path = data_model.concat(src_name);
		GTTreePath dst_path = data_model.concat(dst_name);

		/* Item Type definition */
		createItemType(data_model, src_name, notAbstractKv, rootKv);
		createItemType(data_model, dst_name, notAbstractKv, rootKv);

		/* Link type definition */
		createLinkType(link_name, src_path, dst_path, "0", "unbounded", new KeyValue(
				CadseGCST.LINK_TYPE_at_ANNOTATION_, true));
	}

	@Test
	public void test_part() throws Exception {

		String type_prefix = "part_";
		String src_name = type_prefix + "src";
		String dst_name = type_prefix + "dst";
		String link_name = type_prefix + "link";
		GTTreePath src_path = data_model.concat(src_name);
		GTTreePath dst_path = data_model.concat(dst_name);

		/* Item Type definition */
		createItemType(data_model, src_name, notAbstractKv, rootKv);
		createItemType(data_model, dst_name, notAbstractKv, rootKv);

		/* Link type definition */
		createLinkType(link_name, src_path, dst_path, "0", "unbounded", partKv);
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
}
