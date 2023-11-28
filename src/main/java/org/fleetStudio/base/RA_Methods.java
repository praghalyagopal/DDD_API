package org.fleetStudio.base;

import io.restassured.response.Response;

import java.util.Map;

public interface RA_Methods {


    public Response postToAPI(String endpoint, Map<String, String> header, String basePath, Object body);

    public Response getToAPI(String endpoint, String basePath, Map<String, String> queryParam);

    public Response getToAPI(String endpoint, String basePath);

    public Response deleteToAPI(String endpoint, Map<String, String> header, String basePath);

    public Response putToAPI(String endpoint, Map<String, String> header, String basePath, Object body);

    public Response patchToAPI(String endpoint, Map<String, String> header, String basePath, Object body);
}
