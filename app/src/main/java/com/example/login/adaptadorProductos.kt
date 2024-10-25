package com.example.appmitienda

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// Datos del producto
data class Producto(val nombre: String, val precio: String, val imagen: Int)

// Crea el adaptador para el RecyclerView
class ProductoAdapter(private val listaProductos: List<Producto>) : RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder>() {

    class ProductoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNombre: TextView = itemView.findViewById(R.id.tvNombreProducto)
        val tvPrecio: TextView = itemView.findViewById(R.id.tvPrecioProducto)
        val ivImagen: ImageView = itemView.findViewById(R.id.ivProducto)
        val cbAgregar: CheckBox = itemView.findViewById(R.id.cbAgregarCarrito)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.item_producto, parent, false)
        return ProductoViewHolder(vista)
    }

    override fun onBindViewHolder(holder: ProductoViewHolder, position: Int) {
        val producto = listaProductos[position]
        holder.tvNombre.text = producto.nombre
        holder.tvPrecio.text = producto.precio
        holder.ivImagen.setImageResource(producto.imagen)
    }

    override fun getItemCount(): Int {
        return listaProductos.size
    }
}
