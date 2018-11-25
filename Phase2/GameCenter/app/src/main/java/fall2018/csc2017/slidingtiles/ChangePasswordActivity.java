//package fall2018.csc2017.slidingtiles;
//
//import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.TextView;
//
//import java.util.ArrayList;
//
//import fall2018.csc2017.slidingtiles.menu_bars.ProfileFragment;
//
//public class ChangePasswordActivity extends AppCompatActivity {
//    TextView username;
//    TextView passwordOrEmail;
//    TextView newPw;
//    TextView confirmPw;
//    Button update;
//    TextView message;
//    UserAccount userAccount;
//    UserAccountManager userAccountManager;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_change_password);
//
//        username = findViewById(R.id.username);
//        passwordOrEmail = findViewById(R.id.psOrEmail);
//        newPw = findViewById(R.id.password);
//        confirmPw = findViewById(R.id.confirmpw);
//        update = findViewById(R.id.update);
//        message = findViewById(R.id.message);
//
//        update.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                updateButtonPushed();
//            }
//        });
////        openFragment();
//
//    }
//
//    boolean updateButtonPushed() {
//        String username = this.username.getText().toString();
//        String pwOrEmail = this.passwordOrEmail.getText().toString();
//        String newPw = this.newPw.getText().toString();
//        String confirm = confirmPw.getText().toString();
////        ArrayList<UserAccount> userList = userAccountManager.getUserList();
//        UserAccount user = userAccount;
////        if (!userList.isEmpty()) {
////            for (UserAccount account : userList) {
////                if ((account.getName().equals(username))) {
////                    user = account;
////                }else{
////                    message.setText("The user with the username doesn't exist");
////                    return false;
////                }
////            }
////        }
//        if (!(user.getEmail() == pwOrEmail || user.getPassword() == pwOrEmail)) {
//            message.setText("The current password or email is not correct.");
//            return false;
//        }
//        if(!(newPw == confirm)){
//            message.setText("The new password are not the same with the confirmed password.");
//            return false;
//        }
//        userAccount.changePassword(newPw);
//        message.setText("Your password is successfully updated.");
//        return true;
//    }
//
////    @Override
////    protected void onResume() {
////        super.onResume();
////        final String sender=this.getIntent().getExtras().getString("SENDER_KEY");
////        if(sender != null)
////        {
////            this.receiveData();
////
////        }
////    }
////
////    private void openFragment()
////    {
////        ProfileFragment pFragment = new ProfileFragment();
////        getSupportFragmentManager().beginTransaction().replace(R.id.container,pFragment).commit();
////    }
////
////    private void receiveData()
////    {
////        Intent i = getIntent();
////        userAccount = (UserAccount)i.getSerializableExtra("user");
////        userAccountManager = (UserAccountManager) i.getSerializableExtra("users");
////    }
//
//
//}
