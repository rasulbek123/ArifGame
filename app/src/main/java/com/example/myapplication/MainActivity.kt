package com.example.myapplication

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.random.Random
import kotlin.random.Random.Default.nextInt
import kotlin.random.nextInt
import android.content.Intent as Intent

@Suppress("UNREACHABLE_CODE")
class MainActivity : AppCompatActivity() {
    private var firstNumber = 0
    private var secondNumber = 0
    private var operator = ""
    private var etap = 0
    private var ball = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        nextStep()
    }

    override fun onResume() {
        super.onResume()
        nextStep()
    }
        fun nextStep(){
            operator =Operator()
            firstNumber = getValueNumber(1,100)
            secondNumber = getValueNumber(1,100)
            if(operator == "/"){
                firstNumber = secondNumber*getValueNumber(1,20)
            }
            text1.text = firstNumber.toString()
            text3.text = secondNumber.toString()
            text2.text = operator
            DurisAnswer()
            QateAnswer()
        }

        fun onClick(buttom: View){
        var intent = Intent(this,MainActivity2::class.java)
            if((buttom as Button).text == DurisAnswer().toString()){
                intent.putExtra("result","Duris")
            }else{
                intent.putExtra("result","Qate")
            }
            startActivity(intent)
       }
    private fun getValueNumber(start:Int,end:Int):Int{
        return Random.nextInt(start, end)
    }
    private fun Operator():String{
        return when(getValueNumber(0,4)){
            0 -> "+"
            1 -> "-"
            2 -> "*"
            3 -> "/"
            else -> "+"
        }
    }

    private fun DurisAnswer():Int{
        var Answer = when(operator){
            "+" -> firstNumber+secondNumber
            "-" -> firstNumber-secondNumber
            "*" -> firstNumber*secondNumber
            "/" -> firstNumber/secondNumber
            else -> firstNumber+secondNumber
        }
        return Answer
    }
    private fun QateAnswer(){
        val Answer = DurisAnswer()
        val set = mutableSetOf<Int>()
        while (set.size < 4){
            set.add(if(Random.nextBoolean()){
                Answer-getValueNumber(2,10)
            }else{
                Answer+getValueNumber(2,10)
            })
        }
        val a = set.toList()
        buttom1.text = a[0].toString()
        buttom2.text = a[1].toString()
        buttom3.text = a[2].toString()
        buttom4.text = a[3].toString()
        when(getValueNumber(0,4)){
            0->buttom1.text = Answer.toString()
            1->buttom2.text = Answer.toString()
            2->buttom3.text = Answer.toString()
            3 ->buttom4.text = Answer.toString()
            else -> buttom1.text = Answer.toString()
        }
    }
}
