/*
 * Copyright (C) 2016 Jared Rummler <jared.rummler@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.jaredrummler.materialspinner;

import android.content.Context;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public abstract class MaterialSpinnerBaseAdapter<T> extends BaseAdapter {

  private Context context;
  private int selectedIndex;

  public MaterialSpinnerBaseAdapter(Context context) {
    this.context = context;
  }

  @Override public View getView(int position, View convertView, ViewGroup parent) {
    TextView textView;

    if (convertView == null) {
      convertView = View.inflate(context, R.layout.spinner_list_item, null);
      textView = (TextView) convertView.findViewById(R.id.tv_tinted_spinner);
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
        textView.setBackground(ContextCompat.getDrawable(context, R.drawable.selector));
      }
      convertView.setTag(new ViewHolder(textView));
    } else {
      textView = ((ViewHolder) convertView.getTag()).textView;
    }
    textView.setText(getItem(position).toString());
    return convertView;
  }

  public int getSelectedIndex() {
    return selectedIndex;
  }

  public void notifyItemSelected(int index) {
    selectedIndex = index;
  }

  @Override public long getItemId(int position) {
    return position;
  }

  @Override public abstract T getItem(int position);

  @Override public abstract int getCount();

  public abstract T getItemInDataset(int position);

  private static class ViewHolder {

    private TextView textView;

    private ViewHolder(TextView textView) {
      this.textView = textView;
    }

  }

}
