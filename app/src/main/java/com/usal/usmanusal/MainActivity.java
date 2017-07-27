package com.usal.usmanusal;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.ParseException;
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
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.fondesa.recyclerviewdivider.RecyclerViewDivider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.usal.usmanusal.Activity.PostActivity;
import com.usal.usmanusal.Adapters.MyRecyclerAdapter;
import com.usal.usmanusal.Model.Idea;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
private RecyclerView recycle;
private List<Idea> idealist=new ArrayList<>();
private  MyRecyclerAdapter madapter;
private RecyclerViewDivider divider;
long time=new Date().getTime();
 private DatabaseReference reference;
    private FirebaseRecyclerAdapter<Idea, ArticleViewHolder> adapter;

    //private
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recycle=(RecyclerView)findViewById(R.id.recyclerV);
      //  recycle.setLayoutManager(new LinearLayoutManager(this));
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
         //recycle.setAdapter(adapter);
        reference= FirebaseDatabase.getInstance().getReference().child("Articlestimer");
        reference.keepSynced(true);
        adapter=new FirebaseRecyclerAdapter<Idea, ArticleViewHolder>(
                Idea.class,
                R.layout.item_row,
                ArticleViewHolder.class,
                reference

        ) {
            @Override
            protected void populateViewHolder(ArticleViewHolder viewHolder, Idea model, int position) {
                long time=new Date().getTime();
                viewHolder.setArticle(model.getArticle());

                viewHolder.setPost_time(model.getDate());
                viewHolder.setTitle(model.getTitle());
                viewHolder.mview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });
                viewHolder.mview.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        return false;
                    }
                });

            }

        };
        recycle.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recycle.setLayoutManager(linearLayoutManager);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recycle.setHasFixedSize(true);

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
    public static class ArticleViewHolder extends RecyclerView.ViewHolder {
      //  TextView title;
        TextView article;
        TextView date;
        View mview;

        public ArticleViewHolder(View v) {
            super(v);
            mview=v;
          //  title = (TextView) mview.findViewById(R.id.row_title);
            //article = (TextView) mview.findViewById(R.id.row_art);
          //  date = (TextView) mview.findViewById(R.id.row_date);
        }
        public void setPost_time(Long post_time){

            TextView time = (TextView) mview.findViewById(R.id.row_date);
            SimpleDateFormat sfd = new SimpleDateFormat("h:mm a dd-MM-yyyy ");

            try {
                time.setText(sfd.format(new Date(post_time)));
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }

        public void setTitle(String titl) {
           TextView title = (TextView) mview.findViewById(R.id.row_title);
            title.setText(titl);
        }

        public void setArticle(String article) {
          TextView marticle = (TextView) mview.findViewById(R.id.row_art);
            marticle.setText(article);
        }
    }

}
