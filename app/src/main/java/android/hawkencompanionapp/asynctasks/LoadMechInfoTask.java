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

package android.hawkencompanionapp.asynctasks;
import android.hawkencompanionapp.logger.Logger;
import android.hawkencompanionapp.models.MechType;
import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by Phillip Adam Nash on 14/08/2014.
 */
public class LoadMechInfoTask extends AsyncTask<Void, Void, Void> {

    private final String mMechLightUrl;
    private AsyncTaskUpdate mAsyncTaskUpdate;
    private final MechType mMechType;

    public LoadMechInfoTask(AsyncTaskUpdate asyncTaskUpdate, String url, MechType mechType) {
        mMechLightUrl = url;
        mAsyncTaskUpdate = asyncTaskUpdate;
        mMechType = mechType;
    }

    @Override
    protected void onPostExecute(Void v) {
        mAsyncTaskUpdate.onAsyncPostComplete();
    }

    @Override
    protected void onPreExecute() {
        mAsyncTaskUpdate.onAsyncPreComplete();
    }

    protected Void doInBackground(Void... v) {
        final HttpPost httpPost = new HttpPost(mMechLightUrl);
        final HttpClient httpClient = new DefaultHttpClient();
        Logger.debug(this, "Obtaining mech details");
        try {
            final HttpResponse httpResponse = httpClient.execute(httpPost);
            final String responseStr = EntityUtils.toString(httpResponse.getEntity());
            mMechType.setHtmlData(responseStr);
        } catch (IOException e) {
            Logger.error(this,e.getMessage());
        }
        return null;
    }
}
