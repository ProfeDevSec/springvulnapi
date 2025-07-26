package com.banco.solicitudes.error;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Map<String, Object> handleException(HttpServletRequest req, Exception ex) {
        Map<String, Object> error = new HashMap<>();
        error.put("error", "Excepción capturada");
        error.put("path", req.getRequestURI());
        error.put("message", ex.getMessage());
        return error;
    }

    @ExceptionHandler(DataAccessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Map<String, Object> handleSqlError(HttpServletRequest req, DataAccessException ex) {
        Map<String, Object> error = new HashMap<>();
        error.put("error", "Error en acceso a datos");
        error.put("path", req.getRequestURI());
        error.put("message", ex.getRootCause() != null ? ex.getRootCause().getMessage() : ex.getMessage());
        return error;
    }
}
