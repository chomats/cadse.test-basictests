/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
 * Copyright (C) 2006-2010 Adele Team/LIG/Grenoble University, France
 */
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
	 * isSettableValue
	 * (fr.imag.adele.cadse.test.basictests.common.GTTestParameter,
	 * fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue)
	 */
	@Override
	public boolean isSettableValue(GTTestParameter tp, KeyValue kv) {

		boolean isList = tp.getBoolean("list");

		if (isList) {
			if (kv == null)
				return false;
			else if (kv.value == null)
				return false;
			else if (!kv.value.toString().equals(""))
				return true;
			else
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
