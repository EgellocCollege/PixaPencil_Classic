package com.realtomjoney.pyxlmoose.database

import com.realtomjoney.pyxlmoose.R
import com.realtomjoney.pyxlmoose.enums.BrushInstruction
import com.realtomjoney.pyxlmoose.models.Brush
import com.realtomjoney.pyxlmoose.models.BrushInstructionCommand

object BrushesDatabase {
    private val database = mutableListOf<Brush>()

    private fun addBrush(brush: Brush) = database.add(brush)

    fun toList(): List<Brush> {
        return database.toList()
    }

    init {
        val brushesData = listOf(
            Brush("Default Brush", listOf(), R.drawable.default_brush),
            Brush("Brush 1", listOf(
                BrushInstructionCommand(BrushInstruction.EXPAND_LEFT, 1),
                BrushInstructionCommand(BrushInstruction.EXPAND_RIGHT, 1),
                BrushInstructionCommand(BrushInstruction.EXPAND_TOP, 1),
                BrushInstructionCommand(BrushInstruction.EXPAND_BOTTOM, 1),
            ), R.drawable.brush_1),
            Brush("Brush 2", listOf(
                BrushInstructionCommand(BrushInstruction.EXPAND_LEFT, 1),
                BrushInstructionCommand(BrushInstruction.EXPAND_RIGHT, 1),
                BrushInstructionCommand(BrushInstruction.EXPAND_TOP, 1),
                BrushInstructionCommand(BrushInstruction.EXPAND_BOTTOM, 1),
                BrushInstructionCommand(BrushInstruction.EXPAND_BOTTOM_LEFT, 1),
                BrushInstructionCommand(BrushInstruction.EXPAND_BOTTOM_RIGHT, 1),
                BrushInstructionCommand(BrushInstruction.EXPAND_TOP_LEFT, 1),
                BrushInstructionCommand(BrushInstruction.EXPAND_TOP_RIGHT, 1),
            ), R.drawable.brush_2),
            Brush("Brush 4", listOf(
                BrushInstructionCommand(BrushInstruction.EXPAND_RIGHT, 1),
                BrushInstructionCommand(BrushInstruction.EXPAND_RIGHT, 2),

                BrushInstructionCommand(BrushInstruction.EXPAND_LEFT, 1),
                BrushInstructionCommand(BrushInstruction.EXPAND_LEFT, 2),
            ), R.drawable.brush_4),
            Brush("Brush 6", listOf(
                BrushInstructionCommand(BrushInstruction.EXPAND_BOTTOM_LEFT, 1),
                BrushInstructionCommand(BrushInstruction.EXPAND_BOTTOM_RIGHT, 1),
                BrushInstructionCommand(BrushInstruction.EXPAND_TOP_LEFT, 1),
                BrushInstructionCommand(BrushInstruction.EXPAND_TOP_RIGHT, 1),

                BrushInstructionCommand(BrushInstruction.EXPAND_LEFT, 2),
                BrushInstructionCommand(BrushInstruction.EXPAND_RIGHT, 2),
                BrushInstructionCommand(BrushInstruction.EXPAND_TOP, 2),
                BrushInstructionCommand(BrushInstruction.EXPAND_BOTTOM, 2),

                BrushInstructionCommand(BrushInstruction.EXPAND_LEFT, 1),
                BrushInstructionCommand(BrushInstruction.EXPAND_RIGHT, 1),
                BrushInstructionCommand(BrushInstruction.EXPAND_TOP, 1),
                BrushInstructionCommand(BrushInstruction.EXPAND_BOTTOM, 1),
            ), R.drawable.brush_6))

        for (brush in brushesData) addBrush(brush)
    }
}