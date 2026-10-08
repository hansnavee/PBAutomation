package PageObjects.models;

public enum Application {

     PROJECT_BUILDER("PROJECT BUILDER");

    private final String appName;  // field

    // constructor
    Application(String appName) {
        this.appName = appName;
    }

    // getter
    public String getApplicationName() {
        return appName;
    }
}
