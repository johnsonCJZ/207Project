package fall2018.csc2017.slidingtiles.menu_bars;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;
import fall2018.csc2017.slidingtiles.R;
import fall2018.csc2017.slidingtiles.UserAccount;
import fall2018.csc2017.slidingtiles.UserAccountManager;

public class GameStoreFragment extends Fragment {
    private View view;
    private ImageButton minesweeper;
    private ImageButton slide;
    private ImageButton g2048;
    private UserAccount user;
    private UserAccountManager users;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_game_store_acivity, container, false);
        Toasty.info(getContext(), "Click logo to purchase game!", Toast.LENGTH_SHORT, true).show();
        minesweeper=view.findViewById(R.id.minesweeper);
        slide=view.findViewById(R.id.slide);
        g2048=view.findViewById(R.id.g2048);
        getUsers();
        addMineButton();
        add2048Button();
        addSlideButton();

        return view; }
    private void getUsers(){
        user = (UserAccount) getArguments().getSerializable("user");
        users = (UserAccountManager) getArguments().getSerializable("users");
    }
    private void addMineButton(){
        minesweeper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user.getGames().contains("Minesweeper")){
                    Toasty.warning(getContext(), "You have this game already!", Toast.LENGTH_SHORT, true).show();
                }
                else{
                    user.addGames("Minesweeper");
                    Toasty.success(getContext(), "Thank you for purchasing Minesweeper! Enjoy!", Toast.LENGTH_SHORT, true).show();
                }
                }
            }
        );
    }

    private void add2048Button(){
        g2048.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user.getGames().contains("G2048")){
                    Toasty.warning(getContext(), "You have this game already!", Toast.LENGTH_SHORT, true).show();
                    }
                    else{
                    user.addGames("G2048");
                    Toasty.success(getContext(), "Thank you for purchasing 2048! Enjoy!", Toast.LENGTH_SHORT, true).show();
                    } }
                    }
        );
    }

    private void addSlideButton(){
        slide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user.getGames().contains("Slide")){
                    Toasty.warning(getContext(), "You have this game already!", Toast.LENGTH_SHORT, true).show();
                    }
                    else{
                    user.addGames("Slide");
                    Toasty.success(getContext(), "Thank you for purchasing Slide! Enjoy!", Toast.LENGTH_SHORT, true).show();
                    }
                    }
                    }
        );
    }

}
