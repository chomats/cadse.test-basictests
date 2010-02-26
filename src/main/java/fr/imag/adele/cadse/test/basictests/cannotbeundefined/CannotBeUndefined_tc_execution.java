package fr.imag.adele.cadse.test.basictests.cannotbeundefined;

import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;
import org.junit.Test;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseFactory;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseShell;
import fr.imag.adele.graphictests.cadse.test.GTCadseTestCase;
import fr.imag.adele.graphictests.gttree.GTTreePath;



public class CannotBeUndefined_tc_execution extends GTCadseTestCase {

	@Test
	public void test_preparation() throws Exception {
		
		selectCadses("Cadse Model.Workspace.CADSE_CannotBeUndefined");
		welcomeView.close();
		workspaceView.show();
	}
	
	@Test
	public void test_attr_bool() throws Exception {
		verif_test_ok("bool", 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
	}
	
	@Test
	public void test_attr_integer() throws Exception {

		int i=0;
		createAttrInteger(1, ++i, "123", "456", true,  "456", failingAssertTimeout); 
		createAttrInteger(1, ++i, "123", "",    false, null,  failingAssertTimeout);
		createAttrInteger(2, ++i, null,  null,  true,  "123", failingAssertTimeout);  
		createAttrInteger(3, ++i, "",    "456", true,  "456", failingAssertTimeout);
		createAttrInteger(3, ++i, "",    "",    false, null,  failingAssertTimeout);
		createAttrInteger(4, ++i, null,  null,  false, null,  failingAssertTimeout);			
		createAttrInteger(5, ++i, "123", "456", true,  "456", failingAssertTimeout);		
		createAttrInteger(5, ++i, "123", "",    true,  "",    failingAssertTimeout);
		createAttrInteger(6, ++i, null,  null,  true,  "123", failingAssertTimeout);
		createAttrInteger(7, ++i, "",    "456", true,  "456", failingAssertTimeout);
		createAttrInteger(7, ++i, "",    "",    true,  "",    failingAssertTimeout);
		createAttrInteger(8, ++i, null,  null,  true,  "",    failingAssertTimeout);
	}
	
	
	private void verif_test_ok(String prefix, int... indexes) {
		for (int i : indexes)
			createBasicItem(workspaceView, null, "my_" + prefix + i, "instance_" + prefix + i, new GTTreePath("instance_" + prefix + i));
	}
	
	/**
	 * Test for integer attributes.
	 * 
	 * @param typeNumber       the number of the int type to be created
	 * @param instanceNumber   the number of this instance
	 * @param fieldValue       the value which should be in the field (including ""), or null, if no field 
	 * @param newValue         the new value for the field, including "" 
	 * @param finishOK         if the ok button should be available 
	 * @param modifPageValue   the value of the integer attribute, in the modification page 
	 * @param timeout          the timeout for the actions which are supposed to fail
	 */
	private void createAttrInteger(int typeNumber, int instanceNumber, String fieldValue, String newValue, boolean finishOK, String modifPageValue, long timeout) {
		
		String typeName = "my_int" + typeNumber;
		String instanceName = "instance_int" + instanceNumber; 
		long curentTimeout = SWTBotPreferences.TIMEOUT;
		
		workspaceView.contextMenuNew(typeName).click();
		GTCadseShell shell = new GTCadseShell(typeName);
		GTCadseFactory.findCadseField(shell, CadseGCST.ITEM_at_NAME_).typeText(instanceName);
	
		/* Field displayed */
		if (fieldValue != null) {
			assertEquals(fieldValue, shell.findTextWithLabel("int_attr").getText());
		} else {
			try {
				SWTBotPreferences.TIMEOUT = timeout;
				shell.findTextWithLabel("int_attr");
				SWTBotPreferences.TIMEOUT = curentTimeout;
				fail("The field exists whereas it shouldn't.");
			} catch(Exception e) {
				// success
				SWTBotPreferences.TIMEOUT = curentTimeout;
			}
		}
		
		/* New value */
		if (newValue != null) {
			shell.findTextWithLabel("int_attr").typeText(newValue);
		}
		
		/* FinishOK */
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		if (finishOK ==  true) {
			shell.close();
			workspaceView.selectNode(instanceName);
		}
		else {
			try {
				SWTBotPreferences.TIMEOUT = timeout;
				shell.close();
				fail("The OK button shouldn't be activated.");
			} catch(Exception e) {
				// success
				SWTBotPreferences.TIMEOUT = curentTimeout;
			}
		}
		
		/* Value in property page */
		if (modifPageValue != null) {
			workspaceView.selectNode(instanceName);
			propertiesView.showTab(typeName);
			assertEquals(modifPageValue, propertiesView.findTextWithLabel("int_attr").getText());
		}
	}
}
