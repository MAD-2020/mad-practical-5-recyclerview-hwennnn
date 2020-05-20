package sg.edu.np.mad.mad_recyclerview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import static java.lang.String.format;

public class ItemAdapter extends RecyclerView.Adapter<ItemHolder> {

    final static String TAG = "ItemAdapter";
    public Item.ItemList _itemList;
    Context c;
    private OnItemClickListener mListener;
    static View view;

    public interface OnItemClickListener{
        //implement an interface then i can retrieve the position from the parameter
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.mListener = listener;
    }

    public ItemAdapter(Context c, Item.ItemList itemList) {
        this.c = c;
        this._itemList = itemList;
    }


    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
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
                    Log.v(TAG, format("%s is unchecked",item.getTitle()));
                    item.setUnchecked();
                }else{
                    Log.v(TAG, format("%s is checked",item.getTitle()));
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
