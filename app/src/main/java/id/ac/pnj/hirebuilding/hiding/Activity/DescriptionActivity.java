package id.ac.pnj.hirebuilding.hiding.Activity;


import android.annotation.SuppressLint;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;


import id.ac.pnj.hirebuilding.hiding.Adapter.CardRuanganAdapter;
import id.ac.pnj.hirebuilding.hiding.Class.Ruangan;
import id.ac.pnj.hirebuilding.hiding.R;

public class DescriptionActivity extends AppCompatActivity
{
	private ImageView _image;
	private TextView _title, _harga, _lokasi, _kapasitas, _fasilitas, _tanggal, _kontak, _bar;

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

		BottomNavigationView btm = findViewById(R.id.btm_nav) ;

		Ruangan ruangan = CardRuanganAdapter.getRuangan();

		_bar.setText(ruangan.getdesc());
		_title.setText(ruangan.getnama_ruangan());
		_kapasitas.setText(ruangan.getkapasitas()+" Orang");
		_kontak.setText(ruangan.getkontak());
		_fasilitas.setText(ruangan.getfasilitas());
		_lokasi.setText(ruangan.getalamat());
		_tanggal.setText(ruangan.getStatus());
		_harga.setText("Rp . " + (int)ruangan.getharga() +" / hari");

		Glide.with(DescriptionActivity.this)
				.load(ruangan.getgambar())
				.crossFade()
				.into(_image);
	}

	private void init_widget()
	{
		_image = findViewById(R.id.image_ruangan);
		_title =  findViewById(R.id.title_ruangan);
		_harga =  findViewById(R.id.harga_ruangan);
		_fasilitas =  findViewById(R.id.fasilitas_ruangan);
		_lokasi = findViewById(R.id.lokasi_ruangan);
		_tanggal =  findViewById(R.id.tanggal_ruangan);
		_kontak = findViewById(R.id.kontak_ruangan);
		_kapasitas =  findViewById(R.id.kapasitas_ruangan);
		_bar =  findViewById(R.id.bar_title);
	}


}
