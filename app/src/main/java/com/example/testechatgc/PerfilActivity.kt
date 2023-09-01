package com.example.testechatgc

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testechatgc.databinding.ActivityPerfilBinding
import PerfilAdapter
import android.util.Log
import java.util.Locale

class PerfilActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPerfilBinding
    private lateinit var rvPerfil: RecyclerView
    private lateinit var chatAdapter: PerfilAdapter
    var nome_contato = "João Carneiro"
    var imagem_contato: Int = R.drawable.foto_perfil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPerfilBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        supportActionBar?.hide()

        rvPerfil = binding.rvPerfil
        chatAdapter = PerfilAdapter()
        rvPerfil.layoutManager = LinearLayoutManager(this)
        rvPerfil.adapter = chatAdapter

        val perfillist: MutableList<ClassMensagem> = mutableListOf()
        perfillist.add(ClassMensagem("João Carneiro", "Olá, como vai?", "09:00 AM"))
        chatAdapter. perfilList = perfillist
        chatAdapter.notifyDataSetChanged()

        binding.icVoltarPerfil.setOnClickListener {
            onBackPressed()
        }




        binding.collapsingToolbar.title = " "

        binding.collapsingToolbar.setCollapsedTitleTextColor(ContextCompat.getColor(this, R.color.white))

        setGroupImage()

        binding.appBar.addOnOffsetChangedListener(AppBarStateChangeListener(::onChangeOffset))
    }


    private fun onChangeOffset(state: AppBarStateChangeListener.State) {
        if (state == AppBarStateChangeListener.State.COLLAPSED && nome_contato != null) {
            binding.collapsingToolbar.title = nome_contato.toUpperCase(Locale.ROOT)
            binding.viewPerfil.visibility = View.GONE
        } else {
            binding.collapsingToolbar.title = " "
            binding.viewPerfil.visibility = View.VISIBLE
        }
    }

    private fun setGroupImage() {
        if (nome_contato != null) {
            binding.viewPerfil.setImageResource(imagem_contato)
        }
    }


}