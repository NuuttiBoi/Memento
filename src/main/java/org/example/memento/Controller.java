package org.example.memento;

import javafx.scene.control.ListView;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    private Model model;
    private Gui gui;
    private List<IMemento> history; // Memento history
    private List<IMemento> undoHistory;
    private List<IMemento> historyList;

    public Controller(Gui gui) {
        this.model = new Model();
        this.gui = gui;
        this.history = new ArrayList<>();
        this.undoHistory = new ArrayList<>();
    }

    public void setOption(int optionNumber, int choice) {
        saveToHistory();
        clearUndoHistory();
        model.setOption(optionNumber, choice);
        setHistoryItems();
    }

    public int getOption(int optionNumber) {
        return model.getOption(optionNumber);
    }

    public void setIsSelected(boolean isSelected) {
        saveToHistory();
        clearUndoHistory();
        model.setIsSelected(isSelected);
        setHistoryItems();
    }

    public boolean getIsSelected() {
        return model.getIsSelected();
    }

    public void undo() {
        if (!history.isEmpty()) {
            System.out.println("Memento found in history");
            IMemento currentState = model.createMemento();
            undoHistory.add(currentState);
            IMemento previousState = history.remove(history.size() - 1);
            model.restoreState(previousState);
            gui.updateGui();
        }
    }

    public void redo() {
        if(!undoHistory.isEmpty()) {
            System.out.println("Memento found in history");
            IMemento nextState = undoHistory.remove(undoHistory.size() - 1);
            model.restoreState(nextState);
            gui.updateGui();
        }
    }

    public void clearUndoHistory() {
        undoHistory.clear();
    }

    private void saveToHistory() {
        IMemento currentState = model.createMemento();
        history.add(currentState);
        undoHistory.add(currentState);
    }

    public void setHistoryItems() {
        historyList = history;
        System.out.println(historyList);
        gui.setHistory(history);
    }

    public void restoreState(IMemento currentState) {
        model.restoreState(currentState);
    }
}
