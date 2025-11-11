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
open class GenUniModulesTestImagePathComponentsTestImagePathTestImagePath : VueComponent {
    constructor(__ins: ComponentInternalInstance) : super(__ins) {}
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _cache = this.`$`.renderCache
        return createElementVNode("view", null, utsArrayOf(
            createElementVNode("view", null, utsArrayOf(
                createElementVNode("text", utsMapOf("class" to "uni-subtitle-text"), "uni_modules的组件模板相对路径静态引入插件内static目录图片"),
                createElementVNode("view", utsMapOf("class" to "uni-center", "style" to normalizeStyle(utsMapOf("background" to "#FFFFFF"))), utsArrayOf(
                    createElementVNode("image", utsMapOf("class" to "image", "mode" to "widthFix", "src" to default3))
                ), 4)
            )),
            createElementVNode("view", null, utsArrayOf(
                createElementVNode("text", utsMapOf("class" to "uni-subtitle-text"), "uni_modules的组件模板绝对路径静态引入插件内static目录图片"),
                createElementVNode("view", utsMapOf("class" to "uni-center", "style" to normalizeStyle(utsMapOf("background" to "#FFFFFF"))), utsArrayOf(
                    createElementVNode("image", utsMapOf("class" to "image", "mode" to "widthFix", "src" to "/uni_modules/test-image-path/static/test-image-path.png"))
                ), 4)
            ))
        ))
    }
    companion object {
        val styles: Map<String, Map<String, Map<String, Any>>> by lazy {
            normalizeCssStyles(utsArrayOf(
                styles0
            ))
        }
        val styles0: Map<String, Map<String, Map<String, Any>>>
            get() {
                return utsMapOf("image" to padStyleMapOf(utsMapOf("marginTop" to 20, "marginRight" to "auto", "marginBottom" to 20, "marginLeft" to "auto", "width" to 100)))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
