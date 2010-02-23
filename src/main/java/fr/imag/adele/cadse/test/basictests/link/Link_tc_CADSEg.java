package fr.imag.adele.cadse.test.basictests.link;

import org.junit.Test;

import fr.imag.adele.cadse.cadseg.managers.CadseDefinitionManager;
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
		
		GTTreePath s1 = data_model.concat("s1");
		createItemType(data_model, "s1", null, notAbstract, root, defaultContent);
		GTTreePath s2 = data_model.concat("s2");
		createItemType(data_model, "s2", s1,   notAbstract, root, defaultContent);
		
		GTTreePath d1 = data_model.concat("d1");
		createItemType(data_model, "d1", null, notAbstract, root, defaultContent);
		GTTreePath d2 = data_model.concat("d2");
		createItemType(data_model, "d2", d1,   notAbstract, root, defaultContent);
		
		createLinkType(s1, "l11", d1, "0", "unbounded");
		createLinkType(s1, "l12", d2, "0", "unbounded");
		createLinkType(s2, "l21", d1, "0", "unbounded");
		createLinkType(s2, "l22", d2, "0", "unbounded");
	}
}
