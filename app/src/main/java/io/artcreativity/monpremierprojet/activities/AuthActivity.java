package io.artcreativity.monpremierprojet.activities;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthSettings;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;


import java.util.concurrent.TimeUnit;

import io.artcreativity.monpremierprojet.R;
import io.artcreativity.monpremierprojet.databinding.ActivityAuthBinding;
import io.artcreativity.monpremierprojet.ui.AuthCallback;
import io.artcreativity.monpremierprojet.ui.auth.PhoneDefineFragment;

public class AuthActivity extends AppCompatActivity implements AuthCallback {

    private AppBarConfiguration appBarConfiguration;
    private ActivityAuthBinding binding;
    private NavController navController;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    PhoneAuthOptions options;
    String phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAuthBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_auth);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);


    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_auth);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    private void buildOption(){
        options = PhoneAuthOptions.newBuilder(auth)
                .setPhoneNumber(phoneNumber)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(this)
                .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onCodeSent(String verificationId,
                                           PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        // Save the verification id somewhere
                        Log.d("TAG", "onCodeSent: " + verificationId);
                        Bundle bundle = new Bundle();
                        bundle.putString("phoneNumber", phoneNumber);
                        navController.navigate(R.id.action_First2Fragment_to_Second2Fragment, bundle);
                    }

                    @Override
                    public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                        // Sign in with the credential
                        // ...
                        Log.d("TAG", "onVerificationCompleted: " + phoneAuthCredential.zza());
                    }

                    @Override
                    public void onVerificationFailed(FirebaseException e) {
                        // ...
                        Toast.makeText(getApplicationContext(), "Erreur d'envoi de code", Toast.LENGTH_SHORT).show();
                    }
                })
                .build();
    }

    @Override
    public void sendMessage(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        buildOption();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    @Override
    public void verification(String code) {
        FirebaseAuthSettings firebaseAuthSettings = auth.getFirebaseAuthSettings();

// Configure faking the auto-retrieval with the whitelisted numbers.
        firebaseAuthSettings.setAutoRetrievedSmsCodeForPhoneNumber(phoneNumber, code);
        PhoneAuthProvider.verifyPhoneNumber(options);
    }
}