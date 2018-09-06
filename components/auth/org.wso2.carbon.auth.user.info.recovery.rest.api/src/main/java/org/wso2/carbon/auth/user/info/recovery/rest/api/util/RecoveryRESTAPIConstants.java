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
        public static final int CODE_LENGTH = 32;
        public static final String DIGITS = "1234567890";
        public static final String ALPHABETS = "ABCDEFGHJKMNPQRSTUVWXYZ";

    }
}
