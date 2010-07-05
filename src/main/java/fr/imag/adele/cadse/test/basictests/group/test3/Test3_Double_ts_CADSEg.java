package fr.imag.adele.cadse.test.basictests.group.test3;

import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses(value = { Test3_Double_tc_CADSEg.class })
public class Test3_Double_ts_CADSEg {
	public static Test suite() {
		return new JUnit4TestAdapter(Test3_Double_ts_CADSEg.class);
	}
}
