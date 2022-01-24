package com.mindex.challenge.data;

import java.util.List;

public class Compensation {
    private Employee employee;
    private String salary;
    private String effectiveDate;

    public Compensation() {
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
