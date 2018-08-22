 package org.xmlcml.cml.converters.compchem.supplemental.log;

import org.junit.Test;
import org.xmlcml.cml.converters.testutils.RegressionSuite;

/**
 *
 * @author ojd20
 */
public class RegressionTest {

    @Test
    public void supplementalLog2XML() {
        RegressionSuite.run("compchem/supplemental/log", "log", "xml",
                          new SupplementalLog2XMLConverter());
    }
}
