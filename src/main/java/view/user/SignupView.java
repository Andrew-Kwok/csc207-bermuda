package view.user;

import interface_adapter.user.login.LoginController;
import interface_adapter.user.login.LoginState;
import interface_adapter.user.login.LoginViewModel;
import interface_adapter.user.signup.SignupController;

import interface_adapter.user.signup.SignupState;
import interface_adapter.user.signup.SignupViewModel;
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

public class SignupView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = SIGNUP_VIEW_NAME;
    private final SignupViewModel signupViewModel;
    private final LoginViewModel loginViewModel;
    private final ViewManagerModel viewManagerModel;
    private final JTextField usernameInputField = new JTextField(15);
    private final JPasswordField passwordInputField = new JPasswordField(15);
    private final JPasswordField repeatPasswordInputField = new JPasswordField(15);
    private final SignupController signupController;
    private final LoginController loginController;
    private final JButton signUp;
    private final JButton cancel;

    public SignupView(SignupController signupController, SignupViewModel signupViewModel,
                      LoginController loginController, LoginViewModel loginViewModel,
                      ViewManagerModel viewManagerModel) {

        this.signupController = signupController;
        this.signupViewModel = signupViewModel;

        this.loginController = loginController;
        this.loginViewModel = loginViewModel;

        this.viewManagerModel = viewManagerModel;

        signupViewModel.addPropertyChangeListener(this);
        //loginViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(SignupViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel usernameInfo = new LabelTextPanel(
                new JLabel(SignupViewModel.USERNAME_LABEL), usernameInputField);
        LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel(SignupViewModel.PASSWORD_LABEL), passwordInputField);
        LabelTextPanel repeatPasswordInfo = new LabelTextPanel(
                new JLabel(SignupViewModel.REPEAT_PASSWORD_LABEL), repeatPasswordInputField);

        JPanel buttons = new JPanel();
        signUp = new JButton(SignupViewModel.SIGNUP_BUTTON_LABEL);
        buttons.add(signUp);
        cancel = new JButton(SignupViewModel.CANCEL_BUTTON_LABEL);
        buttons.add(cancel);



        signUp.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(signUp)) {
                            SignupState currentState = signupViewModel.getState();
                            signupController.execute(
                                    currentState.getUsername(),
                                    currentState.getPassword(),
                                    currentState.getRepeatPassword());
                            clearUserNameField();
                            clearPasswordField();
                            clearRepeatedPasswordField();
                        }
                    }
                }
        );

        cancel.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(cancel)) {
                            viewManagerModel.setActiveView(LOGIN_VIEW_NAME);
                            viewManagerModel.firePropertyChanged();

                            clearUserNameField();
                            clearPasswordField();
                            clearRepeatedPasswordField();
                        }
                    }
                }
        );


        // This makes a new KeyListener implementing class, instantiates it, and
        // makes it listen to keystrokes in the usernameInputField.
        //
        // Notice how it has access to instance variables in the enclosing class!
        usernameInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        SignupState signupState = signupViewModel.getState();
                        LoginState loginState = loginViewModel.getState();
                        String text = usernameInputField.getText() + e.getKeyChar();
                        signupState.setUsername(text);
                        signupViewModel.setState(signupState);

                        loginState.setUsername(text);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });

        passwordInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        SignupState currentState = signupViewModel.getState();
                        currentState.setPassword(passwordInputField.getText() + e.getKeyChar());
                        signupViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );

        repeatPasswordInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        SignupState currentState = signupViewModel.getState();
                        currentState.setRepeatPassword(repeatPasswordInputField.getText() + e.getKeyChar());
                        signupViewModel.setState(currentState); // Hmm, is this necessary?
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
        this.add(usernameInfo);
        this.add(passwordInfo);
        this.add(repeatPasswordInfo);
        this.add(buttons);
    }

    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {
        JOptionPane.showConfirmDialog(this, "Cancel not implemented yet.");
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        SignupState state = (SignupState) evt.getNewValue();
        if (state.getUsernameError() != null) {
            JOptionPane.showMessageDialog(this, state.getUsernameError());
        } else if (state.getUsername() != null) {
            JOptionPane.showMessageDialog(this, "User created with username " + state.getUsername() + ".");
            state.setUsername(null);
        }
    }

    private void clearUserNameField() {
        usernameInputField.setText("");
    }

    private void clearPasswordField() {
        passwordInputField.setText("");
    }

    private void clearRepeatedPasswordField() {
        repeatPasswordInputField.setText("");
    }
}