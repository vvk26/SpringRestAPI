package com.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class FetchProperties {

	public static String getDBProperty(String strName) {
		Document doc;
		String str = "";
		FileInputStream file = null;

		DocumentBuilderFactory dbf = null;
		DocumentBuilder parser = null;

		try {
			file = new FileInputStream("/tomcat/config/DB_PROPERTIES.xml");
			InputSource source = new InputSource(file);

			dbf = DocumentBuilderFactory.newInstance();
			parser = dbf.newDocumentBuilder();
			doc = parser.parse(source);

			// Only one element will be returned.
			NodeList list = doc.getElementsByTagName(strName);
			Node node = list.item(0);

			// If Node not found in XML
			if (node == null)
				return null;

			NodeList children = node.getChildNodes();
			node = children.item(0);

			// If Node Value is not specified
			if (node == null)
				return null;

			str = node.getNodeValue();
			str = str.trim();
			parser = null;
			dbf = null;
			file.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				file.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			parser = null;
			dbf = null;
			file = null;
		}
		return str;
	}

	public static String tomcatPathFinder(){

		String path1 = new File("").getAbsolutePath();
		String path2 = new File(path1).getParent();
		System.out.println(path1+ " "+ path2);
		return path2;
	}

}
