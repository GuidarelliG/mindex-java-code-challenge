package com.mindex.challenge.data;

import java.util.List;

public class Compensation {
    private String cid;
    private Employee employee;
    private String salary;
    private String effectiveDate;

    public Compensation() {
    }

    public void setId(String cid){
        this.cid = cid;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }
}
