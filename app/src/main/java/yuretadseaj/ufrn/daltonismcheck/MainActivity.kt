package yuretadseaj.ufrn.daltonismcheck

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import yuretadseaj.ufrn.daltonismcheck.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var testFinalResult: Int = 0

    private val test1 = Test(1, 29)
    private val test2 = Test(2, 74)
    private val test3 = Test(3, 8)

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (resultCode) {
            Activity.RESULT_OK -> {
                when (requestCode) {
                    test1.code -> {
                        test1.userAnswer = data?.extras?.getInt("user-answer")
                        binding.answer1TextView.text = test1.userAnswer.toString()
                        binding.buttonToTest1.isEnabled = false
                    }
                    test2.code -> {
                        test2.userAnswer = data?.extras?.getInt("user-answer")
                        binding.answer2TextView.text = test2.userAnswer.toString()
                        binding.buttonToTest2.isEnabled = false
                    }
                    test3.code -> {
                        test3.userAnswer = data?.extras?.getInt("user-answer")
                        binding.answer3TextView.text = test3.userAnswer.toString()
                        binding.buttonToTest3.isEnabled = false
                    }
                }
            }
            Activity.RESULT_CANCELED -> {
                Toast.makeText(this, "Teste $requestCode cancelado", Toast.LENGTH_SHORT).show()
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun defineNavigator(button: Button, test: Test) {
        val intent = Intent(this, TestActivity::class.java)
        intent.putExtra("code", test.code)
        intent.putExtra("answer", test.answer)
        button.setOnClickListener { startActivityForResult(intent, test.code) }
    }

    private fun isDaltonic(vararg tests: Test): Boolean {
        for (test in tests) {
            if (test.answer != test.userAnswer) {
                return true
            }
        }
        return false
    }

    private fun testsAreFinished(): Boolean {
        return test1.userAnswer != -1 && test2.userAnswer != -1 && test3.userAnswer != -1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        inicializar o binding
//        basta passar a activity e o layout
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.apply {
            defineNavigator(buttonToTest1, test1)
            defineNavigator(buttonToTest2, test2)
            defineNavigator(buttonToTest3, test3)

            buttonResult.setOnClickListener {
                if (testsAreFinished()) {
                    if (isDaltonic(test1, test2, test3)) {
                        resultTextView.text = "Daltonismo detectado"
                    } else {
                        resultTextView.text = "Visão normal"
                    }
                } else {
                    Toast.makeText(this@MainActivity, "Por favor faça todos os testes", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}