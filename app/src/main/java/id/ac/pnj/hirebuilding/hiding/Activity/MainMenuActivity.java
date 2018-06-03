package id.ac.pnj.hirebuilding.hiding.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

import id.ac.pnj.hirebuilding.hiding.Adapter.CardRuanganAdapter;
import id.ac.pnj.hirebuilding.hiding.Class.CurrentUser;
import id.ac.pnj.hirebuilding.hiding.Class.Data;
import id.ac.pnj.hirebuilding.hiding.Class.Ruangan;
import id.ac.pnj.hirebuilding.hiding.Class.User;
import id.ac.pnj.hirebuilding.hiding.R;

public class MainMenuActivity extends AppCompatActivity
		implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener
{


	private static final String TAG = "MainMenuActivity";
	private ActionBarDrawerToggle toggle;
	private ArrayList<Ruangan> mRoom = new ArrayList<>();
	private CardRuanganAdapter cardViewRuanganAdapter;
	private ProgressBar progressBar;
	private MenuItem menu_nama, menu_email, menu_alamat, menu_telp;
	private NavigationView nav_profile;
	private DrawerLayout draw;
	private Toolbar toolbar;
	private Menu menu;


	@Override //int main
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_menu);

		findViewById(R.id.Drawer_mainmenu).requestFocus();

		if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP)
		{
			 toolbar = findViewById(R.id.MainMenu_toolbar);
			setSupportActionBar(toolbar);
		}

		initNavigation();
		initWidget();
		getUserDetail();
		RecyclerCardRuangan();

	}

	@Override // bikin menu samping kanan toolbar
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}

	@Override // aksi dimana menu di pilih
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.menu_filter)
		{
			return true;
		}
		if (id == R.id.menu_sort)
		{
			return true;
		}
		if (id == R.id.menu_status)
		{
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	@Override //aksi dimana navgiasi kiri besar
	public boolean onNavigationItemSelected(@NonNull MenuItem item)
	{
		int id  = item.getItemId();

		if ( id == R.id.nav_exit )
		{
			log_out();
		}

		else if( id == R.id.nav_change)
		{

		}

		draw.closeDrawer(GravityCompat.START);
		return true;
	}

	private void initNavigation() //inisialisasi navigasi
	{

		draw = (DrawerLayout) findViewById(R.id.Drawer_mainmenu);
		toggle = new ActionBarDrawerToggle
				(this, draw,toolbar, R.string.open, R.string.close);
		draw.addDrawerListener(toggle);
		toggle.syncState();

		getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_akun_profile);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		NavigationView navigationView = findViewById(R.id.navigation_profile);
		navigationView.setNavigationItemSelectedListener(this);
		menu = navigationView.getMenu();
	}

	@Override
	public void onBackPressed() // aksi ketika navigasi kiri di swipe
	{
		if (draw.isDrawerOpen(GravityCompat.START))
		{
			draw.closeDrawer(GravityCompat.START);
		}
		else
		{
			super.onBackPressed();
		}
	}

	private void log_out()   // mettho log out
	{
		FirebaseAuth.getInstance().signOut();
		CurrentUser.nama = null;
		CurrentUser.alamat = null;
		CurrentUser.email = null;
		CurrentUser.umur = null;
		CurrentUser.no_telp = null;
		Intent intent = new Intent(MainMenuActivity.this, LoginActivity.class);
		startActivity(intent);
		finish();
	}

	private void initWidget() //inisialisasi wiget
	{
		menu_nama = menu.findItem(R.id.nav_nama);
		menu_alamat = menu.findItem(R.id.nav_alamat);
		menu_email = menu.findItem(R.id.nav_email);
		menu_telp = menu.findItem(R.id.nav_tlp);
		progressBar = findViewById(R.id.MM_progress);
		nav_profile = findViewById(R.id.navigation_profile);
	}

	private void RecyclerCardRuangan()  // menampilkan ruangan sesuai jumlah ruangan
	{
		RecyclerView rvCategory = (RecyclerView) findViewById(R.id.rv_ruangan_card);
		rvCategory.setLayoutManager(new GridLayoutManager(this, 3));
		cardViewRuanganAdapter = new CardRuanganAdapter(this);
		cardViewRuanganAdapter.set_listRuangan(mRoom);
		rvCategory.setAdapter(cardViewRuanganAdapter);
		Data.getDataRuanganFromDatabase(cardViewRuanganAdapter, mRoom, TAG, progressBar);
	}


	@SuppressLint("SetTextI18n")
	private void getUserDetail()   // mengambil data user dari database
	{
		progressBar.setVisibility(View.VISIBLE);
		FirebaseDatabase database = FirebaseDatabase.getInstance();
		DatabaseReference RetriveUser = database.getReference("Users");
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
		{
			RetriveUser.child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid())
					.addValueEventListener(new ValueEventListener()
					{
						@Override
						public void onDataChange(DataSnapshot dataSnapshot)
						{
							User user = dataSnapshot.getValue(User.class);
							CurrentUser.nama = user.getNama();
							CurrentUser.alamat = user.getAlamat();
							CurrentUser.email = user.getEmail();
							CurrentUser.umur = user.getUmur();
							CurrentUser.no_telp = user.getNo_telp();

							menu_nama.setTitle(CurrentUser.nama);
							menu_alamat.setTitle(CurrentUser.alamat);
							menu_email.setTitle(CurrentUser.email);
							menu_telp.setTitle(CurrentUser.no_telp);

							progressBar.setVisibility(View.GONE);

						}
						@Override
						public void onCancelled(DatabaseError databaseError)
						{
							Log.w(TAG, "onCancelled: ", databaseError.toException());
						}
					});
		}

	}


	@Override
	public void onClick(View v)
	{

	}
}
