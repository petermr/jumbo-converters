package org.xmlcml.cml.converters.molecule.cml;


import java.io.InputStream;
import java.io.OutputStream;

import nu.xom.Element;
import nu.xom.Nodes;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.xmlcml.cml.base.CMLBuilder;
import org.xmlcml.cml.base.CMLConstants;
import org.xmlcml.cml.base.CMLElement;
import org.xmlcml.cml.base.CMLUtil;
import org.xmlcml.cml.converters.AbstractConverter;
import org.xmlcml.cml.converters.JumboParameter;
import org.xmlcml.cml.converters.MimeType;
import org.xmlcml.cml.converters.cml.CMLCommon;
import org.xmlcml.cml.element.CMLMolecule;
import org.xmlcml.cml.element.CMLMolecule.HydrogenControl;
import org.xmlcml.cml.tools.MoleculeLayout;
import org.xmlcml.cml.tools.MoleculeTool;


/** allows editing of CMLObjects
 * not yet finished
 * 
 * @author pm286
 *
 */
public class CMLTransformMolecule extends AbstractConverter {

	private static final Logger LOG = Logger.getLogger(CMLTransformMolecule.class);
	static {
		LOG.setLevel(Level.INFO);
	}
	
	private JumboParameter command = new JumboParameter(JumboParameter.COMMAND, "", false);  //?
	private JumboParameter xpath = new JumboParameter(JumboParameter.XPATH, ".//cml:molecule", true);

	private CMLElement cmlElement;

	public CMLTransformMolecule() {
	}

	@Override
	public void convert(InputStream inputStream, OutputStream outputStream) {
		try {
			cmlElement = (CMLElement) new CMLBuilder().build(inputStream).getRootElement();
			transformMolecules();
			CMLUtil.debug(cmlElement, outputStream, 0);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void transformMolecules() {
		TransformMoleculeCommand commandx = TransformMoleculeCommand.getCommand(command.getName());
		if (commandx == null) {
			throw new RuntimeException("cannot find command: "+command);
		}
		Nodes nodes = cmlElement.query(xpath.getValue(), CMLConstants.CML_XPATH);
		for (int i = 0; i < nodes.size(); i++) {
			if (nodes.get(i) instanceof CMLMolecule) {
				transform((CMLMolecule)nodes.get(i), commandx);
			}
		}
	}
	
	public void transformMolecule(String command, Element element, String xpath) {
		TransformMoleculeCommand commandx = TransformMoleculeCommand.getCommand(command);
		if (commandx == null) {
			throw new RuntimeException("cannot find command: "+command);
		}
		Nodes nodes = element.query(xpath, CMLConstants.CML_XPATH);
		for (int i = 0; i < nodes.size(); i++) {
			if (nodes.get(i) instanceof CMLMolecule) {
				transform((CMLMolecule)nodes.get(i), commandx);
			}
		}
	}
	
	public void transform(CMLMolecule molecule, TransformMoleculeCommand commandx) {
		MoleculeTool moleculeTool = MoleculeTool.getOrCreateTool(molecule);
		if (TransformMoleculeCommand.fractionalToCartesian.equals(commandx)) {
			throw new RuntimeException("transformFractionalToCartesian NYI");
		} else if (TransformMoleculeCommand.addHydrogens.equals(commandx)) {
			addHydrogens(moleculeTool);
		} else if (TransformMoleculeCommand.addHydrogen2D.equals(commandx)) {
			addHydrogen2D(moleculeTool);
		} else if (TransformMoleculeCommand.addHydrogen3D.equals(commandx)) {
			addHydrogen3D(moleculeTool);
		} else if (TransformMoleculeCommand.calculateBonds.equals(commandx)) {
			moleculeTool.calculateBondedAtoms();
		} else if (TransformMoleculeCommand.addBondOrders.equals(commandx)) {
			moleculeTool.adjustBondOrdersToValency();
		} else if (TransformMoleculeCommand.adjustBondOrdersToValency.equals(commandx)) {
			moleculeTool.adjustBondOrdersToValency();
		} else if (TransformMoleculeCommand.add2DCoordinates.equals(commandx)) {
			add2DCoordinates(moleculeTool);
		} else if (TransformMoleculeCommand.add3DCoordinates.equals(commandx)) {
			throw new RuntimeException("ADD 3D NYI");
		} else if (TransformMoleculeCommand.addAtomParityFromCoordinates.equals(commandx)) {
			throw new RuntimeException("atom parity NYI");
		} else if (TransformMoleculeCommand.addBondStereoFromCoordinates.equals(commandx)) {
			throw new RuntimeException("bond stereo NYI");
		} else if (TransformMoleculeCommand.addFormulaFromAtoms.equals(commandx)) {
			throw new RuntimeException("addFormulaFromAtoms not yet implemented");
		} else if (TransformMoleculeCommand.addSMILESFromFormula.equals(commandx)) {
			throw new RuntimeException("addSMILESFromFormula not yet implemented");
		} else if (TransformMoleculeCommand.addMorgan.equals(commandx)) {
			throw new RuntimeException("addMorgan not yet implemented");
		} else {
			throw new RuntimeException("No routine implemented for: "+command);
		}
	}
	
	public static void addHydrogens(MoleculeTool moleculeTool) {
		moleculeTool.adjustHydrogenCountsToValency(HydrogenControl.USE_EXPLICIT_HYDROGENS);
		moleculeTool.removeHydrogenCountAttributes();
	}
	
	public static void addHydrogen2D(MoleculeTool moleculeTool) {
		moleculeTool.adjustHydrogenCountsToValency(HydrogenControl.USE_EXPLICIT_HYDROGENS);
		moleculeTool.removeHydrogenCountAttributes();
	}
	
	public static void addHydrogen3D(MoleculeTool moleculeTool) {
		moleculeTool.adjustHydrogenCountsToValency(HydrogenControl.USE_EXPLICIT_HYDROGENS);
		moleculeTool.addCalculated3DCoordinatesForExistingHydrogens();
		moleculeTool.removeHydrogenCountAttributes();
		LOG.info("REMOVED HYDROGENCOUNT ATTS");
	}
	
	private void add2DCoordinates(MoleculeTool moleculeTool) {
		MoleculeLayout moleculeLayout = 
			new MoleculeLayout(moleculeTool);
		moleculeLayout.create2DCoordinates();
	}

	public MimeType getInputType() {
		return CMLCommon.CML_TYPE;
	}
	
	public MimeType getOutputType() {
		return CMLCommon.CML_TYPE;
	}
	
	public String getDescription() {
		return "null";
	}

}
