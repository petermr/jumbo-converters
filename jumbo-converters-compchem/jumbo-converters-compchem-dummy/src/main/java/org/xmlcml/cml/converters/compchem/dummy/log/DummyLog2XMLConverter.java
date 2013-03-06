package org.xmlcml.cml.converters.compchem.dummy.log;

import java.io.IOException;

import nu.xom.Element;

import org.xmlcml.cml.converters.MimeType;
import org.xmlcml.cml.converters.compchem.CompchemText2XMLTemplateConverter;
import org.xmlcml.cml.converters.compchem.dummy.DummyModule;

public class DummyLog2XMLConverter extends CompchemText2XMLTemplateConverter {

	public static final String DUMMY_LOG_TO_XML_CONVERTER = "Dummy Log to XML Converter";

	public DummyLog2XMLConverter() {
		this(getDefaultTemplate("dummy", "log", TEMPLATE_XML_REL_TO_CLAZZ, DummyLog2XMLConverter.class));
	}

	public DummyLog2XMLConverter(Element templateElement) {
		super(templateElement);
	}

	public static void main(String[] args) throws IOException {
		CompchemText2XMLTemplateConverter converter = new DummyLog2XMLConverter();
	}

	public MimeType getInputType() {
		return DummyModule.LOG_TYPE;
	}

	public MimeType getOutputType() {
		return DummyModule.LOG_XML_TYPE;
	}

	public String getDescription() {
		return DUMMY_LOG_TO_XML_CONVERTER;
	}
}