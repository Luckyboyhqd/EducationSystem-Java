package com.educational.entity;


import java.math.BigDecimal;

public class Course {

    private String id;

    private String courseNo;

    private String name;

    private String style;

    private BigDecimal credit;

    private BigDecimal theoryTime;

    private BigDecimal experimentTime;

    private String textBook;

    private String className;

    private String stauts;

    private String teacher;

    private int num;

    private String teacherId;

    private int term;

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getTextBook() {
        return textBook;
    }

    public void setTextBook(String textBook) {
        this.textBook = textBook;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCourseNo() {
        return courseNo;
    }

    public void setCourseNo(String courseNo) {
        this.courseNo = courseNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public BigDecimal getCredit() {
        return credit;
    }

    public void setCredit(BigDecimal credit) {
        this.credit = credit;
    }

    public BigDecimal getTheoryTime() {
        return theoryTime;
    }

    public void setTheoryTime(BigDecimal theoryTime) {
        this.theoryTime = theoryTime;
    }

    public BigDecimal getExperimentTime() {
        return experimentTime;
    }

    public void setExperimentTime(BigDecimal experimentTime) {
        this.experimentTime = experimentTime;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getStauts() {
        return stauts;
    }

    public void setStauts(String stauts) {
        this.stauts = stauts;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }
}
