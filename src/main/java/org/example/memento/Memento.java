package org.example.memento;
public class Memento implements IMemento {
    private int[] options;
    private boolean isSelected;
    private String time;

    public Memento(int[] options, boolean isSelected) {
        this.options = options.clone(); // Copy options array
        this.isSelected = isSelected;
        System.out.println("Memento created");
        time = java.util.Calendar.getInstance().getTime().toString();
    }

    public int[] getOptions() {
        return options.clone(); // Return a copy of options array
    }

    public boolean isSelected() {
        return isSelected;
    }
    @Override
    public String getTime(){
        return time;
    }
    @Override
    public String toString(){
        return "Memento: " + time + " isSelected: " + isSelected;
    }
}
