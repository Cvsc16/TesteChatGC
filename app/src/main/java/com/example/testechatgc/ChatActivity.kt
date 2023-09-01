package com.example.testechatgc
import ChatAdapter
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testechatgc.databinding.ActivityChatBinding
import java.sql.Time
import java.text.SimpleDateFormat
import java.util.Locale

class ChatActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChatBinding
    private lateinit var rvChat: RecyclerView
    private lateinit var chatAdapter: ChatAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        var chatList = mutableListOf<ClassMensagem>()

        supportActionBar?.hide()

        rvChat = binding.rvChat
        chatAdapter = ChatAdapter()
        rvChat.layoutManager = LinearLayoutManager(this)
        rvChat.adapter = chatAdapter

        val chatlist: MutableList<ClassMensagem> = mutableListOf()
        chatlist.add(ClassMensagem("João", "Olá, como vai?", "09:00 AM"))
        chatlist.add(ClassMensagem("Você", "Estou bem, obrigado!", "09:05 AM"))
        chatlist.add(ClassMensagem("João", "Que bom! E como foi seu dia?", "09:10 AM"))

        chatAdapter.chatList = chatlist
        chatAdapter.notifyDataSetChanged()

        binding.btnEnviar.setOnClickListener {
            sendMessage()
        }

        binding.imgVoltar.setOnClickListener{
            onBackPressed()
        }

        binding.fmContato.setOnClickListener {
            val intent = Intent(
                this@ChatActivity, PerfilActivity::class.java).apply {}
            startActivity(intent)
        }
    }

    private fun sendMessage() {
        val message = binding.etEscritaChat.text.toString()
        val timeStamp = System.currentTimeMillis()

        if (message.isNotEmpty()) {
            val mensagemSend = ClassMensagem("Você", message, formatTimeStamp(timeStamp))
            chatAdapter.chatList.add(mensagemSend)
            binding.etEscritaChat.text.clear()


            chatAdapter.notifyItemInserted(chatAdapter.chatList.size - 1)
            rvChat.scrollToPosition(chatAdapter.chatList.size - 1)

            val handler = Handler()
            handler.postDelayed({
                val mensagemReversed = ClassMensagem("João", reverseText(message), formatTimeStamp(System.currentTimeMillis()))
                chatAdapter.chatList.add(mensagemReversed)
                chatAdapter.notifyItemInserted(chatAdapter.chatList.size - 1)
                rvChat.scrollToPosition(chatAdapter.chatList.size - 1)
            }, 2000)
        }
    }

    private fun reverseText(text: String): String {
        return text.reversed()
    }


    private fun formatTimeStamp(timeStamp: Long): String {
        val sdf = SimpleDateFormat("HH:mm a", Locale.getDefault())
        return sdf.format(timeStamp)
    }
}



