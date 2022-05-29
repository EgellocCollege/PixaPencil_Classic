package com.therealbluepandabear.pixapencil.activities.canvas.onpixeltapped

import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.activities.canvas.canvascommands.overrideSetPixel
import com.therealbluepandabear.pixapencil.activities.canvas.getSelectedColor
import com.therealbluepandabear.pixapencil.algorithms.LineAlgorithm
import com.therealbluepandabear.pixapencil.algorithms.PixelPerfectAlgorithm
import com.therealbluepandabear.pixapencil.extensions.getPixel
import com.therealbluepandabear.pixapencil.fragments.canvas.pixelGridViewInstance
import com.therealbluepandabear.pixapencil.models.Coordinates

fun CanvasActivity.pencilToolOnPixelTapped(coordinatesTapped: Coordinates) {
    if (pixelGridViewInstance.prevX != null && pixelGridViewInstance.prevY != null) {
        val color = if (pixelGridViewInstance.pixelPerfectMode) {
            pixelGridViewInstance.pixelGridViewBitmap.getPixel(coordinatesTapped)
        } else {
            getSelectedColor()
        }
        primaryAlgorithmInfoParameter.color = color

        val lineAlgorithmInstance = LineAlgorithm(primaryAlgorithmInfoParameter)

        lineAlgorithmInstance.compute(Coordinates(pixelGridViewInstance.prevX!!, pixelGridViewInstance.prevY!!), coordinatesTapped)
    }

    canvasCommandsHelperInstance.overrideSetPixel(coordinatesTapped, pixelGridViewInstance.pixelGridViewBitmap.getPixel(coordinatesTapped))

    pixelGridViewInstance.prevX = coordinatesTapped.x
    pixelGridViewInstance.prevY = coordinatesTapped.y


    if (pixelGridViewInstance.pixelPerfectMode) {
        for (i in PixelPerfectAlgorithm(primaryAlgorithmInfoParameter).compute()) {
            canvasCommandsHelperInstance.overrideSetPixel(i, getSelectedColor())
        }
    }
}
