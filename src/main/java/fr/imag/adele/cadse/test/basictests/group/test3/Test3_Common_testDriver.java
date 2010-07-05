package fr.imag.adele.cadse.test.basictests.group.test3;

import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseView.propertiesView;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseView.workspaceView;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.groupKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.notAbstractKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.rootKv;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.createBasicHead;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.createItemType;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.createLinkType;

import java.util.UUID;

import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.attribute.IAttributeType;
import fr.imag.adele.cadse.core.impl.CadseCore;
import fr.imag.adele.cadse.test.basictests.common.GTSimpleTestDriver;
import fr.imag.adele.cadse.test.basictests.common.GTTestParameter;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.GTCadseShell;
import fr.imag.adele.graphictests.gttree.GTTreePath;

public class Test3_Common_testDriver extends GTSimpleTestDriver {

	@Override
	public void testCreation(GTTestParameter tp) {

		/* Pre create */
		preCreate(tp);

		/* Item type and link creation */
		createTypes(tp);
	}

	protected String getItSrcName(GTTestParameter tp) {
		String sufix = getItName(tp).substring(0, 1).toUpperCase() + getItName(tp).substring(1);
		return "parent" + sufix;
	}

	protected String getInstanceSrcName(GTTestParameter tp) {
		return "groupHead" + tp.testNumber;
	}

	@Override
	protected GTTreePath createTypes(GTTestParameter tp) {

		GTTreePath src = getDataModel(tp).concat(getItSrcName(tp));
		GTTreePath dst = getDataModel(tp).concat(getItName(tp));

		createItemType(getDataModel(tp), src.getDestinationName(), notAbstractKv(), rootKv());
		createItemType(getDataModel(tp), dst.getDestinationName(), notAbstractKv(), rootKv());
		createLinkType("groupLink", src, dst, null, null, groupKv());

		return null;
	}

	@Override
	protected void preExecute(GTTestParameter tp) {

		GTTreePath headPath = new GTTreePath(getInstanceSrcName(tp));
		/* Head creation */
		createBasicHead(workspaceView, null, getItSrcName(tp), getInstanceSrcName(tp), headPath);

		/* Attributes creation */
		boolean success = createAttributes(tp, headPath);
		boolean expected = tp.isAttributeCreationSuccess();
		assertEquals("testCreation error with #" + tp.testNumber, expected, success);

		/* Post create */
		if (success) {
			GTTreePath attrPath = headPath.concat(tp.getAttributeName());
			postCreate(tp, headPath, attrPath);
		}
	}

	@Override
	protected GTCadseShell initCreationPage(GTTestParameter tp) {
		workspaceView.contextMenuNewMember(new GTTreePath(getInstanceSrcName(tp)), getItName(tp)).click();
		GTCadseShell shell = new GTCadseShell(getItName(tp));
		return shell;
	}

	@Override
	protected void displayAttributeCreationPage(GTTestParameter tp, GTCadseShell shell) {

		boolean sicp = tp.getBoolean("sicp");
		boolean fieldInCP = sicp && tp.isAttributeCreationSuccess();

		if (fieldInCP) {
			shell.next();
		}
	}

	@Override
	protected void displayAttributeModificationPage(GTTestParameter tp) {
		propertiesView.showTab(getInstanceSrcName(tp));
	}

	@Override
	protected void modelChecking(GTTestParameter tp, UUID id) {
		Item item = CadseCore.getLogicalWorkspace().getItem(id);
		assertNotNull(item);
		IAttributeType<?> attr = item.getLocalAttributeType(getAttributeName(tp));
		Object actualModel = item.getAttribute(attr);
		Object expectedModel = getFinalValue(tp);
		assertEqualsListValues("Error in model checking for #" + tp.testNumber, expectedModel, actualModel);
	}
}
