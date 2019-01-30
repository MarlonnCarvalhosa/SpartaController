package com.marlonn.spartacontroller.spartacontroller.views;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
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

        builder.setView(view)
                .setTitle("Adicione um dia de exercicio")
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Adicionar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        return builder.create();
    }

    private void campoIds(View view) {

        nomeAluno = view.findViewById(R.id.edit_nome_aluno);

    }

}