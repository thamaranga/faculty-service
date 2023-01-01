package com.hasithat.facultyservice.config;

import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

import static org.springframework.http.HttpStatus.Series.CLIENT_ERROR;
import static org.springframework.http.HttpStatus.Series.SERVER_ERROR;
/*
* When client API returns a http status code which is not equal to 200
* this class will be called.
*
* */
public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {
    @Override
    /*
    * HttpClientErrorException – in the case of HTTP status 4xx
    * HttpServerErrorException – in the case of HTTP status 5xx
    * */
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return (
                response.getStatusCode().series() == CLIENT_ERROR
                        || response.getStatusCode().series() == SERVER_ERROR);
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        System.out.println("Client API is not returning 200 "+response.getStatusCode());
    }
    }

