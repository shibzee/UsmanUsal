package com.usal.usmanusal.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ekalips.fancybuttonproj.FancyButton;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.usal.usmanusal.MainActivity;
import com.usal.usmanusal.Model.Idea;
import com.usal.usmanusal.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;
import julianfalcionelli.magicform.MagicForm;
import julianfalcionelli.magicform.base.FormField;
import julianfalcionelli.magicform.validation.ValidationNotEmpty;
import julianfalcionelli.magicform.validation.ValidationRegex;
import me.riddhimanadib.formmaster.helper.FormBuildHelper;
import me.riddhimanadib.formmaster.model.FormElement;
import me.riddhimanadib.formmaster.model.FormHeader;
import me.riddhimanadib.formmaster.model.FormObject;

public class PostActivity extends AppCompatActivity {
private RecyclerView recyclepost;
    private FormBuildHelper mFormBuilder;
    private EditText mEmailEditText;
    private EditText mPasswordEditText;
    private EditText edit_titler;
    private EditText edit_storie;
    //private Button publish;
    private Map<String,Object> map=new HashMap<>();
    private DatabaseReference databaseReference;
    private DatabaseReference reference;
    private FancyButton publish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       // offer();
        edit_titler=(EditText)findViewById(R.id.edit_title);
        edit_storie=(EditText)findViewById(R.id.edit_story);
       // publish=(Button)findViewById(R.id.publish_but);
       publish = (FancyButton) findViewById(R.id.publish_but);
        publish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(TextUtils.isEmpty(edit_storie.getText().toString().trim())&& TextUtils.isEmpty(edit_titler.getText().toString().trim())){
                Snackbar.make(view, "Both fields are empty", Snackbar.LENGTH_LONG).show();
            }
               else if (TextUtils.isEmpty(edit_titler.getText().toString().trim())){
                   Snackbar.make(view, "Title field is empty", Snackbar.LENGTH_LONG).show();
               }
               else if(TextUtils.isEmpty(edit_storie.getText().toString().trim())){
                   Snackbar.make(view, "Story field is empty", Snackbar.LENGTH_LONG).show();
               }
                else if(!(TextUtils.isEmpty(edit_titler.getText().toString().trim()))&&!(TextUtils.isEmpty(edit_storie.getText().toString().trim()))&&isOnline()){
                  // Snackbar.make(view, "Fiels  Empty", Snackbar.LENGTH_LONG).show();
                     databaseReference= FirebaseDatabase.getInstance().getReference().child("Articlestimer");
                     map.put("title", (String)edit_titler.getText().toString());
                     map.put("article",(String)edit_storie.getText().toString());
                     map.put("date", new Date().getTime());
                   publish.collapse();
                   //progressDoalog.show();
                     databaseReference.push().setValue(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                         @Override
                         public void onSuccess(Void aVoid) {
                           //  progressDoalog.dismiss();
                             publish.expand();
                             Toasty.success(getApplicationContext(), "Successfully Uploaded!", Toast.LENGTH_SHORT, true).show();
                             edit_titler.setText(null);
                             edit_storie.setText(null);
                             // Toast.makeText(getApplicationContext(),"Succesfully Uploaded",Toast.LENGTH_SHORT).show();
                         }
                     }).addOnFailureListener(new OnFailureListener() {
                         @Override
                         public void onFailure(@NonNull Exception e) {
                             Toasty.error(getApplicationContext(), "Not uploaded try again.", Toast.LENGTH_SHORT, true).show();
                            // Toast.makeText(getApplicationContext(),"Not uploaded make sure you have internet connection",Toast.LENGTH_SHORT);
                         }
                     });
                }
                else{
                   Snackbar.make(view, "No Internet Connection .....", Snackbar.LENGTH_LONG).show();
               }
            }
        });

    }
    private boolean isOnline() {
        ConnectivityManager cm=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo=cm.getActiveNetworkInfo();
        return (netInfo!=null && netInfo.isConnected());
    }



}
