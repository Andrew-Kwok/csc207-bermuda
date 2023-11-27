package view.share_project;

import constant.ViewConstant;
import interface_adapter.project.get_project.GetProjectViewModel;
import interface_adapter.share_project.ShareProjectController;
import interface_adapter.share_project.ShareProjectViewModel;
import interface_adapter.share_project.share_project_page.ShareProjectPageState;
import interface_adapter.share_project.share_project_page.ShareProjectPageViewModel;
import interface_adapter.view_model.ViewManagerModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

/**
 * This class is a View for ShareProjectPageView.
 * It contains the title, the text field for the other username,
 * the share button and the back button.
 *
 * Inherits from JPanel.
 */
public class ShareProjectPageView extends JPanel implements
        ActionListener, PropertyChangeListener {

    public final String viewName = ViewConstant.SHARE_PROJECT_PAGE_VIEW_NAME;

    private final ViewManagerModel viewManagerModel;
    private final GetProjectViewModel getProjectViewModel;
    private final ShareProjectPageViewModel shareProjectPageViewModel;
    private final ShareProjectViewModel shareProjectViewModel;
    private final ShareProjectController shareProjectController;

    final JLabel title;
    final JLabel projectIdLabel;
    final JLabel projectNameLabel;

    final JButton shareButton;
    final JButton backButton;

    DefaultListModel<UserFormat> userListModel = new DefaultListModel<>();
    JList<UserFormat> userList = new JList<>(userListModel);

    private class UserFormat {
        private final String username;
        private final String userId;
        public UserFormat(String username, String userId) {
            this.username = username;
            this.userId = userId;
        }
        public String getUsername() { return this.username; }
        public String getUserId() { return this.userId; }
        @Override
        public String toString() { return String.format("username: %s, userId: %s", username, userId); }
    }

    public ShareProjectPageView(
            ViewManagerModel viewManagerModel,
            GetProjectViewModel getProjectViewModel,
            ShareProjectPageViewModel shareProjectPageViewModel,
            ShareProjectViewModel shareProjectViewModel,
            ShareProjectController shareProjectController
    ) {
        this.viewManagerModel = viewManagerModel;
        this.getProjectViewModel = getProjectViewModel;
        this.shareProjectPageViewModel = shareProjectPageViewModel;
        this.shareProjectViewModel = shareProjectViewModel;
        this.shareProjectController = shareProjectController;

        this.shareProjectPageViewModel.addPropertyChangeListener(this);

        this.title = new JLabel(ShareProjectPageViewModel.TITLE_LABEL);

        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttonsPanel = new JPanel();
        shareButton = new JButton(ShareProjectPageViewModel.SHARE_BUTTON_LABEL);
        buttonsPanel.add(shareButton);

        backButton = new JButton(ShareProjectPageViewModel.BACK_BUTTON_LABEL);
        buttonsPanel.add(backButton);

        shareButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               if (e.getSource().equals(shareButton)) {
                   if (userList.getSelectedValue() == null) {
                       JOptionPane.showMessageDialog(
                               ShareProjectPageView.this,
                               "Please select a user to share with."
                       );
                       return;
                   }

                   String otherUname = userList.getSelectedValue().getUsername();
                   String otherUserId = userList.getSelectedValue().getUserId();
                   int res = JOptionPane.showConfirmDialog(
                           ShareProjectPageView.this,
                           String.format(
                                   "Are you sure you want to share this project with \"%s\"?",
                                   otherUname)
                   );
                   if (res == JOptionPane.YES_OPTION) {
                       shareProjectController.execute(
                               shareProjectPageViewModel.getState().getProjectId(),
                               otherUserId,
                               otherUname
                       );
                   }
                   if (shareProjectViewModel.getState().getErrorMessage() != null) {
                       JOptionPane.showMessageDialog(
                               ShareProjectPageView.this,
                               shareProjectViewModel.getState().getErrorMessage()
                       );
                   } else {
                       JOptionPane.showMessageDialog(
                               ShareProjectPageView.this,
                               String.format(
                                       "Project successfully shared with \"%s\"",
                                       otherUname
                               )
                           );
                       }
                   }
               }
            });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(backButton)) {
                    viewManagerModel.setActiveView(getProjectViewModel.getViewName());
                    viewManagerModel.firePropertyChanged();
                }
            }
        });

         this.projectIdLabel = new JLabel(
                ShareProjectPageViewModel.PROJECT_ID_LABEL + " :");
         this.projectNameLabel = new JLabel(
                ShareProjectPageViewModel.PROJECT_NAME_LABEL + " :");

         this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
         this.add(title);
         this.add(projectIdLabel);
         this.add(projectNameLabel);
         this.add(new JScrollPane(userList));
         this.add(buttonsPanel);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showConfirmDialog(
                this, "share project action performed, not implemented");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        ShareProjectPageState state = (ShareProjectPageState) evt.getNewValue();
        userListModel.clear();
        if (state.getErrorMessage() != null) {
            JOptionPane.showMessageDialog(
                    this, state.getErrorMessage());
            viewManagerModel.setActiveView(getProjectViewModel.getViewName());
            return;
        }

        this.projectIdLabel.setText(
                String.format("%s : %s",
                        ShareProjectPageViewModel.PROJECT_ID_LABEL,
                        shareProjectPageViewModel.getState().getProjectId()
                )
        );
        this.projectNameLabel.setText(
                String.format("%s : %s",
                        ShareProjectPageViewModel.PROJECT_NAME_LABEL,
                        shareProjectPageViewModel.getState().getProjectName()
                )
        );

        if (state.getUsersNameAndId().isEmpty()) {
            JOptionPane.showConfirmDialog(
                    this, "No users to share with.");
        }

        for (List<String> userNameAndId : state.getUsersNameAndId()) {
            String username = userNameAndId.get(0);
            String userId = userNameAndId.get(1);

            UserFormat formatterUser = new UserFormat(username, userId);
            userListModel.addElement(formatterUser);
        }
    }
}
