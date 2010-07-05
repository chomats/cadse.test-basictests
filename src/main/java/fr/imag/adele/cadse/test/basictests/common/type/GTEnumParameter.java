package fr.imag.adele.cadse.test.basictests.common.type;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.test.basictests.common.GTTestParameter;

public class GTEnumParameter extends GTTypeParameter {

	@Override
	public ItemType getAttributeType() {
		return CadseGCST.ENUM;
	}

	@Override
	public String getAttributeName() {
		return "Enum";
	}

	@Override
	public boolean isValidValue(GTTestParameter tp, Object value) {

		String defVal = tp.getString("defVal");
		boolean sicp = tp.getBoolean("sicp");

		// Attribute haven't been created : no problem for creating the item
		// type (!)
		// or attribute not displayed in Creation page
		if (!sicp || defVal == null || defVal.isEmpty()) {
			return true;
		}

		// Null or empty new value is forbidden
		else if (value == null || value.toString().isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public boolean isAttributeCreationSuccess(GTTestParameter tp) {

		String defVal = tp.getString("defVal");
		if (defVal == null || defVal.equals("")) {
			return false;
		} else {
			return true;
		}
	}
}
