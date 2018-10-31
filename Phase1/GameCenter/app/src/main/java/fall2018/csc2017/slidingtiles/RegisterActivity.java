package fall2018.csc2017.slidingtiles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;

public class RegisterActivity extends AppCompatActivity {

    EditText etUsername;
    EditText etPassword;
    EditText etConfirmPW;
    Button bRegister;
    Button bBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etConfirmPW = (EditText) findViewById(R.id.etConfirmPW);
        bRegister = (Button) findViewById(R.id.bRegister);

        bRegister.setOnClickListener(new View.OnClickListener() {
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
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        String confirmPW = etConfirmPW.getText().toString();
        ArrayList<UserAccount> userList = UserAccountManager.getUserList();
        UserAccount newUser = new UserAccount(username, password);
        TextView textView = (TextView)findViewById(R.id.textView7);
        // should change to use the iterator design pattern after solving the static problem
        if (!userList.isEmpty()){
            for (UserAccount account : userList) {
                if (account.getName().equals(username)) {
                    textView.setText("this name is taken");
                    return false;
                }
                //tell the user the username already exists.
        }}
        if (!password.equals(confirmPW)) {
            textView.setText("password doesn't match");
            return false;

        }
        //Tell the user the password and confirmed password are not the same
        UserAccountManager.AddUser(newUser);
        return true;

    }


}
