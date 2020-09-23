package com.tech.atm.model;

public class PossibleFailure {

    private Long id;
    private String failureName;
    private String failureCode;
    private Boolean hasBeenFixed;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFailureName() {
        return failureName;
    }

    public void setFailureName(String failureName) {
        this.failureName = failureName;
    }

    public String getFailureCode() {
        return failureCode;
    }

    public void setFailureCode(String failureCode) {
        this.failureCode = failureCode;
    }

    public Boolean getHasBeenFixed() {
        return hasBeenFixed;
    }

    public void setHasBeenFixed(Boolean hasBeenFixed) {
        this.hasBeenFixed = hasBeenFixed;
    }
}
