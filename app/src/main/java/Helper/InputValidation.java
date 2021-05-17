package Helper;

import android.app.Activity;
import android.content.Context;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class InputValidation {
    private Context context;

    public InputValidation(Context context) {
        this.context = context;
    }

    public boolean isInputEditTextFilled(TextInputEditText textInputEditText, TextInputLayout textInputLayout,String message){
        String value = textInputEditText.getText().toString().trim();

        if (value.isEmpty()){
            textInputLayout.setError(message);
            hideKeyboardFrom(textInputEditText);
            return false;
        }else{
            textInputLayout.setErrorEnabled(false);
        }
        return true;
    }
    public boolean isInputEditTextEmail(TextInputEditText textInputEditText,TextInputLayout textInputLayout,String message){
        String value = textInputEditText.getText().toString().trim();

        if (value.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(value).matches()){
            textInputLayout.setError(message);
            return false;
        }else{
            textInputLayout.setErrorEnabled(false);
        }
        return true;
    }
    public boolean isInputEditTextMatches(TextInputEditText textInputEditText,TextInputEditText textInputEditText2,TextInputLayout textInputLayout,String message){
       String value1 = textInputEditText.getText().toString().trim();
       String value2 = textInputEditText2.getText().toString().trim();

       if (!value1.contentEquals(value2)){
           textInputLayout.setError(message);
           hideKeyboardFrom(textInputEditText2);
           return false;
       }
       else{
           textInputLayout.setErrorEnabled(false);
       }
        return true;
    }

    private void hideKeyboardFrom(View view){
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }
}
