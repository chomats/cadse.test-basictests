package fr.imag.adele.cadse.test.basictests.basicproperties;

import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses(value = { BasicProperties_Long_tc_CADSEg.class })
public class BasicProperties_Long_ts_CADSEg {
	public static Test suite() {
		return new JUnit4TestAdapter(BasicProperties_Long_ts_CADSEg.class);
	}
}