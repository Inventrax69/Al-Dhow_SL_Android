package com.inventrax.falconsl_new.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.inventrax.falconsl_new.R;
import com.inventrax.falconsl_new.pojos.CycleCountDTO;
import com.inventrax.falconsl_new.pojos.OutbountDTO;

import java.util.List;

public class PickListAdapter extends RecyclerView.Adapter {

    private List<OutbountDTO> outbountDTOS;
    private OnItemClickListener listener;
    Context context;

    public PickListAdapter(Context context, List<OutbountDTO> list , OnItemClickListener mlistener) {
        this.context = context;
        this.outbountDTOS = list;
        this.listener=mlistener;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView  txtCCName, txtLocationCode, txtMCode, txtAsgQty, txtPickedQty,txtLineNumber;// init the item view's
        View view;
        CardView cardView;

        public MyViewHolder(View itemView) {

            super(itemView);
            // get the reference of item view's
            txtCCName = (TextView) itemView.findViewById(R.id.txtCCName);
            txtLocationCode = (TextView) itemView.findViewById(R.id.txtLocationCode);
            txtMCode = (TextView) itemView.findViewById(R.id.txtMCode);
            txtAsgQty = (TextView) itemView.findViewById(R.id.txtAsgQty);
            txtPickedQty = (TextView) itemView.findViewById(R.id.txtPickedQty);
            txtLineNumber = (TextView) itemView.findViewById(R.id.txtLineNumber);
            cardView = (CardView) itemView.findViewById(R.id.cardView);
            view= itemView;


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int pos = getAdapterPosition();
                        if (pos != RecyclerView.NO_POSITION) {
                            listener.onItemClick(pos);
                        }
                    }
                }
            });

        }
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.pick_list_view, parent, false);

        // set the view's size, margins, paddings and layout parameters
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        OutbountDTO outbountDTO = (OutbountDTO) outbountDTOS.get(position);

        // set the data in items
        ((MyViewHolder) holder).txtCCName.setText(outbountDTO.getPalletNo());
        ((MyViewHolder) holder).txtLocationCode.setText(outbountDTO.getLocation());
        ((MyViewHolder) holder).txtMCode.setText(outbountDTO.getSKU());
        ((MyViewHolder) holder).txtAsgQty.setText(outbountDTO.getAssignedQuantity());
        ((MyViewHolder) holder).txtPickedQty.setText(outbountDTO.getPickedQty());
        ((MyViewHolder) holder).txtLineNumber.setText(outbountDTO.getLineno());

        if(outbountDTO.getAssignedQuantity().equalsIgnoreCase(outbountDTO.getPickedQty())){
            ((MyViewHolder) holder).cardView.setCardBackgroundColor(Color.parseColor("#1eff00"));
        }else{
            ((MyViewHolder) holder).cardView.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
        }


    }


    @Override
    public int getItemCount() {
        return outbountDTOS.size();
    }

    public interface OnItemClickListener {
        void onItemClick(int pos);
    }


}
