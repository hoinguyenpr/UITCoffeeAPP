package com.hoinguyen.uitcoffeeapp.CustomAdapter;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hoinguyen.uitcoffeeapp.DAO.CategoryDAO;
import com.hoinguyen.uitcoffeeapp.DTO.CategoryDTO;
import com.hoinguyen.uitcoffeeapp.R;

import java.io.File;
import java.util.List;

public class ShowCategoryFoodAdapter extends BaseAdapter {
    Context context;
    int layout;
    List<CategoryDTO> categoryDTOList;
    ViewHolderShowCategory viewHolderShowCategory;

    //18-6
    CategoryDAO categoryDAO;



    public ShowCategoryFoodAdapter(Context context, int layout, List<CategoryDTO>categoryDTOList){
        this.context = context;
        this.layout = layout;
        this.categoryDTOList = categoryDTOList;

        //18-6
        categoryDAO = new CategoryDAO(context);
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

    public class ViewHolderShowCategory{
        ImageView imgCategory;
        TextView txtNameOfCategoryInList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null){
            viewHolderShowCategory = new ViewHolderShowCategory();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, parent, false);

            viewHolderShowCategory.imgCategory = view.findViewById(R.id.imgCategory);
            viewHolderShowCategory.txtNameOfCategoryInList = view.findViewById(R.id.txtNameOfCategoryInList);

            view.setTag(viewHolderShowCategory);

        }else{
            viewHolderShowCategory = (ViewHolderShowCategory) view.getTag();
        }

        CategoryDTO categoryDTO =  categoryDTOList.get(position);
        viewHolderShowCategory.txtNameOfCategoryInList.setText(categoryDTO.getCategory_name());

        //18-6
//        int categoryID = categoryDTO.getCategory_id();
//        Log.d("categoryid", categoryID + "");
//        String imgCategory = categoryDAO.getImageCategory(categoryID);
//        Log.d("Hinh cua " + categoryID+ " la: ", imgCategory);
////        Uri uri = Uri.parse(imgCategory);
////        viewHolderShowCategory.imgCategory.setImageURI(uri);
//        //key : uri image request permission android

        return view;
    }

}
