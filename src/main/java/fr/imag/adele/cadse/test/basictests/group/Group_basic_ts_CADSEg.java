package fr.imag.adele.cadse.test.basictests.group;

import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses(value = { Group_basic_tc_CADSEg.class })
public class Group_basic_ts_CADSEg {
	public static Test suite() {
		return new JUnit4TestAdapter(Group_basic_ts_CADSEg.class);
	}
}