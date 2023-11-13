package view;

import interface_adapter.project.create_project.CreateProjectController;
import interface_adapter.project.create_project.CreateProjectState;
import interface_adapter.project.create_project.CreateProjectViewModel;
import interface_adapter.project.create_project.CreateProjectController;
import interface_adapter.view_model.ViewManagerModel;
import org.checkerframework.checker.units.qual.C;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class CreateProjectView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "CreateProjectView";
    private final CreateProjectViewModel createProjectViewModel;
    private final ViewManagerModel viewManagerModel;
    private final JTextField projectNameInputField = new JTextField(50);
    private final CreateProjectController createProjectController;
    private final JButton createProjectButton;
    private final JButton cancelButton;

    public CreateProjectView(
            CreateProjectController createProjectController, CreateProjectViewModel createProjectViewModel,
            ViewManagerModel viewManagerModel) {
        this.createProjectController = createProjectController;
        this.createProjectViewModel = createProjectViewModel;
        this.viewManagerModel = viewManagerModel;

        createProjectViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(CreateProjectViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel projectNamePanel = new LabelTextPanel(new JLabel(CreateProjectViewModel.PROJECT_NAME_LABEL), projectNameInputField);

        JPanel button1 = new JPanel();
        createProjectButton = new JButton(CreateProjectViewModel.CREATE_PROJECT_BUTTON_LABEL);
        button1.add(createProjectButton);

        JPanel button2 = new JPanel();
        cancelButton = new JButton(CreateProjectViewModel.CANCEL_BUTTON_LABEL);
        button2.add(cancelButton);

        createProjectButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        if (evt.getSource().equals(createPermissionButton)) {
                            CreatePermissionState createPermissionState = createProjectViewModel.getState();
                            createPermissionController.execute(
                                    createPermissionState.getUserId(),
                                    createPermissionState.getProjectId(),
                                    createPermissionState.getPermissionName(),
                                    createPermissionState.getPermissionDescription()
                            );
                            clearInputFields();
                        }
                    }
                }
        );

        createProjectButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        if (evt.getSource().equals(createPermissionButton)) {
                            CreatePermissionState createPermissionState = createProjectViewModel.getState();
                            createPermissionController.execute(
                                    createPermissionState.getUserId(),
                                    createPermissionState.getProjectId(),
                                    createPermissionState.getPermissionName(),
                                    createPermissionState.getPermissionDescription()
                            );
                            clearInputFields();
                        }
                    }
                }
        );


        userIdInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        CreatePermissionState createPermissionState = createProjectViewModel.getState();
                        String text = userIdInputField.getText() + e.getKeyChar();
                        createPermissionState.setUserId(text);
                        createProjectViewModel.setState(createPermissionState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                }
        );

        projectIdInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        CreatePermissionState createPermissionState = createProjectViewModel.getState();
                        String text = projectIdInputField.getText() + e.getKeyChar();
                        createPermissionState.setProjectId(text);
                        createProjectViewModel.setState(createPermissionState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                }
        );

        permissionNameInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        CreatePermissionState createPermissionState = createProjectViewModel.getState();
                        String text = permissionNameInputField.getText() + e.getKeyChar();
                        createPermissionState.setPermissionName(text);
                        createProjectViewModel.setState(createPermissionState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                }
        );

        permissionDescriptionInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        CreatePermissionState createPermissionState = createProjectViewModel.getState();
                        String text = permissionDescriptionInputField.getText() + e.getKeyChar();
                        createPermissionState.setPermissionDescription(text);
                        createProjectViewModel.setState(createPermissionState);
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
        this.add(userIdPanel);
        this.add(projectIdPanel);
        this.add(permissionNamePanel);
        this.add(permissionDescriptionPanel);
        this.add(buttons);
    };

    public void actionPerformed(ActionEvent evt) {
        JOptionPane.showConfirmDialog(this, "Not implemented yet.");
    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        CreatePermissionState createPermissionState = (CreatePermissionState) evt.getNewValue();
        if (createPermissionState.getCreatePermissionError() != null) {
            JOptionPane.showMessageDialog(this, createPermissionState.getCreatePermissionError());
        } else if (createPermissionState.getPermissionId() != null) {
            // On success, switch to the get permission view.
            JOptionPane.showMessageDialog(this, "Permission created with id " + createPermissionState.getPermissionId() + ".");
            createPermissionState.setPermissionId(null);
        }
    }

    private void clearInputFields() {
        userIdInputField.setText("");
        projectIdInputField.setText("");
        permissionNameInputField.setText("");
        permissionDescriptionInputField.setText("");
    }
}