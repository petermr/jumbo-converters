package org.xmlcml.cml.converters.compchem.nwchem.log;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;

import nu.xom.Builder;
import nu.xom.Element;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.xmlcml.cml.converters.templates.output.Text2XMLTemplateConverter;
import org.xmlcml.cml.converters.util.ClassPathXIncludeResolver;
import org.xmlcml.euclid.Util;

public class NWChemLog2XMLConverterTest {

	private static String URI_BASE = ClassPathXIncludeResolver.createClasspath(NWChemLog2XMLConverterTest.class);

	@Test
	@Ignore
	public void test0() throws Exception {
		String filename = URI_BASE+"in/ch3f.log";
		System.out.println(filename);
		File file = new File(filename);
		System.out.println(file.getAbsolutePath());
		System.out.println(file.length());
		InputStream inputStream = Util.getInputStreamFromResource(URI_BASE+"in/ch3f.log");
		BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
		while (true) {
			String line = br.readLine();
			System.out.println(">>? "+line);
			if (line == null) break;
		}
	}

	@Test
	@Ignore // fails
	public void test1() throws Exception {
		InputStream templateStream = Util.getInputStreamFromResource(URI_BASE+"templates/topTemplate.xml");
		Element templateXML = new Builder().build(templateStream, "classpath:"+URI_BASE+"templates/").getRootElement();
		InputStream inputStream = Util.getInputStreamFromResource(URI_BASE+"in/ch3f.log");
		Text2XMLTemplateConverter converter = new NWChemLog2XMLConverter(templateXML);
        File out = new File(new File("."), "target/test/compchem/nwchem/test1.xml");
		converter.convert(inputStream, out);
		Assert.assertTrue(out.exists());
	}

}
