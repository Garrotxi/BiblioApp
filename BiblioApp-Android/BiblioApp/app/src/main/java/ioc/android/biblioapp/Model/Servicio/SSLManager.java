package ioc.android.biblioapp.Model.Servicio;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

import ioc.android.biblioapp.R;

/**
 * Create by wz
 * Certificado de lectura SSLSocketFactory personalizado
 */
public class SSLManager {

    private final static String LOCAL_PRI_KEY = "local.bks";
    private final static String TRUSTSTORE_PUB_KEY = "truststore.bks";
    private final static String LOCAL_BKS_PASSWORD = "123456";
    private final static String TRUSTSTORE_BKS_PASSWORD = "123456";
    private final static String KEYSTORE_TYPE = "BKS";
    private final static String PROTOCOL_TYPE = "TLS";
    private final static String CERTIFICATE_FORMAT = "X509";


    public static SSLSocketFactory getSSLCertifcation(Context context) {
        SSLSocketFactory sslSocketFactory = null;
        try {
            // El certificado de cliente que el servidor necesita verificar es en realidad el almacén de claves del cliente
            KeyStore keyStore = KeyStore.getInstance(KEYSTORE_TYPE); // El certificado del lado del servidor en el que confía el cliente
            KeyStore trustStore = KeyStore.getInstance(KEYSTORE_TYPE); // Leer certificado
            InputStream ksIn = context.getResources().openRawResource(R.raw.local);
            InputStream tsIn = context.getResources().openRawResource(R.raw.truststore); // Cargue el certificado
            //InputStream ksIn = context.getAssets().open(LOCAL_PRI_KEY);
            //InputStream tsIn = context.getAssets().open(TRUSTSTORE_PUB_KEY); // Cargue el certificado
            keyStore.load(ksIn, LOCAL_BKS_PASSWORD.toCharArray());
            trustStore.load(tsIn, TRUSTSTORE_BKS_PASSWORD.toCharArray());
            ksIn.close();
            tsIn.close();
            // Inicializar SSLContext
            SSLContext sslContext = SSLContext.getInstance(PROTOCOL_TYPE);
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(CERTIFICATE_FORMAT);
            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(CERTIFICATE_FORMAT);
            trustManagerFactory.init(trustStore);
            keyManagerFactory.init(keyStore, LOCAL_BKS_PASSWORD.toCharArray());
            sslContext.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), null);
            sslSocketFactory = sslContext.getSocketFactory();
        } catch (KeyStoreException | IOException | CertificateException | NoSuchAlgorithmException | UnrecoverableKeyException | KeyManagementException e) {
            Log.d("sslManager", "error: "+e.getMessage()+""+e.getLocalizedMessage()+""+e.toString());
        }
        return sslSocketFactory;
    }
}


