package com.pravin.xml.ecd.writer;

import java.io.IOException;
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
	 * 
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

		Element dimensionDVAL = new Element("DVAL");
		dimensionDVAL.setAttribute(new Attribute("TYPE", "EXACT"));
		Element dimensionDVALId = new Element("DVAL_ID");
		dimensionDVALId.setAttribute(new Attribute("ID", "6200"));

		Element dimensionSYN = new Element("SYN");
		dimensionSYN.setAttribute(new Attribute("CLASSIFY", "FALSE"));
		dimensionSYN.setAttribute(new Attribute("DISPLAY", "TRUE"));
		dimensionSYN.setAttribute(new Attribute("SEARCH", "TRUE"));
		dimensionSYN.addContent("Wine Type");

		dimensionDVAL.addContent(dimensionDVALId);
		dimensionDVAL.addContent(dimensionSYN);
		dimensionNode.addContent(dimensionDVAL);
		dimension.addContent(dimensionId);
		dimension.addContent(dimensionNode);
		rootElement.addContent(dimension);
	}

	private static void createDimension(DimensionBean dimensionBean, Document document) {

		if (dimensionBean.getParentDimensionID() == null) {
			Element dimensionNode = new Element("DIMENSION_NODE");

			Element dimensionDVAL = new Element("DVAL");
			dimensionDVAL.setAttribute(new Attribute("TYPE", "EXACT"));
			Element dimensionDVALId = new Element("DVAL_ID");
			dimensionDVALId.setAttribute(new Attribute("ID", dimensionBean.getDimensionID()));

			Element dimensionSYN = new Element("SYN");
			dimensionSYN.setAttribute(new Attribute("CLASSIFY", "TRUE"));
			dimensionSYN.setAttribute(new Attribute("DISPLAY", "TRUE"));
			dimensionSYN.setAttribute(new Attribute("SEARCH", "TRUE"));
			dimensionSYN.addContent(dimensionBean.getDimensionName());

			dimensionDVAL.addContent(dimensionDVALId);
			dimensionDVAL.addContent(dimensionSYN);
			dimensionNode.addContent(dimensionDVAL);

			List<Element> elements = document.getRootElement().getChildren();
			for (Element element2 : elements) {
				if (element2.getAttribute("NAME").getValue().equals("Wine Type")) {
					element2.getChildren().get(1).addContent(dimensionNode);
				}
			}
		} else {
			Document doc = document.clone();
//			Element element = new Element("DIMENSION");
//			element.setAttribute("NAME", dimensionBean.getDimensionName());
//			Element dimensionId = new Element("DIMENSION_ID");
//			dimensionId.setAttribute(new Attribute("ID", dimensionBean.getDimensionID()));
//			element.addContent(dimensionId);


			Element dimensionNode = new Element("DIMENSION_NODE");

			Element dimensionDVAL = new Element("DVAL");
			dimensionDVAL.setAttribute(new Attribute("TYPE", "EXACT"));
			Element dimensionDVALId = new Element("DVAL_ID");
			dimensionDVALId.setAttribute(new Attribute("ID", dimensionBean.getDimensionID()));
			
			Element dimensionSYN = new Element("SYN");
			dimensionSYN.setAttribute(new Attribute("CLASSIFY", "TRUE"));
			dimensionSYN.setAttribute(new Attribute("DISPLAY", "TRUE"));
			dimensionSYN.setAttribute(new Attribute("SEARCH", "TRUE"));
			dimensionSYN.addContent(dimensionBean.getDimensionName());

			dimensionDVAL.addContent(dimensionDVALId);			
			dimensionDVAL.addContent(dimensionSYN);
			dimensionNode.addContent(dimensionDVAL);

			
			List<Element> elements = doc.getRootElement().getChildren();
			for (int i = 0; i < elements.size(); i++) {
				Element element2 = elements.get(i);
				List<Element> elements1 = element2.getChildren();
				for (int j = 0; j < elements1.size(); j++) {
					Element element3 = elements1.get(j);
					List<Element> elements2 = element3.getChildren();
					for (int k = 0; k < elements2.size(); k++) {
						Element element4 = elements2.get(k);
						List<Element> elements41 = element4.getChildren();
						for (int l = 0; l < elements41.size(); l++) {
							Element element41_1 = elements41.get(l);
							List<Element> elements41_2 = element41_1.getChildren();
							for (int m = 0; m < elements41_2.size(); m++) {
								Element elements41_2_1 = elements41_2.get(m);	
							if (elements41_2_1.getAttribute("ID") != null && elements41_2_1.getAttribute("ID").getValue().equals(dimensionBean.getParentDimensionID())) {
								document.getRootElement().getChildren().get(i).getChildren().get(j).getChildren().get(k).addContent(dimensionNode);
							}
							}
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
		DimensionBean bean5 = new DimensionBean("Blanc Blancs", "6202", "6204");
		DimensionBean bean6 = new DimensionBean("Blanc Noirs", "8011", "6204");
//		DimensionBean bean7 = new DimensionBean("Red flag", "8071", "8021");
		DimensionBean bean8 = new DimensionBean("Red flag", "8091", "8071");

		dimensionBeans.add(bean1);
		dimensionBeans.add(bean2);
		dimensionBeans.add(bean3);
		dimensionBeans.add(bean4);
		dimensionBeans.add(bean5);
		dimensionBeans.add(bean6);
//		dimensionBeans.add(bean7);
		dimensionBeans.add(bean8);
	}

	private static void createRootDimension(Document rootDimension) {
		// TODO Auto-generated method stub
		Element rootElement = new Element("DIMENSIONS");
		rootElement.setAttribute(new Attribute("VERSION", "1.0.0"));
		rootDimension.setRootElement(rootElement);
	}

}
