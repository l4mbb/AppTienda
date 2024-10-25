package com.example.appmitienda

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class ListaProductosActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var productoAdapter: ProductoAdapter
    private lateinit var listaProductos: List<Producto>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_productos)

        recyclerView = findViewById(R.id.rvProductos)
        recyclerView.layoutManager = LinearLayoutManager(this)

        //Lista de productos
        listaProductos = listOf(
            Producto("Play Station 5", "$2'500.000", R.drawable.ps5),
            Producto("Steam Deck ", "$1'850.000", R.drawable.steam),
            Producto("Televisor 60 Pulgadas", "$3'000.000", R.drawable.tv),
            Producto("Iphone 14", "$2'000.000", R.drawable.iphone),
            Producto("Tabler Generica", "$750.000", R.drawable.tablet),
            Producto("Computador Unico", "$1'300.000", R.drawable.compu),
            )

        // Configurar el adaptador
        productoAdapter = ProductoAdapter(listaProductos)
        recyclerView.adapter = productoAdapter

        //Inicializar el boton
        val btnIrCarrito = findViewById<Button>(R.id.btnIrCarrito)

        // Para que el boton redirija a CarritoActivity
         btnIrCarrito.setOnClickListener{
            val intent = Intent(this,CarritoActivity::class.java)
            startActivity(intent)
        }
    }
}
