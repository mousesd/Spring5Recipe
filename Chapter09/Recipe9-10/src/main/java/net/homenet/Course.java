package net.homenet;

import javax.persistence.*;
import java.util.Date;

//# XML mappings
//@SuppressWarnings("WeakerAccess")
//public class Course {
//    private Long id;
//    private String title;
//    private Date beginDate;
//    private Date endDate;
//    private int fee;
//
//    public Course() { }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public Date getBeginDate() {
//        return beginDate;
//    }
//
//    public void setBeginDate(Date beginDate) {
//        this.beginDate = beginDate;
//    }
//
//    public Date getEndDate() {
//        return endDate;
//    }
//
//    public void setEndDate(Date endDate) {
//        this.endDate = endDate;
//    }
//
//    public int getFee() {
//        return fee;
//    }
//
//    public void setFee(int fee) {
//        this.fee = fee;
//    }
//
//    @Override
//    public String toString() {
//        return "Course{id=" + id +
//            ", title='" + title + '\'' +
//            ", beginDate=" + beginDate +
//            ", endDate=" + endDate +
//            ", fee=" + fee +
//            '}';
//    }
//}

//# JPA Annotation
@SuppressWarnings("WeakerAccess")
@Entity
@Table(name = "COURSE")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "TITLE", length = 100, nullable = false)
    private String title;

    @Column(name = "BEGIN_DATE")
    private Date beginDate;

    @Column(name = "END_DATE")
    private Date endDate;

    @Column(name = "FEE")
    private int fee;

    public Course() { }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    @Override
    public String toString() {
        return "Course{id=" + id +
            ", title='" + title + '\'' +
            ", beginDate=" + beginDate +
            ", endDate=" + endDate +
            ", fee=" + fee +
            '}';
    }
}