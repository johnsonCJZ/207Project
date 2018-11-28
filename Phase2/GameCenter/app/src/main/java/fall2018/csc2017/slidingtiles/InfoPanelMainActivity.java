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
import fall2018.csc2017.slidingtiles.database.DatabaseHelper;
import fall2018.csc2017.slidingtiles.drop_down_menu.ContactsFragment;
import fall2018.csc2017.slidingtiles.drop_down_menu.HelpsFragment;
import fall2018.csc2017.slidingtiles.drop_down_menu.SettingsFragment;
import fall2018.csc2017.slidingtiles.menu_bars.GameStoreFragment;
import fall2018.csc2017.slidingtiles.menu_bars.ProfileFragment;
import fall2018.csc2017.slidingtiles.menu_bars.ToolFragment;
import fall2018.csc2017.slidingtiles.ui.Games.G2048Fragment;
import fall2018.csc2017.slidingtiles.ui.Games.MinesweeperFragment;
import fall2018.csc2017.slidingtiles.ui.Games.SlideGameFragment;

public class InfoPanelMainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, MenuItem.OnMenuItemClickListener {
    DatabaseHelper myDB;
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

    private String currentUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.myDB = new DatabaseHelper(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_bar_main_info_panel);
        setContentView(R.layout.nav_header_main_info_panel);
        setContentView(R.layout.activity_main_info_panel);

        getUsers();

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (fragment==null){
            ProfileFragment pp = new ProfileFragment();
            passInfo();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    pp).commit();
            navigationView.setCheckedItem(R.id.profile);
        }
        else {
            switch (fragment) {
                case "Slide":
                    SlideGameFragment s =new SlideGameFragment();
                    passInfo();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            s).commit();
                    navigationView.setCheckedItem(R.id.slide);
                    break;
                case "Mine":
                    MinesweeperFragment m =new MinesweeperFragment();
                    passInfo();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            m).commit();
                    navigationView.setCheckedItem(R.id.mine_sweeper);
                    break;
                case "2048":
                    G2048Fragment g =new G2048Fragment();
                    passInfo();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            g).commit();
                    navigationView.setCheckedItem(R.id.g2048);
                    break;
                case "profile":
                    ProfileFragment p =new ProfileFragment();
                    passInfo();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            p).commit();
                    navigationView.setCheckedItem(R.id.profile);
                    break;
                case "store":
                    GameStoreFragment gs =new GameStoreFragment();
                    passInfo();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            gs).commit();
                    navigationView.setCheckedItem(R.id.game_store);
                    break;
                case "tool":
                    ToolFragment t =new ToolFragment();
                    passInfo();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            t).commit();
                    navigationView.setCheckedItem(R.id.toolbar);
                    break;
                default:
                    ProfileFragment de =new ProfileFragment();
                    passInfo();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            de).commit();
                    navigationView.setCheckedItem(R.id.profile);
            }
        }

        View headerView = navigationView.getHeaderView(0);
        TextView t = headerView.findViewById(R.id.primary_username);
        t.setText(currentUser);
    }

    private void getUsers(){
        Intent intentExtras = getIntent();
        Bundle extra = intentExtras.getExtras();
        currentUser = (String)DataHolder.getInstance().retrieve("current user");
        try {
            this.user = myDB.selectUser(currentUser);
        }
        catch (RuntimeException e){
            myDB = new DatabaseHelper(this);
        }

        this.users = myDB.selectAccountManager();

        try {
            this.fragment = extra.getString("fragment");
        }
        catch (NullPointerException e){
            this.fragment = null;
        }

        // use this to set user name on global
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
        // update info
        getUsers();

        //noinspection SimplifiableIfStatement
        switch (id){
            case R.id.action_settings:
                SettingsFragment s = new SettingsFragment();
                passInfo();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        s).commit();
                break;
            case R.id.action_helps:
                HelpsFragment h = new HelpsFragment();
                passInfo();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        h).commit();
                break;
            case R.id.action_contacts:
                ContactsFragment c = new ContactsFragment();
                passInfo();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        c).commit();
                break;
        }

        return true;
    }

    private void passInfo(){
        user = myDB.selectUser(currentUser); //get updated after user bought games in game store
        myDB.updateAccountManager(users);
        myDB.updateUser(currentUser, user);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        // update info
        getUsers();
        int id = item.getItemId();
        switch (id){
            case R.id.profile:
                ProfileFragment p =new ProfileFragment();
                passInfo();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        p).commit();
                break;
            case R.id.game_store:
                GameStoreFragment gf = new GameStoreFragment();
                passInfo();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        gf).commit();
                break;

            case R.id.nav_manage:
                ToolFragment t = new ToolFragment();
                passInfo();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        t).commit();
                break;

            case R.id.slide:
                if (!user.getGames().contains("Slide")){
                    Toasty.error(getApplicationContext(), "You haven't bought this game!", Toast.LENGTH_SHORT, true).show();
                }
                else{
                    SlideGameFragment s =new SlideGameFragment();
                    passInfo();
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
                    passInfo();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            m).commit();}
                break;

            case R.id.g2048:
                if (!user.getGames().contains("G2048")){
                    Toasty.error(getApplicationContext(), "You haven't bought this game!", Toast.LENGTH_SHORT, true).show();
                }
                else{
                    G2048Fragment g =new G2048Fragment();
                    passInfo();
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
