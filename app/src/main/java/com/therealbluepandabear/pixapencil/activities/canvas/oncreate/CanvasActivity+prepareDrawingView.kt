/*
 * PixaPencil
 * Copyright 2022  therealbluepandabear
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.therealbluepandabear.pixapencil.activities.canvas.oncreate

import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.activities.canvas.onactionup.root.extendedOnActionUp
import com.therealbluepandabear.pixapencil.activities.canvas.onpixeltapped.extendedOnPixelTapped
import com.therealbluepandabear.pixapencil.activities.canvas.preferences.*
import com.therealbluepandabear.pixapencil.models.BitmapAction

fun CanvasActivity.prepareDrawingView() {
    binding.activityCanvasDrawingView.setBitmapWidth(width)
    binding.activityCanvasDrawingView.setBitmapHeight(height)

    binding.activityCanvasDrawingView.setOnPixelTapped {
        extendedOnPixelTapped(it)
    }

    binding.activityCanvasDrawingView.setOnTouchEvent {
        if (viewModel.currentBitmapAction == null) {
            viewModel.currentBitmapAction = BitmapAction(mutableListOf())
        }
    }

    binding.activityCanvasDrawingView.setOnActionUp {
        extendedOnActionUp()
    }

    binding.activityCanvasDrawingView.post {
        applyPixelPerfectValueFromPreference()
        applyGridEnabledValueFromPreference()
        applyShowShadingToolTipValueFromPreference()
        applyShowSprayToolTipValueFromPreference()
        applyShowDitherToolTipFromPreference()
    }
}