package org.xmlcml.cml.converters.templates.output.field;



public class SpaceField extends Field {

	public SpaceField(SimpleFortranFormat sff, FieldType type) {
		super(sff, type, null);
		setTypesAndParameters();
	}
	private void setTypesAndParameters() {
		this.setFieldType(FieldType.X);
		this.setDataTypeClass(FieldType.X);
		int widthToRead= simpleFortranFormat.parseSpaceFormatInto(this, fieldType);
		this.setWidth(widthToRead);
	}
}
