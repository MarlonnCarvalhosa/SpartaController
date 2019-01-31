package com.marlonn.spartacontroller.spartacontroller.views;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import com.marlonn.spartacontroller.spartacontroller.R;

public class AdicionarAlunosDialog extends AppCompatDialogFragment {

    private EditText nomeAluno;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.aluno_dialog_layout, null);

        campoIds(view);

        builder.setView(view);
        builder.setIcon(R.drawable.iconebraco);
        builder.setTitle("Adicionar Aluno");
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setPositiveButton("Adicionar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                final ProgressDialog progressDialog = new ProgressDialog(getContext());
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.setMessage(AdicionarAlunosDialog.this.getString(R.string.aguarde));
                progressDialog.show();

                Runnable progressRunnable = new Runnable() {
                    @Override
                    public void run() {
                        progressDialog.cancel();
                    }
                };

                Handler pdCanceller = new Handler();
                pdCanceller.postDelayed(progressRunnable, 2500);

            }
        });

        return builder.create();
    }

    private void campoIds(View view) {

        nomeAluno = view.findViewById(R.id.edit_nome_aluno);

    }

}