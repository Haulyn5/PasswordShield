package com.zjc.passwordsheild_keystore_demo;

import android.os.Build;
import android.os.Bundle;
import android.security.KeyPairGeneratorSpec;
import android.security.keystore.KeyProperties;
import android.util.Base64;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Calendar;
import java.util.Enumeration;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.security.auth.x500.X500Principal;

import static com.zjc.passwordsheild_keystore_demo.util.UtilLog.d;


public class MainActivity extends AppCompatActivity {
    private static final String ANDROIDKEYSTORE = "AndroidKeyStore";
    String TAG = "MainActivity";
    private TextView textShow;
    private Button encrypt;
    private Button decrypt;
    private EditText dataText;
    private Encryptor encryptor;
    private Decryptor decryptor;
    private KeyStore mStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String alisa = "PasswordSheild_zjc";
        String zjc1 = "qwewqeqweqweqw`12121212e";
        String zjc2 = "qwewqeqweqwewqe123213wq";
        String zjc3 = "as3453ndjadjaskdjkad";
        String zjc4 = "asdnsakjnd657sakjdnsajndkjasn";
        String zjc1_ciper = EncryUtils.getInstance().encryptString(zjc1, alisa);
        String zjc2_ciper = EncryUtils.getInstance().encryptString(zjc2, alisa);
        String zjc3_ciper = EncryUtils.getInstance().encryptString(zjc3, alisa);
        String zjc4_ciper = EncryUtils.getInstance().encryptString(zjc4, alisa);

        d(zjc1 + "enc-->" + zjc1_ciper);
        d(zjc2 + "enc-->" + zjc2_ciper);
        d(zjc3 + "enc-->" + zjc3_ciper);
        d(zjc4 + "enc-->" + zjc4_ciper);

        String zjc1_plainText = EncryUtils.getInstance().decryptString(zjc1_ciper, alisa);
        String zjc2_plainText = EncryUtils.getInstance().decryptString(zjc2_ciper, alisa);
        String zjc3_plainText = EncryUtils.getInstance().decryptString(zjc3_ciper, alisa);
        String zjc4_plainText = EncryUtils.getInstance().decryptString(zjc4_ciper, alisa);

        d(zjc1_ciper + "enc-->" + zjc1);
        d(zjc2_ciper + "enc-->" + zjc2);
        d(zjc3_ciper + "enc-->" + zjc3);
        d(zjc4_ciper + "enc-->" + zjc4);


//        test();
//        testKeyStore();
//        dataText = (EditText) findViewById(R.id.data);
//        encrypt = (Button) findViewById(R.id.encrypt);
//        decrypt = (Button) findViewById(R.id.decrypt);
//        textShow = (TextView) findViewById(R.id.text);
//
//        encryptor = new Encryptor();
//
//        try {
//            decryptor = new Decryptor();
//        } catch (CertificateException | NoSuchAlgorithmException | KeyStoreException |
//                IOException e) {
//            e.printStackTrace();
//        }
//
//        encrypt.setOnClickListener(new View.OnClickListener() {
//            @RequiresApi(api = Build.VERSION_CODES.M)
//            @Override
//            public void onClick(View v) {
//                encryptText();
//            }
//        });
//
//        decrypt.setOnClickListener(new View.OnClickListener() {
//            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
//            @Override
//            public void onClick(View v) {
//                decryptText();
//            }
//        });
    }

//    private void decryptText() {
//        try {
//            textShow.setText(decryptor
//                    .decryptData(encryptor.getEncryption(), encryptor.getIv()));
//        } catch (UnrecoverableEntryException | NoSuchAlgorithmException |
//                KeyStoreException | NoSuchPaddingException |
//                IOException | InvalidKeyException e) {
//            Log.e(TAG, "decryptData() called with: " + e.getMessage(), e);
//        } catch (IllegalBlockSizeException | BadPaddingException | InvalidAlgorithmParameterException e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * 输入明文字符串
     * 返回密文字符串
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    private String encryptText(String ciperText) {

        try {
            final byte[] encryptedText = encryptor
                    .encryptText(ciperText);
            String plainText = Base64.encodeToString(encryptedText, Base64.DEFAULT);
            d(ciperText + "--->" + plainText);
            return ciperText;
        } catch (NoSuchAlgorithmException | NoSuchProviderException |
                IOException | NoSuchPaddingException | InvalidKeyException e) {
            Log.e(TAG, "onClick() called with: " + e.getMessage(), e);
        } catch (InvalidAlgorithmParameterException |
                IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void test() {


    }

    public void testKeyStore() {


        /*
         * Load the Android KeyStore instance using the the
         * "AndroidKeyStore" provider to list out what entries are
         * currently stored.
         */

        String BKS = KeyStore.getDefaultType();//return String ("BKS")

        /**枚举alias*/
        try {
            KeyStore ks = KeyStore.getInstance("AndroidKeyStore");
            ks.load(null);
            Enumeration<String> aliases = ks.aliases();
            while (aliases.hasMoreElements()) {
                String value = (String) aliases.nextElement();//调用nextElement方法获得元素
                Log.d("debug", value);
            }

        } catch (Exception e) {
            e.printStackTrace();

        }


    }

    public void testAnyStringKey() {
        try {
            KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
            keyStore.load(null);

            String alias = "key3";

            int nBefore = keyStore.size();

            // Create the keys if necessary
            if (!keyStore.containsAlias(alias)) {

                Calendar notBefore = Calendar.getInstance();
                Calendar notAfter = Calendar.getInstance();
                notAfter.add(Calendar.YEAR, 1);
                KeyPairGeneratorSpec spec = new KeyPairGeneratorSpec.Builder(this)
                        .setAlias(alias)
                        .setKeyType(KeyProperties.KEY_ALGORITHM_RSA)
                        .setKeySize(2048)
                        .setSubject(new X500Principal("CN=test"))
                        .setSerialNumber(BigInteger.ONE)
                        .setStartDate(notBefore.getTime())
                        .setEndDate(notAfter.getTime())
                        .build();
                KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA", "AndroidKeyStore");
                generator.initialize(spec);

                KeyPair keyPair = generator.generateKeyPair();
            }
            int nAfter = keyStore.size();
            Log.v(TAG, "Before = " + nBefore + " After = " + nAfter);

            // Retrieve the keys
            KeyStore.PrivateKeyEntry privateKeyEntry = (KeyStore.PrivateKeyEntry) keyStore.getEntry(alias, null);
            RSAPrivateKey privateKey = (RSAPrivateKey) privateKeyEntry.getPrivateKey();
            RSAPublicKey publicKey = (RSAPublicKey) privateKeyEntry.getCertificate().getPublicKey();

            Log.v(TAG, "private key = " + privateKey.toString());
            Log.v(TAG, "public key = " + publicKey.toString());

            // Encrypt the text
            String plainText = "This text is supposed to be a secret!";
            String dataDirectory = getApplicationInfo().dataDir;
            String filesDirectory = getFilesDir().getAbsolutePath();
            String encryptedDataFilePath = filesDirectory + File.separator + "keep_yer_secrets_here";

            Log.v(TAG, "plainText = " + plainText);
            Log.v(TAG, "dataDirectory = " + dataDirectory);
            Log.v(TAG, "filesDirectory = " + filesDirectory);
            Log.v(TAG, "encryptedDataFilePath = " + encryptedDataFilePath);

            Cipher inCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding", "AndroidOpenSSL");
            inCipher.init(Cipher.ENCRYPT_MODE, publicKey);

            Cipher outCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding", "AndroidOpenSSL");
            outCipher.init(Cipher.DECRYPT_MODE, privateKey);

            CipherOutputStream cipherOutputStream =
                    new CipherOutputStream(
                            new FileOutputStream(encryptedDataFilePath), inCipher);
            cipherOutputStream.write(plainText.getBytes("UTF-8"));
            cipherOutputStream.close();

            CipherInputStream cipherInputStream =
                    new CipherInputStream(new FileInputStream(encryptedDataFilePath),
                            outCipher);
            byte[] roundTrippedBytes = new byte[1000]; // TODO: dynamically resize as we get more data

            int index = 0;
            int nextByte;
            while ((nextByte = cipherInputStream.read()) != -1) {
                roundTrippedBytes[index] = (byte) nextByte;
                index++;
            }
            String roundTrippedString = new String(roundTrippedBytes, 0, index, "UTF-8");
            Log.v(TAG, "round tripped string = " + roundTrippedString);

        } catch (NoSuchAlgorithmException e) {
            Log.e(TAG, Log.getStackTraceString(e));
        } catch (NoSuchProviderException e) {
            Log.e(TAG, Log.getStackTraceString(e));
        } catch (InvalidAlgorithmParameterException e) {
            Log.e(TAG, Log.getStackTraceString(e));
        } catch (KeyStoreException e) {
            Log.e(TAG, Log.getStackTraceString(e));
        } catch (CertificateException e) {
            Log.e(TAG, Log.getStackTraceString(e));
        } catch (IOException e) {
            Log.e(TAG, Log.getStackTraceString(e));
        } catch (UnrecoverableEntryException e) {
            Log.e(TAG, Log.getStackTraceString(e));
        } catch (NoSuchPaddingException e) {
            Log.e(TAG, Log.getStackTraceString(e));
        } catch (InvalidKeyException e) {
            Log.e(TAG, Log.getStackTraceString(e));
//        } catch (BadPaddingException e) {
//            Log.e(TAG, Log.getStackTraceString(e));
//        } catch (IllegalBlockSizeException e) {
//            Log.e(TAG, Log.getStackTraceString(e));
        } catch (UnsupportedOperationException e) {
            Log.e(TAG, Log.getStackTraceString(e));
        }
    }

}
