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
import io.dcloud.uniapp.extapi.showModal as uni_showModal
open class GenPagesComponentRichTextRichTextTags : BasePage {
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
        val _component_enum_data = resolveEasyComponent("enum-data", GenComponentsEnumDataEnumDataClass)
        return createElementVNode("scroll-view", utsMapOf("style" to normalizeStyle(utsMapOf("flex" to "1"))), utsArrayOf(
            createVNode(_component_page_head, utsMapOf("title" to "rich-text-tags")),
            createVNode(_component_enum_data, utsMapOf("title" to "设置mode（仅Android平台支持，默认值为web）", "items" to _ctx.modeItemTypes, "onChange" to _ctx.onModeChange), null, 8, utsArrayOf(
                "items",
                "onChange"
            )),
            createElementVNode("view", utsMapOf("class" to "uni-padding-wrap uni-common-mt"), utsArrayOf(
                createElementVNode("view", utsMapOf("class" to "uni-title"), utsArrayOf(
                    createElementVNode("text", utsMapOf("class" to "uni-title-text"), "支持的HTML标签")
                )),
                createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.data, fun(item, index, __index, _cached): Any {
                    return createElementVNode("view", utsMapOf("class" to "uni-common-mt", "key" to index), utsArrayOf(
                        createElementVNode("text", utsMapOf("class" to "uni-subtitle-text"), toDisplayString(item.name), 1),
                        createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("background" to "#FFFFFF"))), utsArrayOf(
                            createElementVNode("rich-text", utsMapOf("nodes" to item.html, "onItemclick" to _ctx.itemClick, "mode" to _ctx.mode), null, 40, utsArrayOf(
                                "nodes",
                                "onItemclick",
                                "mode"
                            ))
                        ), 4)
                    ))
                }
                ), 128)
            ))
        ), 4)
    }
    open var data: UTSArray<TagInfo> by `$data`
    open var mode: String by `$data`
    open var modeItems: UTSArray<String> by `$data`
    open var modeItemTypes: UTSArray<ItemType> by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("data" to utsArrayOf<TagInfo>(TagInfo(name = "<br/>", html = utsArrayOf<UTSJSONObject>(object : UTSJSONObject() {
            var name = "br"
        }, object : UTSJSONObject() {
            var type = "text"
            var text = "hello uni-app x"
        }, object : UTSJSONObject() {
            var name = "br"
        }, object : UTSJSONObject() {
            var type = "text"
            var text = "hello uni-app x"
        })), TagInfo(name = "<p/>", html = utsArrayOf<UTSJSONObject>(object : UTSJSONObject() {
            var name = "p"
            var attrs = object : UTSJSONObject() {
                var style = "text-align: center; text-decoration: line-through;"
            }
            var children = utsArrayOf(
                object : UTSJSONObject() {
                    var type = "text"
                    var text = "hello uni-app x"
                }
            )
        }, object : UTSJSONObject() {
            var name = "p"
            var attrs = object : UTSJSONObject() {
                var style = "background-color: green; color: red;"
            }
            var children = utsArrayOf(
                object : UTSJSONObject() {
                    var type = "text"
                    var text = "hello uni-app x"
                }
            )
        })), TagInfo(name = "<ul/> <li/>", html = utsArrayOf<UTSJSONObject>(object : UTSJSONObject() {
            var name = "ul"
            var children = utsArrayOf(
                object : UTSJSONObject() {
                    var name = "li"
                    var attrs = object : UTSJSONObject() {
                        var style = "text-decoration: line-through; text-align: center;"
                    }
                    var children = utsArrayOf(
                        object : UTSJSONObject() {
                            var type = "text"
                            var text = "hello uni-app x"
                        }
                    )
                },
                object : UTSJSONObject() {
                    var name = "li"
                    var attrs = object : UTSJSONObject() {
                        var style = "color: red; background-color: green; text-align: center;"
                    }
                    var children = utsArrayOf(
                        object : UTSJSONObject() {
                            var type = "text"
                            var text = "hello uni-app x"
                        }
                    )
                }
            )
        })), TagInfo(name = "<span/>", html = utsArrayOf<UTSJSONObject>(object : UTSJSONObject() {
            var name = "span"
            var attrs = object : UTSJSONObject() {
                var style = "color: red; background-color: green; text-decoration: line-through;"
            }
            var children = utsArrayOf(
                object : UTSJSONObject() {
                    var type = "text"
                    var text = "hello uni-app x"
                }
            )
        })), TagInfo(name = "<strong/>", html = utsArrayOf<UTSJSONObject>(object : UTSJSONObject() {
            var name = "strong"
            var children = utsArrayOf(
                object : UTSJSONObject() {
                    var type = "text"
                    var text = "hello uni-app x"
                }
            )
        })), TagInfo(name = "<i/>", html = utsArrayOf<UTSJSONObject>(object : UTSJSONObject() {
            var name = "i"
            var children = utsArrayOf(
                object : UTSJSONObject() {
                    var type = "text"
                    var text = "hello uni-app x"
                }
            )
        })), TagInfo(name = "<big/>", html = utsArrayOf<UTSJSONObject>(object : UTSJSONObject() {
            var name = "big"
            var children = utsArrayOf(
                object : UTSJSONObject() {
                    var type = "text"
                    var text = "hello uni-app x"
                }
            )
        })), TagInfo(name = "<small/>", html = utsArrayOf<UTSJSONObject>(object : UTSJSONObject() {
            var name = "small"
            var children = utsArrayOf(
                object : UTSJSONObject() {
                    var type = "text"
                    var text = "hello uni-app x"
                }
            )
        })), TagInfo(name = "<a/>", html = utsArrayOf<UTSJSONObject>(object : UTSJSONObject() {
            var name = "a"
            var attrs = object : UTSJSONObject() {
                var href = "https://www.baidu.com"
            }
            var children = utsArrayOf(
                object : UTSJSONObject() {
                    var type = "text"
                    var text = "百度"
                }
            )
        })), TagInfo(name = "<u/>", html = utsArrayOf<UTSJSONObject>(object : UTSJSONObject() {
            var name = "u"
            var children = utsArrayOf(
                object : UTSJSONObject() {
                    var type = "text"
                    var text = "hello uni-app x"
                }
            )
        })), TagInfo(name = "<del/>", html = utsArrayOf<UTSJSONObject>(object : UTSJSONObject() {
            var name = "del"
            var children = utsArrayOf(
                object : UTSJSONObject() {
                    var type = "text"
                    var text = "hello uni-app x"
                }
            )
        })), TagInfo(name = "<h1/> <h2/> <h3/> <h4/> <h5/> <h6/>", html = utsArrayOf<UTSJSONObject>(object : UTSJSONObject() {
            var name = "h1"
            var children = utsArrayOf(
                object : UTSJSONObject() {
                    var type = "text"
                    var text = "hello uni-app x"
                }
            )
        }, object : UTSJSONObject() {
            var name = "h2"
            var children = utsArrayOf(
                object : UTSJSONObject() {
                    var type = "text"
                    var text = "hello uni-app x"
                }
            )
        }, object : UTSJSONObject() {
            var name = "h3"
            var children = utsArrayOf(
                object : UTSJSONObject() {
                    var type = "text"
                    var text = "hello uni-app x"
                }
            )
        }, object : UTSJSONObject() {
            var name = "h4"
            var children = utsArrayOf(
                object : UTSJSONObject() {
                    var type = "text"
                    var text = "hello uni-app x"
                }
            )
        }, object : UTSJSONObject() {
            var name = "h5"
            var children = utsArrayOf(
                object : UTSJSONObject() {
                    var type = "text"
                    var text = "hello uni-app x"
                }
            )
        }, object : UTSJSONObject() {
            var name = "h6"
            var children = utsArrayOf(
                object : UTSJSONObject() {
                    var type = "text"
                    var text = "hello uni-app x"
                }
            )
        })), TagInfo(name = "带 # 内容", html = utsArrayOf<UTSJSONObject>(object : UTSJSONObject() {
            var name = "span"
            var attrs = object : UTSJSONObject() {
                var style = "color: #42b983;"
            }
            var children = utsArrayOf(
                object : UTSJSONObject() {
                    var type = "text"
                    var text = "span 标签 style 中使用 16 进制颜色 # 正常显示"
                }
            )
        })), TagInfo(name = "<img/>", html = utsArrayOf<UTSJSONObject>(object : UTSJSONObject() {
            var name = "img"
            var attrs = object : UTSJSONObject() {
                var src = "https://qiniu-web-assets.dcloud.net.cn/unidoc/zh/uni@2x.png"
                var width = "100"
                var height = "100"
            }
        }))), "mode" to "web", "modeItems" to utsArrayOf(
            "web",
            "native"
        ), "modeItemTypes" to utsArrayOf<ItemType>(ItemType(value = 0, name = "web"), ItemType(value = 1, name = "native")))
    }
    open var itemClick = ::gen_itemClick_fn
    open fun gen_itemClick_fn(e: UniRichTextItemClickEvent) {
        console.log(JSON.stringify(e.detail), " at pages/component/rich-text/rich-text-tags.uvue:328")
        var clicktext = ""
        if (e.detail.src != null) {
            clicktext = "点击了图片，src = " + e.detail.src
        } else if (e.detail.href != null) {
            clicktext = "点击了链接，href = " + e.detail.href
        }
        uni_showModal(ShowModalOptions(content = clicktext, showCancel = false))
    }
    open var onModeChange = ::gen_onModeChange_fn
    open fun gen_onModeChange_fn(value: Number) {
        this.mode = this.modeItems[value]
    }
    companion object {
        val styles: Map<String, Map<String, Map<String, Any>>> by lazy {
            normalizeCssStyles(utsArrayOf(), utsArrayOf(
                GenApp.styles
            ))
        }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
