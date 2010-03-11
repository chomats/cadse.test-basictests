package fr.imag.adele.cadse.test.basictests.basicproperties;

import org.junit.Test;

/**
 */
public class BasicProperties_double_tc_execution extends BasicProperties_number_tc_execution {

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

		int instanceNumber = 1;
		for (int typeNumber = 1; typeNumber <= 16; typeNumber++) {
			System.out.println("Starting Test Double part 1 : instance#=" + instanceNumber + " type#=" + typeNumber
					+ " New visual=456.0");
			doubleTest(instanceNumber++, typeNumber, "456.0", 456d);
			System.out.println("Starting Test Double part 2 : instance#=" + instanceNumber + " type#=" + typeNumber
					+ " New visual=\"\"");
			doubleTest(instanceNumber++, typeNumber, "", null);
		}
	}

	private void doubleTest(int instanceNumber, int typeNumber, String newVisualValue, Double newModelValue) {

		// The type name
		String type = "double";

		// A not null default value
		String initialVisualValueNotNull = "123.0";

		// The model value, corresponding to the initialVisualValueNotNull
		Object initialModelValueNotNull = 123d;

		// Performs the test
		computeParameters(type, instanceNumber, typeNumber, initialVisualValueNotNull, initialModelValueNotNull,
				newVisualValue, newModelValue);
	}
}
