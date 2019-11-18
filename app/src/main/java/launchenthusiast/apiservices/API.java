package launchenthusiast.apiservices;

public enum API {
    LAUNCH_ENTHUSIAST("https://launchenthusiastapi.azurewebsites.net/api/v1/"),
    LAUNCH_LIBRARY("https://launchlibrary.net/1.4/");

    private String endpoint;
    API(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getEndpoint() {
        return endpoint;
    }
}
