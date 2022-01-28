package com.doutya.superadmin;

import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.appcompat.widget.AppCompatTextView;

import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CommonFunctions {

    //Validate EditText
    public boolean validate(TextInputLayout layout) {
        if (layout.getEditText().getText().toString().isEmpty()) {
            layout.setError("Field cannot be empty!");
        }

        return !layout.getEditText().getText().toString().isEmpty();
    }

    public String GetText(TextInputLayout layout) {
        return layout.getEditText().getText().toString();
    }

    public boolean validate(AppCompatTextView textView) {
        if (textView.getText().toString().isEmpty()) {
            textView.setError("Field cannot be empty!");
        }

        return !textView.getText().toString().isEmpty();
    }

    public boolean validate(AppCompatAutoCompleteTextView completeTextView) {
        if (completeTextView.getText().toString().isEmpty()) {
            completeTextView.setError("Field cannot be empty!");
        }

        return !completeTextView.getText().toString().isEmpty();
    }

    public String GetText(AppCompatAutoCompleteTextView completeTextView ) {
        return completeTextView.getText().toString();
    }

    public String currentDate(){
        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String currentDate = df.format(date);
        return currentDate;
    }

}
