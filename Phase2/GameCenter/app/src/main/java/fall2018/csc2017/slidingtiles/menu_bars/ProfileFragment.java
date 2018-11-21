package fall2018.csc2017.slidingtiles.menu_bars;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import fall2018.csc2017.slidingtiles.R;
import fall2018.csc2017.slidingtiles.UserAccount;
import fall2018.csc2017.slidingtiles.UserAccountManager;

import static android.content.Context.MODE_PRIVATE;

public class ProfileFragment extends Fragment {
    private View view;
    private TextView username;
    private TextView age;
    private TextView email;
    private UserAccount userAccount;
//    private UserAccountManager userAccountManager;
    private boolean isEnablbed = false;
    private Button editProfile;
    private Button changePs;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.profile_fragment, container, false);
        username = view.findViewById(R.id.username);
        age = view.findViewById(R.id.age);
        email = view.findViewById(R.id.email);
        editProfile = view.findViewById(R.id.editButton);
        changePs = view.findViewById(R.id.changePs);
        setEnablbed(isEnablbed);

        getUserAccount();
        setContents();

        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editProfileButtonPushed();
            }
        });

        return view;
    }

    private void getUserAccount() {
        userAccount = (UserAccount) getArguments().getSerializable("user");
//        userAccountManager = (UserAccountManager) getArguments().getSerializable("users");
    }

    private void setContents() {
        username.setText(userAccount.getName());
        if (userAccount.getAge() != null) {
            age.setText(userAccount.getAge().toString());
        }
        email.setText(userAccount.getEmail());

    }

    private void setEnablbed(boolean isEnabled) {
        username.setEnabled(isEnabled);
        age.setEnabled(isEnabled);
        email.setEnabled(isEnabled);
    }

    private void editProfileButtonPushed() {
        if (isEnablbed == false) {
            isEnablbed = true;
            setEnablbed(isEnablbed);
        } else {
            isEnablbed = false;
            setEnablbed(isEnablbed);
            String newAge = view.findViewById(R.id.age).toString();
//            ArrayList<UserAccount> userList = userAccountManager.getUserList();
//            if (!userList.isEmpty()) {
//                for (UserAccount account : userList) {
//                    if (account.getName().equals(username)) {
////                        textView.setText("this name is taken");
//                    }
//                }
//            } else {
//                userAccount.changeName(view.findViewById(R.id.username).toString());
//            }
            userAccount.setAge(Integer.getInteger(newAge));
            userAccount.setEmail(email.toString());
//            saveToFile(UserAccountManager.USERS);
        }
    }

    private void changePWButtonPushed() {
        //new page
    }


//    public void saveToFile(String fileName) {
//        try {
//            ObjectOutputStream outputStream = new ObjectOutputStream(
//                    this.openFileOutput(fileName, MODE_PRIVATE));
//            outputStream.writeObject(users);
//            outputStream.close();
//        } catch (IOException e) {
//            Log.e("Exception", "File write failed: " + e.toString());
//        }
//        }
    }

