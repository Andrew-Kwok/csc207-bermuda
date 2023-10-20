package entity;

public class Project {

    // Refer to the API documentation for the meaning of these fields.
    private final String projectID;
    private final String projectName;

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

    @Override
    public String toString() {
        return "Project ID: %s\nProject name: %s\n".formatted(projectID, projectName);
    }
}
