package venkov.vladimir.thebeginning.Views.chat.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import venkov.vladimir.thebeginning.R;
import venkov.vladimir.thebeginning.Views.chat.utils.Messages;
import venkov.vladimir.thebeginning.models.User;

public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.MessagesViewHolder> {

    private List<Messages> messagesList;
    private Context context;
    public Messages message;
    private User mLoggedUser;

    //constructor
    public MessagesAdapter(List<Messages> messagesList, User mLoggedUser, Context context) {
        this.messagesList = messagesList;
        this.context = context;
        this.mLoggedUser = mLoggedUser;
    }


    @Override
    public MessagesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.chat_to_row, parent, false);
        return new MessagesViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final MessagesViewHolder holder, int position) {
        /*//init the logged user
        final String loggedUser = loggedPrefs.getString("user_email", "");
*/
        //init message from list
        message = messagesList.get(position);

        //get message from who ?
        String from = message.getFrom();


        //init new date and simple date format to format the date into string value of our choosing
        Date date = new Date(message.getTime());
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        String dateFormatted = formatter.format(date);


        int fiftyDP = (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                50, context.getResources().getDisplayMetrics()));
        int eightDP = (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                8, context.getResources().getDisplayMetrics()));

        int hundredAndEightyDP = (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                180, context.getResources().getDisplayMetrics()));
        int hundredAndSeventyDP = (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                170, context.getResources().getDisplayMetrics()));

        RelativeLayout.LayoutParams profileImageParams = new RelativeLayout.LayoutParams(fiftyDP, fiftyDP);
        RelativeLayout.LayoutParams messageParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams imageMessageParams = new RelativeLayout.LayoutParams(hundredAndEightyDP, hundredAndSeventyDP);
        RelativeLayout.LayoutParams timeStampParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);


        if (message.getTo().equals(message.getFrom())) {

            Picasso.get().load(message.getImageFrom()).into(holder.profileImage);
            profileImageParams.addRule(RelativeLayout.ALIGN_PARENT_START);
            profileImageParams.leftMargin = eightDP;

            messageParams.addRule(RelativeLayout.END_OF, holder.profileImage.getId());
            messageParams.leftMargin = eightDP;
            holder.messageText.setBackgroundTintList(context.getResources().getColorStateList(R.color.colorPrimary));

            timeStampParams.addRule(RelativeLayout.END_OF, holder.profileImage.getId());

            imageMessageParams.addRule(RelativeLayout.END_OF, holder.profileImage.getId());
            imageMessageParams.leftMargin = eightDP;


        } else {

            Picasso.get().load(message.getImageTo()).into(holder.profileImage);
            profileImageParams.addRule(RelativeLayout.ALIGN_PARENT_END);
            profileImageParams.rightMargin = eightDP;


            messageParams.addRule(RelativeLayout.START_OF, holder.profileImage.getId());
            messageParams.rightMargin = eightDP;


            timeStampParams.addRule(RelativeLayout.START_OF, holder.profileImage.getId());


            imageMessageParams.addRule(RelativeLayout.START_OF, holder.profileImage.getId());
            imageMessageParams.rightMargin = eightDP;
        }

        if (message.getType().equals("image")) {

            holder.messageText.setVisibility(View.GONE);
            holder.imageMessage.setVisibility(View.VISIBLE);
            holder.imageMessage.setImageBitmap(byteArrayToBitmap(message.getMessage()));

            timeStampParams.addRule(RelativeLayout.BELOW, holder.imageMessage.getId());

        } else {

            holder.imageMessage.setVisibility(View.GONE);
            holder.messageText.setVisibility(View.VISIBLE);
            holder.messageText.setText(message.getMessage());

            timeStampParams.addRule(RelativeLayout.BELOW, holder.messageText.getId());
        }

        timeStampParams.topMargin = eightDP;
        imageMessageParams.topMargin = eightDP;
        profileImageParams.topMargin = eightDP;
        messageParams.topMargin = eightDP;


        holder.timeStamp.setText(dateFormatted);


        holder.profileImage.setLayoutParams(profileImageParams);
        holder.messageText.setLayoutParams(messageParams);
        holder.timeStamp.setLayoutParams(timeStampParams);
        holder.imageMessage.setLayoutParams(imageMessageParams);
    }

    public Bitmap byteArrayToBitmap(String image) {
        byte[] b = Base64.decode(image, Base64.DEFAULT);

        return BitmapFactory.decodeByteArray(b, 0, b.length);
    }


    @Override
    public int getItemCount() {
        return messagesList.size();
    }


    //-------------Methods used for remembering position of views on recycling----------------

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }
    //--------------------------------------------------------------------------------------------

    //------------------------------MESSAGES VIEW HOLDER -------------
    public class MessagesViewHolder extends RecyclerView.ViewHolder {

        private TextView messageText;
        private ImageView profileImage, imageMessage;
        private TextView timeStamp;


        public MessagesViewHolder(View itemView) {
            super(itemView);
            //init message view text and profile image view

            messageText = itemView.findViewById(R.id.tv_to_row);
            profileImage = itemView.findViewById(R.id.iv_to_row);
            timeStamp = itemView.findViewById(R.id.tv_created_at_to_row);
            imageMessage = itemView.findViewById(R.id.imageView);


        }

    }
}
