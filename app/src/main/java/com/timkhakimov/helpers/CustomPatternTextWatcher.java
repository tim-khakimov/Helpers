package com.timkhakimov.helpers;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * Created by Timur Khakimov on 10.02.2018.
 */

public abstract class CustomPatternTextWatcher implements TextWatcher {

    protected EditText editText;
    protected boolean backspacingFlag = false;
    protected boolean editedFlag = false;
    protected int cursorComplement;

    public CustomPatternTextWatcher(EditText editText) {
        this.editText = editText;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        cursorComplement = s.length()- editText.getSelectionStart();
        backspacingFlag = count > after;
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (editedFlag) {
            editedFlag = false;
            return;
        }
        if (!backspacingFlag) {
            editedFlag = true;
            editText.setText(getFormattedText(s.toString()));
            int selection = editText.getText().length() - cursorComplement + 1;
            if (selection > editText.getText().length()) {
                selection = editText.getText().length();
            }
            editText.setSelection(selection);
        }
    }

    protected abstract String getFormattedText(String editedText);
}
