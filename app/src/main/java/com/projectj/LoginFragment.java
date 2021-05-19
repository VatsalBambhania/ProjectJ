package com.projectj;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
//import com.projectj.databinding.FragmentLoginBinding;

public class LoginFragment extends Fragment {
//
//    private FragmentLoginBinding binding;
    private EditText userIdEt;
    private EditText passwordEt;
    private Button loginBt;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();

        if (user != null) {
            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, PodcastList.class, null, "PodcastList")
                    .commit();
        }

        userIdEt = view.findViewById(R.id.userIdEt);
        passwordEt = view.findViewById(R.id.passwordEt);
        loginBt = view.findViewById(R.id.checkCred);

        loginBt.setOnClickListener(butView -> {
            String userId = userIdEt.getText().toString().trim();
            String password = passwordEt.getText().toString().trim();
            if(!userId.isEmpty() && !password.isEmpty()){
                Toast.makeText(requireContext(), "Clicked", Toast.LENGTH_SHORT).show();
                auth.signInWithEmailAndPassword(userId, password).addOnCompleteListener(requireActivity(), task->{
                    if(task.isSuccessful()) {
                        requireActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, PodcastList.class, null, "PodcastList")
                                .commit();
                    }
                    else
                        Toast.makeText(requireContext(), "Wrong Creds", Toast.LENGTH_SHORT).show();
                });
            }else{
                Toast.makeText(requireContext(), "Please fill User Id and Password", Toast.LENGTH_SHORT).show();
            }
        });

    }

}