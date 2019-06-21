package tat.mukhutdinov.musicmanagement.infrastructure.util.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat
import tat.mukhutdinov.musicmanagement.R

abstract class SwipeToDeleteCallback(context: Context) : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {

    private val deleteIcon = VectorDrawableCompat.create(context.resources, R.drawable.ic_delete, context.theme)
    private val intrinsicWidth = deleteIcon?.intrinsicWidth ?: 0
    private val intrinsicHeight = deleteIcon?.intrinsicHeight ?: 0
    private val background = ColorDrawable()
    private val backgroundColor = ContextCompat.getColor(context, R.color.colorPrimary)
    private val clearPaint = Paint().apply { xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR) }

    override fun getSwipeEscapeVelocity(defaultValue: Float): Float =
        2000f

    override fun getSwipeThreshold(viewHolder: RecyclerView.ViewHolder): Float =
        0.7f

    override fun onMove(recycler: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder) =
        false

    override fun onChildDraw(
        canvas: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {

        val itemView = viewHolder.itemView
        val itemHeight = itemView.bottom - itemView.top
        val isCanceled = dX == 0f && !isCurrentlyActive

        if (isCanceled) {
            clearCanvas(
                canvas,
                itemView.right + dX,
                itemView.top.toFloat(),
                itemView.right.toFloat(),
                itemView.bottom.toFloat()
            )
            super.onChildDraw(canvas, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
            return
        }

        // Draw delete background
        background.color = backgroundColor
        background.setBounds(itemView.left, itemView.top, itemView.left + dX.toInt(), itemView.bottom)
        background.draw(canvas)

        // Calculate position of delete icon
        val deleteIconTop = itemView.top + (itemHeight - intrinsicHeight) / 2
        val deleteIconMargin = (itemHeight - intrinsicHeight) / 2
        val deleteIconLeft = deleteIconMargin
        val deleteIconRight = deleteIconMargin + intrinsicWidth
        val deleteIconBottom = deleteIconTop + intrinsicHeight

        // Draw the delete icon
        deleteIcon?.setBounds(deleteIconLeft, deleteIconTop, deleteIconRight, deleteIconBottom)
        deleteIcon?.draw(canvas)

        // Draw text
        val paint = Paint()
        paint.color = Color.WHITE
        paint.typeface = Typeface.create(Typeface.SANS_SERIF, Typeface.ITALIC)
        paint.textSize = Utils.dpToPx(16f, recyclerView.resources)
        canvas.drawText(
            recyclerView.resources.getString(R.string.swipe_to_delete),
            deleteIconRight + deleteIconMargin.toFloat(),
            itemView.top + (itemHeight / 2).toFloat() + (paint.textSize / 2) - 4,
            paint
        )

        super.onChildDraw(canvas, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }

    private fun clearCanvas(c: Canvas, left: Float, top: Float, right: Float, bottom: Float) {
        c.drawRect(left, top, right, bottom, clearPaint)
    }
}
