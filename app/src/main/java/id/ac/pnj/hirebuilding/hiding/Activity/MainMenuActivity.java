package id.ac.pnj.hirebuilding.hiding.Activity;

import android.os.Bundle;
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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import id.ac.pnj.hirebuilding.hiding.Adapter.CardRuanganAdapter;
import id.ac.pnj.hirebuilding.hiding.Class.DataRuangan;
import id.ac.pnj.hirebuilding.hiding.Class.Ruangan;
import id.ac.pnj.hirebuilding.hiding.R;

public class MainMenuActivity extends AppCompatActivity
{


	private static final String TAG = "MainMenuActivity";
	ActionBarDrawerToggle toggle;
	private ArrayList<Ruangan> mRoom = new ArrayList<>();
	private CardRuanganAdapter cardViewRuanganAdapter;


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_menu);

		findViewById(R.id.mainmenu_main_layout).requestFocus();

		if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP)
		{
			Toolbar toolbar = (Toolbar) findViewById(R.id.MainMenu_toolbar);
			setSupportActionBar(toolbar);
		}

		initNavigation();
		RecyclerCardRuangan();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		return toggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
	}

	private void initNavigation()
	{
		DrawerLayout draw = (DrawerLayout) findViewById(R.id.Drawer_mainmenu);
	    toggle = new ActionBarDrawerToggle(this,draw,R.string.open,R.string.close);
		draw.addDrawerListener(toggle);
		toggle.syncState();
		getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_akun_profile);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);


	}

	private void RecyclerCardRuangan()
	{
		//ArrayList<Ruangan> _list = new ArrayList<Ruangan>(DataRuangan.getListData());
		RecyclerView rvCategory = (RecyclerView) findViewById(R.id.rv_ruangan_card);
		rvCategory.setLayoutManager(new GridLayoutManager(this,2));
		cardViewRuanganAdapter = new CardRuanganAdapter(this);
		cardViewRuanganAdapter.set_listRuangan(mRoom);
		rvCategory.setAdapter(cardViewRuanganAdapter);
		DataRuangan.getDataFromDatabase(cardViewRuanganAdapter, mRoom, TAG);
	}


}
