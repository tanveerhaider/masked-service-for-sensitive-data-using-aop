package com.simplejava.util.masking;

import java.util.Objects;

/**
 * Description :
 * User: Tanveer Haider
 * Date: 5/6/2023
 * Time: 12:15 AM
 */
public class MaskData {

    private static final int TOTAL_CHARS_TO_RETAIN=4;
    public static String maskSensitiveData(String sensitiveData) {
        if (Objects.nonNull(sensitiveData) && !sensitiveData.isBlank()) {
            int totalChars = sensitiveData.length();
            int maskedLength= totalChars-TOTAL_CHARS_TO_RETAIN;
            if(maskedLength>0)
                return findMaskedString(maskedLength) + sensitiveData.substring(maskedLength,totalChars);
            else
                return findMaskedString(maskedLength);

        }
        return sensitiveData;
    }

    private static String findMaskedString(int count){
        return "*".repeat(count);
    }

    /**
     * Mask data as per regex Logic
     * @param sensitiveData
     * @return
     */
    public static String mask(String sensitiveData){
        return sensitiveData.replaceAll(".(?=.{4})", "x");
    }

}
