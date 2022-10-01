package com.example.healthify

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.healthify.databinding.ActivityWorkoutBinding
import com.example.healthify.model.WORK_ID_EXTRA
import kotlinx.android.synthetic.main.activity_calculator.bottom_nav
import kotlinx.android.synthetic.main.activity_main.switchbut


class WorkoutMain : AppCompatActivity(), OnWorkoutClickListener {

    private lateinit var binding: ActivityWorkoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWorkoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()

        switchbut.setOnClickListener {
            val name = intent.getStringExtra("username")
            val intent = Intent(this, WorkoutMainAct2::class.java)
            intent.putExtra("username",name)
            startActivity(intent)
        }

        bottom_nav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.homefragment -> {
                    home()

                }

                R.id.tools -> {
                    tools()


                }

                R.id.workout -> {
                    workout()

                }

                R.id.foodplan -> {
                    foodplan()

                }

                R.id.userfragment -> {
                    usersetting()

                }


            }
            true
        }

    }

    override fun onResume() {


        binding.rView.apply {
            layoutManager = GridLayoutManager(applicationContext, 2)
            adapter = WorkoutAdapter(workoutList, this@WorkoutMain)
        }


        populateWorkouts()
        populateWorkoutsDetails()
        super.onResume()
    }

    private fun populateWorkoutsDetails() {
        val workD1 = WorkoutDataDetails(
            R.drawable.pushups,
            getString(R.string.Pushups),
            "Get on the floor on all fours, positioning your hands slightly wider than your shoulders. Don't lock out the elbows; keep them slightly bent. Extend your legs back so you are balanced on your hands and toes, your feet hip-width apart.\n" +
                    "\n" +
                    "Contract your abs and tighten your core by pulling your belly button toward your spine. \n" +
                    "Inhale as you slowly bend your elbows and lower yourself to the floor, until your elbows are at a 90-degree angle.\n" +
                    "Exhale while contracting your chest muscles and pushing back up through your hands, returning to the start position. ",
            "https://www.youtube.com/watch?v=IODxDxX7oi4"
        )
    workoutDetails.add(workD1)

        val workD2 = WorkoutDataDetails(
            R.drawable.situp,
            "Situps",
            "Introducing sit ups to your workouts is one way to help boost your core strength. It can also help improve your digestion, as the motion strengthens the abdominal muscles that allow the intestines to function correctly. This ab exercise can be performed with using nothing more than your own bodyweight which means you can do it anywhere, whether that in or out the gym.  \n" +
                    "\n" +
                    "One of the many reasons sit ups are ever-popular is that they're easy to modify (e.g. doing them on an incline or while holding weights) so you can continue to challenge yourself as your core strength improves.\n" +
                    "\n" +
                    "To perform the perfect sit up keep your feet, hips and knees aligned with knees bent and feet flat on the floor, and exhale on your way up and inhale as you return to the start position.\n" +
                    "\n" +
                    "To get you started we've put together our favourite sit up exercises for you to try.",
            "https://www.youtube.com/watch?v=-WSon5E798w"
        )
        workoutDetails.add(workD2)

        val workD3 = WorkoutDataDetails(
            R.drawable.planks,
            "Planks",
            "1. Plant hands directly under shoulders (slightly wider than shoulder width) like you’re about to do a push-up.\n" +
                    "\n" +
                    "2. Ground toes into the floor and squeeze glutes to stabilize your body. Your legs should be working, too — be careful not to lock or hyperextend your knees.\n" +
                    "\n" +
                    "3. Neutralize your neck and spine by looking at a spot on the floor about a foot beyond your hands. Your head should be in line with your back.\n" +
                    "\n" +
                    "4. Hold the position for 20 seconds. As you get more comfortable with the move, hold your plank for as long as possible without compromising your form or breath.",
            "null"
        )
        workoutDetails.add(workD3)

        val workD4 = WorkoutDataDetails(
            R.drawable.squats,
            "Squats",
      "1. Assume the squat stance.\n" +
           "Before you squat, you should get in proper squat position: Keep your feet about shoulder-width apart, Tamir says. There’s no set rule for exact positioning of your feet—it’ll vary depending upon anatomical differences—but a good guideline is for them to turn out anywhere between 5 and 30 degrees. So rather than pointing straight ahead, your feet will turn out slightly, but how much they do will depend on your particular comfort level and mobility.\n" +
           "\n" +
           "2. Screw your feet into the floor.\n" +
           "Dialing your feet into the ground helps engage your muscles, improve alignment, and create stability with the ground, says Tamir. It’ll also help keep your arches from collapsing, which can make your knees more likely to cave inward when you squat. (This is what’s known as knee valgus.)\n" +
           "\n" +
           "3. Keep your chest up.\n" +
           "Your upper body also matters for squats. “Keep your chest up, your chest proud,” says Tamir. This will prevent your shoulders and upper back from rounding—a common mistake—which could overstress your spine, especially if you are squatting with weight on your back.\n" +
           "\n" +
           "4. Initiate the movement.\n" +
           "When you’re ready to squat, think about starting the movement by bending your knees and pushing your hips back, says Tamir. Engage your core for the descent, and keep it braced throughout the move.\n" +
           "\n" +
           "WATCH\n",
   "https://www.youtube.com/watch?v=mGvzVjuY8SY"
)
workoutDetails.add(workD4)


val workD5 = WorkoutDataDetails(
    R.drawable.lunges,
    "Lunges",
      "How to do a lunge\n" +
              "1. Start in a standing position with your feet hip-width apart.\n" +
              "2. Step forward longer than a walking stride so one leg is ahead of your torso and the other is behind. Your foot should land flat and remain flat while it’s on the ground. Your rear heel will rise off of the ground.\n" +
              "3. Bend your knees to approximately 90 degrees as you lower yourself. Remember to keep your trunk upright and core engaged.\n" +
              "   Then, forcefully push off from your front leg to return to the starting position.\n" +
              "Points to remember:\n" +
              "\n" +
              "- Your lead knee should not go past your toes as you lower toward the ground.\n" +
              "- Your rear knee should not touch the ground.\n" +
              "- Aim to keep your hips symmetrical (at the same height, without dropping the hip of your back leg or hiking the hip of your front leg).\n" +
              "- Contract your abdominals during the movement to help keep your trunk upright.\n" +
              "- Your feet should stay hip-width apart during the landing and return.",
      "https://www.youtube.com/watch?v=wrwwXE_x-pQ"
)
workoutDetails.add(workD5)


val workD6 = WorkoutDataDetails(
    R.drawable.jog,
    "Jogging",
    "Jogging may have a slower pace than running, but it still boasts a range of health benefits. Here’s how to maximize your jogging workouts:\n" +
           "\n" +
           "While jogging, maintain good posture, engage your core, and gaze forward.\n" +
           "Avoid tilting your head down and slumping your shoulders.\n" +
           "Broaden your chest, and keep it lifted as you draw your shoulders down and back.\n" +
           "Keep your hands loose, and use a relaxed arm swing. Avoid crossing your arms in front of your body.\n" +
           "To prevent injuries to your lower body, use a midfoot strike, and avoid hitting the ground with your heel. This allows your foot to land directly under your hip as you drive your body forward. A heel strike may cause your leg to slow down your stride and stress your knees.",
   "https://www.youtube.com/watch?v=R0B4WQv6Sfk&list=PLysK5kM8r78sJFo--opGDHCdTgNJTIb-o&index=28"
   //https://www.youtube.com/watch?v=5umbf4ps0GQ*/
)
workoutDetails.add(workD6)

val workD7 = WorkoutDataDetails(
    R.drawable.starjump,
    "Star-Jumps",
 "How to Do Star Jumps With Proper Form\n" +
           "For star jumps, begin by performing 2–3 sets of 10–20 repetitions. Choose your sets and repetitions based on your ability to maintain good technique throughout each set.\n" +
           "\n" +
           "Stand with your feet shoulder-width apart and a slight bend in your knees. Your shoulders should be directly over your hips with a neutral head and neck position. Your arms should be long and by your sides. Your chin should remain tucked throughout the movement, as if you were holding an egg under your chin.\n" +
           "Pre-tension your shoulders and hips with a good inhale and exhale, and engage your core. Maintain a neutral spine, and begin to bend your hips, knees, and ankles to lower into a quarter-squat or full-squat position. All repetitions should begin from this starting position.\n" +
           "Explosively push your feet into the ground to begin the jump. As you jump into the air, reach your arms and legs out to the side, forming the letter X in the air. Your arms and legs should be fully extended.\n" +
           "Land in a quarter squat or full squat position. Your landing posture should be the same as your jumping posture. Land from your jump on the balls of your feet, and evenly distribute the weight along each entire foot, allowing your hips and knees to bend to absorb force.\n" +
           "Your bodyweight should be loaded into your midfoot and heel. Keep your toes engaged and your knees in line with your toes. Land softly and under control while keeping your core engaged throughout the landing.\n" +
           "Continue to jump for the desired number of repetitions.",
   "https://www.youtube.com/watch?v=lTbQQmpMZv4"
)
workoutDetails.add(workD7)

}



 fun populateWorkouts() {
val work1 = WorkoutData(
   R.drawable.pushups,
   "Pushups",
   "Tap to learn more.",
)
workoutList.add(work1)

val work2 = WorkoutData(
   R.drawable.situp,
   "Situps",
   "Tap to learn more.",
)
workoutList.add(work2)

val work3 = WorkoutData(
   R.drawable.planks,
   "Planks",
   "Tap to learn more.",
)
workoutList.add(work3)

val work4 = WorkoutData(
   R.drawable.squats,
   "Squats",
   "Tap to learn more.",
)
workoutList.add(work4)

val work5 = WorkoutData(
   R.drawable.lunges,
   "Lunges",
   "Tap to learn more.",

)
workoutList.add(work5)

val work6 = WorkoutData(
   R.drawable.jog,
   "Jogging",
   "Tap to learn more.",
)
workoutList.add(work6)

val work7 = WorkoutData(
   R.drawable.starjump,
   "Star-Jumps",
   "Tap to learn more.",
)
workoutList.add(work7)
}

override fun onClick(work: WorkoutData) {
val intent = Intent(applicationContext, WorkoutDetails::class.java)
intent.putExtra(WORK_ID_EXTRA, work.id)
finish()
startActivity(intent)
}


    private fun usersetting() {
        val name = intent.getStringExtra("username")
        val context = binding.bottomNav.context
        val intent = Intent(context, Usersetting::class.java)
        intent.putExtra("username", name);
        startActivity(intent)

    }


    private fun home() {
        val name = intent.getStringExtra("username")
        val context = binding.bottomNav.context
        val intent = Intent(context, Home::class.java)
        intent.putExtra("username", name);
        startActivity(intent)

    }

    private fun tools() {
        val name = intent.getStringExtra("username")
        val context = binding.bottomNav.context
        val intent = Intent(context, Calculator::class.java)
        intent.putExtra("username", name);
        startActivity(intent)
    }


    private fun workout() {
        val name = intent.getStringExtra("username")
        val context = binding.bottomNav.context
        val intent = Intent(context, WorkoutMain::class.java)
        intent.putExtra("username", name);
        startActivity(intent)
    }

    private fun foodplan() {
        val name = intent.getStringExtra("username")
        val context = binding.bottomNav.context
        val intent = Intent(context, SelectedPage::class.java)
        intent.putExtra("username", name);
        startActivity(intent)

    }




}
