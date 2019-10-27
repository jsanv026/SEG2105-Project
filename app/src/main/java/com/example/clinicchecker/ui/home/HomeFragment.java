package com.example.clinicchecker.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.clinicchecker.Account;
import com.example.clinicchecker.R;
import com.example.clinicchecker.Singleton;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private TextView txtAccountInfo;
    private Singleton singleton = Singleton.getInstance();
    private Account[] userAccounts = singleton.getAccounts().getAccounts();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        return root;

    }

    public void onViewCreated(View v, Bundle savedInstanceState) {

        txtAccountInfo = (TextView) getView().findViewById(R.id.txtAccountInfo);
        for (int i = 0; i < userAccounts.length - 1; i++) {

            if (userAccounts[i] != null) {

                if (userAccounts[i].equals(singleton.getCurrentLoggedIn())) {

                    txtAccountInfo.append(userAccounts[i].toString());

                }

            }

        }


    }
}