package fall2018.csc2017.slidingtiles;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import fall2018.csc2017.slidingtiles.database.DatabaseHelper;
import fall2018.csc2017.slidingtiles.menu_bars.ProfileFragment;

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
    private String usernameS;


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
                updateButtonPushed();
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
        usernameS=(String) DataHolder.getInstance().retrieve("current user");
        this.user=myDB.selectUser(usernameS);
        this.users= myDB.selectAccountManager();
    }

    boolean updateButtonPushed() {
        String passwordOrEmailS = this.passwordOrEmail.getText().toString();
        String newPwS = this.newPw.getText().toString();
        String confirmS = confirmPw.getText().toString();
        if (!(user.getEmail() == passwordOrEmailS || user.getPassword() == passwordOrEmailS)) {
            message.setText("The current password or email is not correct.");
            return false;
        }
        if(!(newPwS.equals(confirmS))){
            message.setText("The new password are not the same with the confirmed password.");
            return false;
        }
        user.changePassword(newPwS);
        message.setText("Your password is successfully updated.");
        return true;
    }

}
