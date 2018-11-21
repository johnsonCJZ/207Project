package fall2018.csc2017.slidingtiles;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.storage.StorageManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;
import fall2018.csc2017.slidingtiles.drop_down_menu.ContactsFragment;
import fall2018.csc2017.slidingtiles.drop_down_menu.HelpsFragment;
import fall2018.csc2017.slidingtiles.drop_down_menu.SettingsFragment;
import fall2018.csc2017.slidingtiles.menu_bars.GameStoreFragment;
import fall2018.csc2017.slidingtiles.menu_bars.ProfileFragment;
import fall2018.csc2017.slidingtiles.menu_bars.ToolFragment;
import fall2018.csc2017.slidingtiles.ui.Games.G2048Fragment;
import fall2018.csc2017.slidingtiles.ui.Games.MinesweeperFragment;
import fall2018.csc2017.slidingtiles.ui.Games.SlideGameFragment;

public class MainInfoPanelActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, MenuItem.OnMenuItemClickListener {
    Toolbar toolbar;
    DrawerLayout drawer;
    /**
     * The current user playing the game.
     */
    private UserAccount user;
    /**
     * UserAccountManager keep record of all users data.
     */
    private UserAccountManager users;

    private String fragment;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_bar_main_info_panel);
        setContentView(R.layout.nav_header_main_info_panel);
        setContentView(R.layout.activity_main_info_panel);

        getUsers();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (fragment==null){
            ProfileFragment pp = new ProfileFragment();
            pp.setArguments(passInfo());
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    pp).commit();
            navigationView.setCheckedItem(R.id.profile);
        }
        else {
            switch (fragment) {
                case "Slide":
                    SlideGameFragment s =new SlideGameFragment();
                    s.setArguments(passInfo());
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            s).commit();
                    navigationView.setCheckedItem(R.id.slide);
                    break;
                case "Mine":
                    MinesweeperFragment m =new MinesweeperFragment();
                    m.setArguments(passInfo());
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            m).commit();
                    navigationView.setCheckedItem(R.id.mine_sweeper);
                    break;
                case "2048":
                    G2048Fragment g =new G2048Fragment();
                    g.setArguments(passInfo());
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            g).commit();
                    navigationView.setCheckedItem(R.id.g2048);
                    break;
                case "profile":
                    ProfileFragment p =new ProfileFragment();
                    p.setArguments(passInfo());
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            p).commit();
                    navigationView.setCheckedItem(R.id.profile);
                    break;
                case "store":
                    GameStoreFragment gs =new GameStoreFragment();
                    gs.setArguments(passInfo());
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            gs).commit();
                    navigationView.setCheckedItem(R.id.game_store);
                    break;
                case "tool":
                    ToolFragment t =new ToolFragment();
                    t.setArguments(passInfo());
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            t).commit();
                    navigationView.setCheckedItem(R.id.toolbar);
                    break;
                default:
                    ProfileFragment de =new ProfileFragment();
                    de.setArguments(passInfo());
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            de).commit();
                    navigationView.setCheckedItem(R.id.profile);
            }
        }

        View headerView = navigationView.getHeaderView(0);
        TextView t = (TextView) headerView.findViewById(R.id.primary_username);
        t.setText(user.getName());
    }

    private void getUsers(){
        Intent intentExtras = getIntent();
        Bundle extra = intentExtras.getExtras();
        this.user =(UserAccount) extra.getSerializable("user");
        this.users = (UserAccountManager) extra.getSerializable("allUsers");
        this.fragment = (String) extra.getString("fragment");
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_info_panel, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id){
            case R.id.action_settings:
                SettingsFragment s = new SettingsFragment();
                s.setArguments(passInfo());
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        s).commit();
                break;
            case R.id.action_helps:
                HelpsFragment h = new HelpsFragment();
                h.setArguments(passInfo());
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        h).commit();
                break;
            case R.id.action_contacts:
                ContactsFragment c = new ContactsFragment();
                c.setArguments(passInfo());
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        c).commit();
                break;
        }

        return true;
    }

    private Bundle passInfo(){
        Bundle pass = new Bundle();
        pass.putSerializable("user",this.user);
        pass.putSerializable("allUsers", this.users);
        return pass;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        int id = item.getItemId();
        switch (id){
            case R.id.profile:
                ProfileFragment p =new ProfileFragment();
                p.setArguments(passInfo());
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        p).commit();
                break;
            case R.id.game_store:
                GameStoreFragment gf = new GameStoreFragment();
                gf.setArguments(passInfo());
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        gf).commit();
                break;

            case R.id.nav_manage:
                ToolFragment t = new ToolFragment();
                t.setArguments(passInfo());
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        t).commit();
                break;

            case R.id.slide:
                if (!user.getGames().contains("Slide")){
                    Toasty.error(getApplicationContext(), "You haven't bought this game!", Toast.LENGTH_SHORT, true).show();
                }
                else{
                    SlideGameFragment s =new SlideGameFragment();
                    s.setArguments(passInfo());
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            s).commit();
                }
                break;

            case R.id.mine_sweeper:
                if (!user.getGames().contains("Minesweeper")){
                    Toasty.error(getApplicationContext(), "You haven't bought this game!", Toast.LENGTH_SHORT, true).show();
                }
                else{
                    MinesweeperFragment m =new MinesweeperFragment();
                    m.setArguments(passInfo());
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            m).commit();}
                break;

            case R.id.g2048:
                if (!user.getGames().contains("G2048")){
                    Toasty.error(getApplicationContext(), "You haven't bought this game!", Toast.LENGTH_SHORT, true).show();
                }
                else{
                    G2048Fragment g =new G2048Fragment();
                    g.setArguments(passInfo());
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            g).commit();}
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }
}
