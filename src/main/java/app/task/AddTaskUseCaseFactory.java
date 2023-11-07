package app.task;

import domains.task.entity.NewTaskFactory;
import domains.task.entity.TaskFactory;
import domains.task.use_case.add_task.AddTaskDataAccessInterface;
import domains.task.use_case.add_task.AddTaskInputBoundary;
import domains.task.use_case.add_task.AddTaskInteractor;
import domains.task.use_case.add_task.AddTaskOutputBoundary;
import domains.user.entity.NewUserFactory;
import domains.user.entity.UserFactory;
import domains.user.use_case.login.LoginInputBoundary;
import domains.user.use_case.login.LoginInteractor;
import domains.user.use_case.login.LoginOutputBoundary;
import interface_adapter.task.add_task.AddTaskController;
import interface_adapter.task.add_task.AddTaskPresenter;
import interface_adapter.task.add_task.AddTaskViewModel;
import interface_adapter.user.login.LoginController;
import interface_adapter.user.login.LoginPresenter;
import interface_adapter.view_model.ViewManagerModel;
import view.AddTaskView;

import javax.swing.*;
import java.io.IOException;

public class AddTaskUseCaseFactory {
    private AddTaskUseCaseFactory(){}

    public static AddTaskView create(
            ViewManagerModel viewManagerModel,
            AddTaskViewModel addTaskViewModel,
            AddTaskDataAccessInterface addTaskDAO) {

        try {
            AddTaskController addTaskController = createAddTaskUseCase(viewManagerModel, addTaskViewModel, addTaskDAO);
            return new AddTaskView(addTaskViewModel, addTaskController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "AddTask View failed");
        }

        return null;
    }

    private static AddTaskController createAddTaskUseCase(ViewManagerModel viewManagerModel,
                                                          AddTaskViewModel addTaskViewModel,
                                                          AddTaskDataAccessInterface addTaskDAO) throws IOException {

        AddTaskOutputBoundary addTaskOutputBoundary = new AddTaskPresenter(viewManagerModel, addTaskViewModel, addTaskDAO);

        TaskFactory taskFactory = new NewTaskFactory();

        AddTaskInputBoundary addTaskInteractor = new AddTaskInteractor(
                addTaskDAO, addTaskOutputBoundary);

        return new AddTaskController(addTaskInteractor);
    }
}
