package com.example.task.configuration;

import com.example.task.clients.model.City;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class CitiesMiner{
    public static List<City> mineData() throws ParserConfigurationException, IOException, SAXException {
        List<City> cities = new ArrayList<>();
        File file = new File(
                "Cities.xml");
        DocumentBuilderFactory dbf
                = DocumentBuilderFactory.newInstance();

        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(file);
        doc.getDocumentElement().normalize();

        NodeList nodeList
                = doc.getElementsByTagName("city");

        for (int i = 0; i < nodeList.getLength(); ++i) {
            Node node = nodeList.item(i);
            if (node.getNodeType()
                    == Node.ELEMENT_NODE) {
                Element tElement = (Element) node;

                String stringIdentity = tElement.getElementsByTagName("id").item(0).getTextContent();
                String stringName = tElement.getElementsByTagName("name").item(0).getTextContent();
                String stringLongitude = tElement.getElementsByTagName("longitude").item(0).getTextContent();
                String stringLatitude = tElement.getElementsByTagName("latitude").item(0).getTextContent();


                double longitude = Double.parseDouble(stringLongitude);
                double latitude = Double.parseDouble(stringLatitude);
                long identity = Long.parseLong(stringIdentity);

                cities.add(new City(stringName, latitude, longitude, identity));

            }
        }
        return cities;
    }
}
