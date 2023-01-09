package com.api.mrbudget.userservice.exception;

import com.api.mrbudget.userservice.config.PropertiesConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.Optional;

/**
 * Author: Daniel Lim
 *
 * Custom Exception handler for User.
 *
 */
@Component
public class UserException {

    private static PropertiesConfig propertiesConfig;

    @Autowired
    public UserException(PropertiesConfig propertiesConfig) {
        UserException.propertiesConfig = propertiesConfig;
    }

    /**
     * Returns new RuntimeException based on EntityType, ExceptionType and args
     *
     * @param entityType
     * @param exceptionType
     * @param args
     * @return Error
     */
    public static RuntimeException throwException(EntityType entityType, ExceptionType exceptionType, String... args) {
        String errorKey = formatToErrorKey(entityType, exceptionType);

        return generateException(exceptionType, errorKey, args);
    }

    /**
     * Formats the entity type and exception type into a custom error key
     * being used in our custom error exception properties.
     *
     * @param entityType
     * @param exceptionType
     * @return String
     */
    private static String formatToErrorKey(EntityType entityType, ExceptionType exceptionType) {
        String result = entityType.name().concat(".").concat(exceptionType.getValue()).toLowerCase();

        return result;
    }

    /**
     * Retrieves error message using the error key from the custom exception properties.
     *
     * @param errorKey
     * @return String
     */
    private static Optional getErrorMessage(String errorKey) {
        Optional<String> message = Optional.ofNullable(propertiesConfig.getConfigValue(errorKey));

        return message;
    }

    /**
     * Generates an exception depending on error.
     *
     * @param exceptionType
     * @param errorKey
     * @param args
     * @return Error
     */
    private static RuntimeException generateException(ExceptionType exceptionType, String errorKey, String... args) {
        Optional<String> message = getErrorMessage(errorKey);
        String formattedMessage = MessageFormat.format(message.get(), args);

        if(ExceptionType.DUPLICATE_ENTITY.equals(exceptionType)) {
            return new DuplicateEntityException(formattedMessage);
        }

        return new RuntimeException(formattedMessage);
    }

    /**
     * Subclass of RuntimeException
     */
    public static class DuplicateEntityException extends RuntimeException {
        public DuplicateEntityException(String message) {
            super(message);
        }
    }
}
