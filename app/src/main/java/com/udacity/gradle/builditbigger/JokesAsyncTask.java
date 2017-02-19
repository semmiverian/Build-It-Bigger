package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.util.Pair;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.semmiverian.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import id.semmi.showjokes.JokeActivity;

/**
 * Created by Semmiverian on 2/19/17.
 */

public class JokesAsyncTask extends AsyncTask<Pair<Context, MaterialDialog>, Void, List<String>> {
    private static MyApi myApiService = null;
    private Context context;
    private MaterialDialog dialog;
    private JokeListener mListener = null;
    private Exception mError = null;


    public JokesAsyncTask setListener (JokeListener listener) {
        this.mListener = listener;
        return this;
    }


    @Override
    protected List<String> doInBackground(Pair<Context, MaterialDialog>... pairs) {
        if(myApiService == null) {
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            myApiService = builder.build();
        }

        context = pairs[0].first;
        dialog = pairs[0].second;

        try {
            return myApiService.allJoke().execute().getListData();
        } catch (IOException e) {
            List<String> error = new ArrayList<>();
            error.add(e.getMessage());
            mError = e;
            return error;
        }
    }

    @Override
    protected void onPostExecute(List<String> strings) {
        super.onPostExecute(strings);

        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }


        // i don't know why it change my array list to list
        ArrayList<String> jokes = new ArrayList<>();
        for (String joke : strings) {
            jokes.add(joke);
        }

        if (this.mListener != null) {
            this.mListener.onComplete(strings, mError);
        }


        if (mListener == null) {
            // Only do the intent if not on testing mode
            // i've got an error when try to do this when testing because i start an activity here
            // might be need something like mockito or expresso maybe?

            Intent allJokesIntent = new Intent(context, JokeActivity.class);
            allJokesIntent.putStringArrayListExtra("jokes", jokes);
            context.startActivity(allJokesIntent);



        }
    }

    public interface JokeListener {
        void onComplete(List<String> strings, Exception e);
    }
}
