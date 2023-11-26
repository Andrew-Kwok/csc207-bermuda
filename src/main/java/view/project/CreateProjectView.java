package view.project;

import interface_adapter.project.create_project.CreateProjectController;
import interface_adapter.project.create_project.CreateProjectState;
import interface_adapter.project.create_project.CreateProjectViewModel;
import interface_adapter.project.get_project.GetProjectViewModel;
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

import static constant.ViewConstant.CREATE_PROJECT_VIEW_NAME;

public class CreateProjectView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = CREATE_PROJECT_VIEW_NAME;
    private final CreateProjectViewModel createProjectViewModel;
    private final GetProjectViewModel getProjectViewModel;
    private final ViewManagerModel viewManagerModel;
    private final JTextField projectNameInputField = new JTextField(50);
    private final CreateProjectController createProjectController;
    private final JButton createProjectButton;
    private final JButton cancelButton;

    public CreateProjectView(
            CreateProjectController createProjectController, CreateProjectViewModel createProjectViewModel,
            GetProjectViewModel getProjectViewModel,
            ViewManagerModel viewManagerModel) {
        this.createProjectController = createProjectController;
        this.createProjectViewModel = createProjectViewModel;
        this.getProjectViewModel = getProjectViewModel;
        this.viewManagerModel = viewManagerModel;

        createProjectViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(CreateProjectViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel projectNamePanel = new LabelTextPanel(new JLabel(CreateProjectViewModel.PROJECT_NAME_LABEL), projectNameInputField);

        JPanel buttons = new JPanel();
        createProjectButton = new JButton(CreateProjectViewModel.CREATE_PROJECT_BUTTON_LABEL);
        buttons.add(createProjectButton);

        cancelButton = new JButton(CreateProjectViewModel.CANCEL_BUTTON_LABEL);
        buttons.add(cancelButton);

        createProjectButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        if (evt.getSource().equals(createProjectButton)) {
                            CreateProjectState createProjectState = createProjectViewModel.getState();
                            createProjectController.execute(createProjectState.getProjectName(), createProjectState.getUserId());
                            clearInputFields();
                        }
                    }
                }
        );

        cancelButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        if (evt.getSource().equals(cancelButton)) {
                            clearInputFields();
                            viewManagerModel.setActiveView(getProjectViewModel.getViewName());
                            viewManagerModel.firePropertyChanged();
                        }
                    }
                }
        );


        projectNameInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        CreateProjectState createProjectState = createProjectViewModel.getState();
                        String text = projectNameInputField.getText() + e.getKeyChar();
                        createProjectState.setProjectName(text);
                        createProjectViewModel.setState(createProjectState);
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
        this.add(projectNamePanel);
        this.add(buttons);
    }

    ;

    public void actionPerformed(ActionEvent evt) {
        JOptionPane.showConfirmDialog(this, "Not implemented yet.");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        CreateProjectState createProjectState = (CreateProjectState) evt.getNewValue();
        if (createProjectState.getProjectError() != null) {
            JOptionPane.showMessageDialog(this, createProjectState.getProjectError());
        } else if (createProjectState.getProjectName() != null) {
            JOptionPane.showMessageDialog(this, "Project created with name " + createProjectState.getProjectName() + ".");
            createProjectState.setProjectName("");
        }
    }

    private void clearInputFields() {
        projectNameInputField.setText("");
    }
}