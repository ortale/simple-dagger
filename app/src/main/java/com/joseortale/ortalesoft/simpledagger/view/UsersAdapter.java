package com.joseortale.ortalesoft.simpledagger.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.joseortale.ortalesoft.simpledagger.R;
import com.joseortale.ortalesoft.simpledagger.model.User;

import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder> {
    private List<User> data;
    private LayoutInflater inflater;
    private Context context;

    public UsersAdapter(Context context, List<User> data) {
        this.inflater = LayoutInflater.from(context);
        this.data = data;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.user_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        User user = data.get(position);
        holder.tvTitle.setText(user.getName());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvTitle;

        ViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvName);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int selectedPosition = getLayoutPosition();
            notifyItemChanged(selectedPosition);
            User user = data.get(selectedPosition);
            ((MainActivity) context).setFragment(UserDetailFragment.newInstance(user.getId()));
        }
    }
}
