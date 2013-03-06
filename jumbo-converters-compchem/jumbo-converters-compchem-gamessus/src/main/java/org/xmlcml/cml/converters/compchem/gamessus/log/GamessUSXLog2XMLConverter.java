package org.xmlcml.cml.converters.compchem.gamessus.log;

import java.io.IOException;

import nu.xom.Element;

import org.xmlcml.cml.converters.MimeType;
import org.xmlcml.cml.converters.compchem.CompchemText2XMLTemplateConverter;
import org.xmlcml.cml.converters.compchem.gamessus.GamessUSModule;

public class GamessUSXLog2XMLConverter extends CompchemText2XMLTemplateConverter {
	
	private static final String GAMESSUS_LOG_TO_XML = "GamessUS Log to Log_XML";

	public GamessUSXLog2XMLConverter() {
	}
	
	public static GamessUSXLog2XMLConverter createDefaultConverter() {
		return new GamessUSXLog2XMLConverter(getDefaultTemplate("gamessus", "log", "topTemplate.xml", GamessUSXLog2XMLConverter.class));
	}
	
	public GamessUSXLog2XMLConverter(Element templateElement) {
		super(templateElement);
	}
	
	public static void main(String[] args) throws IOException {
		CompchemText2XMLTemplateConverter converter = new GamessUSXLog2XMLConverter();
//		File in = new File("D:\\projects\\nwchem-tests\\in\\ch3f_rot\\ch3f_rot.out");
//		File out = new File("test-out.xml");
//		converter.convert(in, out);
	}
	
	public MimeType getInputType() {
		return GamessUSModule.LOG_TYPE;
	}
	
	public MimeType getOutputType() {
		return GamessUSModule.LOG_XML_TYPE;
	}
	
	public String getDescription() {
		return GAMESSUS_LOG_TO_XML;
	}
}