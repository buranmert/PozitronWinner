package com.cnlms.rottenandroid.network;

import android.content.Context;
import org.json.JSONObject;

/**
 * Author: Can Elmas <can.elmas@pozitron.com>
 * Date: 12/20/12 5:31 AM
 */
public class TestRequest extends BaseAsyncTask {

    /**
     *
     *  DUMMY REQUEST EXAMPLE
     *
     *
     */

    private String data;

    public TestRequest(Context context, RequestListener listener) {
        super(context, listener);
    }

    /**
     *
     * This method should be overridden in your request classes.
     *
     * It will tell BaseAsyncTask the url that it should make connection
     *
     */
    @Override
    protected String getRequestEndpointUrl() {
        return Endpoints.URL_MOVIE_SEARCH;
    }

    /**
     *
     *  This method should be overridden in your request classes.
     *
     *  This is where you should parse the JSON returned from the API,
     *  initialize your model classes and add them to a collection or list.
     *
     */
    @Override
    protected void processReceivedData(JSONObject json) throws Exception {

        /**
         *
         *  Parse json object and initialize model classes fetched from the endpoint
         *
         *  Ex :
         *
         *  Movie movie = new Movie();
         *
         *  movie.setTitle(json.getString("movie_title));
         *  ...
         *  ...
         *
         */
        data = json.getString("data");

    }

    public String getData() {
        return data;
    }
}
