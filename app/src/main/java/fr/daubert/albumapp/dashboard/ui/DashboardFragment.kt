package fr.daubert.albumapp.dashboard.ui

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import fr.daubert.albumapp.adapters.AlbumAdapter
import fr.daubert.albumapp.dashboard.DashboardViewModel
import fr.daubert.albumapp.databinding.FragmentDashboardBinding

@AndroidEntryPoint
class DashboardFragment : Fragment() {

    private val viewModel: DashboardViewModel by viewModels()

    private lateinit var adapter: AlbumAdapter
    private lateinit var binding: FragmentDashboardBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDashboardBinding.inflate(inflater, container, false)
        context ?: return binding.root

        adapter = AlbumAdapter(requireContext(), emptyList())
        binding.rvAlbums.adapter = adapter
        binding.rvAlbums.layoutManager = LinearLayoutManager(requireContext())

        subscribeUi()

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)

        return binding.root
    }

    private fun subscribeUi() {
        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state.mode) {
                DashboardViewModel.ScreenMode.NONE -> {
                    binding.loading.visibility = View.GONE
                    val adapter = AlbumAdapter(requireContext(), state.albums)
                    binding.rvAlbums.adapter = adapter
                }
                else -> binding.loading.visibility = View.VISIBLE
            }
        }
    }
}