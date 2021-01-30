package yuretadseaj.ufrn.daltonismcheck

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import yuretadseaj.ufrn.daltonismcheck.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var testFinalResult: Int = -1

    private val test1 = Test(1, 29)
    private val test2 = Test(2, 74)
    private val test3 = Test(3, 8)

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when(requestCode) {
            Activity.RESULT_OK-> {
                when(resultCode) {
                    test1.code-> {
                        if (data?.extras?.getInt("user-answer") == test1.answer) {
                            testFinalResult++
                        }
                    }
                    test2.code->{
                        if (data?.extras?.getInt("user-answer") == test2.answer) {
                            testFinalResult++
                        }
                    }
                    test3.code->{
                        if (data?.extras?.getInt("user-answer") == test2.answer) {
                            testFinalResult++
                        }
                    }
                }
            }
            Activity.RESULT_CANCELED-> {
                Toast.makeText(this, "Teste cancelado", Toast.LENGTH_SHORT).show()
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        inicializar o binding
//        basta passar a activity e o layout
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val intent = Intent(this, TestActivity::class.java)

        binding.buttonToTest1.setOnClickListener {
            startActivityForResult(intent, test1.code)
        }
        binding.buttonToTest2.setOnClickListener {
            startActivityForResult(intent, test2.code)
        }
        binding.buttonToTest2.setOnClickListener {
            startActivityForResult(intent, test3.code)
        }

    }
}