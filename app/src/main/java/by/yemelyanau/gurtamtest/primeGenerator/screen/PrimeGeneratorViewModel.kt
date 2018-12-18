package by.yemelyanau.gurtamtest.primeGenerator.screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import by.yemelyanau.gurtamtest.primeGenerator.usecase.PrimeGeneratorUseCase

class PrimeGeneratorViewModel: ViewModel() {

    private val primeGeneratorUseCase: PrimeGeneratorUseCase = PrimeGeneratorUseCase()

    private var upperLimit: Int = 0
    private var isGeneratingRunning: Boolean = false

    private val _wrongUpperLimitFormat: MutableLiveData<Unit> = MutableLiveData()
    val wrongUpperLimitFormat: LiveData<Unit>
        get() = _wrongUpperLimitFormat

    private val _isGeneratingStateChangeEvent: MutableLiveData<Boolean> = MutableLiveData()
    val isGeneratingStateChangeEvent: LiveData<Boolean>
        get() = _isGeneratingStateChangeEvent

    private val _primes: MutableLiveData<List<Int>> = MutableLiveData()
    val primes: LiveData<List<Int>>
        get() = _primes


    fun onUpperLimitChanged(upperLimit: String) {
        try {
            this.upperLimit = upperLimit.toInt()
        } catch (ex: NumberFormatException) {
            _wrongUpperLimitFormat.value = null
        }
    }

    fun onGenerateButtonClick() {
        if (upperLimit > 0) {
            _isGeneratingStateChangeEvent.value = isGeneratingRunning
            isGeneratingRunning = !isGeneratingRunning
            if (isGeneratingRunning) {
                runPrimeGenerating()
            } else {
                stopPrimeGenerating()
            }
        }
    }

    private fun runPrimeGenerating() {
        _primes.value = primeGeneratorUseCase.getPrimes(2, upperLimit)
        onGenerateButtonClick()
    }

    private fun stopPrimeGenerating() {

    }

}