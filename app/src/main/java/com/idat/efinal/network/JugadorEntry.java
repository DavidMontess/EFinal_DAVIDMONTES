package com.idat.efinal.network;

import android.content.res.Resources;
import android.net.Uri;
import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.idat.efinal.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JugadorEntry {
    private static final String TAG=JugadorEntry.class.getSimpleName();

    public final String title;
    public final Uri dynamicUrl;
    public final String nombre;
    public final String foto;

    public JugadorEntry(String title, String dynamicUrl, String url, String description) {
        this.title = title;
        this.dynamicUrl = Uri.parse(dynamicUrl);
        this.nombre = url;
        this.foto = description;
    }
    public static List<JugadorEntry> initJugadorEntryList(Resources resources){
        InputStream inputStream=resources.openRawResource(R.raw.futbolistas);
        Writer writer=new StringWriter();
        char[] buffer= new char[1024];

        try {
            Reader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            int pointer;
            while ((pointer = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, pointer);
            }
        }catch (IOException exception) {
            Log.e(TAG, "error al escribir o o leer JSON",exception);
        }finally {
            try {
                inputStream.close();
            }catch (IOException exception){
                Log.e(TAG, "error al cerrar la conexion",exception);
            }
        }
        String jsonJugadorString= writer.toString();
        Gson gson= new Gson();
        Type jugadorListType= new TypeToken<ArrayList<JugadorEntry>>(){
        }.getType();

        return gson.fromJson(jsonJugadorString,jugadorListType);

    }

}
