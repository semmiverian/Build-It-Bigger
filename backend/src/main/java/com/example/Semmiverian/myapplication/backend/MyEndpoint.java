/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.example.Semmiverian.myapplication.backend;

import com.example.Jokes;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import javax.inject.Named;

/**
 * An endpoint class we are exposing
 */
@Api(
        name = "myApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.myapplication.Semmiverian.example.com",
                ownerName = "backend.myapplication.Semmiverian.example.com",
                packagePath = ""
        )
)
public class MyEndpoint {

    /**
     * A simple endpoint method that takes a name and says Hi back
     */
    @ApiMethod(name = "sayHi")
    public MyBean sayHi(@Named("name") String name) {
        MyBean response = new MyBean();
        response.setData("Hi, " + name);

        return response;
    }

    @ApiMethod(name = "oneJoke")
    public MyBean getJoke () {
        Jokes joke = new Jokes();

        MyBean response = new MyBean();
        response.setData(joke.getJoke());

        return response;
    }

    @ApiMethod(name = "allJoke")
    public MyBean allJokes () {
        Jokes jokes = new Jokes();

        MyBean response = new MyBean();
        response.setListData(jokes.allJokes());
        return response;
    }

}
