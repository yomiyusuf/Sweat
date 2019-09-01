package com.yomi.sweat.ui;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.yomi.sweat.model.Program;
import com.yomi.sweat.ui.views.ProgramCard;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ProgramListAdapter extends RecyclerView.Adapter<ProgramListAdapter.ViewHolder> {
    private Context mContext;
    private ProgramClickListener mListener;
    private List<Program> programs;

    interface ProgramClickListener{
        void onProgramClicked(Program program);
    }

    public ProgramListAdapter(Context context, ProgramClickListener listener){
        mContext = context;
        mListener = listener;
        programs = new ArrayList<>();
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ProgramCard itemView = new ProgramCard(mContext);
        itemView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.bind(programs.get(position), mListener);
    }

    @Override
    public int getItemCount() {
        if (programs == null){
            return 0;
        } else{
            return programs.size();
        }
    }

    public void setData(List<Program> data){
        programs = data;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ProgramCard card;

        private ViewHolder(@NonNull View itemView) {
            super(itemView);
            card = (ProgramCard) itemView;
        }

        private void bind(final Program item, final ProgramClickListener listener){
            card.setViews(item);
            card.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    listener.onProgramClicked(item);
                }
            });
        }
    }
}
