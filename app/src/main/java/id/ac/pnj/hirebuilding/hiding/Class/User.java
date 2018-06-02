package id.ac.pnj.hirebuilding.hiding.Class;

public class User
{


	private  String nama;
	private  String email;
	private  String alamat;
	private  String no_telp;
	private  String umur;

	public User()
	{

	}

	public User(String nama, String email, String alamat, String no_telp, String umur)
	{
		this.nama = nama;
		this.email = email;
		this.alamat = alamat;
		this.no_telp = no_telp;
		this.umur = umur;
	}


	public  String getNama()
	{
		return nama;
	}

	public  void setNama(String nama)
	{
		this.nama = nama;
	}

	public  String getEmail()
	{
		return email;
	}

	public  void setEmail(String email)
	{
		this.email = email;
	}

	public  String getAlamat()
	{
		return alamat;
	}

	public  void setAlamat(String alamat)
	{
		this.alamat = alamat;
	}

	public  String getNo_telp()
	{
		return no_telp;
	}

	public  void setNo_telp(String no_telp)
	{
		this.no_telp = no_telp;
	}

	public  String getUmur()
	{
		return umur;
	}

	public  void setUmur(String umur)
	{
		this.umur = umur;
	}



}
