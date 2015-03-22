package com.androidy.conect;

/**
 * Created by christinajackey on 3/20/15.
 */
import android.content.Context;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.parse.ParseObject;

import java.util.List;

/**
 * Created by christinajackey on 3/19/15.
 */
public class StatusAdapter extends ArrayAdapter<ParseObject> {

    private Context mContext;
    private List<ParseObject> mStatus;


    public StatusAdapter(Context context , List<ParseObject> status) {
        super(context , R.layout.home_page_list_item , status);
        mStatus = status;
        mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {

            convertView = LayoutInflater.from(mContext).inflate(R.layout.home_page_list_item , null);
            holder = new ViewHolder();

            holder.usernameHomePage = (TextView) convertView.findViewById(R.id.usernameHP);
            holder.commentHomePage = (TextView) convertView.findViewById(R.id.passwordRegisterEditText);
            holder.commentHomePage.setMovementMethod(new ScrollingMovementMethod());

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ParseObject statusObject = mStatus.get(position);

        String username = statusObject.getString("user");
        holder.usernameHomePage.setText(username);
        String status = statusObject.getString("newStatus");
        holder.commentHomePage.setText(status);

        return convertView;
    }

    public static class ViewHolder {
        TextView usernameHomePage;
        TextView commentHomePage;

    }
}