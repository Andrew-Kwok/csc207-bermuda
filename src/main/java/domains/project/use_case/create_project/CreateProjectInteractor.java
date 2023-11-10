//package domains.project.use_case.create_project;
//import app.project.CreateProjectUseCaseFactory;
//
//public class CreateProjectInteractor implements CreateProjectInputBoundary{
//
//    final CreateProjectDataAccessInterface createProjectDataAccessObject;
//    final CreateProjectOutputBoundary createProjectPresenter;
//
//    public CreaterProjectInteractor(CreateProjectDataAccessInterface createProjectDataAccessObject,
//                             CreaterProjectOutputBoundary createrProjectOutputBoundary) {
//        this.createProjectDataAccessObject = createProjectDataAccessObject;
//        this.createProjectPresenter = createrProjectOutputBoundary;
//    }
//
//
//    @Override
//    public void execute(CreateProjectInputData createProjectInputData) {
//        String projectID = this.createProjectDataAccessObject.createProject();
//        CreateProjectOutputData createProjectOutputData = new CreateProjectOutputData(projectID);
//        this.createProjectPresenter.printProjectID(createProjectOutputData);
//    }
//}
