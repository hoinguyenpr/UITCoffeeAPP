package com.hoinguyen.uitcoffeeapp.CustomAdapter;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.hoinguyen.uitcoffeeapp.DTO.CategoryDTO;
import com.hoinguyen.uitcoffeeapp.R;

import java.util.List;

public class ShowCategoryAdapter extends BaseAdapter {
    Context context;
    int layout;
    List<CategoryDTO> categoryDTOList;
    ViewHolderCategory viewHolderCategory;

    public ShowCategoryAdapter(Context context, int layout, List<CategoryDTO> categoryDTOList){
        this.context = context;
        this.layout = layout;
        this.categoryDTOList = categoryDTOList;
    }
    @Override
    public int getCount() {
        return categoryDTOList.size();
    }

    @Override
    public Object getItem(int i) {
        return categoryDTOList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return categoryDTOList.get(i).getCategory_id();
    }

    public class ViewHolderCategory{
        TextView txtNameOfCategory;
    }


    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null){
            viewHolderCategory = new ViewHolderCategory();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.custom_layout_spin_category, parent, false);

            viewHolderCategory.txtNameOfCategory = view.findViewById(R.id.txtNameOfCategory);

            view.setTag(viewHolderCategory);
        }else{
            viewHolderCategory = (ViewHolderCategory) view.getTag();
        }

        CategoryDTO categoryDTO = categoryDTOList.get(position);
        viewHolderCategory.txtNameOfCategory.setText(categoryDTO.getCategory_name());
        viewHolderCategory.txtNameOfCategory.setTag(categoryDTO.getCategory_id());

        return view;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null){
            viewHolderCategory = new ViewHolderCategory();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.custom_layout_spin_category, parent, false);

           viewHolderCategory.txtNameOfCategory = view.findViewById(R.id.txtNameOfCategory);

           view.setTag(viewHolderCategory);
        }else{
            viewHolderCategory = (ViewHolderCategory) view.getTag();
        }

        CategoryDTO categoryDTO = categoryDTOList.get(position);
        viewHolderCategory.txtNameOfCategory.setText(categoryDTO.getCategory_name());
        viewHolderCategory.txtNameOfCategory.setTag(categoryDTO.getCategory_id());

        return view;
    }
}
