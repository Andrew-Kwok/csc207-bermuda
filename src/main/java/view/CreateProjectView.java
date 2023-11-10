package interface_adapter.project.create_project;
import interface_adapter.project.create_project.CreateProjectController;
import interface_adapter.project.create_project.CreateProjectViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


public class CreateProjectView extends JPanel implements ActionListener, PropertyChangeListener {
    public CreateProjectView(CreateProjectViewModel createProjectViewModel, CreateProjectController createProjectController){

    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}