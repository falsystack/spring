package jp.co.falsystack.hexagonal.common;

import jakarta.validation.*;

import java.util.Set;

public abstract class SelfValidating<T> {

    private Validator validator;

    public SelfValidating() {
        var factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    protected void validateSelf() {
        Set<ConstraintViolation<T>> validations = validator.validate((T) this);
        if (!validations.isEmpty()) {
            throw new ConstraintViolationException(validations);
        }
    }
}
