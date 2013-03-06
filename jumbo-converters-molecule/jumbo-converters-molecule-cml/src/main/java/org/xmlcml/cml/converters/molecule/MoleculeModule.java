package org.xmlcml.cml.converters.molecule;

import java.util.ArrayList;

import java.util.List;

import org.xmlcml.cml.converters.Converter;
import org.xmlcml.cml.converters.MimeType;
import org.xmlcml.cml.converters.MimeType.ObjectType;
import org.xmlcml.cml.converters.registry.AbstractConverterModule;

/**
 * @author Sam Adams
 */
public class MoleculeModule extends AbstractConverterModule {

	public static final MimeType MDL_TYPE = new MimeType("chemical/x-mdl-molfile", ObjectType.TEXT, "mol");
	public static final MimeType PUBCHEM_XML_TYPE = new MimeType("chemical/x-pubchem-xml", ObjectType.XML, "xml");
	public static final MimeType SDF_TYPE = new MimeType("chemical/x-sdf", ObjectType.TEXT, "sdf");
	public static final MimeType XYZ_TYPE = new MimeType("chemical/x-xyz", ObjectType.TEXT, "xyz");
	private static final String PREFIX = "molecule";

    public String getPrefix() {
    	return PREFIX;
    }

    public MoleculeModule() {
    	super();
    }

	public List<Converter> getConverterList() {
//        converterList.add(new CMLEditor1());
//        converterList.add(new CMLTransformMolecule());
//        converterList.add(new CML2MDLConverter());
//        converterList.add(new MDL2CMLConverter());
//        converterList.add(new CML2SDFConverter());
//        converterList.add(new SDF2CMLConverter());
//        converterList.add(new PubchemXML2CMLConverter());
//        converterList.add(new CML2XYZConverter());
//        converterList.add(new XYZ2CMLConverter());
		return converterList;
	}

	public List<MimeType> getMimeTypeList() {
		if (mimeTypeList == null) {
			mimeTypeList = new ArrayList<MimeType>();
			mimeTypeList.add(MDL_TYPE);
			mimeTypeList.add(PUBCHEM_XML_TYPE);
			mimeTypeList.add(SDF_TYPE);
			mimeTypeList.add(XYZ_TYPE);
		}
		return mimeTypeList;
	}
	
}
