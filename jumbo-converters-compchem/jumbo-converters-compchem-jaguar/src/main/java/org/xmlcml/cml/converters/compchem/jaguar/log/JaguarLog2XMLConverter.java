package org.xmlcml.cml.converters.compchem.jaguar.log;

import java.io.File;
import java.io.IOException;

import nu.xom.Element;

import org.apache.log4j.Logger;
import org.xmlcml.cml.converters.MimeType;
import org.xmlcml.cml.converters.compchem.CompchemText2XMLTemplateConverter;
import org.xmlcml.cml.converters.compchem.jaguar.JaguarModule;
import org.xmlcml.cml.converters.util.ConverterUtils;

public class JaguarLog2XMLConverter extends CompchemText2XMLTemplateConverter {
	
	private static Logger LOG = Logger.getLogger(JaguarLog2XMLConverter.class);
	private static final String BASE_URI = "classpath:/org/xmlcml/cml/converters/compchem/jaguar/log/templates/topTemplate.xml";

	public JaguarLog2XMLConverter() {
	}
	
	public static JaguarLog2XMLConverter createDefaultConverter() {
		return new JaguarLog2XMLConverter(BASE_URI, "templates/topTemplate.xml");
	}
	
	public JaguarLog2XMLConverter(String baseUri, String templateName) {
		this(ConverterUtils.buildElementIncludingBaseUri(baseUri, templateName, JaguarLog2XMLConverter.class));
	}
	
	public JaguarLog2XMLConverter(Element templateElement) {
		super(templateElement);
	}
	
	public static void usage() {
		System.out.println("JaguarConverter <infile> <outfile>");
	}
	public static void main(String[] args) throws IOException {
		if (args.length != 2) {
			usage();
		} else {
			CompchemText2XMLTemplateConverter converter = new JaguarLog2XMLConverter();
			File in = new File(args[0]);
			File out = new File(args[1]);
			converter.convert(in, out);
		}
	}
	
	public MimeType getInputType() {
		return JaguarModule.LOG_TYPE;
	}

	public MimeType getOutputType() {
		return JaguarModule.LOG_XML_TYPE;
	}

	public String getDescription() {
		return "Convert Jaguar Log to XML";
	}

}