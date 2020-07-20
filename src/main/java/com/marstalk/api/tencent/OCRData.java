package com.marstalk.api.tencent;

import java.util.List;

public class OCRData {
    private List<Item> item_list;

    public List<Item> getItem_list() {
        return item_list;
    }

    public void setItem_list(List<Item> item_list) {
        this.item_list = item_list;
    }
}

class Item{
    private String item;
    private String itemstring;

    public Item() {
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getItemstring() {
        return itemstring;
    }

    public void setItemstring(String itemstring) {
        this.itemstring = itemstring;
    }
}