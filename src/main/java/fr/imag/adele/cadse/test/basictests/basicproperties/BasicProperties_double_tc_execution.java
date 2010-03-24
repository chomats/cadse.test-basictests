package fr.imag.adele.cadse.test.basictests.basicproperties;

import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.selectCadses;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.workspaceView;

import java.util.ArrayList;

import org.junit.Test;

import fr.imag.adele.graphictests.test.GTTestCase;

/**
 */
public class BasicProperties_double_tc_execution extends GTTestCase {

	/**
	 * Selects the CADSE to be executed and closes unless views.
	 */
	@Test
	public void test_preparation() throws Exception {

		selectCadses("Cadse Model.Workspace.CADSE_BasicProperties_double");
		welcomeView.close();
		workspaceView.show();
	}

	@Test
	public void test_double() throws Exception {

		BasicProperties_double_testDriver doubleTest = new BasicProperties_double_testDriver();

		ArrayList<Integer> exclude = new ArrayList<Integer>();
		exclude.add(2); // it's possible to add an empty string in a list...
		exclude.add(6); // it's possible to add an empty string in a list...
		exclude.add(50); // it's possible to add an empty string in a list...
		exclude.add(54); // it's possible to add an empty string in a list...

		doubleTest.testExecution(exclude);

	}
}
