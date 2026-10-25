package com.example.prycursosequipo1.Adaptador

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.button.MaterialButton
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.prycursosequipo1.R
import com.example.prycursosequipo1.Modelo.dataCursos
import com.example.prycursosequipo1.Vista.MainActivity

class CursosAdaptador(
    private val activity: MainActivity,
    private var listaCursos: List<dataCursos>
) : RecyclerView.Adapter<CursosAdaptador.CursosViewHolder>() {

    class CursosViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        val tvNombre: TextView =
            itemView.findViewById(R.id.txtCurso)

        val tvDetalle: TextView =
            itemView.findViewById(R.id.txtDetalle)

        val imgCurso: ImageView =
            itemView.findViewById(R.id.imgCurso)

        val btnDetalle: MaterialButton =
            itemView.findViewById(R.id.btnDetalle)

        val btnRegistro: MaterialButton =
            itemView.findViewById(R.id.btnRegistro)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CursosViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_curso, parent, false)

        return CursosViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: CursosViewHolder,
        position: Int
    ) {

        val curso = listaCursos[position]

        holder.tvNombre.text = curso.nombre
        holder.tvDetalle.text = curso.descripcion

        val baseUrl = "http://192.168.3.54/cursos_api/"

        val urlImagenCompleta =
            baseUrl + curso.urlImagenPrincipal

        Glide.with(holder.itemView.context)
            .load(urlImagenCompleta)
            .placeholder(android.R.drawable.ic_menu_gallery)
            .error(android.R.drawable.stat_notify_error)
            .centerCrop()
            .into(holder.imgCurso)

        /*holder.btnDetalle.setOnClickListener {
            activity.seleccionarCurso(curso)
        }*/

        holder.btnRegistro.setOnClickListener {
            // Aquí se implementará el registro al curso.
        }
    }

    override fun getItemCount(): Int {
        return listaCursos.size
    }

    fun actualizarLista(nuevaLista: List<dataCursos>){
        listaCursos = nuevaLista
        notifyDataSetChanged()
    }
}