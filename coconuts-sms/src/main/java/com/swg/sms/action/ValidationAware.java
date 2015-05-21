package com.swg.sms.action;

public interface ValidationAware extends Validatable {
    boolean isValidationEnabled();

    void setValidationEnabled(boolean validationEnabled);

    void addValidator(Validator<?> validator);

    void removeValidator(Validator<?> validator);
}
