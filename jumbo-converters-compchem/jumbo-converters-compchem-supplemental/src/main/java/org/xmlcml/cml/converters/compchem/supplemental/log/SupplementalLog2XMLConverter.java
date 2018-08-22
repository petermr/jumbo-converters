package org.xmlcml.cml.converters.compchem.supplemental.log;

import java.io.IOException;


import nu.xom.Element;
import org.xmlcml.cml.converters.MimeType;

import org.xmlcml.cml.converters.compchem.CompchemText2XMLTemplateConverter;
import org.xmlcml.cml.converters.compchem.supplemental.SupplementalCommon;

public class SupplementalLog2XMLConverter extends CompchemText2XMLTemplateConverter {

	public static final String SUPPLEMENTAL_LOG_TO_XML_CONVERTER = "Supplemental Log to XML Converter";

	public SupplementalLog2XMLConverter() {
		this(getDefaultTemplate("supplemental", "log", TEMPLATE_XML_REL_TO_CLAZZ, SupplementalLog2XMLConverter.class));
	}

	public SupplementalLog2XMLConverter(Element templateElement) {
		super(templateElement);
	}

	public static void main(String[] args) throws IOException {
		CompchemText2XMLTemplateConverter converter = new SupplementalLog2XMLConverter();
	}
	
	public MimeType getInputType() {
		return SupplementalCommon.LOG_TYPE;
	}

	public MimeType getOutputType() {
		return SupplementalCommon.LOG_XML_TYPE;
	}

	public String getDescription() {
		return SUPPLEMENTAL_LOG_TO_XML_CONVERTER;
	}
}
