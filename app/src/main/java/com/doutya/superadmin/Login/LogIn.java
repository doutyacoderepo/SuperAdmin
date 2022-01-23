package com.doutya.superadmin.Login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.doutya.superadmin.MainActivity;
import com.doutya.superadmin.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class LogIn extends AppCompatActivity {

    TextInputLayout Phn,Access;
    AppCompatButton LogInButton;
    FirebaseFirestore db;
    FirebaseAuth fAuth;
    String Number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        Phn = findViewById(R.id.SignInPhn);
        FirebaseApp.initializeApp(this);
        fAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        LogInButton = findViewById(R.id.LogInButton);
        Access = findViewById(R.id.SignInAccess);

        LogInButton.setOnClickListener(View -> {
            if (Validate(Phn) && Validate(Access)) {
                ProgressDialog pr = new ProgressDialog(this);
                pr.setMessage("Verifying number");
                pr.setProgressStyle(0);
                pr.setCancelable(false);
                pr.show();

                Number = Phn.getEditText().getText().toString();
                db.collection("SuperAdmin").document(Number).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                pr.dismiss();
                                if (Access.getEditText().getText().toString()
                                .equals(document.getString("AccessCode"))){
                                    LogIn();
                                }else{
                                    Toast.makeText(LogIn.this, "Invalid Access code", Toast.LENGTH_SHORT).show();
                                }

                            } else {
                                pr.dismiss();
                                Toast.makeText(LogIn.this,
                                        "Invalid credentials. Authorization revoked!",
                                        Toast.LENGTH_LONG).show();
                            }
                        } else {
                            pr.dismiss();
                            Log.d("FirebaseAuth:Doutya ", "get failed with ", task.getException());
                        }
                    }
                });

            }
        });



    }

    private boolean Validate(TextInputLayout name) {
        if (name.getEditText().getText().toString().isEmpty())
            name.setError("Field cannot be empty");
        else
            name.setError(null);

        return !name.getEditText().getText().toString().isEmpty();
    }

    private void LogIn() {
        Intent i = new Intent(this, VerifyOtpActivity.class);
        i.putExtra("phone", Phn.getEditText().getText().toString());
        startActivity(i);


    }

    @Override
    protected void onStart() {
        super.onStart();
        if (FirebaseAuth.getInstance().getCurrentUser()!=null){
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }
}