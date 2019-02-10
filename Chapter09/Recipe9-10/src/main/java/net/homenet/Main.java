package net.homenet;

import net.homenet.configuration.CourseConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.GregorianCalendar;

public class Main {
    @SuppressWarnings("Duplicates")
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(CourseConfiguration.class);

        Course course = new Course();
        course.setTitle("Core Spring");
        course.setBeginDate(new GregorianCalendar(2007, 8, 1).getTime());
        course.setEndDate(new GregorianCalendar(2007, 9, 1).getTime());
        course.setFee(1000);

        System.out.println("\r\nCourse before persisting");
        System.out.println(course);

        CourseRepository courseRepository = context.getBean(CourseRepository.class);
        Course persisted = courseRepository.save(course);

        System.out.println("\r\nCourse after persisting");
        System.out.println(persisted);

        Long courseId = persisted.getId();
        System.out.println("\r\nCourse fresh from database");
        courseRepository.findById(courseId).ifPresent(System.out::println);

        courseRepository.deleteById(courseId);
        System.exit(0);
    }
}
