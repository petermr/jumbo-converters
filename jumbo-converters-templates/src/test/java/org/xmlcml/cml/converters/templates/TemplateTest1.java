package org.xmlcml.cml.converters.templates;

import java.io.File;
import java.io.FileOutputStream;

import junit.framework.Assert;
import nu.xom.Element;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.xmlcml.cml.base.CMLUtil;
import org.xmlcml.cml.converters.templates.output.LineContainer;
import org.xmlcml.cml.converters.templates.output.Template;
import org.xmlcml.cml.testutil.JumboTestUtils;

public class TemplateTest1 {
	
	
	@Test
	public void test0() throws Exception {
		generateAndCompareXml(
			"qespresso",
			"src/test/resources/org/xmlcml/cml/converters/text/", 
			"target/test/text",
			"qespresso0.out",
			"template0.xml",
			"qespresso0.xml"
		);
	}

	
	private void generateAndCompareXml(
			String title, String dirS, String outDirS, String infileS, String templateFileS, String refS) throws Exception {
		File dir = new File(dirS);
		dir.mkdirs();
		File out = new File(outDirS);
		out.mkdirs();
		String stringToBeParsed = FileUtils.readFileToString(new File(dir, infileS));
		Assert.assertNotNull(stringToBeParsed);
		String templateS = FileUtils.readFileToString(new File(dir, templateFileS));
		Template template = new Template(CMLUtil.parseXML(templateS));
		template.applyMarkup(stringToBeParsed);
		LineContainer lineContainer = template.getLineContainer();
		Element linesElement = lineContainer.getNormalizedLinesElement();
		CMLUtil.debug(lineContainer.getLinesElement(), new FileOutputStream(new File(out, refS)), 1);
		String refXml = FileUtils.readFileToString(new File(dir, refS));
		JumboTestUtils.assertEqualsCanonically(title, refXml, linesElement, true);
	}
}
