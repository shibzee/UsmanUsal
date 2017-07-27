package com.usal.usmanusal.ContextualBar;


import android.support.v7.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by Sheriffdeen on 25/07/2017.
 */

public class ActionBarCallBack implements ActionMode.Callback {
    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        return false;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        return false;
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        return false;
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {

    }
}
