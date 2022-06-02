package com.example.myapplication.view

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.MyApp
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentMainBinding
import com.example.myapplication.model.URLsResp
import com.example.myapplication.vm.AppVMFactory
import com.example.myapplication.vm.MainViewModel
import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper
import javax.inject.Inject
import kotlin.math.floor


class MainFragment : Fragment()
{
    @Inject
    lateinit var factory:AppVMFactory
    private var _b: FragmentMainBinding? = null
    private val b get() = _b!!
    private lateinit var vm: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.application as MyApp).appComponent.inject(this)
        vm = ViewModelProvider(this, factory)[MainViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _b = FragmentMainBinding.inflate(inflater, container, false)
        b.nightBtn.setOnClickListener{(activity as MainActivity).nightModeSwitch()}
        return b.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.data.observe(viewLifecycleOwner) { r -> processResp(r)  }
    }
    private fun processResp(r: URLsResp)
    {
        b.loading.visibility = View.GONE
        if(r.isError) showError(r.errMessage )
        else if(r.urls.size==0) showError( getString(R.string.err_empty_urls) )
        else showRecycler(r.urls)
    }

    private fun showError(errMessage: String?) {
        b.error.visibility = View.VISIBLE
        b.error.text = errMessage
    }

    private fun showRecycler(urls: MutableSet<String>) {
        b.error.visibility = View.GONE
        with(b.recycler)
        {
            visibility = View.VISIBLE
            adapter = PicturesAdapter(requireContext(), urls) { url -> (activity as MainActivity).nav.navigate(MainFragmentDirections.actionMainFragmentToImageFragment(url)) }

            addItemDecoration(ItemOffsetDecoration(resources.getDimension(com.example.myapplication.R.dimen.grid_gap)
                .toInt()))
            val helper = GravitySnapHelper(Gravity.TOP)
            helper.attachToRecyclerView(this)

            viewTreeObserver.addOnGlobalLayoutListener(
                object : OnGlobalLayoutListener {
                    override fun onGlobalLayout() {
                        viewTreeObserver.removeOnGlobalLayoutListener(this)
                        val viewWidth: Int = b.recycler.measuredWidth
                        val cardViewWidth =
                            activity!!.resources.getDimension(com.example.myapplication.R.dimen.thumbnail_size)
                        val newSpanCount = floor((viewWidth / cardViewWidth).toDouble()).toInt()
                        layoutManager = GridLayoutManager(requireContext(), newSpanCount,  RecyclerView.VERTICAL,  false )
                        requestLayout()
                    }
                })

        }
    }

}