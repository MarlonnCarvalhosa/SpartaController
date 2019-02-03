package com.marlonn.spartacontroller.spartacontroller.DAO;

import android.app.ProgressDialog;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.marlonn.spartacontroller.spartacontroller.model.Alunos;
import com.marlonn.spartacontroller.spartacontroller.model.Mensalidade;
import com.marlonn.spartacontroller.spartacontroller.utils.ConfiguraçõesFirebase;
import com.marlonn.spartacontroller.spartacontroller.utils.ConstantsUtils;

public class DataBaseDAO {

    private boolean sucesso = true;
    private int cont = 0;


    public void saveAluno(FragmentActivity loginActivity, Alunos alunos) {
        alunos.setId(ConfiguraçõesFirebase.getFirebase().push().getKey());
        Log.v("teste save", alunos.getId());
        DatabaseReference reference = ConfiguraçõesFirebase.getFirebase();
        reference.child(ConstantsUtils.BANCO_ALUNOS).child(String.valueOf(alunos.getId())).setValue(alunos);
    }

    public void updateSimpleInfoUser(FragmentActivity activity, Mensalidade mensalidade) {
        DatabaseReference reference = ConfiguraçõesFirebase.getFirebase();
        reference.child(ConstantsUtils.BANCO_MENSALIDADE).child(String.valueOf(mensalidade.getId())).setValue(mensalidade);
    }

    public void removeSimpleInfoUser(FragmentActivity activity, Alunos alunos) {
        DatabaseReference reference = ConfiguraçõesFirebase.getFirebase();
        reference.child(ConstantsUtils.BANCO_ALUNOS).removeValue();

    }

    public static Query getQuerryUsuario(String uId) {
        return FirebaseDatabase.getInstance().getReference(ConstantsUtils.BANCO_ALUNOS).child(uId);
    }

    public void saveMensalidade(FragmentActivity loginActivity, Mensalidade mensalidade) {
        mensalidade.setId(ConfiguraçõesFirebase.getFirebase().getKey());
        DatabaseReference reference = ConfiguraçõesFirebase.getFirebase();
        reference.child(ConstantsUtils.BANCO_MENSALIDADE).child(String.valueOf(mensalidade.getId())).setValue(mensalidade);
    }

}