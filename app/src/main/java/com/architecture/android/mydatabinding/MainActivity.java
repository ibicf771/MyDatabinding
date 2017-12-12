package com.architecture.android.mydatabinding;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.architecture.android.mydatabinding.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private User user;

    private UserAdapter mAdapter;

     ActivityMainBinding binding;


    private List<User> mLists = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        user = new User("okname");
        user.setName("two two");
//        binding.setUser(user);
        binding.setVariable(BR.user,user);
        binding.setMainactivity(MainActivity.this);
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "button press", Toast.LENGTH_SHORT).show();
                binding.textview.setText("ten ten");
                Log.d("MainActivity", "name: " + user.getName());
            }
        });
        setRecyclerView();
    }


    private void setRecyclerView(){
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);

        setLists();
        mAdapter = new UserAdapter(mLists);
        // 设置布局管理器
        binding.usersList.setLayoutManager(mLayoutManager);
        // 设置adapter
        binding.usersList.setAdapter(mAdapter);
    }

    private void setLists(){
        for(int i = 0;i<10;i++){
            mLists.add(new User("item" + i));
        }
    }

    public void onTextViewClick(View view){
        Log.d("MainActivity", "textview press");
        Toast.makeText(this, "fine", Toast.LENGTH_SHORT).show();
        user.setName("four");
    }


    private static class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder>{

        private List<User> mUsers;

        public UserAdapter(List<User> users){
            mUsers = users;
        }

        public void refreshList(List<User> users){
            mUsers = users;
            notifyDataSetChanged();
        }

        @Override
        public UserAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            ViewDataBinding binding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.getContext()),
                    R.layout.user_item,
                    parent,
                    false);
            ViewHolder holder = new ViewHolder(binding.getRoot());
            return holder;
        }

        @Override
        public void onBindViewHolder(UserAdapter.ViewHolder holder, final int position) {
            User user = mUsers.get(position);
            holder.getBinding().setVariable(BR.user, user);
            holder.getBinding().executePendingBindings();
        }

        @Override
        public int getItemCount() {
            return mUsers == null ? 0 : mUsers.size();
        }

        public static class ViewHolder extends RecyclerView.ViewHolder {
            private ViewDataBinding mBinding;
            public ViewHolder(View itemView) {
                super(itemView);
                mBinding = DataBindingUtil.bind(itemView);
            }

            public void setBinding(ViewDataBinding binding){
                mBinding = binding;
            }

            public ViewDataBinding getBinding(){
                return mBinding;
            }
        }
    }

}
