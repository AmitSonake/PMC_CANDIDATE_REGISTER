package com.example.registercandidate.adapters


import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.registercandidate.EditCandidate
import com.example.registercandidate.model.Candidate
import com.example.votersearch.databinding.CandidateListItemBinding
import java.io.OutputStream


class CandidateListAdapter(private var candidateList: List<Candidate>, private var outputStream: OutputStream? = null)
    : RecyclerView.Adapter<CandidateListAdapter.ViewHolder>() {

   inner class ViewHolder(val binding: CandidateListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflate the custom view from xml layout file
        val binding =CandidateListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // display the current animal
        holder.binding.tvVoterName.text = candidateList[position].CandidateNameEnglish
        holder.binding.tvVoterAddress.text = candidateList[position].Address
        holder.binding.btnView.setOnClickListener {
            val intent = Intent(it.context, EditCandidate::class.java)
            intent.putExtra("candidate_object", candidateList.get(position))
            startActivity(it.context,intent,null)
        }
       /* holder.itemView.setOnClickListener { v ->
            val position = holder.adapterPosition
            val voter: VoterData = candidateList.get(position) // Get the Voter object
            val intent: Intent = Intent(v.context, SearchDetailsActivity::class.java)
            intent.putExtra("voter_object", voter) // Pass the object
           startActivity(v.context,intent,null)
        }*/
    }

    override fun getItemCount(): Int {
        // number of items in the data set held by the adapter
        return candidateList.size
    }

    fun setData(newList: List<Candidate>) {
        candidateList = newList
        notifyDataSetChanged()
    }
    // this two methods useful for avoiding duplicate item

}