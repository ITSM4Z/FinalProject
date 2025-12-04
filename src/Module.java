/**
 *
 * @author Meshal
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Module {
    private String title;
    private ArrayList<Lesson> lessons;

    public Module(){}
    public Module(String title) { //removed the ArrayList parameter
        this.title = title;
        this.lessons = new ArrayList<>();
    }

    public void setTitle(String title){ //new
        this.title = title;
    }
    public String getTitle(){ //new
        return title;
    }

    public void addLesson(Lesson lesson){ //made it public
        this.lessons.add(lesson);
    }
    public void removeLesson(Lesson lesson){ //made it public
        for(Lesson obj : lessons){
            if(obj.getTitle().equalsIgnoreCase(lesson.getTitle())){
                lessons.remove(obj);
            }
        }
    }
    public List<Lesson> getLessonsList(){ //new
       return Collections.unmodifiableList(lessons);
    }
    public void setLessonsList(ArrayList<Lesson> lessons){ //new
        this.lessons = lessons;
    }

    @Override
    public String toString() { //Added more info
        return "module title: " + title + ". " + lessons.size() + " lessons.";
    }
}