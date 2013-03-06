package org.xmlcml.cml.converters.reaction;

import java.util.ArrayList;
import java.util.List;

import org.xmlcml.cml.converters.Converter;
import org.xmlcml.cml.converters.MimeType;
import org.xmlcml.cml.converters.MimeType.ObjectType;
import org.xmlcml.cml.converters.registry.AbstractConverterModule;

/**
 * @author Sam Adams
 */
public class ReactionModule extends AbstractConverterModule {

	public static final MimeType RXN_TYPE = new MimeType("chemical/x-mdl-rxnfile", ObjectType.TEXT, "rxn");
	private static final String PREFIX = "reaction";
	
    public String getPrefix() {
    	return PREFIX;
    }

    public ReactionModule() {
    	super();
    }

	public List<Converter> getConverterList() {
		return converterList;
	}

	public List<MimeType> getMimeTypeList() {
		if (mimeTypeList == null) {
			mimeTypeList = new ArrayList<MimeType>();
			mimeTypeList.add(RXN_TYPE);
		}
		return mimeTypeList;
	}
	
}
