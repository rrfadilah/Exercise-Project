package id.anantyan.exerciseproject.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import id.anantyan.exerciseproject.R
import id.anantyan.exerciseproject.adapter.OnBoardAdapter
import id.anantyan.exerciseproject.databinding.ActivityOnBoardBinding
import id.anantyan.utils.viewbinding.viewBinding

class OnBoardActivity : AppCompatActivity() {

    private lateinit var adapter: OnBoardAdapter
    private val binding: ActivityOnBoardBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_board)
        onBinding()
    }

    private fun onBinding() {
        val item = listOf(
            R.drawable.the_doctor_3,
            R.drawable.the_doctor_2,
            R.drawable.the_doctor_1
        )
        adapter = OnBoardAdapter(item)
        binding.viewPager.adapter = adapter
        binding.btnSignIn.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.viewPager.registerOnPageChangeCallback(onPageChangeListener)
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { _, _ -> }.attach()
    }

    override fun onBackPressed() {
        if (binding.viewPager.currentItem > 0) {
            binding.viewPager.currentItem = 0
        } else {
            super.onBackPressed()
        }
    }

    private val onPageChangeListener = object : ViewPager2.OnPageChangeCallback() {
        @SuppressLint("SetTextI18n")
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            when (position) {
                0 -> binding.textView.setText(R.string.txt_konsultasi_dengan_dokter_jadi_lebih_mudah_amp_fleksibel)
                1 -> binding.textView.text = "Mudah dan cepat"
                2 -> binding.textView.text = "Dokter aman dan berpengalaman"
            }

            if (position == 0 || position > 0 && position < adapter.itemCount - 1) {
                binding.btnSignUp.setText(R.string.btn_next)
                binding.btnSignUp.setOnClickListener {
                    binding.viewPager.currentItem++
                }
            } else if (position == adapter.itemCount - 1) {
                binding.btnSignUp.setText(R.string.txt_btn_get_started)
                binding.btnSignUp.setOnClickListener {
                    val intent = Intent(this@OnBoardActivity, SignUpActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }
}