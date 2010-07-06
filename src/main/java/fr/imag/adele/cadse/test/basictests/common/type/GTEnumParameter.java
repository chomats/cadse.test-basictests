package fr.imag.adele.cadse.test.basictests.common.type;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.test.basictests.common.GTTestParameter;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue;
import fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods;
import fr.imag.adele.graphictests.gttree.GTTreePath;

public class GTEnumParameter extends GTTypeParameter {

	/** Enum type name. */
	String enumTypeName = "myEnum";

	/*
	 * (non-Javadoc)
	 * 
	 * @seefr.imag.adele.cadse.test.basictests.common.type.GTTypeParameter#
	 * getAttributeType()
	 */
	@Override
	public ItemType getAttributeType() {
		return CadseGCST.ENUM;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seefr.imag.adele.cadse.test.basictests.common.type.GTTypeParameter#
	 * getAttributeName()
	 */
	@Override
	public String getAttributeName() {
		return "Enum";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.imag.adele.cadse.test.basictests.common.type.GTTypeParameter#isValidValue
	 * (fr.imag.adele.cadse.test.basictests.common.GTTestParameter,
	 * java.lang.Object)
	 */
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

	/*
	 * (non-Javadoc)
	 * 
	 * @seefr.imag.adele.cadse.test.basictests.common.type.GTTypeParameter#
	 * isAttributeCreationSuccess
	 * (fr.imag.adele.cadse.test.basictests.common.GTTestParameter)
	 */
	@Override
	public boolean isAttributeCreationSuccess(GTTestParameter tp) {

		String defVal = tp.getString("defVal");
		if (defVal == null || defVal.equals("")) {
			return false;
		} else {
			return true;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seefr.imag.adele.cadse.test.basictests.common.type.GTTypeParameter#
	 * getCreationKeyValues
	 * (fr.imag.adele.cadse.test.basictests.common.GTTestParameter)
	 */
	@Override
	public KeyValue[] getCreationKeyValues(GTTestParameter tp) {
		return tp.getValues("enumType", "sicp", "simp", /* "cbu", */"list", "defVal");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.imag.adele.cadse.test.basictests.common.type.GTTypeParameter#preCreate
	 * (fr.imag.adele.cadse.test.basictests.common.GTTestParameter,
	 * fr.imag.adele.graphictests.gttree.GTTreePath)
	 */
	@Override
	public void preCreate(GTTestParameter tp, GTTreePath dataModel) {
		if (tp.testNumber == 0) {
			GTCadseHelperMethods.createEnumType(dataModel, enumTypeName, "one", "two", "three", "four", "five");
		}
	}
}
