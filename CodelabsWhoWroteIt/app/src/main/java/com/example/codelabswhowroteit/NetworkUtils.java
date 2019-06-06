package com.example.codelabswhowroteit;

import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkUtils {

    private static final String LOG_TAG =
            NetworkUtils.class.getSimpleName();

    //base URL for BOOk API
    private static final String BOOK_BASE_URL = "https://www.googleapis.com/books/v1/volumes?";
    //parameter for the search string
    private static final String QUERY_PARAM = "q";
    //parameter that limits results
    private static final String MAX_RESULTS = "maxResults";
    //parameter to filter by print type
    private static final String PRINT_TYPE = "printType";



    static String getBookInfo(String queryString){
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String bookJSONString = null;


        try{

            //build the full query URI, limiting results to 10 and printed books
            Uri buiitURI = Uri.parse(BOOK_BASE_URL).buildUpon()
                    .appendQueryParameter(QUERY_PARAM, queryString)
                    .appendQueryParameter(MAX_RESULTS, "10")
                    .appendQueryParameter(PRINT_TYPE, "books")
                    .build();

            //convert the URI to a URL
            URL requestURL = new URL(buiitURI.toString());

            //open the network connection
            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            //get the InputStream
            InputStream inputStream = urlConnection.getInputStream();

            //create a buffered reader from that input stream
            reader = new BufferedReader(new InputStreamReader(inputStream));

            //use a StringBuilder to holad the incoming response
            StringBuilder builder = new StringBuilder();

            String line;
            while((line = reader.readLine()) != null) {
                builder.append(line);
                builder.append("\n");
            }

            if(builder.length() == 0){
                return null;
            }

            bookJSONString = builder.toString();

        } catch (IOException e) {
        e.printStackTrace();
        } finally {
            //close the connection and the buffered reader
            if(urlConnection != null){
                urlConnection.disconnect();
            }
            if(reader != null){
                try {
                    reader.close();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }

        Log.d(LOG_TAG, bookJSONString);

        return bookJSONString;
    }

}
