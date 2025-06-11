# 🧠 Peak Alpha Analyzer

An Android app for visualizing alpha-band (8–13 Hz) peaks from **MindMonitor** CSV files recorded with the **Muse** headband.

---

## 🎯 Features

- 🔄 **Load** a ZIP containing MindMonitor `.csv` exports  
- 🗂️ **Filter & trim**: keep only `HeadBandOn == 1`, drop first/last 10 s  
- 📈 **FFT PAF**: sliding single-window PSD (Hamming + zero-pad → peak in dB)  
- 🔬 **Welch PAF**: true Welch method—average overlapping sub-window PSDs → peak in dB  
- 📊 **Interactive charts** (MPAndroidChart)  
  - FFT chart: cyan (L) & magenta (R)  
  - Welch chart: blue (L) & red (R)  
  - Fixed Y-axis from –6 dB to +15 dB  
  - Tap any point to see “{peakDb} dB @ {time}s”  
- ⚙️ **On-screen controls** for window size, sub-window size, and overlap  
- 📝 **Numeric summary** + exact second each PAF occurs  

---

## 🛠 MindMonitor Settings

To ensure consistent and reliable data capture, configure MindMonitor as follows:

- **Record Format:** CSV  
  Ensures compatibility with data analysis tools and easy import into spreadsheets.  
- **Recording Interval:** Constant  
  Use a fixed sampling rate throughout the session to maintain uniform timing between samples.  
- **Other Settings:** Default  
  Leave filters, thresholds, output paths, etc. at their defaults unless you have a specific reason to adjust them.

These settings standardize your recordings and simplify downstream processing.

---

## 🔬 How Data Is Managed

1. **Parsing & Filtering**  
   - Read CSV with OpenCSV  
   - Keep only rows where `HeadBandOn == 1`  
2. **Timestamp Handling**  
   - Convert numeric or ISO timestamps → seconds since start  
   - Trim first/last 10 s to remove artifacts  
3. **Interpolation**  
   - Uniform resampling via Apache Commons Math `SplineInterpolator`  
   - Remove duplicate timestamps before interpolation  
4. **PAF Computation**  
   - **FFT PAF**: single-periodogram per window → PSD = |X|²/(fs·power(window)) → peak in 8–13 Hz → dB  
   - **Welch PAF**: split window into overlapping sub-windows → compute & average PSDs → peak → dB  

---

## 📈 Chart Descriptions

### FFT Chart  
- **X-axis:** time (s) at window center  
- **Y-axis:** peak PSD in dB (single-window)  
- **Series:**  
  - Left → cyan  
  - Right → magenta  

### Welch Chart  
- **X-axis:** same time axis  
- **Y-axis:** peak PSD in dB (Welch-averaged)  
- **Series:**  
  - Left → blue  
  - Right → red  

Tap datapoints to view “`{peakDb} dB @ {time}s`”.

## Demo

[![Peak Alpha Analyzer Demo](docs/demo-thumb.png)][

> Click the image to watch a quick walkthrough of the app processing Muse/MindMonitor data.
> 
## Demo Video

[![Watch the demo](https://img.youtube.com/vi/mDIf9wOj8SY/0.jpg)](https://youtube.com/shorts/mDIf9wOj8SY)
