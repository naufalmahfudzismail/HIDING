package id.ac.pnj.hirebuilding.hiding.Class;

public class Penyewaan
{

	private static String nama_customer;
	private static int lama_sewa;
	private static String tanggal;
	private static String Lokasi;


	public static String getNama_customer()
	{
		return nama_customer;
	}

	public static void setNama_customer(String nama_customer)
	{
		Penyewaan.nama_customer = nama_customer;
	}

	public static int getLama_sewa()
	{
		return lama_sewa;
	}

	public static void setLama_sewa(int lama_sewa)
	{
		Penyewaan.lama_sewa = lama_sewa;
	}

	public static String getTanggal()
	{
		return tanggal;
	}

	public static void setTanggal(String tanggal)
	{
		Penyewaan.tanggal = tanggal;
	}

	public static String getLokasi()
	{
		return Lokasi;
	}

	public static void setLokasi(String lokasi)
	{
		Lokasi = lokasi;
	}
}
