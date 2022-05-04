package io.travis.golfscorecard
import android.content.Context
import android.widget.BaseAdapter
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView

class ListAdapter : BaseAdapter {

    private var mContext: Context
    private var mHoles: Array<Hole>

    constructor(context: Context, holes: Array<Hole>) {
        mContext = context
        mHoles = holes
    }

    override fun getCount(): Int {
        return mHoles.size
    }

    override fun getItem(position: Int): Any {
        return mHoles[position]
    }

    override fun getItemId(position: Int): Long {
        return 0 // Not implemented
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        var convertView= convertView
        val holder: ViewHolder

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item, null)
            holder = ViewHolder()
            holder.holeLabel = convertView.findViewById<View>(R.id.textView_Hole_Label) as TextView
            holder.strokeCount = convertView.findViewById<View>(R.id.textView_Stroke_Count) as TextView
            holder.removeStrokeButton = convertView.findViewById<View>(R.id.button_Remove_Stroke) as Button
            holder.addStrokeButton = convertView.findViewById<View>(R.id.button_Add_Stroke) as Button
            convertView.tag = holder
        } else {
            holder = convertView.tag as ViewHolder
        }

        holder.holeLabel.text = mHoles[position].getLabel()
        holder.strokeCount.text = mHoles[position].getStrokeCount().toString() + ""

        holder.removeStrokeButton.setOnClickListener {
            var updatedStrokeCount: Int = mHoles[position].getStrokeCount() - 1
            if (updatedStrokeCount < 0) updatedStrokeCount = 0
            mHoles[position].setStrokeCount(updatedStrokeCount)
            holder.strokeCount.text = updatedStrokeCount.toString() + ""
        }
        holder.addStrokeButton.setOnClickListener {
            val updatedStrokeCount: Int = mHoles[position].getStrokeCount() + 1
            mHoles[position].setStrokeCount(updatedStrokeCount)
            holder.strokeCount.text = updatedStrokeCount.toString() + ""
        }
        return convertView
    }

    private class ViewHolder {
        lateinit var holeLabel: TextView
        lateinit var strokeCount: TextView
        lateinit var removeStrokeButton: Button
        lateinit var addStrokeButton: Button
    }

}