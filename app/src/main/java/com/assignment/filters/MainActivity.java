package com.assignment.filters;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.assignment.filters.model.BaseResponse;
import com.assignment.filters.model.Category;
import com.assignment.filters.model.Filter;
import com.assignment.filters.model.ExcludeList;
import com.assignment.filters.rest.ApiClient;
import com.assignment.filters.rest.ApiClientInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity  implements RadioGroup.OnCheckedChangeListener {
    private RadioGroup category;
    private RadioButton typebtn;
    private TextView tvcategoryName;

    private List<Category> categories;
    private LinearLayout llcat;
    private List<Filter> filters;
    private List<List<ExcludeList>> excludeLists;
    private List<ExcludeList> subExcludeList;

    private ProgressDialog progressDialog;

    private ApiClientInterface apiClientInterface;

    private static final int categoryConst = 300;

    private static final String TAG = MainActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getViewById();
        getData();
    }
/*
find id of the layout and intialize API client instance
 */
    private void getViewById() {
        apiClientInterface = ApiClient.getApiClient().create(ApiClientInterface.class);
        llcat = (LinearLayout) findViewById(R.id.llcat);
    }

    /*
        retrieve data from API
     */
    private void getData()
    {

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait....");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

        apiClientInterface.getData().enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                progressDialog.dismiss();

                try
                {
                    if (response.isSuccessful()) {

                        categories = response.body().getResult().getCategories();
                        excludeLists = response.body().getResult().getExcludeList();

                        bindData();

                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),getString(R.string.error_msg),Toast.LENGTH_SHORT).show();
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                progressDialog.dismiss();
                Log.e(TAG, t.toString());
                Toast.makeText(getApplicationContext(),getString(R.string.error_msg),Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*
    create radio group and radio button and bind data in the respective radio button
     */
    private void bindData() {

        for (int categIndex = 0; categIndex < categories.size(); categIndex++) {
            tvcategoryName = new TextView(this);

            tvcategoryName.setId(categories.get(categIndex).getCategoryId());
            tvcategoryName.setText(categories.get(categIndex).getName());
            tvcategoryName.setTextSize(16);
            tvcategoryName.setTextColor(getResources().getColor(R.color.text));
            filters = categories.get(categIndex).getFilters();

            llcat.addView(tvcategoryName);
            category = new RadioGroup(this);
            category.setId(categories.get(categIndex).getCategoryId() + categoryConst);
            category.setPadding(0,0,0,20);


            for (int filterInd = 0; filterInd < filters.size(); filterInd++) {
                typebtn = new RadioButton(this);
                typebtn.setId(filters.get(filterInd).getId());
                typebtn.setText(filters.get(filterInd).getName());
                if (filters.get(filterInd).getDefault() == 1)
                {
                    typebtn.setChecked(true);
                }

                category.addView(typebtn);

            }
            category.setOnCheckedChangeListener(this);
            llcat.addView(category);
        }

    }

    /*
    checking the condition to disable button from the exclude data retrieved from API
     */

    private void getExcludedData() {
        for (int parentIndex = 0; parentIndex < excludeLists.size(); parentIndex++) {
            subExcludeList = excludeLists.get(parentIndex);
            for(int childIndex=0; childIndex<subExcludeList.size(); childIndex++)
            {
               if(childIndex==(subExcludeList.size()-1))
                   break;
                int catId =  subExcludeList.get(childIndex).getCategoryId();
                int id = subExcludeList.get(childIndex).getFilterId();

                int catId1 = subExcludeList.get(childIndex+1).getCategoryId();
                int id1 = subExcludeList.get(childIndex+1).getFilterId();

                RadioGroup group = llcat.findViewById(catId + categoryConst);
                RadioButton button = llcat.findViewById(id1);
                RadioGroup group1= llcat.findViewById(catId1 + categoryConst);
                RadioButton button2 = llcat.findViewById(id);
                if (group.getCheckedRadioButtonId() == id) {
                    button.setEnabled(false);
                } else {
                    button.setEnabled(true);
                }
                if (group1.getCheckedRadioButtonId() == id1) {
                    button2.setEnabled(false);
                } else {
                    button2.setEnabled(true);
                }
            }
        }
    }
/*
this is called when change listener is called on the radio button;
exclude data function to disable button, function is called here - after selecting the radio button
 */
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        getExcludedData();
    }
}
