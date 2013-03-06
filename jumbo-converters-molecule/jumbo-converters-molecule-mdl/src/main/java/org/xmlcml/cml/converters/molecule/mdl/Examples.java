package org.xmlcml.cml.converters.molecule.mdl;

import java.io.File;

import org.xmlcml.cml.converters.Converter;

public class Examples {

	public void mdl2cml() {
		Converter converter = new MDL2CMLConverter();
		converter.convert(
				new File("src/test/resources/org/xmlcml/cml/converters/molecule/mdl/mdl2cml/in/crystal.mol"),
				new File("target/org/xmlcml/cml/converters/molecule/mdl/mdl2cml/out/crystal.cml"));
	}
	
	public void cml2mdl() {
		Converter converter = new CML2MDLConverter();
		converter.convert(
				new File("src/test/resources/org/xmlcml/cml/converters/molecule/mdl/cml2mdl/in/crystal.cml"),
				new File("target/org/xmlcml/cml/converters/molecule/mdl/mdl2cml/out/crystal.mol"));
	}
	
	public static void main(String[] args) {
		new Examples().mdl2cml();
		new Examples().cml2mdl();
	}
}
