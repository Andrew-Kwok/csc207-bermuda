package view.project;

import interface_adapter.project.get_project.GetProjectViewModel;
import interface_adapter.project.share_project.ShareProjectController;
import interface_adapter.project.share_project.ShareProjectViewModel;
import interface_adapter.project.share_project.ShareProjectState;
import interface_adapter.view_model.ViewManagerModel;
import interface_adapter.user.loggedin_user.LoggedInViewModel;

import view.common.LabelTextPanel;

import constant.ViewConstant;
import view.user.LoggedInView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ShareProjectView extends JPanel implements
        ActionListener, PropertyChangeListener {

    public final String viewName = ViewConstant.SHARE_PROJECT_VIEW_NAME;
    final JTextField otherUserNameTextField = new JTextField(20);

    private final ViewManagerModel viewManagerModel;
    private final GetProjectViewModel getProjectViewModel;
    private final ShareProjectViewModel shareProjectViewModel;
    private final ShareProjectController shareProjectController;

    final JLabel title;

    final JButton shareButton;
    final JButton cancelButton;

    public ShareProjectView(
            ViewManagerModel viewManagerModel,
            GetProjectViewModel getProjectViewModel,
            ShareProjectViewModel shareProjectViewModel,
            ShareProjectController shareProjectController
            ) {
        this.viewManagerModel = viewManagerModel;
        this.getProjectViewModel = getProjectViewModel;
        this.shareProjectViewModel = shareProjectViewModel;
        this.shareProjectController = shareProjectController;
        this.shareProjectViewModel.addPropertyChangeListener(this);

        this.title = new JLabel("Share Project Screen");

        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel otherUserNamePanel = new LabelTextPanel(
                new JLabel(shareProjectViewModel.OTHER_USERNAME_FIELD),
                otherUserNameTextField
        );

        JPanel buttonsPanel = new JPanel();
        shareButton = new JButton(ShareProjectViewModel.SHARE_BUTTON_LABEL);
        buttonsPanel.add(shareButton);

        cancelButton = new JButton(ShareProjectViewModel.CANCEL_BUTTON_LABEL);
        buttonsPanel.add(cancelButton);

        shareButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               if (e.getSource().equals(shareButton)) {
                   String otherUname = otherUserNameTextField.getText();
                   int res = JOptionPane.showConfirmDialog(
                           ShareProjectView.this,
                           String.format("Are you sure you want to share this project with \"%s\"?", otherUname)
                   );
                   if (res == JOptionPane.YES_OPTION) {
                       shareProjectController.execute(
                               shareProjectViewModel.getState().getProjectId(),
                               shareProjectViewModel.getState().getUserId(),
                               otherUname
                       );
                       JOptionPane.showConfirmDialog(
                               ShareProjectView.this,
                               String.format("Project successfully shared with \"%s\"", otherUname)
                       );
                   }
               }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(cancelButton)) {
                    shareProjectViewModel.setState(new ShareProjectState());
                    shareProjectViewModel.firePropertyChanged();
                    viewManagerModel.setActiveView(getProjectViewModel.getViewName());
                    viewManagerModel.firePropertyChanged();
                }
            }
        });

         otherUserNameTextField.addKeyListener(new KeyListener() {
             @Override
             public void keyTyped(KeyEvent e) {
                 shareProjectViewModel.getState()
                         .setOtherUserName(otherUserNameTextField.getText() + e.getKeyChar());
             }
             @Override
             public void keyPressed(KeyEvent e) {}
             @Override
             public void keyReleased(KeyEvent e) {}
         });

         this.add(title);
         this.add(otherUserNamePanel);
         this.add(buttonsPanel);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showConfirmDialog(
                this, "share project action performed, not implemented");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        ShareProjectState state = (ShareProjectState) evt.getNewValue();

        if (state.getOtherUserName().isEmpty()) {
            JOptionPane.showConfirmDialog(
                    this, "other user name is empty, please try again");
            clearOtherUserNameTextField();
        } else {
            otherUserNameTextField.setText(state.getOtherUserName());
        }
    }

    private void clearOtherUserNameTextField() {
        otherUserNameTextField.setText("");
    }
}
