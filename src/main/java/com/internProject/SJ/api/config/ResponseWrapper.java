package com.internProject.SJ.api.config;

import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

public class ResponseWrapper extends ContentCachingResponseWrapper {
    public ResponseWrapper(HttpServletResponse response) {
        super(response);
    }
}
