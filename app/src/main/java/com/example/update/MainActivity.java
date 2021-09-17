package com.example.update;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements LoginFragment.loginListener , AccountFragment.accountListener, UpdateFragment.updateListener,RegisterFragment.RegisterListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.containerView, new LoginFragment())
                .commit();
    }

    @Override
    public void goToAccount(DataServices.Account account) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containerView, AccountFragment.newInstance(account))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void UpdateCancel() {
        getSupportFragmentManager()
                .popBackStack();
    }

    @Override
    public void goToLogin() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containerView, new LoginFragment())
                .commit();
    }

    @Override
    public void goToRegister() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containerView, new RegisterFragment())
                .commit();
    }

    @Override
    public void goToUpdate(DataServices.Account account) {

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containerView, UpdateFragment.newInstance(account))
                .addToBackStack(null)
                .commit();
    }
}