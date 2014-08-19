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

import android.hawkencompanionapp.logger.Logger;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * A basic model representing the content based here:
 *
 * http://www.playhawken.com/game-guide/mechs
 *
 * Created by Phillip Adam Nash on 14/08/2014.
 */
public final class MechType {
    private String mMechTitle;
    private String mMechDescription;
    private int mMechStatSpeed,mMechStatSpeedMax;
    private int mMechStatArmor,mMechStatArmorMax;
    private int mMechEnergyCapacity,mMechEnergyCapacityMax;
    private List<Mech> mMechList;
    private Elements mMainContent;
    private String mMechIntroPictureUrl;


    public MechType() {
        mMechList = new ArrayList<Mech>();
    }

    public void setHtmlData(String htmlData) {
        final Document d = Jsoup.parse(htmlData);
        mMainContent = d.select("#main-content");
        obtainMechStats();
        obtainMechTitleAndDescription();
        obtainMechIntroPictureUrl();
        obtainMechDetails();
    }

    private void obtainMechDetails() {
        final Elements mechGrid = mMainContent.select(".mech-grid");
        final Elements content = mechGrid.select("a.content");

        Logger.debug(this, "Content Size: " + content.size());
        for (int j = 0; j < content.size(); j++) {
            final Element c = content.get(j);
            final String mechHref = c.attr("href");
            final String mechTitle = c.attr("title");
            final Elements showMechImgElem = c.select("a:nth-child("+(j + 1)+") > li:nth-child(1) > div:nth-child(1) > img:nth-child(1)");
            final Element showLoadoutElem = c.select("a:nth-child("+(j + 1)+") > li:nth-child(1) > div:nth-child(2)").first();
            final int loadoutLen = showLoadoutElem.children().size();
            final Elements pngs = showLoadoutElem.select("img[src$=.png]");
            final Elements weaponDivs = showLoadoutElem.select("div.panel-top");
            final Elements abilityDivs = showLoadoutElem.select("div:nth-child(3)");

            final Element description = c.select("div.p2").get(0);
            Logger.debug(this,description.text());

            //Get picture of weapons
            for (int k = 0; k < pngs.size(); k++) {
               // Logger.debug(this, pngs.get(k).attr("src"));
            }

            //Get weapon names for this mech
            for (int k = 0; k < weaponDivs.size(); k++) {
                final Element weaponType = weaponDivs.get(k).child(0);
                final Element weaponName = weaponDivs.get(k).child(1);
              //  Logger.debug(this, weaponType.text());
               // Logger.debug(this, weaponName.text());
            }

            final Element specialAbility = abilityDivs.get(0);
            //Logger.debug(this, specialAbility.text());

        }
    }

    private void obtainMechIntroPictureUrl() {
        mMechIntroPictureUrl = mMainContent.select(".imgframe > img:nth-child(1)").attr("src");
    }

    private void obtainMechTitleAndDescription() {
        final Element title = mMainContent.select(".header-text > h2:nth-child(1)").first();
        final Element desc = mMainContent.select(".header-text > p:nth-child(2)").first();
        mMechTitle = title.html();
        mMechDescription = desc.html();
    }

    private void obtainMechStats() {
        final Element statElem = mMainContent.select(".stats-panel").first();
        final String[] divs = {"armor", "speed", "energy"};

        for (String div : divs) {
            final Elements elems = statElem.select("span."+div+":nth-child(1)");

            for (Element e : elems) {
                final int fill = Integer.parseInt(e.attr("fill"));
                final int max = Integer.parseInt(e.attr("max"));

                if (e.className().equals("fill speed")) {
                    mMechStatSpeed = fill;
                    mMechStatSpeedMax = max;
                } else if (e.className().equals("fill armor")) {
                    mMechStatArmor = fill;
                    mMechStatArmorMax = max;
                } else {
                    mMechEnergyCapacity = fill;
                    mMechEnergyCapacityMax = max;
                }
            }
        }
    }
}
