package fr.imag.adele.cadse.test.basictests.basicproperties;

import org.junit.Test;

import fr.imag.adele.cadse.cadseg.managers.CadseDefinitionManager;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.graphictests.cadse.test.GTCadseRTConstants;
import fr.imag.adele.graphictests.gttree.GTTreePath;

/**
 * Tests basic properties for double attributes.
 * <ul>
 * <li>default value</li>
 * <li>hidden in computed pages</li>
 * <li>must be initialized</li>
 * <li>cannot be undefined</li>
 * </ul>
 */
public class BasicProperties_double_tc_CADSEg extends BasicProperties_number_tc_CADSEg {

	protected final String attr_name = "double";
	protected final String cadse_name = "CADSE_BasicProperties_" + attr_name;
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
	 * Creates a set of double attributes.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void test_double() throws Exception {

		String[] defVal = { null, "123" };
		boolean[] sicp = { true, false };
		boolean[] simp = { true, false };
		boolean[] cbu = { true, false };

		createItems(data_model, CadseGCST.DOUBLE, attr_name, defVal, sicp, simp, cbu);
	}
}
