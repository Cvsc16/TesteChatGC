import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testechatgc.ClassMensagem
import com.example.testechatgc.databinding.CardMensagemEnviadaBinding
import com.example.testechatgc.databinding.CardMensagemRecebidaBinding

class ChatAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var chatList = mutableListOf<ClassMensagem>()

    companion object {
        const val TIPO_ENVIADA = 1
        const val TIPO_RECEBIDA = 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TIPO_ENVIADA -> {
                val binding = CardMensagemEnviadaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                EnviadaViewHolder(binding)
            }
            TIPO_RECEBIDA -> {
                val binding = CardMensagemRecebidaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                RecebidaViewHolder(binding)
            }
            else -> throw IllegalArgumentException("Tipo de View não suportado")
        }
    }

    override fun getItemCount(): Int {
        return chatList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val chat = chatList[position]
        when (holder) {
            is EnviadaViewHolder -> holder.bind(chat)
            is RecebidaViewHolder -> holder.bind(chat)
        }
    }

    override fun getItemViewType(position: Int): Int {
        val chat = chatList[position]
        return if (chat.contato == "Você") {
            TIPO_ENVIADA
        } else {
            TIPO_RECEBIDA
        }
    }

    inner class EnviadaViewHolder(private val binding: CardMensagemEnviadaBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(chat: ClassMensagem) {
            binding.mensagemEnviada.text = chat.mensagem
        }
    }

    inner class RecebidaViewHolder(private val binding: CardMensagemRecebidaBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(chat: ClassMensagem) {
            binding.mensagemRecebida.text = chat.mensagem
        }
    }

    fun insertMessage(message: ClassMensagem) {
        this.chatList.add(message)
        notifyItemInserted(chatList.size)
    }
}