package android.hawkencompanionapp.asynctasks;
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
import android.hawkencompanionapp.models.MechType;
import android.os.AsyncTask;

/**
 * Created by Phillip Adam Nash on 14/08/2014.
 */
public class LoadMechInfoTask extends AsyncTask<MechType, Void, Void> {

    @Override
    protected void onPostExecute(Void v) {

    }

    @Override
    protected void onPreExecute() {

    }

    protected Void doInBackground(MechType... userDetails) {

        return null;
    }
}
