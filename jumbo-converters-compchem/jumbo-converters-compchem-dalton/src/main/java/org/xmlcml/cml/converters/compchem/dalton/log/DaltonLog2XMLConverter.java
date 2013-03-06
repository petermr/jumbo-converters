package org.xmlcml.cml.converters.compchem.dalton.log;

import java.io.IOException;

import nu.xom.Element;

import org.xmlcml.cml.converters.MimeType;
import org.xmlcml.cml.converters.compchem.CompchemText2XMLTemplateConverter;
import org.xmlcml.cml.converters.compchem.dalton.DaltonModule;

public class DaltonLog2XMLConverter extends CompchemText2XMLTemplateConverter {
	
	public static final String DALTON_LOG_TO_XML_CONVERTER = "Dalton Log to XML Converter";

	public DaltonLog2XMLConverter() {
	}
	
	public static DaltonLog2XMLConverter createDefaultConverter() {
		return new DaltonLog2XMLConverter(getDefaultTemplate("dalton", "log", "topTemplate.xml", DaltonLog2XMLConverter.class));
	}
	
	public DaltonLog2XMLConverter(Element templateElement) {
		super(templateElement);
	}
	
	public static void main(String[] args) throws IOException {
		CompchemText2XMLTemplateConverter converter = new DaltonLog2XMLConverter();
//		File in = new File("D:\\projects\\nwchem-tests\\in\\ch3f_rot\\ch3f_rot.out");
//		File out = new File("test-out.xml");
//		converter.convert(in, out);
	}
	
	public MimeType getInputType() {
		return DaltonModule.LOG_TYPE;
	}
	
	public MimeType getOutputType() {
		return DaltonModule.LOG_XML_TYPE;
	}
	
	public String getDescription() {
		return DALTON_LOG_TO_XML_CONVERTER;
	}
}