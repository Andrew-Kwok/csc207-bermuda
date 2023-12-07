package view.task;

import interface_adapter.task.edit_task.EditTaskController;
import interface_adapter.task.edit_task.EditTaskState;
import interface_adapter.task.edit_task.EditTaskViewModel;
import interface_adapter.task.get_task.GetTaskController;
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

import static constant.ViewConstant.EDIT_TASK_VIEW_NAME;

public class EditTaskView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = EDIT_TASK_VIEW_NAME;
    private final EditTaskViewModel editTaskViewModel;
    private final GetTaskViewModel getTaskViewModel;
    private final ViewManagerModel viewManagerModel;

    private final JTextField taskNameInputField = new JTextField(50);
    private final JTextField taskDescriptionInputField = new JTextField(50);
    private final EditTaskController editTaskController;
    private final GetTaskController getTaskController;
    private final JButton editTaskButton;
    private final JButton cancel;

    public EditTaskView(
            EditTaskController editTaskController, EditTaskViewModel editTaskViewModel,
            GetTaskController getTaskController, GetTaskViewModel getTaskViewModel,
            ViewManagerModel viewManagerModel) {
        this.editTaskController = editTaskController;
        this.editTaskViewModel = editTaskViewModel;

        this.getTaskController = getTaskController;
        this.getTaskViewModel = getTaskViewModel;

        this.viewManagerModel = viewManagerModel;

        editTaskViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(EditTaskViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel taskNamePanel = new LabelTextPanel(new JLabel(EditTaskViewModel.TASK_NAME_LABEL), taskNameInputField);
        LabelTextPanel taskDescriptionPanel = new LabelTextPanel(new JLabel(editTaskViewModel.TASK_DESCRIPTION_LABEL), taskDescriptionInputField);

        JPanel buttons = new JPanel();
        editTaskButton = new JButton(EditTaskViewModel.EDIT_TASK_BUTTON_LABEL);
        buttons.add(editTaskButton);

        cancel = new JButton(EditTaskViewModel.CANCEL_BUTTON_LABEL);
        buttons.add(cancel);

        editTaskButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        if (evt.getSource().equals(editTaskButton)) {
                            EditTaskState editTaskState = editTaskViewModel.getState();
                            editTaskState.setTaskName(taskNameInputField.getText());
                            editTaskState.setTaskDescription(taskDescriptionInputField.getText());
                            editTaskController.execute(
                                    editTaskState.getTaskID(),
                                    editTaskState.getProjectID(),
                                    editTaskState.getTaskName(),
                                    editTaskState.getTaskDescription()
                            );
                            clearInputFields();
                        }
                    }
                }
        );

        taskNameInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        EditTaskState editTaskState = editTaskViewModel.getState();
                        String text = taskNameInputField.getText() + e.getKeyChar();
                        editTaskState.setTaskName(text);
                        editTaskViewModel.setState(editTaskState);
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
                        EditTaskState editTaskState = editTaskViewModel.getState();
                        String text = taskDescriptionInputField.getText() + e.getKeyChar();
                        editTaskState.setTaskDescription(text);
                       editTaskViewModel.setState(editTaskState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                }
        );

        cancel.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(cancel)) {
                            clearInputFields();
                            viewManagerModel.setActiveView(getTaskViewModel.getViewName());
                            viewManagerModel.firePropertyChanged();
                        }
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(taskNamePanel);
        this.add(taskDescriptionPanel);
        this.add(buttons);
    }

    public void actionPerformed(ActionEvent evt) {
        JOptionPane.showConfirmDialog(this, "Not implemented yet.");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        EditTaskState editTaskState = (EditTaskState) evt.getNewValue();
        if (editTaskState.isInitial()) {
            setInitialInputFields();
            editTaskState.setInitial(false);
        } else if (editTaskState.getEditTaskError() != null) {
            JOptionPane.showMessageDialog(this, editTaskState.getEditTaskError());
        } else if (editTaskState.getTaskID() != null) {
            JOptionPane.showMessageDialog(this, String.format("Task \"%s\" is successfully updated.",editTaskState.getTaskName()));
        }
    }

    public void setInitialInputFields() {
        EditTaskState editTaskState = editTaskViewModel.getState();
        taskNameInputField.setText(editTaskState.getTaskName());
        taskDescriptionInputField.setText(editTaskState.getTaskDescription());
    }

    private void clearInputFields() {
        taskNameInputField.setText("");
        taskDescriptionInputField.setText("");

        EditTaskState editTaskState = editTaskViewModel.getState();
        editTaskState.setTaskName("");
        editTaskState.setTaskDescription("");
    }

    public JButton getEditTaskButton() {
        return editTaskButton;
    }

    public JButton getCancelButton() {
        return cancel;
    }

    public JTextField getTaskNameInputField() {
        return taskNameInputField;
    }

    public JTextField getTaskDescriptionInputField() {
        return taskDescriptionInputField;
    }
}
