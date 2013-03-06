package org.xmlcml.cml.converters.compchem.qchem.log;

import java.io.IOException;

import nu.xom.Element;

import org.xmlcml.cml.converters.MimeType;
import org.xmlcml.cml.converters.compchem.CompchemText2XMLTemplateConverter;
import org.xmlcml.cml.converters.compchem.qchem.QChemModule;

public class QChemLog2XMLConverter extends CompchemText2XMLTemplateConverter {

	public static final String QCHEM_LOG_TO_XML_CONVERTER = "QChem Log to XML Converter";

	public QChemLog2XMLConverter() {
		this(getDefaultTemplate("qchem", "log", TEMPLATE_XML_REL_TO_CLAZZ, QChemLog2XMLConverter.class));
	}

	public QChemLog2XMLConverter(Element templateElement) {
		super(templateElement);
	}

	public static void main(String[] args) throws IOException {
		CompchemText2XMLTemplateConverter converter = new QChemLog2XMLConverter();
	}

	public MimeType getInputType() {
		return QChemModule.LOG_TYPE;
	}

	public MimeType getOutputType() {
		return QChemModule.LOG_XML_TYPE;
	}

	public String getDescription() {
		return QCHEM_LOG_TO_XML_CONVERTER;
	}
}