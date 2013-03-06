package org.xmlcml.cml.converters.compchem.dlpoly.log;

import java.io.IOException;

import nu.xom.Element;

import org.xmlcml.cml.converters.MimeType;
import org.xmlcml.cml.converters.compchem.CompchemText2XMLTemplateConverter;
import org.xmlcml.cml.converters.compchem.dlpoly.DLPolyModule;

public class DLPolyLog2XMLConverter extends CompchemText2XMLTemplateConverter {

	public static final String DLPOLY_LOG_TO_XML_CONVERTER = "DLPoly Log to XML Converter";

	public DLPolyLog2XMLConverter() {
	}

	public static DLPolyLog2XMLConverter createDefaultConverter() {
		return new DLPolyLog2XMLConverter(getDefaultTemplate("dlpoly", "log", TEMPLATE_XML_REL_TO_CLAZZ, DLPolyLog2XMLConverter.class));
	}

	public DLPolyLog2XMLConverter(Element templateElement) {
		super(templateElement);
	}

	public static void main(String[] args) throws IOException {
		CompchemText2XMLTemplateConverter converter = new DLPolyLog2XMLConverter();
	}

	public MimeType getInputType() {
		return DLPolyModule.LOG_TYPE;
	}

	public MimeType getOutputType() {
		return DLPolyModule.LOG_XML_TYPE;
	}

	public String getDescription() {
		return DLPOLY_LOG_TO_XML_CONVERTER;
	}
}