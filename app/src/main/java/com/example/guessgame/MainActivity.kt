package com.example.guessgame

import android.content.Intent
import android.content.SharedPreferences
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.widget.*

class MainActivity : AppCompatActivity() {

    var points: Int = 0 //points set to Zero
    var count: Int = 0 //Question count set to Zero
    var size: Int = 1 //size set to one


    //Array
    //1. Array carries the image
    val images = arrayOf(
        R.drawable.pap,
        R.drawable.boy,
        R.drawable.den,
        R.drawable.te,
        R.drawable.khab,
        R.drawable.mosala
    )
    // Will Display images in the order displayed dynamically

    //2. Array carries the Questions
    val questions = arrayOf(
        "Who is the G.O.A.T?",
        "Which concert would you go to?",
        "I Know what you want to talk about?",
        "Who is she?",
        "Very Simple",
        "You know football?"
    )

    //3. Array carries the answers
    val answers = arrayOf("khaligraph", "boy", "den", "te", "khaby", "mosala")


    //After Arrays we create the views
    lateinit var question: TextView
    lateinit var img: ImageView
    lateinit var answer: EditText
    lateinit var timer: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Find them from Xml - check activity_main.xml
        img = findViewById(R.id.img1)
        question = findViewById(R.id.question)
        answer = findViewById(R.id.answer)
        timer = findViewById(R.id.timer)

        //Defined the variable type as media player.. created a media player getting from raw directory
        val soundplayer: MediaPlayer = MediaPlayer.create(this,R.raw.song)
        //start the sound
        soundplayer.start()
        //Loop the sound until the game ends
        soundplayer.isLooping = true

        //Timer: Countdown Function.....
            //The Ticker which displays the countdown
        object:CountDownTimer(10000,1000){
            //Gives Us the countdown
            override fun onTick(millisUntilFinished: Long){
                timer.setText("Time remaining: " + millisUntilFinished/1000)
            }

            //when time is up SetText Time up and load game over activity
            override fun onFinish() {
                timer.setText("Your Time Is Up")
                finish()
                soundplayer.stop()
                //display points
                val marks = points.toString()
                val index = Intent(applicationContext, done::class.java).apply {
                    putExtra(EXTRA_MESSAGE,marks)
                }
                startActivity(index)


            }
        }.start()





        //set the first question - array at 0
        //This loads the first question and image

        img.setImageResource(images[count])
        question.text = questions[count]







//The Logic behind the functionality
        val check = findViewById<Button>(R.id.check)
        check.setOnClickListener {

            if (size <= 5) {


                println("Click:" + count)

                if (count == 5) {
                    val index = Intent(applicationContext, done::class.java)
                    startActivity(index)
                } else {
                    if (answer.text.toString() == answers[count]) {
                        points + 2
                        count++
                        img.setImageResource(images[count])
                        question.text = questions[count]
                        Toast.makeText(applicationContext, "Right", Toast.LENGTH_LONG).show()
                        answer.text.toString().equals("")
                    } else {
                        points + 0
                        count++
                        img.setImageResource(images[count])
                        Toast.makeText(applicationContext, "Wrong", Toast.LENGTH_LONG).show()
                        answer.text.toString().equals("")

                    }


                }

            }
        }
    }
}