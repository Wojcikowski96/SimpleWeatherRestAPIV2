package com.example.task.filter;

import org.springframework.util.StreamUtils;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class RequestWrapper extends HttpServletRequestWrapper {
    private byte[] httpInData;
    public RequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        this.httpInData = StreamUtils.copyToByteArray(request.getInputStream());

    }
    @Override
    public ServletInputStream getInputStream(){
        return new ServletInputStreamWrapper(this.httpInData);

    }
}
