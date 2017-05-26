package com.nnnkp.transac;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/**
 * Created by npraj1 on 5/26/2017.
 */

public class TransactionsDataAdapter extends RecyclerView.Adapter<TransactionsDataAdapter.ViewHolder> {

    private List<Client> clientList;
    private List<Transaction> transactionList;

    public TransactionsDataAdapter(List<Client> clientList, List<Transaction> transactionList) {
        this.clientList = clientList;
        this.transactionList = transactionList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.transactions_summary, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TransactionsDataAdapter.ViewHolder holder, int i) {
        holder.tvClientName.setText(clientList.get(i).getClientName());
        holder.tvNetTransactionType.setText(clientList.get(i).getNetType());
        holder.tvNetTransactionAmount.setText(clientList.get(i).getNetAmount());

        holder.tvPrevTransaction1.setText(transactionList.get(i).getAmount());
        holder.tvPrevTransaction2.setText(transactionList.get(i).getAmount());
        holder.tvPrevTransaction3.setText(transactionList.get(i).getAmount());
        holder.tvPrevTransaction4.setText(transactionList.get(i).getAmount());
        holder.tvPrevTransaction5.setText(transactionList.get(i).getAmount());
    }

    @Override
    public int getItemCount() {
        return clientList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvClientName;
        private TextView tvNetTransactionType;
        private TextView tvNetTransactionAmount;

        private TextView tvPrevTransaction1;
        private TextView tvPrevTransaction2;
        private TextView tvPrevTransaction3;
        private TextView tvPrevTransaction4;
        private TextView tvPrevTransaction5;


        private Button btnTransactionHistory;
        private Button btnAddTransaction;


        public ViewHolder(View itemView) {

            super(itemView);
            tvClientName = (TextView) itemView.findViewById(R.id.tv_client_name);
            tvNetTransactionType = (TextView) itemView.findViewById(R.id.tv_net_transaction_type);
            tvNetTransactionAmount = (TextView) itemView.findViewById(R.id.tv_net_transaction_amount);

            tvPrevTransaction1 = (TextView) itemView.findViewById(R.id.tv_prev_transaction_1);
            tvPrevTransaction2 = (TextView) itemView.findViewById(R.id.tv_prev_transaction_2);
            tvPrevTransaction3 = (TextView) itemView.findViewById(R.id.tv_prev_transaction_3);
            tvPrevTransaction4 = (TextView) itemView.findViewById(R.id.tv_prev_transaction_4);
            tvPrevTransaction5 = (TextView) itemView.findViewById(R.id.tv_prev_transaction_5);

            btnTransactionHistory = (Button) itemView.findViewById(R.id.btn_transaction_history);
            btnAddTransaction = (Button) itemView.findViewById(R.id.btn_add_transaction);


        }
    }
}
