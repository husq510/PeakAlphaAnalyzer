# Peak Alpha Analyzer

An Android app for visualizing alpha-band (8–13 Hz) peaks from **MindMonitor** CSV files recorded with the **Muse** headband.  
It computes both a **single-window PSD** (FFT) and a **Welch-averaged PSD**, then plots the peak power over time for left & right channels.

---

## 🎯 Features

- **Load** a ZIP containing one or more MindMonitor `.csv` files.
- **Filter** only segments where `HeadBandOn == 1`.
- **Trim** the first and last 10 s to remove startup/shutdown artifacts.
- **Interpolate** timestamps & raw channels to a uniform sampling rate (spline).
- **Compute**  
  1. **FFT PAF**: single-window periodogram peak (sliding window)  
  2. **Welch PAF**: average of overlapping sub-periodograms (true Welch)  
- **Plot** two synchronized LineCharts:  
  - **FFT chart**: sliding single-window PSD peaks (L=cyan, R=magenta)  
  - **Welch chart**: averaged PSD peaks (L=blue, R=red)  
- **Interactive**: tap any point to see “\{dB\} @ \{time\} s” with a custom marker.
- **Configurable**: window size, sub-window size, overlap via on-screen controls.
- **Fixed Y-axis** from –6 dB to +15 dB so the two charts are directly comparable.

---

## MindMonitor settings

To ensure consistent and reliable data capture when using MindMonitor in this project, please configure the following settings:
Record Format: CSV
Ensures compatibility with data analysis tools and easy importing into spreadsheets.

Recording Interval: Constant
Use a fixed sampling rate throughout the session to maintain uniform timing between samples.

Other Settings: Default
All remaining options (e.g., filters, thresholds, output paths) should be left at their default values unless you have a specific reason to adjust them.
These settings will help standardize your recordings and simplify downstream processing of MindMonitor data.


## 🔬 How data is managed

1. **Parsing & filtering**  
   - Read the MindMonitor CSV with [OpenCSV].  
   - Keep only rows where column `HeadBandOn == 1`.  
2. **Timestamp handling**  
   - Convert either numeric or ISO timestamps into seconds relative to the first sample.  
   - Drop the first/last 10 s of data.  
3. **Spline interpolation**  
   - Use Apache Commons Math `SplineInterpolator` on left/right raw channels to ensure uniform sampling.  
4. **Peak Alpha Frequency (PAF)**  
   - **FFT PAF**:  
     - For each sliding window: apply Hamming, pad to next power of 2, compute FFT, build PSD = |X|²/(fs·power(window)), find max in 8–13 Hz, convert to dB.  
   - **Welch PAF**:  
     - For each sliding window: break into overlapping **sub-windows**, apply Hamming + FFT + PSD, average PSDs, find the same 8–13 Hz peak, convert to dB.  
5. **Result**  
   - Display numeric results (fs, durations, PAF left/right, means/medians) in a text view.  
   - Plot two LineCharts with shared time axis and fixed –6 to +15 dB range.

---

## 📈 Chart descriptions

### FFT chart  
- **X-axis**: time (s) at center of each window  
- **Y-axis**: peak PSD in dB (single-periodogram)  
- **Series**:  
  - Left channel → cyan line  
  - Right channel → magenta line  

### Welch chart  
- **X-axis**: same time axis  
- **Y-axis**: peak PSD in dB (Welch-averaged)  
- **Series**:  
  - Left channel → blue line  
  - Right channel → red line  

Tap any datapoint to see “`{peakDb} dB @ {time}s`”.

## Demo

[![Peak Alpha Analyzer Demo](docs/demo-thumb.png)][

> Click the image to watch a quick walkthrough of the app processing Muse/MindMonitor data.
> 
## Demo Video

[![Watch the demo](https://img.youtube.com/vi/mDIf9wOj8SY/0.jpg)](https://youtube.com/shorts/mDIf9wOj8SY)


Tap any datapoint to see “`{peakDb} dB @ {time}s`”.

## Demo

[![Peak Alpha Analyzer Demo](docs/demo-thumb.png)][

> Click the image to watch a quick walkthrough of the app processing Muse/MindMonitor data.
> 
## Demo Video

[![Watch the demo](https://img.youtube.com/vi/mDIf9wOj8SY/0.jpg)](https://youtube.com/shorts/mDIf9wOj8SY)
