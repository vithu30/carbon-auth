package org.wso2.carbon.auth.user.info.recovery.rest.api;


import io.swagger.annotations.ApiParam;

import org.wso2.carbon.auth.user.info.recovery.rest.api.dto.ErrorDTO;
import org.wso2.carbon.auth.user.info.recovery.rest.api.dto.InitiateRequestDTO;
import org.wso2.carbon.auth.user.info.recovery.rest.api.dto.NotifyRequestDTO;
import org.wso2.carbon.auth.user.info.recovery.rest.api.factories.PasswordResetApiServiceFactory;

import org.wso2.msf4j.Microservice;
import org.wso2.msf4j.Request;
import org.wso2.msf4j.formparam.FileInfo;
import org.wso2.msf4j.formparam.FormDataParam;
import org.osgi.service.component.annotations.Component;

import java.io.InputStream;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HEAD;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

@Component(
    name = "org.wso2.carbon.auth.user.info.recovery.rest.api.PasswordResetApi",
    service = Microservice.class,
    immediate = true
)
@Path("/api/identity/recovery/v1.[\\d]+/password-reset")
@Consumes({ "application/json" })
@Produces({ "application/json" })
@ApplicationPath("/password-reset")
@io.swagger.annotations.Api(description = "the password-reset API")
public class PasswordResetApi implements Microservice  {
   private final PasswordResetApiService delegate = PasswordResetApiServiceFactory.getPasswordResetApi();

    @OPTIONS
    @POST
    @Path("/initiate")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Initiate password reset", notes = "Initiate password reset by generating a confirmation code ", response = void.class, tags={ "Password Reset Initiate", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successfully sent", response = void.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Bad Request", response = void.class),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Server Error", response = void.class) })
    public Response passwordResetInitiatePost(@ApiParam(value = "Request to initiate password reset. " ) InitiateRequestDTO initiateRequest
 ,@Context Request request)
    throws NotFoundException {
        
        return delegate.passwordResetInitiatePost(initiateRequest,request);
    }
    @OPTIONS
    @POST
    @Path("/notify")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Notify user via email", notes = "Notify password reset link to user via an email ", response = void.class, tags={ "Password Reset Notify", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successfully sent", response = void.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Bad Request", response = void.class),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Server Error", response = void.class) })
    public Response passwordResetNotifyPost(@ApiParam(value = "Request to notify password reset. " ) NotifyRequestDTO notifyRequest
 ,@Context Request request)
    throws NotFoundException {
        
        return delegate.passwordResetNotifyPost(notifyRequest,request);
    }
}
