package com.juhasz.gyula.example.stylingcompositeviews;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;

/**
 * Main activity to test the example View.
 */
public class MainActivity extends AppCompatActivity {

    private DoubleButton topDoubleButton;
    private DoubleButton bottomDoubleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_layout);
        topDoubleButton = (DoubleButton) findViewById(R.id.top_double_button);
        bottomDoubleButton = (DoubleButton) findViewById(R.id.bottom_double_button);

        final DoubleButton.DoubleButtonClickListener doubleButtonClickListener =
                new DoubleButton.DoubleButtonClickListener() {
                    @Override
                    public void onLeftButtonClicked(@NonNull DoubleButton doubleButton) {
                        String message = null;
                        if (doubleButton == topDoubleButton) {
                            message = "Top left button clicked.";
                        } else if (doubleButton == bottomDoubleButton) {
                            message = "Bottom left button clicked.";
                        }
                        if (message != null) {
                            showSnackBarMessage(message);
                        }
                    }

                    @Override
                    public void onRightButtonClicked(@NonNull DoubleButton doubleButton) {
                        String message = null;
                        if (doubleButton == topDoubleButton) {
                            message = "Top right button clicked.";
                        } else if (doubleButton == bottomDoubleButton) {
                            message = "Bottom right button clicked.";
                        }
                        if (message != null) {
                            showSnackBarMessage(message);
                        }
                    }
                };

        topDoubleButton.setDoubleButtonClickListener(doubleButtonClickListener);
        bottomDoubleButton.setDoubleButtonClickListener(doubleButtonClickListener);
    }

    private void showSnackBarMessage(final @NonNull CharSequence message) {
        Snackbar.make(topDoubleButton, message, Snackbar.LENGTH_SHORT).show();
    }

}
