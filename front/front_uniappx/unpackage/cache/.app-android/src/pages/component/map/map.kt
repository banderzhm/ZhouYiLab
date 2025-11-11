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
import io.dcloud.uniapp.extapi.createMapContext as uni_createMapContext
import io.dcloud.uniapp.extapi.showModal as uni_showModal
open class GenPagesComponentMapMap : BasePage {
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
    open var jestResult: TypeJestResult
        get() {
            return unref(this.`$exposed`["jestResult"]) as TypeJestResult
        }
        set(value) {
            setRefValue(this.`$exposed`, "jestResult", value)
        }
    open var autoTest: Boolean
        get() {
            return unref(this.`$exposed`["autoTest"]) as Boolean
        }
        set(value) {
            setRefValue(this.`$exposed`, "autoTest", value)
        }
    open var updateAutoTest: (value: Boolean) -> Unit
        get() {
            return unref(this.`$exposed`["updateAutoTest"]) as (value: Boolean) -> Unit
        }
        set(value) {
            setRefValue(this.`$exposed`, "updateAutoTest", value)
        }
    open var addControls: () -> Unit
        get() {
            return unref(this.`$exposed`["addControls"]) as () -> Unit
        }
        set(value) {
            setRefValue(this.`$exposed`, "addControls", value)
        }
    open var addMarkers: () -> Unit
        get() {
            return unref(this.`$exposed`["addMarkers"]) as () -> Unit
        }
        set(value) {
            setRefValue(this.`$exposed`, "addMarkers", value)
        }
    open var removeMarker: () -> Unit
        get() {
            return unref(this.`$exposed`["removeMarker"]) as () -> Unit
        }
        set(value) {
            setRefValue(this.`$exposed`, "removeMarker", value)
        }
    open var addPolyline: () -> Unit
        get() {
            return unref(this.`$exposed`["addPolyline"]) as () -> Unit
        }
        set(value) {
            setRefValue(this.`$exposed`, "addPolyline", value)
        }
    open var removePolyline: () -> Unit
        get() {
            return unref(this.`$exposed`["removePolyline"]) as () -> Unit
        }
        set(value) {
            setRefValue(this.`$exposed`, "removePolyline", value)
        }
    open var addPolygons: () -> Unit
        get() {
            return unref(this.`$exposed`["addPolygons"]) as () -> Unit
        }
        set(value) {
            setRefValue(this.`$exposed`, "addPolygons", value)
        }
    open var removePolygon: () -> Unit
        get() {
            return unref(this.`$exposed`["removePolygon"]) as () -> Unit
        }
        set(value) {
            setRefValue(this.`$exposed`, "removePolygon", value)
        }
    open var addCircles: () -> Unit
        get() {
            return unref(this.`$exposed`["addCircles"]) as () -> Unit
        }
        set(value) {
            setRefValue(this.`$exposed`, "addCircles", value)
        }
    open var removeCircle: () -> Unit
        get() {
            return unref(this.`$exposed`["removeCircle"]) as () -> Unit
        }
        set(value) {
            setRefValue(this.`$exposed`, "removeCircle", value)
        }
    open var includePoint: () -> Unit
        get() {
            return unref(this.`$exposed`["includePoint"]) as () -> Unit
        }
        set(value) {
            setRefValue(this.`$exposed`, "includePoint", value)
        }
    open var handleGetCenterLocation: () -> Unit
        get() {
            return unref(this.`$exposed`["handleGetCenterLocation"]) as () -> Unit
        }
        set(value) {
            setRefValue(this.`$exposed`, "handleGetCenterLocation", value)
        }
    open var handleGetRegion: () -> Unit
        get() {
            return unref(this.`$exposed`["handleGetRegion"]) as () -> Unit
        }
        set(value) {
            setRefValue(this.`$exposed`, "handleGetRegion", value)
        }
    open var handleTranslateMarker: () -> Unit
        get() {
            return unref(this.`$exposed`["handleTranslateMarker"]) as () -> Unit
        }
        set(value) {
            setRefValue(this.`$exposed`, "handleTranslateMarker", value)
        }
    open fun handleMoveToLocation(showModal: Boolean = true) {
        callKotlinFunction(this.`$exposed`["handleMoveToLocation"]!!, utsArrayOf(
            showModal
        ))
    }
    open var handleGetScale: () -> Unit
        get() {
            return unref(this.`$exposed`["handleGetScale"]) as () -> Unit
        }
        set(value) {
            setRefValue(this.`$exposed`, "handleGetScale", value)
        }
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenPagesComponentMapMap, _arg1: SetupContext) -> Any? = fun(__props, ref1): Any? {
            var __expose = ref1.expose
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenPagesComponentMapMap
            val _cache = __ins.renderCache
            val testMarkers = utsArrayOf(
                Marker(id = 0, latitude = 39.989631, longitude = 116.481018, title = "方恒国际 阜通东大街6号", iconPath = "../../../static/location.png", rotate = 0, width = 20, height = 20, anchor = Anchor(x = 0.5, y = 1), callout = MapMarkerCallout(content = "方恒国际 阜通东大街6号", color = "#00BFFF", fontSize = 10, borderRadius = 4, borderWidth = 1, borderColor = "#333300", bgColor = "#CCFF99", padding = 5, display = "ALWAYS")),
                Marker(id = 1, latitude = 39.9086920000, longitude = 116.3974770000, title = "天安门", iconPath = "../../../static/location.png", width = 40, height = 40, anchor = Anchor(x = 0.5, y = 1), callout = MapMarkerCallout(content = "首都北京\n天安门", color = "#00BFFF", fontSize = 12, borderRadius = 10, borderWidth = 2, borderColor = "#333300", bgColor = "#CCFF11", padding = 5, display = "ALWAYS")),
                Marker(id = 2, latitude = 39.894793, longitude = 116.321592, title = "北京西站", iconPath = "../../../static/location.png", width = 40, height = 40, anchor = Anchor(x = 0.5, y = 1), callout = MapMarkerCallout(content = "北京西站", color = "#ffffff", fontSize = 12, borderRadius = 10, borderWidth = 2, borderColor = "#333300", bgColor = "#ff5500", padding = 5, display = "ALWAYS")),
                Marker(id = 3, latitude = 39.902344, longitude = 116.484822, title = "北京东站", iconPath = "../../../static/location.png", width = 20, height = 20, anchor = Anchor(x = 0.5, y = 1), callout = MapMarkerCallout(content = "北京东站", color = "#ffffff", fontSize = 12, borderRadius = 10, borderWidth = 2, borderColor = "#333300", bgColor = "#ff5500", padding = 5, display = "ALWAYS")),
                Marker(id = 4, latitude = 39.865011, longitude = 116.379007, title = "北京南站", iconPath = "../../../static/location.png", width = 40, height = 40, anchor = Anchor(x = 0.5, y = 1), callout = MapMarkerCallout(content = "北京南站", color = "#ffffff", fontSize = 12, borderRadius = 10, borderWidth = 2, borderColor = "#333300", bgColor = "#00aa00", padding = 5, display = "ALWAYS"))
            ) as UTSArray<Marker>
            val testPolyline = utsArrayOf(
                Polyline(points = utsArrayOf(
                    LocationObject(latitude = 39.925539, longitude = 116.279037),
                    LocationObject(latitude = 39.925539, longitude = 116.520285)
                ), color = "#FFCCFF", width = 7, dottedLine = true, arrowLine = true, borderColor = "#000000", borderWidth = 2),
                Polyline(points = utsArrayOf(
                    LocationObject(latitude = 39.938698, longitude = 116.275177),
                    LocationObject(latitude = 39.966069, longitude = 116.289253),
                    LocationObject(latitude = 39.944226, longitude = 116.306076),
                    LocationObject(latitude = 39.966069, longitude = 116.322899),
                    LocationObject(latitude = 39.938698, longitude = 116.336975)
                ), color = "#CCFFFF", width = 5, dottedLine = false, arrowLine = true, borderColor = "#CC0000", borderWidth = 3)
            ) as UTSArray<Polyline>
            val testPolygons = utsArrayOf(
                Polygon(points = utsArrayOf(
                    LocationObject(latitude = 39.781892, longitude = 116.293413),
                    LocationObject(latitude = 39.787600, longitude = 116.391842),
                    LocationObject(latitude = 39.733187, longitude = 116.417932),
                    LocationObject(latitude = 39.704653, longitude = 116.338255)
                ), fillColor = "#FFCCFF", strokeWidth = 3, strokeColor = "#CC99CC", zIndex = 11),
                Polygon(points = utsArrayOf(
                    LocationObject(latitude = 39.887600, longitude = 116.518932),
                    LocationObject(latitude = 39.781892, longitude = 116.518932),
                    LocationObject(latitude = 39.781892, longitude = 116.428932),
                    LocationObject(latitude = 39.887600, longitude = 116.428932)
                ), fillColor = "#CCFFFF", strokeWidth = 5, strokeColor = "#CC0000", zIndex = 3)
            ) as UTSArray<Polygon>
            val testCircles = utsArrayOf(
                Circle(latitude = 39.996441, longitude = 116.411146, radius = 15000, strokeWidth = 5, color = "#CCFFFF", fillColor = "#CC0000"),
                Circle(latitude = 40.096441, longitude = 116.511146, radius = 12000, strokeWidth = 3, color = "#CCFFFF", fillColor = "#FFCCFF"),
                Circle(latitude = 39.896441, longitude = 116.311146, radius = 9000, strokeWidth = 1, color = "#CCFFFF", fillColor = "#CC0000")
            ) as UTSArray<Circle>
            val testIncludePoints = utsArrayOf(
                LocationObject(latitude = 39.989631, longitude = 116.481018),
                LocationObject(latitude = 39.9086920000, longitude = 116.3974770000)
            ) as UTSArray<LocationObject>
            val mapContext = ref(null as MapContext?)
            val location = ref(LocationObject(longitude = 116.39742, latitude = 39.909))
            val rotate = ref(0)
            val skew = ref(0)
            val autoTest = ref(false)
            val updateAutoTest = fun(value: Boolean){
                autoTest.value = value
            }
            val jestResult = reactive(TypeJestResult(translateMarkerMsg = "", animationEnd = false, centerPoints = LocationObject(latitude = 0, longitude = 0), southwest = LocationObject(latitude = 0, longitude = 0), northeast = LocationObject(latitude = 0, longitude = 0), moveToLocationMsg = "", scale = 0, eventDetailJsonStringify = "{}"))
            onReady(fun(){
                mapContext.value = uni_createMapContext("map1", getCurrentInstance()!!.proxy!!)
            }
            )
            val scale = ref(13)
            val confirm_scale_input = fun(value: Number){
                scale.value = value
            }
            val controls = ref(utsArrayOf<Control>())
            val addControls = fun(){
                controls.value = utsArrayOf(
                    Control(id = 1, position = ControlPosition(left = 5, top = 180, width = 30, height = 30), iconPath = "../../../static/uni.png", clickable = true)
                )
            }
            val showLocation = ref(false)
            val enable3D = ref(false)
            val showCompass = ref(false)
            val enableOverlooking = ref(false)
            val enableRotate = ref(false)
            val enableZoom = ref(true)
            val enableScroll = ref(true)
            val enableSatellite = ref(false)
            val enableTraffic = ref(false)
            val change_show_location = fun(checked: Boolean){
                showLocation.value = checked
            }
            val change_enable_3d = fun(checked: Boolean){
                enable3D.value = checked
            }
            val change_show_campass = fun(checked: Boolean){
                showCompass.value = checked
            }
            val change_enable_overlooking = fun(checked: Boolean){
                enableOverlooking.value = checked
            }
            val change_enable_rotate = fun(checked: Boolean){
                enableRotate.value = checked
            }
            val change_enable_zoom = fun(checked: Boolean){
                enableZoom.value = checked
            }
            val change_enable_scroll = fun(checked: Boolean){
                enableScroll.value = checked
            }
            val change_enable_satellite = fun(checked: Boolean){
                enableSatellite.value = checked
            }
            val change_enable_traffic = fun(checked: Boolean){
                enableTraffic.value = checked
            }
            val includePoints = ref(utsArrayOf<LocationObject>())
            val includePoint = fun(){
                includePoints.value = testIncludePoints
            }
            var markers = ref(utsArrayOf<Marker>())
            val addMarkers = fun(){
                scale.value = 11
                val temp = UTSAndroid.consoleDebugError(JSON.parse<UTSArray<Marker>>(JSON.stringify(testMarkers)), " at pages/component/map/map.uvue:430")!!
                markers.value = temp
            }
            val removeMarker = fun(){
                mapContext.value?.removeMarkers(MapContextRemoveMarkersOptions(markerIds = utsArrayOf(
                    4
                )))
            }
            val polyline = ref(utsArrayOf<Polyline>())
            val addPolyline = fun(){
                scale.value = 11
                polyline.value = UTSAndroid.consoleDebugError(JSON.parse<UTSArray<Polyline>>(JSON.stringify(testPolyline)), " at pages/component/map/map.uvue:477")!!
            }
            val removePolyline = fun(){
                if (polyline.value.length > 1) {
                    polyline.value = UTSAndroid.consoleDebugError(JSON.parse<UTSArray<Polyline>>(JSON.stringify(testPolyline)), " at pages/component/map/map.uvue:481")!!.splice(0, 1)
                }
            }
            val polygons = ref(utsArrayOf<Polygon>())
            val addPolygons = fun(){
                scale.value = 10
                polygons.value = UTSAndroid.consoleDebugError(JSON.parse<UTSArray<Polygon>>(JSON.stringify(testPolygons)), " at pages/component/map/map.uvue:489")!!
            }
            val removePolygon = fun(){
                if (polygons.value.length > 1) {
                    polygons.value = UTSAndroid.consoleDebugError(JSON.parse<UTSArray<Polygon>>(JSON.stringify(testPolygons)), " at pages/component/map/map.uvue:493")!!.splice(0, 1)
                }
            }
            val circles = ref(utsArrayOf<Circle>())
            val addCircles = fun(){
                scale.value = 10
                circles.value = UTSAndroid.consoleDebugError(JSON.parse<UTSArray<Circle>>(JSON.stringify(testCircles)), " at pages/component/map/map.uvue:501")!!
            }
            val removeCircle = fun(){
                if (circles.value.length > 1) {
                    circles.value = UTSAndroid.consoleDebugError(JSON.parse<UTSArray<Circle>>(JSON.stringify(testCircles)), " at pages/component/map/map.uvue:505")!!.slice(1)
                }
            }
            val handleGetCenterLocation = fun(){
                mapContext.value?.getCenterLocation(MapContextGetCenterLocationOptions(success = fun(ret){
                    jestResult.centerPoints = ret
                    if (!autoTest.value) {
                        uni_showModal(ShowModalOptions(content = JSON.stringify(ret)))
                    }
                }
                ))
            }
            val handleGetRegion = fun(){
                mapContext.value?.getRegion(MapContextGetRegionOptions(success = fun(ret){
                    jestResult.southwest = ret.southwest
                    jestResult.northeast = ret.northeast
                    if (!autoTest.value) {
                        uni_showModal(ShowModalOptions(content = JSON.stringify(ret)))
                    }
                }
                ))
            }
            val handleTranslateMarker = fun(){
                mapContext.value?.translateMarker(MapContextTranslateMarkerOptions(markerId = 1, destination = LocationObject(latitude = 39.989631, longitude = 116.481018), autoRotate = true, rotate = 10, duration = 2000, moveWithRotate = true, success = fun(ret){
                    console.log("handleTranslateMarker", JSON.stringify(ret), " at pages/component/map/map.uvue:557")
                    val result = ret as UTSJSONObject
                    jestResult.translateMarkerMsg = result["errMsg"] as String
                }
                , fail = fun(error){
                    console.log(error, " at pages/component/map/map.uvue:562")
                }
                ))
                mapContext.value?.translateMarker(MapContextTranslateMarkerOptions(markerId = 2, destination = LocationObject(latitude = 39.902344, longitude = 116.484822), autoRotate = true, rotate = 0, duration = 2000, success = fun(ret){
                    console.log("handleTranslateMarker", JSON.stringify(ret), " at pages/component/map/map.uvue:575")
                }
                , fail = fun(error){
                    console.log(error, " at pages/component/map/map.uvue:578")
                }
                ))
            }
            val handleGetScale = fun(){
                mapContext.value?.getScale(MapContextGetScaleOptions(success = fun(res){
                    scale.value = res.scale
                    jestResult.scale = res.scale
                    if (!autoTest.value) {
                        uni_showModal(ShowModalOptions(content = "当前地图的缩放级别为：" + res.scale))
                    }
                }
                , fail = fun(error){
                    console.log(error, " at pages/component/map/map.uvue:597")
                }
                ))
            }
            fun handleMoveToLocation(showModal: Boolean = true) {
                mapContext.value?.moveToLocation(MapContextMoveToLocationOptions(latitude = 39.909, longitude = 116.39742, success = fun(res){
                    val result = res as UTSJSONObject
                    jestResult.moveToLocationMsg = result["errMsg"] as String
                    if (!autoTest.value && showModal) {
                        uni_showModal(ShowModalOptions(content = JSON.stringify(res)))
                    }
                }
                , fail = fun(error){
                    console.log(error, " at pages/component/map/map.uvue:619")
                }
                ))
            }
            val handleMoveToMyLocation = fun(){
                mapContext.value?.moveToLocation(MapContextMoveToLocationOptions(success = fun(res){
                    uni_showModal(ShowModalOptions(content = "地图中心已经移动到当前位置"))
                }
                , fail = fun(error){
                    console.log(error, " at pages/component/map/map.uvue:632")
                }
                ))
            }
            val maptap = fun(e: UniMapTapEvent){
                uni_showModal(ShowModalOptions(content = JSON.stringify(e)))
            }
            val onmarkertap = fun(e: UniMapMarkerTapEvent){
                uni_showModal(ShowModalOptions(content = JSON.stringify(e)))
            }
            val oncontroltap = fun(e: UniMapControlTapEvent){
                uni_showModal(ShowModalOptions(content = JSON.stringify(e.detail)))
            }
            val oncallouttap = fun(e: UniMapCalloutTapEvent){
                uni_showModal(ShowModalOptions(content = JSON.stringify(e.detail)))
            }
            val onupdated = fun(e: UniMapUpdatedEvent){}
            val onregionchange = fun(e: UniMapRegionChangeEvent){
                jestResult.eventDetailJsonStringify = JSON.stringify(e.detail)
            }
            val onpoitap = fun(e: UniMapPoiTapEvent){
                uni_showModal(ShowModalOptions(content = JSON.stringify(e)))
            }
            __expose(utsMapOf("jestResult" to jestResult, "autoTest" to autoTest, "updateAutoTest" to updateAutoTest, "addControls" to addControls, "addMarkers" to addMarkers, "removeMarker" to removeMarker, "addPolyline" to addPolyline, "removePolyline" to removePolyline, "addPolygons" to addPolygons, "removePolygon" to removePolygon, "addCircles" to addCircles, "removeCircle" to removeCircle, "includePoint" to includePoint, "handleGetCenterLocation" to handleGetCenterLocation, "handleGetRegion" to handleGetRegion, "handleTranslateMarker" to handleTranslateMarker, "handleMoveToLocation" to ::handleMoveToLocation, "handleGetScale" to handleGetScale))
            return fun(): Any? {
                val _component_map = resolveComponent("map", true)
                val _component_input_data = resolveEasyComponent("input-data", GenComponentsInputDataInputDataClass)
                val _component_boolean_data = resolveEasyComponent("boolean-data", GenComponentsBooleanDataBooleanDataClass)
                return createElementVNode("view", utsMapOf("class" to "content"), utsArrayOf(
                    createVNode(_component_map, utsMapOf("class" to "map", "id" to "map1", "ref" to "map1", "longitude" to unref(location).longitude, "latitude" to unref(location).latitude, "scale" to unref(scale), "markers" to unref(markers), "include-points" to unref(includePoints), "polyline" to unref(polyline), "polygons" to unref(polygons), "circles" to unref(circles), "controls" to unref(controls), "show-location" to unref(showLocation), "enable-3D" to unref(enable3D), "rotate" to unref(rotate), "skew" to unref(skew), "show-compass" to unref(showCompass), "enable-overlooking" to unref(enableOverlooking), "enable-zoom" to unref(enableZoom), "enable-scroll" to unref(enableScroll), "enable-rotate" to unref(enableRotate), "enable-satellite" to unref(enableSatellite), "enable-traffic" to unref(enableTraffic), "onMarkertap" to onmarkertap, "onCallouttap" to oncallouttap, "onControltap" to oncontroltap, "onRegionchange" to onregionchange, "onTap" to maptap, "onUpdated" to onupdated, "onPoitap" to onpoitap), null, 8, utsArrayOf(
                        "longitude",
                        "latitude",
                        "scale",
                        "markers",
                        "include-points",
                        "polyline",
                        "polygons",
                        "circles",
                        "controls",
                        "show-location",
                        "enable-3D",
                        "rotate",
                        "skew",
                        "show-compass",
                        "enable-overlooking",
                        "enable-zoom",
                        "enable-scroll",
                        "enable-rotate",
                        "enable-satellite",
                        "enable-traffic"
                    )),
                    createElementVNode("scroll-view", utsMapOf("class" to "scrollview", "scroll-y" to "true"), utsArrayOf(
                        createElementVNode("view", utsMapOf("class" to "tips"), "注意：App和Web需要正确配置地图服务商的Key才能显示地图组件"),
                        createElementVNode("view", utsMapOf("class" to "uni-title"), utsArrayOf(
                            createElementVNode("text", utsMapOf("class" to "uni-title-text"), "属性示例")
                        )),
                        createVNode(_component_input_data, utsMapOf("defaultValue" to "13", "title" to "scale: 缩放级别，取值范围为5-18", "type" to "number", "onConfirm" to confirm_scale_input)),
                        createVNode(_component_boolean_data, utsMapOf("defaultValue" to unref(showLocation), "title" to "开启显示带有方向的当前定位点", "onChange" to change_show_location), null, 8, utsArrayOf(
                            "defaultValue"
                        )),
                        createVNode(_component_boolean_data, utsMapOf("default-value" to unref(enable3D), "disabled" to unref(enableSatellite), "title" to "3D效果(放大缩放级别才能看到建筑物3D效果)", "onChange" to change_enable_3d), null, 8, utsArrayOf(
                            "default-value",
                            "disabled"
                        )),
                        createVNode(_component_boolean_data, utsMapOf("default-value" to unref(showCompass), "title" to "显示指南针", "onChange" to change_show_campass), null, 8, utsArrayOf(
                            "default-value"
                        )),
                        createVNode(_component_boolean_data, utsMapOf("default-value" to unref(enableOverlooking), "title" to "俯视支持", "onChange" to change_enable_overlooking), null, 8, utsArrayOf(
                            "default-value"
                        )),
                        createVNode(_component_boolean_data, utsMapOf("default-value" to unref(enableRotate), "title" to "旋转支持", "onChange" to change_enable_rotate), null, 8, utsArrayOf(
                            "default-value"
                        )),
                        createVNode(_component_boolean_data, utsMapOf("default-value" to unref(enableZoom), "title" to "缩放支持", "onChange" to change_enable_zoom), null, 8, utsArrayOf(
                            "default-value"
                        )),
                        createVNode(_component_boolean_data, utsMapOf("default-value" to unref(enableScroll), "title" to "拖动支持", "onChange" to change_enable_scroll), null, 8, utsArrayOf(
                            "default-value"
                        )),
                        createVNode(_component_boolean_data, utsMapOf("default-value" to unref(enableSatellite), "title" to "卫星图", "onChange" to change_enable_satellite), null, 8, utsArrayOf(
                            "default-value"
                        )),
                        createVNode(_component_boolean_data, utsMapOf("default-value" to unref(enableTraffic), "title" to "实时路况", "onChange" to change_enable_traffic), null, 8, utsArrayOf(
                            "default-value"
                        )),
                        createElementVNode("button", utsMapOf("class" to "button", "onClick" to addControls), "控件"),
                        createElementVNode("button", utsMapOf("class" to "button", "onClick" to addMarkers), "添加标记点"),
                        createElementVNode("button", utsMapOf("class" to "button", "onClick" to removeMarker), "移除ID为4的标记点和标签"),
                        createElementVNode("button", utsMapOf("class" to "button", "onClick" to addPolyline), "添加路线"),
                        createElementVNode("button", utsMapOf("class" to "button", "onClick" to removePolyline), "移除一条路线"),
                        createElementVNode("button", utsMapOf("class" to "button", "onClick" to addPolygons), "添加多边形"),
                        createElementVNode("button", utsMapOf("class" to "button", "onClick" to removePolygon), "移除一个多边形"),
                        createElementVNode("button", utsMapOf("class" to "button", "onClick" to addCircles), "添加圆"),
                        createElementVNode("button", utsMapOf("class" to "button", "onClick" to removeCircle), "移除一个圆"),
                        createElementVNode("button", utsMapOf("class" to "button", "onClick" to includePoint), "缩放视野以包含所有给定的坐标点"),
                        createElementVNode("view", utsMapOf("class" to "uni-title"), utsArrayOf(
                            createElementVNode("text", utsMapOf("class" to "uni-title-text"), "方法示例")
                        )),
                        createElementVNode("button", utsMapOf("class" to "button", "onClick" to handleGetCenterLocation), "获取当前地图中心的经纬度"),
                        createElementVNode("button", utsMapOf("class" to "button", "onClick" to handleGetRegion), "获取当前地图的视野范围"),
                        createElementVNode("button", utsMapOf("class" to "button", "onClick" to handleTranslateMarker), "平移marker"),
                        createElementVNode("button", utsMapOf("class" to "button", "onClick" to fun(){
                            handleMoveToLocation(true)
                        }
                        ), "将地图中心移动到指定坐标", 8, utsArrayOf(
                            "onClick"
                        )),
                        createElementVNode("button", utsMapOf("class" to "button", "onClick" to handleMoveToMyLocation), "将地图中心移动到当前位置"),
                        createElementVNode("button", utsMapOf("class" to "button", "onClick" to handleGetScale), "获取当前地图的缩放级别")
                    ))
                ))
            }
        }
        val styles: Map<String, Map<String, Map<String, Any>>> by lazy {
            normalizeCssStyles(utsArrayOf(
                styles0
            ), utsArrayOf(
                GenApp.styles
            ))
        }
        val styles0: Map<String, Map<String, Map<String, Any>>>
            get() {
                return utsMapOf("content" to padStyleMapOf(utsMapOf("flex" to 1)), "map" to padStyleMapOf(utsMapOf("width" to "100%", "height" to 300, "backgroundColor" to "#f0f0f0")), "scrollview" to padStyleMapOf(utsMapOf("flex" to 1, "paddingTop" to 10, "paddingRight" to 10, "paddingBottom" to 10, "paddingLeft" to 10)), "button" to padStyleMapOf(utsMapOf("marginTop" to 5, "marginBottom" to 5)), "tips" to padStyleMapOf(utsMapOf("fontSize" to 12, "marginTop" to 15, "opacity" to 0.8)))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
