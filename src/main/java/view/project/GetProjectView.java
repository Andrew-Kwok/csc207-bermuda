package view.project;

import domains.project.entity.Project;
import interface_adapter.project.create_project.CreateProjectController;
import interface_adapter.project.create_project.CreateProjectViewModel;
import interface_adapter.project.get_project.GetProjectController;
import interface_adapter.project.get_project.GetProjectState;
import interface_adapter.project.get_project.GetProjectViewModel;
import interface_adapter.view_model.ViewManagerModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static constant.ViewConstant.GET_PROJECT_VIEW_NAME;

public class GetProjectView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = GET_PROJECT_VIEW_NAME;
    private final GetProjectViewModel getProjectViewModel;
    private final GetProjectController getProjectController;
    private final CreateProjectViewModel createProjectViewModel;
    private final CreateProjectController createProjectController;
    private final ViewManagerModel viewManagerModel;

    JLabel title;
    final JButton createProject;
    DefaultListModel<Project> projectListModel = new DefaultListModel<Project>();

    public GetProjectView(ViewManagerModel viewManagerModel,
                          GetProjectViewModel getProjectViewModel,
                          GetProjectController getProjectContoller,
                          CreateProjectViewModel createProjectViewModel,
                          CreateProjectController createProjectController) {
        this.viewManagerModel = viewManagerModel;
        this.getProjectViewModel = getProjectViewModel;
        this.getProjectController = getProjectContoller;
        this.createProjectViewModel = createProjectViewModel;
        this.createProjectController = createProjectController;
        this.getProjectViewModel.addPropertyChangeListener(this);

        title = new JLabel(getProjectViewModel.TITLE_LABEL);

        this.getProjectController.execute(null);

        JPanel buttons = new JPanel();

        createProject = new JButton(GetProjectViewModel.CREATE_PROJECT_BUTTON_LABEL);
        buttons.add(createProject);

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

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(new JScrollPane(new JList<>(projectListModel)));
        this.add(buttons);
    }

    public void actionPerformed(ActionEvent e) {
        JOptionPane.showConfirmDialog(this, "Not implemented yet.");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("getProjectState")) {
            GetProjectState state = (GetProjectState) evt.getNewValue();
            if (state.getGetProjectError() != null) {
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
