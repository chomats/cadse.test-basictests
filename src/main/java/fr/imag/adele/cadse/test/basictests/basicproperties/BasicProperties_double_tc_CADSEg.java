package fr.imag.adele.cadse.test.basictests.basicproperties;

import org.junit.Test;

import fr.imag.adele.graphictests.cadse.test.GTCadseRTConstants;

/**
 * Tests basic properties for double attributes.
 * <ul>
 * <li>default value</li>
 * <li>hidden in computed pages</li>
 * <li>must be initialized</li>
 * <li>cannot be undefined</li>
 * </ul>
 */
public class BasicProperties_double_tc_CADSEg extends BasicProperties_number_tc_Common {

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
	}

	/**
	 * Creates a set of double attributes.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void test_double() throws Exception {
		BasicProperties_double_data data = new BasicProperties_double_data();
		data.createAll();
	}
}
