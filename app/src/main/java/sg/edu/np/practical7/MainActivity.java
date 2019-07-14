package sg.edu.np.practical7;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity
{
    Dbhandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv = findViewById(R.id.create);
        tv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN){
                    Intent i = new Intent(MainActivity.this, createuser.class);
                    startActivity(i);
                    return true;
                }
                return false;
            }
        });
    }

    public void onLogin(View v)
    {
        TextView tv = findViewById(R.id.tvMsg);
        EditText user = findViewById(R.id.etUserLogin);
        EditText pass = findViewById(R.id.etPassLogin);
        String userInput = user.getText().toString();
        String passInput = pass.getText().toString();

        Pattern userPattern = Pattern.compile("^[A-Za-z0-9]*.{6,12}$");
        Matcher userMatcher = userPattern.matcher(userInput);
        Pattern passPattern = Pattern.compile("^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[@#$%^&+=]).*$");
        Matcher passMatcher = passPattern.matcher(passInput);
        Toast ttValid = Toast.makeText(this, "Valid", Toast.LENGTH_SHORT);
        Toast ttInvalid = Toast.makeText(this, "Invalid", Toast.LENGTH_SHORT);



        if (userMatcher.matches()==true && passMatcher.matches()==true) {

            SharedPreferences preferences = getSharedPreferences("userInfo", MODE_PRIVATE);
            String userChecker = preferences.getString("Username", "");
            String passChecker = preferences.getString("Password", "");

            if(userChecker.equals(userInput) && passChecker.equals(passInput))
            {
                ttValid.show();
            }

        }   else
            ttInvalid.show();

    }
}
