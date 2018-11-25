package fall2018.csc2017.slidingtiles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import es.dmoral.toasty.Toasty;
import fall2018.csc2017.slidingtiles.database.DatabaseHelper;

public class RegisterActivity extends AppCompatActivity {
    DatabaseHelper myDB = new DatabaseHelper(this);
    EditText username;
    EditText password;
    EditText confirm;
    EditText email;
    EditText age;
    Button bRegister;
    UserAccountManager userAccountManager;
    UserAccount newUser;
    String usernameS;
    String passwordS;
    String confirmPWS;
    String emailS;
    String ageS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadFromFile();
        if (userAccountManager == null){
            userAccountManager = new UserAccountManager();
        }
        setContentView(R.layout.activity_register);
        updateInfo();

        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateInfo();
                if(registerButtonPushed()) {
                    boolean update = myDB.createAndInsertNew(newUser.getName(), myDB.convertToJson(newUser));
                    if (update) {
                        Intent registerIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                        RegisterActivity.this.startActivity(registerIntent);
                    }
                    else {
                        Toasty.success(getApplicationContext(), "Please Try Again", Toast.LENGTH_SHORT, true).show();
                    }
                }
            }
        });
    }

    private void updateInfo(){
        username = findViewById(R.id.username);
        usernameS = this.username.getText().toString();
        password = findViewById(R.id.password);
        passwordS = this.password.getText().toString();
        confirm = findViewById(R.id.confirmpw);
        confirmPWS = confirm.getText().toString();
        email = findViewById(R.id.email);
        emailS = email.getText().toString();
        age = findViewById(R.id.age);
        ageS = age.getText().toString();
        bRegister = findViewById(R.id.bRegister);
    }

    /**
     * Return true when a new account is registered, return false when a username is taken or
     * the confirming password does not match.
     * @return a boolean showing whether an account is successfully registered
     */
    private boolean registerButtonPushed() {
        ArrayList<String> userList = userAccountManager.getUserList();
        newUser = new UserAccount(usernameS, passwordS);
        TextView message = findViewById(R.id.message);
        if(!(ageS.equals(""))){
            Integer ageI = Integer.parseInt(ageS);
            newUser.setAge(ageI);
        }
        if (!email.equals("")){
            newUser.setEmail(emailS);
        }
        if (!userList.isEmpty()){
            for (String account : userList) {
                if (account.equals(username)) {
                    message.setText("this name is taken.");
                    return false;
                }
            }}
        if (!passwordS.equals(confirmPWS)) {
            message.setText("password doesn't match.");
            return false;
        }
        if(!validateInfo(usernameS, "^[a-z]{3,7}$")){
            message.setText("Illegal input of username.");
            return false;
        }
        if(!(validateInfo(passwordS, "^[a-z0-9]{1,9}$"))){
            message.setText("Illegal input of password.");
            return false;
        }
        if(!validateInfo(emailS, "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$")){
            message.setText("Illegal input of  email address.");
            return false;
        }
        if(!validateInfo(ageS,"^[1-9][0-9]?$")){
            message.setText("Illegal input of age.");
            return false;
        }
        userAccountManager.addUser(newUser.getName());
        saveToFile(UserAccountManager.USERS);
        return true;
    }

    private boolean validateInfo(String info, String regex){
        String infoRegex = regex;
        Pattern regexP = Pattern.compile(infoRegex);
        Matcher matcher = regexP.matcher(info);
        return matcher.matches();
    }

    /**
     * Load from pre-saved .ser file.
     */
    private void loadFromFile() {

        try {
            InputStream inputStream = this.openFileInput(UserAccountManager.USERS);
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

    /**
     * Save to file fileName.
     * @param fileName the file to save
     */
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
