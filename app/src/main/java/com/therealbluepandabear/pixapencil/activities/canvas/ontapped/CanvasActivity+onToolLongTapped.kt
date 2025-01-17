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

package com.therealbluepandabear.pixapencil.activities.canvas.ontapped

import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.enums.Tool
import com.therealbluepandabear.pixapencil.extensions.showSimpleInfoDialog
import com.therealbluepandabear.pixapencil.utility.constants.StringConstants

fun CanvasActivity.extendedOnToolLongTapped(toolName: String) {
    when (toolName) {
        StringConstants.Identifiers.PENCIL_TOOL_IDENTIFIER -> {
            if (viewModel.currentTool == Tool.PencilTool) {
                showSimpleInfoDialog(
                    getString(R.string.pencil_tool_info_title),
                    getString(R.string.pencil_tool_info_text))
            }
        }

        StringConstants.Identifiers.FILL_TOOL_IDENTIFIER  -> {
            if (viewModel.currentTool == Tool.FillTool) {
                showSimpleInfoDialog(
                    getString(R.string.fill_tool_info_title),
                    getString(R.string.fill_tool_info_text))
            }
        }

        StringConstants.Identifiers.MOVE_TOOL_IDENTIFIER  -> {
            if (viewModel.currentTool == Tool.MoveTool) {
                showSimpleInfoDialog(
                    getString(R.string.move_tool_info_title),
                    getString(R.string.move_tool_info_text))
            }
        }

        StringConstants.Identifiers.LINE_TOOL_IDENTIFIER -> {
            if (viewModel.currentTool == Tool.LineTool) {
                showSimpleInfoDialog(
                    getString(R.string.line_tool_info_title),
                    getString(R.string.line_tool_info_text))
            }
        }

        StringConstants.Identifiers.RECTANGLE_TOOL_IDENTIFIER -> {
            if (viewModel.currentTool == Tool.RectangleTool) {
                showSimpleInfoDialog(
                    getString(R.string.rectangle_tool_info_title),
                    getString(R.string.rectangle_tool_info_text))
            }
        }

        StringConstants.Identifiers.OUTLINED_RECTANGLE_TOOL_IDENTIFIER -> {
            if (viewModel.currentTool == Tool.OutlinedRectangleTool) {
                showSimpleInfoDialog(
                    getString(R.string.outlined_rectangle_tool_info_title),
                    getString(R.string.outlined_rectangle_tool_info_text))
            }
        }

        StringConstants.Identifiers.SQUARE_TOOL_IDENTIFIER -> {
            if (viewModel.currentTool == Tool.SquareTool) {
                showSimpleInfoDialog(
                    getString(R.string.square_tool_info_title),
                    getString(R.string.square_tool_info_text))
            }
        }

        StringConstants.Identifiers.OUTLINED_SQUARE_TOOL_IDENTIFIER -> {
            if (viewModel.currentTool == Tool.OutlinedSquareTool) {
                showSimpleInfoDialog(
                    getString(R.string.outlined_square_tool_info_title),
                    getString(R.string.outlined_square_tool_info_text))
            }
        }

        StringConstants.Identifiers.ELLIPSE_TOOL_IDENTIFIER -> {
            if (viewModel.currentTool == Tool.EllipseTool) {
                showSimpleInfoDialog(
                    getString(R.string.ellipse_tool_info_title),
                    getString(R.string.ellipse_tool_info_text))
            }
        }

        StringConstants.Identifiers.OUTLINED_ELLIPSE_TOOL_IDENTIFIER -> {
            if (viewModel.currentTool == Tool.OutlinedEllipseTool) {
                showSimpleInfoDialog(
                    getString(R.string.outlined_ellipse_tool_info_title),
                    getString(R.string.outlined_ellipse_tool_info_text))
            }
        }

        StringConstants.Identifiers.CIRCLE_TOOL_IDENTIFIER -> {
            if (viewModel.currentTool == Tool.CircleTool) {
                showSimpleInfoDialog(
                    getString(R.string.circle_tool_info_title),
                    getString(R.string.circle_tool_info_text))
            }
        }

        StringConstants.Identifiers.OUTLINED_CIRCLE_TOOL_IDENTIFIER -> {
            if (viewModel.currentTool == Tool.OutlinedCircleTool) {
                showSimpleInfoDialog(
                    getString(R.string.outlined_circle_tool_info_title),
                    getString(R.string.outlined_circle_tool_info_text))
            }
        }

        StringConstants.Identifiers.SPRAY_TOOL_IDENTIFIER -> {
            if (viewModel.currentTool == Tool.SprayTool) {
                showSimpleInfoDialog(
                    getString(R.string.spray_tool_info_title),
                    getString(R.string.spray_tool_info_text))
            }
        }

        StringConstants.Identifiers.POLYGON_TOOL_IDENTIFIER -> {
            if (viewModel.currentTool == Tool.PolygonTool) {
                showSimpleInfoDialog(
                    getString(R.string.polygon_tool_info_title),
                    getString(R.string.polygon_tool_info_text))
            }
        }

        StringConstants.Identifiers.DITHER_TOOL_IDENTIFIER -> {
            if (viewModel.currentTool == Tool.DitherTool) {
                showSimpleInfoDialog(
                    getString(R.string.dither_tool_info_title),
                    getString(R.string.dither_tool_info_text))
            }
        }

        StringConstants.Identifiers.SHADING_TOOL_IDENTIFIER  -> {
            if (viewModel.currentTool == Tool.ShadingTool) {
                showSimpleInfoDialog(
                    getString(R.string.shading_tool_info_title),
                    getString(R.string.shading_tool_info_text))
            }
        }

        StringConstants.Identifiers.COLOR_PICKER_TOOL_IDENTIFIER -> {
            if (viewModel.currentTool == Tool.ColorPickerTool) {
                showSimpleInfoDialog(
                    getString(R.string.color_picker_tool_info_title),
                    getString(R.string.color_picker_tool_info_text))
            }
        }

        StringConstants.Identifiers.ERASE_TOOL_IDENTIFIER -> {
            if (viewModel.currentTool == Tool.EraseTool) {
                showSimpleInfoDialog(
                    getString(R.string.erase_tool_info_title),
                    getString(R.string.erase_tool_info_text))
            }
        }
    }
}