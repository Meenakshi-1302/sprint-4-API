package com.rts.tap.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long employeeId;

    @Column(name = "employee_email", unique = true)
    private String employeeEmail;

    @Column(name = "employee_name")
    private String employeeName;

    @Column(name = "work_location")
    private String workLocation;

    @Enumerated(EnumType.STRING)
    @Column(name = "employee_status")
    private EmploymentStatus employeeStatus;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @ManyToOne
    @JoinColumn(name = "businessunit_id")
    private BusinessUnit businessUnit;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "created_date", updatable = false)
    private LocalDateTime createdDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    @Column(name = "manager_id")
    private Long managerId;

    @PrePersist
    protected void onCreate() {
        createdDate = LocalDateTime.now();
    }
 
    @PreUpdate
    protected void onUpdate() {
        updatedDate = LocalDateTime.now();
    }
 
    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getWorkLocation() {
        return workLocation;
    }

    public void setWorkLocation(String workLocation) {
        this.workLocation = workLocation;
    }

    public EmploymentStatus getEmployeeStatus() {
        return employeeStatus;
    }

    public void setEmployeeStatus(EmploymentStatus employeeStatus) {
        this.employeeStatus = employeeStatus;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public BusinessUnit getBusinessUnit() {
        return businessUnit;
    }

    public void setBusinessUnit(BusinessUnit businessUnit) {
        this.businessUnit = businessUnit;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    public Employee(Long employeeId, String employeeEmail, String employeeName, String workLocation,
                    EmploymentStatus employeeStatus, Role role, BusinessUnit businessUnit, LocalDateTime createdDate,
                    LocalDateTime updatedDate, Long managerId) {
        super();
        this.employeeId = employeeId;
        this.employeeEmail = employeeEmail;
        this.employeeName = employeeName;
        this.workLocation = workLocation;
        this.employeeStatus = employeeStatus;
        this.role = role;
        this.businessUnit = businessUnit;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.managerId = managerId;
    }

    public Employee() {
        super();
    }

    public enum EmploymentStatus {
        ACTIVE, INACTIVE
    }
}
