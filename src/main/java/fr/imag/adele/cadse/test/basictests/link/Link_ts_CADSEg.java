package fr.imag.adele.cadse.test.basictests.link;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;


@RunWith(Suite.class)
@SuiteClasses(value={
		Link_tc_CADSEg.class
})


public class Link_ts_CADSEg {
	public static Test suite() {
		return (Test) new JUnit4TestAdapter(Link_ts_CADSEg.class);
	}
}