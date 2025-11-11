@file:Suppress("UNCHECKED_CAST", "USELESS_CAST", "INAPPLICABLE_JVM_NAME", "UNUSED_ANONYMOUS_PARAMETER", "NAME_SHADOWING", "UNNECESSARY_NOT_NULL_ASSERTION")
package uni.UNICA45710
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
import io.dcloud.uniapp.extapi.`$emit` as uni__emit
import io.dcloud.uniapp.extapi.connectSocket as uni_connectSocket
import io.dcloud.uniapp.extapi.exit as uni_exit
import io.dcloud.uniapp.extapi.getStorageSync as uni_getStorageSync
import io.dcloud.uniapp.extapi.setStorageSync as uni_setStorageSync
import io.dcloud.uniapp.extapi.showToast as uni_showToast
val runBlock1 = run {
    __uniConfig.getAppStyles = fun(): Map<String, Map<String, Map<String, Any>>> {
        return GenApp.styles
    }
}
fun initRuntimeSocket(hosts: String, port: String, id: String): UTSPromise<SocketTask?> {
    if (hosts == "" || port == "" || id == "") {
        return UTSPromise.resolve(null)
    }
    return hosts.split(",").reduce<UTSPromise<SocketTask?>>(fun(promise: UTSPromise<SocketTask?>, host: String): UTSPromise<SocketTask?> {
        return promise.then(fun(socket): UTSPromise<SocketTask?> {
            if (socket != null) {
                return UTSPromise.resolve(socket)
            }
            return tryConnectSocket(host, port, id)
        }
        )
    }
    , UTSPromise.resolve(null))
}
val SOCKET_TIMEOUT: Number = 500
fun tryConnectSocket(host: String, port: String, id: String): UTSPromise<SocketTask?> {
    return UTSPromise(fun(resolve, reject){
        val socket = uni_connectSocket(ConnectSocketOptions(url = "ws://" + host + ":" + port + "/" + id, fail = fun(_) {
            resolve(null)
        }
        ))
        val timer = setTimeout(fun(){
            socket.close(CloseSocketOptions(code = 1006, reason = "connect timeout"))
            resolve(null)
        }
        , SOCKET_TIMEOUT)
        socket.onOpen(fun(e){
            clearTimeout(timer)
            resolve(socket)
        }
        )
        socket.onClose(fun(e){
            clearTimeout(timer)
            resolve(null)
        }
        )
        socket.onError(fun(e){
            clearTimeout(timer)
            resolve(null)
        }
        )
    }
    )
}
fun initRuntimeSocketService(): UTSPromise<Boolean> {
    val hosts: String = "198.18.0.1,192.168.31.254,127.0.0.1,172.21.48.1"
    val port: String = "8090"
    val id: String = "app-android_3vBxQ1"
    if (hosts == "" || port == "" || id == "") {
        return UTSPromise.resolve(false)
    }
    var socketTask: SocketTask? = null
    __registerWebViewUniConsole(fun(): String {
        return "!function(){\"use strict\";\"function\"==typeof SuppressedError&&SuppressedError;var e=[\"log\",\"warn\",\"error\",\"info\",\"debug\"],n=e.reduce((function(e,n){return e[n]=console[n].bind(console),e}),{}),t=null,r=new Set,o={};function i(e){if(null!=t){var n=e.map((function(e){if(\"string\"==typeof e)return e;var n=e&&\"promise\"in e&&\"reason\"in e,t=n?\"UnhandledPromiseRejection: \":\"\";if(n&&(e=e.reason),e instanceof Error&&e.stack)return e.message&&!e.stack.includes(e.message)?\"\".concat(t).concat(e.message,\"\\n\").concat(e.stack):\"\".concat(t).concat(e.stack);if(\"object\"==typeof e&&null!==e)try{return t+JSON.stringify(e)}catch(e){return t+String(e)}return t+String(e)})).filter(Boolean);n.length>0&&t(JSON.stringify(Object.assign({type:\"error\",data:n},o)))}else e.forEach((function(e){r.add(e)}))}function a(e,n){try{return{type:e,args:u(n)}}catch(e){}return{type:e,args:[]}}function u(e){return e.map((function(e){return c(e)}))}function c(e,n){if(void 0===n&&(n=0),n>=7)return{type:\"object\",value:\"[Maximum depth reached]\"};switch(typeof e){case\"string\":return{type:\"string\",value:e};case\"number\":return function(e){return{type:\"number\",value:String(e)}}(e);case\"boolean\":return function(e){return{type:\"boolean\",value:String(e)}}(e);case\"object\":try{return function(e,n){if(null===e)return{type:\"null\"};if(function(e){return e.\$&&s(e.\$)}(e))return function(e,n){return{type:\"object\",className:\"ComponentPublicInstance\",value:{properties:Object.entries(e.\$.type).map((function(e){return f(e[0],e[1],n+1)}))}}}(e,n);if(s(e))return function(e,n){return{type:\"object\",className:\"ComponentInternalInstance\",value:{properties:Object.entries(e.type).map((function(e){return f(e[0],e[1],n+1)}))}}}(e,n);if(function(e){return e.style&&null!=e.tagName&&null!=e.nodeName}(e))return function(e,n){return{type:\"object\",value:{properties:Object.entries(e).filter((function(e){var n=e[0];return[\"id\",\"tagName\",\"nodeName\",\"dataset\",\"offsetTop\",\"offsetLeft\",\"style\"].includes(n)})).map((function(e){return f(e[0],e[1],n+1)}))}}}(e,n);if(function(e){return\"function\"==typeof e.getPropertyValue&&\"function\"==typeof e.setProperty&&e.\$styles}(e))return function(e,n){return{type:\"object\",value:{properties:Object.entries(e.\$styles).map((function(e){return f(e[0],e[1],n+1)}))}}}(e,n);if(Array.isArray(e))return{type:\"object\",subType:\"array\",value:{properties:e.map((function(e,t){return function(e,n,t){var r=c(e,t);return r.name=\"\".concat(n),r}(e,t,n+1)}))}};if(e instanceof Set)return{type:\"object\",subType:\"set\",className:\"Set\",description:\"Set(\".concat(e.size,\")\"),value:{entries:Array.from(e).map((function(e){return function(e,n){return{value:c(e,n)}}(e,n+1)}))}};if(e instanceof Map)return{type:\"object\",subType:\"map\",className:\"Map\",description:\"Map(\".concat(e.size,\")\"),value:{entries:Array.from(e.entries()).map((function(e){return function(e,n){return{key:c(e[0],n),value:c(e[1],n)}}(e,n+1)}))}};if(e instanceof Promise)return{type:\"object\",subType:\"promise\",value:{properties:[]}};if(e instanceof RegExp)return{type:\"object\",subType:\"regexp\",value:String(e),className:\"Regexp\"};if(e instanceof Date)return{type:\"object\",subType:\"date\",value:String(e),className:\"Date\"};if(e instanceof Error)return{type:\"object\",subType:\"error\",value:e.message||String(e),className:e.name||\"Error\"};var t=void 0,r=e.constructor;r&&r.get\$UTSMetadata\$&&(t=r.get\$UTSMetadata\$().name);var o=Object.entries(e);(function(e){return e.modifier&&e.modifier._attribute&&e.nodeContent})(e)&&(o=o.filter((function(e){var n=e[0];return\"modifier\"!==n&&\"nodeContent\"!==n})));return{type:\"object\",className:t,value:{properties:o.map((function(e){return f(e[0],e[1],n+1)}))}}}(e,n)}catch(e){return{type:\"object\",value:{properties:[]}}}case\"undefined\":return{type:\"undefined\"};case\"function\":return function(e){return{type:\"function\",value:\"function \".concat(e.name,\"() {}\")}}(e);case\"symbol\":return function(e){return{type:\"symbol\",value:e.description}}(e);case\"bigint\":return function(e){return{type:\"bigint\",value:String(e)}}(e)}}function s(e){return e.type&&null!=e.uid&&e.appContext}function f(e,n,t){var r=c(n,t);return r.name=e,r}var l=null,p=[],y={},g=\"---BEGIN:EXCEPTION---\",d=\"---END:EXCEPTION---\";function v(e){null!=l?l(JSON.stringify(Object.assign({type:\"console\",data:e},y))):p.push.apply(p,e)}var m=/^\\s*at\\s+[\\w/./-]+:\\d+\$/;function b(){function t(e){return function(){for(var t=[],r=0;r<arguments.length;r++)t[r]=arguments[r];var o=function(e,n,t){if(t||2===arguments.length)for(var r,o=0,i=n.length;o<i;o++)!r&&o in n||(r||(r=Array.prototype.slice.call(n,0,o)),r[o]=n[o]);return e.concat(r||Array.prototype.slice.call(n))}([],t,!0);if(o.length){var u=o[o.length-1];\"string\"==typeof u&&m.test(u)&&o.pop()}if(n[e].apply(n,o),\"error\"===e&&1===t.length){var c=t[0];if(\"string\"==typeof c&&c.startsWith(g)){var s=g.length,f=c.length-d.length;return void i([c.slice(s,f)])}if(c instanceof Error)return void i([c])}v([a(e,t)])}}return function(){var e=console.log,n=Symbol();try{console.log=n}catch(e){return!1}var t=console.log===n;return console.log=e,t}()?(e.forEach((function(e){console[e]=t(e)})),function(){e.forEach((function(e){console[e]=n[e]}))}):function(){}}function _(e){var n={type:\"WEB_INVOKE_APPSERVICE\",args:{data:{name:\"console\",arg:e}}};return window.__uniapp_x_postMessageToService?window.__uniapp_x_postMessageToService(n):window.__uniapp_x_.postMessageToService(JSON.stringify(n))}!function(){if(!window.__UNI_CONSOLE_WEBVIEW__){window.__UNI_CONSOLE_WEBVIEW__=!0;var e=\"[web-view]\".concat(window.__UNI_PAGE_ROUTE__?\"[\".concat(window.__UNI_PAGE_ROUTE__,\"]\"):\"\");b(),function(e,n){if(void 0===n&&(n={}),l=e,Object.assign(y,n),null!=e&&p.length>0){var t=p.slice();p.length=0,v(t)}}((function(e){_(e)}),{channel:e}),function(e,n){if(void 0===n&&(n={}),t=e,Object.assign(o,n),null!=e&&r.size>0){var a=Array.from(r);r.clear(),i(a)}}((function(e){_(e)}),{channel:e}),window.addEventListener(\"error\",(function(e){i([e.error])})),window.addEventListener(\"unhandledrejection\",(function(e){i([e])}))}}()}();"
    }
    , fun(data: String){
        socketTask?.send(SendSocketMessageOptions(data = data))
    }
    )
    return UTSPromise.resolve().then(fun(): UTSPromise<Boolean> {
        return initRuntimeSocket(hosts, port, id).then(fun(socket): Boolean {
            if (socket == null) {
                return false
            }
            socketTask = socket
            return true
        }
        )
    }
    ).`catch`(fun(): Boolean {
        return false
    }
    )
}
val runBlock2 = run {
    initRuntimeSocketService()
}
var firstBackTime: Number = 0
open class GenApp : BaseApp {
    constructor(__ins: ComponentInternalInstance) : super(__ins) {
        onLaunch(fun(_: OnLaunchOptions) {
            console.log("App Launch", " at App.uvue:5")
        }
        , __ins)
        onAppShow(fun(_: OnShowOptions) {
            console.log("App Show", " at App.uvue:8")
        }
        , __ins)
        onAppHide(fun() {
            console.log("App Hide", " at App.uvue:11")
        }
        , __ins)
        onLastPageBackPress(fun() {
            console.log("App LastPageBackPress", " at App.uvue:15")
            if (firstBackTime == 0) {
                uni_showToast(ShowToastOptions(title = "再按一次退出应用", position = "bottom"))
                firstBackTime = Date.now()
                setTimeout(fun(){
                    firstBackTime = 0
                }, 2000)
            } else if (Date.now() - firstBackTime < 2000) {
                firstBackTime = Date.now()
                uni_exit(null)
            }
        }
        , __ins)
        onExit(fun() {
            console.log("App Exit", " at App.uvue:32")
        }
        , __ins)
    }
    companion object {
        val styles: Map<String, Map<String, Map<String, Any>>> by lazy {
            normalizeCssStyles(utsArrayOf(
                styles0
            ))
        }
        val styles0: Map<String, Map<String, Map<String, Any>>>
            get() {
                return utsMapOf("uni-row" to padStyleMapOf(utsMapOf("flexDirection" to "row")), "uni-column" to padStyleMapOf(utsMapOf("flexDirection" to "column")), "wuxing-mu" to padStyleMapOf(utsMapOf("color" to "#28a745")), "wuxing-huo" to padStyleMapOf(utsMapOf("color" to "#dc3545")), "wuxing-tu" to padStyleMapOf(utsMapOf("color" to "#8b7355")), "wuxing-jin" to padStyleMapOf(utsMapOf("color" to "#ffd700")), "wuxing-shui" to padStyleMapOf(utsMapOf("color" to "#0d6efd")), "wuxing-mu-bg" to padStyleMapOf(utsMapOf("backgroundColor" to "#d4edda")), "wuxing-huo-bg" to padStyleMapOf(utsMapOf("backgroundColor" to "#f8d7da")), "wuxing-tu-bg" to padStyleMapOf(utsMapOf("backgroundColor" to "#e8dfd6")), "wuxing-jin-bg" to padStyleMapOf(utsMapOf("backgroundColor" to "#fff8dc")), "wuxing-shui-bg" to padStyleMapOf(utsMapOf("backgroundColor" to "#cfe2ff")), "tiangan-jia" to padStyleMapOf(utsMapOf("color" to "#28a745")), "tiangan-yi" to padStyleMapOf(utsMapOf("color" to "#28a745")), "tiangan-bing" to padStyleMapOf(utsMapOf("color" to "#dc3545")), "tiangan-ding" to padStyleMapOf(utsMapOf("color" to "#dc3545")), "tiangan-wu" to padStyleMapOf(utsMapOf("color" to "#8b7355")), "tiangan-ji" to padStyleMapOf(utsMapOf("color" to "#8b7355")), "tiangan-geng" to padStyleMapOf(utsMapOf("color" to "#ffd700")), "tiangan-xin" to padStyleMapOf(utsMapOf("color" to "#ffd700")), "tiangan-ren" to padStyleMapOf(utsMapOf("color" to "#0d6efd")), "tiangan-gui" to padStyleMapOf(utsMapOf("color" to "#0d6efd")), "dizhi-zi" to padStyleMapOf(utsMapOf("color" to "#0d6efd")), "dizhi-hai" to padStyleMapOf(utsMapOf("color" to "#0d6efd")), "dizhi-yin" to padStyleMapOf(utsMapOf("color" to "#28a745")), "dizhi-mao" to padStyleMapOf(utsMapOf("color" to "#28a745")), "dizhi-si" to padStyleMapOf(utsMapOf("color" to "#dc3545")), "dizhi-wu" to padStyleMapOf(utsMapOf("color" to "#dc3545")), "dizhi-shen" to padStyleMapOf(utsMapOf("color" to "#ffd700")), "dizhi-you" to padStyleMapOf(utsMapOf("color" to "#ffd700")), "dizhi-chou" to padStyleMapOf(utsMapOf("color" to "#8b7355")), "dizhi-chen" to padStyleMapOf(utsMapOf("color" to "#8b7355")), "dizhi-wei" to padStyleMapOf(utsMapOf("color" to "#8b7355")), "dizhi-xu" to padStyleMapOf(utsMapOf("color" to "#8b7355")), "theme-bg-primary" to padStyleMapOf(utsMapOf("backgroundColor" to "#f8f9fa")), "theme-bg-secondary" to padStyleMapOf(utsMapOf("backgroundColor" to "#ffffff")), "theme-text-primary" to padStyleMapOf(utsMapOf("color" to "#212529")), "theme-text-secondary" to padStyleMapOf(utsMapOf("color" to "#6c757d")), "theme-border" to padStyleMapOf(utsMapOf("borderTopColor" to "#dee2e6", "borderRightColor" to "#dee2e6", "borderBottomColor" to "#dee2e6", "borderLeftColor" to "#dee2e6")), "color-general" to padStyleMapOf(utsMapOf("color" to "#198754")), "color-heaven" to padStyleMapOf(utsMapOf("color" to "#0d6efd")), "color-earth" to padStyleMapOf(utsMapOf("color" to "#212529")), "color-branch" to padStyleMapOf(utsMapOf("color" to "#dc3545")), "color-shensha" to padStyleMapOf(utsMapOf("color" to "#6c757d")), "color-relation" to padStyleMapOf(utsMapOf("color" to "#495057")))
            }
    }
}
val GenAppClass = CreateVueAppComponent(GenApp::class.java, fun(): VueComponentOptions {
    return VueComponentOptions(type = "app", name = "", inheritAttrs = true, inject = Map(), props = Map(), propsNeedCastKeys = utsArrayOf(), emits = Map(), components = Map(), styles = GenApp.styles)
}
, fun(instance): GenApp {
    return GenApp(instance)
}
)
typealias ThemeType = String
typealias WuxingType = String
typealias TianganType = String
typealias DizhiType = String
val THEME_CONFIG: UTSJSONObject = object : UTSJSONObject(UTSSourceMapPosition("THEME_CONFIG", "utils/theme.uts", 14, 14)) {
    var light = object : UTSJSONObject() {
        var name = "浅色主题"
        var bgPrimary = "#f8f9fa"
        var bgSecondary = "#ffffff"
        var bgTertiary = "#e9ecef"
        var borderPrimary = "#dee2e6"
        var borderSecondary = "#ced4da"
        var borderLight = "#f1f3f5"
        var textPrimary = "#212529"
        var textSecondary = "#6c757d"
        var textTertiary = "#495057"
        var shadow = "rgba(0, 0, 0, 0.075)"
    }
    var dark = object : UTSJSONObject() {
        var name = "深色主题"
        var bgPrimary = "#1a1d20"
        var bgSecondary = "#2d3338"
        var bgTertiary = "#3e4347"
        var borderPrimary = "#495057"
        var borderSecondary = "#6c757d"
        var borderLight = "#495057"
        var textPrimary = "#f8f9fa"
        var textSecondary = "#adb5bd"
        var textTertiary = "#dee2e6"
        var shadow = "rgba(0, 0, 0, 0.3)"
    }
    var gold = object : UTSJSONObject() {
        var name = "黑金主题"
        var bgPrimary = "#0a0a0a"
        var bgSecondary = "#1a1a1a"
        var bgTertiary = "#2a2a2a"
        var borderPrimary = "#ffd700"
        var borderSecondary = "#daa520"
        var borderLight = "#3a3a3a"
        var textPrimary = "#ffd700"
        var textSecondary = "#daa520"
        var textTertiary = "#b8860b"
        var shadow = "rgba(255, 215, 0, 0.2)"
    }
    var green = object : UTSJSONObject() {
        var name = "护眼主题"
        var bgPrimary = "#e8f5e9"
        var bgSecondary = "#f1f8f4"
        var bgTertiary = "#c8e6c9"
        var borderPrimary = "#a5d6a7"
        var borderSecondary = "#81c784"
        var borderLight = "#dcedc8"
        var textPrimary = "#1b5e20"
        var textSecondary = "#388e3c"
        var textTertiary = "#4caf50"
        var shadow = "rgba(76, 175, 80, 0.1)"
    }
    var tech = object : UTSJSONObject() {
        var name = "科技主题"
        var bgPrimary = "#0d1117"
        var bgSecondary = "#161b22"
        var bgTertiary = "#21262d"
        var borderPrimary = "#30363d"
        var borderSecondary = "#00d9ff"
        var borderLight = "#30363d"
        var textPrimary = "#c9d1d9"
        var textSecondary = "#8b949e"
        var textTertiary = "#58a6ff"
        var shadow = "rgba(0, 217, 255, 0.15)"
    }
    var minimal = object : UTSJSONObject() {
        var name = "简洁主题"
        var bgPrimary = "#ffffff"
        var bgSecondary = "#ffffff"
        var bgTertiary = "#fafafa"
        var borderPrimary = "#e0e0e0"
        var borderSecondary = "#bdbdbd"
        var borderLight = "#f5f5f5"
        var textPrimary = "#212121"
        var textSecondary = "#757575"
        var textTertiary = "#9e9e9e"
        var shadow = "rgba(0, 0, 0, 0.05)"
    }
}
val WUXING_COLORS: UTSJSONObject = object : UTSJSONObject(UTSSourceMapPosition("WUXING_COLORS", "utils/theme.uts", 95, 14)) {
    var mu = object : UTSJSONObject() {
        var primary = "#28a745"
        var light = "#d4edda"
        var dark = "#198754"
    }
    var huo = object : UTSJSONObject() {
        var primary = "#dc3545"
        var light = "#f8d7da"
        var dark = "#b02a37"
    }
    var tu = object : UTSJSONObject() {
        var primary = "#8b7355"
        var light = "#e8dfd6"
        var dark = "#6b5644"
    }
    var jin = object : UTSJSONObject() {
        var primary = "#ffd700"
        var light = "#fff8dc"
        var dark = "#daa520"
    }
    var shui = object : UTSJSONObject() {
        var primary = "#0d6efd"
        var light = "#cfe2ff"
        var dark = "#0a58ca"
    }
}
val TIANGAN_WUXING_MAP: Map<TianganType, WuxingType> = Map(utsArrayOf(
    utsArrayOf(
        "甲",
        "mu"
    ),
    utsArrayOf(
        "乙",
        "mu"
    ),
    utsArrayOf(
        "丙",
        "huo"
    ),
    utsArrayOf(
        "丁",
        "huo"
    ),
    utsArrayOf(
        "戊",
        "tu"
    ),
    utsArrayOf(
        "己",
        "tu"
    ),
    utsArrayOf(
        "庚",
        "jin"
    ),
    utsArrayOf(
        "辛",
        "jin"
    ),
    utsArrayOf(
        "壬",
        "shui"
    ),
    utsArrayOf(
        "癸",
        "shui"
    )
))
val DIZHI_WUXING_MAP: Map<DizhiType, WuxingType> = Map(utsArrayOf(
    utsArrayOf(
        "子",
        "shui"
    ),
    utsArrayOf(
        "丑",
        "tu"
    ),
    utsArrayOf(
        "寅",
        "mu"
    ),
    utsArrayOf(
        "卯",
        "mu"
    ),
    utsArrayOf(
        "辰",
        "tu"
    ),
    utsArrayOf(
        "巳",
        "huo"
    ),
    utsArrayOf(
        "午",
        "huo"
    ),
    utsArrayOf(
        "未",
        "tu"
    ),
    utsArrayOf(
        "申",
        "jin"
    ),
    utsArrayOf(
        "酉",
        "jin"
    ),
    utsArrayOf(
        "戌",
        "tu"
    ),
    utsArrayOf(
        "亥",
        "shui"
    )
))
open class ThemeManager : IUTSSourceMap {
    override fun `__$getOriginalPosition`(): UTSSourceMapPosition? {
        return UTSSourceMapPosition("ThemeManager", "utils/theme.uts", 151, 7)
    }
    private var currentTheme: ThemeType = "light"
    open fun getTheme(): ThemeType {
        return this.currentTheme
    }
    open fun setTheme(theme: ThemeType) {
        this.currentTheme = theme
        uni__emit("themeChanged", theme)
    }
    open fun toggleTheme() {
        this.currentTheme = if (this.currentTheme === "light") {
            "dark"
        } else {
            "light"
        }
        uni__emit("themeChanged", this.currentTheme)
    }
    open fun getThemeConfig(): Any {
        return THEME_CONFIG[this.currentTheme]
    }
    open fun getTianganColor(tiangan: TianganType, variant: String = "primary"): String {
        val wuxing = TIANGAN_WUXING_MAP.get(tiangan)
        if (wuxing) {
            return WUXING_COLORS[wuxing][variant]
        }
        return "#212529"
    }
    open fun getDizhiColor(dizhi: DizhiType, variant: String = "primary"): String {
        val wuxing = DIZHI_WUXING_MAP.get(dizhi)
        if (wuxing) {
            return WUXING_COLORS[wuxing][variant]
        }
        return "#212529"
    }
    open fun getWuxingColor(wuxing: WuxingType, variant: String = "primary"): String {
        return WUXING_COLORS[wuxing][variant]
    }
}
val themeManager = ThemeManager()
open class ThemeStore : IUTSSourceMap {
    override fun `__$getOriginalPosition`(): UTSSourceMapPosition? {
        return UTSSourceMapPosition("ThemeStore", "store/theme.uts", 7, 7)
    }
    open var currentTheme: ThemeType = "light"
    open var enableWuxingColors: Boolean = true
    open fun getThemeConfig(): Any {
        return THEME_CONFIG[this.currentTheme]
    }
    open fun setTheme(theme: ThemeType) {
        this.currentTheme = theme
        uni_setStorageSync("theme", theme)
        uni__emit("themeChanged", theme)
    }
    open fun toggleTheme() {
        val newTheme: ThemeType = if (this.currentTheme === "light") {
            "dark"
        } else {
            "light"
        }
        this.setTheme(newTheme)
    }
    open fun getAllThemes(): UTSArray<ThemeType> {
        return utsArrayOf(
            "light",
            "dark",
            "gold",
            "green",
            "tech",
            "minimal"
        )
    }
    open fun getThemeName(): String {
        return THEME_CONFIG[this.currentTheme].name
    }
    open fun toggleWuxingColors() {
        this.enableWuxingColors = !this.enableWuxingColors
        uni_setStorageSync("enableWuxingColors", this.enableWuxingColors)
        uni__emit("wuxingColorsChanged", this.enableWuxingColors)
    }
    open fun getTianganColor(tiangan: TianganType, variant: String = "primary"): String {
        if (!this.enableWuxingColors) {
            return this.getThemeConfig().textPrimary
        }
        val wuxing = TIANGAN_WUXING_MAP.get(tiangan)
        if (wuxing != null) {
            return WUXING_COLORS[wuxing][variant]
        }
        return this.getThemeConfig().textPrimary
    }
    open fun getDizhiColor(dizhi: DizhiType, variant: String = "primary"): String {
        if (!this.enableWuxingColors) {
            return this.getThemeConfig().textPrimary
        }
        val wuxing = DIZHI_WUXING_MAP.get(dizhi)
        if (wuxing != null) {
            return WUXING_COLORS[wuxing][variant]
        }
        return this.getThemeConfig().textPrimary
    }
    open fun getWuxingColor(wuxing: WuxingType, variant: String = "primary"): String {
        return WUXING_COLORS[wuxing][variant]
    }
    open fun init() {
        val savedTheme = uni_getStorageSync("theme")
        val validThemes = utsArrayOf(
            "light",
            "dark",
            "gold",
            "green",
            "tech",
            "minimal"
        ) as UTSArray<ThemeType>
        if (savedTheme != null && validThemes.includes(savedTheme as ThemeType)) {
            this.currentTheme = savedTheme as ThemeType
        }
        val savedWuxingColors = uni_getStorageSync("enableWuxingColors")
        if (savedWuxingColors != null) {
            this.enableWuxingColors = savedWuxingColors as Boolean
        }
    }
}
val themeStore = ThemeStore()
val GenPagesIndexIndexClass = CreateVueComponent(GenPagesIndexIndex::class.java, fun(): VueComponentOptions {
    return VueComponentOptions(type = "page", name = "", inheritAttrs = GenPagesIndexIndex.inheritAttrs, inject = GenPagesIndexIndex.inject, props = GenPagesIndexIndex.props, propsNeedCastKeys = GenPagesIndexIndex.propsNeedCastKeys, emits = GenPagesIndexIndex.emits, components = GenPagesIndexIndex.components, styles = GenPagesIndexIndex.styles)
}
, fun(instance, renderer): GenPagesIndexIndex {
    return GenPagesIndexIndex(instance, renderer)
}
)
val GenPagesDalurenDalurenClass = CreateVueComponent(GenPagesDalurenDaluren::class.java, fun(): VueComponentOptions {
    return VueComponentOptions(type = "page", name = "", inheritAttrs = GenPagesDalurenDaluren.inheritAttrs, inject = GenPagesDalurenDaluren.inject, props = GenPagesDalurenDaluren.props, propsNeedCastKeys = GenPagesDalurenDaluren.propsNeedCastKeys, emits = GenPagesDalurenDaluren.emits, components = GenPagesDalurenDaluren.components, styles = GenPagesDalurenDaluren.styles)
}
, fun(instance, renderer): GenPagesDalurenDaluren {
    return GenPagesDalurenDaluren(instance, renderer)
}
)
val GenComponentsThemeSettingsClass = CreateVueComponent(GenComponentsThemeSettings::class.java, fun(): VueComponentOptions {
    return VueComponentOptions(type = "component", name = "", inheritAttrs = GenComponentsThemeSettings.inheritAttrs, inject = GenComponentsThemeSettings.inject, props = GenComponentsThemeSettings.props, propsNeedCastKeys = GenComponentsThemeSettings.propsNeedCastKeys, emits = GenComponentsThemeSettings.emits, components = GenComponentsThemeSettings.components, styles = GenComponentsThemeSettings.styles)
}
, fun(instance, renderer): GenComponentsThemeSettings {
    return GenComponentsThemeSettings(instance)
}
)
val GenPagesSettingsSettingsClass = CreateVueComponent(GenPagesSettingsSettings::class.java, fun(): VueComponentOptions {
    return VueComponentOptions(type = "page", name = "", inheritAttrs = GenPagesSettingsSettings.inheritAttrs, inject = GenPagesSettingsSettings.inject, props = GenPagesSettingsSettings.props, propsNeedCastKeys = GenPagesSettingsSettings.propsNeedCastKeys, emits = GenPagesSettingsSettings.emits, components = GenPagesSettingsSettings.components, styles = GenPagesSettingsSettings.styles)
}
, fun(instance, renderer): GenPagesSettingsSettings {
    return GenPagesSettingsSettings(instance, renderer)
}
)
fun createApp(): UTSJSONObject {
    val app = createSSRApp(GenAppClass)
    themeStore.init()
    return UTSJSONObject(Map<String, Any?>(utsArrayOf(
        utsArrayOf(
            "app",
            app
        )
    )))
}
fun main(app: IApp) {
    definePageRoutes()
    defineAppConfig()
    (createApp()["app"] as VueApp).mount(app, GenUniApp())
}
open class UniAppConfig : io.dcloud.uniapp.appframe.AppConfig {
    override var name: String = "zhouyi_front"
    override var appid: String = "__UNI__CA45710"
    override var versionName: String = "1.0.0"
    override var versionCode: String = "100"
    override var uniCompilerVersion: String = "4.66"
    constructor() : super() {}
}
fun definePageRoutes() {
    __uniRoutes.push(UniPageRoute(path = "pages/index/index", component = GenPagesIndexIndexClass, meta = UniPageMeta(isQuit = true), style = utsMapOf("navigationBarTitleText" to "周易实验室")))
    __uniRoutes.push(UniPageRoute(path = "pages/daluren/daluren", component = GenPagesDalurenDalurenClass, meta = UniPageMeta(isQuit = false), style = utsMapOf("navigationBarTitleText" to "大六壬排盘", "navigationBarBackgroundColor" to "#ffffff", "navigationBarTextStyle" to "black")))
    __uniRoutes.push(UniPageRoute(path = "pages/settings/settings", component = GenPagesSettingsSettingsClass, meta = UniPageMeta(isQuit = false), style = utsMapOf("navigationBarTitleText" to "主题设置", "navigationBarBackgroundColor" to "#ffffff", "navigationBarTextStyle" to "black")))
}
val __uniLaunchPage: Map<String, Any?> = utsMapOf("url" to "pages/index/index", "style" to utsMapOf("navigationBarTitleText" to "周易实验室"))
fun defineAppConfig() {
    __uniConfig.entryPagePath = "/pages/index/index"
    __uniConfig.globalStyle = utsMapOf("navigationBarTextStyle" to "black", "navigationBarTitleText" to "周易实验室", "navigationBarBackgroundColor" to "#F8F8F8", "backgroundColor" to "#F8F8F8")
    __uniConfig.getTabBarConfig = fun(): Map<String, Any>? {
        return null
    }
    __uniConfig.tabBar = __uniConfig.getTabBarConfig()
    __uniConfig.conditionUrl = ""
    __uniConfig.uniIdRouter = utsMapOf()
    __uniConfig.ready = true
}
open class GenUniApp : UniAppImpl() {
    open val vm: GenApp?
        get() {
            return getAppVm() as GenApp?
        }
    open val `$vm`: GenApp?
        get() {
            return getAppVm() as GenApp?
        }
}
fun getApp(): GenUniApp {
    return getUniApp() as GenUniApp
}
