package sg.edu.np.practical7;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class createuser extends AppCompatActivity {
    Dbhandler db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createuser);
    }

    public void onCancel(View v)
    {
        Intent i = new Intent(createuser.this, MainActivity.class);
        startActivity(i);
    }

    public void onCreate(View v)
    {

        TextView tv =  findViewById(R.id.tvMsg);
        EditText user = findViewById(R.id.etName);
        EditText pass = findViewById(R.id.etPassword);
        String userInput = user.getText().toString();
        String passInput = pass.getText().toString();

        Pattern userPattern = Pattern.compile("^[A-Za-z0-9]{6,12}$");
        Matcher userMatcher = userPattern.matcher(userInput);
        Pattern passPattern = Pattern.compile("^(?=.*[A-Z])(?=.*[0-9])(?=.*\\W).*$");
        Matcher passMatcher = passPattern.matcher(passInput);

        Toast ttValid = Toast.makeText(this, "Valid", Toast.LENGTH_SHORT);
        Toast ttInvalid = Toast.makeText(this, "Invalid", Toast.LENGTH_SHORT);


        if (userMatcher.matches() && passMatcher.matches())
        {

            SharedPreferences.Editor et = getSharedPreferences("userInfo", MODE_PRIVATE).edit();
            et.putString("Username", userInput);
            et.putString("Password", passInput);
            et.apply();

            Account a = new Account(userInput, passInput);
            db.addAccount(a);
            ttValid.show();
        }
        else
            ttInvalid.show();

    }


}
