package org.xmlcml.cml.converters.cif;

import java.util.ArrayList;
import java.util.List;

import org.xmlcml.cml.converters.Converter;
import org.xmlcml.cml.converters.MimeType;
import org.xmlcml.cml.converters.MimeType.ObjectType;
import org.xmlcml.cml.converters.registry.AbstractConverterModule;

/**
 * @author Sam Adams
 */
public class CIFModule extends AbstractConverterModule {

	public static final MimeType CIF_TYPE = new MimeType("chemical/x-cif", ObjectType.TEXT, "cif");
	public static final MimeType CIFXML_TYPE = new MimeType("chemical/x-cifxml", ObjectType.XML, "cif.xml");
	public static final MimeType CIF_RAW_CML_TYPE = new MimeType("chemical/x-cif-raw-xml", ObjectType.XML, "cif.raw.xml");
	
    public CIFModule() {
    	super();
    }

    public String getPrefix() {
    	return "chemdraw";
    }
    
	public List<Converter> getConverterList() {
        converterList.add(new CIF2CIFXMLConverter());
        converterList.add(new CIF2CMLConverter());
        converterList.add(new CIFXML2CMLConverter());
        converterList.add(new RawCML2CompleteCMLConverter());
		return converterList;
	}

	public List<MimeType> getMimeTypeList() {
		if (mimeTypeList == null) {
			mimeTypeList = new ArrayList<MimeType>();
			mimeTypeList.add(CIF_TYPE);
			mimeTypeList.add(CIFXML_TYPE);
			mimeTypeList.add(CIF_RAW_CML_TYPE);
		}
		return mimeTypeList;
	}
	
}
