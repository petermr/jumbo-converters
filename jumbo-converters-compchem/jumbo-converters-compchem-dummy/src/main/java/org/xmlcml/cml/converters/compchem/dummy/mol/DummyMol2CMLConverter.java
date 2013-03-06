package org.xmlcml.cml.converters.compchem.dummy.mol;

import java.io.IOException;
import java.util.List;

import nu.xom.Element;

import org.xmlcml.cml.converters.AbstractConverter;
import org.xmlcml.cml.converters.MimeType;
import org.xmlcml.cml.converters.cml.CMLCommon;
import org.xmlcml.cml.converters.compchem.dummy.DummyModule;
import org.xmlcml.cml.element.CMLCml;

public class DummyMol2CMLConverter extends AbstractConverter {

	public static final String DUMMY_MOL_TO_CML_CONVERTER = "Dummy Molecule to CML Converter";

	public DummyMol2CMLConverter() {
		super();
	}
	
	public static void main(String[] args) throws IOException {
		AbstractConverter converter = new DummyMol2CMLConverter();
	}
	
	@Override
	public Element convertToXML(List<String> lines) {
		CMLCml cml = null;
		if (lines != null && lines.size() > 0) {
			MolProcessor molProcessor = new MolProcessor();
			cml = molProcessor.create(lines);
		}
		return cml;
		
	}

	public MimeType getInputType() {
		return DummyModule.MOL_TYPE;
	}

	public MimeType getOutputType() {
		return CMLCommon.CML_TYPE;
	}

	public String getDescription() {
		return DUMMY_MOL_TO_CML_CONVERTER;
	}
}