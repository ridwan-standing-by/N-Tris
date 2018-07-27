package com.ridwanstandingby.ntris.input

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.math.Vector2
import com.ridwanstandingby.ntris.render.views.View
import com.ridwanstandingby.ntris.states.GameStateManager

class RawPlayInputProcessor(private val gsm: GameStateManager) {

    fun getRawInput(views: List<View>): RawPlayInput =
        getRawPointerInput(views) or getKeyboardInput(views)

    private fun getRawPointerInput(views: List<View>): RawPlayInput {
        val pointers = getAllPointers()
        return checkRawPointerInputInViews(views, pointers)
    }

    private fun checkRawPointerInputInViews(views: List<View>, pointers: List<Vector2>) =
            RawPlayInput().also { rawPlayInput ->
                pointers.forEach { pointer ->
                    views.forEach { view ->
                        if (view.wasPointerInView(pointer.x, pointer.y))
                            view.handleInputIsInView(rawPlayInput)
                    }
                }
            }

    private fun getAllPointers(): List<Vector2> =
            (0 until MAX_POINTERS).mapNotNull { i ->
                if (Gdx.input.isTouched(i))
                    getPositionOfPointer(i)
                else
                    null
            }

    private fun getPositionOfPointer(i: Int): Vector2 = // NOTE: y is from top on input.getY
            Vector2(Gdx.input.getX(i).toFloat(), gsm.dimensions.screenHeight.toFloat() - Gdx.input.getY(i))

    private fun getKeyboardInput(views: List<View>): RawPlayInput =
            RawPlayInput().also { rawPlayInput ->
                views.forEach { view ->
                    view.inputKeys.forEach { key ->
                        if (Gdx.input.isKeyPressed(key))
                            view.handleInputIsInView(rawPlayInput)
                    }
                }
                handleNonViewKeys(rawPlayInput)
            }

    private fun handleNonViewKeys(rawPlayInput: RawPlayInput) {
        KeyInput.EXIT_KEYS.forEach { key ->
            if (Gdx.input.isKeyPressed(key)) rawPlayInput.exit = true
        }
    }

    companion object {
        private const val MAX_POINTERS = 20
    }
}
