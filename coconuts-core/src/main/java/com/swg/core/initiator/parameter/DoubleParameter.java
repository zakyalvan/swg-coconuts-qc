package com.swg.core.initiator.parameter;

public class DoubleParameter implements CellParameter.NumberParameter {

    private Double value;

    private boolean proceed = false;

    @Override
    public Double getValue() {
        return value;
    }

    @Override
    public void setValue(Double value) {
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
