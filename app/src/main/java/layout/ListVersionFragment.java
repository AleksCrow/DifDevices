package layout;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alexvoronkov.difdevices.AndroidVersion;
import com.alexvoronkov.difdevices.DataAdapter;
import com.alexvoronkov.difdevices.DetailsFragment;
import com.alexvoronkov.difdevices.R;
import com.alexvoronkov.difdevices.RequestInterface;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListVersionFragment extends Fragment implements Serializable{

    private ArrayList<AndroidVersion> data;
    private DataAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final RecyclerView recyclerView = (RecyclerView)inflater.inflate(R.layout.fragment_list_version, container, false);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        //ретрофит
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.learn2crack.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterface request = retrofit.create(RequestInterface.class);
        Call<AndroidVersion> call = request.getJSON();
        call.enqueue(new Callback<AndroidVersion>() {
            @Override
            public void onResponse(Call<AndroidVersion> call, Response<AndroidVersion> response) {

                //получение данных с сети и парсинг на массив массивов, создание адаптера
                AndroidVersion androidVersion = response.body();
                data = new ArrayList<>(Arrays.asList(androidVersion.getAndroid()));
                adapter = new DataAdapter(data);

                //реализация Listener для обработки нажатий и выбора нужного варианта
                adapter.setListener(new DataAdapter.Listener() {
                    @Override
                    public void onClick(int position) {
                        AndroidVersion obj = data.get(position);
                        Intent intent = new Intent(getActivity(), DetailsFragment.class);
                        intent.putExtra("obj", obj);
                        getActivity().startActivity(intent);
                    }
                });

                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<AndroidVersion> call, Throwable t) {
                Log.d("Error",t.getMessage());
            }
        });
        return recyclerView;
    }
}
