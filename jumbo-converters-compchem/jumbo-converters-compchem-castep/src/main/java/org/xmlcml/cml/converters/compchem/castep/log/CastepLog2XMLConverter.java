package org.xmlcml.cml.converters.compchem.castep.log;

import java.io.IOException;

import nu.xom.Element;

import org.xmlcml.cml.converters.MimeType;
import org.xmlcml.cml.converters.compchem.CompchemText2XMLTemplateConverter;
import org.xmlcml.cml.converters.compchem.castep.CastepModule;

public class CastepLog2XMLConverter extends CompchemText2XMLTemplateConverter {

	public static final String CASTEP_LOG_TO_XML_CONVERTER = "Castep Log to XML Converter";

	public CastepLog2XMLConverter() {
		this(getDefaultTemplate("castep", "log", TEMPLATE_XML_REL_TO_CLAZZ, CastepLog2XMLConverter.class));
	}

	public CastepLog2XMLConverter(Element templateElement) {
		super(templateElement);
	}

	public static void main(String[] args) throws IOException {
		CompchemText2XMLTemplateConverter converter = new CastepLog2XMLConverter();
	}

	public MimeType getInputType() {
		return CastepModule.LOG_TYPE;
	}

	public MimeType getOutputType() {
		return CastepModule.LOG_XML_TYPE;
	}

	public String getDescription() {
		return CASTEP_LOG_TO_XML_CONVERTER;
	}
}