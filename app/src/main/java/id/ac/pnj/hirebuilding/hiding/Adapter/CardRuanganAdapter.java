package id.ac.pnj.hirebuilding.hiding.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import id.ac.pnj.hirebuilding.hiding.Activity.DescriptionActivity;
import id.ac.pnj.hirebuilding.hiding.Class.Ruangan;
import id.ac.pnj.hirebuilding.hiding.R;

public class CardRuanganAdapter extends RecyclerView.Adapter<CardRuanganAdapter.CardViewViewHolder>
{

	private Context context;
	private ArrayList<Ruangan> _listRuangan;
	private static Ruangan ruangan;

	public CardRuanganAdapter(Context context)
	{
		this.context = context;
	}

	public void set_listRuangan(ArrayList<Ruangan> _listRuangan)
	{
		this._listRuangan = _listRuangan;
	}

	public ArrayList<Ruangan> getListRuangan()
	{
		return _listRuangan;
	}


	@NonNull
	@Override
	public CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
	{
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_ruangan_view, parent, false);
		return new CardViewViewHolder(view);
	}

	public static Ruangan getRuangan()
	{
		return ruangan;
	}

	public static void setRuangan(Ruangan ruangan)
	{
		CardRuanganAdapter.ruangan = ruangan;
	}

	@Override
	public void onBindViewHolder(@NonNull CardViewViewHolder holder, int position)
	{
		Ruangan ruangan = getListRuangan().get(position);

		Glide.with(context)
				.load(ruangan.getgambar())
				.override(350, 550)
				.into(holder.imgPhoto);

		holder.tvName.setText(ruangan.getnama_ruangan());
		holder.tvRemarks.setText(ruangan.getdesc());



		holder.btnFavorite.setOnClickListener(new CustomOnItemClickListener(position, new CustomOnItemClickListener.OnItemClickCallback()
		{

			@Override
			public void onItemClicked(View view, int position)
			{
				setRuangan(getListRuangan().get(position));
				Intent intent = new Intent(context, DescriptionActivity.class);
				Toast.makeText(context, "Pilih " + getListRuangan().get(position).getnama_ruangan(), Toast.LENGTH_SHORT).show();
				context.startActivity(intent);
			}
		}));

	}



	@Override
	public int getItemCount()
	{
		return _listRuangan.size();
	}

	public class CardViewViewHolder extends RecyclerView.ViewHolder
	{
		ImageView imgPhoto;
		TextView tvName, tvRemarks;
		Button btnFavorite;

		public CardViewViewHolder(View itemView)
		{
			super(itemView);
			imgPhoto = (ImageView) itemView.findViewById(R.id.img_item_photo);
			tvName = (TextView) itemView.findViewById(R.id.tv_item_name);
			tvRemarks = (TextView) itemView.findViewById(R.id.tv_item_remarks);
			btnFavorite = (Button) itemView.findViewById(R.id.btn_set_favorite);
		}
	}
}
