package com.example.banksystem.ApiLogs;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

@Component
public class ApiLogFilter implements Filter {

    @Autowired
    private ApiLogRepository apiLogRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        if(!(servletRequest instanceof HttpServletRequest) || !(servletResponse instanceof HttpServletResponse)){
            filterChain.doFilter(servletRequest, servletResponse);
        }

        long start = System.currentTimeMillis();


        //wrap request and response in content caching wrapper class for read these fields multiple times
        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper((HttpServletRequest) servletRequest);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper((HttpServletResponse) servletResponse);
        filterChain.doFilter(requestWrapper, responseWrapper);

        long duration = System.currentTimeMillis() - start;

        //build logs
        ApiLogsEntity apiLogsEntity = new ApiLogsEntity();
        String url = requestWrapper.getRequestURI();
        if(requestWrapper.getQueryString() != null){
            url += "?" + requestWrapper.getQueryString();
        }
        apiLogsEntity.setUrl(url);


        apiLogsEntity.setMethod(requestWrapper.getMethod());
        apiLogsEntity.setCallAt(LocalDateTime.now());
        apiLogsEntity.setStatusCode(responseWrapper.getStatus());
        apiLogsEntity.setResponseTime(duration);

        apiLogsEntity.setRequestHeader(getRequestHeader(requestWrapper));
        apiLogsEntity.setResponseHeader(getResponseHeader(responseWrapper));


        apiLogsEntity.setRequestBody(getJsonBody(requestWrapper.getContentAsByteArray(),
                requestWrapper.getCharacterEncoding()));
        apiLogsEntity.setResponseBody(getJsonBody(responseWrapper.getContentAsByteArray(),
                responseWrapper.getCharacterEncoding()));

        apiLogRepository.save(apiLogsEntity);

        //copy the response body back to the client, otherwise response is empty
        responseWrapper.copyBodyToResponse();

    }

    //get request header
    private String getRequestHeader(HttpServletRequest request){
        Map<String , String> headersMap = new HashMap<>();
        for(String name : Collections.list(request.getHeaderNames())){
            String value = request.getHeader(name);
            if ("authorization".equalsIgnoreCase(name)) value = "REDACTED";
            headersMap.put(name, value);
        }
        try{
            return new ObjectMapper().writeValueAsString(headersMap);
        }catch (Exception e){
            return "{}";
        }
    }

    //get response header
    private String getResponseHeader(HttpServletResponse response){
        Map<String, String> map = new HashMap<>();
        for(String name : response.getHeaderNames()){
            String value = response.getHeader(name);
            map.put(name, value);
        }
        try{
            return new ObjectMapper().writeValueAsString(map);
        }catch (Exception e){
            return "{}";
        }
    }

    //get response body
    private String getJsonBody(byte[] buf, String encoding) {
        try {
            String body = buf.length > 0 ? new String(buf, encoding) : "";
            if (body.isBlank()) {
                return "{}"; // empty body -> empty JSON
            }
            String trimmed = body.trim();
            // body already in json
            if (trimmed.startsWith("{") || trimmed.startsWith("[")) {
                return trimmed;
            }

            return objectMapper.writeValueAsString(trimmed);
        } catch (Exception e) {
            return "{}";
        }
        }

}
