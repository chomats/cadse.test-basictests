package fr.imag.adele.cadse.test.basictests.bug1;

import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses(value = { Bug1_tc_CADSEg.class })
public class Bug1_ts_CADSEg {
	public static Test suite() {
		return new JUnit4TestAdapter(Bug1_ts_CADSEg.class);
	}
}
