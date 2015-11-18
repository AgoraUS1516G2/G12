package algoritmos;
 
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
 
public class test {
 
    public static void main(String[] args) throws NoSuchAlgorithmException,
            NoSuchPaddingException, BadPaddingException,
            InvalidKeySpecException, InvalidKeyException,
            IllegalBlockSizeException {
        String voto = "{\"age\": \"24\",\"answers\":[{\"question\":\"Pregunta 1\",\"answer_question\":\"SI\"}"
                + "],\"id\": 1,\"autonomous_community\": \"Andalucia\",\"genre\": \"Masculino\",\"id_poll\": 32778}";
        // Authority aut = new AuthorityImpl();
        // String publicKey = aut.getPublicKey("4");
        //
        // byte[] cifrado = aut.encrypt("4", voto);
 
        // String des = aut.decrypt("4", cifrado);
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(2048);
        KeyPair kp = kpg.genKeyPair();
        PublicKey publicKey = kp.getPublic();
        PrivateKey privateKey = kp.getPrivate();
 
        System.out.println("keys created");
 
        KeyFactory fact = KeyFactory.getInstance("RSA");
        RSAPublicKeySpec pubKey = fact.getKeySpec(publicKey,
                RSAPublicKeySpec.class);
        RSAPrivateKeySpec privKey = fact.getKeySpec(privateKey,
                RSAPrivateKeySpec.class);
 
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
 
        System.out.println(cipher);
 
        byte[] res = null;
        // Authority aut = new AuthorityImpl();
        res = cipher.doFinal(voto.getBytes());
 
        System.out.println(res);
 
        System.out.println("start decryption");
 
        String votoDesencriptado = Algoritmo.decrypt(res, privateKey);
 
        System.out.println(votoDesencriptado);
 
        List<String> votes = new ArrayList<String>();
 
//      final Gson gson = new Gson();
//      final VotoNuevo votoNuevo = gson.fromJson(votoDesencriptado,
//              VotoNuevo.class);
         
         
    }
 
   
 
}
