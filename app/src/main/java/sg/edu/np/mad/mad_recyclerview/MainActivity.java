package sg.edu.np.mad.mad_recyclerview;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static java.lang.String.format;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "To-do-List";
    Item.ItemList itemList;
    RecyclerView mRecyclerView;
    ItemAdapter myAdapter;
    Button add_btn;
    EditText add_title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.my_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        add_btn = findViewById(R.id.buttonAdd);
        add_title = findViewById(R.id.add_task_name);

        myAdapter = new ItemAdapter(this, getItemList());
        mRecyclerView.setAdapter(myAdapter);

        myAdapter.setOnItemClickListener(new ItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(final int position) {
                Log.v(TAG,"Delete alert dialog popup!");
                Item item = myAdapter._itemList.getItemAt(position);
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                //Inflate the custom layout
                LayoutInflater inflater = getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.custom_dialog, null);

                TextView txt = dialogView.findViewById(R.id.message);
                //set the text by using html
                txt.setText(Html.fromHtml("Are you sure you want to delete<br/>"+ "<b>" + item.getTitle() + "?</b><br>"));

                ImageView img = dialogView.findViewById(R.id.image);
                //set the rubbish bin icon
                img.setImageResource(android.R.drawable.ic_menu_delete);

                builder.setTitle("Delete");
                builder.setView(dialogView);
                builder.setCancelable(false);

                //set the positive button which will delete the item
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.v(TAG, format("%s deleted!",itemList.getItemAt(position).getTitle()));
                        myAdapter._itemList.removeItemAt(position);
                        myAdapter.notifyItemRemoved(position);
                        myAdapter.notifyItemRangeChanged(position, itemList.size());
                        myAdapter.notifyDataSetChanged();
                    }
                });

                //set the negative button which will not delete the item
                builder.setNegativeButton("No", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id){
                        Log.v(TAG,format("User refuses to delete %s!",itemList.getItemAt(position).getTitle()));
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();

            }
        });

        //this is to add the task
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = add_title.getText().toString();
                Log.v(TAG,format("%s is added",title));
                add_title.setText("");
                myAdapter._itemList.addItem(title);
                myAdapter.notifyDataSetChanged();
                showNewEntry(mRecyclerView, myAdapter._itemList);
            }
        });

    }

    /**
     * Upon calling this method, the keyboard will retract
     * and the recyclerview will scroll to the last item
     *
     * @param rv RecyclerView for scrolling to
     * @param data ArrayList that was passed into RecyclerView
     */
    private void showNewEntry(RecyclerView rv, Item.ItemList itemList){
        //scroll to the last item of the recyclerview
        rv.scrollToPosition(itemList.size() - 1);

        //auto hide keyboard after entry
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        assert imm != null;
        imm.hideSoftInputFromWindow(rv.getWindowToken(), 0);
    }

    private Item.ItemList getItemList(){
        //initialise the fake data
        itemList = new Item.ItemList();
        itemList.addItem("Drink water");
        itemList.addItem("Workout");
        itemList.addItem("Leetcoding");
        itemList.addItem("Swimming");
        return itemList;
    }

}
