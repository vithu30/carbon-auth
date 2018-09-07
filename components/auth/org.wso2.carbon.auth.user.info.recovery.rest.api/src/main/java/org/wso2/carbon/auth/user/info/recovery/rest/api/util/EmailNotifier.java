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

package org.wso2.carbon.auth.user.info.recovery.rest.api.util;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;

import static org.wso2.carbon.auth.user.info.recovery.rest.api.util.RecoveryRESTAPIConstants.NotificationConstants;

/**
 * This class is used to send email notification.
 */
public class EmailNotifier {
    /**
     * Send email with provided configurations
     *
     * @param properties SMTP configurations
     * @param username name of requesting user
     * @param password Email password
     * @param from Sender's email address
     * @param to Recipcient's email address
     */
    public void sendNotification(Properties properties, String username, String password, String from, String to,
                                 String confirmationCode)
            throws UserInfoRecoveryException {
        try {
            Authenticator auth = new SMTPAuthenticator(username, password);
            String content = loadEmailContent(username, confirmationCode);
            Session session = Session.getInstance(properties, auth);
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(NotificationConstants.EMAIL_SUBJECT);
            ByteArrayDataSource dataSource = new ByteArrayDataSource(content.getBytes(Charset.forName("UTF-8")),
                    NotificationConstants.TEXT_TYPE);
            DataHandler handler = new DataHandler(dataSource);
            message.setDataHandler(handler);
            Transport.send(message);
        } catch (IOException e) {
            throw new UserInfoRecoveryException("Error while retrieving email template", e);
        } catch (MessagingException e) {
            throw new UserInfoRecoveryException("Error while sending mail to user: " + username, e);
        }
    }

    private String loadEmailContent(String username, String confirmationCode) throws IOException {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = loader
                .getResourceAsStream(NotificationConstants.EMAIL_TEMPLATE_PATH);
        String[] keys = {"{username}", "{confirmationCode}"};
        String[] values = {username, confirmationCode};
        String content = IOUtils.toString(inputStream);
        return StringUtils.replaceEach(content, keys, values);
    }

    /**
     * Class to Authenticate User.
     */
    private static class SMTPAuthenticator extends Authenticator {
        private String username;
        private String password;
        private SMTPAuthenticator(String username, String password) {
            this.username = username;
            this.password = password;
        }
        public PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(username, password);
        }
    }
}
