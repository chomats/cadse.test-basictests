package fr.imag.adele.cadse.test.basictests.hascontent;

import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses(value={
		HasContent_tc_execution.class
})


public class HasContent_ts_execution {
	public static Test suite() {
		return (Test) new JUnit4TestAdapter(HasContent_ts_execution.class);
	}
}