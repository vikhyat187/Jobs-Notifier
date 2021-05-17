//package Adapter;
//
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import androidx.appcompat.widget.AppCompatTextView;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.vikhyat.jobnotifier.R;
//
//import java.util.List;
//
//import modal.User;
//
//public class UserRecyclerAdapter extends RecyclerView.Adapter<UsersRecyclerAdapter.UserViewHolder> {
//    private List<User>  listUsers;
//
//    public UserRecyclerAdapter(List<User> listUsers) {
//        this.listUsers = listUsers;
//    }
//    @Override
//    public UserViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
//        View itemView = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.item_user_recycler,parent,false);
//        return new UserViewHolder(itemView);
//    }
//
//    @Override
//    public void onBindViewHolder(UserViewHolder holder,int position){
//        holder.textViewName.setText(listUsers.get(position).getName());
//        holder.textViewEmail.setText(listUsers.get(position).getEmail());
//        holder.textViewPassword.setText(listUsers.get(position).getPassword());
//    }
//
//    @Override
//    public int getItemCount(){
//        Log.v(UsersRecyclerAdapter.class.getSimpleName(),""+listUsers.size());
//        return listUsers.size();
//    }
//    public class UserViewHolder extends RecyclerView.ViewHolder{
//        public AppCompatTextView textViewName;
//        public AppCompatTextView textViewEmail;
//        public AppCompatTextView textViewPassword;
//
//        public UserViewHolder(View view){
//            super(view);
//            textViewName = view.findViewById(R.id.textViewName);
//            textViewEmail = view.findViewById(R.id.textViewEmail);
//            textViewPassword = view.findViewById(R.id.textViewPassword);
//        }
//
//    }
//}
//
