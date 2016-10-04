package com.retrofitreactive.validate;


import android.widget.EditText;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

public class Validation {

    private static String REQUIRED = "Este campo es requerido";
    private static String NOTMATCH = "Este campo no es correcto";
    private DoValidations doValidations;

    public interface DoValidations {
        void onError(HashMap<EditText, String> errors);

        void onSucess();
    }

    public void validate(DoValidations view, ArrayList<EditText> editTextList) {
        doValidations = view;
        HashMap<EditText, String> errores = new HashMap<>();
        for (EditText editText : editTextList) {
            if (hasRequired(editText)) {
                errores.put(editText, REQUIRED);
            } else if (!match(editText)) {
                errores.put(editText, REQUIRED);
            } else if (!(hasRequired(editText) && !(editText.getText().toString().isEmpty()))) {
                if (!match(editText))
                    errores.put(editText, NOTMATCH);
            }
        }

        if (errores.isEmpty()) {
            doValidations.onSucess();
        } else {
            doValidations.onError(errores);
        }
    }

    public static boolean hasRequired(EditText editText) {
        return editText.getHint().toString().contains("*");
    }

    public static boolean match(EditText editText) {
        return Pattern.matches("regex", editText.getText().toString());
    }
}
