package fr.imag.adele.cadse.test.basictests.basicproperties;

import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.selectCadses;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.workspaceView;

import java.util.ArrayList;

import org.junit.Test;

import fr.imag.adele.graphictests.test.GTTestCase;

/**
 */
public class BasicProperties_long_tc_execution extends GTTestCase {

	/**
	 * Selects the CADSE to be executed and closes unless views.
	 */
	@Test
	public void test_preparation() throws Exception {

		selectCadses("Cadse Model.Workspace.CADSE_BasicProperties_long");
		welcomeView.close();
		workspaceView.show();
	}

	@Test
	public void test_long() throws Exception {

		BasicPropertiesLongTestDriver longTest = new BasicPropertiesLongTestDriver();

		ArrayList<Integer> exclude = new ArrayList<Integer>();
		exclude.add(2); // it's possible to add an empty string in a list...
		exclude.add(6); // it's possible to add an empty string in a list...
		exclude.add(50); // it's possible to add an empty string in a list...
		exclude.add(54); // it's possible to add an empty string in a list...
		exclude.add(90); // not in context menu
		exclude.add(91); // not in context menu
		exclude.add(92); // not in context menu
		exclude.add(93); // not in context menu
		exclude.add(94); // not in context menu
		exclude.add(95); // not in context menu
		exclude.add(96); // not in context menu

		longTest.testExecution(exclude);
	}
}
