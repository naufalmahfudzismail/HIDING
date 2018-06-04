package id.ac.pnj.hirebuilding.hiding.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import id.ac.pnj.hirebuilding.hiding.R;

public class PromosiPagerAdapter extends PagerAdapter
{

	private Activity activity;

	private List<String> ImagesList = new ArrayList<>();

	private LayoutInflater inflater;

	private boolean doNotifyDataSetChangedOnce = false;

	public PromosiPagerAdapter(Activity activity, List<String> ImagesList)
	{
		this.activity = activity;
		this.ImagesList = ImagesList;
	}

	@Override
	public int getCount()
	{
		return ImagesList.size();
	}


	@Override
	public boolean isViewFromObject(@NonNull View view, @NonNull Object object)
	{
		return view == object;
	}

	@NonNull
	@Override
	public Object instantiateItem(@NonNull ViewGroup container, int position)
	{

		inflater = (LayoutInflater) activity.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		assert inflater != null;
		View itemview = inflater.inflate(R.layout.pager_promotion, container, false);

		ImageView image;
		image = itemview.findViewById(R.id.image_promotion);

		TextView text_promotion = itemview.findViewById(R.id.txt_promotion);
		text_promotion.setRotation(45);

		DisplayMetrics dis = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(dis);
		int height = dis.heightPixels;
		int width = dis.widthPixels;

		image.setMinimumHeight(height);
		image.setMinimumWidth(width);

		try
		{

			Glide.with(activity.getApplicationContext())
					.load(ImagesList.get(position))
					.placeholder(R.mipmap.ic_launcher_round)
					.error(R.mipmap.ic_launcher)
					.into(image);

		} catch (Exception E)
		{
			Toast.makeText(activity, "An Error Accoured " + E, Toast.LENGTH_SHORT).show();
		}

		container.addView(itemview);
		return itemview;
	}

	@Override
	public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object)
	{
		((ViewPager) container).removeView((View) object);
	}
}
