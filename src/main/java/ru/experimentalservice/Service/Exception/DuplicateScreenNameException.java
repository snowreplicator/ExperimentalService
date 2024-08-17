package ru.experimentalservice.Service.Exception;

public class DuplicateScreenNameException extends BaseException {

    private final String screenName;

    public DuplicateScreenNameException(String screenName) {
        super("ScreenName: " + screenName + " already exists.");
        this.screenName = screenName;
    }

    public String getScreenName() {
        return screenName;
    }

}