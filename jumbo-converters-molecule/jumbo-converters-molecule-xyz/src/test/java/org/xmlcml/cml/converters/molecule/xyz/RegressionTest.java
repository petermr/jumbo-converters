/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.xmlcml.cml.converters.molecule.xyz;


import org.junit.Ignore;

import org.junit.Test;
import org.xmlcml.cml.converters.testutils.RegressionSuite;

/**
 *
 * @author ojd20
 */
public class RegressionTest {

   @Test
   @Ignore
   public void xyz2cml() {
      RegressionSuite.run("molecule/xyz/xyz2cml", "xyz", "cml",
                          new XYZ2CMLConverter());
   }

   @Test
   @Ignore
   public void cml2xyz() {
      RegressionSuite.run("molecule/xyz/cml2xyz", "cml", "xyz",
                          new CML2XYZConverter());
   }
}
