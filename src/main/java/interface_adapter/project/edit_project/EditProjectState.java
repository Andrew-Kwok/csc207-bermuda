package interface_adapter.project.edit_project;

public class EditProjectState {
    private String projectID = "";
    private String projectName = "";
    private String editProjectError = null;
    private boolean initial = true;

    public EditProjectState(EditProjectState copy) {
        this.projectID = copy.projectID;
        this.projectName = copy.projectName;
        this.editProjectError = copy.editProjectError;
        this.initial = copy.initial;
    }

    public EditProjectState() {
    }

    public String getProjectID() {return projectID;}
    public String getProjectName() {return projectName;}
    public String getEditProjectError() {return editProjectError;}
    public boolean isInitial() {return initial;}

    public void setProjectID(String projectID) {this.projectID = projectID;}
    public void setProjectName(String projectName) {this.projectName = projectName;}
    public void setEditProjectError(String editProjectError) {this.editProjectError = editProjectError;}
    public void setInitial(boolean initial) {this.initial = initial;}

    @Override
    public String toString() {
        return "EditProjectState{" +
                ", projectID='" + projectID + '\'' +
                ", projectName='" + projectName + '\'' +
                ", editProjectError='" + editProjectError + '\'' +
                ", initial=" + initial +
                '}';
    }
}
