package com.example.task.filter;

import org.apache.catalina.connector.Request;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.soap.SoapBody;
import org.springframework.ws.soap.SoapMessage;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.ws.handler.Handler;
@Component
public class SoapHandler implements EndpointInterceptor {

    @Override
    public boolean handleRequest(MessageContext messageContext, Object o) throws Exception {
        SoapMessage message = (SoapMessage) messageContext.getRequest();
        SoapBody soapBody = message.getSoapBody();
        Source bodySource = soapBody.getPayloadSource();
        DOMSource bodyDomSource = (DOMSource) bodySource;

        JAXBContext context = JAXBContext.newInstance(Request.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        Request request = (Request) unmarshaller.unmarshal(bodyDomSource);
        System.out.println("request w nowej klasie");
        System.out.println(request);
        return true;
    }

    @Override
    public boolean handleResponse(MessageContext messageContext, Object o) throws Exception {
        return false;
    }

    @Override
    public boolean handleFault(MessageContext messageContext, Object o) throws Exception {
        return false;
    }

    @Override
    public void afterCompletion(MessageContext messageContext, Object o, Exception e) throws Exception {

    }
}
