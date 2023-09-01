package com.example.testechatgc

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testechatgc.databinding.CardContatoBinding

class ConversasAdapter(var mensagemlist: MutableList<ClassMensagem>, val clickListener: OnClickListener?): RecyclerView.Adapter<ConversasAdapter.MensagemViewHolder>() {

    interface OnClickListener {
        fun onClick(mensagem: ClassMensagem)
        fun onLongPress(mensagem: ClassMensagem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MensagemViewHolder {
        val binding = CardContatoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MensagemViewHolder(binding)
    }


    inner class MensagemViewHolder(private val binding: CardContatoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(mensagem: ClassMensagem) {
            binding.nomeContato.text = mensagem.contato
            binding.mensagem.text = mensagem.mensagem
            binding.horarioMensagem.text = mensagem.horario

            val currentAdapterPosition = adapterPosition // Captura a posição atual

            binding.cardContato.setOnClickListener {
                if (currentAdapterPosition != RecyclerView.NO_POSITION) {
                    clickListener?.onClick(mensagemlist[currentAdapterPosition])
                    val mensagem = mensagemlist[currentAdapterPosition]
                    mensagem.status = "lida"
                    clickListener?.onLongPress(mensagem)
                    notifyDataSetChanged()
                }
            }

            binding.cardContato.setOnLongClickListener {
                if (currentAdapterPosition != RecyclerView.NO_POSITION) {
                    val mensagem = mensagemlist[currentAdapterPosition]
                    mensagem.status = "lida"
                    clickListener?.onLongPress(mensagem)
                    notifyDataSetChanged()
                }
                true
            }
        }
    }

    override fun getItemCount(): Int {
        return mensagemlist.size
    }

    override fun onBindViewHolder(holder: MensagemViewHolder, position: Int) {
        val mensagem = mensagemlist[position]
        holder.bind(mensagem)
        if (mensagemlist.isNotEmpty()) {
            val mensagem = mensagemlist[position]

            if (mensagem.status == "lida") {

                holder.itemView.findViewById<View>(R.id.ic_notificacao).visibility = View.GONE
                }
            else
            {
                holder.itemView.findViewById<View>(R.id.ic_notificacao).visibility = View.VISIBLE
            }
        }
    }
}