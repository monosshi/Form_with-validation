package com.example.form;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Objects;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

     private EditText name_ed,phone_ed,email_ed,pass_ed,con_pass_ed;
     private Spinner contact_spinner;
     private String name,phone,email,pass,conf_pass,cont;
      private Button signin;

    LinearLayout inputLayout, outputLayout;

    TextView outputText;

     Pattern namePattern = Pattern.compile("[a-z A-Z._]+");

      Pattern numpattern = Pattern.compile("^01(3|4|5|6|7|8|9)\\d{8}");

      Pattern emailpattern = Pattern.compile("[a-z\\d_.-]+@(gmail\\.com|yahoo\\.com|hotmail\\.com|lus.ac.bd)");



     Pattern uppercase = Pattern.compile("(?=.*[A-Z])");
    Pattern lowercase = Pattern.compile("(?=.*[a-z])");
   Pattern digit = Pattern.compile("(?=.*\\d)");
    Pattern specialChar = Pattern.compile("(?=.*[@$!%*?&])");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



        name_ed=findViewById(R.id.name);
        phone_ed=findViewById(R.id.phone);
        email_ed=findViewById(R.id.email);
        pass_ed=findViewById(R.id.pass);
        con_pass_ed= findViewById(R.id.conf_pass);
        contact_spinner=findViewById(R.id.spinner);
        signin=findViewById(R.id.sign_in);
        inputLayout = findViewById(R.id.inputLayout);
        outputLayout = findViewById(R.id.outputLayout);
        outputText = findViewById(R.id.outputText);

        String[] items = new String[]{"Select Contact Medium","Text", "Email", "Call"};
        contact_spinner.setAdapter(new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, items));
        contact_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 cont = contact_spinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

        signin.setOnClickListener(V->{


        name = name_ed.getText().toString();
        phone = phone_ed.getText().toString();
        email = email_ed.getText().toString();
        pass = pass_ed.getText().toString();
        conf_pass = con_pass_ed.getText().toString();

        if (name.isEmpty()){
            name_ed.setError("Empty!!");
            name_ed.requestFocus();

        }else if (!namePattern.matcher(name).matches()){
            name_ed.setError("Name can be only Alphabet");
            name_ed.requestFocus();


        } else if (phone.isEmpty()){
            phone_ed.setError("Empty!!");
            phone_ed.requestFocus();
        }else if (!numpattern.matcher(phone).matches()){
            phone_ed.setError("Invalid Number");
            phone_ed.requestFocus();
        }
        else if (email.isEmpty()){
            email_ed.setError("Empty!!");
            email_ed.requestFocus();
        }
        else if (!emailpattern.matcher(email).matches()){
            email_ed.setError("Please Enter a Valid Email Address");
            email_ed.requestFocus();
        }
        else if (pass.isEmpty()){
            pass_ed.setError("Empty!!");
            pass_ed.requestFocus();
        }

        else if (pass.length() < 6) {
            pass_ed.setError("Password must contain at least 6 characters!");
            pass_ed.requestFocus();
        } else if (!uppercase.matcher(pass).find()) {
            pass_ed.setError("Password must contain at least one uppercase letter!");
            pass_ed.requestFocus();
        } else if (!lowercase.matcher(pass).find()) {
            pass_ed.setError("Password must contain at least one lowercase letter!");
            pass_ed.requestFocus();
        } else if (!digit.matcher(pass).find()) {
            pass_ed.setError("Password must contain at least one digit!");
            pass_ed.requestFocus();
        } else if (!specialChar.matcher(pass).find()) {
            pass_ed.setError("Password must contain at least one special character!");
            pass_ed.requestFocus();
        }
        else if (conf_pass.compareTo(pass) != 0) {
            pass_ed.setError("Passwords do not match");
            con_pass_ed.requestFocus();
        }
        else if (Objects.equals(cont, "Select Contact Medium")){
            Toast.makeText(getApplicationContext(), "Please Select any Contact Medium", Toast.LENGTH_SHORT).show();
        }
        else {
            inputLayout.setVisibility(View.GONE);
            outputLayout.setVisibility(View.VISIBLE);
            String s = "Name: " + name + "\nMobile Number: " + phone + "\nEmail: " + email + "\nContact Way: "+cont ;
            outputText.setText(s);
        }
    });

    }
}




