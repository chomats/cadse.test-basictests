package fr.imag.adele.cadse.test.basictests.common.type;

import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.test.basictests.common.GTTestParameter;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue;

public abstract class GTTypeParameter {

	public abstract ItemType getAttributeType();

	public abstract String getAttributeName();

	/**
	 * Checks if it should be possible to set a value.
	 * 
	 * @param tp
	 *            the tp
	 * @param value
	 *            the value
	 * @return true, if the value can be set
	 */
	public boolean isSettableValue(GTTestParameter tp, KeyValue kv) {

		boolean isList = tp.getBoolean("list");

		if (isList) {
			return kv != null && kv.value != null && !kv.value.toString().equals("");
		} else {
			return true;
		}
	}

	/**
	 * Checks if a value is valid, depending on the context.
	 * 
	 * @param tp
	 *            the test parameter
	 * @param value
	 *            the value
	 * @return true, if is ok button is activated
	 */
	public boolean isValidValue(GTTestParameter tp, Object value) {

		boolean cbu = tp.getBoolean("cbu");
		boolean isList = tp.getBoolean("list");

		if (isList) {
			return true;
		} else {
			if (cbu == true && (value == null || value.toString().isEmpty())) {
				return false;
			} else {
				return true;
			}
		}
	}
}
