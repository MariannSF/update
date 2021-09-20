package com.example.update;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.renderscript.ScriptGroup;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.update.databinding.FragmentLoginBinding;


public class LoginFragment extends Fragment {


    // TODO: Rename and change types of parameters
    private String email;
    private String pw;

    public LoginFragment() {
        // Required empty public constructor
    }

    FragmentLoginBinding binding;

 /*   public static LoginFragment newInstance(DataServices.Account account) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

DataServices.AccountRequestTask task ;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Login");

        binding.Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //when view created we get access to the editTexts and buttons
                email =binding.editTextName.getText().toString();
                pw = binding.editTextTextPassword.getText().toString();
                task = DataServices.login(email,pw);
                if(!email.equals("") && !pw.equals("")){
                if(task.isSuccessful()){
                    DataServices.Account account =task.getAccount();
                    //call mlistener to go to the new fragment and send data with it
                    mListener.goToAccount(account);
                }else {
                    String  error = task.getErrorMessage();
                    Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
                }}else {
                    Toast.makeText(getActivity(), "Missin Input! ", Toast.LENGTH_SHORT).show();
                }




            }
        });
        binding.buttonCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.goToRegister();

            }
        });
    }
    //needed when fragment is sending data to activity
    //When fragment is attached to Activity
    /*
    We make sure the context is the instance of the interface (loginListener)
    if so then we cast it to an interface type and assign to mListener
     */
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof loginListener){

            mListener = (loginListener) context;

        }
        else {
            throw new RuntimeException(context.toString() + "Must implement loginListener");
        }
    }

    //set up an interface to communicate the data back to the mainActivity
    loginListener mListener;
    public interface loginListener{
        void goToAccount(DataServices.Account account);
        void goToRegister();
    }
}