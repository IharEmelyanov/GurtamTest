package by.yemelyanau.gurtamtest.primeGenerator.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import by.yemelyanau.gurtamtest.R
import kotlinx.android.synthetic.main.activity_prime_generator.*

class PrimeGeneratorActivity : AppCompatActivity() {

    private lateinit var viewModel: PrimeGeneratorViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prime_generator)
        viewModel = ViewModelProviders.of(this).get(PrimeGeneratorViewModel::class.java)

        edLimit.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(!s.isNullOrBlank()) {
                    viewModel.onUpperLimitChanged(s.toString())
                }
            }

        })

        btnGenerate.setOnClickListener(){ viewModel.onGenerateButtonClick()}


        viewModel.wrongUpperLimitFormat.observe(this, Observer { errorMessageEvent ->
            errorMessageEvent?.let {
                Toast.makeText(this, getString(R.string.wrong_upper_limit_format), Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.isGeneratingStateChangeEvent.observe(this, Observer { isGeneratingStateChangeEvent ->
            isGeneratingStateChangeEvent?.let {
                btnGenerate.text = getString( if(it) R.string.generate else R.string.stop )
            }
        })

        viewModel.primes.observe(this, Observer { primes ->
            primes?.let {
                tvResult.text = it.joinToString("  ")
            }
        })
    }
}
