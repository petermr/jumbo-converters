package org.xmlcml.cml.converters.registry;

import org.xmlcml.cml.converters.MimeType;
import org.xmlcml.euclid.Util;

public class TypePair {

    private String inString;
    private String outString;
    private MimeType inType;
    private MimeType outType;

    public TypePair(String in, String out) {
        this.inString = in;
        this.outString = out;
    }

    public TypePair(MimeType inType, MimeType outType) {
        this.inType = inType;
        this.outType = outType;
    }

    public String getInString() {
        return inString;
    }

    public String getOutString() {
        return outString;
    }

    public MimeType getInType() {
        return inType;
    }

    public MimeType getOutType() {
        return outType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof TypePair) {
            TypePair t = (TypePair) o;
            return (this.inString == null || t.inString == null || this.outString == null || t.outString == null) ? false :
            	this.inString.equals(t.inString) && this.outString.equals(t.outString);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int inhash = (inString != null) ? inString.hashCode()*31 : 137;
        int outhash = (outString != null) ? outString.hashCode() : 0;
        return inhash + outhash;
    }

    public String toString() {
        return Util.S_LSQUARE+inString + Util.S_PIPE + outString+Util.S_RSQUARE;
    }

}
