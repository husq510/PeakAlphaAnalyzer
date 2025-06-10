//PafResult.kt
package com.example.peakalphaanalyzer

data class PafResult(
    val fs: Double,
    val duration: Double,
    val pafL: Double,
    val pafR: Double,
    val pafMean: Double,
    val pafMedian: Double,
    val timeL: Double,
    val timeR: Double,
    val welchL: Double,
    val welchR: Double,
    val welchMean: Double,
    val welchMedian: Double,
    // Aggiunti per il plot
    val rawLeft: DoubleArray,
    val rawRight: DoubleArray
) {
    fun format(): String = buildString {
        append("Fs = $fs Hz\nDurata = $duration s\n")
        append(String.format("PAF originale: L=%.1f Hz, R=%.1f Hz, mean=%.1f Hz\n", pafL, pafR, pafMean))
        append(String.format("PAF Welch:     L=%.1f Hz, R=%.1f Hz, mean=%.1f Hz", welchL, welchR, welchMean))
    }
}