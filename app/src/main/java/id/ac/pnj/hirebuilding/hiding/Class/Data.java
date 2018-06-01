package id.ac.pnj.hirebuilding.hiding.Class;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.*;

import id.ac.pnj.hirebuilding.hiding.Adapter.CardRuanganAdapter;


public class Data
{

	public static void getDataFromDatabase(final CardRuanganAdapter adapter, final ArrayList<Ruangan> ListRuangan, final String TAG)
	{
		FirebaseDatabase database = FirebaseDatabase.getInstance();
		DatabaseReference myRef = database.getReference("ruangan");
		myRef.addValueEventListener(new ValueEventListener()
		{
			@Override
			public void onDataChange(DataSnapshot dataSnapshot)
			{
				for (DataSnapshot roomSnapshot : dataSnapshot.getChildren())
				{
					Ruangan room = roomSnapshot.getValue(Ruangan.class);
					ListRuangan.add(room);
				}

				adapter.notifyDataSetChanged();
			}

			@Override
			public void onCancelled(DatabaseError databaseError)
			{
				Log.w(TAG, "onCancelled: ", databaseError.toException());
			}
		});

	}

}






















	/*public static String[][] data = new String[][]{

			{"0", "Gedung ALITA", "https://drive.google.com/uc?id=1U1GYm0apQz_N2XDzZl3t5L2cTyOUkbKC", "Jakarta Selatan", "Jl. TB. Simatupang, Jakarta Selatan", "200", "AC 4, kursi, sofa 2, meja 2, Sound system", "250000", "081276534521", " 18-05-2018 sampai 30-07-2018"},
			{"1", "Gedung Jaya", "https://drive.google.com/uc?id=1gfxFabhv81M-epRX_4HtFBLrg-T5Z0A3", "Jakarta Pusat", "MH Thamrin Jakarta Pusat", "400", "AC 5, kursi, sofa 4, meja 3, Sound system", "600000", "081276534522", "18-05-2018 sampai 30-07-2018"},
			{"2", "Menara Standard Charted", "https://drive.google.com/uc?id=1Jni1H056X9ULr2ZsJBfdqAIMBvmWj2ym", "Jakarta Selatan", "JL. Prof. Dr. Satrio, Jakarta Selatan", "470", "AC 5, kursi, sofa 4, meja 3, Sound system", "700000", "081276534523", "18-05-2018 sampai 30-07-2018"},
			{"3", "Gedung Komersial", "https://drive.google.com/uc?id=1qj1wCvitDdX4ACdhuyETDWFNQppHxpcc", "Jakarta Barat", "Tanjung Duren, Jakarta Barat", "220", "AC 4, kursi, sofa 3, meja 2, Sound system", "300000", "081276534524", "18-05-2018 sampai 30-07-2018"},
			{"4", "Gedung Kantor", "https://drive.google.com/uc?id=1cCmJ0UKbH7Q5un17Rx6CZ6_KIOUqRf_j", "Jakarta Selatan", "Jl. Prof. Dr. Soepomo,Tebet, Jakarta Selatan", "500", "AC 6, kursi, sofa 4, meja 3, Sound system, Konsumsi", "850000", "081276534525", "18-05-2018 sampai 30-07-2018"},
			{"5", "Gedung ex bank dan Restaurant", "https://drive.google.com/uc?id=1D6UhoFMxf5QeKYkgzv-TUvvhE2wTR2Rd", "Jakarta Barat", "Taman Sari, Jakarta Barat", "300", "AC 4, kursi, sofa 4, meja 2, Sound system", "600000", "081276534526", "18-05-2018 sampai 30-07-2018"},
			{"6", "Gedung Graha inti", "https://drive.google.com/uc?id=1ZLdYK3jWaN7mQFWbqKZt3Gb8h2HHU2vi", "Jakarta Timur", "Pondok Bambu, Duren Sawit, Jakarta Timur", "380", "AC 4, kursi, sofa 4, meja 2, Sound system", "690000", "081276534527", "18-05-2018 sampai 30-07-2018"},
			{"7", "Office Space For Lease Wisma Antara", "https://drive.google.com/uc?id=16T4QGjHcLKlFwaGwS8OtTy8Rr3P4F2-6", "Jakarta Pusat", "Gambir, Jakarta Pusat", "330", "AC 4, kursi, sofa 3, meja 2, Sound system", "670000", "081276534528", "18-05-2018 sampai 30-07-2018"},
			{"8", "Office Space APL Tower Central Park Podomoro City", "https://drive.google.com/uc?id=19BWo0yQdU-dd0SjPWDXpx7XG4v0GkW6a", "Jakarta Barat", "CBD Jakarta Barat, Tanjung Duren", "480", "AC 4, kursi, sofa 4, meja 3, Sound system", "750000", "081276534529", "18-05-2018 sampai 30-07-2018"},
			{"9", "Gedung Fatmawati 75", "https://drive.google.com/uc?id=1yUeRNUu8S75M8YCJfaSRHVcKsLzNECvk", "Jakarta Selatan", "Jl.Fatmawati, Gedung Fatmawati 75, Jakarta Selatan", "1000", "AC 6, kursi, sofa 5, meja 4, Sound system, Konsumsi", "1700000", "081276534530", "18-05-2018 sampai 30-07-2018"},

	};


	/*public static ArrayList<Ruangan> getListData()
	{
		Ruangan ruangan = null;

		ArrayList<Ruangan> list = new ArrayList<>();

		for (int i = 0; i < data.length; i++)
		{
			ruangan = new Ruangan();
			ruangan.setnama(data[i][1]);
			ruangan.set_gambar(data[i][2]);
			ruangan.set_deskripsi(data[i][3]);
			ruangan.set_lokasi(data[i][4]);
			ruangan.set_kapasitas(Integer.parseInt(data[i][5]));
			ruangan.set_fasilitas(data[i][6]);
			ruangan.set_harga(Float.parseFloat(data[i][7]));
			ruangan.set_kontak(data[i][8]);

			list.add(ruangan);
		}

		return list;
	}*/



