package view.task;

import interface_adapter.task.add_task.AddTaskController;
import interface_adapter.task.add_task.AddTaskState;
import interface_adapter.task.add_task.AddTaskViewModel;
import interface_adapter.task.get_task.GetTaskViewModel;
import interface_adapter.view_model.ViewManagerModel;
import view.common.LabelTextPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static constant.ViewConstant.ADD_TASK_VIEW_NAME;

public class AddTaskView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = ADD_TASK_VIEW_NAME;
    private final AddTaskViewModel addTaskViewModel;
    private final GetTaskViewModel getTaskViewModel;
    private final ViewManagerModel viewManagerModel;
    private final JTextField taskNameInputField = new JTextField(50);
    private final JTextField taskDescriptionInputField = new JTextField(50);
    private final AddTaskController addTaskController;
    private final JButton addTaskButton;
    private final JButton cancelButton;
    private JFormattedTextField formatText;

    public AddTaskView(
            AddTaskViewModel addTaskViewModel, AddTaskController addTaskController,
            GetTaskViewModel getTaskViewModel, ViewManagerModel viewManagerModel){
        this.addTaskViewModel = addTaskViewModel;
        this.addTaskController = addTaskController;
        this.getTaskViewModel = getTaskViewModel;
        this.viewManagerModel = viewManagerModel;

        addTaskViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(AddTaskViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel taskNamePanel = new LabelTextPanel(new JLabel(AddTaskViewModel.TASK_NAME_LABEL), taskNameInputField);
        LabelTextPanel taskDescriptionPanel = new LabelTextPanel(new JLabel(AddTaskViewModel.TASK_DESCRIPTION_LABEL), taskDescriptionInputField);

        JPanel buttons = new JPanel();
        addTaskButton = new JButton(addTaskViewModel.ADD_TASK_BUTTON_LABEL);
        buttons.add(addTaskButton);

        cancelButton = new JButton(addTaskViewModel.CANCEL_BUTTON_LABEL);
        buttons.add(cancelButton);

        addTaskButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(addTaskButton)) {
                            AddTaskState addTaskState = addTaskViewModel.getState();
                            addTaskController.execute(
                                    addTaskState.getProjectID(),
                                    addTaskState.getTaskName(),
                                    addTaskState.getTaskDescription()
                            );
                            clearInputFields();
                        }
                    }
                }
        );

        cancelButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(cancelButton)) {
                            clearInputFields();
                            viewManagerModel.setActiveView(getTaskViewModel.getViewName());
                            viewManagerModel.firePropertyChanged();
                        }
                    }
                }
        );

        taskNameInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        AddTaskState addTaskState = addTaskViewModel.getState();
                        String text = taskNameInputField.getText() + e.getKeyChar();
                        addTaskState.setTaskName(text);
                        addTaskViewModel.setState(addTaskState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );

        taskDescriptionInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        AddTaskState addTaskState = addTaskViewModel.getState();
                        String text = taskDescriptionInputField.getText() + e.getKeyChar();
                        addTaskState.setTaskDescription(text);
                        addTaskViewModel.setState(addTaskState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(taskNamePanel);
        this.add(taskDescriptionPanel);
        this.add(buttons);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showConfirmDialog(this, "Not implemented yet.");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        AddTaskState addTaskState = (AddTaskState) evt.getNewValue();
        if (addTaskState.getAddTaskError() != null) {
            JOptionPane.showMessageDialog(this, addTaskState.getAddTaskError());
        } else if (addTaskState.getTaskID() != null) {
            JOptionPane.showMessageDialog(this, String.format("Task \"%s\" is successfully created.", addTaskState.getTaskName()));
        }
    }

    private void clearInputFields() {
        taskNameInputField.setText("");
        taskDescriptionInputField.setText("");

        AddTaskState addTaskState = addTaskViewModel.getState();
        addTaskState.setTaskName("");
        addTaskState.setTaskDescription("");
    }

    public JButton getAddTaskButton() {
        return addTaskButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public JTextField getTaskNameInputField() {
        return taskNameInputField;
    }

    public JTextField getTaskDescriptionInputField() {
        return taskDescriptionInputField;
    }
}
