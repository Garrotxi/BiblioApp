package ioc.android.biblioapp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import android.widget.TextView;

public class LoginTest {
    //@Test
    //public void addition_isCorrect() {
    // assertEquals(4, 2 + 2);
    // }
   // private MainActivity prueba;

    @Before
    public void setUp() {
       // prueba = new MainActivity();
    }



    @Test
    public void login() {

  /*      try {
            String user= prueba.getString(R.id.user_edit_text);
            prueba.
            prueba.setContentView(R.id.user_edit_text,"Admin");
            assertEquals("Admin", user);


*//*            //TextView usuario= R.id.user_edit_text;
            //TextView usuario= TextView.findViewById(R.id.user_edit_text);
            String user= ((Integer)R.id.user_edit_text).toString();
            //String e = Integer.toString(R.string.nombre_usuario);
            assertEquals("2131231172", user);
            //assertEquals("Admin", user);*//*
        } catch (Exception e) {
            System.out.println("Mal, no eres Admin!");
        }*/
    }
}

/*    @After
    public void tearDown() {
        try {
            //TextView usuario= findViewById(R.id.user_edit_text);
            assertEquals("Admin", Integer.toString(R.id.user_edit_text));
        } catch (Exception e) {
            System.out.println("Mal, no eres Admin!");
        }
    }*/



