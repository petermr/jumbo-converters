package org.xmlcml.cml.converters.templates.output.field;



public class WidthField extends Field {

	public WidthField(SimpleFortranFormat sff, FieldType type) {
		super(sff, type, null);
		setTypesAndParameters();
	}
	private void setTypesAndParameters() {
		this.setDataTypeClass(fieldType);
		int widthToRead= simpleFortranFormat.readWidth();
		this.setWidth(widthToRead);
	}
}
