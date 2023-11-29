package domains.project.entity;

import java.util.Map;

import static constant.ViewConstant.PROJECT_ID;
import static constant.ViewConstant.PROJECT_NAME;

public class Project {

    // Refer to the API documentation for the meaning of these fields.
    private final String projectID;
    private String projectName;

    public Project(String projectID, String projectName) {
        this.projectID = projectID;
        this.projectName = projectName;
    }

    public static ProjectBuilder builder() {
        return new ProjectBuilder();
    }

    public static class ProjectBuilder {
        private String projectID;
        private String projectName;
        ProjectBuilder() {
        }

        public ProjectBuilder projectID(String projectID) {
            this.projectID = projectID;
            return this;
        }

        public ProjectBuilder projectName(String projectName) {
            this.projectName = projectName;
            return this;
        }

        public Project build() {
            return new Project(projectID, projectName);
        }
    }


    public String getProjectID() {
        return projectID;
    }

    public String getProjectName() {
        return projectName;
    }


    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Override
    public String toString() {
        return ("""
                [Project ID: %s]
                \t Project Name: %s
                """).
                formatted(projectID, projectName);
    }

    public Map<String, String> toMap() {
        return Map.of(
                PROJECT_ID, projectID,
                PROJECT_NAME, projectName
        );
    }
}
