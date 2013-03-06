/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.xmlcml.cml.converters.molecule.mdl;


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
   public void cml2mdl() {
      RegressionSuite.run("molecule/mdl/cml2mdl", "cml", "mol",
                          new CML2MDLConverter());
   }

   @Test
   @Ignore
   public void mdl2cml() {
      RegressionSuite.run("molecule/mdl/mdl2cml", "mol", "cml",
                          new MDL2CMLConverter());
   }

   @Test
   @Ignore
   public void sdf2cml() {
      RegressionSuite.run("molecule/sdf", "sdf", "cml",
                          new SDF2CMLConverter());
   }
}
