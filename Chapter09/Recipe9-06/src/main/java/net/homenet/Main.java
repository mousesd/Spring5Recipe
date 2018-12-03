package net.homenet;

import java.util.GregorianCalendar;

public class Main {
    public static void main(String[] args) {
        Course course = new Course();
        course.setTitle("Core Spring");
        course.setBeginDate(new GregorianCalendar(2007, 8, 1).getTime());
        course.setEndDate(new GregorianCalendar(2007, 9, 1).getTime());
        course.setFee(1000);

        System.out.println("\r\nCourse before persisting");
        System.out.println(course);

        CourseDao courseDao = new JpaCourseDaoImpl();
        Course persisted = courseDao.store(course);

        System.out.println("\r\nCourse after persisting");
        System.out.println(persisted);

        Long courseId = persisted.getId();
        Course courseFromDb = courseDao.findById(courseId);

        System.out.println("\r\nCourse fresh from database");
        System.out.println(courseFromDb);

        courseDao.delete(courseId);
        System.exit(0);
    }
}
