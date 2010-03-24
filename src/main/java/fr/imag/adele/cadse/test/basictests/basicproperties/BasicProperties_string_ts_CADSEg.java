package fr.imag.adele.cadse.test.basictests.basicproperties;

import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses(value = { BasicProperties_string_tc_CADSEg.class })
public class BasicProperties_string_ts_CADSEg {
	public static Test suite() {
		return new JUnit4TestAdapter(BasicProperties_string_ts_CADSEg.class);
	}
}