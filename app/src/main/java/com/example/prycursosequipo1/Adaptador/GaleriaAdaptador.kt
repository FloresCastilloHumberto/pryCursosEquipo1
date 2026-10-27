package com.example.prycursosequipo1.Adaptador

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.prycursosequipo1.Modelo.dataCursos
import com.example.prycursosequipo1.R

class GaleriaAdaptador (private var listaCursos: List<dataCursos>):
    RecyclerView.Adapter<GaleriaAdaptador.GaleriaViewHolder>() {

    // Antes tenemos que crear una clase Holder
    class GaleriaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgCurso: ImageView = itemView.findViewById(R.id.imgCurso)
        val txtNombreCurso: TextView = itemView.findViewById(R.id.txtCurso)
    }

    // Estas 3 funciones se van a programar. Son contratos
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GaleriaViewHolder {
        // Vamos a inflar el layout item_cursos.xml
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_curso, parent, false)

        return GaleriaViewHolder(view)
    }

    override fun onBindViewHolder(holder: GaleriaViewHolder, position: Int) {
        val curso = listaCursos[position]

        holder.txtNombreCurso.text = curso.nombre

        val baseUrl = "http://192.168.5.200/cursos_api/"
        val urlCompleta = baseUrl + curso.urlImagenPrincipal

        Glide.with(holder.itemView.context)
            .load(urlCompleta)
            .placeholder(android.R.drawable.ic_menu_gallery)
            .error(android.R.drawable.stat_notify_error)
            .into(holder.imgCurso)
    }

    override fun getItemCount(): Int {
        return listaCursos.size
    }

    fun actualizarLista(nuevaLista: List<dataCursos>) {
        listaCursos = nuevaLista
        notifyDataSetChanged()
    }
}