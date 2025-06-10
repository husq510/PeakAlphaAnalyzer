package com.example.peakalphaanalyzer

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.utils.MPPointF

/**
 * MarkerView personalizzato che mostra "Y dB @ X s" al tap sul punto.
 * Deve avere entrambi i costruttori per poter essere inflate-ato da XML.
 */
class MyMarkerView : MarkerView {

    private lateinit var tv: TextView

    // 1) costruttore chiamato da codice
    constructor(context: Context) : super(context, R.layout.marker_view) {
        init()
    }

    // 2) costruttore chiamato dal LayoutInflater (deve accettare AttributeSet)
    constructor(context: Context, attrs: AttributeSet) : super(context, R.layout.marker_view) {
        init()
    }

    private fun init() {
        tv = findViewById(R.id.marker_text)
    }

    override fun refreshContent(e: Entry, highlight: Highlight) {
        // mostra Y dB @ X s
        tv.text = String.format("%.1f dB @ %.2f s", e.y, e.x)
        super.refreshContent(e, highlight)
    }

    override fun getOffset(): MPPointF {
        // centra orizzontalmente e posiziona sopra il punto
        return MPPointF(-(width / 2f), -height.toFloat())
    }
}