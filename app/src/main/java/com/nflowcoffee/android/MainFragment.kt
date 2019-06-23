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
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.nflowcoffee.android.adapters.MainAdapter
import com.nflowcoffee.android.databinding.FragmentMainBinding
import com.nflowcoffee.android.utilities.InjectorUtils
import com.nflowcoffee.android.viewmodels.MainListViewModel

class MainFragment : Fragment() {

    private val viewModel: MainListViewModel by viewModels {
        InjectorUtils.provideMainListViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMainBinding.inflate(inflater, container, false)
        val adapter = MainAdapter()
        binding.mainList.adapter = adapter
        subscribeUi(adapter, binding)
        return binding.root
    }

    private fun subscribeUi(adapter: MainAdapter, binding: FragmentMainBinding) {
        viewModel.gardenPlantings.observe(viewLifecycleOwner) { plantings ->
            binding.hasPlantings = !plantings.isNullOrEmpty()
        }

        viewModel.plantAndGardenPlantings.observe(viewLifecycleOwner) { result ->
            if (!result.isNullOrEmpty())
                adapter.submitList(result)
        }
    }
}
