package com.marlonn.spartacontroller.spartacontroller.utils;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class ConfiguraçõesFirebase {
    private static DatabaseReference referenceFirebase;

    public static DatabaseReference getFirebase(){
        if(referenceFirebase == null){
            referenceFirebase= FirebaseDatabase.getInstance().getReference();

        }
        return referenceFirebase;
    }

    public static Query getAlunos() {
        return FirebaseDatabase.getInstance().getReference(ConstantsUtils.BANCO_ALUNOS);
    }

    public static Query getMensalidade() {
        return FirebaseDatabase.getInstance().getReference(ConstantsUtils.BANCO_MENSALIDADE);
    }

}
