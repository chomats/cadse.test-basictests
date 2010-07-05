package fr.imag.adele.cadse.test.basictests.common.type;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.ItemType;

public class GTDoubleParameter extends GTTypeParameter {

	@Override
	public ItemType getAttributeType() {
		return CadseGCST.DOUBLE;
	}

	@Override
	public String getAttributeName() {
		return "Double";
	}
}
