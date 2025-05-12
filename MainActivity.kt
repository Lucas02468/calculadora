package com.example.calculadoracientifica

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.calculadoracientifica.R.*
import kotlin.math.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var visor: EditText
        var entrada = ""
        var resultado = 0.0
        var operador = ""
        var novaEntrada = true
        var radianos = true
        var inverso = false
        var ultimaResposta = 0.0



        val display = findViewById<TextView>(id.btndisplay)
        val btn1 = findViewById<Button>(id.btn1)
        val btn2 = findViewById<Button>(id.btn2)
        val btn3 = findViewById<Button>(id.btn3)
        val btn4 = findViewById<Button>(id.btn4)
        val btn5 = findViewById<Button>(id.btn5)
        val btn6 = findViewById<Button>(id.btn6)
        val btn7 = findViewById<Button>(id.btn7)
        val btn8 = findViewById<Button>(id.btn8)
        val btn9 = findViewById<Button>(id.btn9)
        val btn0 = findViewById<Button>(id.btn0)
        val btnMultiplicar = findViewById<Button>(id.btnMultiplicar)
        val btnMais = findViewById<Button>(id.btnMais)
        val btnMenos = findViewById<Button>(id.btnMenos)
        val btnDividir = findViewById<Button>(id.btnDividir)
        val btnPonto = findViewById<Button>(id.btnPonto)
        val btnIgual = findViewById<Button>(id.btnIgual)
        val btnAbre = findViewById<Button>(id.btnAbre)
        val btnFecha = findViewById<Button>(id.btnFecha)
        val btnPorcento = findViewById<Button>(id.btnPorcento)
        val btnCE = findViewById<Button>(id.btnCE)
        val btnRad = findViewById<Button>(id.btnRad)
        val btnDeg = findViewById<Button>(id.btnDeg)
        val btnX = findViewById<Button>(id.btnX)
        val btnLog = findViewById<Button>(id.btnLog)
        val btnInv = findViewById<Button>(id.btnInv)
        val btnSin = findViewById<Button>(id.btnSin)
        val btnIn = findViewById<Button>(id.btnIn)
        val btnTan = findViewById<Button>(id.btnTan)
        val btnRaiz = findViewById<Button>(id.btnRaiz)
        val btnPi = findViewById<Button>(id.btnPI)
        val btnCos = findViewById<Button>(id.btnCos)
        val btnAns = findViewById<Button>(id.btnAns)
        val btnExp = findViewById<Button>(id.btnEXP)
        val btnXy = findViewById<Button>(id.btnXy)
        val btnE = findViewById<Button>(id.btnE)


        btn0.setOnClickListener {
            if (display.text.toString() != "0") {
                display.text = display.text.toString() + "0"
            }
        }
        btn1.setOnClickListener {
            if (display.text.toString() == "0") {
                display.text = "1"
            } else {
                display.append("1")
            }
        }

        btn2.setOnClickListener {
            if (display.text.toString() == "0") {
                display.text = "2"
            } else {
                display.append("2")
            }
        }

        btn3.setOnClickListener {
            if (display.text.toString() == "0") {
                display.text = "3"
            } else {
                display.append("3")
            }
        }

        btn4.setOnClickListener {
            if (display.text.toString() == "0") {
                display.text = "4"
            } else {
                display.append("4")
            }
        }

        btn5.setOnClickListener {
            if (display.text.toString() == "0") {
                display.text = "5"
            } else {
                display.append("5")
            }
        }

        btn6.setOnClickListener {
            if (display.text.toString() == "0") {
                display.text = "6"
            } else {
                display.append("6")
            }
        }

        btn7.setOnClickListener {
            if (display.text.toString() == "0") {
                display.text = "7"
            } else {
                display.append("7")
            }
        }

        btn8.setOnClickListener {
            if (display.text.toString() == "0") {
                display.text = "8"
            } else {
                display.append("8")
            }
        }

        btn9.setOnClickListener {
            if (display.text.toString() == "0") {
                display.text = "9"
            } else {
                display.append("9")
            }
        }

        btnCE.setOnClickListener {
            display.text = "0"
        }

        btnAbre.setOnClickListener {
            if (display.text.toString() == "0") {
                display.text = "("
            } else {
                display.append("(")
            }
        }

        btnFecha.setOnClickListener {
            display.append(")")
        }

        btnPorcento.setOnClickListener {
            display.append("%")
        }


        fun adicionarDigito(digito: String) {
            entrada = if (novaEntrada) digito else entrada + digito
            novaEntrada = false
            display.setText(entrada)
        }

        fun definirOperador(op: String) {
            if (entrada.isNotEmpty()) resultado = entrada.toDouble()
            operador = op
            novaEntrada = true
        }

        fun calcular() {
            if (operador.isNotEmpty() && !novaEntrada) {
                val valor = entrada.toDouble()
                resultado = when (operador) {
                    "+" -> resultado + valor
                    "-" -> resultado - valor
                    "×" -> resultado * valor
                    "÷" -> if (valor != 0.0) resultado / valor else Double.NaN
                    "%" -> resultado * valor / 100
                    "^" -> resultado.pow(valor)
                    else -> valor
                }
                entrada = if (resultado.isNaN()) "Erro" else resultado.toString()
                ultimaResposta = resultado
                display.setText(entrada)
                operador = ""
            }
        }

        fun limparEntrada() {
            entrada = ""
            display.setText("")
            novaEntrada = true
        }


        fun usarUltimaResposta() {
            entrada = ultimaResposta.toString()
            display.setText(entrada)
            novaEntrada = false
        }

        fun alternarModoAngulo() {
            radianos = !radianos
            findViewById<Button>(R.id.btnRad).text = if (radianos) "rad" else "deg"
        }

        fun calcularTrigonometrica(funcao: (Double) -> Double) {
            if (entrada.isNotEmpty()) {
                var valor = entrada.toDouble()
                if (!radianos && !inverso) valor = Math.toRadians(valor)
                resultado = if (inverso) when (funcao) {
                    ::sin -> asin(valor)
                    ::cos -> acos(valor)
                    else -> atan(valor)
                } else funcao(valor)
                if (inverso && !radianos) resultado = Math.toDegrees(resultado)
                entrada = resultado.toString()
                display.setText(entrada)
                novaEntrada = true
                inverso = false
            }
        }

        fun calcularLogaritmo() {
            if (entrada.isNotEmpty()) {
                resultado = if (inverso) 10.0.pow(entrada.toDouble()) else log10(entrada.toDouble())
                entrada = resultado.toString()
                display.setText(entrada)
                novaEntrada = true
            }
        }

        fun adicionarNotacaoCientifica() {
            entrada = if (entrada.isEmpty()) "1E" else entrada + "E"
            display.setText(entrada)
            novaEntrada = false
        }
    }
}














