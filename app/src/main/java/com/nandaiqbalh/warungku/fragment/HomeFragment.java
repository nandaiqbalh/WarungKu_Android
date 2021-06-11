package com.nandaiqbalh.warungku.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nandaiqbalh.warungku.R;
import com.nandaiqbalh.warungku.adapter.ProdukAdapter;
import com.nandaiqbalh.warungku.model.Produk;
import com.smarteist.autoimageslider.DefaultSliderView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderLayout;

import java.util.ArrayList;
import java.util.List;

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
    RecyclerView recyclerView;
    ArrayList<Produk> dataHolder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // slider image
        sliderLayout = view.findViewById(R.id.slider);
        sliderLayout.setIndicatorAnimation(IndicatorAnimations.FILL);
        sliderLayout.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderLayout.setScrollTimeInSec(1);
        setSliderViews();

        // TODO: RECYCLER VIEW PRODUK TERBARU
        recyclerView = view.findViewById(R.id.rv_produk_terbaru);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        dataHolder = new ArrayList<>();
        Produk terbaru1 = new Produk("Adidas Nemeziz 18 || Football", "Rp 1.350.000", R.drawable.produk_sepatu_adidas_nemeziz_18);
        dataHolder.add(terbaru1);
        Produk terbaru2 = new Produk("Jersey Arsenal HOME 2020-2021", "Rp 350.000", R.drawable.produk_jersey_arsenal_home_2020_2021);
        dataHolder.add(terbaru2);
        Produk terbaru3 = new Produk("Adidas ONE || Football", "Rp 1.400.000", R.drawable.produk_sepatu_adidas_one);
        dataHolder.add(terbaru3);
        Produk terbaru4 = new Produk("Nike Phantom GT Pro FG || Football", "Rp 1.200.000", R.drawable.produk_sepatu_nike_phantom_gt_pro_fg);
        dataHolder.add(terbaru4);
        Produk terbaru5 = new Produk("Macbook PRO 2020", "Rp 18.999.000", R.drawable.produk_macbook_pro_2020);
        dataHolder.add(terbaru5);

        LinearLayoutManager layoutManagerTerbaru = new LinearLayoutManager(getActivity());
        layoutManagerTerbaru.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManagerTerbaru);

        recyclerView.setAdapter(new ProdukAdapter(dataHolder));

        // TODO: RECYCLER VIEW PRODUK TERLARIS
        recyclerView = view.findViewById(R.id.rv_produk_terlaris);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        dataHolder = new ArrayList<>();
        Produk terlaris1 = new Produk("Specs ACC LightSpeed Reborn || Futsal", "Rp 599.000", R.drawable.produk_sepatu_specs_acc_lightspeedreborn);
        dataHolder.add(terlaris1);
        Produk terlaris2 = new Produk("Jersey Manchester United 3rd 2020-2021", "Rp 350.000", R.drawable.produk_jersey_mu_3rd_2020_2021);
        dataHolder.add(terlaris2);
        Produk terlaris3 = new Produk("ECO Tupperware 500 ml", "Rp 52.000", R.drawable.produk_botol_eco_tupperware);
        dataHolder.add(terlaris3);
        Produk terlaris4 = new Produk("Nike Phantom GT Pro FG", "Rp 1.200.000", R.drawable.produk_sepatu_nike_phantom_gt_pro_fg);
        dataHolder.add(terlaris4);
        Produk terlaris5 = new Produk("HP Asus Zenfone MaxPro M1 3Gb", "Rp 1.500.000", R.drawable.produk_hp_asus_zenfone_m1);
        dataHolder.add(terlaris5);

        LinearLayoutManager layoutManagerTerlaris = new LinearLayoutManager(getActivity());
        layoutManagerTerlaris.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManagerTerlaris);

        recyclerView.setAdapter(new ProdukAdapter(dataHolder));

        // TODO: RECYCLER VIEW PRODUK OLAHRAGA
        recyclerView = view.findViewById(R.id.rv_produk_olahraga);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        dataHolder = new ArrayList<>();
        Produk olahraga1 = new Produk("Adidas Nemeziz 18 || Football", "Rp 1.350.000", R.drawable.produk_sepatu_adidas_nemeziz_18);
        dataHolder.add(olahraga1);
        Produk olahraga2 = new Produk("Jersey Arsenal HOME 2020-2021", "Rp 350.000", R.drawable.produk_jersey_arsenal_home_2020_2021);
        dataHolder.add(olahraga2);
        Produk olahraga3 = new Produk("OrtusEight Jogosala Rampage || Futsal", "Rp 449.000", R.drawable.produk_sepatu_ortuseight_jogosalarampage);
        dataHolder.add(olahraga3);
        Produk olahraga4 = new Produk("Nike Phantom GT Pro FG || Football", "Rp 1.200.000", R.drawable.produk_sepatu_nike_phantom_gt_pro_fg);
        dataHolder.add(olahraga4);
        Produk olahraga5 = new Produk("Jersey Manchester United HOME 2020-2021", "Rp 350.000", R.drawable.produk_jersey_mu_home_2020_2021);
        dataHolder.add(olahraga5);

        LinearLayoutManager layoutManagerOlahraga = new LinearLayoutManager(getActivity());
        layoutManagerOlahraga.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManagerOlahraga);

        recyclerView.setAdapter(new ProdukAdapter(dataHolder));

        // TODO: RECYCLER VIEW PRODUK ELEKTRONIK
        recyclerView = view.findViewById(R.id.rv_produk_elektronik);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        dataHolder = new ArrayList<>();
        Produk elektronik1 = new Produk("DELL Vostro 14 | 3401", "Rp 7.350.000", R.drawable.produk_dell_vostro_14_3401);
        dataHolder.add(elektronik1);
        Produk elektronik2 = new Produk("Asus ROG STRIX III-G", "Rp 15.999.000", R.drawable.produk_asus_rog);
        dataHolder.add(elektronik2);
        Produk elektronik3 = new Produk("iPod Touch 32GB", "Rp 1.400.000", R.drawable.produk_ipod_touch_32_gb);
        dataHolder.add(elektronik3);
        Produk elektronik4 = new Produk("HP Asus Zenfone MaxPro M1 3Gb", "Rp 1.500.000", R.drawable.produk_hp_asus_zenfone_m1);
        dataHolder.add(elektronik4);
        Produk elektronik5 = new Produk("Macbook PRO 2020", "Rp 18.999.000", R.drawable.produk_macbook_pro_2020);
        dataHolder.add(elektronik5);

        LinearLayoutManager layoutManagerElektronik = new LinearLayoutManager(getActivity());
        layoutManagerElektronik.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManagerElektronik);

        recyclerView.setAdapter(new ProdukAdapter(dataHolder));

        return view;
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
}