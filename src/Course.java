/**
 *
 * @author Meshal
 */

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Course implements Enrollable, Rateable, Cloneable, Comparable<Course>{
    private int courseID;
    private int capacity;
    private String title;
    private double price;
    private double averageRating; //new
    private CourseLevel courseLevel;

    private ArrayList<Module> modules;
    private ArrayList<Double> ratings;
    private ArrayList<Student> enrolledStudents;

    public Course() {}
    public Course(int courseID, int capacity, String title, double price, CourseLevel courseLevel) {
        this.courseID = courseID;
        this.capacity = capacity;
        this.title = title;
        this.price = price;
        this.courseLevel = courseLevel; //new
        this.modules = new ArrayList<>();
        this.ratings = new ArrayList<>();
        this.enrolledStudents = new ArrayList<>();
    }
    public int getCourseID() {
        return courseID;
    }
    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public int getCapacity() {
        return capacity;
    }
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public CourseLevel getCourseLevel(){
        return courseLevel;
    } //new
    public void setCourseLevel(CourseLevel courseLevel){
        this.courseLevel = courseLevel;
    } //new


    public List<Module> getModules() {
        return Collections.unmodifiableList(modules);
    } //Made it return an unmodifiablelist
    public void setModules(ArrayList<Module> modules) {
        this.modules = modules;
    }

    public List<Double> getRatings() {
        return Collections.unmodifiableList(ratings);
    } //New and made it return an unmodifiablelist
    public void setRatings(ArrayList<Double> ratings) {
        this.ratings = ratings;
    }

    public List<Student> getEnrolledStudents() {
        return Collections.unmodifiableList(enrolledStudents);
    } //Made it return an unmodifiablelist
    public void setEnrolledStudents(ArrayList<Student> enrolledStudents) {
        this.enrolledStudents = enrolledStudents;
    }

    public String courseInfo(){ //new
        return String.format("%s \nID:%d \n%d Students \nProviding %d Modules \nRatings: %.1f \nPrice: %.2f",
                title, courseID, enrolledStudents.size(), modules.size(), averageRating, price);
    }

    @Override
    public String toString(){ //new
        return String.format("%s (%d)", title, courseID);
    }

    @Override
    public Course clone() throws CloneNotSupportedException { //new
        Course cloned = (Course) super.clone();
        cloned.enrolledStudents = new ArrayList<>(this.enrolledStudents);
        cloned.modules = new ArrayList<>(this.modules);
        cloned.ratings = new ArrayList<>(this.ratings);
        return cloned;
    }

    @Override
    public boolean equals(Object obj) { //new
        if(obj == null || !(obj instanceof Course)) return false;
        if(this == obj) return true;

        Course course = (Course) obj;
        return this.courseID == course.courseID;
    }

    @Override
    public int hashCode() { //new
        return Objects.hash(courseID);
    }

    @Override
    public int compareTo(Course course) { //new
        return Integer.compare(this.courseLevel.ordinal(), course.courseLevel.ordinal());
    }

    @Override
    public boolean enroll(Student s) throws AlreadyEnrolledException, CourseFullException {//new
        if (enrolledStudents.contains(s)) {
            throw new AlreadyEnrolledException("Student is already in this course!");
        }
        if (enrolledStudents.size() >= capacity) {
            throw new CourseFullException("Sorry, this course is full!");
        }
        enrolledStudents.add(s);
        Enrollment enrollmentReceipt = new Enrollment(s, LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        enrollmentReceipt.printReceipt();
        System.out.println("Student: " + s.getName() + " was Successfully added!");
        return true;
    }
    @Override
    public boolean drop(Student s) throws UserNotFoundException { //new
        if (enrolledStudents.contains(s)) {
            enrolledStudents.remove(s);
            System.out.println("Student: " + s.getName() + " was successfully removed.");
            return true;
        } else if (!enrolledStudents.contains(s)) {
            throw new UserNotFoundException("Error: The student is not on the registered list.");
        }
        return false;
    }

    @Override
    public void addRating(Double rating) { //added functionality
        ratings.add(rating);
        calculateAverageRating();
    }

    @Override
    public double getAverageRating() { //actually returned something
        return averageRating;
    }
    public void calculateAverageRating(){ //new
        if(ratings.isEmpty()){
            averageRating = 0;
            return;
        }
        double avg = 0.0;
        for(Double rating : ratings){
            avg += rating;
        }
        averageRating = avg/ratings.size();
    }

    public class Enrollment { //made it an inner class
        private Student student;
        private String enrollmentDate;

        public Enrollment(Student student, String enrollmentDate) {
            this.student = student;
            this.enrollmentDate = enrollmentDate;
        }

        public void printReceipt() {
            System.out.println("--- Receipt Printing ---");
            System.out.println("Course: " + title);
            System.out.println("Student name: " + student.getName());
            System.out.println("Registering date:  " + enrollmentDate);
            System.out.println("-------------------");
        }
    }
}