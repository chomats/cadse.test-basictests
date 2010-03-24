package fr.imag.adele.cadse.test.basictests.basicproperties;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.ItemType;

public class BasicProperties_integer_testDriver extends BasicProperties_number_testDriver {

	public BasicProperties_integer_testDriver() {

		defValGraphicValues = new String[] { "", "123" };
		defValModelValues = new Object[] { null, 123 };

		newValGraphicValues = new String[] { "", "456", null }; // null stands for leave unchanged
		newValModelValues = new Object[] { null, 456, null };

		initializeTables();
	}

	@Override
	public String getTypeUnderTest() {
		return "integer";
	}

	@Override
	public ItemType getItemTypeUnderTest() {
		return CadseGCST.INTEGER;
	}
}
