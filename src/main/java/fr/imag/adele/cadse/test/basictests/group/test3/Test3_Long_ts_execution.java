package fr.imag.adele.cadse.test.basictests.group.test3;

import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses(value = { Test3_Long_tc_execution.class })
public class Test3_Long_ts_execution {
	public static Test suite() {
		return new JUnit4TestAdapter(Test3_Long_ts_execution.class);
	}
}
