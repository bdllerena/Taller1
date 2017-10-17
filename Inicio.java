package ufa.desarrolllo.ecuador;

import android.app.AlertDialog;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import ufa.desarrollo.ecuador.MYSQL.Downloader;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;

import static java.security.AccessController.getContext;

/**
 * Created by Steven on 12/02/2017.
 */

public class Inicio extends Fragment

    {
        View rootView;
        MediaPlayer mediaPlayer;
        URL url;
        Bitmap bmp;
        Handler handler = new Handler();
        SeekBar seek;
        ArrayList<String> datos = new ArrayList<>();
        ArrayList<String> dat = new ArrayList<>();
        private double startTime = 0;
        private double finalTime = 0;
        String urlAd="http://192.168.0.107:8080/datos.php";

        private Handler myHandler = new Handler();;
        private int forwardTime = 5000;
        private int backwardTime = 5000;
        public static int oneTimeOnly = 0;
        //DAO dao = new DAO();



    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.activity_principal, container, false);
        ImageView ima1 = (ImageView) rootView.findViewById(R.id.img1);
        ImageView ima2 = (ImageView) rootView.findViewById(R.id.img2);
        ImageView ima3 = (ImageView) rootView.findViewById(R.id.img3);
        ImageView ima4 = (ImageView) rootView.findViewById(R.id.img4);
        ImageView ima5 = (ImageView) rootView.findViewById(R.id.img5);
        ImageView ima6 = (ImageView) rootView.findViewById(R.id.img6);
        ImageView ima7 = (ImageView) rootView.findViewById(R.id.img7);
        ImageView ima8 = (ImageView) rootView.findViewById(R.id.img8);
        ImageView ima9 = (ImageView) rootView.findViewById(R.id.img9);

        System.out.println("Cargado");


        for(int i=0;i<9;i++){
            datos.add(i, "https://rapecuador.000webhostapp.com/musica/" + i + ".jpg");
        }

        String u1 = "https://rapecuador.000webhostapp.com/musica/1.jpg";
        String u2 = "https://rapecuador.000webhostapp.com/musica/2.jpg";
        String u3 = "https://rapecuador.000webhostapp.com/musica/3.jpg";
        String u4 = "https://rapecuador.000webhostapp.com/musica/4.jpg";
        String u5 = "https://rapecuador.000webhostapp.com/musica/5.jpg";
        String u6 = "https://rapecuador.000webhostapp.com/musica/6.jpg";
        String u7 = "https://rapecuador.000webhostapp.com/musica/7.jpg";
        String u8 = "https://rapecuador.000webhostapp.com/musica/8.jpg";
        String u9 = "https://rapecuador.000webhostapp.com/musica/9.jpg";
        Picasso.with(getContext()).load(u1).into(ima1);
        Picasso.with(getContext()).load(u2).into(ima2);
        Picasso.with(getContext()).load(u3).into(ima3);
        Picasso.with(getContext()).load(u4).into(ima4);
        Picasso.with(getContext()).load(u5).into(ima5);
        Picasso.with(getContext()).load(u6).into(ima6);
        Picasso.with(getContext()).load(u7).into(ima7);
        Picasso.with(getContext()).load(u8).into(ima8);
        Picasso.with(getContext()).load(u9).into(ima9);

        final TextView canci = (TextView)rootView.findViewById(R.id.txt_cancion);
        Button detener = (Button)rootView.findViewById(R.id.btn_stop);

        Button buscar = (Button) rootView.findViewById(R.id.btn_buscar);


        seek = (SeekBar)rootView.findViewById(R.id.seekBar);
        seek.setClickable(false);


        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //dao.buscar();
                /*AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Saludos");
                builder.setMessage("No tenemos artistas suficientes para mostrarte el buscar, aun estamos desarrollando esta caracteristica");
                builder.setPositiveButton("OK",null);
                builder.create();
                builder.show();*/

                new Downloader(getContext(),urlAd,dat).execute();
                for(int i=0;i<dat.size();i++){
                    System.out.println(dat.get(i));
                    Toast.makeText(getContext(),dat.get(i),Toast.LENGTH_SHORT).show();
                }
            }
        });

        detener.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.stop();
                canci.setText("");

            }
        });

        ima1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Repoducir("https://rapecuador.000webhostapp.com/musica/1.mp3");
                canci.setText("Reproduciendo");
                //canci.setText("ENCIENDELA - DISCIPULOS DEL CONCRETO");
            }
        });
        ima2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Repoducir("https://rapecuador.000webhostapp.com/musica/2.mp3");
                //canci.setText("REGGAE TRACK MUSIC - JEFFO MC");
                canci.setText("Reproduciendo");

            }
        });
        ima3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Repoducir("https://rapecuador.000webhostapp.com/musica/3.mp3");
                //canci.setText("NOS VEMOS DESPUES - JEFFO MC");
                canci.setText("Reproduciendo");
            }
        });
        ima4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Repoducir("https://rapecuador.000webhostapp.com/musica/4.mp3");
                //canci.setText("NO ES MENTIRA ES REAL - TEO FT JEFFO MC");
                canci.setText("Reproduciendo");
            }
        });
        ima5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Repoducir("https://rapecuador.000webhostapp.com/musica/5.mp3");
                //canci.setText("VOY CAMINANDO - CONEXION DEL VALLE FT NATIVO ELT");
                canci.setText("Reproduciendo");
            }
        });
        ima6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Repoducir("https://rapecuador.000webhostapp.com/musica/6.mp3");
                //canci.setText("CONTRAGIRO - POLAR MC FT ARMONICKO");
                canci.setText("Reproduciendo");
            }
        });
        ima7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Repoducir("https://rapecuador.000webhostapp.com/musica/7.mp3");
                //canci.setText("MADAFAKERS CRIMEN - CSW");
                canci.setText("Reproduciendo");
            }
        });
        ima8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Repoducir("https://rapecuador.000webhostapp.com/musica/8.mp3");
                //canci.setText("EL ESCRITOR - SHARIFF");
                canci.setText("Reproduciendo");
            }
        });
        ima9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Repoducir("https://rapecuador.000webhostapp.com/musica/9.mp3");
                //canci.setText("ASI LLEVO MIS MOVIDAS - ALEJO MC FT ERICK PEREZ");
                canci.setText("Reproduciendo");
            }
        });


        return rootView;

    }

    private void setListParentItemInfo() {
        ImageView ima1 = (ImageView) rootView.findViewById(R.id.img1);
        ImageView ima2 = (ImageView) rootView.findViewById(R.id.img2);
        ImageView ima3 = (ImageView) rootView.findViewById(R.id.img3);
        ImageView ima4 = (ImageView) rootView.findViewById(R.id.img4);
        ImageView ima5 = (ImageView) rootView.findViewById(R.id.img5);
        ImageView ima6 = (ImageView) rootView.findViewById(R.id.img6);
        ImageView ima7 = (ImageView) rootView.findViewById(R.id.img7);
        ImageView ima8 = (ImageView) rootView.findViewById(R.id.img8);
        ImageView ima9 = (ImageView) rootView.findViewById(R.id.img9);

        String u1 = "https://rapecuador.000webhostapp.com/musica/1.jpg";
        String u2 = "https://rapecuador.000webhostapp.com/musica/2.jpg";
        String u3 = "https://rapecuador.000webhostapp.com/musica/3.jpg";
        String u4 = "https://rapecuador.000webhostapp.com/musica/4.jpg";
        String u5 = "https://rapecuador.000webhostapp.com/musica/5.png";
        String u6 = "https://rapecuador.000webhostapp.com/musica/6.jpg";
        String u7 = "https://rapecuador.000webhostapp.com/musica/7.jpg";
        String u8 = "https://rapecuador.000webhostapp.com/musica/8.jpg";
        String u9 = "https://rapecuador.000webhostapp.com/musica/9.jpg";
        Picasso.with(getContext()).load(u1).into(ima1);
        Picasso.with(getContext()).load(u2).into(ima2);
        Picasso.with(getContext()).load(u3).into(ima3);
        Picasso.with(getContext()).load(u4).into(ima4);
        Picasso.with(getContext()).load(u5).into(ima5);
        Picasso.with(getContext()).load(u6).into(ima6);
        Picasso.with(getContext()).load(u7).into(ima7);
        Picasso.with(getContext()).load(u8).into(ima8);
        Picasso.with(getContext()).load(u9).into(ima9);


    }

    public void Repoducir(String url){

        if (mediaPlayer==null|| mediaPlayer.isPlaying()==false) {
            mediaPlayer = MediaPlayer.create(getContext(), Uri.parse(url));
            mediaPlayer.start();
            finalTime = mediaPlayer.getDuration();
            startTime = mediaPlayer.getCurrentPosition();

            if (oneTimeOnly == 0) {
                seek.setMax((int) finalTime);
                oneTimeOnly = 1;
            }

            seek.setProgress((int)startTime);
            myHandler.postDelayed(UpdateSongTime,100);



        } else {
            mediaPlayer.stop();
            mediaPlayer.reset();
            mediaPlayer = MediaPlayer.create(getContext(), Uri.parse(url));
            mediaPlayer.start();




        }
    }

    private Runnable UpdateSongTime = new Runnable() {
        public void run() {
            startTime = mediaPlayer.getCurrentPosition();
            seek.setProgress((int)startTime);
            myHandler.postDelayed(this, 100);
        }
    };

}

