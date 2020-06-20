package com.hoinguyen.uitcoffeeapp.CustomAdapter;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hoinguyen.uitcoffeeapp.DTO.FoodDTO;
import com.hoinguyen.uitcoffeeapp.R;

import java.util.List;

public class ShowFoodListAdapter extends BaseAdapter {

    Context context;
    int layout;
    List<FoodDTO> foodDTOList;
    ViewHolderShowFoodList viewHolderShowFoodList;

    public ShowFoodListAdapter(Context context, int layout, List<FoodDTO>foodDTOList){
        this.context = context;
        this.layout = layout;
        this.foodDTOList = foodDTOList;
    }

    @Override
    public int getCount() {
        return foodDTOList.size();
    }

    @Override
    public Object getItem(int position) {
        return foodDTOList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return foodDTOList.get(position).getFood_id();
    }


    public class ViewHolderShowFoodList{
        ImageView imFoodImage;
        TextView txtNameOfFood,txtFoodPrice;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            viewHolderShowFoodList = new ViewHolderShowFoodList();
            view = inflater.inflate(layout, parent, false);

            viewHolderShowFoodList.imFoodImage = view.findViewById(R.id.imgImageOfFoodList);
            viewHolderShowFoodList.txtFoodPrice = view.findViewById(R.id.txtPriceOfFoodList);
            viewHolderShowFoodList.txtNameOfFood = view.findViewById(R.id.txtNameOfFoodList);

            view.setTag(viewHolderShowFoodList);

        }else{
            viewHolderShowFoodList  = (ViewHolderShowFoodList) view.getTag();
        }
        FoodDTO foodDTO = foodDTOList.get(position);
//        String img = foodDTO.getImage_food().toString();
//        if(img == null || img.equals("")){
//            viewHolderShowFoodList.imFoodImage.setImageResource(R.drawable.menubackground);
//        }else{
//            Uri uri = Uri.parse(img);
//            viewHolderShowFoodList.imFoodImage.setImageURI(uri);
//        }
        //Log.d("dulieu", foodDTO.getName_food() +"");
        viewHolderShowFoodList.imFoodImage.setImageResource(R.drawable.menubackground);
        viewHolderShowFoodList.txtNameOfFood.setText( foodDTO.getName_food());
        viewHolderShowFoodList.txtFoodPrice.setText("Giá: " + foodDTO.getFood_price());

        return view;
    }
}
