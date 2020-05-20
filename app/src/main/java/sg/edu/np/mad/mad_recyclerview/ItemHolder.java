package sg.edu.np.mad.mad_recyclerview;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ItemHolder extends RecyclerView.ViewHolder {

    public TextView title;
    public CheckBox item_checkbox;

    public ItemHolder(@NonNull View itemView, final ItemAdapter.OnItemClickListener listener) {
        super(itemView);

        this.title = itemView.findViewById(R.id.to_do_title);
        this.item_checkbox = itemView.findViewById(R.id.checkBox);
        //set an onclick listener when the holder is clicked
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null){
                    int position = getAdapterPosition(); //this is to get the position of the holder
                    if (position != RecyclerView.NO_POSITION){
                        listener.onItemClick(position);
                        //this is to parse the position into the parameter so that we can utilise the position in other activity further on
                    }
                }
            }
        });
    }
}
