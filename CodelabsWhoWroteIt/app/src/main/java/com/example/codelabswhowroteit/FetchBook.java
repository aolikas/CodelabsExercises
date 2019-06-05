package com.example.codelabswhowroteit;

import android.os.AsyncTask;
import android.widget.TextView;

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
    }
}
