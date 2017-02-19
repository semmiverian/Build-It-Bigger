package com.udacity.gradle.builditbigger;

import android.app.Application;
import android.content.Context;
import android.test.ApplicationTestCase;
import android.util.Pair;

import com.afollestad.materialdialogs.MaterialDialog;


import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    private CountDownLatch signal = null;
    private List<String> jokes = null;
    private Exception mError = null;
    private String jokeReturn = null;


    public ApplicationTest() {
        super(Application.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        signal = new CountDownLatch(1);
    }

    @Override
    protected void tearDown() throws Exception {
        signal.countDown();
    }

    public void testGetJokesTask() throws IOException, InterruptedException {
        JokesAsyncTask task = new JokesAsyncTask();
        task.setListener(new JokesAsyncTask.JokeListener() {
            @Override
            public void onComplete(List<String> strings, Exception e) {
                jokes = strings;
                mError = e;
            }
        }).execute(new Pair<Context, MaterialDialog>(getContext(), null));

        signal.await(5, TimeUnit.SECONDS);

        assertNull(mError);
        assertNotNull(jokes);
        assertTrue(jokes.size() != 0);
    }

    public void testGetOneJokeTask() throws InterruptedException {
        JokeAsyncTask task = new JokeAsyncTask();
        task.setListener(new JokeAsyncTask.JokeListener() {
            @Override
            public void onComplete(String joke, Exception e) {
                jokeReturn = joke;
                mError = e;
            }
        }).execute(new Pair<Context, String>(getContext(), "stub"));

        signal.await(5, TimeUnit.SECONDS);

        assertNull(mError);
        assertNotNull(jokeReturn);
    }
}