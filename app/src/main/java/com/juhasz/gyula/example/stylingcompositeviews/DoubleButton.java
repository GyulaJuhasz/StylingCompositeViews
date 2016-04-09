package com.juhasz.gyula.example.stylingcompositeviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * Component that shows two equally wide buttons.
 */
public class DoubleButton extends LinearLayout {

    private static final String TAG = "DoubleButton";

    private Button leftButton;

    private Button rightButton;

    private DoubleButtonClickListener doubleButtonClickListener;

    private OnClickListener clickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (doubleButtonClickListener == null) {
                return;
            }

            if (v == leftButton) {
                doubleButtonClickListener.onLeftButtonClicked(DoubleButton.this);
            } else if (v == rightButton) {
                doubleButtonClickListener.onRightButtonClicked(DoubleButton.this);
            } else {
                Log.w(TAG, "Click event from unknown source!");
            }
        }
    };

    /**
     * Constructor for creating an instance from code.
     *
     * @param context Context object.
     */
    public DoubleButton(@NonNull final Context context) {
        this(context, null);
    }

    /**
     * Constructor called by the layout inflater.
     *
     * @param context Context object.
     * @param attrs   the attribute set acquired during the inflation.
     */
    public DoubleButton(@NonNull final Context context, @Nullable final AttributeSet attrs) {
        this(context, attrs, R.attr.doubleButtonStyle);
    }

    /**
     * Constructor for creating an instance with a default style.
     *
     * @param context      Context object.
     * @param attrs        the attribute set acquired during the inflation, can be {@code null}.
     * @param defStyleAttr attribute that points to a style in the current theme that should be used to get default
     *                     values for the View's properties
     */
    public DoubleButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(LinearLayout.HORIZONTAL);

        LayoutInflater.from(context).inflate(R.layout.double_button_layout, this);
        leftButton = (Button) findViewById(R.id.left_button);
        rightButton = (Button) findViewById(R.id.right_button);

        leftButton.setOnClickListener(clickListener);
        rightButton.setOnClickListener(clickListener);

        TypedArray doubleButtonTypedArray = context.obtainStyledAttributes(attrs, R.styleable.DoubleButton, defStyleAttr, 0);
        setLeftButtonText(doubleButtonTypedArray.getString(R.styleable.DoubleButton_leftButtonText));
        setRightButtonText(doubleButtonTypedArray.getString(R.styleable.DoubleButton_rightButtonText));
        doubleButtonTypedArray.recycle();
    }

    /**
     * Sets the text of the left button to the text specified by the given string resource.
     *
     * @param leftTextResource the string resource for the new text or {@code 0} to remove the text.
     */
    public void setLeftButtonText(@StringRes final int leftTextResource) {
        setLeftButtonText(leftTextResource == 0 ? null : getContext().getString(leftTextResource));
    }

    /**
     * Sets the text of the left button to the given character sequence.
     *
     * @param leftText the new text for the left button, or {@code null} to remove the text.
     */
    public void setLeftButtonText(@Nullable final CharSequence leftText) {
        leftButton.setText(leftText);
    }

    /**
     * Sets the text of the right button to the text specified by the given string resource.
     *
     * @param rightTextResource the string resource for the new text or {@code 0} to remove the text.
     */
    public void setRightButtonText(@StringRes final int rightTextResource) {
        setRightButtonText(rightTextResource == 0 ? null : getContext().getString(rightTextResource));
    }

    /**
     * Sets the text of the right button to the given character sequence.
     *
     * @param rightText the new text for the left button, or {@code null} to remove the text.
     */
    public void setRightButtonText(@Nullable final CharSequence rightText) {
        rightButton.setText(rightText);
    }

    /**
     * Sets the orientation to horizontal. Vertical orientation is not supported by this component.
     *
     * @param orientation the orientation. Anything other than {@link LinearLayout#HORIZONTAL} is ignored.
     */
    @Override
    public void setOrientation(int orientation) {
        super.setOrientation(LinearLayout.HORIZONTAL);
    }

    /**
     * Sets the double button listener.
     *
     * @param listener the new listener, or {@code null} to remove the listener.
     */
    public void setDoubleButtonClickListener(@Nullable final DoubleButtonClickListener listener) {
        this.doubleButtonClickListener = listener;
    }

    /**
     * Listener for reacting to button clicks in the double button.
     */
    public interface DoubleButtonClickListener {

        /**
         * Left button of the double button component is clicked.
         *
         * @param doubleButton the component that contains the clicked left button.
         */
        void onLeftButtonClicked(@NonNull DoubleButton doubleButton);

        /**
         * Right button of the double button component is clicked.
         *
         * @param doubleButton the component that contains the clicked right button.
         */
        void onRightButtonClicked(@NonNull DoubleButton doubleButton);
    }
}
