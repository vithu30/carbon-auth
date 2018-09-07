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

/**
 * Constants related to user info recovery REST API implementation
 */
class RecoveryRESTAPIConstants {

    /**
     * Represents Constants related to email notification.
     */
    public static class NotificationConstants {
        public static final String EMAIL_TEMPLATE_PATH = "/resources/template/password_recover_email_template.txt";
        public static final String EMAIL_SUBJECT = "Password Reset Notification - WSO2 Carbon";
        public static final String TEXT_TYPE = "text/html";
    }

    /**
     * Represents Constants related to confirmation code generation.
     */
    public static class CodeGenerationConstants {
        public static final int CODE_LENGTH = 8;
        public static final String DIGITS = "1234567890";
        public static final String UPPER_CASE_ALPHABETS = "ABCDEFGHJKMNPQRSTUVWXYZ";
        public static final String LOWER_CASE_ALPHABETS = "abcdefghjkmnpqrstuvwxyz";

    }
}
