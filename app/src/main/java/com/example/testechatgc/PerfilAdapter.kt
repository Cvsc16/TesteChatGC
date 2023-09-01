import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testechatgc.ClassMensagem
import com.example.testechatgc.databinding.CardPerfilBinding

class PerfilAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var perfilList = mutableListOf<ClassMensagem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PerfilViewHolder {
        val binding = CardPerfilBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PerfilViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return perfilList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is PerfilViewHolder) {
            val perfil = perfilList[position]
            holder.bind(perfil)
        }
    }


    inner class PerfilViewHolder(private val binding: CardPerfilBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(perfil: ClassMensagem) {
            binding.tvNomePerfil.text = perfil.contato

        }
    }
}