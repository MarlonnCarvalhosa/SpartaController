package com.marlonn.spartacontroller.spartacontroller.views;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.marlonn.spartacontroller.spartacontroller.DAO.DataBaseDAO;
import com.marlonn.spartacontroller.spartacontroller.R;
import com.marlonn.spartacontroller.spartacontroller.model.Alunos;
import com.marlonn.spartacontroller.spartacontroller.model.Mensalidade;
import com.marlonn.spartacontroller.spartacontroller.utils.ConfiguraçõesFirebase;
import com.marlonn.spartacontroller.spartacontroller.utils.MoneyTextWatcher;

import java.util.Locale;

public class DescricaoAlunoDialog extends AppCompatDialogFragment {

    private EditText mensalidadeTotal;
    private Mensalidade mensalidade = new Mensalidade();
    private Alunos alunos = new Alunos();
    private float valorTotal;
    private String valor;
    private boolean mensalidadePaga;
    private DatabaseReference reference;
    private ValueEventListener ValueEventListener;
    private FirebaseDatabase firebaseDatabase;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.descricao_dialog_layout, null);

        reference = FirebaseDatabase.getInstance().getReference();
        reference.child("mensalidade").child("mensalidadeTotal").getDatabase();

        campoIds(view);

        recuperarValor();

        builder.setView(view);
        builder.setTitle("Pagamento");
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setPositiveButton("Pagar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                final ProgressDialog progressDialog = new ProgressDialog(getContext());
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.setMessage(DescricaoAlunoDialog.this.getString(R.string.finalizando));
                progressDialog.show();

                Runnable progressRunnable = new Runnable() {
                    @Override
                    public void run() {
                        progressDialog.cancel();
                    }
                };

                Handler pdCanceller = new Handler();
                pdCanceller.postDelayed(progressRunnable, 2500);

                uploadMensalidade(view);

            }
        });

        Locale mLocale = new Locale("pt", "BR");
        mensalidadeTotal.addTextChangedListener(new MoneyTextWatcher(mensalidadeTotal, mLocale));

        return builder.create();
    }

    private void recuperarValor (){

        reference = reference.child( "mensalidade" ).child("mensalidadeTotal");
        ValueEventListener = reference.addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        Mensalidade mensalidadee = dataSnapshot.getValue( Mensalidade.class );

                            String rendaTotal = mensalidadee.getValor();


                            //Configura valores recuperados




                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                }
        );

    }

    private void campoIds(View view) {

        mensalidadeTotal = view.findViewById(R.id.edit_pagar);

    }

    private void uploadMensalidade(View view) {

        mensalidade.setValor(mensalidadeTotal.getText().toString().replace("R$", ""));
        alunos.setPago(true);

        new DataBaseDAO().saveMensalidade(getActivity(), mensalidade);

    }

}
