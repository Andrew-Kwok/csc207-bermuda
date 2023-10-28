package domains.project.entity;

public class Project {

    // Refer to the API documentation for the meaning of these fields.
    private final String projectID;
    private String projectName;
    private String projectStatus = "IPR";

    public Project(String projectID, String projectName) {
        this.projectID = projectID;
        this.projectName = projectName;
    }

    public Project(String projectID, String projectName, String projectStatus) {
        this.projectID = projectID;
        this.projectName = projectName;
        this.projectStatus = projectStatus;
    }


    public static ProjectBuilder builder() {
        return new ProjectBuilder();
    }

    public static class ProjectBuilder {
        private String projectID;
        private String projectName;
        private String projectStatus = "IPR";

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

        public ProjectBuilder projectStatus(String projectStatus) {
            this.projectStatus = projectStatus;
            return this;
        }

        public Project build() {
            return new Project(projectID, projectName, projectStatus);
        }
    }



    public String getProjectID() {
        return projectID;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getProjectStatus() {
        return projectStatus;
    }


    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setProjectStatus(String projectStatus) {
        this.projectStatus = projectStatus;
    }

    @Override
    public String toString() {
        return ("""
                [Project ID: %s]
                \t Project Name: %s
                \t Project Status: %s
                """).
                formatted(projectID, projectName, projectStatus);
    }
}
