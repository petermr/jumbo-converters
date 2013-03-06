package org.xmlcml.cml.converters.templates.output;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.xmlcml.cml.converters.AbstractConverter;
import org.xmlcml.cml.converters.MimeType;
import org.xmlcml.euclid.Util;

public abstract class Text2XMLConverter extends AbstractConverter {
	private static final Logger LOG = Logger.getLogger(Text2XMLConverter.class);
	private static final Integer TAB_WIDTH = 8;
	protected Integer tabWidth = TAB_WIDTH;

	public MimeType getInputType() {
		return MimeType.TXT;
	}
	
	public MimeType getOutputType() {
		return MimeType.XML;
	}
	
	public Text2XMLConverter() {
		// must be faithful to whitespace
		this.setIndent(0);
	}
	
	/**
	 * deal with tabs, other possible conversions...
	 */
	protected List<String> convertCharactersInLines(List<String> lines) {
		List<String> newlines = new ArrayList<String>(lines.size());
		for (String line : lines) {
			newlines.add(Util.replaceTabs(line, (int)TAB_WIDTH));
		}
		return lines;
	}

//	public Integer getTabWidth() {
//		return tabWidth;
//	}
//
//	public void setTabWidth(Integer tabWidth) {
//		this.tabWidth = tabWidth;
//	}



	
}
