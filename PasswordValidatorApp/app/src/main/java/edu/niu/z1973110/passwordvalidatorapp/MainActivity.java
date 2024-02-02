/************************************************************************
 * *
 * CSCI 522 Assignment 4 Fall 2023
 * *
 * App Name: Assignment4 Part2
 * *
 * Class Name: MainActivity
 * *
 * Developer(s):  Renuka Pasam
 * *
 * Due Date: 10/20/2023
 * *
 * Purpose: MainActivity.java class for the project
 * *
 ************************************************************************/
package edu.niu.z1973110.passwordvalidatorapp;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText passwordEditText;
    private TextView validationLabel;
    private PasswordModel passwordModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize model
        passwordModel = new PasswordModel();

        // Create and configure UI elements
        passwordEditText = new EditText(this);
        passwordEditText.setHint("Enter Password");
        passwordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                updateValidationLabel(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        validationLabel = new TextView(this);

        // Set up the layout
        androidx.constraintlayout.widget.ConstraintLayout layout = new androidx.constraintlayout.widget.ConstraintLayout(this);
        passwordEditText.setId(View.generateViewId());
        validationLabel.setId(View.generateViewId());
        layout.addView(passwordEditText);
        layout.addView(validationLabel);

        // Configure constraints
        androidx.constraintlayout.widget.ConstraintSet set = new androidx.constraintlayout.widget.ConstraintSet();
        set.clone(layout);

        set.connect(passwordEditText.getId(), androidx.constraintlayout.widget.ConstraintSet.TOP, androidx.constraintlayout.widget.ConstraintSet.PARENT_ID, androidx.constraintlayout.widget.ConstraintSet.TOP, 32);
        set.connect(passwordEditText.getId(), androidx.constraintlayout.widget.ConstraintSet.START, androidx.constraintlayout.widget.ConstraintSet.PARENT_ID, androidx.constraintlayout.widget.ConstraintSet.START, 32);
        set.connect(passwordEditText.getId(), androidx.constraintlayout.widget.ConstraintSet.END, androidx.constraintlayout.widget.ConstraintSet.PARENT_ID, androidx.constraintlayout.widget.ConstraintSet.END, 32);

        set.connect(validationLabel.getId(), androidx.constraintlayout.widget.ConstraintSet.TOP, passwordEditText.getId(), androidx.constraintlayout.widget.ConstraintSet.BOTTOM, 16);
        set.connect(validationLabel.getId(), androidx.constraintlayout.widget.ConstraintSet.START, passwordEditText.getId(), androidx.constraintlayout.widget.ConstraintSet.START, 0);
        set.connect(validationLabel.getId(), androidx.constraintlayout.widget.ConstraintSet.END, passwordEditText.getId(), androidx.constraintlayout.widget.ConstraintSet.END, 0);

        set.applyTo(layout);

        // Set the content view to the created layout
        setContentView(layout);

        // Set title
        setTitle("Password Validator");
    }

    private void updateValidationLabel(String password) {
        boolean isStrong = passwordModel.isPasswordStrong(password);
        validationLabel.setText(isStrong ? "STRONG" : "WEAK");
    }

    public class PasswordModel {
        public boolean isPasswordStrong(String password) {
            return password.length() >= 9;
        }
    }
}