package com.example.update;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.update.databinding.FragmentAccountBinding;
import com.example.update.databinding.FragmentUpdateBinding;


public class UpdateFragment extends Fragment {

    private static final String ARG_ACCOUNT = "ACCOUNT";
    DataServices.Account mAccount;

    public UpdateFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static UpdateFragment newInstance(DataServices.Account account) {
        UpdateFragment fragment = new UpdateFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_ACCOUNT,account);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mAccount = (DataServices.Account) getArguments().getSerializable(ARG_ACCOUNT);

        }
    }


    FragmentUpdateBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentUpdateBinding.inflate(inflater,container,false);
        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Update");

        binding.editTextUpdateName.setText(mAccount.getName());
        binding.editTextUpdatePassword.setText(mAccount.getPassword());
        binding.textViewUpdateEmail.setText(mAccount.getEmail());
        binding.buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String nameNew =  binding.editTextUpdateName.getText().toString();
                String  pwNew =binding.editTextUpdatePassword.getText().toString();
                if(!nameNew.equals("") && !pwNew.equals("")) {
                    DataServices.AccountRequestTask task = DataServices.update(mAccount, nameNew, pwNew);
                    if (task.isSuccessful()) {
                        //go back to Account
                        mListener.goToAccount(task.getAccount());
                    }
                }else {
                    Toast.makeText(getActivity(), "Missing Input! ", Toast.LENGTH_SHORT).show();

                }


            }
        });
        binding.buttonCancelUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            mListener.UpdateCancel();
            }
        });

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof updateListener){
            mListener = (updateListener) context;
        }else{
            throw new RuntimeException(context.toString() +"must implement UpdateListener");
        }
    }

    updateListener mListener;

    public interface updateListener{
        void goToAccount(DataServices.Account account);
        void UpdateCancel();
    }


}