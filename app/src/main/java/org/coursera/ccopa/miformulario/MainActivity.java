package org.coursera.ccopa.miformulario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    TextInputEditText et_name;
    TextInputEditText et_birthday;
    TextInputEditText et_phone;
    TextInputEditText et_email;
    TextInputEditText et_description;
    MaterialButton btn_next;

    MaterialDatePicker<Long> materialDatePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        materialDatePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText(R.string.activity_main_hint_birthday)
                .build();
        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>() {
            @Override
            public void onPositiveButtonClick(Long selection) {
                et_birthday.setText(materialDatePicker.getHeaderText());
            }
        });

        et_name = findViewById(R.id.et_name);
        et_birthday = findViewById(R.id.et_birthday);
        et_phone = findViewById(R.id.et_phone);
        et_email = findViewById(R.id.et_email);
        et_description = findViewById(R.id.et_description);
        btn_next = findViewById(R.id.btn_next);

        et_birthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                materialDatePicker.show(getSupportFragmentManager(), "DATE_PICKER");
            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ConfirmActivity.class);
                intent.putExtra(getResources().getString(R.string.NAME_PARAMETER), Objects.requireNonNull(et_name.getText()).toString());
                intent.putExtra(getResources().getString(R.string.BIRTHDAY_PARAMETER), Objects.requireNonNull(et_birthday.getText()).toString());
                intent.putExtra(getResources().getString(R.string.PHONE_PARAMETER), Objects.requireNonNull(et_phone.getText()).toString());
                intent.putExtra(getResources().getString(R.string.EMAIL_PARAMETER), Objects.requireNonNull(et_email.getText()).toString());
                intent.putExtra(getResources().getString(R.string.DESCRIPTION_PARAMETER), Objects.requireNonNull(et_description.getText()).toString());
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if ( v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int)event.getRawX(), (int)event.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent( event );
    }
}