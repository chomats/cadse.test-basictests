package fr.imag.adele.cadse.test.basictests.link;

import org.junit.Test;

import fr.imag.adele.cadse.cadseg.managers.CadseDefinitionManager;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.graphictests.cadse.test.GTCadseRTConstants;
import fr.imag.adele.graphictests.cadse.test.GTCadseTestCase;
import fr.imag.adele.graphictests.gttree.GTTreePath;

public class Link_tc_CADSEg extends GTCadseTestCase {

	protected final String cadse_name = "CADSE_Link";
	protected GTTreePath cadse_model = new GTTreePath(cadse_name);
	protected GTTreePath build_model = cadse_model.concat(CadseDefinitionManager.BUILD_MODEL);
	protected GTTreePath data_model  = cadse_model.concat(CadseDefinitionManager.DATA_MODEL);
	
	/**
	 * Selects CADSEg in the launcher, and closes useless views. 
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void test_init() throws Exception {

		selectCadses(GTCadseRTConstants.CADSEG_MODEL);
		welcomeView.close();
		workspaceView.show();
		createCadseDefinition(cadse_name, "model." + cadse_name);
	}

	@Test
	public void test_source_destination() throws Exception {
		
		String prefix = "";
		String s1_name = prefix + "s1";
		String s2_name = prefix + "s2";
		String d1_name = prefix + "d1";
		String d2_name = prefix + "d2";
		GTTreePath s1_path = data_model.concat("s1");
		GTTreePath s2_path = data_model.concat("s2");
		GTTreePath d1_path = data_model.concat("d1");
		GTTreePath d2_path = data_model.concat("d2");
		
		/* Item Type definition */
		createItemType(data_model, s1_name, null,    notAbstract, root, defaultContent);
		createItemType(data_model, s2_name, s1_path, notAbstract, root, defaultContent);
		createItemType(data_model, d1_name, null,    notAbstract, root, defaultContent);
		createItemType(data_model, d2_name, d1_path, notAbstract, root, defaultContent);
		
		/* Link type definition */
		createLinkType("l11", s1_path, d1_path, "0", "unbounded");
		createLinkType("l12", s1_path, d2_path, "0", "unbounded");
		createLinkType("l21", s2_path, d1_path, "0", "unbounded");
		createLinkType("l22", s2_path, d2_path, "0", "unbounded");
	}
	
	@Test
	public void test_annotation() throws Exception {
	
		String prefix = "annotation_";
		String src_name = prefix + "src";
		String dst_name = prefix + "dst";
		GTTreePath src_path = data_model.concat(src_name);
		GTTreePath dst_path = data_model.concat(dst_name);
		
		/* Item Type definition */
		createItemType(data_model, src_name, null, notAbstract, root, defaultContent);
		createItemType(data_model, dst_name, null, notAbstract, root, defaultContent);
		
		/* Link type definition */
		createLinkType(prefix + "link", src_path, dst_path, "0", "unbounded", CadseGCST.LINK_TYPE_at_ANNOTATION_, true);
	}
	
	@Test
	public void test_part() throws Exception {
	
		String prefix = "part_";
		String src_name = prefix + "src";
		String dst_name = prefix + "dst";
		GTTreePath src_path = data_model.concat(src_name);
		GTTreePath dst_path = data_model.concat(dst_name);
		
		/* Item Type definition */
		createItemType(data_model, src_name, null, notAbstract, root, defaultContent);
		createItemType(data_model, dst_name, null, notAbstract, root, defaultContent);
		
		/* Link type definition */
		createLinkType(prefix + "link", src_path, dst_path, "0", "unbounded", CadseGCST.LINK_TYPE_at_PART_, true);
	}
}
