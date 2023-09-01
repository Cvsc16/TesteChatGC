package com.example.testechatgc

import com.google.android.material.appbar.AppBarLayout
import kotlin.math.abs

class AppBarStateChangeListener (onStateChanged: (state : State) -> Unit) :
    AppBarLayout.OnOffsetChangedListener {
    enum class State {
        EXPANDED, COLLAPSED, IDLE
    }

    var onStateChanged : (state : State) -> Unit

    init {
        this.onStateChanged = onStateChanged
    }

    private var mCurrentState = State.IDLE

    override fun onOffsetChanged(appBarLayout: AppBarLayout, i: Int) {
        mCurrentState = if (i == 0) {
            if (mCurrentState != State.EXPANDED) {
                onStateChanged(State.EXPANDED)
            }
            State.EXPANDED
        } else if (abs(i) >= appBarLayout.totalScrollRange) {
            if (mCurrentState != State.COLLAPSED) {
                onStateChanged(State.COLLAPSED)
            }
            State.COLLAPSED
        } else {
            if (mCurrentState != State.IDLE) {
                onStateChanged(State.IDLE)
            }
            State.IDLE
        }
    }
}