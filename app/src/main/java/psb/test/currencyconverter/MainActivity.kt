package psb.test.currencyconverter

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import psb.test.currencyconverter.remote.CBService

class MainActivity : AppCompatActivity(R.layout.activity_main) {}
