package com.banco.solicitudes.util;

import org.apache.commons.text.StringEscapeUtils;

public class InputSanitizer {

    /**
     * Escapa HTML potencialmente peligroso
     */
    public static String sanitizeHtml(String input) {
        return input == null ? null : StringEscapeUtils.escapeHtml4(input.trim());
    }
}
