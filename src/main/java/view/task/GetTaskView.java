package view.task;

import domains.permission.entity.Permission;
import domains.task.entity.Task;
import interface_adapter.permission.delete_permission.DeletePermissionState;
import interface_adapter.project.get_project.GetProjectViewModel;
import interface_adapter.task.add_task.AddTaskController;
import interface_adapter.task.add_task.AddTaskState;
import interface_adapter.task.add_task.AddTaskViewModel;
import interface_adapter.task.close_task.CloseTaskController;
import interface_adapter.task.close_task.CloseTaskState;
import interface_adapter.task.close_task.CloseTaskViewModel;
import interface_adapter.task.edit_task.EditTaskState;
import interface_adapter.task.edit_task.EditTaskViewModel;
import interface_adapter.task.get_task.GetTaskController;
import interface_adapter.task.get_task.GetTaskState;
import interface_adapter.task.get_task.GetTaskViewModel;
import interface_adapter.view_model.ViewManagerModel;
import view.permission.GetPermissionView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static constant.ViewConstant.GET_TASK_VIEW_NAME;

public class GetTaskView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = GET_TASK_VIEW_NAME;

    private final GetProjectViewModel getProjectViewModel;
    private final GetTaskViewModel getTaskViewModel;
    private final GetTaskController getTaskController;
    private final AddTaskViewModel addTaskViewModel;
    private final AddTaskController addTaskController;
    private final EditTaskViewModel editTaskViewModel;

    private final CloseTaskViewModel closeTaskViewModel;
    private final CloseTaskController closeTaskController;
    private final ViewManagerModel viewManagerModel;
    JLabel title;
    private final JButton addTask;
    private final JButton editTask;
    private final JButton closeTask;
    private final JButton goBack;

    DefaultListModel<Task> taskListModel = new DefaultListModel<>();
    JList<Task> taskList = new JList<>(taskListModel);

    public GetTaskView(ViewManagerModel viewManagerModel, GetProjectViewModel getProjectViewModel,
                       GetTaskViewModel getTaskViewModel, GetTaskController getTaskController,
                       AddTaskViewModel addTaskViewModel, AddTaskController addTaskController,
                       EditTaskViewModel editTaskViewModel,
                       CloseTaskViewModel closeTaskViewModel, CloseTaskController closeTaskController) {
        this.viewManagerModel = viewManagerModel;
        this.getTaskViewModel = getTaskViewModel;
        this.addTaskViewModel = addTaskViewModel;
        this.getProjectViewModel = getProjectViewModel;
        this.getTaskController = getTaskController;
        this.addTaskController = addTaskController;
        this.editTaskViewModel = editTaskViewModel;
        this.closeTaskViewModel = closeTaskViewModel;
        this.closeTaskController = closeTaskController;

        this.getTaskViewModel.addPropertyChangeListener(this);
        this.closeTaskViewModel.addPropertyChangeListener(this);

        title = new JLabel(getTaskViewModel.TITLE_LABEL);

        JPanel buttons = new JPanel();

        addTask = new JButton(GetTaskViewModel.ADD_TASK_BUTTON_LABEL);
        buttons.add(addTask);

        editTask = new JButton(GetTaskViewModel.EDIT_TASK_BUTTON_LABEL);
        buttons.add(editTask);

        closeTask = new JButton(getTaskViewModel.CLOSE_TASK_BUTTON_LABEL);
        buttons.add(closeTask);

        goBack = new JButton(GetTaskViewModel.GO_BACK_BUTTON_LABEL);
        buttons.add(goBack);

        addTask.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(java.awt.event.ActionEvent e) {
                        if (e.getSource().equals(addTask)){
                            GetTaskState getTaskState = getTaskViewModel.getState();
                            AddTaskState addTaskState = addTaskViewModel.getState();
                            addTaskState.setProjectID(getTaskState.getProjectID());
                            addTaskViewModel.setState(addTaskState);

                            viewManagerModel.setActiveView(addTaskViewModel.getViewName());
                            viewManagerModel.firePropertyChanged();
                        }
                    }
                }
        );

        editTask.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(java.awt.event.ActionEvent e) {
                        if (e.getSource().equals(editTask)){
                            Task selectedTask = taskList.getSelectedValue();
                            if (selectedTask == null) {
                                JOptionPane.showMessageDialog(GetTaskView.this, "Please select a task to edit.");
                            } else {
                                EditTaskState editTaskState = editTaskViewModel.getState();
                                editTaskState.setProjectID(selectedTask.getProjectID());
                                editTaskState.setTaskID(selectedTask.getTaskID());
                                editTaskState.setTaskName(selectedTask.getTaskName());
                                editTaskState.setTaskDescription(selectedTask.getTaskDescription());
                                editTaskState.setInitial(true);

                                editTaskViewModel.setState(editTaskState);
                                editTaskViewModel.firePropertyChanged();

                                viewManagerModel.setActiveView(editTaskViewModel.getViewName());
                                viewManagerModel.firePropertyChanged();
                            }
                        }
                    }
                }
        );

        closeTask.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(closeTask)) {
                            Task selectedTask = taskList.getSelectedValue();
                            if (selectedTask == null) {
                                JOptionPane.showMessageDialog(GetTaskView.this, "Please select a task to close.");
                            } else {
                                int result = JOptionPane.showConfirmDialog(GetTaskView.this, String.format("Are you sure you want to delete task \"%s\"?", selectedTask.getTaskName()));
                                if (result == JOptionPane.YES_OPTION) {
                                    closeTaskController.execute(selectedTask.getTaskID());
                                }
                            }
                        }
                    }
                }
        );

        goBack.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(java.awt.event.ActionEvent e) {
                        if (e.getSource().equals(goBack)){
                            viewManagerModel.setActiveView(getProjectViewModel.getViewName());
                            viewManagerModel.firePropertyChanged();
                        }
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(new JScrollPane(taskList));
        this.add(buttons);
    }

    public void actionPerformed(ActionEvent evt) {
        JOptionPane.showConfirmDialog(this, "Not implemented yet.");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("getTaskState")) {
            GetTaskState state = (GetTaskState) evt.getNewValue();

            if (state.isInitial()) {
                state.setInitial(false);
                getTaskController.execute(state.getProjectID());
            } else if (state.getGetTaskError() != null) {
                JOptionPane.showMessageDialog(this, state.getGetTaskError());
            } else {
                taskListModel.clear();
                for (Task task : state.getTasks()) {
                    taskListModel.addElement(task);
                }
            }
        }

        if (evt.getPropertyName().equals("closeTaskState")) {
            CloseTaskState state = (CloseTaskState) evt.getNewValue();
            if (state.getCloseTaskError() != null) {
                JOptionPane.showMessageDialog(this, state.getCloseTaskError());
            } else {
                JOptionPane.showMessageDialog(this, String.format("Task with id \"%s\" deleted.", state.getTaskID()));
                this.getTaskController.execute(null);
            }
        }
    }
}
