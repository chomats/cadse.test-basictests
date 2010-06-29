package fr.imag.adele.cadse.test.basictests.group.test1;

import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.listKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.notListKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.notSimpKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.sicpKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.simpKv;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.test.basictests.testdriver.GTCollectionTestParameter;
import fr.imag.adele.cadse.test.basictests.testdriver.GTTestParameter;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue;
import fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods;
import fr.imag.adele.graphictests.gttree.GTTreePath;

public class Test1_Enum_testDriver extends Test1_Common_testDriver {

	/** Enum Type name */
	String enumTypeName = "myEnum";

	/*
	 * (non-Javadoc)
	 * @see fr.imag.adele.cadse.test.basictests.testdriver.GTCommonTestDriver#getAttributeTypeUnderTest()
	 */
	@Override
	public ItemType getAttributeTypeUnderTest() {
		return CadseGCST.ENUM;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.imag.adele.cadse.test.basictests.testdriver.GTCommonTestDriver#getAttributeNameUnderTest()
	 */
	@Override
	public String getAttributeNameUnderTest() {
		return "Enum";
	}

	/*
	 * (non-Javadoc)
	 * @see fr.imag.adele.cadse.test.basictests.testdriver.GTCommonTestDriver#getCTP()
	 */
	@Override
	public GTCollectionTestParameter getCTP() {

		GTCollectionTestParameter ctp = new GTCollectionTestParameter();

		/* =========== */
		/* DEFINITIONS */
		/* =========== */

		/* Common parameters */
		KeyValue[] sicpValues = { sicpKv(), /* notSicpKv() */};
		KeyValue[] simpValues = { simpKv(), notSimpKv() };
		/* KeyValue[] cbuValues = { cbuKv(), notCbuKv() }; CBU = this attribute does not exists with enum type */
		KeyValue[] listValues = { notListKv(), listKv() };

		/* Default value given into CADSEg */
		KeyValue defValKv1 = new KeyValue(CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE_, "");
		KeyValue defValKv2 = new KeyValue(CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE_, "two");
		KeyValue defValKv3 = new KeyValue(CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE_, null);
		KeyValue[] defVal = new KeyValue[] { defValKv1, defValKv2, defValKv3 };

		/* Value used for head creation */
		KeyValue newValue1Kv1 = new KeyValue(getAttributeName(), "");
		KeyValue newValue1Kv2 = new KeyValue(getAttributeName(), "three");
		KeyValue[] newValue1 = new KeyValue[] { newValue1Kv1, newValue1Kv2 };

		/* Value used for member creation */
		KeyValue newValue2Kv1 = new KeyValue(getAttributeName(), "");
		KeyValue newValue2Kv2 = new KeyValue(getAttributeName(), "four");
		KeyValue[] newValue2 = new KeyValue[] { newValue2Kv1, newValue2Kv2 };

		/* Value used to modify head after member creation */
		KeyValue newValue3Kv1 = new KeyValue(getAttributeName(), "");
		KeyValue newValue3Kv2 = new KeyValue(getAttributeName(), "five");
		KeyValue[] newValue3 = new KeyValue[] { newValue3Kv1, newValue3Kv2 };

		/* Enum Type */
		KeyValue enumTypeKv = new KeyValue(CadseGCST.ENUM_lt_ENUM_TYPE, new GTTreePath(enumTypeName));
		KeyValue[] enumType = new KeyValue[] { enumTypeKv };

		/* ==== */
		/* INIT */
		/* ==== */

		ctp.addParameter("sicp", sicpValues);
		ctp.addParameter("simp", simpValues);
		/* ctp.addParameter("cbu", cbuValues); CBU = this attribute does not exists with enum type */
		ctp.addParameter("list", listValues);

		ctp.addParameter("defVal", defVal);
		ctp.addParameter("newValue1", newValue1);
		ctp.addParameter("newValue2", newValue2);
		ctp.addParameter("newValue3", newValue3);

		ctp.addParameter("enumType", enumType);

		return ctp;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * fr.imag.adele.cadse.test.basictests.testdriver.GTCommonTestDriver#preCreate(fr.imag.adele.cadse.test.basictests
	 * .testdriver.GTTestParameter)
	 */
	@Override
	protected void preCreate(GTTestParameter tp) {
		if (tp.testNumber == 0) {
			GTCadseHelperMethods.createEnumType(dataModel, enumTypeName, "one", "two", "three", "four", "five");
		}
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * fr.imag.adele.cadse.test.basictests.group.test1.Test1_Common_testDriver#getCreationKeyValues(fr.imag.adele.cadse
	 * .test.basictests.testdriver.GTTestParameter)
	 */
	@Override
	public KeyValue[] getCreationKeyValues(GTTestParameter tp) {
		return tp.getValues("enumType", "sicp", "simp", /* "cbu", */"list", "defVal");
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * fr.imag.adele.cadse.test.basictests.testdriver.GTCommonTestDriver#attributeCreationSuccess(fr.imag.adele.cadse
	 * .test.basictests.testdriver.GTTestParameter)
	 */
	@Override
	protected boolean isAttributeCreationSuccess(GTTestParameter tp) {

		String defVal = tp.getString("defVal");
		if (defVal == null || defVal.equals("")) {
			return false;
		}
		else {
			return true;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * fr.imag.adele.cadse.test.basictests.basicproperties.BasicProperties_Common_testDriver#isOkButtonActivated(fr.
	 * imag.adele.cadse.test.basictests.testdriver.GTTestParameter)
	 */
	@Override
	protected boolean isValidValue(GTTestParameter tp, Object value) {

		String defVal = tp.getString("defVal");
		boolean sicp = tp.getBoolean("sicp");

		// Attribute haven't been created : no problem for creating the item type (!)
		// or attribute not displayed in Creation page
		if (!sicp || defVal == null || defVal.isEmpty()) {
			return true;
		}

		// Null or empty new value is forbidden
		else if (value == null || value.toString().isEmpty()) {
			return false;
		}
		else {
			return true;
		}
	}
}
