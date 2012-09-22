package com.pravin.xml.ecd.writer;

import java.io.IOException;
import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import com.pravin.bean.DimensionBean;

public class ECDCreator {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		Document rootDimension = new Document();

		List<DimensionBean> dimensionBeans = new ArrayList<DimensionBean>();
		populateDimensionBean(dimensionBeans);

		createRootDimension(rootDimension);
		createParentDimension(rootDimension);

		for (DimensionBean dimensionBean : dimensionBeans) {
			createDimension(dimensionBean, rootDimension);
		}

		XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
		xmlOutputter.output(rootDimension, System.out);
	}

	private static void createParentDimension(Document rootDimension) {

		Element rootElement = rootDimension.getRootElement();
		Element dimension = new Element("DIMENSION");
		dimension.setAttribute(new Attribute("NAME", "Wine Type"));
		dimension.setAttribute(new Attribute("SRC_TYPE", "INTERNAL"));
		Element dimensionId = new Element("DIMENSION_ID");
		dimensionId.setAttribute(new Attribute("ID", "6200"));
		Element dimensionNode = new Element("DIMENSION_NODE");
		dimension.addContent(dimensionId);
		dimension.addContent(dimensionNode);
		rootElement.addContent(dimension);
	}

	private static void createDimension(DimensionBean dimensionBean,
			Document document) {

		if (dimensionBean.getParentDimensionID() == null) {
			Element element = new Element("DIMENSION");
			element.setAttribute("NAME", dimensionBean.getDimensionName());
			element.setAttribute(new Attribute("SRC_TYPE", "INTERNAL"));
			Element dimensionId = new Element("DIMENSION_ID");
			dimensionId.setAttribute(new Attribute("ID", dimensionBean
					.getDimensionID()));
			element.addContent(dimensionId);
			List<Element> elements = document.getRootElement().getChildren();
			for (Element element2 : elements) {
				if (element2.getAttribute("NAME").getValue().equals("Wine Type")) {
					element2.getChildren().get(1).addContent(element);
				}
			}
		} else {
			Document doc = document.clone();
			Element element = new Element("DIMENSION");
			element.setAttribute("NAME", dimensionBean.getDimensionName());
			element.setAttribute(new Attribute("SRC_TYPE", "INTERNAL"));
			Element dimensionId = new Element("DIMENSION_ID");
			dimensionId.setAttribute(new Attribute("ID", dimensionBean.getDimensionID()));
			element.addContent(dimensionId);

			List<Element> elements = doc.getRootElement().getChildren();
			for (int i = 0; i < elements.size(); i++) {
				Element element2 = elements.get(i);
				List<Element> elements1 = element2.getChildren();
				for (int j = 0; j < elements1.size(); j++) {
					Element element3 = elements1.get(j);
					List<Element> elements2 = element3.getChildren();
					for (int k = 0; k < elements2.size(); k++) {
						Element element4 = elements2.get(k);
						if (element4.getAttribute("ID") != null
								&& element4.getAttribute("ID").getValue().equals(dimensionBean.getParentDimensionID())) {
							document.getRootElement().getChildren().get(i).getChildren().get(j).addContent(element);
						}

					}
				}
			}
		}
	}

	private static void populateDimensionBean(List<DimensionBean> dimensionBeans) {

		DimensionBean bean1 = new DimensionBean("Red", "8021", null);
		DimensionBean bean2 = new DimensionBean("Beaujolais", "8023", "8021");
		DimensionBean bean3 = new DimensionBean("Bordeaux", "8022", "8021");

		DimensionBean bean4 = new DimensionBean("Sparkling", "6204", null);
		DimensionBean bean5 = new DimensionBean("Blanc de Blancs", "6202",
				"6204");
		DimensionBean bean6 = new DimensionBean("Blanc de Noirs", "8011",
				"6204");

		dimensionBeans.add(bean1);
		dimensionBeans.add(bean2);
		dimensionBeans.add(bean3);
		dimensionBeans.add(bean4);
		dimensionBeans.add(bean5);
		dimensionBeans.add(bean6);
	}

	private static void createRootDimension(Document rootDimension) {
		// TODO Auto-generated method stub
		Element rootElement = new Element("DIMENSIONS");
		rootElement.setAttribute(new Attribute("VERSION", "1.0.0"));
		rootDimension.setRootElement(rootElement);
	}

}
