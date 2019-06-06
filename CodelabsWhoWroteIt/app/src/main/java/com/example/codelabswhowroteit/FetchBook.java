package com.example.codelabswhowroteit;

import android.os.AsyncTask;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;

public class FetchBook extends AsyncTask<String,Void,String> {

    private WeakReference<TextView> mTextTitle, mAuthorText;

    FetchBook(TextView titleText, TextView authorText) {
        this.mTextTitle = new WeakReference<>(titleText);
        this.mAuthorText = new WeakReference<>(authorText);
    }

    @Override
    protected String doInBackground(String... strings) {
        return null;
    }

    @Override
    protected void onPostExecute(String s) {

        super.onPostExecute(s);
        
        try {
            //convert the response into a JSON object
            JSONObject jsonObject = new JSONObject(s);
            //get the JSON ARRAY of book items
            JSONArray itemsArray = jsonObject.getJSONArray("items");
       
            //initialize iterator and results fields
            int i = 0;
            String title = null;
            String authors = null;
            
            while (i < itemsArray.length() &&
                    (authors== null && title == null)) {
                //get the current item information
                JSONObject book = itemsArray.getJSONObject(i);
                JSONObject volumeInfo = book.getJSONObject("VolumeInfo");
                
                try{
                    title = volumeInfo.getString("title");
                    authors = volumeInfo.getString("authors");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //move to the next item
                i += 1;
            }
            
            //if both are found, display the results
            if(title != null && authors != null) {
                mTextTitle.get().setText(title);
                mAuthorText.get().setText(authors);
            } else {
                mTextTitle.get().setText(R.string.no_result);
                mAuthorText.get().setText("");
            }
            
        } catch (Exception e) {
            mTextTitle.get().setText(R.string.no_result);
            mAuthorText.get().setText("");
        }
    }
}
