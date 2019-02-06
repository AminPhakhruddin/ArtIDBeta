package com.luminaryid.android.artidbeta;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder> {

    private List<Messages> mMessageList;
    private FirebaseAuth mAuth;
    private DatabaseReference mRootRef;
    private String fromUser;
    private String toUser;

    public MessageAdapter(List<Messages> mMessageList){
        this.mMessageList = mMessageList;
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.messages_single_layout, parent, false);
        return new MessageViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder messageViewHolder, int i) {
        mAuth = FirebaseAuth.getInstance();
        mRootRef = FirebaseDatabase.getInstance().getReference();
        String current_user_id = mAuth.getCurrentUser().getUid();

        Messages c = mMessageList.get(i);

        //String fromUser = mRootRef.child("Users").child(c.getFrom()).child("name").getKey().toString();
        //Log.d("Current User's name", fromUser);
        //String chatUser
        mRootRef.child("Users").child(c.getFrom()).child("name").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                fromUser = dataSnapshot.getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        String from_user = c.getFrom();
        String message_type = c.getType();

        if(from_user.equals(current_user_id)){
            //messageViewHolder.messageText.setBackgroundColor(Color.WHITE);
            messageViewHolder.messageText.setTextColor(Color.BLACK);
            //messageViewHolder.nameText.setText(fromUser);

        } else {
            //messageViewHolder.messageText.setBackgroundResource(R.drawable.message_text_background);
            messageViewHolder.messageText.setTextColor(Color.BLACK);
            //messageViewHolder.nameText.setText(fromUser);
        }

        messageViewHolder.messageText.setText(c.getMessage());
        //messageViewHolder.nameText.setText(fromUser);
        //messageViewHolder.timeText.setText(c.getTime());

        if(message_type.equals("text")){
            messageViewHolder.messageText.setText(c.getMessage());
            messageViewHolder.messageImage.setVisibility(View.INVISIBLE);
        } else {
            messageViewHolder.messageText.setVisibility(View.INVISIBLE);
            Picasso.get().load(c.getMessage()).placeholder(R.drawable.profiledefault).into(messageViewHolder.messageImage);

        }
    }

    @Override
    public int getItemCount() {
        return mMessageList.size();
    }


    public class MessageViewHolder extends RecyclerView.ViewHolder {

        public TextView messageText;
        public CircleImageView profileImage;
        public TextView nameText;
        public ImageView messageImage;
        //public TextView timeText;

        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);

            messageText = (TextView) itemView.findViewById(R.id.message_text_layout);
            profileImage = (CircleImageView) itemView.findViewById(R.id.message_profile_layout);
            nameText = (TextView) itemView.findViewById(R.id.name_text_layout);
            messageImage = (ImageView) itemView.findViewById(R.id.message_image_layout);
            //timeText = (TextView) itemView.findViewById(R.id.message_item_time);
        }
    }
}
