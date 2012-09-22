
import java.io.IOException;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class Test {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		System.out.println("Starting ECD generator utility.");
		Document rootDimension = new Document();

		createRootDimension(rootDimension);

		XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
		xmlOutputter.output(rootDimension, System.out);
		System.out.println("Ending ECD generator utility.");
	}

	private static void createDimensionNode(Element dimension) {
		// TODO Auto-generated method stub
		Element dimensionNode = new Element("DIMENSION_NODE");
		dimension.addContent(dimensionNode);
	}

	private static void createParentDimension(Element rootElement) {
		// TODO Auto-generated method stub
		Element dimension = new Element("DIMENSION");
		dimension.setAttribute(new Attribute("NAME", "Wine Type"));
		dimension.setAttribute(new Attribute("SRC_TYPE", "INTERNAL"));
		Element dimensionId = new Element("DIMENSION_ID");
		dimensionId.setAttribute(new Attribute("ID", "6200"));
		dimension.addContent(dimensionId);
		createDimensionNode(dimension);
		rootElement.addContent(dimension);
	}

	private static void createRootDimension(Document rootDimension) {
		// TODO Auto-generated method stub
		Element rootElement = new Element("DIMENSIONS");
		rootElement.setAttribute(new Attribute("VERSION", "1.0.0"));
		createParentDimension(rootElement);
		rootDimension.setRootElement(rootElement);
	}

}
