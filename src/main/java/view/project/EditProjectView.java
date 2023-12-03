package view.project;

import interface_adapter.project.edit_project.EditProjectController;
import interface_adapter.project.edit_project.EditProjectState;
import interface_adapter.project.edit_project.EditProjectViewModel;
import interface_adapter.project.get_project.GetProjectController;
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

import static constant.ViewConstant.EDIT_PROJECT_VIEW_NAME;

public class EditProjectView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = EDIT_PROJECT_VIEW_NAME;
    private final EditProjectViewModel editProjectViewModel;
    private final GetProjectViewModel getProjectViewModel;
    private final ViewManagerModel viewManagerModel;

    private final JTextField projectNameInputField = new JTextField(50);
    private final EditProjectController editProjectController;
    private final GetProjectController getProjectController;
    private final JButton editProjectButton;
    private final JButton cancel;

    public EditProjectView(
            EditProjectController editProjectController, EditProjectViewModel editProjectViewModel,
            GetProjectController getProjectController, GetProjectViewModel getProjectViewModel,
            ViewManagerModel viewManagerModel) {
        this.editProjectController = editProjectController;
        this.editProjectViewModel = editProjectViewModel;

        this.getProjectController = getProjectController;
        this.getProjectViewModel = getProjectViewModel;

        this.viewManagerModel = viewManagerModel;

        editProjectViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(EditProjectViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel projectNamePanel = new LabelTextPanel(new JLabel(EditProjectViewModel.PROJECT_NAME_LABEL), projectNameInputField);

        JPanel buttons = new JPanel();
        editProjectButton = new JButton(EditProjectViewModel.EDIT_PROJECT_BUTTON_LABEL);
        buttons.add(editProjectButton);

        cancel = new JButton(EditProjectViewModel.CANCEL_BUTTON_LABEL);
        buttons.add(cancel);

        editProjectButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        if (evt.getSource().equals(editProjectButton)) {
                            EditProjectState editProjectState = editProjectViewModel.getState();
                            editProjectController.execute(
                                    editProjectState.getProjectID(),
                                    editProjectState.getProjectName()
                                    );
                            clearInputFields();
                        }
                    }
                }
        );

        projectNameInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        EditProjectState editProjectState = editProjectViewModel.getState();
                        String text = projectNameInputField.getText() + e.getKeyChar();
                        editProjectState.setProjectName(text);
                        editProjectViewModel.setState(editProjectState);
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
                            viewManagerModel.setActiveView(getProjectViewModel.getViewName());
                            viewManagerModel.firePropertyChanged();
                        }
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(projectNamePanel);
        this.add(buttons);
    }

    public void actionPerformed(ActionEvent evt) {
        JOptionPane.showConfirmDialog(this, "Not implemented yet.");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        EditProjectState editProjectState = (EditProjectState) evt.getNewValue();
        if (editProjectState.isInitial()) {
            setInitialInputFields();
            editProjectState.setInitial(false);
        } else if (editProjectState.getEditProjectError() != null) {
            JOptionPane.showMessageDialog(this, editProjectState.getEditProjectError());
        } else if (editProjectState.getProjectID() != null) {
            JOptionPane.showMessageDialog(this, String.format("Project \"%s\" is successfully updated.",editProjectState.getProjectName()));
        }
    }

    public void setInitialInputFields() {
        EditProjectState editProjectState = editProjectViewModel.getState();
        projectNameInputField.setText(editProjectState.getProjectName());
    }

    private void clearInputFields() {
        projectNameInputField.setText("");

        EditProjectState editProjectState = editProjectViewModel.getState();
        editProjectState.setProjectName("");
    }
}


