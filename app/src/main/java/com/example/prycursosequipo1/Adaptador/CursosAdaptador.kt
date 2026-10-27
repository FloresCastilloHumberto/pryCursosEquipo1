package com.example.prycursosequipo1.Adaptador

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.prycursosequipo1.Modelo.dataCursos
import com.example.prycursosequipo1.R


class CursosAdaptador (
    private var listaCursos: List<dataCursos>,
    private val seleccionarCurso: (dataCursos) -> Unit
) : RecyclerView.Adapter<CursosAdaptador.CursoViewHolder>() {

    class CursoViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        val txtNombreCurso: TextView =
            itemView.findViewById(R.id.txtCurso)

        val imgCurso: ImageView =
            itemView.findViewById(R.id.imgCurso)

        val btnInscribirse: Button =
            itemView.findViewById(R.id.btnRegistro)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CursoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_curso, parent, false)

        return CursoViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: CursoViewHolder,
        position: Int
    ) {
        val curso = listaCursos[position]

        holder.txtNombreCurso.text = curso.nombre

        val baseUrl = "http://192.168.5.200/cursos_api/"
        val urlImagenCompleta = baseUrl + curso.urlImagenPrincipal

        Glide.with(holder.itemView.context)
            .load(urlImagenCompleta)
            .placeholder(android.R.drawable.ic_menu_gallery)
            .error(android.R.drawable.stat_notify_error)
            .into(holder.imgCurso)

        holder.btnInscribirse.setOnClickListener {
            seleccionarCurso(curso)
        }
    }

    override fun getItemCount(): Int {
        return listaCursos.size
    }


    fun actualizarLista(nuevaLista: List<dataCursos>) {
        listaCursos = nuevaLista
        notifyDataSetChanged()
    }
}