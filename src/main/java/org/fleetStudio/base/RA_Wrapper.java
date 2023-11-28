/**
 * Author: Sathish Kumaravelu
 */
package org.fleetStudio.base;

import POJO.AuthBodyPojo;
import POJO.AuthBody_TokenPojo;
import POJO.BookingResponsePojo;
import POJO.createBookingRequest;
import io.restassured.response.Response;
import utils.ApplicationProLoader;

import java.util.HashMap;
import java.util.Map;

public class RA_Wrapper extends RA_Implementation {
    private final String baseURI = ApplicationProLoader.loadProps().endpoint();

    /**
     * Sets the Content-Type header for API requests.
     *
     * @return A map containing the Content-Type header.
     */
    private Map<String, String> setContentTypeHeader() {
        Map<String, String> header = new HashMap<>();
        header.put("Content-Type", "application/json");
        return header;
    }

    /**
     * Sets the header with authentication token for API requests.
     *
     * @return A map containing the Content-Type and Cookie headers.
     */
    private Map<String, String> setHeaderWithCookies() {
        Map<String, String> header = new HashMap<>();
        header.put("Content-Type", "application/json");
        header.put("Cookie", "token=" + createAuthTokenPojo().getToken());
        return header;
    }

    /**
     * Sets query parameters for API requests.
     *
     * @param firstName The first name parameter.
     * @param lastName  The last name parameter.
     * @return A map containing the query parameters.
     */
    private Map<String, String> setQueryParam(String firstName, String lastName) {
        Map<String, String> queryParam = new HashMap<>();
        queryParam.put("firstname=", firstName);
        queryParam.put("lastname=", lastName);
        return queryParam;
    }

    /**
     * Sets the request body for authentication.
     *
     * @return An instance of AuthBodyPojo containing username and password.
     */
    private AuthBodyPojo setAuthRequestPojoBody() {
        AuthBodyPojo authRequestPojoBody = new AuthBodyPojo();
        authRequestPojoBody.setUsername("admin");
        authRequestPojoBody.setPassword("password123");
        return authRequestPojoBody;
    }

    /**
     * Creates an authentication token and returns the corresponding AuthBody_TokenPojo.
     *
     * @return An instance of AuthBody_TokenPojo containing the generated token.
     */
    public AuthBody_TokenPojo createAuthTokenPojo() {
        Response tokenResponse = postToAPI(baseURI, setContentTypeHeader(), "auth", setAuthRequestPojoBody());
        tokenResponse.then().statusCode(200);
        System.out.println("***********************");
        System.out.println("Token Successfully Generated");
        System.out.println("***********************");
        return tokenResponse.getBody().as(AuthBody_TokenPojo.class);
    }

    /**
     * Performs a ping check on the application.
     *
     * @return The response of the ping check.
     */
    public Response pingCheck() {
        return getToAPI(baseURI, "ping");
    }

    /**
     * Creates a booking using the provided request body.
     *
     * @param reqBody The request body for creating a booking.
     * @return The response of the create booking request.
     */
    public Response createBooking(Object reqBody) {
        return postToAPI(baseURI, setContentTypeHeader(),
                ApplicationProLoader.loadProps().bookingModuleBasePath(), reqBody);
    }

    /**
     * Creates a booking using the provided request body and returns the corresponding BookingResponsePojo.
     *
     * @param reqBody The request body for creating a booking.
     * @return An instance of BookingResponsePojo containing information about the created booking.
     */
    public BookingResponsePojo createBookingPojo(Object reqBody) {
        Response bookingResponse = postToAPI(baseURI, setContentTypeHeader(),
                ApplicationProLoader.loadProps().bookingModuleBasePath(), reqBody);
        bookingResponse.prettyPrint();
        bookingResponse.then().statusCode(200);
        return bookingResponse.getBody().as(BookingResponsePojo.class);
    }

    /**
     * Retrieves a booking as a createBookingRequest Pojo using the provided booking ID.
     *
     * @param bookingId The ID of the booking to retrieve.
     * @return An instance of createBookingRequest containing information about the retrieved booking.
     */
    public createBookingRequest getBookingPojo(String bookingId) {
        Response bookingResponse = getToAPI(baseURI,
                ApplicationProLoader.loadProps().bookingModuleBasePath() + "/" + bookingId);
        return bookingResponse.getBody().as(createBookingRequest.class);
    }

    /**
     * Retrieves a booking using the provided booking ID.
     *
     * @param bookingId The ID of the booking to retrieve.
     * @return The response of the get booking request.
     */
    public Response getBooking(String bookingId) {
        return getToAPI(baseURI,
                ApplicationProLoader.loadProps().bookingModuleBasePath() + "/" + bookingId);
    }

    /**
     * Retrieves a booking using the provided first name and last name parameters.
     *
     * @param firstName The first name parameter.
     * @param lastName  The last name parameter.
     * @return The response of the get booking request.
     */
    public Response getBookingByName(String firstName, String lastName) {
        return getToAPI(baseURI,
                ApplicationProLoader.loadProps().bookingModuleBasePath(), setQueryParam(firstName, lastName));
    }

    /**
     * Updates a booking using the provided request body and booking ID.
     *
     * @param reqBody   The request body for updating a booking.
     * @param bookingId The ID of the booking to update.
     * @return The response of the update booking request.
     */
    public Response updateBooking(Object reqBody, String bookingId) {
        return putToAPI(baseURI, setHeaderWithCookies(),
                ApplicationProLoader.loadProps().bookingModuleBasePath(), reqBody);
    }

    /**
     * Performs a partial update on a booking using the provided request body and booking ID.
     *
     * @param reqBody   The request body for partial updating a booking.
     * @param bookingId The ID of the booking to partially update.
     * @return The response of the partial update booking request.
     */
    public Response partialUpdateBooking(Object reqBody, String bookingId) {
        return patchToAPI(baseURI, setHeaderWithCookies(),
                ApplicationProLoader.loadProps().bookingModuleBasePath() + "/" + bookingId, reqBody);
    }

    /**
     * Deletes a booking using the provided booking ID.
     *
     * @param bookingId The ID of the booking to delete.
     * @return The response of the delete booking request.
     */
    public Response deleteBooking(String bookingId) {
        return deleteToAPI(baseURI, setHeaderWithCookies(),
                ApplicationProLoader.loadProps().bookingModuleBasePath() + "/" + bookingId);
    }
}
