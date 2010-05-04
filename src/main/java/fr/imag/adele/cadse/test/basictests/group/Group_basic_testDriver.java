package fr.imag.adele.cadse.test.basictests.group;

import java.util.ArrayList;

import fr.imag.adele.cadse.core.CadseGCST;
import fr.imag.adele.graphictests.cadse.test.KeyValue;
import fr.imag.adele.graphictests.test.GTTestCase;

public class Group_basic_testDriver extends GTTestCase {

	/** The CADSE definition name */
	protected final String cadse_name = "CADSE_group_basic";

	// ========================== //
	// PROPERTIES POSSIBLE VALUES //
	// ========================== //
	protected interface CST {
		/** Set of values for the Show in Creation Page property */
		public KeyValue[] sicpValues = { KeyValue.sicpKv, KeyValue.notSicpKv };
		/** Set of values for the Show in Modification Page property */
		public KeyValue[] simpValues = { KeyValue.simpKv, KeyValue.notSimpKv };
	}

	// ======================================================= //
	// TABLES USED FOR PROPERTIES VALUES FOR ALL THE INSTANCES //
	// ======================================================= //

	/** The Show in Creation Page property value for all the instances */
	protected final ArrayList<KeyValue> sicpTab = new ArrayList<KeyValue>();
	/** The Show in Modification Page property value for all the instances */
	protected final ArrayList<KeyValue> simpTab = new ArrayList<KeyValue>();

	protected KeyValue srcDefVal = new KeyValue(CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE, "def_val_src");
	protected KeyValue dstDefVal = new KeyValue(CadseGCST.ATTRIBUTE_at_DEFAULT_VALUE, "def_val_dst");

	/**
	 * Performs table initializations
	 */
	public void initializeTables() {
		for (KeyValue sicp : CST.sicpValues) {
			for (KeyValue simp : CST.simpValues) {
				sicpTab.add(sicp);
				simpTab.add(simp);
			}
		}
	}
}
