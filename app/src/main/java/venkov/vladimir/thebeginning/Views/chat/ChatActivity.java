package venkov.vladimir.thebeginning.Views.chat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ServerValue;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import venkov.vladimir.thebeginning.R;
import venkov.vladimir.thebeginning.Views.chat.adapters.MessagesAdapter;
import venkov.vladimir.thebeginning.Views.chat.select_user.SelectUserActivity;
import venkov.vladimir.thebeginning.Views.chat.utils.GPSTracker;
import venkov.vladimir.thebeginning.Views.chat.utils.Messages;
import venkov.vladimir.thebeginning.models.User;

public class ChatActivity extends AppCompatActivity {

    private User mLoggedUser;
    private User mTargetUser;
    //init db ref
    private DatabaseReference mDatabaseRef;
    private DatabaseReference mLoggedUserDBRef;
    private ArrayList<Messages> mMessagesList;
    private MessagesAdapter mMessagesAdapter;
    private StorageReference mStorageRef;
    private Uri imageUri;

    @BindView(R.id.btn_send_chat_log)
    ImageButton mSendMessage;

    @BindView(R.id.et_chat_log)
    EditText mMessageField;

    @BindView(R.id.rv_chat_log)
    RecyclerView mMessagesRecyclerView;

    @BindView(R.id.imageButton)
    ImageButton mSendImageButton;

    //private Accommodation mCurrentAccommodation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        //Hide keyboard at start
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        Intent intent = getIntent();
        mLoggedUser = (User) intent.getSerializableExtra(SelectUserActivity.EXTRA_LOGGED_USER);
        mTargetUser = (User) intent.getSerializableExtra(SelectUserActivity.EXTRA_TARGET_USER);

        ButterKnife.bind(this);

        mMessagesList = new ArrayList<>();

        mMessagesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mMessagesAdapter = new MessagesAdapter(mMessagesList, mLoggedUser, this);
        mMessagesRecyclerView.setAdapter(mMessagesAdapter);


        getSupportActionBar().setTitle(mTargetUser.getFirstName() + " " + mTargetUser.getLastName());

        //init ref
        mDatabaseRef = FirebaseDatabase.getInstance().getReference();
        mStorageRef = FirebaseStorage.getInstance().getReference();

        //init firebase reference to the logged user
        mLoggedUserDBRef = mDatabaseRef.child("users").child(String.valueOf(mLoggedUser));

        /*
        mCurrentAccommodation =
                (Accommodation) intent.getSerializableExtra
                        (AccommodationDetailsActivity.ACCOMMODATION_EXTRA_KEY);
        int b = 5;*/
        loadMessages();
        Log.d("proba", "onCreate: targetuser " + mTargetUser + " mlogged " + mLoggedUser);
    }


    @OnClick(R.id.btn_send_chat_log)
    public void onBtnSendMessageClick() {
        sendMessage("");
    }

    public void sendMessage(String message) {


        if (TextUtils.isEmpty(message)) {
            ///get the message from our messageField
            message = mMessageField.getEditableText().toString();
        }

        //check if the message is empty
        if (!TextUtils.isEmpty(message)) {

            //current user ref and chat user ref
            final String currentUserRef = "messages/" + mLoggedUser + "/" + mTargetUser;
            final String chatUserRef = "messages/" + mTargetUser + "/" + mLoggedUser;

            //create a push id for the mssage
            DatabaseReference userMessagePush = FirebaseDatabase.getInstance().getReference()
                    .child("messages").child(mLoggedUser.getFirstName())
                    .child(mTargetUser.getFirstName()).push();

            //get the push id
            String pushId = userMessagePush.getKey();

            //create stamp of the message
            Map messageMap = new HashMap();
            messageMap.put("message", message);
            messageMap.put("type", "text");
            messageMap.put("time", ServerValue.TIMESTAMP);
            messageMap.put("from", mLoggedUser.getFirstName());

            //put the mssage in both users
            Map messageUserMap = new HashMap();
            messageUserMap.put(currentUserRef + "/" + pushId, messageMap);
            messageUserMap.put(chatUserRef + "/" + pushId, messageMap);


            //update the root ref for that message
            mDatabaseRef.updateChildren(messageUserMap, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(@Nullable final DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {

                    Toast.makeText(ChatActivity.this, "MessageSend", Toast.LENGTH_SHORT).show();

                    mMessageField.getText().clear();


                    //for notification
             /*       mLoggedUserDBRef.child("name").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            final Map<String, String> notificationData = new HashMap<>();

                            notificationData.put("fromUser", dataSnapshot.getValue().toString());

                            notificationData.put("message", messageField.getText().toString());
                            notificationDatabase.child(chatUserEmail.replace(".", "-")).push().setValue(notificationData).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    //     sendFCMPush(messageField.getText().toString(), loggedUser);
                                    //clear the edit field
                                    messageField.getText().clear();
                                }
                            });
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });*/


                }
            });
        }
    }


    private void loadMessages() {
        //init messages reference
        DatabaseReference messagesReference = mDatabaseRef.child("messages")
                .child(String.valueOf(mLoggedUser))
                .child(String.valueOf(mTargetUser));

        //query to load part of the messages as message pages
        Query message = messagesReference;

        //add child event listener to the query to get the messages
        message.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                //init message into our Messages class
                Messages message = dataSnapshot.getValue(Messages.class);
                message.setImageFrom(mTargetUser.getImageOfTheUser());
                message.setImageTo(mLoggedUser.getImageOfTheUser());
                message.setTo(mTargetUser.getFirstName());
                                /*//increment item pos
                itemPos++;*/
                /*//if last key equal to 1 then set last key to the first key in the recycler
                if (itemPos == 1) {
                    lastKey = dataSnapshot.getKey().toString();
                }*/
                //add the message to our messages list
                mMessagesList.add(message);
                //notify the adapter for data changed
                mMessagesAdapter.notifyDataSetChanged();
                //scroll to position bottm of reyccler view
                mMessagesRecyclerView.scrollToPosition(mMessagesList.size() - 1);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    @OnClick(R.id.imageButton)
    public void openCamera() {


        if (checkPermission()) {


            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, 1);

        } else {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {


                } else {
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.CAMERA},
                            3);
                }

            }
        }
        if (!isLocationEnabled(this)) {
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(intent);
        }


    }


    public static boolean isLocationEnabled(Context context) {
        int locationMode = 0;
        String locationProviders;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            try {
                locationMode = Settings.Secure.getInt(context.getContentResolver(), Settings.Secure.LOCATION_MODE);

            } catch (Settings.SettingNotFoundException e) {
                e.printStackTrace();
                return false;
            }

            return locationMode != Settings.Secure.LOCATION_MODE_OFF;

        } else {
            locationProviders = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
            return !TextUtils.isEmpty(locationProviders);
        }


    }

    private boolean checkPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            return false;
        }
        return true;
    }

    GPSTracker gps;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        try {
            if (requestCode == 1) {
                if (resultCode == Activity.RESULT_OK && data != null) {

                    Bitmap bmp = (Bitmap) Objects.requireNonNull(data.getExtras()).get("data");
                    gps = new GPSTracker(this);

                    LocationManager lm = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        ActivityCompat.requestPermissions(this,
                                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                                6);
                        ActivityCompat.requestPermissions(this,
                                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                9);

                        return;
                    }
                    Location location = (Location) lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    //  Log.d("proba", "onActivityResult: " + location.getLatitude() + " " + location.getLongitude());


                    if (gps.canGetLocation()) {
                        double latitude = gps.getLatitude();
                        double longitude = gps.getLongitude();

                        sendImageMessage(bmp, latitude, longitude);
                        String message = "https://www.google.com/maps/place/" + latitude + "," + longitude;
                        sendMessage(message);
                    }

                }
            }
        } catch (Exception e) {
            Toast.makeText(this, e + "Something went wrong", Toast.LENGTH_LONG).show();
        }
    }


    public void sendImageMessage(Bitmap bitmap, double latitude, double longitude) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);

        String imageEcoded = Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT);

        //current user ref and chat user ref
        final String currentUserRef = "messages/" + mLoggedUser + "/" + mTargetUser;
        final String chatUserRef = "messages/" + mTargetUser + "/" + mLoggedUser;

        //create a push id for the mssage
        DatabaseReference userMessagePush = FirebaseDatabase.getInstance().getReference()
                .child("messages").child(mLoggedUser.getFirstName())
                .child(mTargetUser.getFirstName()).push();

        //get the push id
        String pushId = userMessagePush.getKey();

        //create stamp of the message
        Map messageMap = new HashMap();
        messageMap.put("message", imageEcoded);
        messageMap.put("type", "image");
        messageMap.put("time", ServerValue.TIMESTAMP);
        messageMap.put("from", mLoggedUser.getFirstName());
        messageMap.put("latitude", latitude);
        messageMap.put("longitude", longitude);

        //put the mssage in both users
        Map messageUserMap = new HashMap();
        messageUserMap.put(currentUserRef + "/" + pushId, messageMap);
        messageUserMap.put(chatUserRef + "/" + pushId, messageMap);


        //update the root ref for that message
        mDatabaseRef.updateChildren(messageUserMap, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable final DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {

                Toast.makeText(ChatActivity.this, "MessageSend", Toast.LENGTH_SHORT).show();

                mMessageField.getText().clear();

            }
        });

    }
}
