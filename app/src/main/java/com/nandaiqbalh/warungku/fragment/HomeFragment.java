package com.nandaiqbalh.warungku.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nandaiqbalh.warungku.MainActivity;
import com.nandaiqbalh.warungku.R;
import com.nandaiqbalh.warungku.activity.LoginActivity;
import com.nandaiqbalh.warungku.adapter.ProdukAdapter;
import com.nandaiqbalh.warungku.helper.SharedPref;
import com.nandaiqbalh.warungku.model.Product;
import com.nandaiqbalh.warungku.rest.ApiClient;
import com.nandaiqbalh.warungku.rest.LoginResponse;
import com.nandaiqbalh.warungku.rest.ProductResponse;
import com.smarteist.autoimageslider.DefaultSliderView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderLayout;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    SliderLayout sliderLayout;

    // recycler view
    RecyclerView recyclerViewTerbaru, recyclerViewTerlaris, recyclerViewOlahraga, recyclerViewElektronik;
    ArrayList<Product> dataHolder;
    SharedPref s;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        init(view);

        getProduct();

//        dataHolder = new ArrayList<>();
//        Product terbaru1 = new Product("Adidas Nemeziz 18 || Football", "Rp 1.350.000", R.drawable.produk_sepatu_adidas_nemeziz_18);
//        dataHolder.add(terbaru1);
//        Product terbaru2 = new Product("Jersey Arsenal HOME 2020-2021", "Rp 350.000", R.drawable.produk_jersey_arsenal_home_2020_2021);
//        dataHolder.add(terbaru2);
//        Product terbaru3 = new Product("Adidas ONE || Football", "Rp 1.400.000", R.drawable.produk_sepatu_adidas_one);
//        dataHolder.add(terbaru3);
//        Product terbaru4 = new Product("Nike Phantom GT Pro FG || Football", "Rp 1.200.000", R.drawable.produk_sepatu_nike_phantom_gt_pro_fg);
//        dataHolder.add(terbaru4);
//        Product terbaru5 = new Product("Macbook PRO 2020", "Rp 18.999.000", R.drawable.produk_macbook_pro_2020);
//        dataHolder.add(terbaru5);
//


//        // TODO: RECYCLER VIEW PRODUK TERLARIS
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//
//        dataHolder = new ArrayList<>();
//        Product terlaris1 = new Product("Specs ACC LightSpeed Reborn || Futsal", "Rp 599.000", R.drawable.produk_sepatu_specs_acc_lightspeedreborn);
//        dataHolder.add(terlaris1);
//        Product terlaris2 = new Product("Jersey Manchester United 3rd 2020-2021", "Rp 350.000", R.drawable.produk_jersey_mu_3rd_2020_2021);
//        dataHolder.add(terlaris2);
//        Product terlaris3 = new Product("ECO Tupperware 500 ml", "Rp 52.000", R.drawable.produk_botol_eco_tupperware);
//        dataHolder.add(terlaris3);
//        Product terlaris4 = new Product("Nike Phantom GT Pro FG", "Rp 1.200.000", R.drawable.produk_sepatu_nike_phantom_gt_pro_fg);
//        dataHolder.add(terlaris4);
//        Product terlaris5 = new Product("HP Asus Zenfone MaxPro M1 3Gb", "Rp 1.500.000", R.drawable.produk_hp_asus_zenfone_m1);
//        dataHolder.add(terlaris5);
//

//
//        recyclerView.setAdapter(new ProdukAdapter(dataHolder));
//
//        // TODO: RECYCLER VIEW PRODUK OLAHRAGA
//
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//
//        dataHolder = new ArrayList<>();
//        Product olahraga1 = new Product("Adidas Nemeziz 18 || Football", "Rp 1.350.000", R.drawable.produk_sepatu_adidas_nemeziz_18);
//        dataHolder.add(olahraga1);
//        Product olahraga2 = new Product("Jersey Arsenal HOME 2020-2021", "Rp 350.000", R.drawable.produk_jersey_arsenal_home_2020_2021);
//        dataHolder.add(olahraga2);
//        Product olahraga3 = new Product("OrtusEight Jogosala Rampage || Futsal", "Rp 449.000", R.drawable.produk_sepatu_ortuseight_jogosalarampage);
//        dataHolder.add(olahraga3);
//        Product olahraga4 = new Product("Nike Phantom GT Pro FG || Football", "Rp 1.200.000", R.drawable.produk_sepatu_nike_phantom_gt_pro_fg);
//        dataHolder.add(olahraga4);
//        Product olahraga5 = new Product("Jersey Manchester United HOME 2020-2021", "Rp 350.000", R.drawable.produk_jersey_mu_home_2020_2021);
//        dataHolder.add(olahraga5);
//

//
//        recyclerView.setAdapter(new ProdukAdapter(dataHolder));
//
//        // TODO: RECYCLER VIEW PRODUK ELEKTRONIK
//
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//
//        dataHolder = new ArrayList<>();
//        Product elektronik1 = new Product("DELL Vostro 14 | 3401", "Rp 7.350.000", R.drawable.produk_dell_vostro_14_3401);
//        dataHolder.add(elektronik1);
//        Product elektronik2 = new Product("Asus ROG STRIX III-G", "Rp 15.999.000", R.drawable.produk_asus_rog);
//        dataHolder.add(elektronik2);
//        Product elektronik3 = new Product("iPod Touch 32GB", "Rp 1.400.000", R.drawable.produk_ipod_touch_32_gb);
//        dataHolder.add(elektronik3);
//        Product elektronik4 = new Product("HP Asus Zenfone MaxPro M1 3Gb", "Rp 1.500.000", R.drawable.produk_hp_asus_zenfone_m1);
//        dataHolder.add(elektronik4);
//        Product elektronik5 = new Product("Macbook PRO 2020", "Rp 18.999.000", R.drawable.produk_macbook_pro_2020);
//        dataHolder.add(elektronik5);
//

//
//        recyclerView.setAdapter(new ProdukAdapter(dataHolder));
//
        return view;
    }

    private void displayProduct(){
        // slider image
        sliderLayout.setIndicatorAnimation(IndicatorAnimations.FILL);
        sliderLayout.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderLayout.setScrollTimeInSec(1);
        setSliderViews();

        LinearLayoutManager layoutManagerTerbaru = new LinearLayoutManager(getActivity());
        layoutManagerTerbaru.setOrientation(RecyclerView.HORIZONTAL);
        recyclerViewTerbaru.setLayoutManager(layoutManagerTerbaru);
        recyclerViewTerbaru.setAdapter(new ProdukAdapter(requireActivity(), productArrayList));

        LinearLayoutManager layoutManagerTerlaris = new LinearLayoutManager(getActivity());
        layoutManagerTerlaris.setOrientation(RecyclerView.HORIZONTAL);
        recyclerViewTerlaris.setLayoutManager(layoutManagerTerlaris);
        recyclerViewTerlaris.setAdapter(new ProdukAdapter(requireActivity(), productArrayList));

        LinearLayoutManager layoutManagerOlahraga = new LinearLayoutManager(getActivity());
        layoutManagerOlahraga.setOrientation(RecyclerView.HORIZONTAL);
        recyclerViewOlahraga.setLayoutManager(layoutManagerOlahraga);
        recyclerViewOlahraga.setAdapter(new ProdukAdapter(requireActivity(), productArrayList));

        LinearLayoutManager layoutManagerElektronik = new LinearLayoutManager(getActivity());
        layoutManagerElektronik.setOrientation(RecyclerView.HORIZONTAL);
        recyclerViewElektronik.setLayoutManager(layoutManagerElektronik);
        recyclerViewElektronik.setAdapter(new ProdukAdapter(requireActivity(), productArrayList));

    }

    private ArrayList<Product> productArrayList = new ArrayList<>();
    private void getProduct(){
        Call<ProductResponse> productResponseCall = ApiClient.getService().productAPI();
        productResponseCall.enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {

                ProductResponse respon = response.body();
                if (respon.getSuccess() == 1){
                    productArrayList = respon.getProduct();
                    displayProduct();
                }

            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {


            }

        });
    }

    private void setSliderViews() {

        for (int i = 0; i < 3; i++) {
            DefaultSliderView sliderView = new DefaultSliderView(getContext());

            switch (i) {
                case 0:
                    sliderView.setImageDrawable(R.drawable.slider1);
                    break;
                case 1:
                    sliderView.setImageDrawable(R.drawable.slider2);
                    break;
                case 2:
                    sliderView.setImageDrawable(R.drawable.slider3);
                    break;
            }
            sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP);
            sliderLayout.addSliderView(sliderView);
        }
    }
    private void init(View view){
        sliderLayout = view.findViewById(R.id.slider);

        recyclerViewTerbaru = view.findViewById(R.id.rv_produk_terbaru);
        recyclerViewTerlaris = view.findViewById(R.id.rv_produk_terlaris);
        recyclerViewOlahraga = view.findViewById(R.id.rv_produk_olahraga);
        recyclerViewElektronik = view.findViewById(R.id.rv_produk_elektronik);
    }
}