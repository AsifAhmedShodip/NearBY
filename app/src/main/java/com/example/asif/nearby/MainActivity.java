
package com.example.asif.nearby;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class MainActivity extends AppCompatActivity {

    private EditText mEmailField,mPassField;
    private Button mSignUp,mSignIn;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Temporary
      /*  Intent intent = new Intent(MainActivity.this , CreatEventsActivity.class);
        startActivity(intent);*/

        mEmailField = (EditText) findViewById(R.id.editText4);
        mPassField = (EditText) findViewById(R.id.editText5);
        mSignIn= (Button) findViewById(R.id.button3);
        mSignUp= (Button) findViewById(R.id.button4);
        mAuth = FirebaseAuth.getInstance();
        mSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Sign UP",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this,SignUp.class);
                startActivity(intent);
            }
        });

        mSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });
    }

    private void signIn() {
        String email = mEmailField.getText().toString().trim();
        String pass = mPassField.getText().toString().trim();
        if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(pass))
        {
           /* mprog.setMessage("Signing UP...");
            mprog.show();*/
            mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(this,new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        Toast.makeText(getApplicationContext(),"DONE",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(MainActivity.this,nearby.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Failed",Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }
   /* private static final String TAG = "MAIN";
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private TabLayout mtabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mtabLayout  = (TabLayout) findViewById(R.id.tabs);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);
        mtabLayout.setupWithViewPager(mViewPager);
    }        mViewPager = (ViewPager) findViewById(R.id.container);


    private void setupViewPager(ViewPager viewPager)
    {
        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Attending_Fragment(),"ATTENDING");
        adapter.addFragment(new Interested_Fragment(),"INTERESTED");
        adapter.addFragment(new Not_Intersted_Fragment(),"NOT GOING");
        viewPager.setAdapter(adapter);
    }*/
}
