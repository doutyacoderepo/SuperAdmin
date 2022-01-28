package com.doutya.superadmin.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.doutya.superadmin.Models.SearchUser;
import com.doutya.superadmin.R;
import com.doutya.superadmin.SearchUserActivity;

import java.util.List;


public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    Context context;
    List<SearchUser> userList;

    public SearchAdapter(Context context, List<SearchUser> userList) {
        this.context = context;
        this.userList = userList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.search_item_design, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.SetData(userList.get(position).getContact_Number());
        holder.itemView.setOnClickListener(View ->
        {
            String token = userList.get(position).getFcmToken();
            String num = userList.get(position).getContact_Number();
            ((SearchUserActivity)context).SetSelectedItem(num,token);
        });

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public void filterList(List<SearchUser> filteredList) {
        userList = filteredList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView Number;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            Number = itemView.findViewById(R.id.SearchNumberTvR);


        }


        public void SetData(String number) {
            Number.setText(number);


        }
    }
}
