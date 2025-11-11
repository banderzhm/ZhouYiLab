@file:Suppress("UNCHECKED_CAST", "USELESS_CAST", "INAPPLICABLE_JVM_NAME", "UNUSED_ANONYMOUS_PARAMETER", "NAME_SHADOWING", "UNNECESSARY_NOT_NULL_ASSERTION")
package uni.UNI740BA18
import io.dcloud.uniapp.*
import io.dcloud.uniapp.extapi.*
import io.dcloud.uniapp.framework.*
import io.dcloud.uniapp.runtime.*
import io.dcloud.uniapp.vue.*
import io.dcloud.uniapp.vue.shared.*
import io.dcloud.unicloud.*
import io.dcloud.uts.*
import io.dcloud.uts.Map
import io.dcloud.uts.Set
import io.dcloud.uts.UTSAndroid
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
open class GenPagesComponentImageImageMode : BasePage {
    constructor(__ins: ComponentInternalInstance, __renderer: String?) : super(__ins, __renderer) {
        onLoad(fun(_: OnLoadOptions) {
            stat_instance.onLoad(this)
        }
        , __ins)
        onPageShow(fun() {
            stat_instance.onShow(this)
        }
        , __ins)
        onPageHide(fun() {
            stat_instance.onHide(this)
        }
        , __ins)
        onUnload(fun() {
            stat_instance.onUnload(this)
        }
        , __ins)
    }
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this
        val _cache = this.`$`.renderCache
        val _component_page_head = resolveEasyComponent("page-head", GenComponentsPageHeadPageHeadClass)
        return createElementVNode("scroll-view", utsMapOf("style" to normalizeStyle(utsMapOf("flex" to "1"))), utsArrayOf(
            createVNode(_component_page_head, utsMapOf("title" to _ctx.title), null, 8, utsArrayOf(
                "title"
            )),
            createElementVNode("view", utsMapOf("class" to "uni-padding-wrap uni-common-mt"), utsArrayOf(
                createElementVNode("view", utsMapOf("class" to "uni-title"), utsArrayOf(
                    createElementVNode("text", utsMapOf("class" to "uni-title-text"), "支持的图片缩放模式示例")
                )),
                createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.data, fun(item, index, __index, _cached): Any {
                    return createElementVNode("view", utsMapOf("key" to index), utsArrayOf(
                        createElementVNode("text", utsMapOf("class" to "uni-subtitle-text"), toDisplayString(item.mode) + ": " + toDisplayString(item.description), 1),
                        createElementVNode("view", utsMapOf("class" to "uni-center", "style" to normalizeStyle(utsMapOf("background" to "#FFFFFF"))), utsArrayOf(
                            createElementVNode("image", utsMapOf("class" to "image", "mode" to item.mode, "src" to "/static/shuijiao.jpg"), null, 8, utsArrayOf(
                                "mode"
                            ))
                        ), 4)
                    ))
                }
                ), 128),
                createElementVNode("view", utsMapOf("class" to "uni-title"), utsArrayOf(
                    createElementVNode("text", utsMapOf("class" to "uni-title-text"), "其他示例")
                )),
                createElementVNode("view", null, utsArrayOf(
                    createElementVNode("text", utsMapOf("class" to "uni-subtitle-text"), "同时设置mode和圆角"),
                    createElementVNode("view", utsMapOf("class" to "uni-center", "style" to normalizeStyle(utsMapOf("background" to "#FFFFFF"))), utsArrayOf(
                        createElementVNode("image", utsMapOf("class" to "image radius", "mode" to "heightFix", "src" to "/static/shuijiao.jpg"))
                    ), 4)
                )),
                createElementVNode("view", null, utsArrayOf(
                    createElementVNode("text", utsMapOf("class" to "uni-subtitle-text"), "设置图片width='100%'与mode='widthFix'"),
                    createElementVNode("view", utsMapOf("class" to "uni-center", "style" to normalizeStyle(utsMapOf("background" to "#FFFFFF"))), utsArrayOf(
                        createElementVNode("view", utsMapOf("class" to "uni-center", "style" to normalizeStyle(utsMapOf("background-color" to "red", "width" to "150px", "margin" to "20px"))), utsArrayOf(
                            createElementVNode("image", utsMapOf("style" to normalizeStyle(utsMapOf("width" to "100%")), "mode" to "widthFix", "src" to "/static/shuijiao.jpg"), null, 4)
                        ), 4)
                    ), 4)
                )),
                createElementVNode("view", null, utsArrayOf(
                    createElementVNode("text", utsMapOf("class" to "uni-subtitle-text"), "image默认mode"),
                    createElementVNode("view", utsMapOf("class" to "uni-center", "style" to normalizeStyle(utsMapOf("background" to "#FFFFFF"))), utsArrayOf(
                        createElementVNode("view", utsMapOf("class" to "uni-center", "style" to normalizeStyle(utsMapOf("margin" to "20px"))), utsArrayOf(
                            createElementVNode("image", utsMapOf("style" to normalizeStyle(utsMapOf("width" to "100px", "height" to "100px")), "src" to "/static/logo.png"), null, 4)
                        ), 4)
                    ), 4)
                ))
            ))
        ), 4)
    }
    open var title: String by `$data`
    open var data: UTSArray<ImageMode> by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("title" to "image-mode", "data" to utsArrayOf<ImageMode>(ImageMode(mode = "scaleToFill", description = "不保持纵横比缩放图片，使图片的宽高完全拉伸至填满 image 元素"), ImageMode(mode = "aspectFit", description = "保持纵横比缩放图片，使图片的长边能完全显示出来。也就是说，可以完整地将图片显示出来"), ImageMode(mode = "aspectFill", description = "保持纵横比缩放图片，只保证图片的短边能完全显示出来。也就是说，图片通常只在水平或垂直方向是完整的，另一个方向将会发生截取"), ImageMode(mode = "top", description = "不缩放图片，只显示图片的顶部区域"), ImageMode(mode = "bottom", description = "不缩放图片，只显示图片的底部区域"), ImageMode(mode = "center", description = "不缩放图片，只显示图片的中间区域"), ImageMode(mode = "left", description = "不缩放图片，只显示图片的左边区域"), ImageMode(mode = "right", description = "不缩放图片，只显示图片的右边区域"), ImageMode(mode = "top left", description = "不缩放图片，只显示图片的左上边区域"), ImageMode(mode = "top right", description = "不缩放图片，只显示图片的右上边区域"), ImageMode(mode = "bottom left", description = "不缩放图片，只显示图片的左下边区域"), ImageMode(mode = "bottom right", description = "不缩放图片，只显示图片的右下边区域"), ImageMode(mode = "widthFix", description = "宽度不变，高度自动变化，保持原图宽高比不变"), ImageMode(mode = "heightFix", description = "高度不变，宽度自动变化，保持原图宽高比不变")))
    }
    companion object {
        val styles: Map<String, Map<String, Map<String, Any>>> by lazy {
            normalizeCssStyles(utsArrayOf(
                styles0
            ), utsArrayOf(
                GenApp.styles
            ))
        }
        val styles0: Map<String, Map<String, Map<String, Any>>>
            get() {
                return utsMapOf("image" to padStyleMapOf(utsMapOf("marginTop" to 20, "marginRight" to "auto", "marginBottom" to 20, "marginLeft" to "auto", "width" to 100, "height" to 100, "backgroundColor" to "#eeeeee")), "radius" to padStyleMapOf(utsMapOf("borderTopLeftRadius" to 10, "borderTopRightRadius" to 10, "borderBottomRightRadius" to 10, "borderBottomLeftRadius" to 10)))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
