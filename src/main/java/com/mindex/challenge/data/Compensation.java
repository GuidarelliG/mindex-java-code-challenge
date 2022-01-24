package com.mindex.challenge.data;

import java.util.List;

public class Compensation {
    private String employeeId;
    private String salary;
    private String effectiveDate;

    public Compensation() {
    }

    public String getId(){
        return this.employeeId;
    }

    public String getSalary() {
        return this.salary;
    }

    public String getEffectiveDate() {
        return this.effectiveDate;
    }

    public void setId(String employeeId){
        this.employeeId = employeeId;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }
}
