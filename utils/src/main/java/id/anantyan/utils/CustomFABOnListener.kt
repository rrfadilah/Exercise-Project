package id.anantyan.utils

import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.floatingactionbutton.FloatingActionButton

class RVExtendedFloatingButtonView(
    private var extendedFloatingActionButton: ExtendedFloatingActionButton
): RecyclerView.OnScrollListener() {
    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        if (newState == RecyclerView.SCROLL_STATE_IDLE
            && !extendedFloatingActionButton.isExtended
            && recyclerView.computeVerticalScrollOffset() == 0
        ) extendedFloatingActionButton.extend()
        super.onScrollStateChanged(recyclerView, newState)
    }
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        if (dy != 0 && extendedFloatingActionButton.isExtended) extendedFloatingActionButton.shrink()
        super.onScrolled(recyclerView, dx, dy)
    }
}

class RVFloatingActionButtonView(
    private var floatingActionButton: FloatingActionButton
) : RecyclerView.OnScrollListener() {
    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        if (newState == RecyclerView.SCROLL_STATE_IDLE
            && !floatingActionButton.isOrWillBeShown
            && recyclerView.computeVerticalScrollOffset() == 0
        ) floatingActionButton.show()
        super.onScrollStateChanged(recyclerView, newState)
    }
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        if (dy != 0 && floatingActionButton.isOrWillBeShown) floatingActionButton.hide()
        super.onScrolled(recyclerView, dx, dy)
    }
}

