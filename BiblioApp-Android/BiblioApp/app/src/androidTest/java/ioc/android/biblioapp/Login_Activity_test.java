/**
 * author Saul Lopez Diez
 */
package ioc.android.biblioapp;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intending;
import static androidx.test.espresso.intent.matcher.ComponentNameMatchers.hasShortClassName;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ioc.android.biblioapp.View.Login_Activity;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class Login_Activity_test {
    String usuario = null;
    String contraseña = null;



    @Rule
    public IntentsTestRule<Login_Activity> intentsRule =
            new IntentsTestRule<>(Login_Activity.class);

    @Test
    public void loginAdmin() {

        usuario ="admin";
        contraseña = "1234";
        // Types a message into a EditText element.
        onView(withId(R.id.user_edit_text))
                .perform(typeText(usuario), closeSoftKeyboard());
        onView(withId(R.id.password_edit_text))
                .perform(typeText(contraseña), closeSoftKeyboard());


        onView(withId(R.id.next_button)).perform(click());

        intending(hasComponent(hasShortClassName(".Pantalla_Inicio_Administrador")));

    }

    @Test
    public void loginUsuario() {

        usuario ="2";
        contraseña = "2";
        // Types a message into a EditText element.
        onView(withId(R.id.user_edit_text))
                .perform(typeText(usuario), closeSoftKeyboard());
        onView(withId(R.id.password_edit_text))
                .perform(typeText(contraseña), closeSoftKeyboard());


        onView(withId(R.id.next_button)).perform(click());

        intending(hasComponent(hasShortClassName(".Pantalla_Inicio_Usuario")));

    }

    /*

     */
    @Test
    public void loginInexistente() {

        usuario ="300";
        contraseña = "300";
        // Types a message into a EditText element.
        onView(withId(R.id.user_edit_text))
                .perform(typeText(usuario), closeSoftKeyboard());
        onView(withId(R.id.password_edit_text))
                .perform(typeText(contraseña), closeSoftKeyboard());

        onView(withId(R.id.login_incorrecto)).check(matches(withText(R.string.Login_incorrecto)));
    }
}
