package view.task;

import interface_adapter.task.get_task.GetTaskController;
import interface_adapter.task.get_task.GetTaskState;
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

import static constant.ViewConstant.GET_TASK_VIEW_NAME;

public class GetTaskView  extends JPanel implements ActionListener, PropertyChangeListener{
    public final String viewName = GET_TASK_VIEW_NAME;
    private final GetTaskViewModel getTaskViewModel;
    private final ViewManagerModel viewManagerModel;
    private final JTextField projectIDInputField = new JTextField(50);
    private final GetTaskController getTaskController;
    private final JButton getTaskButton;

    public GetTaskView(GetTaskViewModel getTaskViewModel, GetTaskController getTaskController, ViewManagerModel viewManagerModel){
        this.getTaskViewModel = getTaskViewModel;
        this.getTaskController = getTaskController;
        this.viewManagerModel = viewManagerModel;

        getTaskViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(GetTaskViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel projectIDPanel = new LabelTextPanel(new JLabel(GetTaskViewModel.PROJECTID_NAME_LABEL), projectIDInputField);

        JPanel buttons = new JPanel();
        getTaskButton = new JButton(getTaskViewModel.GET_TASK_BUTTON_LABEL);
        buttons.add(getTaskButton);

        getTaskButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(getTaskButton)){
                            GetTaskState getTaskState = getTaskViewModel.getState();
                            getTaskController.execute(
                                    getTaskState.getProjectID()
                            );
                            clearInputFields();
                        }
                    }
                }
        );

        projectIDInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        GetTaskState getTaskState = getTaskViewModel.getState();
                        String text = projectIDInputField.getText() + e.getKeyChar();
                        getTaskState.setProjectID(text);
                        getTaskViewModel.setState(getTaskState);
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
        this.add(projectIDPanel);
        this.add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showConfirmDialog(this, "Not implemented yet.");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        GetTaskState getTaskState = (GetTaskState) evt.getNewValue();
        if (getTaskState.getGetTaskError() != null) {
            JOptionPane.showMessageDialog(this, getTaskState.getGetTaskError());
        } else if (getTaskState.getProjectID() != null) {
            // On success, switch to the get permission view.
            JOptionPane.showMessageDialog(this, "Tasks: " + getTaskState.getTasks());
        }
    }

    private void clearInputFields() {
        projectIDInputField.setText("");
    }
}
