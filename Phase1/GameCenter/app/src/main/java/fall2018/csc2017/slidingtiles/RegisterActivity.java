package fall2018.csc2017.slidingtiles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Iterator;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final EditText etConfirmPW = (EditText) findViewById(R.id.etConfirmPW);
        final Button bLogIn = (Button) findViewById(R.id.bRegister);
        final Button bBack = (Button) findViewById(R.id.bBack);

        bBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = etUsername.getText().toString();
                final String password = etPassword.getText().toString();
                final String confirmPW = etConfirmPW.getText().toString();
                ArrayList<UserAccount> userList = UserAccountManager.getUserList();
                Boolean userExists = false;
                UserAccount newUser;
                // should change to use the iterator design pattern after solving the static problem
                for(UserAccount account: userList){
                    if(account.getName().equals(username)){userExists = true;}
                    //tell the user the username already exists.
                }
                if(!userExists){
                    if(password.equals(confirmPW)){
                        newUser = new UserAccount(username, password);
                        UserAccountManager.AddUser(newUser);
                    }
                    ////Tell the user the password and confirmed password are not the same
                }
                Intent registerIntent = new Intent( RegisterActivity.this, LoginActivity.class);
                RegisterActivity.this.startActivity(registerIntent);
            }
        });

    }
}
