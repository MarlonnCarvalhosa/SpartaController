package com.marlonn.spartacontroller.spartacontroller.adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.marlonn.spartacontroller.spartacontroller.R;
import com.marlonn.spartacontroller.spartacontroller.model.Alunos;
import com.marlonn.spartacontroller.spartacontroller.views.DescricaoAlunoDialog;
import com.marlonn.spartacontroller.spartacontroller.views.ExcluirAlunoDialog;

import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class AdapterAlunos extends RecyclerView.Adapter<AdapterAlunos.ViewHolder> {
    private FragmentActivity activity;
    private List<Alunos> alunos;
    private Alunos aluno = new Alunos();

    public AdapterAlunos(FragmentActivity activity, List<Alunos> alunos){
        this.activity = activity;
        this.alunos = alunos;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtNome;
        private ImageView clickExcluir;
        private LinearLayout corPago;
        private String dia;

        public ViewHolder(View itemView) {
            super(itemView);

            final Calendar c = Calendar.getInstance();

            dia = String.valueOf(c.get(Calendar.DAY_OF_MONTH));
            corPago = itemView.findViewById(R.id.linearAdapter);
            clickExcluir = itemView.findViewById(R.id.clickExcluir);
            txtNome = itemView.findViewById(R.id.nomeDoAluno);

        }

    }

    public void atualiza(List<Alunos> alunos){
        Collections.reverse(alunos);
        this.alunos = alunos;
        this.notifyDataSetChanged();

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view ;
        LayoutInflater mInflater = LayoutInflater.from(activity);
        view = mInflater.inflate(R.layout.item_alunos,parent,false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final Alunos aluno = alunos.get(position);

        holder.txtNome.setText(aluno.getNomeAluno());
        holder.txtNome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                abrirDialogo();

            }
        });

        holder.clickExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               abrirDialogo2();

            }
        });

        if (aluno.isPago()) {

            holder.corPago.setBackgroundColor(ContextCompat.getColor(activity, R.color.green));

        }

        //Comparar se for maior que a data de vencimento
        if (holder.dia.equals(aluno.getDataVencimento())) {

            holder.corPago.setBackgroundColor(ContextCompat.getColor(activity, R.color.red));

        }

    }

    @Override
    public int getItemCount() {

        return alunos.size();

    }

    private void abrirDialogo() {

        DescricaoAlunoDialog descricaoAluno = new DescricaoAlunoDialog();
        descricaoAluno.show(activity.getSupportFragmentManager(), " Descricao");

    }

    private void abrirDialogo2() {

        ExcluirAlunoDialog excluirAlunoDialog = new ExcluirAlunoDialog();
        excluirAlunoDialog.show(activity.getSupportFragmentManager(), " Excluir");

    }
}
