package fr.imag.adele.cadse.test.basictests.defaultinstancename;


import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses(value={
		DefaultInstanceName_tc_execution.class
})


public class DefaultInstanceName_ts_execution {
	public static Test suite() {
		return (Test) new JUnit4TestAdapter(DefaultInstanceName_ts_execution.class);
	}
}