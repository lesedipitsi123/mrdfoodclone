package apps.studios.bt.mrdfoodclone

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        deliver.setOnClickListener {
            startActivity(Intent(this, MapActivity::class.java))
        }

        btnGithub.setOnClickListener {
            val intent =
                Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/lesedipitsi123"))
            startActivity(Intent.createChooser(intent, "Github profile"))

        }
    }
}
