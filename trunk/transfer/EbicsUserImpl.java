// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EbicsUserImpl.java


import de.businesslogics.ebics.client.EbicsPartner;
import de.businesslogics.ebics.client.EbicsUser;
import de.businesslogics.ebics.client.KeyManagement;
import de.businesslogics.ebics.schema.request.OrderID;
import de.businesslogics.ebics.schema.response.EbicsException;
import de.businesslogics.security.jce.BcsSignature;
import de.businesslogics.security.jce.Provider;
import de.businesslogics.zkasecurity.*;
import java.io.*;
import java.security.GeneralSecurityException;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

// Referenced classes of package de.businesslogics.ebics.client:
//            EbicsUser, EbicsPartner, KeyManagement

public class EbicsUserImpl
    implements EbicsUser
{

    public EbicsUserImpl(EbicsPartner partner, String userID, PasswordCallback passwordCallback)
    {
        a004PubKey = null;
        e001PrivKey = null;
        x001PrivKey = null;
        needSave = false;
        try
        {
            this.passwordCallback = passwordCallback;
            A004PrivateKey a004PrivKey = new A004PrivateKey(userID, null);
            a004Key = a004PrivKey.toPKCS(passwordCallback);
            setA004PublicKey(a004PrivKey);
            e001PrivKey = new E001PrivateKey(userID, null);
            x001PrivKey = new X001PrivateKey(userID, null);
            this.userID = userID;
        }
        catch(Exception gse)
        {
            throw new RuntimeException("Weired security setup", gse);
        }
        this.partner = partner;
        needSave = true;
        initialized = initializedHIA = false;
    }

    public boolean needsSave()
    {
        return needSave;
    }

    public EbicsUserImpl(EbicsPartner partner, ObjectInputStream ois, PasswordCallback passwordCallback)
        throws IOException, GeneralSecurityException
    {
        a004PubKey = null;
        e001PrivKey = null;
        x001PrivKey = null;
        needSave = false;
        try
        {
            this.partner = partner;
            this.passwordCallback = passwordCallback;
            a004Key = (byte[])(byte[])ois.readObject();
            byte e001Key[] = (byte[])(byte[])ois.readObject();
            byte x001Key[] = (byte[])(byte[])ois.readObject();
            userID = ois.readUTF();
            initialized = ois.readBoolean();
            initializedHIA = ois.readBoolean();
            OnetimePasswordCallback oneTime = null;
            if(passwordCallback != null)
                oneTime = new OnetimePasswordCallback(passwordCallback);
            e001PrivKey = E001PrivateKey.fromPKCS(e001Key, userID, oneTime);
            x001PrivKey = X001PrivateKey.fromPKCS(x001Key, userID, oneTime);
        }
        catch(ClassNotFoundException cnfe)
        {
            throw new RuntimeException(cnfe);
        }
    }

    public void save(ObjectOutputStream oos)
        throws IOException
    {
        oos.writeObject(a004Key);
        OnetimePasswordCallback oneTime = null;
        if(passwordCallback != null)
            oneTime = new OnetimePasswordCallback(passwordCallback);
        try
        {
            oos.writeObject(e001PrivKey.toPKCS(oneTime));
            oos.writeObject(x001PrivKey.toPKCS(oneTime));
        }
        catch(GeneralSecurityException sec)
        {
            throw new IOException(sec.getMessage());
        }
        oos.writeUTF(userID);
        oos.writeBoolean(initialized);
        oos.writeBoolean(initializedHIA);
        needSave = false;
    }

    public boolean isInitialized()
    {
        return initialized;
    }

    public void setInitialized(boolean b)
    {
        if(b != initialized)
        {
            needSave = true;
            initialized = b;
        }
    }

    public boolean isInitializedHIA()
    {
        return initializedHIA;
    }

    public void setInitializedHIA(boolean b)
    {
        if(b != initializedHIA)
        {
            needSave = true;
            initializedHIA = b;
        }
    }

    public RSAPublicKey getA004PublicKey()
    {
        if(a004PubKey == null)
            setA004PublicKey(getA004PrivateKey());
        return a004PubKey;
    }

    private void setA004PublicKey(A004PrivateKey a004PrivKey)
    {
        try
        {
            byte ini[] = a004PrivKey.getINI();
            byte mod[] = new byte[128];
            byte exp[] = new byte[128];
            System.arraycopy(ini, 16, exp, 0, 128);
            System.arraycopy(ini, 148, mod, 0, 128);
            a004PubKey = Provider.buildKey(mod, exp);
        }
        catch(GeneralSecurityException gse)
        {
            throw new RuntimeException(gse);
        }
    }

    public RSAPublicKey getE001PublicKey()
    {
        return e001PrivKey.getPublicKey();
    }

    public byte[] getE001Digest()
    {
        return e001PrivKey.getHash();
    }

    public RSAPublicKey getX001PublicKey()
    {
        return x001PrivKey.getPublicKey();
    }

    public String getSecurityMedium()
    {
        return "0400";
    }

    public EbicsPartner getPartner()
    {
        return partner;
    }

    public String getPartnerID()
    {
        return partner.getPartnerID();
    }

    public String getUserID()
    {
        return userID;
    }

    public byte[] authenticate(byte digest[])
    {
        return x001PrivKey.authenticate(digest);
    }

    public OrderID updateSignatureKey(KeyManagement keymgmt, PasswordCallback passwordCallback2)
        throws EbicsException, IOException
    {
        try
        {
            A004PrivateKey a004PrivKey = new A004PrivateKey(userID, null);
            OrderID orderID = keymgmt.sendPUB(a004PrivKey.getINI());
            a004Key = a004PrivKey.toPKCS(passwordCallback2);
            setA004PublicKey(a004PrivKey);
            needSave = true;
            return orderID;
        }
        catch(GeneralSecurityException gse)
        {
            throw new RuntimeException("Weired security setup", gse);
        }
    }

    public OrderID updateProtocolKeys(KeyManagement keymgmt)
        throws EbicsException, IOException
    {
        try
        {
            E001PrivateKey e001PrivKey = new E001PrivateKey(userID, null);
            RSAPublicKey newE001PubKey = e001PrivKey.getPublicKey();
            X001PrivateKey x001PrivKey = new X001PrivateKey(userID, null);
            RSAPublicKey newX001PubKey = x001PrivKey.getPublicKey();
            OrderID toReturn = keymgmt.sendHCA(newX001PubKey, newE001PubKey);
            this.e001PrivKey = e001PrivKey;
            this.x001PrivKey = x001PrivKey;
            needSave = true;
            return toReturn;
        }
        catch(GeneralSecurityException gse)
        {
            throw new RuntimeException("Weired security setup", gse);
        }
    }

    private A004PrivateKey getA004PrivateKey()
    {
        try
        {
            return A004PrivateKey.fromPKCS(a004Key, userID, passwordCallback);
        }
        catch(Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    public byte[] decrypt(byte encryptedKey[])
        throws IllegalBlockSizeException, BadPaddingException
    {
        return e001PrivKey.decrypt(encryptedKey);
    }

    public byte[] rawSign(byte dsi[])
    {
        return getA004PrivateKey().signRaw(dsi);
    }

    public byte[] sign(byte digest[], String filename, Date fileDate, String orderType)
    {
        A004PrivateKey a004 = getA004PrivateKey();
        byte dsi[] = (new de.businesslogics.security.jce.BcsSignature.A004Parameter(digest, null)).getDsi();
        return A004Signature.createSignatureFile(userID, filename, fileDate, orderType, a004.signRaw(dsi));
    }

    private final EbicsPartner partner;
    private final String userID;
    private boolean initialized;
    private boolean initializedHIA;
    private transient RSAPublicKey a004PubKey;
    private byte a004Key[];
    private transient E001PrivateKey e001PrivKey;
    private transient X001PrivateKey x001PrivKey;
    private transient boolean needSave;
    private transient PasswordCallback passwordCallback;
    
	
}
