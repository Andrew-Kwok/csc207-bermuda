package domains.share_project;

import java.util.*;

import domains.permission.entity.Permission;
import domains.user.entity.User;

public interface ShareProjectDataAccessInterface {
    void createPermission(Permission newPermission) throws Exception;
}
