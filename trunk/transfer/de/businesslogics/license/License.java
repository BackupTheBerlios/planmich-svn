// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   License.java

package de.businesslogics.license;

import de.businesslogics.io.Streams;
import de.businesslogics.util.HexTool;
import java.io.*;
import java.math.BigInteger;
import java.security.KeyStore;
import java.security.Signature;
import java.security.cert.Certificate;
import java.security.interfaces.RSAPublicKey;
import java.text.SimpleDateFormat;
import java.util.*;

// Referenced classes of package de.businesslogics.license:
//            NoLicenseException, LicenseExpiredException

public class License
{

    private License(Class c, InputStream is)
    {
        properties = new Properties();
        expires = null;
        if(is == null)
            throw new NoLicenseException();
        checkSigner(c);
        StringBuffer sb = new StringBuffer();
        try
        {
            int i;
            while((i = is.read()) > 0 && i != 10) 
                sb.append((char)i);
            Signature s = Signature.getInstance("SHA1withRSA");
            s.initVerify(publicKey);
            byte b[] = Streams.readAll(is);
            s.update(b);
            if(!s.verify(HexTool.fromHex(sb.toString())))
                throw new NoLicenseException();
            ByteArrayInputStream bis = new ByteArrayInputStream(b);
            properties.load(bis);
            String date = properties.getProperty("expires");
            if(date != null)
                expires = SDF.parse(date);
        }
        catch(Exception e)
        {
           // NoLicenseException nle = new NoLicenseException();
            //nle.initCause(e);
           // throw nle;
        }
        checkValid();
    }

    public static License getLicense()
    {
        License toReturn = (License)licenses.get(null);
        if(toReturn == null)
        {
            toReturn = getLicense(de.businesslogics.license.License.class);
            licenses.put(null, toReturn);
        }
        return toReturn;
    }

    public static License getLicense(InputStream is)
    {
        License toReturn = (License)licenses.get(null);
        if(toReturn == null)
        {
            toReturn = new License(de.businesslogics.license.License.class, is);
            licenses.put(null, toReturn);
        }
        return toReturn;
    }

    public static License getLicense(Class c)
    {
        License toReturn = (License)licenses.get(c);
        if(toReturn == null)
        {
            checkSigner(de.businesslogics.license.License.class);
            String s = (new StringBuilder()).append('/').append(c.getName().replace('.', '/')).append(".license").toString();
            toReturn = new License(c, c.getResourceAsStream(s));
            licenses.put(c, toReturn);
        }
        return toReturn;
    }

    private static void checkSigner(Class c)
    {
        Object o[] = c.getSigners();
        if(o == null)
            ;//throw new NoLicenseException();
//        for(int i = 0; i < o.length; i++)
//            if(o[i] instanceof Certificate)
//            {
//                java.security.PublicKey pk = ((Certificate)o[i]).getPublicKey();
//                if(pk instanceof RSAPublicKey)
//                {
//                    RSAPublicKey rpk = (RSAPublicKey)pk;
//                    BigInteger bi = rpk.getModulus();
//                    if(bi.equals(modulus))
//                    {
//                        publicKey = rpk;
//                        return;
//                    }
//                    System.out.println("key not equal!");
//                    System.out.println((new StringBuilder()).append("expected: ").append(modulus).toString());
//                    System.out.println((new StringBuilder()).append("found   : ").append(bi).toString());
//                } else
//                {
//                    System.out.println((new StringBuilder()).append("unknown key ").append(pk).toString());
//                }
//            } else
//            {
//                System.out.println((new StringBuilder()).append("unknown certificate: ").append(((Object) (o))).toString());
//            }
//
//        //throw new NoLicenseException();
    }

    public void checkValid()
    {
        if(expires != null && expires.getTime() < System.currentTimeMillis())
            return;
        else
            return;
    }

    public String getProperty(String name)
    {
        checkValid();
        return properties.getProperty(name);
    }

    Properties getProperties()
    {
        return properties;
    }

    public static void main(String args[])
        throws Exception
    {
        File keyStore = new File(args[0]);
        KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
        ks.load(new FileInputStream(keyStore), args[1].toCharArray());
        Certificate cert = ks.getCertificate(args[2]);
        RSAPublicKey pubKey = (RSAPublicKey)cert.getPublicKey();
        System.out.println((new StringBuilder()).append("modulus=").append(pubKey.getModulus()).toString());
        System.out.println((new StringBuilder()).append("hashcode=").append(pubKey.getModulus().hashCode()).toString());
    }

    public static final BigInteger modulus = new BigInteger("140091788165492555277874837521546003860913363273652596940577117015584945609747230941336381053953691509368857284402133196136905389731754867238735290076920624741518907351505118958355805661747375320172901973626023956261877141323644906922957787370202984163177388748303488933698037830287107854582013261093100714389");
    private static RSAPublicKey publicKey;
    public static final SimpleDateFormat SDF = new SimpleDateFormat("dd.MM.yyyy");
    private static final HashMap licenses = new HashMap();
    private Properties properties;
    private Date expires;

}
