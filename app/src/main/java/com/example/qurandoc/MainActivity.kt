package com.example.qurandoc

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {


    // val unicodeChar = 0xFDF0


    val unicodeCharSeen = 0x06DC
    val unicodeCharTripleStope = 0x06DB
    val unicodeCharGem = 0x06DA
    val unicodeCharLam =  0x06D9
    val unicodeCharMem = 0x06D8
    val unicodeCharQal = 0xFDF1
    val unicodeCharSal = 0xFDF0


    val unicodeCharTah =0x0615



    val uniCode =0x06EC
    //0x06DA //
    lateinit var text: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        text.text = " بسم الله الرحمن الرحيم ${uniCode.toChar()} صدق الله العظيم "
    }
}