package org.wso2.carbon.auth.user.info.recovery.rest.api;

import org.wso2.carbon.auth.user.info.recovery.rest.api.*;
import org.wso2.carbon.auth.user.info.recovery.rest.api.dto.*;

import org.wso2.msf4j.formparam.FormDataParam;
import org.wso2.msf4j.formparam.FileInfo;
import org.wso2.msf4j.Request;

import org.wso2.carbon.auth.user.info.recovery.rest.api.dto.ErrorDTO;
import org.wso2.carbon.auth.user.info.recovery.rest.api.dto.InitiateRequestDTO;
import org.wso2.carbon.auth.user.info.recovery.rest.api.dto.NotifyRequestDTO;

import java.util.List;
import org.wso2.carbon.auth.user.info.recovery.rest.api.NotFoundException;

import java.io.InputStream;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

public abstract class PasswordResetApiService {
    public abstract Response passwordResetInitiatePost(InitiateRequestDTO initiateRequest
  ,Request request) throws NotFoundException;
    public abstract Response passwordResetNotifyPost(NotifyRequestDTO notifyRequest
  ,Request request) throws NotFoundException;
}
