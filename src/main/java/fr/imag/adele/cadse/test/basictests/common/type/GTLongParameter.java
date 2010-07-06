package fr.imag.adele.cadse.test.basictests.common.type;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.ItemType;

public class GTLongParameter extends GTTypeParameter {

	/*
	 * (non-Javadoc)
	 * 
	 * @seefr.imag.adele.cadse.test.basictests.common.type.GTTypeParameter#
	 * getAttributeType()
	 */
	@Override
	public ItemType getAttributeType() {
		return CadseGCST.LONG;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seefr.imag.adele.cadse.test.basictests.common.type.GTTypeParameter#
	 * getAttributeName()
	 */
	@Override
	public String getAttributeName() {
		return "Long";
	}
}
