package id.ac.pnj.hirebuilding.hiding.Class;

public class Ruangan
{

	private String nama_ruangan;
	private int kapasitas;
	private String fasilitas;
	private String desc;
	private String alamat;
	private float harga;
	private String kontak;
	private String gambar;
	private String status;
	private int kode_ruangan;


	public int getKode_ruangan()
	{
		return kode_ruangan;
	}

	public void setKode_ruangan(int kode_ruangan)
	{
		this.kode_ruangan = kode_ruangan;
	}


	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public Ruangan(String nama_ruangan, String gambar, String desc)
	{
		this.nama_ruangan = nama_ruangan;
		this.gambar = gambar;
		this.desc = desc;
	}

	public Ruangan(String nama_ruangan, int kapasitas, String fasilitas, String desc, String alamat, float harga, String kontak, String gambar)
	{
		this.nama_ruangan = nama_ruangan;
		this.kapasitas = kapasitas;
		this.fasilitas = fasilitas;
		this.desc = desc;
		this.alamat = alamat;
		this.harga = harga;
		this.kontak = kontak;
		this.gambar = gambar;
	}

	public String getgambar()
	{
		return gambar;
	}

	public void setgambar(String gambar)
	{
		this.gambar = gambar;
	}

	public Ruangan()
	{

	}

	public Ruangan(String nama_ruangan, int kapasitas, String fasilitas, String desc, String alamat, float harga, String kontak, String gambar, String status)
	{
		this.nama_ruangan = nama_ruangan;
		this.kapasitas = kapasitas;
		this.fasilitas = fasilitas;
		this.desc = desc;
		this.alamat = alamat;
		this.harga = harga;
		this.kontak = kontak;
		this.gambar = gambar;
		this.status = status;
	}

	public String getnama_ruangan()
	{
		return nama_ruangan;
	}

	public void setnama_ruangan(String nama_ruangan)
	{
		this.nama_ruangan = nama_ruangan;
	}

	public int getkapasitas()
	{
		return kapasitas;
	}

	public void setkapasitas(int kapasitas)
	{
		this.kapasitas = kapasitas;
	}

	public String getfasilitas()
	{
		return fasilitas;
	}

	public void setfasilitas(String fasilitas)
	{
		this.fasilitas = fasilitas;
	}

	public String getdesc()
	{
		return desc;
	}

	public void setdesc(String desc)
	{
		this.desc = desc;
	}

	public String getalamat()
	{
		return alamat;
	}

	public void setalamat(String alamat)
	{
		this.alamat = alamat;
	}

	public float getharga()
	{
		return harga;
	}

	public void setharga(float harga)
	{
		this.harga = harga;
	}

	public String getkontak()
	{
		return kontak;
	}

	public void setkontak(String kontak)
	{
		this.kontak = kontak;
	}
}
