package com.zsc.base.http;
public class HttpClientException extends Exception {
    private int statusCode = -1;
    private static final long serialVersionUID = -2623309261327598087L;
    public HttpClientException(String msg) {
        super(msg);
    }
    public HttpClientException(Exception cause) {
        super(cause);
    }

    public HttpClientException(String msg, int statusCode) {
        super(msg);
        this.statusCode = statusCode;
    }

    public HttpClientException(String msg, Exception cause) {
        super(msg, cause);
    }

    public HttpClientException(String msg, Exception cause, int statusCode) {
        super(msg, cause);
        this.statusCode = statusCode;

    }
    public int getStatusCode() {
        return this.statusCode;
    }
}
