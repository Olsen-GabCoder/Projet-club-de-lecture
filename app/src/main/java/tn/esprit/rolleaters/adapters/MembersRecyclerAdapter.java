package tn.esprit.rolleaters.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;

import java.util.List;

import tn.esprit.rolleaters.R;
import tn.esprit.rolleaters.models.Member;
import tn.esprit.rolleaters.activities.ChatActivity;

public class MembersRecyclerAdapter extends RecyclerView.Adapter<MembersRecyclerAdapter.MemberViewHolder> {

    private Context context;
    private List<Member> membersList;

    // Constructeur
    public MembersRecyclerAdapter(Context context, List<Member> membersList) {
        this.context = context;
        this.membersList = membersList;
    }

    @NonNull
    @Override
    public MemberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_members, parent, false);
        return new MemberViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MemberViewHolder holder, int position) {
        Member member = membersList.get(position);

        // Remplir les données dans les vues
        holder.tvName.setText(member.getName());
        holder.lastMessage.setText(member.getLastMessage());
        holder.tvHour.setText(member.getHour());
        holder.ivFriend.setImageResource(member.getAvatarResId());

        // Gestion des clics
        holder.itemView.setOnClickListener(v -> {
            // Navigation vers l'activité de chat
            Intent intent = new Intent(context, ChatActivity.class);
            intent.putExtra("memberName", member.getName()); // Passage du nom du membre
            intent.putExtra("memberAvatarResId", member.getAvatarResId()); // Passage de l'avatar du membre
            intent.putExtra("friend", member.getName()); // **Ligne ajoutée**
            context.startActivity(intent);

            // Affichage d'un message (facultatif)
            Toast.makeText(context, "Vous avez cliqué sur : " + member.getName(), Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return membersList.size();
    }

    // ViewHolder interne
    public static class MemberViewHolder extends RecyclerView.ViewHolder {

        ShapeableImageView ivFriend;
        TextView tvName;
        TextView lastMessage;
        TextView tvHour;

        public MemberViewHolder(@NonNull View itemView) {
            super(itemView);

            ivFriend = itemView.findViewById(R.id.ivFriend);
            tvName = itemView.findViewById(R.id.tvName);
            lastMessage = itemView.findViewById(R.id.lastMessage);
            tvHour = itemView.findViewById(R.id.tvHour);
        }
    }
}
