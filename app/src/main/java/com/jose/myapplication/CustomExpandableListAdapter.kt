package com.jose.myapplication

// CustomExpandableListAdapter.kt

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView

class CustomExpandableListAdapter(private val context: Context, private val expandableListItems: List<ExpandableListItem>) : BaseExpandableListAdapter() {

    override fun getGroupCount(): Int {
        return expandableListItems.size
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return expandableListItems[groupPosition].items.size
    }

    override fun getGroup(groupPosition: Int): Any {
        return expandableListItems[groupPosition]
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        return expandableListItems[groupPosition].items[childPosition]
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun getGroupView(groupPosition: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.expandable_list_item, parent, false)
        val header = view.findViewById<TextView>(R.id.listHeader)
        header.text = (getGroup(groupPosition) as ExpandableListItem).header
        return view
    }

    override fun getChildView(groupPosition: Int, childPosition: Int, isLastChild: Boolean, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_list_course, parent, false)
        val listItem = view.findViewById<TextView>(R.id.tvTitle)
        listItem.text = getChild(groupPosition, childPosition) as String

        val divider = view.findViewById<View>(R.id.divider)
        // Ocultar el divisor para el último elemento de cada grupo
        if (isLastChild) {
            //divider.visibility = View.GONE no se queda bien
            divider.visibility = View.INVISIBLE
        } else {
            divider.visibility = View.VISIBLE
        }

        return view
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }
}
