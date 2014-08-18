package android.hawkencompanionapp.models;
/**
 *   Copyright (c) 2014 "Hawken Companion App"
 *
 *   This file is part of Hawken Companion App.
 *
 *   Hawken Companion App is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

import android.hawkencompanionapp.logger.Logger;
import android.os.Parcel;
import android.os.Parcelable;
import org.apache.http.cookie.Cookie;
import java.util.List;

/**
 * Created by Phillip Adam Nash on 08/08/2014.
 *
 * A model to represent a credentials entered by the user.
 */
public final class UserLoginSession implements Parcelable {
    private String mEmailAddress; //This isn't serialized
    private String mUserPassword; //This isn't serialized
    private boolean mIsUserValid;
    private String mPhpSessionId;
    private String mPhpTrackingId;
    private String mPhpAccessId;

    public UserLoginSession(String emailAddress, String userPassword) {
        mEmailAddress = emailAddress;
        mUserPassword = userPassword;
    }

    public UserLoginSession(Parcel in) {
        readFromParcel(in);
    }

    /**
     * This method sets information (in the model) from the user cookie
     * obtained from the server.
     *
     * @param cookies The cookies obtain from the server
     * */
    public void setUserSession(List<Cookie> cookies) {
        mPhpSessionId = cookies.get(0).getValue();
        mPhpTrackingId = cookies.get(1).getValue();

        //Only a valid user can access this
        if (cookies.size() > 2) {
            mPhpAccessId = cookies.get(2).getValue();
            Logger.debug(this,mPhpAccessId);
        }
    }

    /**
     * This returns a PHP session id associated with a user.
     *
     * @return The PHP session id
     * */
    public String getPhpSessionId() {
        return mPhpSessionId;
    }

    /**
     * This returns a PHP tracking id associated with a user.
     *
     * @return The PHP tracking id
     * */
    public String getPhpTrackingId() {
        return mPhpTrackingId;
    }

    /**
     * This returns a PHP addess id associated with a user.
     *
     * @return The PHP addess id
     * */
    public String getPhpAccessId() {
        return mPhpAccessId;
    }

    /**
     * This returns the user's email address.
     *
     * @return The user's entered email address.
     * */
    public String getEmailAddress() {
        return mEmailAddress;
    }

    /**
     * This returns the user's password.
     *
     * @return The user's entered password.
     * */
    public String getUserPassword() {
        return mUserPassword;
    }

    /**
     * This is set if the credentials that are entered are valid.
     * */
    public void setUserIsValid() {
        mIsUserValid = true;
    }

    /**
     * This returns the true if the credentials entered are valid, false otherwise.
     *
     * @return True if the credentials are valid, false otherwise.
     * */
    public boolean isUserValid() {
        return mIsUserValid;
    }

    public String toString() {
        return null;
    }

    public int describeContents(){
        return 0;
    }

    public static final Parcelable.Creator<UserLoginSession> CREATOR
            = new Parcelable.Creator<UserLoginSession>() {
        public UserLoginSession createFromParcel(Parcel in) {
            return new UserLoginSession(in);
        }

        public UserLoginSession[] newArray(int size) {
            return new UserLoginSession[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        boolean[] b = new boolean[]{mIsUserValid};

        dest.writeString(mPhpSessionId);
        dest.writeString(mPhpTrackingId);
        dest.writeString(mPhpAccessId);
        dest.writeBooleanArray(b);
    }

    private void readFromParcel(Parcel in) {
        boolean[] b = new boolean[]{mIsUserValid};
        mPhpSessionId = in.readString();
        mPhpTrackingId = in.readString();
        mPhpAccessId = in.readString();
        in.readBooleanArray(b);
        mIsUserValid = b[0];
    }

    /**
     * This returns a valid session cookie valid for an entire user session. This cookie
     * is only valid for a certain amount of time (determined by the server). Once this
     * has expired, the user will have to re login and obtain a new session cookie.
     *
     * @return A unique session cookie.
     * */
    public String getSessionCookie() {
        return "PHPSESSID=" + getPhpSessionId() + ";" +
                "phtracking=" + getPhpTrackingId() + ";" +
                "phaccess=" + getPhpAccessId() + ";";
    }
}
