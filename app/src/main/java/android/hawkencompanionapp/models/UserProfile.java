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
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.Locale;

/**
 * Created by Phillip Adam Nash on 11/08/2014.
 *
 * This class represents the user's profile page.
 */
public final class UserProfile {
    private Elements mAccountBodyElems;
    private Elements mHawkenCreditsElems;

    public void setHtmlData(String htmlData) {
        final Document d = Jsoup.parse(htmlData);
        //The user's information resides in this body.
        mAccountBodyElems = d.select("div#account_body");
        mHawkenCreditsElems = d.select("#credits-and-stuff");
    }

    /**
     * This method returns the user's callsign (their gamer ID).
     *
     * @return The user's callsign.
     * */
    public String getCallsign() {
        final Elements elem = mAccountBodyElems.select("div#account_manage_callsign");
        try {
            return elem.get(0).text().split(" ")[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            Logger.error(this,"Unable to obtain user's callsign: " + e.getMessage());
        }
        return null;
    }

    /**
     * This method returns the user's email address.
     *
     * @return The user's email address.
     * */
    public String getEmailAddress() {
        try {
            final Elements elem =
                    mAccountBodyElems.select("div#account-basics_email .form_input").get(0).getElementsByTag("input");
            return elem.attr("current");
        } catch (ArrayIndexOutOfBoundsException e) {
            Logger.error(this,"Unable to obtain user's email address: " + e.getMessage());
        }
        return null;
    }

    /**
     * This method returns the user's first name.
     *
     * @return The user's first name.
     * */
    public String getFirstName() {
        try {
            final Elements elem =
                    mAccountBodyElems.select("#account_form_firstname");
            return elem.attr("value");
        } catch (ArrayIndexOutOfBoundsException e) {
            Logger.error(this,"Unable to obtain user's first name: " + e.getMessage());
        }
        return null;
    }

    /**
     * This method returns the user's last name.
     *
     * @return The user's last name.
     * */
    public String getLastName() {
        try {
            final Elements elem =
                    mAccountBodyElems.select("#account_form_lastname");
            return elem.attr("value");
        } catch (ArrayIndexOutOfBoundsException e) {
            Logger.error(this,"Unable to obtain user's email address: " + e.getMessage());
        }
        return null;
    }

    /**
     * This method returns the user's zip / post code.
     *
     * @return The user's zip / post code.
     * */
    public String getPostalCode() {
            final Elements form =
                    mAccountBodyElems.select("#account_form_zip");
            return form.val();
    }

    /**
     * This method returns the user's country.
     *
     * @return The user's country.
     * */
    public String getCountry() {
        try {
            final Element formInput = mAccountBodyElems.select("#row_country .form_input").first();
            final String countryCode = formInput.child(0).attr("default");
            final Locale l = new Locale("", countryCode);
            return l.getDisplayCountry();
        } catch (ArrayIndexOutOfBoundsException e) {
            Logger.error(this,"Unable to obtain user's country code: " + e.getMessage());
        }
        return null;
    }

    /**
     * This returns the user's gender.
     *
     * @return The user's gender.
     * */
    public String getGender() {
        try {
            final Element formInput = mAccountBodyElems.select("#row_gender > div:nth-child(2)").first();
            final Element e = formInput.child(0);
            final String gender = e.attr("default");

            if (gender.equals("F")) {
                return "Female";
            } else if (gender.equals("M")) {
                return "Male";
            } else {
                return "Not Telling";
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            Logger.error(this,"Unable to obtain user's gender: " + e.getMessage());
        }
        return null;
    }

    /**
     * This returns the user's date of birth in this format (MM/DD/YYYY)
     *
     * @return An array containing the user's date of birth.
     * */
    public String[] getDateOfBirth() {
        final String[] dob = new String[3];

        try {
            final Element formInput = mAccountBodyElems.select("#row_birthdate > div:nth-child(2)").first();
            final Elements selectElems = formInput.getElementsByTag("select");
            //Obtain the individual parts of the D.O.B
            for (int i = 0; i < selectElems.size(); i++) {
                final Element e = selectElems.get(i);
                final String attr = e.attr("default");
                dob[i] = attr;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            Logger.error(this,"Unable to obtain user's data of birth: " + e.getMessage());
        }
        return dob;
    }

    public String getLanguage() {
        try {
            final Element formInput = mAccountBodyElems.select("#row_language > div:nth-child(2)").first();
            final Element e = formInput.child(0);
            final String language = e.attr("default");
            final Locale l = new Locale(language, getCountry());

            return l.getDisplayLanguage();
        } catch (ArrayIndexOutOfBoundsException e) {
            Logger.error(this,"Unable to obtain user's language: " + e.getMessage());
        }
        return null;
    }

    public String getHawkenCredits() {
        try {
            final Elements labelElem = mHawkenCreditsElems.select("#sn_hc_label");
            return labelElem.text();
        } catch (ArrayIndexOutOfBoundsException e) {
            Logger.error(this,"Unable to obtain user's hawken credits: " + e.getMessage());
        }
        return null;
    }

    public String getMeteorCredits() {
        try {
            final Elements labelElem = mHawkenCreditsElems.select("#sn_mc_label");
            return labelElem.text();
        } catch (ArrayIndexOutOfBoundsException e) {
            Logger.error(this,"Unable to obtain user's meteor credits: " + e.getMessage());
        }
        return null;
    }
}
