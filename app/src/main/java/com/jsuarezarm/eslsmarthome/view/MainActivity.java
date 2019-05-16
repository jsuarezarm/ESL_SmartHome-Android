package com.jsuarezarm.eslsmarthome.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.jsuarezarm.eslsmarthome.R;
import com.jsuarezarm.eslsmarthome.adapter.ActionAdapter;
import com.jsuarezarm.eslsmarthome.controller.Database;
import com.jsuarezarm.eslsmarthome.dialog.ChangePasswordDialog;
import com.jsuarezarm.eslsmarthome.dialog.DeleteUserDialog;
import com.jsuarezarm.eslsmarthome.dialog.LogOutDialog;
import com.jsuarezarm.eslsmarthome.model.ActionItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<ActionItem> actions;
    private RecyclerView recyclerView;
    private ActionAdapter actionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


        actions = new ArrayList<>();
        actionAdapter = new ActionAdapter(actions);

        recyclerView.setAdapter(actionAdapter);

        Database.updateActions(actions, actionAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
        if(id == R.id.action_logout) {
            LogOutDialog logOutDialog = new LogOutDialog(this, this);
            logOutDialog.show();
        } else if(id == R.id.action_change_password) {
            ChangePasswordDialog changePasswordDialog = new ChangePasswordDialog(this, this);
            changePasswordDialog.show();
        } else if(id == R.id.action_delete_account) {
            DeleteUserDialog deleteUserDialog = new DeleteUserDialog(this, this);
            deleteUserDialog.show();
        }

        return super.onOptionsItemSelected(item);
    }
}
