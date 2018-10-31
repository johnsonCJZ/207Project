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

    EditText etUsername;
    EditText etPassword;
    Button bLogIn;
    TextView registerLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etUsername= (EditText) findViewById(R .id.etUsername);
        etPassword= (EditText) findViewById(R.id.etPassword);
        bLogIn= (Button) findViewById(R.id.bLogin);
        registerLink = (TextView) findViewById(R.id.registerLink);
        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);
            }
        });

        loginButtonPushed();
    }

    private void loginButtonPushed(){
        bLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                if(checkUserId(username,password)){
                Intent loginIntent = new Intent(LoginActivity.this, GameCenterActivity.class);
                LoginActivity.this.startActivity(loginIntent);
            }}
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




