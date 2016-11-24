package com.example.acer.myzhibo.ui.Search;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.acer.myzhibo.R;
import com.example.acer.myzhibo.adapter.SearchAdapter;
import com.example.acer.myzhibo.bean.PostSearchBean;
import com.example.acer.myzhibo.bean.SearchBean;
import com.example.acer.myzhibo.utils.ToastHelper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;


public class SearchActivity extends AppCompatActivity implements SearchContract.ISearchView,SearchAdapter.SearchOnClickListener{
    private Context mContext = this;
    private SearchView searchView ;
    private ImageView iv_search;
    private EditText editText;
    private String searchstr ;
    private XRecyclerView recyclerView;
    private SearchAdapter adapter ;
    private int page =0;
    private List<SearchBean.DataBean.ItemsBean> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        list = new ArrayList<>();
        initView();
        initAdapter();

    }

    private void initAdapter() {
        adapter= new SearchAdapter(mContext,list,this);
        recyclerView.setLayoutManager(new GridLayoutManager(mContext,2, LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(adapter);
    }

    private void initView() {
        recyclerView = (XRecyclerView) findViewById(R.id.recycler_search);
        searchView = (SearchView) findViewById(R.id.searchView);
        iv_search = (ImageView) findViewById(R.id.iv_search_activity);
        searchView.setIconifiedByDefault(false);
        //searchView.setSubmitButtonEnabled(true);
        //clearfocus
        searchView.onActionViewExpanded();
        /*
        这里是重点,SearchView并没有提供样式的修改方法,所以只能
        1.用获取到的实例调用getContext()方法,返回当前view的上下文
        2.调用getResources()方法,获取该view的资源实例(Return a Resources instance)
        3.调用getIdentifier()方法,获取相同名字的ID,(Return a resource identifier for the given resource name)
        4.通过findViewById()获取该ID的实例,然后就可以做相应的操作了
        */
        editText = (EditText) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        iv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 searchstr = editText.getEditableText()+"";
                ToastHelper.showToast(mContext,searchstr);
                JsonObject object = getJson(searchstr,page);
                doSearch(object);
            }
        });
    }

    public JsonObject getJson(String searchstr,int page) {
        //os =1
        //categoryid = 0  key = xxx  page =0/1  size =10
        //v = 2.2.4  ver =4

        PostSearchBean bean = new PostSearchBean();
        bean.setOs(1);
        bean.setV(2.24f);
        bean.setVer(4f);
        PostSearchBean.p p =bean.new p(page,searchstr);
        bean.setp(p);

        Gson gson = new Gson();
        String json1 = gson.toJson(bean);

        JsonObject object = new JsonParser().parse(json1).getAsJsonObject();
        //JSONObject json = JSONObject.fromObject(obj);//将java对象转换为json对象
       //String str = json.toString();//将json对象转换为字符串
       Log.e("TAG", "getJson: "+object);
        return object;
    }

    public void doSearch(JsonObject object) {
        SearchPresenter presenter = new SearchPresenter(this);
        presenter.getSearchReponse(object);

    }

    @Override
    public void getSearchBean(SearchBean bean) {
         int size = bean.getData().getItems().size();
         list.clear();
         list.addAll(bean.getData().getItems());
        adapter.notifyDataSetChanged();

    }

    @Override
    public void fail() {
        ToastHelper.showToast(mContext,"没有更多了o(╯□╰)o");
    }

    //点击监听
    @Override
    public void onClick(int position) {
         ToastHelper.showToast(mContext,"position"+position);
    }
}
