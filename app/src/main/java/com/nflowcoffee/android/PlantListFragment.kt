/*
 * Copyright 2018 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.nflowcoffee.android

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.nflowcoffee.android.adapters.PlantAdapter
import com.nflowcoffee.android.databinding.FragmentPlantListBinding
import com.nflowcoffee.android.utilities.InjectorUtils
import com.nflowcoffee.android.viewmodels.PlantListViewModel

class PlantListFragment : Fragment() {

    private val viewModel: PlantListViewModel by viewModels {
        InjectorUtils.providePlantListViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPlantListBinding.inflate(inflater, container, false)
        context ?: return binding.root

        val adapter = PlantAdapter()
        binding.plantList.adapter = adapter
        subscribeUi(adapter)

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_plant_list, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.filter_zone -> {
                updateData()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun subscribeUi(adapter: PlantAdapter) {
        viewModel.plants.observe(viewLifecycleOwner) { plants ->
            /**
             *  Plant may return null, but the [observe] extension function assumes it will not be null.
             *  So there will be a warning（Condition `plants != null` is always `true`） here.
             *  I am not sure if the database return data type should be defined as nullable, Such as `LiveData<List<Plant>?>` .
             */
            if (plants != null) adapter.submitList(plants)
        }
    }

    private fun updateData() {
        with(viewModel) {
            if (isFiltered()) {
                clearGrowZoneNumber()
            } else {
                setGrowZoneNumber(9)
            }
        }
    }
}