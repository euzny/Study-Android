package com.example.last_project.employee;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.last_project.R;

import java.util.ArrayList;

public class EmpAdapter extends RecyclerView.Adapter<EmpAdapter.ViewHolder>{

    LayoutInflater inflater;
    ArrayList<EmployeeVO> list;

    public EmpAdapter(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    public ArrayList<EmployeeVO> getList() {
        return list;
    }

    public void setList(ArrayList<EmployeeVO> list) {
        this.list = list;
    }

    public EmpAdapter(LayoutInflater inflater, ArrayList<EmployeeVO> list) {
        this.inflater = inflater;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_rev_emp,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        holder.bind(holder,i);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_eid, tv_ename, tv_dname;
        CardView card_emp;
       public ViewHolder(@NonNull View itemView) {
            super(itemView);
           tv_eid = itemView.findViewById(R.id.tv_eid);
           tv_dname = itemView.findViewById(R.id.tv_dname);
            tv_ename =itemView.findViewById(R.id.tv_ename);
            card_emp = itemView.findViewById(R.id.card_emp);
        }

        //googld doc <= 권장하는 방법.
        public void bind(@NonNull ViewHolder holder, int i){
            holder.tv_ename.setText(list.get(i).getName());
            holder.tv_eid.setText(list.get(i).getEmployee_id()+"");
            holder.tv_dname.setText(list.get(i).getDepartment_name());
        }
    }
}
