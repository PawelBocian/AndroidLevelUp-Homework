package pl.daftacademy.androidlevelup.view.movies

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.fragment_movies.*
import pl.daftacademy.androidlevelup.R
import pl.daftacademy.androidlevelup.view.viewmodel.MoviesViewModel


class MoviesFragment(genre: String) : Fragment() {

    private val viewModel by lazy { ViewModelProviders.of(this)[MoviesViewModel::class.java] }
    private val adapter = MoviesAdapter()
    private var gen : String? = null;

    init{
        this.gen = genre
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_movies, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView.adapter = adapter
        adapter.items = viewModel.getMovies()
        if(!gen.equals("All"))
        adapter.items = adapter.items.filter { it.genres.contains(this.gen) }
    }

    companion object {
        fun create(genre: String): MoviesFragment {
            val fragment = MoviesFragment(genre)
            return fragment
        }
    }
}
