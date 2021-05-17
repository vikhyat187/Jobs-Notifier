//package Activities;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.widget.AppCompatTextView;
//import androidx.recyclerview.widget.DefaultItemAnimator;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.os.AsyncTask;
//import android.os.Bundle;
//
//import com.vikhyat.jobnotifier.R;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import Adapter.UserRecyclerAdapter;
//import SQL.DatabaseHelper;
//import modal.User;
//
//public class UsersList extends AppCompatActivity {
//
//    private AppCompatActivity activity = UsersList.this;
//    private AppCompatTextView textViewName;
//    private RecyclerView recyclerViewUsers;
//    private List<User> listUsers;
//    private UserRecyclerAdapter usersRecyclerAdapter;
//    private DatabaseHelper databaseHelper;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_users_list);
//        getSupportActionBar().setTitle("");
//        initViews();
//        initObjects();
//
//    }
//    private void initViews(){
//        textViewName = (AppCompatTextView)findViewById(R.id.textViewName);
//        recyclerViewUsers = (RecyclerView) findViewById(R.id.recyclerViewUsers);
//    }
//    private void initObjects(){
//        listUsers = new ArrayList<>();
//        usersRecyclerAdapter = new UserRecyclerAdapter(listUsers);
//
//        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
//        recyclerViewUsers.setLayoutManager(mLayoutManager);
//        recyclerViewUsers.setItemAnimator(new DefaultItemAnimator());
//        recyclerViewUsers.setHasFixedSize(true);
//        recyclerViewUsers.setAdapter(usersRecyclerAdapter);
//        databaseHelper = new DatabaseHelper(activity);
//
//        String emailFromIntent = getIntent().getStringExtra("EMAIL");
//        textViewName.setText(emailFromIntent);
//
////        getDataFromSQLite();
//    }
//////    This method is to fetch all user records from SQLite
////    private void getDataFromSQLite(){
////        //Async task is used that SQLite operation not blocks the UI thread
////            new AsyncTask<Void,Void,Void>(){
////                @Override
////                protected Void doInBackground(Void... params){
////                    listUsers.clear();
////                    listUsers.addAll(databaseHelper.getAllUser());
////
////                    return null;
////                }
////                @Override
////                protected void onPostExcecute(Void aVoid){
////                    super.onPostExecute(aVoid);
////                    usersRecyclerAdapter.notifyDataSetChanged();
////                }
////        }.execute();
////    }
//}