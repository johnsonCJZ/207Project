package fall2018.csc2017.slidingtiles.ui.Games;

import android.media.Image;
import android.support.v4.app.Fragment;
import android.arch.lifecycle.ViewModelProviders;
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
import fall2018.csc2017.slidingtiles.R;
import fall2018.csc2017.slidingtiles.UserAccount;
import fall2018.csc2017.slidingtiles.UserAccountManager;

public class MinesweeperFragment extends Fragment {
    private UserAccount user;
    private View view;
    private UserAccountManager users;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.minesweeper_fragment, container, false);
        user = (UserAccount) getArguments().getSerializable("user");
        users = (UserAccountManager) getArguments().getSerializable("allUsers");
        addImageAnim();
        return view;
    }

    private void addImageAnim(){
        ImageView thomas = (ImageView) view.findViewById(R.id.Thomas);
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
