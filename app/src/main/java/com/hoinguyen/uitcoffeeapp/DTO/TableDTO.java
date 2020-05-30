package com.hoinguyen.uitcoffeeapp.DTO;

public class TableDTO {
    private String table_name;
    private int table_id, store_id, table_status;

    public String getTable_name() {
        return table_name;
    }

    public void setTable_name(String table_name) {
        this.table_name = table_name;
    }

    public int getTable_id() {
        return table_id;
    }

    public void setTable_id(int table_id) {
        this.table_id = table_id;
    }

    public int getStore_id() {
        return store_id;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
    }

    public int getTable_status() {
        return table_status;
    }

    public void setTable_status(int table_status) {
        this.table_status = table_status;
    }
}
