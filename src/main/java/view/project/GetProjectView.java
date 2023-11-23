package view.project;

import domains.project.entity.Project;
import interface_adapter.project.create_project.CreateProjectController;
import interface_adapter.project.create_project.CreateProjectViewModel;
import interface_adapter.project.get_project.GetProjectController;
import interface_adapter.project.get_project.GetProjectState;
import interface_adapter.project.get_project.GetProjectViewModel;
import interface_adapter.task.get_task.GetTaskState;
import interface_adapter.task.get_task.GetTaskViewModel;
import interface_adapter.user.loggedin.LoggedInViewModel;
import interface_adapter.view_model.ViewManagerModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static constant.ViewConstant.GET_PROJECT_VIEW_NAME;

public class GetProjectView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = GET_PROJECT_VIEW_NAME;
    private final LoggedInViewModel loggedInUserViewModel;
    private final GetProjectViewModel getProjectViewModel;
    private final GetProjectController getProjectController;
    private final CreateProjectViewModel createProjectViewModel;
    private final CreateProjectController createProjectController;
    private final GetTaskViewModel getTaskViewModel;
    private final ViewManagerModel viewManagerModel;

    JLabel title;
    final JButton createProject;
    final JButton checkTask;
    final JButton goBack;
    DefaultListModel<Project> projectListModel = new DefaultListModel<>();
    JList<Project> projectList = new JList<>(projectListModel);

    public GetProjectView(ViewManagerModel viewManagerModel,
                          LoggedInViewModel loggedInUserViewModel,
                          GetProjectViewModel getProjectViewModel,
                          GetProjectController getProjectContoller,
                          CreateProjectViewModel createProjectViewModel,
                          CreateProjectController createProjectController,
                          GetTaskViewModel getTaskViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loggedInUserViewModel = loggedInUserViewModel;
        this.getProjectViewModel = getProjectViewModel;
        this.getProjectController = getProjectContoller;
        this.createProjectViewModel = createProjectViewModel;
        this.createProjectController = createProjectController;
        this.getTaskViewModel = getTaskViewModel;
        this.getProjectViewModel.addPropertyChangeListener(this);

        title = new JLabel(getProjectViewModel.TITLE_LABEL);

        JPanel buttons = new JPanel();

        createProject = new JButton(GetProjectViewModel.CREATE_PROJECT_BUTTON_LABEL);
        buttons.add(createProject);

        checkTask = new JButton(GetProjectViewModel.CHECK_TASK_BUTTON_LABEL);
        buttons.add(checkTask);

        goBack = new JButton(GetProjectViewModel.GO_BACK_BUTTON_LABEL);
        buttons.add(goBack);

        createProject.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    if (e.getSource().equals(createProject)){
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
                        Project project = projectList.getSelectedValue();
                        if (project == null) {
                            JOptionPane.showMessageDialog(null, "Please select a project.");
                        } else {
                            GetTaskState getTaskState = getTaskViewModel.getState();
                            getTaskState.setProjectID(project.getProjectID());
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
                for (Project project : state.getProjects()) {
                    projectListModel.addElement(project);
                }
            }
        }
    }
}
