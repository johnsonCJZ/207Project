package fall2018.csc2017.slidingtiles.ui.Games;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;
import fall2018.csc2017.slidingtiles.CreateNewOrLoad2048Activity;
import fall2018.csc2017.slidingtiles.DataHolder;
import fall2018.csc2017.slidingtiles.MineSweeperManager;
import fall2018.csc2017.slidingtiles.R;
import fall2018.csc2017.slidingtiles.UserAccount;
import fall2018.csc2017.slidingtiles.UserAccountManager;
import fall2018.csc2017.slidingtiles.database.DatabaseHelper;

public class G2048Fragment extends Fragment {
    //    private UserAccount user;
//    private UserAccountManager users;
    private ImageButton Image2048;
//    private MineSweeperManager boardManager;
//    private String username;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
//        myDB = new DatabaseHelper(this.getContext());
//        username = (String) DataHolder.getInstance().retrieve("current user");
        //    DatabaseHelper myDB;
        View view = inflater.inflate(R.layout.g2048_fragment, container, false);
        Image2048 = (ImageButton) view.findViewById(R.id.g_2048);
        Toasty.success(getContext(), "Press 2048 logo to start", Toast.LENGTH_LONG, true).show();
        addStartButton();
//        getUser();
        return view;
    }

    private void addStartButton() {
        Image2048.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent tmp = new Intent(getActivity(), CreateNewOrLoad2048Activity.class);
//                Bundle pass = new Bundle();
//                pass.putSerializable("user",user);
//                pass.putSerializable("allUsers", users);
//                pass.putSerializable("personalScoreBoard", this.personalScoreBoard);
//                pass.putSerializable("globalScoreBoard", this.globalScoreBoard);
//                tmp.putExtras(pass);
                startActivity(tmp);
            }

        });
    }

//    private void getUser(){
//        user = myDB.selectUser(username);
//        users = myDB.selectAccountManager();
//    }

}

