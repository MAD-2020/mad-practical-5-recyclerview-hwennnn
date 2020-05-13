package sg.edu.np.mad.mad_recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemHolder> {

    final static String TAG = "ItemAdapter";
    public Item.ItemList _itemList;
    Context c;
    private OnItemClickListener mListener;
    static View view;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.mListener = listener;
    }

    public ItemAdapter(Context c, Item.ItemList itemList) {
        this.c = c;
        this._itemList = itemList;
    }

    public static class ItemHolder extends RecyclerView.ViewHolder {

        public TextView title;
        public CheckBox item_checkbox;

        public ItemHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);

            this.title = itemView.findViewById(R.id.to_do_title);
            this.item_checkbox = itemView.findViewById(R.id.checkBox);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener!=null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public ItemAdapter.ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.to_do_row,null);

        return new ItemHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        final Item item = _itemList.getItemAt(position);
        holder.title.setText(item.getTitle().trim());
        holder.item_checkbox.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (item.checked){
                    item.setUnchecked();
                }else{
                    item.setChecked();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return _itemList.size();
    }
}
