package com.jose.myapplication

import android.os.Bundle
import android.widget.ExpandableListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.jose.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var expandableListView: ExpandableListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Codigo

        expandableListView = findViewById(R.id.elv)
        val expandableListItems = generateExpandableListItems()

        val expandableListAdapter = CustomExpandableListAdapter(this, expandableListItems)
        expandableListView.setAdapter(expandableListAdapter)

        // Escuchar eventos de clic en los elementos de la lista
        expandableListView.setOnChildClickListener { _, _, groupPosition, childPosition, _ ->
            val selectedItem = expandableListItems[groupPosition].items[childPosition]
            Toast.makeText(applicationContext, "Seleccionado: $selectedItem", Toast.LENGTH_SHORT).show()
            false
        }


    }

    private fun generateExpandableListItems(): List<ExpandableListItem> {
        val expandableListItems = mutableListOf<ExpandableListItem>()

        // Agrega grupos y elementos de lista
        expandableListItems.add(ExpandableListItem("Grupo 1", listOf("Elemento 1.1", "Elemento 1.2", "Elemento 1.3")))
        expandableListItems.add(ExpandableListItem("Grupo 2", listOf("Elemento 2.1", "Elemento 2.2", "Elemento 2.3")))
        expandableListItems.add(ExpandableListItem("Grupo 3", listOf("Elemento 3.1", "Elemento 3.2", "Elemento 3.3")))
        expandableListItems.add(ExpandableListItem("Grupo 4", listOf("Elemento 4.1", "Elemento 4.2", "Elemento 4.3")))
        expandableListItems.add(ExpandableListItem("Grupo 5", listOf("Elemento 5.1", "Elemento 5.2", "Elemento 5.3")))
        expandableListItems.add(ExpandableListItem("Grupo 6", listOf("Elemento 6.1", "Elemento 6.2", "Elemento 6.3")))

        return expandableListItems
    }
}