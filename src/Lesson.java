/**
 *
 * @author Meshal
 */

public class Lesson {
    private String title;
    private int durationMinutes;

    public Lesson(){}
    public Lesson(String title, int durationMinutes) {
        this.title = title;
        this.durationMinutes = durationMinutes;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public int getDurationMinutes() {
        return durationMinutes;
    }
    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    @Override
    public String toString() {
        return "Lesson title: " + title + ", Duration in minutes: " + "(" + durationMinutes + ")";
    }
}
