package org.xmlcml.cml.converters.cml;

import org.xmlcml.cml.base.CMLElement;

public interface AbstractCMLFilter {
	
	boolean accept(CMLElement element);
}
