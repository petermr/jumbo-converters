 package org.xmlcml.cml.converters.compchem.castep.mol;

import org.junit.Test;
import org.xmlcml.cml.converters.testutils.RegressionSuite;

/**
 *
 * @author ojd20
 */
public class RegressionTest {

	   @Test
	   public void castepMol2CML() {
	      RegressionSuite.run("compchem/castep/mol", "mol", "xml",
	                          new CastepMol2CMLConverter());
	   }
	   
}
