package io.travis.golfscorecard

import android.content.Context
import io.travis.golfscorecard.Hole.getLabel
import io.travis.golfscorecard.Hole.getStrokeCount
import io.travis.golfscorecard.Hole.setStrokeCount
import io.travis.golfscorecard.Hole
import android.widget.BaseAdapter
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import io.travis.golfscorecard.R
import android.widget.TextView

class ListAdapter(private val mContext: Context, private val mHoles: Array<Hole>) : BaseAdapter() {
    override fun getCount(): Int {
        return mHoles.size
    }

    override fun getItem(position: Int): Any {
        return mHoles[position]
    }

    override fun getItemId(position: Int): Long {
        return 0 // Not implemented yet
    }

    override fun getView(position: Int, convertView: View, viewGroup: ViewGroup): View {
        var convertView = convertView
        val holder: ViewHolder
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item, null)
            holder = ViewHolder()
            holder.textView_Hole_Label =
                convertView.findViewById<View>(R.id.textView_Hole_Label) as TextView
            holder.textView_Stroke_Count =
                convertView.findViewById<View>(R.id.textView_Stroke_Count) as TextView
            holder.button_Remove_Stroke =
                convertView.findViewById<View>(R.id.button_Remove_Stroke) as Button
            holder.button_Add_Stroke =
                convertView.findViewById<View>(R.id.button_Add_Stroke) as Button
            convertView.tag = holder
        } else {
            holder = convertView.tag as ViewHolder
        }
        holder.textView_Hole_Label!!.text = mHoles[position].getLabel()
        holder.textView_Stroke_Count!!.text = mHoles[position].getStrokeCount().toString() + ""
        holder.button_Remove_Stroke!!.setOnClickListener {
            var updateStrokeCount = mHoles[position].getStrokeCount() - 1
            if (updateStrokeCount < 0) updateStrokeCount = 0
            mHoles[position].setStrokeCount(updateStrokeCount)
            holder.textView_Stroke_Count!!.text = updateStrokeCount.toString() + ""
        }
        holder.button_Add_Stroke!!.setOnClickListener {
            val updateStrokeCount = mHoles[position].getStrokeCount() + 1
            mHoles[position].setStrokeCount(updateStrokeCount)
            holder.textView_Stroke_Count!!.text = updateStrokeCount.toString() + ""
        }
        return convertView
    }

    private class ViewHolder {
        var textView_Hole_Label: TextView? = null
        var textView_Stroke_Count: TextView? = null
        var button_Remove_Stroke: Button? = null
        var button_Add_Stroke: Button? = null
    }
}