package com.example.codelabsdroidcafe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class OrderActivity extends AppCompatActivity implements
                                   AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        //get the intent and its data
        Intent intent = getIntent();
        String msg = "Order: "
                     + intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        TextView orderMessage = findViewById(R.id.tv_order);
        orderMessage.setText(msg);

        //create a spinner
        Spinner spinner = findViewById(R.id.label_spinner);
        if(spinner != null) {
            spinner.setOnItemSelectedListener(this);
        }

        //create ArrayAdapter using the string array and default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                                    R.array.label_array, android.R.layout.simple_spinner_item);

        //specify the layout to use with the list of choices appears
        adapter.setDropDownViewResource
                                    (android.R.layout.simple_spinner_dropdown_item);

        //apply the adapter to the spinner
        if(spinner != null) {
            spinner.setAdapter(adapter);
        }

    }

    public void onRadioButtonClicked(View view) {

        //is a button now checked
        boolean checked = ((RadioButton)view).isChecked();

        //checking which radio button is clicked
        switch (view.getId()) {
            case R.id.same_day:
                if(checked)
                    displayToast(getString(R.string.radio_same_day));
                break;

            case R.id.next_day:
                if(checked)
                    displayToast(getString(R.string.radio_next_day));
                break;

            case R.id.pickup:
                if(checked)
                    displayToast(getString(R.string.radio_pick_up));
                break;

                default:
                    break;
        }
    }

    public void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message,
                       Toast.LENGTH_LONG).show();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view,
                                          int position, long id) {
       String spinnerLabel = parent.getItemAtPosition(position).toString();
       displayToast(spinnerLabel);


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
