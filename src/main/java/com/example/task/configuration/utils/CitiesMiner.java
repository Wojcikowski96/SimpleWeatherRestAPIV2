package com.example.task.configuration.utils;

import com.example.task.clients.model.City;
import com.example.task.repository.CitiesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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
@RequiredArgsConstructor
public class CitiesMiner{
@Autowired
CitiesRepository citiesRepository;
    @Bean
    public  void mineData() throws ParserConfigurationException, IOException, SAXException {
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

                City city = new City();
                city.setName(stringName);
                city.setLatitude(latitude);
                city.setLongitude(longitude);

                cities.add(city);
                citiesRepository.save(city);

            }
        }

    }
}
