package com.tech.atm.support.exception;

import com.tech.atm.domain.logic.BusinessMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.core.annotation.AnnotationUtils.findAnnotation;
import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class.getName());

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity defaultErrorHandler(Exception e) throws Exception {
        ResponseStatus responseStatus = findAnnotation(e.getClass(), ResponseStatus.class);
        if (responseStatus != null) {
            return new ResponseEntity(e.getMessage(), responseStatus.value());
        }
        LOGGER.error(e.getMessage(), e);
        return new ResponseEntity(e.getMessage(), INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = BusinessRuntimeException.class)
    public ResponseEntity defaultBusinessRuntimeExceptionHandler(BusinessRuntimeException exc) throws Exception {
        LOGGER.warn(exc.getMessage(), exc);
        StringBuilder sbMessage = new StringBuilder();

        if (exc.getSource() != null) {
            if (exc.getSource() instanceof BusinessMessage) {
                BusinessMessage bm = (BusinessMessage) exc.getSource();
                sbMessage.append(messageSource.getMessage(bm.getMessage(), bm.getParams(), null));
            }
        } else if (exc.getMessage() != null) {
            sbMessage.append(messageSource.getMessage(exc.getMessage(), null, null));
        }

        return new ResponseEntity(sbMessage.toString(), INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = BusinessException.class)
    public ResponseEntity defaultBusinessExceptionHandler(BusinessException exc) throws Exception {
        LOGGER.warn(exc.getMessage(), exc);
        StringBuilder sbMessage = new StringBuilder();

        if (exc.getBusinessMessage() != null) {
            BusinessMessage bm = exc.getBusinessMessage();
            sbMessage.append(messageSource.getMessage(bm.getMessage(), bm.getParams(), null));
        } else if (exc.getMessage() != null) {
            sbMessage.append(exc.getMessage());
        }

        return new ResponseEntity(sbMessage.toString(), INTERNAL_SERVER_ERROR);
    }
}
