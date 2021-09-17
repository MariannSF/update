package com.example.update;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.update.databinding.FragmentAccountBinding;

public class AccountFragment extends Fragment {

    private static final String ARG_ACCOUNT = "ACCOUNT";
    DataServices.Account mAccount;

    public AccountFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static AccountFragment newInstance(DataServices.Account account) {
        AccountFragment fragment = new AccountFragment();
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


    FragmentAccountBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAccountBinding.inflate(inflater, container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Account");

        binding.textViewAccountName.setText(mAccount.getName());
        binding.buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mlistener.goToUpdate(mAccount);
            }
        });
        binding.buttonLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mlistener.goToLogin();
            }
        });
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if(context instanceof accountListener){
            mlistener = (accountListener) context;
        } else {
            throw new RuntimeException(context.toString()+ "must be implementig UpdateListener");
        }
    }

    accountListener mlistener;

    public interface accountListener{
        void goToUpdate(DataServices.Account account);
        void goToLogin();

    }
}