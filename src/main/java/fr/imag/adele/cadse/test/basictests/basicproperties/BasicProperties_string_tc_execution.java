package fr.imag.adele.cadse.test.basictests.basicproperties;

import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.selectCadses;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.workspaceView;

import java.util.ArrayList;

import org.junit.Test;

import fr.imag.adele.graphictests.test.GTTestCase;

/**
 */
public class BasicProperties_string_tc_execution extends GTTestCase {

	/**
	 * Selects the CADSE to be executed and closes unless views.
	 */
	@Test
	public void test_preparation() throws Exception {

		selectCadses("Cadse Model.Workspace.CADSE_BasicProperties_string");
		welcomeView.close();
		workspaceView.show();
	}

	@Test
	public void test_string() throws Exception {

		BasicProperties_string_testDriver stringTest = new BasicProperties_string_testDriver();

		ArrayList<Integer> exclude = new ArrayList<Integer>();
		exclude.add(2); // it's possible to add an empty string in a list...
		exclude.add(6); // it's possible to add an empty string in a list...
		exclude.add(50); // it's possible to add an empty string in a list...
		exclude.add(54); // it's possible to add an empty string in a list...

		stringTest.testExecution(exclude);

	}
}
