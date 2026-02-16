package edu.sjsu.android.project1danielkuvan;
import android.text.InputFilter;
import android.text.Spanned;

//Honestly this class I asked from ChatGPT so that it won't take more than 2 numbers after decimal point
public class DecimalDigitInputFilter implements InputFilter {
    private final int decimalDigits;

    public DecimalDigitInputFilter(int decimalDigits) {
        this.decimalDigits = decimalDigits;
    }

    public CharSequence filter(CharSequence source, int start, int end,
                               Spanned dest, int dstart, int dend) {

        String newText = dest.toString() + source.toString();

        if (newText.matches("^\\d*(\\.\\d{0," + decimalDigits + "})?$")) {
            return null; // Accept input
        }
        return ""; // Reject input
    }
}
