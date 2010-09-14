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

import fr.imag.adele.cadse.core.ItemType;
import fr.imag.adele.cadse.test.basictests.common.GTTestParameter;
import fr.imag.adele.graphictests.cadse.gtcadseworkbench_part.KeyValue;
import fr.imag.adele.graphictests.gttree.GTTreePath;

public abstract class GTTypeParameter {

	/** Gets the ItemType of this attribute */
	public abstract ItemType getAttributeType();

	/** Gets the name of this attribute */
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
		boolean cbu = tp.getBoolean("cbu");

		if (isList) {
			if (kv == null)
				return false;
			if (kv.value == null)
				return false;
			if (!kv.value.toString().equals(""))
				return true;
			if (cbu)
				return false;
			else
				return true;
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

	/**
	 * Returns true if the attribute can be created, false otherwise.
	 * 
	 * @param tp
	 *            the test parameter
	 * @return true if the attribute can be created, false otherwise.
	 */
	public boolean isAttributeCreationSuccess(GTTestParameter tp) {
		return true;
	}

	public KeyValue adaptedValue(GTTestParameter tp, KeyValue kv) {
		if (kv.value instanceof String && kv.getString().isEmpty()) {
			return new KeyValue(kv, null);
		} else {
			return kv;
		}
	}

	/**
	 * Performs actions after the item creation.
	 * 
	 * @param tp
	 *            the test parameter
	 * @param itPath
	 *            the item type path
	 * @param attrPath
	 *            the attribute path
	 */
	public void setAdvancedAttributeProperties(GTTestParameter tp, GTTreePath attrPath) {
	}

	/**
	 * Gets the key values used in creation page for creating the attribute.
	 * 
	 * @param tp
	 *            the test parameter
	 * 
	 * @return the creation key values
	 */
	public KeyValue[] getCreationKeyValues(GTTestParameter tp) {
		return tp.getValues("defVal", "sicp", "simp", "cbu", "list");
	}

	/**
	 * Actions to be performed before attribute creation
	 * 
	 * @param tp
	 *            the test parameter
	 * @param dataModel
	 *            path to the data model
	 */
	public void preCreate(GTTestParameter tp, GTTreePath dataModel) {
	}
}
