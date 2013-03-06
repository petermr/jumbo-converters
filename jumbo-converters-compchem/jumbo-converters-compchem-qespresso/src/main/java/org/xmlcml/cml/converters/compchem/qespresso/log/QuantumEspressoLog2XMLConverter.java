package org.xmlcml.cml.converters.compchem.qespresso.log;

import java.io.IOException;

import nu.xom.Element;

import org.xmlcml.cml.converters.MimeType;
import org.xmlcml.cml.converters.compchem.CompchemText2XMLTemplateConverter;
import org.xmlcml.cml.converters.compchem.qespresso.QespressoModule;

public class QuantumEspressoLog2XMLConverter extends CompchemText2XMLTemplateConverter {
	
	public QuantumEspressoLog2XMLConverter() {
	}
	
	public static QuantumEspressoLog2XMLConverter createDefaultConverter() {
		return new QuantumEspressoLog2XMLConverter(getDefaultTemplate("qespresso", "log", "topTemplate.xml", QuantumEspressoLog2XMLConverter.class));
	}
	
	public QuantumEspressoLog2XMLConverter(Element templateElement) {
		super(templateElement);
	}
	
	public static void main(String[] args) throws IOException {
		CompchemText2XMLTemplateConverter converter = new QuantumEspressoLog2XMLConverter();
//		File in = new File("D:\\projects\\nwchem-tests\\in\\ch3f_rot\\ch3f_rot.out");
//		File out = new File("test-out.xml");
//		converter.convert(in, out);
	}
	
	public MimeType getInputType() {
		return QespressoModule.LOG_TYPE;
	}
	
	public MimeType getOutputType() {
		return QespressoModule.LOG_XML_TYPE;
	}
	
	public String getDescription() {
		return "Convert Quantum Espresso log files to compchem";
	}
}