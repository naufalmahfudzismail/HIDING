package id.ac.pnj.hirebuilding.hiding.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import id.ac.pnj.hirebuilding.hiding.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener
{

	private Button btn_login, btn_regist;
	private EditText edtUser, edtPass;
	private ProgressBar progressBar;
	private FirebaseAuth auth;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		init();
	}

	@Override
	public void onClick(View v)
	{
		if(v.getId() == R.id.button_login)
		{
			String username = edtUser.getText().toString().trim();
			final String pass = edtPass.getText().toString().trim();

			if (TextUtils.isEmpty(username))
			{
				Toast.makeText(getApplicationContext(), "Enter email /  username address!", Toast.LENGTH_SHORT).show();
				return;
			}

			if (TextUtils.isEmpty(pass) )
			{
				Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
				return;
			}

			else if (pass.length() < 8)
			{
				Toast.makeText(getApplicationContext(), "Password harus lebih dari 8 karakter", Toast.LENGTH_SHORT).show();
				return;
			}

			progressBar.setVisibility(View.VISIBLE);

			auth.signInWithEmailAndPassword(username, pass).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>()
			{
				@Override
				public void onComplete(@NonNull Task<AuthResult> task)
				{
					progressBar.setVisibility(View.GONE);
					if (!task.isSuccessful())
					{
						// there was an error
						if (pass.length() < 8)
						{
							edtPass.setError(getString(R.string.minimum_password));
						} else
						{
							Toast.makeText(LoginActivity.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
						}
					} else
					{
						Intent intent = new Intent(LoginActivity.this, MainMenuActivity.class);
						startActivity(intent);
						finish();
					}
				}
			});

		}


		if (v.getId() == R.id.button_register)
		{
			Intent intent  = new Intent(LoginActivity.this, RegisterActivity.class);
			startActivity(intent);
		}
	}

	private void init()
	{
		btn_login = findViewById(R.id.button_login);
		btn_regist = findViewById(R.id.button_register);
		edtPass = findViewById(R.id.edit_login_password);
		edtUser = findViewById(R.id.edit_login_username);
		progressBar = findViewById(R.id.progressBar);

		btn_login.setOnClickListener(this);
		btn_regist.setOnClickListener(this);

		auth = FirebaseAuth.getInstance();

		if (auth.getCurrentUser() != null) {
			startActivity(new Intent(LoginActivity.this, MainMenuActivity.class));
			finish();
		}
	}
}
