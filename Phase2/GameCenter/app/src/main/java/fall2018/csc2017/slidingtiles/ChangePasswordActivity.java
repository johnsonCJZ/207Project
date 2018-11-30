package fall2018.csc2017.slidingtiles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import es.dmoral.toasty.Toasty;
import fall2018.csc2017.slidingtiles.database.DatabaseHelper;

public class ChangePasswordActivity extends AppCompatActivity {
    TextView username;
    TextView passwordOrEmail;
    TextView newPw;
    TextView confirmPw;
    Button update;
    TextView message;
    UserAccount user;
    UserAccountManager users;
    private DatabaseHelper myDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getAllComponents();
        getAllUser();
        addUpdateButton();
    }

    private void addUpdateButton(){
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(updateButtonPushed()){
                    Intent intent = new Intent(ChangePasswordActivity.this,LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void getAllComponents(){
        setContentView(R.layout.activity_change_password);
        username = findViewById(R.id.username);
        passwordOrEmail = findViewById(R.id.psOrEmail);
        newPw = findViewById(R.id.password);
        confirmPw = findViewById(R.id.confirmpw);
        update = findViewById(R.id.update);
        message = findViewById(R.id.message);
    }

    private void getAllUser(){
        myDB = new DatabaseHelper(this);
        this.users= myDB.selectAccountManager();
    }

    boolean updateButtonPushed() {
        String usernameS = username.getText().toString();
        if(!users.getUserList().contains(usernameS)){
            Toasty.error(getApplicationContext(), "User does not exist.", Toast.LENGTH_SHORT, true).show();
            return false;
        }
        user = myDB.selectUser(usernameS);
        String passwordOrEmailS = this.passwordOrEmail.getText().toString();
        String newPwS = this.newPw.getText().toString();
        String confirmS = confirmPw.getText().toString();
        if (!(user.getEmail().equals(passwordOrEmailS) || user.getPassword().equals(passwordOrEmailS))) {
            Toasty.error(getApplicationContext(), "The current password or email is not correct.", Toast.LENGTH_SHORT, true).show();
            return false;
        }
        if (!(newPwS.equals(confirmS))) {
            Toasty.error(getApplicationContext(), "The new password are not the same with the confirmed password.", Toast.LENGTH_SHORT, true).show();
            return false;
        }
        if (!(validateInfo(newPw.getText().toString(), "^[a-z0-9]{1,9}$"))) {
            Toasty.error(getApplicationContext(), "The format of new password is not correct.", Toast.LENGTH_SHORT, true).show();
            return false;
        }
            user.setPassword(newPwS);
            myDB.updateUser(username.getText().toString(), user);
            Toasty.success(getApplicationContext(), "Your password is successfully updated.", Toast.LENGTH_SHORT, true).show();
            return true;

    }

    private boolean validateInfo(String info, String regex){
        String infoRegex = regex;
        Pattern regexP = Pattern.compile(infoRegex);
        Matcher matcher = regexP.matcher(info);
        return matcher.matches();
    }

}
