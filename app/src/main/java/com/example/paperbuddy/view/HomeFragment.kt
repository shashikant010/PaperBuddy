package com.example.paperbuddy.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.paperbuddy.R
import com.example.paperbuddy.model.QuestionPaper
import com.example.paperbuddy.view.adapter.PaperListAdapter
import com.example.paperbuddy.viewmodel.HomeViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HomeFragment : Fragment() {

    // 1. Get reference to the ViewModel
    private val viewModel: HomeViewModel by viewModels()

    private lateinit var paperListAdapter: PaperListAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var fab: FloatingActionButton
    private lateinit var emptyStateText: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 2. Find Views
        recyclerView = view.findViewById(R.id.recycler_view_papers)
        fab = view.findViewById(R.id.fab_generate_new)
        emptyStateText = view.findViewById(R.id.text_empty_state)

        // 3. Setup RecyclerView
        setupRecyclerView()

        // 4. Setup Click Listeners
        fab.setOnClickListener {
            // Use the NavController to go to GenerationFragment
            findNavController().navigate(R.id.action_homeFragment_to_generationFragment)
        }

        // 5. Observe data from ViewModel
        observePaperList()

        // 6. (For testing) Load dummy data
        viewModel.loadDummyData()
    }

    private fun setupRecyclerView() {
        // Initialize the adapter with a click listener
        paperListAdapter = PaperListAdapter { paper ->
            // This lambda is called when a paper is clicked
            onPaperClicked(paper)
        }
        recyclerView.adapter = paperListAdapter
        // We already set the LayoutManager in the XML
    }

    private fun observePaperList() {
        // When the 'papers' LiveData changes, this code runs
        viewModel.papers.observe(viewLifecycleOwner) { paperList ->
            if (paperList.isEmpty()) {
                // Show empty state
                recyclerView.visibility = View.GONE
                emptyStateText.visibility = View.VISIBLE
            } else {
                // Show list
                recyclerView.visibility = View.VISIBLE
                emptyStateText.visibility = View.GONE
                paperListAdapter.submitList(paperList) // Submit new list to adapter
            }
        }
    }

    private fun onPaperClicked(paper: QuestionPaper) {
        // For now, just show a Toast.
        // In a later step, we will show the OptionsBottomSheet.
        Toast.makeText(context, "Clicked on: ${paper.title}", Toast.LENGTH_SHORT).show()
    }
}