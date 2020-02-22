package apps.studios.bt.mrdfoodclone.views

import android.content.Context
import android.view.MotionEvent
import android.widget.FrameLayout


class MapWrapper(context: Context) : FrameLayout(context) {
    interface OnDragListener {
        fun onMapDrag(motionEvent: MotionEvent?)

        fun onMapRelease(action_release: Int?)
    }

    var mapDragListener: OnDragListener? = null

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        mapDragListener?.onMapDrag(ev)
        if (ev?.action == MotionEvent.ACTION_UP) {
            mapDragListener?.onMapRelease(ev.action)
        }
        return super.dispatchTouchEvent(ev)
    }


}