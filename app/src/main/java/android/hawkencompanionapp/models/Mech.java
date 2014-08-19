/*
 * Copyright (c) 2014 "Hawken Companion App"
 *
 * This file is part of Hawken Companion App.
 *
 * Hawken Companion App is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses.
 */

package android.hawkencompanionapp.models;

/**
 * Created by Phillip Adam Nash on 2014.
 */
public class Mech {
    private String mMechName;
    private String mMechDescription;
    private String mMechUrl;
    private String mMechImgUrl;
    private String mMechPrimaryWeaponName;
    private String mMechPrimaryWeaponImgName;
    private String mMechSecondaryWeaponName;
    private String mMechSecondaryWeaponImgUrl;
    private String mMechAbility;

    public String getMechImgUrl() {
        return mMechImgUrl;
    }

    public void setMechImgUrl(String mechImgUrl) {
        this.mMechImgUrl = mechImgUrl;
    }

    public String getMechName() {
        return mMechName;
    }

    public void setMechName(String mechName) {
        this.mMechName = mechName;
    }

    public String getMechDescription() {
        return mMechDescription;
    }

    public void setMechDescription(String mechDescription) {
        this.mMechDescription = mechDescription;
    }

    public String getMechUrl() {
        return mMechUrl;
    }

    public void setMechUrl(String mechUrl) {
        this.mMechUrl = mechUrl;
    }

    public String getMechPrimaryWeaponName() {
        return mMechPrimaryWeaponName;
    }

    public void setMechPrimaryWeaponName(String mechPrimaryWeaponName) {
        this.mMechPrimaryWeaponName = mechPrimaryWeaponName;
    }

    public String getMechPrimaryWeaponImgName() {
        return mMechPrimaryWeaponImgName;
    }

    public void setMechPrimaryWeaponImgName(String mechPrimaryWeaponImgName) {
        this.mMechPrimaryWeaponImgName = mechPrimaryWeaponImgName;
    }

    public String getMechSecondaryWeaponName() {
        return mMechSecondaryWeaponName;
    }

    public void setMechSecondaryWeaponName(String mechSecondaryWeaponName) {
        this.mMechSecondaryWeaponName = mechSecondaryWeaponName;
    }

    public String getMechSecondaryWeaponImgUrl() {
        return mMechSecondaryWeaponImgUrl;
    }

    public void setMechSecondaryWeaponImgUrl(String mechSecondaryWeaponImgUrl) {
        this.mMechSecondaryWeaponImgUrl = mechSecondaryWeaponImgUrl;
    }

    public String getMechSpecialAbility() {
        return mMechAbility;
    }

    public void setMechAbility(String mechAbility) {
        this.mMechAbility = mechAbility;
    }
}
