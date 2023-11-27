package domains.project.share_project_page;

import java.util.List;
public class ShareProjectPageInteractor implements ShareProjectPageInputBoundary {
   private final ShareProjectPageOutputBoundary presenter;
   private final ShareProjectPageDataAccessInterface dataAccessInterface;
   public ShareProjectPageInteractor(
           ShareProjectPageOutputBoundary presenter,
           ShareProjectPageDataAccessInterface dataAccessInterface
   ) {
      this.presenter = presenter;
      this.dataAccessInterface = dataAccessInterface;
   }

   public void execute(ShareProjectPageInputData input) {
      try {
         List<List<String>> usersNameAndId = dataAccessInterface.getUsersNameAndId(input.getProjectId());

         presenter.prepareSuccessView(new ShareProjectPageOutputData(
                 usersNameAndId, input.getProjectId(), input.getProjectName(), null));
      } catch (Exception e) {
         presenter.prepareFailView(new ShareProjectPageOutputData(
                 null, null, null, 1));
      }
   }
}
