package fall2018.csc2017.slidingtiles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final Button bLogIn = (Button) findViewById(R.id.bLogin);
        final TextView registerLink = (TextView) findViewById(R.id.registerLink);

        bLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = etUsername.getText().toString();
                final String password = etPassword.getText().toString();
                // checkUserId
                Intent loginIntent = new Intent(LoginActivity.this, GameCenterActivity.class);
                LoginActivity.this.startActivity(loginIntent);
                }
            });

        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);
            }
        });
    }
    private boolean checkUserId(String username, String password){
        ArrayList<UserAccount> userList = UserAccountManager.getUserList();
        for(UserAccount account: userList){
            if(account.getName().equals(username) && account.getPassword().equals(password)){
                return true;
            }

        }return false;}
}
