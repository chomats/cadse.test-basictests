package fr.imag.adele.cadse.test.basictests.basicproperties;

import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.listKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.notListKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.notSicpKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.notSimpKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.sicpKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.simpKv;
import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.test.basictests.testdriver.GTTestParameter;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue;
import fr.imag.adele.graphictests.cadse.test.GTCadseHelperMethods;
import fr.imag.adele.graphictests.gttree.GTTreePath;

public class BasicProperties_Enum_testDriver extends BasicProperties_Common_testDriver {

	/** Enum type name */
	String enumTypeName = "myEnum";

	/**
	 * Instantiates a new testDriver
	 */
	public BasicProperties_Enum_testDriver() {
		initializeTestParameters();
	}

	/*
	 * (non-Javadoc)
	 * @see fr.imag.adele.cadse.test.basictests.testdriver.GTCommonTestDriver#initializeTestParameters()
	 */
	@Override
	protected void initializeTestParameters() {

		/* =========== */
		/* DEFINITIONS */
		/* =========== */

		/* Common parameters */
		KeyValue[] sicpValues = { sicpKv, notSicpKv };
		KeyValue[] simpValues = { simpKv, notSimpKv };
		/* KeyValue[] cbuValues = { cbuKv, notCbuKv }; CBU = this attribute does not exists with enum type */
		KeyValue[] listValues = { notListKv, listKv };

		/* Default Value */
		KeyValue kv11 = new KeyValue(CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE_, "");
		KeyValue kv12 = new KeyValue(CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE_, "two");
		KeyValue[] defVal = new KeyValue[] { kv11, kv12 };

		/* Execution : new value */
		KeyValue kv21 = new KeyValue(getAttributeName(), "");
		KeyValue kv22 = new KeyValue(getAttributeName(), "three");
		KeyValue[] newVal = new KeyValue[] { kv21, kv22 };

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
		ctp.addParameter("newValue", newVal);
		ctp.addParameter("enumType", enumType);
	}

	/*
	 * (non-Javadoc)
	 * @see fr.imag.adele.cadse.test.basictests.testdriver.GTCommonTestDriver#getAttributeTypeUnderTest()
	 */
	@Override
	protected ItemType getAttributeTypeUnderTest() {
		return CadseGCST.ENUM;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.imag.adele.cadse.test.basictests.testdriver.GTCommonTestDriver#getAttributeNameUnderTest()
	 */
	@Override
	protected String getAttributeNameUnderTest() {
		return "Enum";
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
	 * fr.imag.adele.cadse.test.basictests.basicproperties.BasicProperties_Common_testDriver#getCreationKeyValues(fr
	 * .imag.adele.cadse.test.basictests.testdriver.GTTestParameter)
	 */
	@Override
	protected KeyValue[] getCreationKeyValues(GTTestParameter tp) {
		return tp.getValues("enumType", "defVal", "sicp", "simp", /* "cbu", */"list");
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * fr.imag.adele.cadse.test.basictests.testdriver.GTCommonTestDriver#attributeCreationSuccess(fr.imag.adele.cadse
	 * .test.basictests.testdriver.GTTestParameter)
	 */
	@Override
	protected boolean attributeCreationSuccess(GTTestParameter tp) {

		String defVal = tp.getString("defVal");
		if (defVal == null || defVal.equals("")) {
			return false;
		} else {
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
	protected boolean isOkButtonActivated(GTTestParameter tp) {

		KeyValue newVal = tp.getValue("newValue"); // careful : NULL = leave unchanged
		String defVal = tp.getString("defVal");
		boolean sicp = tp.getBoolean("sicp");

		// Attribute haven't been created : no problem for creating the item type (!)
		// or attribute not displayed in Creation page
		if (!sicp || defVal == null || defVal.isEmpty()) {
			return true;
		}

		// Attribute created and leave unchanged
		else if (newVal == null) {
			return true;
		}

		// Empty new value is forbidden
		else if (newVal.value == null || newVal.getString().isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * fr.imag.adele.cadse.test.basictests.basicproperties.BasicProperties_Common_testDriver#getFinalGraphicalValue(
	 * fr.imag.adele.cadse.test.basictests.testdriver.GTTestParameter)
	 */
	@Override
	protected Object getFinalGraphicalValue(GTTestParameter tp) {
		Object superVal = super.getFinalGraphicalValue(tp);

		if (superVal instanceof String) {
			String val = (String) superVal;
			if (val != null && val.equals(""))
				return null;
		}

		return superVal;
	}

}
