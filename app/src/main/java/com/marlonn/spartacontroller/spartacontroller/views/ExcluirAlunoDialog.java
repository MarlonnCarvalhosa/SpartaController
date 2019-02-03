package com.marlonn.spartacontroller.spartacontroller.views;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;

import com.marlonn.spartacontroller.spartacontroller.DAO.DataBaseDAO;
import com.marlonn.spartacontroller.spartacontroller.R;
import com.marlonn.spartacontroller.spartacontroller.model.Alunos;

import java.util.List;

public class ExcluirAlunoDialog extends AppCompatDialogFragment {

    private FragmentActivity activity;
    private List<Alunos> alunos;
    private Alunos aluno = new Alunos();


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.excluir_aluno_dialog_layout, null);

        campoIds(view);

        builder.setView(view);
        builder.setTitle("Realmente deseja excluir?");
        builder.setNegativeButton("Nao", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                final ProgressDialog progressDialog = new ProgressDialog(getContext());
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.setMessage(ExcluirAlunoDialog.this.getString(R.string.excluindo));
                progressDialog.show();

                Runnable progressRunnable = new Runnable() {
                    @Override
                    public void run() {
                        progressDialog.cancel();
                    }
                };

                Handler pdCanceller = new Handler();
                pdCanceller.postDelayed(progressRunnable, 2500);

                new DataBaseDAO().removeSimpleInfoUser(getActivity(), aluno);


            }
        });

        return builder.create();
    }

    private void campoIds(View view) {

    }

}
