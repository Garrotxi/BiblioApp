package ioc.android.biblioapp.View.Administrador;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import ioc.android.biblioapp.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Administrador_Detalle_LibroFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Administrador_Detalle_LibroFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Administrador_Detalle_LibroFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Administrador_Detalle_LibroFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Administrador_Detalle_LibroFragment newInstance(String param1, String param2) {
        Administrador_Detalle_LibroFragment fragment = new Administrador_Detalle_LibroFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_administrador__detalle__usuario, container, false);
    }
}