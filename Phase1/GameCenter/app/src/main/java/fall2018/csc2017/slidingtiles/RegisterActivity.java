package fall2018.csc2017.slidingtiles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;

public class RegisterActivity extends AppCompatActivity {

    EditText etUsername;
    EditText etPassword;
    EditText etConfirmPW;
    Button bRegister;
    Button bBack;
    UserAccountManager userAccountManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadFromFile(UserAccountManager.USERS);
        if (userAccountManager == null){
            userAccountManager = new UserAccountManager();
        }
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
        ArrayList<UserAccount> userList = userAccountManager.getUserList();
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
        userAccountManager.AddUser(newUser);
        saveToFile(UserAccountManager.USERS);
        return true;

    }

    private void loadFromFile(String fileName) {

        try {
            InputStream inputStream = this.openFileInput(fileName);
            if (inputStream != null) {
                ObjectInputStream input = new ObjectInputStream(inputStream);
                userAccountManager = (UserAccountManager) input.readObject();
                inputStream.close();
            }
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        } catch (ClassNotFoundException e) {
            Log.e("login activity", "File contained unexpected data type: " + e.toString());
        }
    }

    public void saveToFile(String fileName) {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(
                    this.openFileOutput(fileName, MODE_PRIVATE));
            outputStream.writeObject(userAccountManager);
            outputStream.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }


}
