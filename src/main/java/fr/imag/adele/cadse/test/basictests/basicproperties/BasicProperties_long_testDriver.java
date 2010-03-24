package fr.imag.adele.cadse.test.basictests.basicproperties;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.ItemType;

public class BasicProperties_long_testDriver extends BasicProperties_number_testDriver {

	public BasicProperties_long_testDriver() {

		defValGraphicValues = new String[] { "", "123" };
		defValModelValues = new Object[] { null, 123l };

		newValGraphicValues = new String[] { "", "456", null }; // null stands for leave unchanged
		newValModelValues = new Object[] { null, 456l, null };

		initializeTables();
	}

	@Override
	public String getTypeUnderTest() {
		return "long";
	}

	@Override
	public ItemType getItemTypeUnderTest() {
		return CadseGCST.LONG;
	}
}
