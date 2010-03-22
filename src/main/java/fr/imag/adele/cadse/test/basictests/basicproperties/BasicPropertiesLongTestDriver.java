package fr.imag.adele.cadse.test.basictests.basicproperties;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.ItemType;

public class BasicPropertiesLongTestDriver extends BasicPropertiesNumberTestDriver {

	public BasicPropertiesLongTestDriver() {

		defValGraphicValues.add("");
		defValGraphicValues.add("123");
		defValModelValues.add(null);
		defValModelValues.add(123l);

		newValGraphicValues.add("");
		newValGraphicValues.add("456");
		newValGraphicValues.add(null); // null stands for leave unchanged
		newValModelValues.add(null);
		newValModelValues.add(456l);
		newValModelValues.add(null);

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
