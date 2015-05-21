package com.swg.base.backend.initiator.parameter;

public class TextParameter implements CellParameter.StringParameter {

    private String value;

    private boolean proceed = false;

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean isProceed() {
        return proceed;
    }

    @Override
    public void setProceed(boolean proceed) {
        this.proceed = proceed;
    }

}
