package domains.share_project.share_project_page;

import java.util.*;
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
         List<List<String>> usersNameAndId = dataAccessInterface.getUsersNameAndId();

         for (List<String> userNameAndId : usersNameAndId) {
            if (userNameAndId.get(0).equals(input.getUserId())) {
               usersNameAndId.remove(userNameAndId);
               break;
            }
         }

         presenter.prepareSuccessView(new ShareProjectPageOutputData(
                 usersNameAndId, input.getProjectId(), input.getProjectId(), null));
      } catch (Exception e) {
         presenter.prepareFailView(new ShareProjectPageOutputData(
                 null, null, null, 1));
      }
   }
}
