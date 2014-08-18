package android.hawkencompanionapp.models;

/**
 * Created by Phillip Adam Nash on 14/08/2014.
 */
public final class UserForgottenPassword {
    private final String mEmailAddress;
    private boolean mEmailAccountValid;

    public UserForgottenPassword(String emailAddress) {
        mEmailAddress = emailAddress;
    }

    public String getEmailAddress() {
        return mEmailAddress;
    }

    public boolean accountIsValid() {
        return mEmailAccountValid;
    }

    public void setAccountValid(boolean valid) {
        mEmailAccountValid = valid;
    }
}
