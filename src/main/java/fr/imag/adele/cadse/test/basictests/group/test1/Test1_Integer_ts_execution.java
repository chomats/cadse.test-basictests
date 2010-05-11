package fr.imag.adele.cadse.test.basictests.group.test1;

import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses(value = { Test1_Integer_tc_execution.class })
public class Test1_Integer_ts_execution {
	public static Test suite() {
		return new JUnit4TestAdapter(Test1_Integer_ts_execution.class);
	}
}
