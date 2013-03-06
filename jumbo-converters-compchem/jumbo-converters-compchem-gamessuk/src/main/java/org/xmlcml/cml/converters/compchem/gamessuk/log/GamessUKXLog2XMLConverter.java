package org.xmlcml.cml.converters.compchem.gamessuk.log;

import java.io.IOException;

import nu.xom.Element;

import org.xmlcml.cml.converters.MimeType;
import org.xmlcml.cml.converters.compchem.CompchemText2XMLTemplateConverter;
import org.xmlcml.cml.converters.compchem.gamessuk.GamessUKModule;

public class GamessUKXLog2XMLConverter extends CompchemText2XMLTemplateConverter {
	
	private static final String GAMESSUK_LOG_TO_XML = "GamessUK_LOG to GamessUK_LOG_XML";

	public GamessUKXLog2XMLConverter() {
	}
	
	public static GamessUKXLog2XMLConverter getDefaultConverter() {
		return new GamessUKXLog2XMLConverter(getDefaultTemplate("gamessuk", "log", "topTemplate.xml", GamessUKXLog2XMLConverter.class));
	}
	
	public GamessUKXLog2XMLConverter(Element templateElement) {
		super(templateElement);
	}
	
	public static void main(String[] args) throws IOException {
		CompchemText2XMLTemplateConverter converter = new GamessUKXLog2XMLConverter();
//		File in = new File("D:\\projects\\nwchem-tests\\in\\ch3f_rot\\ch3f_rot.out");
//		File out = new File("test-out.xml");
//		converter.convert(in, out);
	}
	
	public MimeType getInputType() {
		return GamessUKModule.LOG_TYPE;
	}
	
	public MimeType getOutputType() {
		return GamessUKModule.LOG_XML_TYPE;
	}
	
	public String getDescription() {
		return GAMESSUK_LOG_TO_XML;
	}
}