package view.task;

import interface_adapter.task.add_task.AddTaskController;
import interface_adapter.task.add_task.AddTaskState;
import interface_adapter.task.add_task.AddTaskViewModel;
import interface_adapter.task.get_task.GetTaskController;
import interface_adapter.task.get_task.GetTaskViewModel;
import interface_adapter.view_model.ViewManagerModel;
import view.common.LabelTextPanel;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class AddTaskView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "AddTaskView";
    private final AddTaskViewModel addTaskViewModel;
    private final GetTaskViewModel getTaskViewModel;
    private final ViewManagerModel viewManagerModel;
    private final JTextField taskNameInputField = new JTextField(50);
    private final JTextField taskContentInputField = new JTextField(50);
    private final JTextField deadlineInputField = new JTextField(50);
    private final AddTaskController addTaskController;
    private final GetTaskController getTaskController;
    private final JButton addTaskButton;
    private final JButton cancelButton;
    private JFormattedTextField formatText;

    public AddTaskView(AddTaskViewModel addTaskViewModel, AddTaskController addTaskController,
                       GetTaskViewModel getTaskViewModel, GetTaskController getTaskController, ViewManagerModel viewManagerModel) {
        this.addTaskViewModel = addTaskViewModel;
        this.addTaskController = addTaskController;
        this.getTaskViewModel = getTaskViewModel;
        this.getTaskController = getTaskController;
        this.viewManagerModel = viewManagerModel;

        addTaskViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(AddTaskViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel taskNamePanel = new LabelTextPanel(new JLabel(AddTaskViewModel.TASK_NAME_LABEL), taskNameInputField);
        LabelTextPanel taskContentPanel = new LabelTextPanel(new JLabel(AddTaskViewModel.TASK_CONTENT_LABEL), taskContentInputField);
        LabelTextPanel taskDeadlinePanel = new LabelTextPanel(new JLabel(AddTaskViewModel.DEADLINE_LABEL), deadlineInputField);

        JPanel buttons = new JPanel();
        addTaskButton = new JButton(addTaskViewModel.ADD_TASK_BUTTON_LABEL);
        cancelButton = new JButton(addTaskViewModel.CANCEL_BUTTON_LABEL);
        buttons.add(addTaskButton);
        buttons.add(cancelButton);

        addTaskButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(addTaskButton)) {
                            AddTaskState addTaskState = addTaskViewModel.getState();
                            addTaskController.execute(
                                    addTaskState.getTaskName(),
                                    addTaskState.getTaskContent(),
                                    addTaskState.getDeadline(),
                                    addTaskState.getProjectID()
                            );
                            clearInputFields();
                        }
                    }
                }
        );

        cancelButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(cancelButton)) {
                            clearInputFields();
                        }
                    }
                }
        );

        taskNameInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        AddTaskState addTaskState = addTaskViewModel.getState();
                        String text = taskNameInputField.getText() + e.getKeyChar();
                        addTaskState.setTaskName(text);
                        addTaskViewModel.setState(addTaskState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );

        taskContentInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        AddTaskState addTaskState = addTaskViewModel.getState();
                        String text = taskContentInputField.getText() + e.getKeyChar();
                        addTaskState.setTaskContent(text);
                        addTaskViewModel.setState(addTaskState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );

        deadlineInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        AddTaskState addTaskState = addTaskViewModel.getState();
                        //String text = deadlineInputField.getText() + e.getKeyChar();

                        Date date = new Date();
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                        String dateString = formatter.format(date);
                        formatText = new JFormattedTextField(createFormatter("####-##-## ##:##:##"));
                        formatText.setColumns(20);
                        formatText.setText(dateString);

                        setLayout(new BorderLayout());
                        add(new JLabel("Enter Date and Time in YYYY-MM-DD HH:MM:SS format"), BorderLayout.NORTH);
                        add(formatText, BorderLayout.CENTER);

                        LocalDateTime localDate = convertToLocalDateTimeViaInstant(date);
                        addTaskState.setDeadline(localDate);
                        addTaskViewModel.setState(addTaskState);
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
        this.add(taskNamePanel);
        this.add(taskContentPanel);
        this.add(taskDeadlinePanel);
        this.add(buttons);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showConfirmDialog(this, "Not implemented yet.");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        AddTaskState addTaskState = (AddTaskState) evt.getNewValue();
        if (addTaskState.getAddTaskError() != null) {
            JOptionPane.showMessageDialog(this, addTaskState.getAddTaskError());
        } else if (addTaskState.getTaskID() != null) {
            // On success, switch to the get permission view.
            JOptionPane.showMessageDialog(this, "Task created with id " + addTaskState.getTaskID() + ".");
            addTaskState.setTaskID(null);
        }
    }

    private void clearInputFields() {
        taskNameInputField.setText("");
        taskContentInputField.setText("");
        deadlineInputField.setText("");
        taskNameInputField.setText("");
    }

    public LocalDateTime convertToLocalDateTimeViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    private MaskFormatter createFormatter(String s) {
        MaskFormatter formatter = null;
        try {
            formatter = new MaskFormatter(s);
        } catch (java.text.ParseException exc) {
            System.err.println("formatter is bad: " + exc.getMessage());
            System.exit(-1);
        }
        return formatter;
    }
}
