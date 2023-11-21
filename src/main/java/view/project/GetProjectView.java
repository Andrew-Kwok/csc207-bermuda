package view.project;

import domains.project.entity.Project;
import interface_adapter.project.create_project.CreateProjectController;
import interface_adapter.project.create_project.CreateProjectViewModel;
import interface_adapter.project.get_project.GetProjectController;
import interface_adapter.project.get_project.GetProjectState;
import interface_adapter.project.get_project.GetProjectViewModel;
import interface_adapter.user.loggedin_user.LoggedInViewModel;
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
    private final ViewManagerModel viewManagerModel;

    JLabel title;
    final JButton createProject;
    final JButton checkProject;
    final JButton cancel;
    DefaultListModel<Project> projectListModel = new DefaultListModel<Project>();
    JList<Project> projectList = new JList<Project>(projectListModel);

    public GetProjectView(ViewManagerModel viewManagerModel,
                          LoggedInViewModel loggedInUserViewModel,
                          GetProjectViewModel getProjectViewModel,
                          GetProjectController getProjectContoller,
                          CreateProjectViewModel createProjectViewModel,
                          CreateProjectController createProjectController) {
        this.viewManagerModel = viewManagerModel;
        this.loggedInUserViewModel = loggedInUserViewModel;
        this.getProjectViewModel = getProjectViewModel;
        this.getProjectController = getProjectContoller;
        this.createProjectViewModel = createProjectViewModel;
        this.createProjectController = createProjectController;
        this.getProjectViewModel.addPropertyChangeListener(this);

        title = new JLabel(getProjectViewModel.TITLE_LABEL);

        JPanel buttons = new JPanel();

        createProject = new JButton(GetProjectViewModel.CREATE_PROJECT_BUTTON_LABEL);
        buttons.add(createProject);

        checkProject = new JButton(GetProjectViewModel.CHECK_PROJECT_BUTTON_LABEL);
        buttons.add(checkProject);

        cancel = new JButton(GetProjectViewModel.CANCEL_BUTTON_LABEL);
        buttons.add(cancel);

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

        checkProject.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    if (e.getSource().equals(checkProject)){
                        Project project = projectList.getSelectedValue();
                        if (project == null) {
                            JOptionPane.showMessageDialog(null, "Please select a project.");
                            return;
                        } else {
                            System.out.println("Selected project: " + project.getProjectName());
                            // TODO: switch to GetTaskView
                            // viewManagerModel.firePropertyChanged();
                        }
                    }
                }
            }
        );

        cancel.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(java.awt.event.ActionEvent e) {
                        if (e.getSource().equals(cancel)){
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

            if (state.isInitialized()) {
                getProjectController.execute(state.getUserId());
                state.setInitialized(false);
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
