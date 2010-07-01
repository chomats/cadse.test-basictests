package fr.imag.adele.cadse.test.basictests.group.test3;

import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.groupKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.notAbstractKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.rootKv;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.createItemType;
import static fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods.createLinkType;
import fr.imag.adele.cadse.test.basictests.basicproperties.BasicProperties_Integer_testDriver;
import fr.imag.adele.cadse.test.basictests.common.GTTestParameter;
import fr.imag.adele.graphictests.gttree.GTTreePath;

public class Test3_Integer_testDriver extends BasicProperties_Integer_testDriver {

	/*
	 * (non-Javadoc)
	 * @see
	 * fr.imag.adele.cadse.test.basictests.testdriver.GTCommonTestDriver#createTypes(fr.imag.adele.cadse.test.basictests
	 * .testdriver.GTTestParameter)
	 */
	@Override
	protected GTTreePath createTypes(GTTestParameter tp) {
		String sufix = getItName(tp).substring(0, 1).toUpperCase() + getItName(tp).substring(1);
		GTTreePath src = getDataModel(tp).concat("parent" + sufix);
		GTTreePath dst = getDataModel(tp).concat(getItName(tp));

		createItemType(getDataModel(tp), src.getDestinationName(), notAbstractKv(), rootKv());
		createItemType(getDataModel(tp), dst.getDestinationName(), notAbstractKv(), rootKv());
		createLinkType("group" + sufix, src, dst, null, null, groupKv());

		return dst;
	}
}
