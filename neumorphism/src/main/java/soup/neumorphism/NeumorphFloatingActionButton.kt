package soup.neumorphism

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import androidx.annotation.ColorInt
import androidx.appcompat.widget.AppCompatImageButton
import soup.neumorphism.internal.util.withClip

class NeumorphFloatingActionButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = R.style.defaultNeumorphFloatingActionButton
) : AppCompatImageButton(context, attrs, defStyleAttr) {

    private val shapeDrawable: NeumorphShapeDrawable

    init {
        val a = context.obtainStyledAttributes(
            attrs, R.styleable.NeumorphFloatingActionButton, defStyleAttr, defStyleRes
        )
        val shapeType =
            a.getInt(R.styleable.NeumorphFloatingActionButton_neumorph_shapeType, ShapeType.FLAT)
        val shadowElevation = a.getDimension(
            R.styleable.NeumorphFloatingActionButton_neumorph_shadowElevation, 0f
        )
        val shadowColorLight = a.getColor(
            R.styleable.NeumorphFloatingActionButton_neumorph_shadowColorLight, Color.WHITE
        )
        val shadowColorDark = a.getColor(
            R.styleable.NeumorphFloatingActionButton_neumorph_shadowColorDark, Color.BLACK
        )
        a.recycle()

        shapeDrawable = NeumorphShapeDrawable(context, attrs, defStyleAttr, defStyleRes).apply {
            setShapeType(shapeType)
            setShadowElevation(shadowElevation)
            setShadowColorLight(shadowColorLight)
            setShadowColorDark(shadowColorDark)
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        shapeDrawable.setBounds(0, 0, w, h)
    }

    override fun draw(canvas: Canvas) {
        shapeDrawable.draw(canvas)
        canvas.withClip(shapeDrawable.getOutlinePath()) {
            super.draw(this)
        }
    }

    fun setShapeAppearanceModel(shapeAppearanceModel: NeumorphShapeAppearanceModel) {
        shapeDrawable.setShapeAppearanceModel(shapeAppearanceModel)
    }

    fun getShapeAppearanceModel(): NeumorphShapeAppearanceModel {
        return shapeDrawable.getShapeAppearanceModel()
    }

    fun setShapeType(@ShapeType shapeType: Int) {
        shapeDrawable.setShapeType(shapeType)
    }

    @ShapeType
    fun getShapeType(): Int {
        return shapeDrawable.getShapeType()
    }

    fun setShadowElevation(shadowElevation: Float) {
        shapeDrawable.setShadowElevation(shadowElevation)
    }

    fun getShadowElevation(): Float {
        return shapeDrawable.getShadowElevation()
    }

    fun setShadowColorLight(@ColorInt shadowColor: Int) {
        shapeDrawable.setShadowColorLight(shadowColor)
    }

    fun setShadowColorDark(@ColorInt shadowColor: Int) {
        shapeDrawable.setShadowColorDark(shadowColor)
    }
}
