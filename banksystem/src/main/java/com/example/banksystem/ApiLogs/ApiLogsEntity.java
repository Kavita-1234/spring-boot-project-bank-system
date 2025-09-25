package com.example.banksystem.ApiLogs;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "api_logs")
public class ApiLogsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String url;
    private String method;
    @Column(name = "call_at")
    private LocalDateTime callAt;
    @Column(name = "status_code")
    private int statusCode;

    @Column(name = "response_time")
    private long responseTime;

    @Lob
    @Column(name = "response_header", columnDefinition = "JSON")
    private String responseHeader;

    @Lob
    @Column(name = "response_body", columnDefinition = "JSON")
    private  String responseBody;

    @Lob
    @Column(name = "request_body", columnDefinition = "JSON")
    private String requestBody;

    @Lob
    @Column(name = "request_header", columnDefinition = "JSON")
    private String requestHeader;


    public ApiLogsEntity() {
    }

    public ApiLogsEntity(int id, String url, String method, LocalDateTime callAt, int statusCode, String responseHeader, long responseTime, String requestBody, String responseBody, String requestHeader) {
        this.id = id;
        this.url = url;
        this.method = method;
        this.callAt = callAt;
        this.statusCode = statusCode;
        this.responseHeader = responseHeader;
        this.responseTime = responseTime;
        this.requestBody = requestBody;
        this.responseBody = responseBody;
        this.requestHeader = requestHeader;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public LocalDateTime getCallAt() {
        return callAt;
    }

    public void setCallAt(LocalDateTime callAt) {
        this.callAt = callAt;
    }

    public long getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(long responseTime) {
        this.responseTime = responseTime;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getResponseHeader() {
        return responseHeader;
    }

    public void setResponseHeader(String responseHeader) {
        this.responseHeader = responseHeader;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }

    public String getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }

    public String getRequestHeader() {
        return requestHeader;
    }

    public void setRequestHeader(String requestHeader) {
        this.requestHeader = requestHeader;
    }
}
