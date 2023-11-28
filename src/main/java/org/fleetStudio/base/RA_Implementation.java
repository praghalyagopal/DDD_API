package org.fleetStudio.base;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.Map;

public class RA_Implementation implements RA_Methods {
    @Override
    public Response postToAPI(String endpoint, Map<String, String> header, String basePath, Object body) {
        return RestAssured.given()
                .baseUri(endpoint)
                .headers(header)
                .body(body)
                .post(basePath)
                .then()
                .log()
                .all()
                .and()
                .extract().response();
    }

    @Override
    public Response getToAPI(String endpoint, String basePath, Map<String, String> queryParam) {
        return RestAssured.given()
                .baseUri(endpoint)
                .queryParams(queryParam)
                .get( basePath)
                .then()
                .log()
                .all()
                .and()
                .extract().response();


    }

    @Override
    public Response getToAPI(String endpoint, String basePath) {
        return RestAssured.given()
                .baseUri(endpoint)
                .get(basePath)
                .then()
                .log()
                .all()
                .and()
                .extract().response();
    }


    @Override
    public Response deleteToAPI(String endpoint, Map<String, String> header, String basePath) {
        return RestAssured.given()
                .baseUri(endpoint)
                .headers(header)
                .delete(basePath)
                .then()
                .log()
                .all()
                .and()
                .extract().response();
    }

    @Override
    public Response putToAPI(String endpoint, Map<String, String> header, String basePath, Object body) {
        return RestAssured.given()
                .baseUri(endpoint)
                .headers(header)
                .body(body)
                .put( basePath)
                .then()
                .log()
                .all()
                .and()
                .extract().response();
    }

    @Override
    public Response patchToAPI(String endpoint, Map<String, String> header, String basePath, Object body) {
        return RestAssured.given()
                .baseUri(endpoint)
                .headers(header)
                .body(body)
                .patch( basePath)
                .then()
                .log()
                .all()
                .and()
                .extract().response();

    }
}
