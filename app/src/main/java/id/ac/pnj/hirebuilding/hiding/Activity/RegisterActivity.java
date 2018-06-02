package id.ac.pnj.hirebuilding.hiding.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import id.ac.pnj.hirebuilding.hiding.Class.User;
import id.ac.pnj.hirebuilding.hiding.R;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener
{

	private EditText edtResPass, edtResEmail, edtResNama, edtResTelp, edtResUmur, edtResLokasi;
	private Button btnRes;
	private ProgressBar progress;
	private FirebaseAuth authRegis;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		init();
	}

	@Override
	public void onClick(View v)
	{
		if (v.getId() == R.id.btnRegister)
		{
			register();
		}

	}

	private void init()
	{
		edtResPass = findViewById(R.id.edit_password_registrasi);
		edtResEmail = findViewById(R.id.edit_email_registrasi);
		edtResNama = findViewById(R.id.edit_nama_registrasi);
		edtResLokasi = findViewById(R.id.edit_alamat_registrasi);
		edtResTelp = findViewById(R.id.edit_telpon_regiatrasi);
		edtResUmur = findViewById(R.id.edit_umur_registrasi);
		btnRes = findViewById(R.id.btnRegister);
		progress = findViewById(R.id.progress_regis);
		btnRes.setOnClickListener(this);
		authRegis = FirebaseAuth.getInstance();
	}

	@Override
	protected void onStart()
	{
		super.onStart();

		if (authRegis.getCurrentUser() != null)
		{

			startActivity(new Intent(RegisterActivity.this, MainMenuActivity.class));
		}
	}

	private void register()
	{
		String pass = edtResPass.getText().toString().trim();
		final String Email = edtResEmail.getText().toString().trim();
		final String nama = edtResNama.getText().toString().trim();
		final String umur = edtResUmur.getText().toString().trim();
		final String telp = edtResTelp.getText().toString().trim();
		final String lokasi = edtResLokasi.getText().toString().trim();

		if (nama.isEmpty())
		{
			edtResNama.setError(getString(R.string.input_error_name));
			edtResNama.requestFocus();
			return;
		}

		if (Email.isEmpty())
		{
			edtResEmail.setError(getString(R.string.input_error_email));
			edtResEmail.requestFocus();
			return;
		}

		if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches())
		{
			edtResEmail.setError(getString(R.string.input_error_email_invalid));
			edtResEmail.requestFocus();
			return;
		}

		if (pass.isEmpty())
		{
			edtResPass.setError(getString(R.string.input_error_password));
			edtResPass.requestFocus();
			return;
		}

		if (pass.length() < 8)
		{
			edtResPass.setError(getString(R.string.input_error_password_length));
			edtResPass.requestFocus();
			return;
		}

		if (telp.isEmpty())
		{
			edtResTelp.setError(getString(R.string.input_error_phone));
			edtResTelp.requestFocus();
			return;
		}

		if (telp.length() != 12)
		{
			edtResTelp.setError(getString(R.string.input_error_phone_invalid));
			edtResTelp.requestFocus();
			return;
		}

		if (umur.isEmpty())
		{
			edtResUmur.setError(getString(R.string.input_umur_error));
			edtResUmur.requestFocus();
			return;
		}

		if (lokasi.isEmpty())
		{
			edtResLokasi.setError(getString(R.string.input_lokasi_error));
			edtResLokasi.requestFocus();
		}

		progress.setVisibility(View.VISIBLE);

		authRegis.createUserWithEmailAndPassword(Email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>()
		{
			@Override
			public void onComplete(@NonNull Task<AuthResult> task)
			{
				if (task.isSuccessful())
				{
					User user = new User(nama, Email, lokasi, telp, umur);

					FirebaseDatabase.getInstance().getReference("Users")
							.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
							.setValue(user).addOnCompleteListener(new OnCompleteListener<Void>()
					{
						@Override
						public void onComplete(@NonNull Task<Void> task)
						{
							progress.setVisibility(View.GONE);
							if (task.isSuccessful())
							{
								sendVerificationEmail();

							} else
							{
								Toast.makeText(RegisterActivity.this, "Pendaftaran gagal", Toast.LENGTH_LONG).show();
							}
						}
					});

				} else
				{
					Toast.makeText(RegisterActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
				}
			}
		});

	}

	private void sendVerificationEmail()
	{
		FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
		user.sendEmailVerification()
				.addOnCompleteListener(new OnCompleteListener<Void>()
				{
					@Override
					public void onComplete(@NonNull Task<Void> task)
					{
						if (task.isSuccessful())
						{
							// email sent
							// after email is sent just logout the user and finish this activity
							authRegis.signOut();
							Toast.makeText(RegisterActivity.this, getString(R.string.registration_success), Toast.LENGTH_LONG).show();
							startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
							finish();
						}
						else
						{
							// email not sent, so display message and restart the activity or do whatever you wish to do

							//restart this activity
							overridePendingTransition(0, 0);
							finish();
							overridePendingTransition(0, 0);
							startActivity(getIntent());

						}
					}
				});
	}
}
