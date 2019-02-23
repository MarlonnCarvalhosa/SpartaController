package com.marlonn.spartacontroller.spartacontroller.DAO;

import android.app.ProgressDialog;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.marlonn.spartacontroller.spartacontroller.model.Alunos;
import com.marlonn.spartacontroller.spartacontroller.model.Mensalidade;
import com.marlonn.spartacontroller.spartacontroller.utils.ConfiguraçõesFirebase;
import com.marlonn.spartacontroller.spartacontroller.utils.ConstantsUtils;

public class DataBaseDAO {

    private boolean sucesso = true;
    private int cont = 0;
    private DatabaseReference reference;
    private ValueEventListener ValueEventListener;
    private Alunos alunos = new Alunos();
    private String idAluno;


    public void saveAluno(FragmentActivity loginActivity, Alunos alunos) {
        alunos.setId(ConfiguraçõesFirebase.getFirebase().push().getKey());
        Log.v("teste save", alunos.getId());
        DatabaseReference reference = ConfiguraçõesFirebase.getFirebase();
        reference.child(ConstantsUtils.BANCO_ALUNOS).child(String.valueOf(alunos.getId())).setValue(alunos);
    }

    public void updateSimpleInfoUser(FragmentActivity activity, Alunos alunos) {
        recuperarValor();
        DatabaseReference reference = ConfiguraçõesFirebase.getFirebase();
        reference.child(ConstantsUtils.BANCO_ALUNOS).child(String.valueOf(alunos.getId())).setValue(alunos.isPago());
    }

    public void removeSimpleInfoUser(FragmentActivity activity, Alunos alunos) {
        DatabaseReference reference = ConfiguraçõesFirebase.getFirebase();
        reference.child(ConstantsUtils.BANCO_ALUNOS).child(String.valueOf(alunos.getId())).removeValue();

    }

    public static Query getQuerryUsuario(String uId) {
        return FirebaseDatabase.getInstance().getReference(ConstantsUtils.BANCO_ALUNOS).child(uId);
    }

    public void saveMensalidade(FragmentActivity activity, Mensalidade mensalidade) {
        mensalidade.setId(ConfiguraçõesFirebase.getFirebase().getKey());
        DatabaseReference reference = ConfiguraçõesFirebase.getFirebase();
        reference.child(ConstantsUtils.BANCO_MENSALIDADE).child(String.valueOf("mensalidadeTotal")).setValue(mensalidade);
        //recuperarValor();
    }

    public void atualiza(Alunos alunos) {
        recuperarValor();
        DatabaseReference reference = ConfiguraçõesFirebase.getFirebase();
        reference.child(ConstantsUtils.BANCO_ALUNOS).child(idAluno).setValue(alunos);
    }

    private void recuperarValor (){

        reference = FirebaseDatabase.getInstance().getReference();
        reference.child("alunos").getDatabase();

        reference = reference.child("alunos").child(alunos.getId());
        ValueEventListener = reference.addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        Alunos alunos1 = dataSnapshot.getValue(Alunos.class);

                        idAluno = alunos1.getId();

                        DatabaseReference reference = ConfiguraçõesFirebase.getFirebase();
                        reference.child(ConstantsUtils.BANCO_ALUNOS).child(String.valueOf(idAluno)).setValue(alunos1);

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                }
        );

    }

}