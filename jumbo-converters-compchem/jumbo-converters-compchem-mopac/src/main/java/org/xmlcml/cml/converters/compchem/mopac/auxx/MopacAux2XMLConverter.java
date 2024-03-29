package org.xmlcml.cml.converters.compchem.mopac.auxx;

import java.io.IOException;

import nu.xom.Element;

import org.xmlcml.cml.converters.MimeType;
import org.xmlcml.cml.converters.compchem.CompchemText2XMLTemplateConverter;
import org.xmlcml.cml.converters.compchem.mopac.MopacModule;

public class MopacAux2XMLConverter extends CompchemText2XMLTemplateConverter {
	
	public MopacAux2XMLConverter() {
	}
	
	public static MopacAux2XMLConverter createDefaultConverter() {
		return new MopacAux2XMLConverter(getDefaultTemplate("mopac", "auxx", "topTemplate.xml", MopacAux2XMLConverter.class));
	}
	
	public MopacAux2XMLConverter(Element templateElement) {
		super(templateElement);
	}
	
	public static void main(String[] args) throws IOException {
		CompchemText2XMLTemplateConverter converter = new MopacAux2XMLConverter();
//		File in = new File("D:\\projects\\nwchem-tests\\in\\ch3f_rot\\ch3f_rot.out");
//		File out = new File("test-out.xml");
//		converter.convert(in, out);
	}
	public MimeType getInputType() {
		return MopacModule.AUX_TYPE;
	}

	public MimeType getOutputType() {
		return MopacModule.AUX_XML_TYPE;
	}

	public String getDescription() {
		return "Convert Mopac Aux to XML";
	}


}