/**
 * @Autor Saúl López Díez
 * Clase LoginViewModel que gestiona las llamadas de la vista y trata con la parte del Modelo para conseguir la información
 * del login
 */
package ioc.android.biblioapp.ViewModel;

import android.content.Context;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import ioc.android.biblioapp.Model.Clases.Login;
import ioc.android.biblioapp.Model.Clases.Usuari;
import ioc.android.biblioapp.Model.Repositorio.BiblioAppRepo;

public class LoginViewModel extends ViewModel {
    private BiblioAppRepo biblioAppRepo;
    private MutableLiveData<Usuari> mutableLiveData;
    private Usuari usuariActiu;
    private LoginViewModel loginViewModel;

    public LoginViewModel getLoginViewModel() {
        return loginViewModel;
    }

    public void setLoginViewModel(LoginViewModel loginViewModel) {
        this.loginViewModel = loginViewModel;
    }

    public Usuari getUsuariActiu() {
        return usuariActiu;
    }

    /**
     * Constructor de LoginViewModel
     */
    public LoginViewModel(){
        biblioAppRepo = new BiblioAppRepo();
    }



    /**
     *
     * @param login datos de login
     * @param context contexto de la actividad
     * @param loginViewModel instancia loginViewModel
     * @return Lista con valores que necesitamos para el Login, rol y token
     */
    public MutableLiveData<List<String>> loginViewModel (Login login, Context context, LoginViewModel loginViewModel){
        MutableLiveData <List<String>>  mensaje = new MutableLiveData<>();
        loginViewModel.ConseguirLoginRepositorio(login).observe((LifecycleOwner) context, new Observer<Usuari>() {//Generamos un observer a la espera de la consulta con el repositorio
            @Override
            public void onChanged(Usuari usuari) {//con la respuesta
                if (usuari != null) {//si no es null
                    List<String> valores = new ArrayList<>();
                    Collection c= usuari.getAuthorities();
                    Iterator it=c.iterator();

                    String [] rol= new String[2];
                    rol[0]= it.next().toString();//conseguimos el rol
                    valores.add(rol[0]);
                    if(it.hasNext()){
                        rol[1]= it.next().toString();
                        valores.add(rol[1]);
                    }else{

                    }
                    valores.add(usuari.getToken());
                    mensaje.setValue(valores);
                    usuariActiu=usuari;

                }else{
                    mensaje.setValue(null);
                }
            }
        });
        return mensaje;
    }


    /**
     *
     * @param login datos de login
     * @return datos de la consulta al repositorio, una instancia de Usuari
     */
        public LiveData<Usuari> ConseguirLoginRepositorio(Login login) {

        if (mutableLiveData==null) {
            mutableLiveData = biblioAppRepo.pideLogin(login);
        }
        return mutableLiveData;
    }

}
