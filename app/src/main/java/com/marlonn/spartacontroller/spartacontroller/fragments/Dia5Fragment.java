package com.marlonn.spartacontroller.spartacontroller.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.marlonn.spartacontroller.spartacontroller.R;
import com.marlonn.spartacontroller.spartacontroller.adapter.AdapterAlunos;
import com.marlonn.spartacontroller.spartacontroller.model.Alunos;
import com.marlonn.spartacontroller.spartacontroller.utils.ConfiguraçõesFirebase;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Dia5Fragment extends Fragment {


    private static final int PICK_IMAGE_REQUEST = 71;
    private RecyclerView recyclerView;
    private List<Alunos> alunos;
    private Query databaseAlunos;
    private AdapterAlunos adapter;

    public Dia5Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dia5, container, false);

        idCampo(view);
        preencherLista(view);

        return view;
    }

    private void preencherLista(final View view) {
        alunos = new ArrayList<>();
        databaseAlunos = ConfiguraçõesFirebase.getAlunos().orderByValue();
        databaseAlunos.keepSynced(true);
        databaseAlunos.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                try {

                    alunos.clear();
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Alunos aluno = snapshot.getValue(Alunos.class);
                        if (aluno.getDataVencimento()!=null){
                            if (aluno.getDataVencimento().equals("05")){
                                alunos.add(aluno);
                            }};


                    }
                    adapter.atualiza(alunos);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

        adapter = new AdapterAlunos(getActivity(), alunos);
        recyclerView.setAdapter(adapter);

    }

    public void idCampo(View view) {

        recyclerView = view.findViewById(R.id.recyclerAlunos);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }

}
