package com.example.update;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.update.databinding.FragmentRegisterBinding;


public class RegisterFragment extends Fragment {



    public RegisterFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static RegisterFragment newInstance(String param1, String param2) {
        RegisterFragment fragment = new RegisterFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }
FragmentRegisterBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRegisterBinding.inflate(inflater,container,false);
        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Register");

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = binding.editTextRegisterName.getText().toString();
                String  email = binding.editTextRegisterEmail.getText().toString();
                String pw = binding.edtTextRegisterPw.getText().toString();

                if(!name.equals("") && !email.equals("") && !pw.equals("")) {
                    DataServices.AccountRequestTask task = DataServices.register(name, email, pw);
                    if (task.isSuccessful()) {
                        mListener.goToAccount(task.getAccount());
                    }else {
                        Toast.makeText(getActivity(), " Login Not Successful! ", Toast.LENGTH_SHORT).show();

                    }
                }else {
                    Toast.makeText(getActivity(), "Missing Input! ", Toast.LENGTH_SHORT).show();

                }
            }
        });
        binding.buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.goToLogin();
            }
        });



    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof RegisterListener){
            mListener = (RegisterListener) context;
        } else {
            throw new RuntimeException(context.toString() +"must implement Listener");
        }
    }

    RegisterListener mListener;
    public interface RegisterListener{
        void goToAccount(DataServices.Account account);
        void goToLogin();

    }
}

