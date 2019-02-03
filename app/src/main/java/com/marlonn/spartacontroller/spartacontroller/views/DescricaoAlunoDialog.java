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

import com.marlonn.spartacontroller.spartacontroller.DAO.DataBaseDAO;
import com.marlonn.spartacontroller.spartacontroller.R;
import com.marlonn.spartacontroller.spartacontroller.model.Alunos;
import com.marlonn.spartacontroller.spartacontroller.model.Mensalidade;

public class DescricaoAlunoDialog extends AppCompatDialogFragment {

    private Spinner mensalidadeTotal;
    private Mensalidade mensalidade = new Mensalidade();
    private Alunos alunos = new Alunos();
    private String valorTotal;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.descricao_dialog_layout, null);

        campoIds(view);

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

        return builder.create();
    }

    private void campoIds(View view) {

        mensalidadeTotal = view.findViewById(R.id.spinnerMensalidade);


    }

    private void uploadMensalidade(View view) {

        mensalidade.setValor(Integer.parseInt(mensalidadeTotal.getSelectedItem().toString().replace("R$:", "").replace(" ", "").replace(",", "")));

        new DataBaseDAO().saveMensalidade(getActivity(), mensalidade);

    }



}
