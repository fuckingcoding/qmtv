package com.example.acer.myzhibo.ui.Search;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.acer.myzhibo.R;
import com.example.acer.myzhibo.adapter.SearchAdapter;
import com.example.acer.myzhibo.bean.PostSearchBean;
import com.example.acer.myzhibo.bean.SearchBean;
import com.example.acer.myzhibo.utils.ToastHelper;
import com.example.acer.myzhibo.utils.UIManager;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;


public class SearchActivity extends AppCompatActivity implements SearchContract.ISearchView,SearchAdapter.SearchOnClickListener{
    private Context mContext = this;
    private SearchView searchView ;
    private ImageView iv_search,iv_back;
    private SearchView.SearchAutoComplete editText;
    private String searchstr ;
    private XRecyclerView recyclerView;
    private SearchAdapter adapter ;
    ArrayAdapter<String> Arrayadapter;
    private int page =0;
    private List<SearchBean.DataBean.ItemsBean> list;

    private static final String[] city=new String[]
            {"lol","shanghai","乌鲁木齐市", "北京市", "郑州市", "上海市","天津市", "深圳市", "广州市", "南京市","大连市","大同市"};
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
        iv_back = (ImageView) findViewById(R.id.iv_back_search);
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
        Arrayadapter=new ArrayAdapter<String>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                city);
        editText = (SearchView.SearchAutoComplete) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        editText.setAdapter(Arrayadapter);
        editText.setThreshold(2);

        editText.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                editText.setText(city[i]);
            }
        });
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
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
        if(size ==0){
            ToastHelper.showToast(mContext,"找不到相关信息");
        }
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

        SearchBean.DataBean.ItemsBean bean = list.get(position);
       if(bean.isPlay_status()){

           UIManager.startPlayActivity(mContext,bean.getAvatar(),bean.getTitle(),bean.getNick(),bean.getView(),bean.getUid()+"");
       }else{
           ToastHelper.showToast(mContext,"主播不在哦o(╯□╰)o");
       }


    }
}
