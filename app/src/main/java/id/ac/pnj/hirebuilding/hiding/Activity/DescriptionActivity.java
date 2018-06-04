package id.ac.pnj.hirebuilding.hiding.Activity;


import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;


import id.ac.pnj.hirebuilding.hiding.Adapter.CardRuanganAdapter;
import id.ac.pnj.hirebuilding.hiding.Class.Ruangan;
import id.ac.pnj.hirebuilding.hiding.R;


public class DescriptionActivity extends AppCompatActivity implements View.OnClickListener
{
	private ImageView mimage;
	private TextView mtitle, mharga, mlokasi, mkapasitas, mfasilitas, mtanggal, mkontak, mbar;

	@SuppressLint("SetTextI18n")
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_description);

		init_widget();

		if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP)
		{
			Toolbar toolbar =  findViewById(R.id.Desc_toolbar);
			setSupportActionBar(toolbar);
			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		}



		Ruangan ruangan = CardRuanganAdapter.getRuangan();

		mbar.setText(ruangan.getdesc());
		mtitle.setText(ruangan.getnama_ruangan());
		mkapasitas.setText(ruangan.getkapasitas()+" Orang");
		mkontak.setText(ruangan.getkontak());
		mfasilitas.setText(ruangan.getfasilitas());
		mlokasi.setText(ruangan.getalamat());
		mtanggal.setText(ruangan.getStatus());
		mharga.setText("Rp . " + (int)ruangan.getharga() +" / hari");

		Glide.with(DescriptionActivity.this)
				.load(ruangan.getgambar())
				.crossFade()
				.into(mimage);
	}

	private void init_widget()
	{
		mimage = findViewById(R.id.image_ruangan);
		mtitle =  findViewById(R.id.title_ruangan);
		mharga =  findViewById(R.id.harga_ruangan);
		mfasilitas =  findViewById(R.id.fasilitas_ruangan);
		mlokasi = findViewById(R.id.lokasi_ruangan);
		mtanggal =  findViewById(R.id.tanggal_ruangan);
		mkontak = findViewById(R.id.kontak_ruangan);
		mkapasitas =  findViewById(R.id.kapasitas_ruangan);
		mbar =  findViewById(R.id.bar_title);
	}


	@Override
	public void onClick(View v)
	{

	}
}
