package com.example.collections_and_maps.view_model.models;
import android.text.Editable;

public class ValueValidator {
    public int value;

    public int getValue() {
        return value;
    }

    public ValueValidator(Editable inputtedValue) {
        String value = inputtedValue.toString();

        try {
            int mValue = Integer.parseInt(value);
            if (mValue >= 0) {
                this.value = mValue;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

}
