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

package com.therealbluepandabear.pixapencil.fragments.replacecolor

import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.adapters.ColorPickerAdapter
import com.therealbluepandabear.pixapencil.converters.BitmapConverter
import com.therealbluepandabear.pixapencil.database.AppData
import com.therealbluepandabear.pixapencil.databinding.FragmentReplaceColorBinding
import com.therealbluepandabear.pixapencil.extensions.*
import com.therealbluepandabear.pixapencil.fragments.base.ActivityFragment
import com.therealbluepandabear.pixapencil.listeners.ColorPickerListener
import com.therealbluepandabear.pixapencil.listeners.ReplaceColorFragmentListener
import com.therealbluepandabear.pixapencil.utility.constants.StringConstants
import java.util.ArrayList

class ReplaceColorFragment : Fragment(), ActivityFragment {
    private var _binding: FragmentReplaceColorBinding? = null

    private val binding get(): FragmentReplaceColorBinding {
        return _binding!!
    }

    private lateinit var caller: ReplaceColorFragmentListener

    override val title: String by lazy { getString(R.string.fragment_replace_color_title) }

    private var colorToFind: Int? = null
    private var colorToReplace: Int? = null

    private lateinit var paramCanvasColors: List<Int>
    private lateinit var paramTransparentBitmapSource: Bitmap
    private lateinit var paramdrawingViewBitmapSource: Bitmap
    private var paramSelectedColorPaletteIndex: Int = 0
    private var paramScaledWidth: Int = 500
    private var paramScaledHeight: Int = 500

    fun setParams(
        paramCanvasColors: List<Int>,
        paramTransparentBitmapSource: Bitmap,
        paramdrawingViewBitmapSource: Bitmap,
        paramSelectedColorPaletteIndex: Int,
        paramScaledWidth: Int,
        paramScaledHeight: Int) {
        this.paramCanvasColors = paramCanvasColors
        this.paramTransparentBitmapSource = paramTransparentBitmapSource
        this.paramdrawingViewBitmapSource = paramdrawingViewBitmapSource
        this.paramSelectedColorPaletteIndex = paramSelectedColorPaletteIndex
        this.paramScaledWidth = paramScaledWidth
        this.paramScaledHeight = paramScaledHeight
    }

    private fun setup() {
        setupCanvasColorsRecyclerView()
        setupAvailableColorsRecyclerView()
        setOnClickListeners()
        setupPreview()
    }

    private fun setupPreview() {
        val bitmap = Bitmap.createScaledBitmap(paramTransparentBitmapSource.clone().overlay(paramdrawingViewBitmapSource.clone()), paramScaledWidth, paramScaledHeight, false)
        binding.fragmentFindAndReplaceOldPreview.setImageBitmap(bitmap)
        binding.fragmentFindAndReplaceNewPreview.setImageBitmap(bitmap)
    }

    private fun setupCanvasColorsRecyclerView() {
        binding.fragmentFindAndReplaceCanvasColorsRecyclerView.layoutManager =
            LinearLayoutManager(this@ReplaceColorFragment.requireContext()).apply {
                orientation = LinearLayoutManager.HORIZONTAL
            }

        binding.fragmentFindAndReplaceCanvasColorsRecyclerView.adapter = ColorPickerAdapter(paramCanvasColors, ColorsToFindCaller(binding))
    }

    private fun findAndReplace() {
        val bitmap = paramdrawingViewBitmapSource.clone()
        bitmap.replacePixelsByColor(colorToFind!!, colorToReplace!!)

        val finalBitmap = Bitmap.createScaledBitmap(paramTransparentBitmapSource.clone().overlay(bitmap), paramScaledWidth, paramScaledHeight, false)
        binding.fragmentFindAndReplaceNewPreview.setImageBitmap(finalBitmap)
    }

    private fun setupAvailableColorsRecyclerView() {
        binding.fragmentFindAndReplaceAvailableColorsRecyclerView.layoutManager =
            LinearLayoutManager(this@ReplaceColorFragment.requireContext()).apply {
                orientation = LinearLayoutManager.HORIZONTAL
            }
        binding.fragmentFindAndReplaceAvailableColorsRecyclerView.adapter = ColorPickerAdapter(
            AppData.colorPalettesDB.colorPalettesDao().getAllColorPalettesNoLiveData()[paramSelectedColorPaletteIndex].getFindAndReplaceCompatibleColorPaletteColorData(),
            ColorsToReplaceCaller(binding),
        )
    }

    private fun setOnClickListeners() {
        binding.fragmentFindAndReplaceDoneButton.setOnClickListener {
            caller.onDoneButtonPressed(colorToFind, colorToReplace)
        }
    }

    inner class ColorsToFindCaller(val binding: FragmentReplaceColorBinding) : ColorPickerListener {
        override fun onColorTapped(colorTapped: Int) {
            colorToFind = colorTapped

            if (colorToReplace != null) {
                findAndReplace()
            }
        }
    }

    inner class ColorsToReplaceCaller(val binding: FragmentReplaceColorBinding) : ColorPickerListener {
        override fun onColorTapped(colorTapped: Int) {
            colorToReplace = colorTapped

            if (colorToFind != null) {
                findAndReplace()
            }
        }
    }

    companion object {
        fun newInstance(
            paramCanvasColors: List<Int>,
            paramTransparentBitmapSource: Bitmap,
            paramdrawingViewBitmapSource: Bitmap,
            paramSelectedColorPaletteIndex: Int,
            paramScaledWidth: Int,
            paramScaledHeight: Int): ReplaceColorFragment {
            val fragment = ReplaceColorFragment()
            fragment.setParams(paramCanvasColors, paramTransparentBitmapSource, paramdrawingViewBitmapSource, paramSelectedColorPaletteIndex, paramScaledWidth, paramScaledHeight)

            return fragment
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ReplaceColorFragmentListener) caller = context
        requireActivity().title = title
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(ActivityFragment.rootMenuProvider, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentReplaceColorBinding.inflate(inflater, container, false)

        setup()

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState != null) {
            paramCanvasColors = savedInstanceState.getIntegerArrayList(StringConstants.Identifiers.PREV_COLORS_TO_FIND_BUNDLE_IDENTIFIER)!!.toList()
            paramTransparentBitmapSource = BitmapConverter.convertStringToBitmap(savedInstanceState.getString(
                StringConstants.Identifiers.PREV_TRANSPARENT_BITMAP_SOURCE_BUNDLE_IDENTIFIER)!!)!!.createMutableClone()
            paramdrawingViewBitmapSource = BitmapConverter.convertStringToBitmap(savedInstanceState.getString(
                StringConstants.Identifiers.PREV_PIXEL_GRID_VIEW_BITMAP_SOURCE_BUNDLE_IDENTIFIER)!!)!!.createMutableClone()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putIntegerArrayList(
            StringConstants.Identifiers.PREV_COLORS_TO_FIND_BUNDLE_IDENTIFIER,
            paramCanvasColors as ArrayList<Int>
        )

        outState.putString(
            StringConstants.Identifiers.PREV_TRANSPARENT_BITMAP_SOURCE_BUNDLE_IDENTIFIER,
            BitmapConverter.convertBitmapToString(paramTransparentBitmapSource))
        outState.putString(
            StringConstants.Identifiers.PREV_PIXEL_GRID_VIEW_BITMAP_SOURCE_BUNDLE_IDENTIFIER,
            BitmapConverter.convertBitmapToString(paramdrawingViewBitmapSource))

        super.onSaveInstanceState(outState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        requireActivity().title = (requireActivity() as CanvasActivity).projectTitle
    }
}