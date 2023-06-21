package net.cnam.fleetview.controller.courses;

import net.cnam.fleetview.model.course.Course;

import java.util.List;

public interface CourseChooser {
    void chooseCourse(Course course);

    List<Integer> getBlacklist();
}
