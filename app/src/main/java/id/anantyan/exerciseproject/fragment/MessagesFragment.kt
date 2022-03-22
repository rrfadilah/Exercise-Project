package id.anantyan.exerciseproject.fragment

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import id.anantyan.exerciseproject.R
import id.anantyan.exerciseproject.activity.BaseFragmentActivity
import id.anantyan.exerciseproject.activity.MessagesDetailActivity
import id.anantyan.exerciseproject.adapter.MessagesAdapter
import id.anantyan.exerciseproject.databinding.FragmentMessagesBinding
import id.anantyan.exerciseproject.model.Messages
import id.anantyan.utils.Constant.PASSING_TO_MESSAGES_ACTIVITY
import id.anantyan.utils.DividerItemDecorationUtil
import id.anantyan.utils.dividerVertical
import id.anantyan.utils.viewbinding.viewBinding

class MessagesFragment : Fragment() {

    private var list: MutableList<Messages> = mutableListOf()
    private var position: Int? = null
    private val viewModel: SharedViewModel by activityViewModels()
    private val binding: FragmentMessagesBinding by viewBinding()
    private val adapter: MessagesAdapter by lazy { MessagesAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_messages, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onBinding()
        onAdapter()
        onGetData()
        onViewModel()
    }

    override fun onResume() {
        super.onResume()
        (activity as BaseFragmentActivity).supportActionBar?.title = "Messages"
    }

    private fun onViewModel() {
        viewModel.listMessages.observe(viewLifecycleOwner) {
            list.clear()
            list.addAll(it)
            adapter.differ(it)
        }
        viewModel.messages.observe(viewLifecycleOwner) { item ->
            item?.let {
                if (position == null) {
                    onInsertData(item)
                }
                position?.let {
                    onUpdateData(it, item)
                    position = null
                }
            }
        }
    }

    private fun onAdapter() {
        adapter.setOnClick { i, messages ->
            position = i
            val intent = Intent((activity as BaseFragmentActivity), MessagesDetailActivity::class.java)
            intent.putExtra(PASSING_TO_MESSAGES_ACTIVITY, messages)
            (activity as BaseFragmentActivity).onResultActivity.launch(intent)
        }
        adapter.setOnLongClick { i, messages ->
            onDeleteData(i)
            Toast.makeText(
                (activity as BaseFragmentActivity),
                messages.senderName,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun onBinding() {
        binding.rvItems.adapter = adapter
        binding.rvItems.setHasFixedSize(true)
        binding.rvItems.layoutManager = LinearLayoutManager((activity as BaseFragmentActivity))
        binding.rvItems.itemAnimator = DefaultItemAnimator()
        binding.rvItems.addItemDecoration(
            dividerVertical(
                (activity as BaseFragmentActivity),
                32,
                0
            )
        )
    }

    private fun onGetData() {
        list.addAll(onDataDummy())
        adapter.differ(list)
    }

    private fun onInsertData(item: Messages) {
        list.add(item)
        adapter.differ(list)
    }

    private fun onUpdateData(position: Int, item: Messages) {
        list[position] = item
        adapter.differ(list)
    }

    private fun onDeleteData(position: Int) {
        list.removeAt(position)
        adapter.differ(list)
    }

    private fun onDataDummy(): MutableList<Messages> {
        return mutableListOf(
            Messages("Arya Rezza", "Annisa Era", "Hi ❤"),
            Messages("Arya Rezza", "Kevin Sanjaya", "Holla"),
            Messages("Arya Rezza", "Prilly Latuconsina", "Hello")
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.setMessages(null)
        viewModel.setListMessages(list)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_action_bar, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_add -> {
                val intent = Intent((activity as BaseFragmentActivity), MessagesDetailActivity::class.java)
                (activity as BaseFragmentActivity).onResultActivity.launch(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}