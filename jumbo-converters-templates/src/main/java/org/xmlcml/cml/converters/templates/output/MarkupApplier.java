package org.xmlcml.cml.converters.templates.output;

import nu.xom.Element;

public interface MarkupApplier {

	final String ID = "id";
	void applyMarkup(LineContainer lineContainer);
	void applyMarkup(Element element);
	void debug();
	String getId();
}
