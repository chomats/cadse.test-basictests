package fr.imag.adele.cadse.test.basictests.group.test1;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue;

public class Test1_Integer_testDriver extends Test1_common_testDriver {

	/*
	 * (non-Javadoc)
	 * @see fr.imag.adele.cadse.test.basictests.group.test1.Test1_common_testDriver#getItemTypeUnderTest()
	 */
	@Override
	public ItemType getItemTypeUnderTest() {
		return CadseGCST.INTEGER;
	}

	public Test1_Integer_testDriver() {
		defaultValue = new KeyValue(new String(), 10, 10);
		newValue1 = new KeyValue(new String(), 11, 11);
		newValue2 = new KeyValue(new String(), 12, 12);
		newValue3 = new KeyValue(new String(), 13, 13);

		initializeTables();
	}
}
