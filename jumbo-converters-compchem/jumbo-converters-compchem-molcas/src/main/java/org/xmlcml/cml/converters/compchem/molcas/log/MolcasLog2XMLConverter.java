package org.xmlcml.cml.converters.compchem.molcas.log;

import java.io.IOException;

import nu.xom.Element;

import org.xmlcml.cml.converters.MimeType;
import org.xmlcml.cml.converters.compchem.CompchemText2XMLTemplateConverter;
import org.xmlcml.cml.converters.compchem.molcas.MolcasModule;

public class MolcasLog2XMLConverter extends CompchemText2XMLTemplateConverter {
	
	public MolcasLog2XMLConverter() {
	}
	
	public static MolcasLog2XMLConverter createDefaultConverter() {
		return new MolcasLog2XMLConverter(getDefaultTemplate("molcas", "log", "topTemplate.xml", MolcasLog2XMLConverter.class));
	}
	
	public MolcasLog2XMLConverter(Element templateElement) {
		super(templateElement);
	}
	
	public static void main(String[] args) throws IOException {
		CompchemText2XMLTemplateConverter converter = new MolcasLog2XMLConverter();
//		File in = new File("D:\\projects\\nwchem-tests\\in\\ch3f_rot\\ch3f_rot.out");
//		File out = new File("test-out.xml");
//		converter.convert(in, out);
	}
	
	public MimeType getInputType() {
		return MolcasModule.LOG_TYPE;
	}

	public MimeType getOutputType() {
		return MolcasModule.LOG_XML_TYPE;
	}

	public String getDescription() {
		return "Convert Molcas Log to XML";
	}

}