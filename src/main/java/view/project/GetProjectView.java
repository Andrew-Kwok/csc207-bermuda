package view.project;

import interface_adapter.project.create_project.CreateProjectController;
import interface_adapter.project.create_project.CreateProjectState;
import interface_adapter.project.create_project.CreateProjectViewModel;
import interface_adapter.project.delete_project.DeleteProjectController;
import interface_adapter.project.delete_project.DeleteProjectState;
import interface_adapter.project.delete_project.DeleteProjectViewModel;
import interface_adapter.project.get_project.GetProjectController;
import interface_adapter.project.get_project.GetProjectState;
import interface_adapter.project.get_project.GetProjectViewModel;
import interface_adapter.project.share_project_page.ShareProjectPageController;
import interface_adapter.project.share_project_page.ShareProjectPageViewModel;
import interface_adapter.task.get_task.GetTaskState;
import interface_adapter.task.get_task.GetTaskViewModel;
import interface_adapter.user.loggedin.LoggedInState;
import interface_adapter.user.loggedin.LoggedInViewModel;
import interface_adapter.view_model.ViewManagerModel;
import view.permission.GetPermissionView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Map;

import static constant.ViewConstant.*;

public class GetProjectView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = GET_PROJECT_VIEW_NAME;

    private final LoggedInViewModel loggedInUserViewModel;
    private final GetProjectViewModel getProjectViewModel;
    private final GetProjectController getProjectController;
    private final CreateProjectViewModel createProjectViewModel;
    private final CreateProjectController createProjectController;
    private final ShareProjectPageViewModel shareProjectPageViewModel;
    private final ShareProjectPageController shareProjectPageController;
    private final DeleteProjectController deleteProjectController;
    private final DeleteProjectViewModel deleteProjectViewModel;
    private final GetTaskViewModel getTaskViewModel;
    private final ViewManagerModel viewManagerModel;

    JLabel title;
    final JButton createProject;
    final JButton checkTask;
    final JButton shareProject;
    final JButton deleteProject;
    final JButton goBack;
    DefaultListModel<Map<String, String>> projectListModel = new DefaultListModel<>();
    JList<Map<String, String>> projectList = new JList<>(projectListModel);

    public GetProjectView(ViewManagerModel viewManagerModel,
                          LoggedInViewModel loggedInUserViewModel,
                          GetProjectViewModel getProjectViewModel,
                          GetProjectController getProjectContoller,
                          CreateProjectViewModel createProjectViewModel,
                          CreateProjectController createProjectController,
                          DeleteProjectViewModel deleteProjectViewModel,
                          DeleteProjectController deleteProjectController,
                          ShareProjectPageViewModel shareProjectPageViewModel,
                          ShareProjectPageController shareProjectPageController,
                          GetTaskViewModel getTaskViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loggedInUserViewModel = loggedInUserViewModel;
        this.getProjectViewModel = getProjectViewModel;
        this.getProjectController = getProjectContoller;
        this.createProjectViewModel = createProjectViewModel;
        this.createProjectController = createProjectController;
        this.deleteProjectController = deleteProjectController;
        this.deleteProjectViewModel = deleteProjectViewModel;
        this.shareProjectPageViewModel = shareProjectPageViewModel;
        this.shareProjectPageController = shareProjectPageController;
        this.getTaskViewModel = getTaskViewModel;

        this.getProjectViewModel.addPropertyChangeListener(this);


        title = new JLabel(getProjectViewModel.TITLE_LABEL);

        JPanel buttons = new JPanel();

        createProject = new JButton(GetProjectViewModel.CREATE_PROJECT_BUTTON_LABEL);
        buttons.add(createProject);

        checkTask = new JButton(GetProjectViewModel.CHECK_TASK_BUTTON_LABEL);
        buttons.add(checkTask);

        shareProject = new JButton(GetProjectViewModel.SHARE_PROJECT_BUTTON_LABEL);
        buttons.add(shareProject);

        deleteProject = new JButton(GetProjectViewModel.DELETE_PROJECT_BUTTON_LABEL);
        buttons.add(deleteProject);

        goBack = new JButton(GetProjectViewModel.GO_BACK_BUTTON_LABEL);
        buttons.add(goBack);

        createProject.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    if (e.getSource().equals(createProject)){
                        CreateProjectState createProjectState = createProjectViewModel.getState();
                        LoggedInState loggedInState = loggedInUserViewModel.getState();
                        createProjectState.setUserId(loggedInState.getUser().getUserID());
                        createProjectViewModel.setState(createProjectState);

                        viewManagerModel.setActiveView(createProjectViewModel.getViewName());
                        viewManagerModel.firePropertyChanged();
                    }
                }
            }
        );

        checkTask.addActionListener(
            new ActionListener() {
                    @Override
                    public void actionPerformed(java.awt.event.ActionEvent e) {
                        if (e.getSource().equals(checkTask)){
                        Map<String, String>  project = projectList.getSelectedValue();
                        if (project == null) {
                            JOptionPane.showMessageDialog(null, "Please select a project.");
                        } else {
                            GetTaskState getTaskState = getTaskViewModel.getState();
                            getTaskState.setProjectID(project.get(PROJECT_ID));
                            getTaskState.setInitial(true);
                            getTaskViewModel.setState(getTaskState);
                            getTaskViewModel.firePropertyChanged();

                            viewManagerModel.setActiveView(getTaskViewModel.getViewName());
                            viewManagerModel.firePropertyChanged();
                        }
                    }
                }
            }
        );

        shareProject.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    if (e.getSource().equals(shareProject)){
                        Map<String, String> project = projectList.getSelectedValue();
                        if (project == null) {
                            JOptionPane.showMessageDialog(GetProjectView.this, "Please select a project.");
                        } else {
                            shareProjectPageController.execute(
                                    project.get(PROJECT_ID),
                                    project.get(PROJECT_NAME),
                                    getProjectViewModel.getState().getUserId()
                            );
                            if (shareProjectPageViewModel.getState().getErrorMessage() != null) {
                                JOptionPane.showMessageDialog(
                                        GetProjectView.this,
                                        shareProjectPageViewModel.getState().getErrorMessage());
                                return;
                            }

                            viewManagerModel.setActiveView(shareProjectPageViewModel.getViewName());
                            viewManagerModel.firePropertyChanged();
                        }
                    }
                }
            }
        );

        deleteProject.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(deleteProject)) {
                            Map<String, String> selectedProject = projectList.getSelectedValue();
                            if (selectedProject == null) {
                                JOptionPane.showMessageDialog(GetProjectView.this, "Please select a project to delete.");
                            } else {
                                int result = JOptionPane.showConfirmDialog(GetProjectView.this, String.format("Are you sure you want to delete project \"%s\"?", selectedProject.get(PROJECT_NAME)));
                                if (result == JOptionPane.YES_OPTION) {
                                    deleteProjectController.execute(selectedProject.get(PROJECT_ID));
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
                            viewManagerModel.setActiveView(loggedInUserViewModel.getViewName());
                            viewManagerModel.firePropertyChanged();
                        }
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(new JScrollPane(projectList));
        this.add(buttons);
    }

    public void actionPerformed(ActionEvent e) {
        JOptionPane.showConfirmDialog(this, "Not implemented yet.");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("getProjectState")) {
            GetProjectState state = (GetProjectState) evt.getNewValue();

            if (state.isInitial()) {
                state.setInitial(false);
                getProjectController.execute(state.getUserId());
            } else if (state.getGetProjectError() != null) {
                JOptionPane.showMessageDialog(this, state.getGetProjectError());
            } else {
                projectListModel.clear();
                for (Map<String, String> project : state.getProjects()) {
                    projectListModel.addElement(project);
                }
            }
        }

        if (evt.getPropertyName().equals("deleteProjectState")) {
            DeleteProjectState state = (DeleteProjectState) evt.getNewValue();
            if (state.getDeleteProjectError() != null) {
                JOptionPane.showMessageDialog(this, state.getDeleteProjectError());
            } else {
                JOptionPane.showMessageDialog(this, String.format("Project with id \"%s\" deleted.", state.getProjectId()));
                this.getProjectController.execute(null);
            }
        }
    }
}