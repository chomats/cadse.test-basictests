package fr.imag.adele.cadse.test.basictests.group.basic;

import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseView.propertiesView;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseView.workspaceView;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.createString;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.selectCadses;
import static fr.imag.adele.graphictests.gtworkbench_part.GTView.welcomeView;

import org.junit.Test;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.core.attribute.IAttributeType;
import fr.imag.adele.cadse.core.transaction.delta.ItemDelta;
import fr.imag.adele.cadse.si.workspace.uiplatform.swt.SWTUIPlatform;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseShell;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue;
import fr.imag.adele.graphictests.gttree.GTTreePath;

/**
 */
public class Group_basic_tc_execution extends Group_basic_testDriver {

	GTCadseShell shell;

	/**
	 * Selects the CADSE to be executed and closes unless views.
	 */
	@Test
	public void test_preparation() throws Exception {

		selectCadses("Cadse Model.Workspace." + cadse_name);
		welcomeView.close();
		workspaceView.show();
		waitUntilWorkspaceStarted();
	}

	@Test
	public void test1() throws Exception {

		final int i = 1;

		String attr = "attr" + i;
		String itSrc = "it_src" + i;
		String itDst = "it_dst" + i;
		String head = "head" + i;
		String member = "member" + i;

		KeyValue defVal = new KeyValue(attr, "def_val" + i, "def_val" + i);
		KeyValue newVal1 = new KeyValue(attr, "new_val1" + i, "new_val1" + i);
		KeyValue newVal2 = new KeyValue(attr, "new_val2" + i, "new_val2" + i);
		KeyValue newVal3 = new KeyValue(attr, "new_val3" + i, "new_val3" + i);

		// head creation
		workspaceView.contextMenuNewHead(itSrc).click();
		shell = new GTCadseShell(itSrc);
		assertFieldEquals("default value", defVal, shell);
		shell.setValue(newVal1);
		shell.findCadseFieldName().typeText(head);
		assertFieldEquals("New value in creation page", newVal1, shell);
		shell.close();

		// looking into properties pages and changing value
		workspaceView.selectNode(head);
		propertiesView.showTab(itSrc);
		assertFieldEquals("New value in modification page", newVal1, propertiesView);
		propertiesView.setValue(newVal2);

		// member creation
		workspaceView.contextMenuNewMember(new GTTreePath(head), itDst).click();
		shell = new GTCadseShell(itDst);
		shell.findCadseFieldName().typeText(member);
		shell.next();
		assertFieldEquals("New value in modification page", newVal2, shell);
		shell.close();

		// looking into properties pages
		workspaceView.selectNode(member);
		propertiesView.showTab(head);
		assertFieldEquals("New value in modification page", newVal2, propertiesView);

		// Value modification in the head
		workspaceView.selectNode(head);
		propertiesView.showTab(itSrc);
		assertFieldEquals("Value in modification page", newVal2, propertiesView);
		propertiesView.setValue(newVal3);

		// Assert value has changed in the member
		workspaceView.selectNode(member);
		propertiesView.showTab(head);
		assertFieldEquals("New value in modification page", newVal3, propertiesView);
	}

	@Test
	public void test2() throws Exception {

		final int i = 2;

		String attr = "attr" + i;
		String itSrc = "it_src" + i;
		String itDst = "it_dst" + i;
		String head = "head" + i;
		String member = "member" + i;

		KeyValue defVal = new KeyValue(attr, "def_val" + i, "def_val" + i);
		KeyValue newVal1 = new KeyValue(attr, "new_val1" + i, "new_val1" + i);

		// head creation
		workspaceView.contextMenuNewHead(itSrc).click();
		shell = new GTCadseShell(itSrc);
		shell.findCadseFieldName().typeText(head);
		shell.close();

		// member creation
		workspaceView.contextMenuNewMember(new GTTreePath(head), itDst).click();
		shell = new GTCadseShell(itDst);
		assertFieldEquals("Creation page", defVal, shell);
		shell.setValue(newVal1);
		shell.findCadseFieldName().typeText(member);
		assertFieldEquals("newval", newVal1, shell);
		shell.close();

		// looking into properties pages
		workspaceView.selectNode(member);
		propertiesView.showTab(itDst);
		assertFieldEquals("New value in modification page", newVal1, propertiesView);
	}

	@Test
	public void test3() throws Exception {

		final int i = 3;

		String attr = "attr" + i;
		String itSrc = "it_src" + i;
		String itDst = "it_dst" + i;
		String head = "head" + i;
		GTTreePath headPath = new GTTreePath(head);
		String member = "member" + i;

		KeyValue defVal = new KeyValue(attr, "def_val" + i, "def_val" + i);
		KeyValue newVal1 = new KeyValue(attr, "new_val1" + i, "new_val1" + i);
		// KeyValue newVal2 = new KeyValue(attr, "new_val2" + i, "new_val2" + i);

		// head creation
		workspaceView.contextMenuNewHead(itSrc).click();
		shell = new GTCadseShell(itSrc);
		shell.findCadseFieldName().typeText(head);
		shell.close();

		// New attribute in the head
		KeyValue dv = new KeyValue(CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE, "def_val" + i);
		createString(headPath, attr, dv, KeyValue.sicpKv);

		// member creation
		workspaceView.contextMenuNewMember(new GTTreePath(head), itDst).click();
		shell = new GTCadseShell(itDst);

		// check default value in model.
		SWTUIPlatform swtuiPlatform = shell.getSwtUIPlatform();
		final ItemDelta currentItem = (ItemDelta) swtuiPlatform.getItem();
		ItemType gCurrentItem = currentItem.getGroup();
		assertNotNull(gCurrentItem);
		IAttributeType<?> attrType = gCurrentItem.getAttributeType(attr);
		assertNotNull(attrType);
		Object value = currentItem.getAttribute(attrType, false);
		assertEquals(defVal.getString(), value);

		shell.findCadseFieldName().typeText(member);
		shell.next();

		assertFieldEquals("member creation", defVal, shell);
		shell.setValue(newVal1);
		shell.close();

		// looking into properties pages

		// Second attribute in the head

		// Assert value has changed in the member

		// Adding field in the head

		// ...

	}
}
