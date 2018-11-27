package fall2018.csc2017.slidingtiles.ui.Games;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;
import fall2018.csc2017.slidingtiles.DataHolder;
import fall2018.csc2017.slidingtiles.MineSweeperBoardManager;
import fall2018.csc2017.slidingtiles.MinesweeperDifficultyActivity;
import fall2018.csc2017.slidingtiles.R;
import fall2018.csc2017.slidingtiles.UserAccount;
import fall2018.csc2017.slidingtiles.UserAccountManager;
import fall2018.csc2017.slidingtiles.database.DatabaseHelper;

public class MinesweeperFragment extends Fragment {
    private UserAccount user;
    private View view;
    private UserAccountManager users;
    private ImageView thomas;
    private MineSweeperBoardManager boardManager;
    private DatabaseHelper myDB;
    private String username;

    public MinesweeperFragment(){}


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        myDB = new DatabaseHelper(this.getContext());
        username = (String) DataHolder.getInstance().retrieve("current user");
        view = inflater.inflate(R.layout.minesweeper_fragment, container, false);
        getUser();
        addImageAnim();
        addStartButton();
        // set current game
        DataHolder.getInstance().save("current game", "Mine");
        return view;
    }

    private void addStartButton(){
        thomas.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent tmp = new Intent(getActivity(), MinesweeperDifficultyActivity.class);
                startActivity(tmp);
            }

        });
    }

    private void getUser(){
        user= myDB.selectUser(username);
        users= myDB.selectAccountManager();
    }

    private void addImageAnim(){
        thomas = (ImageView) view.findViewById(R.id.Thomas);
        Animation rotate = AnimationUtils.loadAnimation(getContext(),R.anim.anims);
        thomas.setAnimation(rotate);

        rotate.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Toasty.success(getContext(), "Press smiley THO-MAX to start", Toast.LENGTH_LONG, true).show();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
