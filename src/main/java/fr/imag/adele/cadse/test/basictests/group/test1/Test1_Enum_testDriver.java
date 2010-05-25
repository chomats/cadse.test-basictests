package fr.imag.adele.cadse.test.basictests.group.test1;

import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.listKv;
import static fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue.notCbuKv;
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

public class Test1_Enum_testDriver extends Test1_Common_testDriver {

	String enumTypeName = "myEnum";

	/**
	 * Instantiates a new test1_ integer_test driver.
	 */
	public Test1_Enum_testDriver() {
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
		KeyValue[] cbuValues = { /* cbuKv, */notCbuKv };
		KeyValue[] listValues = { notListKv, listKv };

		KeyValue defValKv = new KeyValue(CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE_, "four", "four");
		KeyValue newValue1Kv = new KeyValue(getAttributeName(), "one", "one");
		KeyValue newValue2Kv = new KeyValue(getAttributeName(), "two", "two");
		KeyValue newValue3Kv = new KeyValue(getAttributeName(), "three", "three");

		KeyValue[] defVal = new KeyValue[] { defValKv };
		KeyValue[] newValue1 = new KeyValue[] { newValue1Kv };
		KeyValue[] newValue2 = new KeyValue[] { newValue2Kv };
		KeyValue[] newValue3 = new KeyValue[] { newValue3Kv };

		/* ==== */
		/* INIT */
		/* ==== */

		ctp.addParameter("sicp", sicpValues);
		ctp.addParameter("simp", simpValues);
		ctp.addParameter("cbu", cbuValues);
		ctp.addParameter("list", listValues);

		ctp.addParameter("defVal", defVal);
		ctp.addParameter("newValue1", newValue1);
		ctp.addParameter("newValue2", newValue2);
		ctp.addParameter("newValue3", newValue3);
	}

	/*
	 * (non-Javadoc)
	 * @see fr.imag.adele.cadse.test.basictests.group.test1.Test1_common_testDriver#getItemTypeUnderTest()
	 */
	@Override
	protected ItemType getItemTypeUnderTest() {
		return CadseGCST.ENUM;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.imag.adele.cadse.test.basictests.testdriver.GTCommonTestDriver#getTypeNameUnderTest()
	 */
	@Override
	protected String getTypeNameUnderTest() {
		return "Enum";
	}

	/*
	 * (non-Javadoc)
	 * @see fr.imag.adele.cadse.test.basictests.group.test1.Test1_common_testDriver#preCreate(int)
	 */
	@Override
	protected void preCreate(GTTestParameter tp) {
		if (tp.testNumber == 0) {
			GTCadseHelperMethods.createEnumType(dataModel, enumTypeName, "one", "two", "three", "four");
		}
	}

	/*
	 * (non-Javadoc)
	 * @see fr.imag.adele.cadse.test.basictests.group.test1.Test1_common_testDriver#getAttributeCreationKeyValues(int)
	 */
	@Override
	public KeyValue[] getAttributeCreationKeyValues(GTTestParameter tp) {

		KeyValue enumTypeKv = new KeyValue(CadseGCST.ENUM_lt_ENUM_TYPE, new GTTreePath(enumTypeName));

		KeyValue defVal = tp.getValue("defVal");
		KeyValue sicp = tp.getValue("sicp");
		KeyValue simp = tp.getValue("simp");
		KeyValue cbu = tp.getValue("cbu");
		KeyValue isList = tp.getValue("list");

		return new KeyValue[] { enumTypeKv, sicp, simp, cbu, isList, defVal };
	}
}
