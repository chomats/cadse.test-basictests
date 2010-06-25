package fr.imag.adele.cadse.test.basictests.bug1;

import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses(value = { Bug1_tc_execution.class })
public class Bug1_ts_execution {
	public static Test suite() {
		return new JUnit4TestAdapter(Bug1_ts_execution.class);
	}
}
