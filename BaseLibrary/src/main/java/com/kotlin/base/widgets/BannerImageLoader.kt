package com.kotlin.base.widgets

import android.content.Context
import android.widget.ImageView
import com.kotlin.base.utils.GlideUtils
import com.youth.banner.loader.ImageLoader

/**
 * @author  桂雁彬
 * @date 2019/3/19.
 * GitHub:
 * email：yanbing.gui@zymobi.com
 * description：
 */
class BannerImageLoader : ImageLoader() {
    override fun displayImage(context: Context, path: Any, imageView: ImageView) {
        GlideUtils.loadUrlImage(context, path.toString(), imageView)
    }
}
