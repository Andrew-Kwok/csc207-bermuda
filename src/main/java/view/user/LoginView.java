package view.user;

import interface_adapter.user.loggedin.LoggedInState;
import interface_adapter.user.loggedin.LoggedInViewModel;
import interface_adapter.user.login.LoginController;
import interface_adapter.user.login.LoginState;
import interface_adapter.user.login.LoginViewModel;
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

import static constant.ViewConstant.LOGIN_VIEW_NAME;
import static constant.ViewConstant.SIGNUP_VIEW_NAME;

public class LoginView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = LOGIN_VIEW_NAME;
    private final LoginViewModel loginViewModel;
    private final LoggedInViewModel loggedInUserViewModel;
    private final ViewManagerModel viewManagerModel;

    final JTextField usernameInputField = new JTextField(15);
    private final JLabel usernameErrorField = new JLabel();

    final JPasswordField passwordInputField = new JPasswordField(15);
    private final JLabel passwordErrorField = new JLabel();

    final JButton logIn;
    final JButton signUp;
    final JButton exit;
    private final LoginController loginController;

    public LoginView(LoginViewModel loginViewModel, LoginController controller,
                     LoggedInViewModel loggedInUserViewModel, ViewManagerModel viewManagerModel) {

        this.loginController = controller;
        this.loginViewModel = loginViewModel;
        this.loggedInUserViewModel = loggedInUserViewModel;
        this.viewManagerModel = viewManagerModel;

        this.loginViewModel.addPropertyChangeListener(this);
        this.loggedInUserViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Login Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel usernameInfo = new LabelTextPanel(
                new JLabel("Username"), usernameInputField);
        LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel("Password"), passwordInputField);

        JPanel buttons = new JPanel();
        logIn = new JButton(loginViewModel.LOGIN_BUTTON_LABEL);
        buttons.add(logIn);
        signUp = new JButton(loginViewModel.SIGNUP_BUTTON_LABEL);
        buttons.add(signUp);
        exit = new JButton(loginViewModel.EXIT_BUTTON_LABEL);
        buttons.add(exit);

        logIn.addActionListener(                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(logIn)) {
                            LoginState currentState = loginViewModel.getState();
                            currentState.setUsername(usernameInputField.getText());
                            currentState.setPassword(passwordInputField.getText());
                            loginViewModel.setState(currentState);
                            loginController.execute(
                                    currentState.getUsername(),
                                    currentState.getPassword()
                            );
                            loginViewModel.setState(new LoginState());
                        }
                    }
                }
        );

        signUp.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(signUp)) {
                            viewManagerModel.setActiveView(SIGNUP_VIEW_NAME);
                            viewManagerModel.firePropertyChanged();
                        }
                    }
                }
        );

//        comment the following line to when doing testing, so it won't exit the program
        exit.addActionListener(e -> {
                    System.exit(0);
                }
        );
        usernameInputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                LoginState currentState = loginViewModel.getState();
                currentState.setUsername(usernameInputField.getText() + e.getKeyChar());
                loginViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        passwordInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        LoginState currentState = loginViewModel.getState();
                        currentState.setPassword(passwordInputField.getText() + e.getKeyChar());
                        loginViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });

        this.add(title);
        this.add(usernameInfo);
        this.add(usernameErrorField);
        this.add(passwordInfo);
        this.add(passwordErrorField);
        this.add(buttons);
    }

    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // log in with error
        if (evt.getSource().equals(loginViewModel)) {
            LoginState loginstate = (LoginState) evt.getNewValue();
            if (loginstate.getUsernameError() != null) {
                JOptionPane.showMessageDialog(this, loginstate.getUsernameError());
                clearUserNameField();
                clearPasswordField();
                loginstate.setUsernameError(null);
                return;
            }
            if (loginstate.getPasswordError() != null) {
                JOptionPane.showMessageDialog(this, loginstate.getPasswordError());
                clearPasswordField();
                loginstate.setPasswordError(null);
                return;
            }
            if (!loginstate.getUsername().equals("")) {
                setUsernameField(loginstate);
            }
        }

        // logout from loggedinUserView
        if (evt.getSource().equals(loggedInUserViewModel)) {
            LoggedInState loggedInstate = (LoggedInState) evt.getNewValue();
            LoginState loginState = loginViewModel.getState();
            if (!loggedInstate.isLoggedIn()) {
                loginState.setUsername(loggedInstate.getUser().getUsername());
                setUsernameField(loginState);
                clearPasswordField();
            }
        }
    }

    private void setUsernameField(LoginState state) {
        usernameInputField.setText(state.getUsername());
    }

    private void clearUserNameField() {
        usernameInputField.setText("");
    }

    private void clearPasswordField() {
        passwordInputField.setText("");
    }

    public JTextField getUsernameInputField() {
        return usernameInputField;
    }

    public JPasswordField getPasswordInputField() {
        return passwordInputField;
    }

    public JButton getLogInButton() {
        return logIn;
    }

    public JButton getSignUpButton() {
        return signUp;
    }

    public JButton getLoginButton() {
        return logIn;
    }

    public JButton getExitButton() {
        return exit;
    }
}