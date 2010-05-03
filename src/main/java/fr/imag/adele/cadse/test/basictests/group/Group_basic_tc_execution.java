package fr.imag.adele.cadse.test.basictests.group;

import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.selectCadses;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.workspaceView;

import org.junit.Test;

import fr.imag.adele.graphictests.test.GTPreferences;
import fr.imag.adele.graphictests.test.GTTestCase;

/**
 */
public class Group_basic_tc_execution extends GTTestCase {

	/**
	 * Selects the CADSE to be executed and closes unless views.
	 */
	@Test
	public void test_preparation() throws Exception {

		selectCadses(GTPreferences.TIMEOUT, "Cadse Model.Workspace.CADSE_group_basic");
		welcomeView.close();
		workspaceView.show();
	}

	@Test
	public void test_basic() throws Exception {
	}
}
