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

public class LoginActivity extends AppCompatActivity {

    EditText etUsername;
    EditText etPassword;
    Button bLogIn;
    TextView registerLink;
    UserAccountManager userAccountManager;
    TextView massage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etUsername= (EditText) findViewById(R.id.etUsername);
        etPassword= (EditText) findViewById(R.id.etPassword);
        bLogIn= (Button) findViewById(R.id.bLogin);
        massage =findViewById(R.id.massage);
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
                loadFromFile(UserAccountManager.USERS);
                if (userAccountManager ==null){
                    userAccountManager = new UserAccountManager();
                }
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                UserAccount user = checkUserId(username,password);
                if(user!=null){
                    Intent loginIntent = new Intent(LoginActivity.this, GameCenterActivity.class);
                    Bundle pass = new Bundle();
                    pass.putSerializable("user",user);
                    pass.putSerializable("allUsers", userAccountManager);
                    loginIntent.putExtras(pass);
                    LoginActivity.this.startActivity(loginIntent);
            }}
        });
    }
    private UserAccount checkUserId(String username, String password){
        ArrayList<UserAccount> userList = userAccountManager.getUserList();
        System.out.println(userList);
        for(UserAccount account: userList){
            if(account.getName().equals(username) && account.getPassword().equals(password)){
                massage.setText("Welcome");
                return account;
            }

        }
        massage.setText("Wrong Username or Password");
        return null;}

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




