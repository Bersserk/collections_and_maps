package com.example.collections_and_maps.ui.benchmark;

import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;
import com.example.collections_and_maps.R;

class InputData {

    private int oldValue, newValue;
    private final EditText fieldEnteredValue;
    private final Context context;

    public InputData(EditText fieldEnteredData, Context context) {
        this.fieldEnteredValue = fieldEnteredData;
        this.context = context;
    }

    protected void rewriteOldValue() {
        this.oldValue = newValue;
    }

    protected int getNewValue() {
        set();
        return newValue;
    }

    protected int getOldValue() {
        return oldValue;
    }


    private void set() {
        String textOfInputData = fieldEnteredValue.getText().toString();
        try {
            int newEnteredValue = Integer.parseInt(textOfInputData);
            if (newEnteredValue > 0) {
                newValue = newEnteredValue;
            } else {
                Toast.makeText(context, R.string.OverZero, Toast.LENGTH_LONG).show();
            }
        } catch (NumberFormatException e) {
            if (textOfInputData.toCharArray().length > 0) {
                Toast.makeText(context, R.string.ToastsText, Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(context, R.string.NeedAnyValue, Toast.LENGTH_LONG).show();
            }
        }
    }
}
