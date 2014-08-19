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
package android.hawkencompanionapp.tabs;

import android.hawkencompanionapp.R;
import android.hawkencompanionapp.asynctasks.AsyncTaskUpdate;
import android.hawkencompanionapp.asynctasks.LoadMechInfoTask;
import android.hawkencompanionapp.logger.Logger;
import android.hawkencompanionapp.models.MechType;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Phillip Adam Nash on 2014.
 */
public class LightMechsGuideTab extends Fragment implements AsyncTaskUpdate {
    private MechType mMechType;
    private String mUrl = "http://www.playhawken.com/game-guide/mechs/light-mechs";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.mech_guide_tab, container, false);
        TextView tv = (TextView) rootView.findViewById(R.id.text);
        tv.setText("Light");
        return rootView;
    }

    @Override
    public void onCreate(Bundle savedBundle) {
        super.onCreate(savedBundle);
        mMechType = new MechType();
        new LoadMechInfoTask(this,mUrl,mMechType).execute();
    }

    @Override
    public void onAsyncPreComplete() {
        Logger.debug(this, "Obtaining mech details");
    }

    @Override
    public void onAsyncPostComplete() {
        Logger.debug(this, "Finished obtaining mech details");
    }
}
