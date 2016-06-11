package com.thienq.webstore.service;

import com.google.gdata.client.photos.PicasawebService;
import com.google.gdata.util.AuthenticationException;

/**
 * Created by Thien on 6/11/2016.
 */
public class PicasaService {
    private PicasawebService pws;

    private PicasaService(){
        pws = authentication();
    }

    private static class PicasaServiceHelper{
        public static final PicasaService INSTANCE = new PicasaService();
    }

    public static PicasaService getInstance(){
        return PicasaServiceHelper.INSTANCE;
    }

    private static PicasawebService authentication(){
        PicasawebService pws = null;
        try {
            pws = new PicasawebService("webstore");
            pws.setUserCredentials("webstore.image.storage@gmail.com", "abc@2016");
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
        return pws;
    }

    public void upload(){

    }
}
