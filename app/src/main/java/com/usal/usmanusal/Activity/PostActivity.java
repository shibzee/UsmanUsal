package com.usal.usmanusal.Activity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.usal.usmanusal.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
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
    private Button publish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       // offer();
        edit_titler=(EditText)findViewById(R.id.edit_title);
        edit_storie=(EditText)findViewById(R.id.edit_story);
        publish=(Button)findViewById(R.id.publish_but);
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

                }
                else{
                   Snackbar.make(view, "No Internet Connection .....", Snackbar.LENGTH_LONG).show();
               }
            }
        });
            //  mEmailEditText=(EditText)findViewById(R.id.to);
      //  mPasswordEditText=(EditText)findViewById(R.id.tot);
//        MagicForm mMagicForm = new MagicForm()
//                .addField(new FormField(mEmailEditText)
//                        .addValidation(new ValidationRegex(Patterns.EMAIL_ADDRESS).setMessage("invalid email")))
//                .addField(new FormField(mPasswordEditText)
//                        .addValidation(new ValidationNotEmpty().setMessage("Required Field")));
//             //   .setListener(this);
    }
    private void offer(){
     //   recyclepost=(RecyclerView)findViewById(R.id.recycler_post);
        mFormBuilder=new FormBuildHelper(getApplicationContext(),recyclepost);

        FormHeader header1 = FormHeader.createInstance().setTitle("Personal Info");
        FormElement element11 = FormElement.createInstance().setType(FormElement.TYPE_EDITTEXT_EMAIL).setTitle("Email").setHint("Enter Email");
        FormElement element12 = FormElement.createInstance().setType(FormElement.TYPE_EDITTEXT_PHONE).setTitle("Phone").setValue("+8801712345678");

        FormHeader header2 = FormHeader.createInstance().setTitle("Family Info");
        FormElement element21 = FormElement.createInstance().setType(FormElement.TYPE_EDITTEXT_TEXT_SINGLELINE).setTitle("Location").setValue("Dhaka");
        FormElement element22 = FormElement.createInstance().setType(FormElement.TYPE_EDITTEXT_TEXT_MULTILINE).setTitle("Address");
        FormElement element23 = FormElement.createInstance().setType(FormElement.TYPE_EDITTEXT_NUMBER).setTitle("Zip Code").setValue("1000");

        FormHeader header3 = FormHeader.createInstance().setTitle("Schedule");
        FormElement element31 = FormElement.createInstance().setType(FormElement.TYPE_PICKER_DATE).setTitle("Date");
        FormElement element32 = FormElement.createInstance().setType(FormElement.TYPE_PICKER_TIME).setTitle("Time");
        FormElement element33 = FormElement.createInstance().setType(FormElement.TYPE_EDITTEXT_PASSWORD).setTitle("Password").setValue("abcd1234");
        List<FormObject> formItems = new ArrayList<>();
        formItems.add(header1);
        formItems.add(element11);
        formItems.add(element12);
        formItems.add(header2);
        formItems.add(element21);
        formItems.add(element22);
        formItems.add(element23);
        formItems.add(header3);
        formItems.add(element31);
        formItems.add(element32);
        formItems.add(element33);

        mFormBuilder.addFormElements(formItems);
        mFormBuilder.refreshView();

    }
    private boolean isOnline() {
        ConnectivityManager cm=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo=cm.getActiveNetworkInfo();
        return (netInfo!=null && netInfo.isConnected());
    }
}
