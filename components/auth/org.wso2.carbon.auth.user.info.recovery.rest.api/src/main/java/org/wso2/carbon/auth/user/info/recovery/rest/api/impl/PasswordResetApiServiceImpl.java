/*
 * Copyright (c) 2018, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * you may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.carbon.auth.user.info.recovery.rest.api.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wso2.carbon.auth.user.info.recovery.rest.api.NotFoundException;
import org.wso2.carbon.auth.user.info.recovery.rest.api.PasswordResetApiService;
import org.wso2.carbon.auth.user.info.recovery.rest.api.dto.InitiateRequestDTO;
import org.wso2.carbon.auth.user.info.recovery.rest.api.dto.NotifyRequestDTO;
import org.wso2.carbon.auth.user.info.recovery.rest.api.util.EmailNotifier;
import org.wso2.carbon.auth.user.info.recovery.rest.api.util.RandomCodeGenerator;
import org.wso2.carbon.auth.user.info.recovery.rest.api.util.UserInfoRecoveryException;
import org.wso2.msf4j.Request;

import java.util.Properties;
import javax.ws.rs.core.Response;

/**
 * REST API implementation class for Password Reset APIs
 */
public class PasswordResetApiServiceImpl extends PasswordResetApiService {
    private static final Logger log = LoggerFactory.getLogger(PasswordResetApiServiceImpl.class);

    private static final String MAIL_SMTP_AUTH = "mail.smtp.auth";
    private static final String MAIL_SMTP_START_TLS_ENABLE = "mail.smtp.starttls.enable";
    private static final String MAIL_SMTP_HOST = "mail.smtp.host";
    private static final String MAIL_SMTP_PORT = "mail.smtp.port";
    private static final String MAIL_SMTP_SSL_TRUST = "mail.smtp.ssl.trust";
    private static final String SUCCESS_MESSAGE = "Successfully sent notification";

    @Override
    public Response passwordResetInitiatePost(InitiateRequestDTO initiateRequest, Request request)
            throws NotFoundException {
        RandomCodeGenerator codeGenerator = new RandomCodeGenerator();
        char[] confirmationCode = codeGenerator.generateCode();

        return Response.status(Response.Status.OK).entity(String.copyValueOf(confirmationCode)).build();
    }

    @Override
    public Response passwordResetNotifyPost(NotifyRequestDTO notifyRequest, Request request) throws NotFoundException {
        String host = notifyRequest.getHost();
        String port = notifyRequest.getPort();
        Properties properties = new Properties();
        properties.setProperty(MAIL_SMTP_AUTH, "true");
        properties.put(MAIL_SMTP_START_TLS_ENABLE, "true");
        properties.put(MAIL_SMTP_HOST, host);
        properties.put(MAIL_SMTP_PORT, port);
        properties.put(MAIL_SMTP_SSL_TRUST, "smtp.gmail.com");
        try {
            EmailNotifier emailNotifier = new EmailNotifier();
            emailNotifier.sendNotification(properties, notifyRequest.getEmailUsername(),
                    notifyRequest.getEmailPassword(), notifyRequest.getFrom(), notifyRequest.getTo(),
                    notifyRequest.getConfirmationCode());
            return Response.status(Response.Status.OK).entity(SUCCESS_MESSAGE).build();
        } catch (UserInfoRecoveryException e) {
            log.error("Error while sending recovery notification to : " + notifyRequest.getUsername());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e).build();
        }
    }
}
