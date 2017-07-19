package com.usal.usmanusal;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.fondesa.recyclerviewdivider.RecyclerViewDivider;
import com.usal.usmanusal.Activity.PostActivity;
import com.usal.usmanusal.Adapters.MyRecyclerAdapter;
import com.usal.usmanusal.Model.Idea;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
private RecyclerView recycle;
private List<Idea> idealist=new ArrayList<>();
private  MyRecyclerAdapter madapter;
private RecyclerViewDivider divider;
long time=new Date().getTime();
//private
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recycle=(RecyclerView)findViewById(R.id.recyclerV);
        recycle.setLayoutManager(new LinearLayoutManager(this));
        divider=RecyclerViewDivider.with(this)
              //  .color(Color.RED)
               // .drawable(this.getResources().getDrawable(R.drawable.horizontal_div))
                //.drawable(ContextCompat.getDrawable(this,R.drawable.horizontal_div))
                .size(1)
                .tint(Color.BLACK)
                .build();
        divider.addTo(recycle);

      //  madapter.notifyDataSetChanged();
       madapter = new MyRecyclerAdapter(idealist);
        recycle.setAdapter(madapter);
        upper();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Intent intent =new Intent(MainActivity.this, PostActivity.class);
                startActivity(intent);
//                long ntime=new Date().getTime();
//                idealist.add(new Idea((String) DateFormat.format("dd-MM-yy (hh:mm:ss)",ntime)));
//                madapter.notifyDataSetChanged();


            }
        });
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private void upper(){

        idealist.add(new Idea("One"));
        idealist.add(new Idea("Two"));
        idealist.add(new Idea("Three"));
        idealist.add(new Idea((String) DateFormat.format("dd-MM-yy (hh:mm:ss)",time)));
        madapter.notifyDataSetChanged();
    }

}
