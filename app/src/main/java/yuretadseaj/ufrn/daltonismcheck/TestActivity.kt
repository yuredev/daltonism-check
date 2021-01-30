package yuretadseaj.ufrn.daltonismcheck

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import yuretadseaj.ufrn.daltonismcheck.databinding.ActivityTestBinding

class TestActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_test)

        when (intent.extras?.getInt("code")) {
            1 -> {
                binding.ishiharaTestImage.setImageResource(R.drawable.ishihara1)
            }
            2 -> {
                binding.ishiharaTestImage.setImageResource(R.drawable.ishihara2)
            }
            3 -> {
                binding.ishiharaTestImage.setImageResource(R.drawable.ishihara3)
            }
        }
        val intent = Intent()

        binding.apply {
            confirmTestButton.setOnClickListener {
                intent.putExtra("user-answer", userAnswerInput.text.toString().toInt())
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
            cancelTestButton.setOnClickListener {
                setResult(Activity.RESULT_CANCELED, intent)
                finish()
            }
        }
    }
}