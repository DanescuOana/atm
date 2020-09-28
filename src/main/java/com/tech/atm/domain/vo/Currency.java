package com.tech.atm.domain.vo;

import java.text.DecimalFormat;

public class Currency {
    private Long id;
    private String name;
    private String unit;
    private DecimalFormat moneyDecimalFormat;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public DecimalFormat getMoneyDecimalFormat() {
        return moneyDecimalFormat;
    }

    public void setMoneyDecimalFormat(DecimalFormat moneyDecimalFormat) {
        this.moneyDecimalFormat = moneyDecimalFormat;
    }
}
