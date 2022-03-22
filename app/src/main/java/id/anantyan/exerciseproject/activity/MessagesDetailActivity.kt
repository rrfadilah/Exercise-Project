package id.anantyan.exerciseproject.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import id.anantyan.exerciseproject.R
import id.anantyan.exerciseproject.databinding.ActivityMessagesDetailBinding
import id.anantyan.exerciseproject.model.Messages
import id.anantyan.utils.Constant.PASSING_TO_MESSAGES_ACTIVITY
import id.anantyan.utils.viewbinding.viewBinding

class MessagesDetailActivity : AppCompatActivity() {

    private val binding: ActivityMessagesDetailBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_messages_detail)
        setSupportActionBar(binding.include.toolbar)
        supportActionBar?.title = "Set Data"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        onBinding()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun onBinding() {
        var messages = intent.extras?.getParcelable<Messages>(PASSING_TO_MESSAGES_ACTIVITY)
        if (intent.hasExtra(PASSING_TO_MESSAGES_ACTIVITY)) {
            binding.txtInputSendTo.setText(messages?.senderName)
            binding.txtInputMessages.setText(messages?.message)
            binding.btnSubmit.setOnClickListener {
                messages = Messages(
                    messages?.fromName,
                    binding.txtInputSendTo.text.toString(),
                    binding.txtInputMessages.text.toString()
                )
                val intent = Intent()
                intent.putExtra(PASSING_TO_MESSAGES_ACTIVITY, messages)
                setResult(RESULT_OK, intent)
                finish()
            }
        } else {
            binding.btnSubmit.setOnClickListener {
                messages = Messages(
                    "Arya Rezza",
                    binding.txtInputSendTo.text.toString(),
                    binding.txtInputMessages.text.toString()
                )
                val intent = Intent()
                intent.putExtra(PASSING_TO_MESSAGES_ACTIVITY, messages)
                setResult(RESULT_OK, intent)
                finish()
            }
        }
    }
}