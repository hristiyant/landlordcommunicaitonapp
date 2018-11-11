package venkov.vladimir.thebeginning.Views.chat.select_user;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

import venkov.vladimir.thebeginning.Constants;
import venkov.vladimir.thebeginning.MainActivity;
import venkov.vladimir.thebeginning.R;
import venkov.vladimir.thebeginning.Views.chat.ChatActivity;
import venkov.vladimir.thebeginning.Views.chat.adapters.UsersListCustomAdapter;
import venkov.vladimir.thebeginning.models.User;
import venkov.vladimir.thebeginning.parsers.GsonJsonParser;

public class SelectUserActivity extends AppCompatActivity implements UsersListCustomAdapter.ItemClickListener{

    public static final String EXTRA_TARGET_USER = "TargetUser";
    public static final String EXTRA_LOGGED_USER = "LoggedUser";

    private RecyclerView mRecyclerView;
    private UsersListCustomAdapter mAdapter;
    private ArrayList<User> mUsersList;
    private GsonJsonParser<User> mParser;

    private User mLoggedUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_user);

        getSupportActionBar().setTitle("Select user");

        Intent intent = getIntent();
        mLoggedUser = (User) intent.getSerializableExtra(MainActivity.EXTRA_KEY);


        RequestQueue requestQueue = Volley.newRequestQueue(this);

        String url;
        if(mLoggedUser.getLandlord()) {
            url = Constants.BASE_SERVER_URL + "/Users/tenants";
        } else {
            url = Constants.BASE_SERVER_URL + "/Users/landlords";
        }

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("TAG", response);
                mParser = new GsonJsonParser<User>(User.class, User[].class);
                mUsersList = new ArrayList<>(mParser.fromJsonArray(response));
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showData();
                    }
                });
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("TAG", error.toString());
            }
        });

        requestQueue.add(stringRequest);


        mRecyclerView = (RecyclerView) findViewById(R.id.rv_users_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void showData() {
        mAdapter = new UsersListCustomAdapter(this, mUsersList);
        mAdapter.setClickListener(this);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(View view, int position) {
        User item = mAdapter.getItem(position);
        Intent intent = new Intent(this, ChatActivity.class);
        intent.putExtra(SelectUserActivity.EXTRA_TARGET_USER, item);
        intent.putExtra(SelectUserActivity.EXTRA_LOGGED_USER, mLoggedUser);
        startActivity(intent);
        finish();
    }
}
