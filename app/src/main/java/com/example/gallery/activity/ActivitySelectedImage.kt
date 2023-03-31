package com.example.gallery.activity

import android.net.Uri
import android.view.LayoutInflater
import androidx.recyclerview.widget.GridLayoutManager
import com.example.gallery.adapter.SelectedImageAdapter
import com.example.gallery.base.BaseActivity
import com.example.gallery.databinding.ActivitySelectedImageBinding

class ActivitySelectedImage : BaseActivity<ActivitySelectedImageBinding>() {

    private val adapter = SelectedImageAdapter()

    override fun inflateVB(inflater: LayoutInflater): ActivitySelectedImageBinding {
        return ActivitySelectedImageBinding.inflate(inflater)
    }

    override fun initListener() {
        val uri: ArrayList<Uri>? = intent.getParcelableArrayListExtra(MainActivity.KEY_IMG)
        if (uri != null) {
            adapter.addImage(uri)
        }
    }

    override fun initView() {
        binding.selectedRecycler.layoutManager = GridLayoutManager(this@ActivitySelectedImage, 3)
        binding.selectedRecycler.adapter = adapter
    }
}