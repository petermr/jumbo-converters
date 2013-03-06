package org.xmlcml.cml.converters.compchem.castep.log;

import org.junit.Test;

import org.xmlcml.cml.converters.testutils.TemplateTester;

public class TemplateTest {
	
	private TemplateTester templateTester = new TemplateTester("castep", "log", ".log", this.getClass());
	
	@Test
	public void testCommentExamples() {
		templateTester.runCommentExamples();
	}
	
	// NYI
//	@Test	public void testGeneralParamComment()       {templateTester.runTemplateCommentExamples("generalParam");}
	
	@Test	public void testCastepLogFile()             {templateTester.runTestOnFile("castep");}
	

}
