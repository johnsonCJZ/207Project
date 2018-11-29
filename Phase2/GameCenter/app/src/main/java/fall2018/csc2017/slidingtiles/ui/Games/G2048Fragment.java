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
import fall2018.csc2017.slidingtiles.DataHolder;
import fall2018.csc2017.slidingtiles.Game2048BoardManager;
import fall2018.csc2017.slidingtiles.Game2048MainActivity;
import fall2018.csc2017.slidingtiles.R;
import fall2018.csc2017.slidingtiles.UserAccount;
import fall2018.csc2017.slidingtiles.UserAccountManager;
import fall2018.csc2017.slidingtiles.database.DatabaseHelper;

public class G2048Fragment extends Fragment {
    private ImageButton Image2048;

    DatabaseHelper myDB;

    private Game2048BoardManager boardManager;

    private UserAccount user;

    private UserAccountManager users;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        this.myDB = new DatabaseHelper(getContext());
        View view = inflater.inflate(R.layout.g2048_fragment, container, false);
        Image2048 = (ImageButton) view.findViewById(R.id.g_2048);
        Toasty.success(getContext(), "Press 2048 logo to start", Toast.LENGTH_LONG, true).show();
        addStartButton();
        DataHolder.getInstance().save("current game", "2048");
        getUser();
        return view;
    }

    private void addStartButton() {
        Image2048.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boardManager = user.getSpecific2048History("resumeHistory2048");
                if (boardManager != null) {
                    switchToGame();
                    } else {
                    boardManager = new Game2048BoardManager();
                    user.setGame2048History("resumeHistory2048", null);
                    switchToGame();
                    } }
        });
        }


    private void switchToGame() {
        Intent tmp = new Intent(getContext(), Game2048MainActivity.class);
        Bundle pass = new Bundle();
        pass.putSerializable("boardManager", boardManager);
        tmp.putExtras(pass);
        startActivity(tmp);
    }

    private void getUser() {
        String currentUser = (String)DataHolder.getInstance().retrieve("current user");
        this.user = myDB.selectUser(currentUser);
        this.users = myDB.selectAccountManager();
    }

}

