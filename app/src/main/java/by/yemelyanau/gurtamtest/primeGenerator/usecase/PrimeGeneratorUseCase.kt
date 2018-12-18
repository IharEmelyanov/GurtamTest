package by.yemelyanau.gurtamtest.primeGenerator.usecase

import android.util.SparseBooleanArray
import java.util.*

class PrimeGeneratorUseCase {

    fun getPrimes(limit: Int): List<Int> {
        val primes = BooleanArray(limit)
        Arrays.fill(primes, true)
        primes[0] = false
        primes[1] = false
        for (i in 2 until primes.size) {
            if (primes[i]) {
                var j = 2
                while (i * j < primes.size) {
                    primes[i * j] = false
                    ++j
                }
            }
        }
        return primes.mapIndexed { index, b -> Pair(index, b) }.filter { it.second }.map { it.first }
    }

    fun getPrimes(from: Int, limit: Int): List<Int> {
        val size = limit - from

        val primes = BooleanArray(size)
        Arrays.fill(primes, true)
        for (i in from until size) {
            if (primes[i - from]) {
                var j = from
                while (i * j < size) {
                    primes[i * j] = false
                    ++j
                }
            }
        }
        return primes.mapIndexed { index, b -> Pair(index + from, b) }.filter { it.second }.map { it.first }
    }

}