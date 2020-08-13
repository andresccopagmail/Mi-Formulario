package org.coursera.ccopa.miformulario;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

public class ConfirmActivity extends AppCompatActivity {

    TextView tv_name;
    TextView tv_birthday;
    TextView tv_phone;
    TextView tv_email;
    TextView tv_description;

    MaterialButton btn_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);

        tv_name = findViewById(R.id.tv_name);
        tv_birthday = findViewById(R.id.tv_birthday);
        tv_phone = findViewById(R.id.tv_phone);
        tv_email = findViewById(R.id.tv_email);
        tv_description = findViewById(R.id.tv_description);
        btn_next = findViewById(R.id.btn_next);

        Bundle parameters = getIntent().getExtras();
        tv_name.setText(parameters.getString(getResources().getString(R.string.NAME_PARAMETER)));
        tv_birthday.setText(parameters.getString(getResources().getString(R.string.BIRTHDAY_PARAMETER)));
        tv_phone.setText(parameters.getString(getResources().getString(R.string.PHONE_PARAMETER)));
        tv_email.setText(parameters.getString(getResources().getString(R.string.EMAIL_PARAMETER)));
        tv_description.setText(parameters.getString(getResources().getString(R.string.DESCRIPTION_PARAMETER)));

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}