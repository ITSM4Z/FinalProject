/**
 *
 * @author Mazen
 */


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Catalogue<T> {
    private ArrayList<T> items = new ArrayList<>();

    public void addItem(T item) { items.add(item); }
    public boolean removeItem(T item) { return items.remove(item); }

    public void printAll(){
        for(T item : items){
            System.out.println(item);
        }
    }

    public List<T> getItems() { return Collections.unmodifiableList(items); }
}