package app;

import entity.Project;

public class BermudaTrial {
    public static void main(String[] args) {
        Project myProject = Project.builder().projectName("myProject01").
                projectID("P01").build();
        System.out.println(myProject);
    }
}
