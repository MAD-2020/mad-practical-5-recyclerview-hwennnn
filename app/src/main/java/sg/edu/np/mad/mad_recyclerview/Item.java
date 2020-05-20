package sg.edu.np.mad.mad_recyclerview;

import java.util.ArrayList;

public class Item {
    public String title;
    public boolean checked;

    public void setUnchecked(){ this.checked = false; }

    public void setChecked() {
        this.checked = true;
    }

    public String getTitle() {
        return title;
    }

    public Item(String title) {
        this.title = title;
        this.checked = false;
    }

    public static class ItemList {
        // so that i can customise the itemList function and also for future considerations

        private ArrayList<Item> itemList;

        public ItemList() { this.itemList = new ArrayList<>(); }

        public Item getItemAt(Integer index) { return this.itemList.get(index); }

        public void addItem(String title) { this.itemList.add(new Item(title)); }

        public void removeItemAt(int position) { this.itemList.remove(position); }

        public int size() { return this.itemList.size(); }
    }
}
