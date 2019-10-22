package com.example.mydatabaseroom.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mydatabaseroom.R;
import com.example.mydatabaseroom.UpdateTaskActivity;
import com.example.mydatabaseroom.entity.Task;

import org.w3c.dom.Text;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    private Context context;
    private List<Task> taskList;

    public TaskAdapter(Context context, List<Task> mList){
        this.context = context;
        this.taskList = mList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_task, parent, false);
        return new ViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Task t = taskList.get(position);

        holder.textViewTask.setText(t.getTask());
        holder.textViewDesc.setText(t.getDesk());
        holder.textViewFinishBy.setText(t.getFinishBy());

        if (t.isFinished()) {
            holder.textViewStatus.setText("Completed");
        }else {
            holder.textViewStatus.setText("Not CompleteD");
        }
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView textViewStatus, textViewTask, textViewDesc, textViewFinishBy;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewDesc = itemView.findViewById(R.id.textViewDesc);
            textViewFinishBy = itemView.findViewById(R.id.textViewFinishBy);
            textViewTask = itemView.findViewById(R.id.textViewTask);
            textViewStatus = itemView.findViewById(R.id.textViewStatus);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Task task = taskList.get(getAdapterPosition());

            Intent mIntent = new Intent(context, UpdateTaskActivity.class);
            mIntent.putExtra("task", task);

            context.startActivity(mIntent);
        }
    }
}
