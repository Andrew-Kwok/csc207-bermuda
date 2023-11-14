package view.project;

import interface_adapter.project.share_project.ShareProjectController;
import interface_adapter.project.share_project.ShareProjectViewModel;
import interface_adapter.project.share_project.ShareProjectState;
import view.common.LabelTextPanel;

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

    public final String viewName = "share_project";
    private final ShareProjectViewModel shareProjectViewModel;

    final JTextField otherUserNameTextField = new JTextField(20);

    final JButton shareButton;
    final JButton cancelButton;

    private final ShareProjectController shareProjectController;

    public ShareProjectView(
            ShareProjectViewModel shareProjectViewModel,
            ShareProjectController shareProjectController
            ) {
        this.shareProjectViewModel = shareProjectViewModel;
        this.shareProjectController = shareProjectController;
        this.shareProjectViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Share Project Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel otherUserNamePanel = new LabelTextPanel(
                new JLabel("Other user"),
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
                   shareProjectController.execute(
                           shareProjectViewModel.getState().getProjectId(),
                           shareProjectViewModel.getState().getUserId(),
                           otherUserNameTextField.getText()
                   );
               }
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(cancelButton)) {
                    shareProjectViewModel.setState(new ShareProjectState());
                    shareProjectViewModel.firePropertyChanged();
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
                                                  }

         );
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
