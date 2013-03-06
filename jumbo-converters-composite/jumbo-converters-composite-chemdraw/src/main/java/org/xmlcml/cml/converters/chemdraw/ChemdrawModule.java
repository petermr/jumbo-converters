package org.xmlcml.cml.converters.chemdraw;

import java.util.ArrayList;
import java.util.List;

import org.xmlcml.cml.converters.Converter;
import org.xmlcml.cml.converters.MimeType;
import org.xmlcml.cml.converters.MimeType.ObjectType;
import org.xmlcml.cml.converters.registry.AbstractConverterModule;

/**
 * @author Sam Adams
 */
public class ChemdrawModule extends AbstractConverterModule {


	public static final MimeType CDX_TYPE = new MimeType("chemical/x-cdx", ObjectType.BYTES, "cdx");
	public static final MimeType CDXML_TYPE = new MimeType("chemical/x-cdxml", ObjectType.XML, "cdxml");
	
    public ChemdrawModule() {
    	super();
    }

    public String getPrefix() {
    	return "chemdraw";
    }
    
	public List<Converter> getConverterList() {
		if (converterList == null) {
			converterList = new ArrayList<Converter>();
	        converterList.add(new CDX2CDXMLConverter());
	        converterList.add(new CDX2CMLConverter());
	        converterList.add(new CDXML2CMLConverter());
		}
		return converterList;
    }

	@Override
	public List<MimeType> getMimeTypeList() {
		if (mimeTypeList == null) {
			mimeTypeList = new ArrayList<MimeType>();
			mimeTypeList.add(CDX_TYPE);
			mimeTypeList.add(CDXML_TYPE);
		}
		return mimeTypeList;
	}

}
