/**
 *
 * @author Osama
 */

public interface Enrollable {
    boolean enroll(Student s) throws AlreadyEnrolledException, CourseFullException;
    boolean drop(Student s) throws UserNotFoundException;
}