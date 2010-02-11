package fr.imag.adele.cadse.test.basictests.isabstract;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;


@RunWith(Suite.class)
@SuiteClasses(value={
		IsAbstract_tc_execution.class
})


public class IsAbstract_ts_execution {
	public static Test suite() {
		return (Test) new JUnit4TestAdapter(IsAbstract_ts_execution.class);
	}
}