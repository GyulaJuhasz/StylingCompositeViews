package com.juhasz.gyula.example.stylingcompositeviews;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;

/**
 * Main activity to test the example View.
 */
public class MainActivity extends AppCompatActivity {

    private DoubleButton doubleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_layout);
        doubleButton = (DoubleButton) findViewById(R.id.double_button);
        doubleButton.setDoubleButtonClickListener(new DoubleButton.DoubleButtonClickListener() {
            @Override
            public void onLeftButtonClicked(@NonNull DoubleButton doubleButton) {
                showSnackBarMessage("Left button clicked.");
            }

            @Override
            public void onRightButtonClicked(@NonNull DoubleButton doubleButton) {
                showSnackBarMessage("Right button clicked.");
            }
        });
    }

    private void showSnackBarMessage(final @NonNull CharSequence message) {
        Snackbar.make(doubleButton, message, Snackbar.LENGTH_SHORT).show();
    }

}
