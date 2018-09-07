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

import java.security.SecureRandom;
import java.util.Random;

import static org.wso2.carbon.auth.user.info.recovery.rest.api.util.RecoveryRESTAPIConstants.CodeGenerationConstants;

/**
 * This class is used to generate a random confirmation code
 */
public class RandomCodeGenerator {
    private static final Random RANDOM = new SecureRandom();

    /**
     * Generate a confirmation code with length of 8
     *
     * @return char[] confirmationCode
     */
    public char[] generateCode() {
        String characters = "23456789abcdefghjkmnpqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ";
        int mandatoryCharactersCount = 3;

        StringBuilder confirmationCode = new StringBuilder();
        int index;
        for (int i = 0; i < CodeGenerationConstants.CODE_LENGTH - mandatoryCharactersCount; i++) {
            index = RANDOM.nextInt(characters.length());
            confirmationCode.append(characters.charAt(index));
        }

        index = RANDOM.nextInt(CodeGenerationConstants.DIGITS.length());
        confirmationCode.append(CodeGenerationConstants.DIGITS.charAt(index));

        index = RANDOM.nextInt(CodeGenerationConstants.UPPER_CASE_ALPHABETS.length());
        confirmationCode.append(CodeGenerationConstants.UPPER_CASE_ALPHABETS.charAt(index));

        index = RANDOM.nextInt(CodeGenerationConstants.LOWER_CASE_ALPHABETS.length());
        confirmationCode.append(CodeGenerationConstants.LOWER_CASE_ALPHABETS.charAt(index));

        char[] password = new char[confirmationCode.length()];
        confirmationCode.getChars(0, confirmationCode.length(), password, 0);
        return password;
    }

}
