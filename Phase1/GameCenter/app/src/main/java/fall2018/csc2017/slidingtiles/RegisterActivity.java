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

    final EditText etUsername = (EditText) findViewById(R.id.etUsername);
    final EditText etPassword = (EditText) findViewById(R.id.etPassword);
    final EditText etConfirmPW = (EditText) findViewById(R.id.etConfirmPW);
    final Button bRegister = (Button) findViewById(R.id.bRegister);
    final Button bBack = (Button) findViewById(R.id.bBack);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        bBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(registerButtonPushed()){
                    Intent registerIntent = new Intent( RegisterActivity.this, LoginActivity.class);
                    RegisterActivity.this.startActivity(registerIntent);
                }
            }
        });
    }

    private boolean registerButtonPushed() {
        final String username = etUsername.getText().toString();
        final String password = etPassword.getText().toString();
        final String confirmPW = etConfirmPW.getText().toString();
        ArrayList<UserAccount> userList = UserAccountManager.getUserList();
        UserAccount newUser = new UserAccount(username, password);
        // should change to use the iterator design pattern after solving the static problem
        for (UserAccount account : userList) {
            if (account.getName().equals(username)) {
                return false;
            }
            //tell the user the username already exists.
        }
        if (password.equals(confirmPW)) {
            UserAccountManager.AddUser(newUser);
            return true;
        }
        //Tell the user the password and confirmed password are not the same
        return false;

    }
}
