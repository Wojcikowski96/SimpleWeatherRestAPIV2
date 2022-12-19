package com.example.task.filter;

import com.example.task.clients.model.RequestFlowData;
import com.example.task.exception.CorrelationIdEmptyException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.xml.parsers.*;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.UUID;


import static org.hibernate.tool.schema.SchemaToolingLogging.LOGGER;

@Component
public class CorrelationGetter implements Filter {

    public String extractCorrelationID(String body) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilder builder = getDocumentBuilder();
        Document document = builder.parse(new InputSource(new StringReader(body.toString())));
        document.getDocumentElement().normalize();
        System.out.println(document.toString());

        String correlationId = document.getElementsByTagName("correlationId").item(0).getTextContent();
        System.out.println("correlationId: "+correlationId);

        return correlationId;


    }

    public static boolean isXMLValid(String string) {
        try {
            SAXParserFactory.newInstance().newSAXParser().getXMLReader().parse(new InputSource(new StringReader(string)));
            return true;
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            return false;
        }
    }
    public static DocumentBuilder getDocumentBuilder() throws ParserConfigurationException {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        builderFactory.setNamespaceAware(true);
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        return builder;
    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        final HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        RequestWrapper wrapper = new RequestWrapper(httpServletRequest);

        byte[] body = StreamUtils.copyToByteArray(wrapper.getInputStream());
        String s = new String(body, StandardCharsets.UTF_8);

        if(isXMLValid(s)){
            String currentCorrId = null;
            try {
                currentCorrId = extractCorrelationID(s);
            } catch (ParserConfigurationException e) {
                throw new RuntimeException(e);
            } catch (SAXException e) {
            }
            RequestFlowData.setId(currentCorrId);
        }else{
            String correlationId = ((HttpServletRequest) servletRequest).getParameter("correlationId");
            RequestFlowData.setId(correlationId);
        }




//        if (!currentRequestIsAsyncDispatcher(httpServletRequest)) {
//            if (currentCorrId == null) {
//                currentCorrId = UUID.randomUUID().toString();
//                LOGGER.info("No correlationId found in Header. Generated : " + currentCorrId);
//            } else {
//                LOGGER.info("Found correlationId in Header : " + currentCorrId);
//            }
//
//            RequestFlowData.setId(currentCorrId);
//        }


      filterChain.doFilter(wrapper, servletResponse);

    }
    private boolean currentRequestIsAsyncDispatcher(HttpServletRequest httpServletRequest) {
        return httpServletRequest.getDispatcherType().equals(DispatcherType.ASYNC);}
}
