package com.marlonn.spartacontroller.spartacontroller.views;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.marlonn.spartacontroller.spartacontroller.DAO.DataBaseDAO;
import com.marlonn.spartacontroller.spartacontroller.R;
import com.marlonn.spartacontroller.spartacontroller.adapter.AdapterAlunos;
import com.marlonn.spartacontroller.spartacontroller.fragments.Dia15Fragment;
import com.marlonn.spartacontroller.spartacontroller.fragments.Dia25Fragment;
import com.marlonn.spartacontroller.spartacontroller.fragments.Dia5Fragment;
import com.marlonn.spartacontroller.spartacontroller.model.Alunos;
import com.marlonn.spartacontroller.spartacontroller.model.Mensalidade;
import com.marlonn.spartacontroller.spartacontroller.utils.ConfiguraçõesFirebase;
import com.marlonn.spartacontroller.spartacontroller.utils.FragmentoUtils;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ValueEventListener ValueEventListener;
    FloatingActionButton addAluno;
    private TextView renda;
    private DatabaseReference teste;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_dia5:
                    FragmentoUtils.replace(MainActivity.this, new Dia5Fragment());
                    return true;
                case R.id.navigation_dia15:
                    FragmentoUtils.replace(MainActivity.this, new Dia15Fragment());
                    return true;
                case R.id.navigation_dia25:
                    FragmentoUtils.replace(MainActivity.this, new Dia25Fragment());
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addAluno = findViewById(R.id.btnAdicionarAluno);
        renda = findViewById(R.id.rendaTotal);
        teste = FirebaseDatabase.getInstance().getReference();
        teste.child("mensalidade").child("mensalidadeTotal").getDatabase();

        recuperarValor();

        FragmentoUtils.replace(MainActivity.this, new Dia5Fragment());

        AdicionarAluno();
        
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }


    public void AdicionarAluno() {
        addAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                abrirDialogo();

            }
        });
    }

    private void abrirDialogo() {

        AdicionarAlunosDialog adicionarAlunosDialog = new AdicionarAlunosDialog();
        adicionarAlunosDialog.show(getSupportFragmentManager(), " Alunos");

    }

    private void recuperarValor (){

        teste = teste.child( "mensalidade" ).child("mensalidadeTotal");
        ValueEventListener = teste.addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        Mensalidade mensalidade = dataSnapshot.getValue( Mensalidade.class );

                        if (mensalidade != null) {
                            String mensalide = (mensalidade.getValor());


                            //Configura valores recuperados

                            renda.setText( mensalide );
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                }
        );

    }

}
