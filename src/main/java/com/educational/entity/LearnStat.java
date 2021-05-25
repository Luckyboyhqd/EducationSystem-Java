package com.educational.entity;

import java.math.BigDecimal;

public class LearnStat {

    private String id;

    private String name;

    private String courseName;

    private String term;

    private String academicYear;

    private String style;

    private BigDecimal totalWorkload;

    private int num;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public BigDecimal getTotalWorkload() {
        return totalWorkload;
    }

    public void setTotalWorkload(BigDecimal totalWorkload) {
        this.totalWorkload = totalWorkload;
    }
}
