package com.example.testechatgc

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testechatgc.databinding.ActivityConversasBinding

class ContatosActivity : AppCompatActivity() {
    private lateinit var binding: ActivityConversasBinding
    private lateinit var rvMensagens: RecyclerView
    private var mensagemlist: MutableList<ClassMensagem> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConversasBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        supportActionBar?.hide()

        mensagemlist.add(ClassMensagem("João", "Olá, como vai?", "09:00 AM"))
        mensagemlist.add(ClassMensagem("Maria", "Tudo bem?", "09:15 AM"))
        mensagemlist.add(ClassMensagem("Carlos", "Nos vemos mais tarde!", "10:30 AM"))
        mensagemlist.add(ClassMensagem("Ana", "Oi!", "11:45 AM"))
        mensagemlist.add(ClassMensagem("Pedro", "E aí?", "12:30 PM"))
        mensagemlist.add(ClassMensagem("Isabela", "Como tem passado?", "01:15 PM"))
        mensagemlist.add(ClassMensagem("Rafael", "Vamos nos encontrar amanhã?", "02:00 PM"))
        mensagemlist.add(ClassMensagem("Julia", "Quer sair para tomar um café?", "02:30 PM"))
        mensagemlist.add(ClassMensagem("Lucas", "Estou a caminho!", "03:00 PM"))
        mensagemlist.add(ClassMensagem("Larissa", "Vai chover hoje?", "03:30 PM"))
        mensagemlist.add(ClassMensagem("Gustavo", "Você viu o novo filme?", "04:00 PM"))
        mensagemlist.add(ClassMensagem("Amanda", "Não acredito no que aconteceu!", "04:30 PM"))
        mensagemlist.add(ClassMensagem("Fernando", "Parabéns pelo novo emprego!", "05:00 PM"))
        mensagemlist.add(ClassMensagem("Mariana", "Que tal um jantar?", "05:30 PM"))
        mensagemlist.add(ClassMensagem("Diego", "Vamos malhar juntos?", "06:00 PM"))
        mensagemlist.add(ClassMensagem("Camila", "Você viu as últimas notícias?", "06:30 PM"))
        mensagemlist.add(ClassMensagem("Thiago", "Estou entediado, vamos fazer algo?", "07:00 PM"))
        mensagemlist.add(ClassMensagem("Laura", "Saudades de você!", "07:30 PM"))
        mensagemlist.add(ClassMensagem("Guilherme", "O que está fazendo agora?", "08:00 PM"))
        mensagemlist.add(ClassMensagem("Beatriz", "Como foi o seu dia?", "08:30 PM"))
        mensagemlist.add(ClassMensagem("Marcos", "Preciso da sua ajuda com um projeto.", "09:00 PM"))

        val listener = object : ConversasAdapter.OnClickListener {
            override fun onClick(mensagem: ClassMensagem) {
                val intent = Intent(this@ContatosActivity, ChatActivity::class.java)
                intent.putExtra("mensagem", mensagem)
                startActivity(intent)
            }

            override fun onLongPress(mensagem: ClassMensagem) {

            }
        }

        rvMensagens = binding.rvMensagens
        rvMensagens.layoutManager = LinearLayoutManager(this)
        val adapter = ConversasAdapter(mensagemlist, listener)
        rvMensagens.adapter = adapter
    }

}