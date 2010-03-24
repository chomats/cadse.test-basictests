package fr.imag.adele.cadse.test.basictests.basicproperties;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.ItemType;

public class BasicProperties_double_testDriver extends BasicProperties_number_testDriver {

	public BasicProperties_double_testDriver() {

		defValGraphicValues = new String[] { "", "123.0" };
		defValModelValues = new Object[] { null, 123d };

		newValGraphicValues = new String[] { "", "456.0", null }; // null stands for leave unchanged
		newValModelValues = new Object[] { null, 456d, null };

		initializeTables();
	}

	@Override
	public String getTypeUnderTest() {
		return "double";
	}

	@Override
	public ItemType getItemTypeUnderTest() {
		return CadseGCST.DOUBLE;
	}
}
