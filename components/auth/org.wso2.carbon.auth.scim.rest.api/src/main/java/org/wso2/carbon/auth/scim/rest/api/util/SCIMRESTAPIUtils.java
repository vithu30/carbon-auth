/*
 * Copyright (c) 2017, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.wso2.carbon.auth.scim.rest.api.util;

import com.google.gson.Gson;
import org.wso2.carbon.auth.scim.exception.AuthUserManagementException;
import org.wso2.carbon.auth.scim.rest.api.SCIMRESTAPIConstants;
import org.wso2.carbon.auth.user.mgt.UserStoreException;
import org.wso2.carbon.auth.user.mgt.UserStoreManager;
import org.wso2.carbon.auth.user.mgt.UserStoreManagerFactory;
import org.wso2.charon3.core.encoder.JSONEncoder;
import org.wso2.charon3.core.exceptions.AbstractCharonException;
import org.wso2.charon3.core.exceptions.CharonException;
import org.wso2.charon3.core.exceptions.UnauthorizedException;
import org.wso2.charon3.core.protocol.SCIMResponse;
import org.wso2.msf4j.Request;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.Base64;
import java.util.Map;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

/**
 * Utility functions for SCIM REST API
 * 
 */
public class SCIMRESTAPIUtils {

    private SCIMRESTAPIUtils() {

    }

    /* Build the javax-rs response from SCIM response
     *
      *@param scimResponse
     * @return Response
     */
    public static Response buildResponse(SCIMResponse scimResponse) throws AuthUserManagementException {
        Response.ResponseBuilder responseBuilder = Response.status(scimResponse.getResponseStatus());
        Map<String, String> httpHeaders = scimResponse.getHeaderParamMap();
        if (httpHeaders != null && !httpHeaders.isEmpty()) {
            for (Map.Entry<String, String> entry : httpHeaders.entrySet()) {
                //skip the location header as it is supported by the framework to set the actual hostnames
                if (HttpHeaders.LOCATION.equals(entry.getKey())) {
                    try {
                        responseBuilder.location(new URI(entry.getValue()));
                    } catch (URISyntaxException e) {
                        throw new AuthUserManagementException(
                                "Error while setting Location URI for the resource in response", e);
                    }
                } else {
                    responseBuilder.header(entry.getKey(), entry.getValue());
                }
            }
        }
        if (scimResponse.getResponseMessage() != null) {
            responseBuilder.entity(scimResponse.getResponseMessage());
        }
        responseBuilder.status(scimResponse.getResponseStatus());
        return responseBuilder.build();
    }

    /**
     * Serialize the provided DTO and creates a JSON string
     * 
     * @param dto DTO object
     * @return serialized JSON string from DTO
     */
    public static String getSerializedJsonStringFromBody(Object dto) {
        return new Gson().toJson(dto);
    }

    /**
     * Authenticate the user an from the request and returns the username
     *
     * @param request Request object
     * @return username if user is authenticated
     * @throws CharonException    if failed to authenticate the user
     * @throws UnauthorizedException if user is un authenticated
     */
    public static String getAuthenticatedUserName(Request request) throws CharonException, UnauthorizedException {
        final String authorization = request.getHeader(SCIMRESTAPIConstants.HEADER_AUTHORIZATION);
        boolean authenticated = false;
        String[] values = null;
        if (authorization != null && authorization.startsWith(SCIMRESTAPIConstants.HEADER_AUTHORIZATION_BASIC)) {
            // Authorization: Basic base64credentials
            String base64Credentials = authorization.substring(SCIMRESTAPIConstants.HEADER_AUTHORIZATION_BASIC.length())
                    .trim();
            String credentials = new String(Base64.getDecoder().decode(base64Credentials),
                    Charset.forName(SCIMRESTAPIConstants.CHARSET_UTF8));
            // credentials = username:password
            values = credentials.split(":", 2);
            if (values.length == 2) {
                //authenticate the user
                UserStoreManager userStoreManager = null;
                try {
                    userStoreManager = UserStoreManagerFactory.getUserStoreManager();
                    authenticated = userStoreManager.doAuthenticate(values[0], values[1]);
                } catch (UserStoreException e) {
                    throw new CharonException("Error while authenticating user", e);
                }
            }
        }

        if (authenticated) {
            return values[0];
        } else {
            throw new UnauthorizedException("User not authenticated");
        }
    }

    /**
     * Get error response from Charon exception
     * 
     * @param e CharonException
     * @return error response from Charon exception
     */
    public static Response getResponseFromCharonException(AbstractCharonException e) {
        JSONEncoder encoder = new JSONEncoder();
        String responseStr = encoder.encodeSCIMException(e);
        return Response.status(e.getStatus()).entity(responseStr).build();
    }

    /**
     * Get internal SCIM error
     */
    @SuppressWarnings("ThrowableNotThrown") 
    public static Response getSCIMInternalErrorResponse() {
        JSONEncoder encoder = new JSONEncoder();
        CharonException exception = new CharonException("Internal Error");
        String responseStr = encoder.encodeSCIMException(exception);
        return Response.status(exception.getStatus()).entity(responseStr).build();
    }
}
