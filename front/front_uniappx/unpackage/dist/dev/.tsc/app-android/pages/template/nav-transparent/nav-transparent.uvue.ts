import _easycom_uni_icons from '@/uni_modules/uni-icons/components/uni-icons/uni-icons.uvue'
import { state } from '@/store/index.uts'
  type Banner = { __$originalPosition?: UTSSourceMapPosition<"Banner", "pages/template/nav-transparent/nav-transparent.uvue", 56, 8>;
    cover : string | null,
    title : string | null,
    post_id : string | null
  }
  type Item = { __$originalPosition?: UTSSourceMapPosition<"Item", "pages/template/nav-transparent/nav-transparent.uvue", 61, 8>;
    author_name : string,
    cover : string,
    id : number,
    post_id : string,
    published_at : string,
    title : string
  }

  
const __sfc__ = defineComponent({
  __name: 'nav-transparent',
  setup(__props): any | null {
const __ins = getCurrentInstance()!;
const _ctx = __ins.proxy as InstanceType<typeof __sfc__>;
const _cache = __ins.renderCache;

  const th_item = ref(["排序", "筛选"])
  const refresherTriggered = ref(false)
  const banner = ref({} as Banner)
  const listData = ref([] as Item[])
  const last_id = ref('')
  const isWideScreen = ref(false) // 是否为宽屏模式
  const currentIndex = ref(0) // 当前选中的列表项索引
  const post_id = ref('')
  const cover = ref('')
  const title = ref('')
  const firstDetailContent = ref('') // 并行预加载的第一个详情内容

  // 导航栏相关
  const navbar = ref<UniElement | null>(null);
  const backRef = ref<UniElement | null>(null);
  const textRef = ref<UniElement | null>(null);
  const iconColor = ref('#999')
  const navbarHeight = ref(0)
  const tabRef = ref<UniElement | null>(null);
  const tabTop = ref(0)

  function getBanner() {
    let data = {__$originalPosition: new UTSSourceMapPosition("data", "pages/template/nav-transparent/nav-transparent.uvue", 92, 9),
      column: 'id,post_id,title,author_name,cover,published_at' //需要的字段名
    };
    uni.request<Banner>({
      url: 'https://unidemo.dcloud.net.cn/api/banner/36kr',
      data: data,
      success: data => {
        refresherTriggered.value = false
        if (data.statusCode == 200) {
          const result = data.data
          if (result != null) {
            banner.value = result;
          }
        }
      },
      fail: (e) => {
        console.log('fail', e, " at pages/template/nav-transparent/nav-transparent.uvue:108");
      }
    });
  }


  function getList() {
    let url = "https://unidemo.dcloud.net.cn/api/news?column=id,post_id,title,author_name,cover,published_at";
    uni.request<Item[]>({
      url: url,
      method: "GET",
      success: (res) => {
        if (res.statusCode == 200) {
          const result = res.data
          //因本接口没有更多分页数据，所以重新赋值。正常有分页的列表应该如下面push方式增加数组项
          // listData.value.push(...result)
          // last_id.value = listData.value[0].id + "";
          if (result != null) {
            listData.value = result
            // 宽屏模式下，自动选中并展示第一个新闻详情
          }
          refresherTriggered.value = false;
        }
      },
      fail: (res) => {
        console.log('fail', res, " at pages/template/nav-transparent/nav-transparent.uvue:133");
        refresherTriggered.value = false
      }
    });
  }

  function clickTH(index : number) {
    uni.showModal({
      content: "点击表头项：" + index,
      showCancel: false
    });
  }

  function onRefresherrefresh() {
    refresherTriggered.value = true
    getBanner();
    getList();
  }

  function contentScroll(event : UniScrollEvent) {
    const top = event.detail.scrollTop
    const THRESHOLD = 100
    let transparent_count = parseFloat((top / THRESHOLD).toFixed(2))
    if (transparent_count >= 1) {
      transparent_count = 1
      iconColor.value = '#fff'
    } else {
      iconColor.value = '#999'
    }

    navbar.value!.style.setProperty('background-color', `rgba(0, 122, 255,${transparent_count})`)
    backRef.value!.style.setProperty('background-color', `rgba(255, 255, 255,${1 - transparent_count})`)
    textRef.value!.style.setProperty('opacity', transparent_count.toString())
    if (tabTop.value <= top) {
      const stickyTran = top - tabTop.value
      tabRef.value!.style.setProperty('transform', 'translateY(' + stickyTran + 'px)');
    }

  }

  async function calcStickyTop() {
    const stickyRect = await (tabRef.value! as UniElement).getBoundingClientRectAsync()!;
    const navbarRect = await (navbar.value! as UniElement).getBoundingClientRectAsync()!;
    navbarHeight.value = navbarRect.height
    tabTop.value = stickyRect.top - navbarHeight.value
  }

  const back = () => {
    uni.navigateBack({});
  }

  const isDarkMode = computed(() => state.isDarkMode)

  onLoad(() => {
    getBanner()
    getList()
  })
  onReady(() => {
    // const top = tabRef.value!.getBoundingClientRect().top
    // navbarHeight.value = navbar.value!.offsetHeight
    // tabTop.value = top - navbarHeight.value
    calcStickyTop()
  })



return (): any | null => {

const _component_uni_icons = resolveEasyComponent("uni-icons",_easycom_uni_icons)

  return createElementVNode("view", utsMapOf({
    class: normalizeClass(['uni-container',  unref(isDarkMode) ? 'theme-dark' : 'theme-light']),
    style: normalizeStyle(utsMapOf({"flex":"1","padding":"0px"}))
  }), [
    createElementVNode("view", utsMapOf({
      ref_key: "navbar",
      ref: navbar,
      class: "custom-navbar"
    }), [
      createElementVNode("view", utsMapOf({ class: "status-bar" })),
      createElementVNode("view", utsMapOf({ class: "custom-navbar-inner" }), [
        createElementVNode("view", utsMapOf({ class: "left-content" }), [
          createElementVNode("view", utsMapOf({
            ref_key: "backRef",
            ref: backRef,
            class: "left-content-inner",
            onClick: back
          }), [
            createVNode(_component_uni_icons, utsMapOf({
              style: normalizeStyle(utsMapOf({"margin-left":"-1px"})),
              type: "back",
              color: unref(iconColor)
            }), null, 8 /* PROPS */, ["style", "color"])
          ], 512 /* NEED_PATCH */)
        ]),
        createElementVNode("view", utsMapOf({ class: "custom-navbar-content" }), [
          createElementVNode("text", utsMapOf({
            ref_key: "textRef",
            ref: textRef,
            class: "content-inner-text"
          }), " 标题 ", 512 /* NEED_PATCH */)
        ])
      ])
    ], 512 /* NEED_PATCH */),
    createElementVNode("scroll-view", utsMapOf({
      class: "list-container",
      direction: "vertical",
      "refresher-enabled": "true",
      onRefresherrefresh: onRefresherrefresh,
      "refresher-triggered": unref(refresherTriggered),
      onScroll: contentScroll
    }), [
      createElementVNode("view", utsMapOf({ class: "banner" }), [
        createElementVNode("image", utsMapOf({
          class: "banner-img",
          src: unref(banner).cover
        }), null, 8 /* PROPS */, ["src"]),
        createElementVNode("text", utsMapOf({ class: "banner-title" }), toDisplayString(unref(banner).title), 1 /* TEXT */)
      ]),
      createElementVNode("view", utsMapOf({
        class: "list-tab",
        ref_key: "tabRef",
        ref: tabRef
      }), [
        createElementVNode("view", utsMapOf({ class: "th-item" }), [
          createElementVNode(Fragment, null, RenderHelpers.renderList(unref(th_item), (name, index, __index, _cached): any => {
            return createElementVNode("text", utsMapOf({
              key: index,
              onClick: () => {clickTH(index)},
              class: "th-item-text"
            }), toDisplayString(name), 9 /* TEXT, PROPS */, ["onClick"])
          }), 128 /* KEYED_FRAGMENT */)
        ])
      ], 512 /* NEED_PATCH */),
      createElementVNode(Fragment, null, RenderHelpers.renderList(unref(listData), (value, index, __index, _cached): any => {
        return createElementVNode("view", utsMapOf({
          key: index,
          class: "uni-list-cell",
          "hover-class": "uni-list-cell-hover"
        }), [
          createElementVNode("view", utsMapOf({ class: "uni-media-list" }), [
            isTrue(unref(isWideScreen))
              ? createElementVNode("image", utsMapOf({
                  key: 0,
                  class: "uni-media-list-logo",
                  src: value.cover
                }), null, 8 /* PROPS */, ["src"])
              : createElementVNode("image", utsMapOf({
                  key: 1,
                  class: "uni-media-list-logo",
                  src: value.cover
                }), null, 8 /* PROPS */, ["src"]),
            createElementVNode("view", utsMapOf({ class: "uni-media-list-body" }), [
              createElementVNode("text", utsMapOf({ class: "uni-media-list-text-top" }), toDisplayString(value.title), 1 /* TEXT */),
              createElementVNode("view", utsMapOf({ class: "uni-media-list-text-bottom" }), [
                createElementVNode("text", utsMapOf({ class: "uni-media-list-text" }), toDisplayString(value.author_name), 1 /* TEXT */),
                createElementVNode("text", utsMapOf({ class: "uni-media-list-text" }), toDisplayString(value.published_at), 1 /* TEXT */)
              ])
            ])
          ])
        ])
      }), 128 /* KEYED_FRAGMENT */)
    ], 40 /* PROPS, NEED_HYDRATION */, ["refresher-triggered"])
  ], 6 /* CLASS, STYLE */)
}
}

})
export default __sfc__
const GenPagesTemplateNavTransparentNavTransparentStyles = [utsMapOf([["list-container", padStyleMapOf(utsMapOf([["position", "relative"], ["width", "100%"], ["backgroundColor", "var(--list-background-color, #ffffff)"], ["flex", 1]]))], ["th-item", padStyleMapOf(utsMapOf([["width", "100%"], ["height", 44], ["backgroundColor", "var(--list-background-color, #ffffff)"], ["flexDirection", "row"], ["justifyContent", "center"], ["alignItems", "center"]]))], ["th-item-text", padStyleMapOf(utsMapOf([["marginRight", 20], ["color", "var(--text-color, #333333)"]]))], ["banner", padStyleMapOf(utsMapOf([["height", 180], ["overflow", "hidden"], ["position", "relative"], ["backgroundColor", "var(--background-color, #f8f8f8)"]]))], ["banner-img", padStyleMapOf(utsMapOf([["width", "100%"]]))], ["banner-title", padStyleMapOf(utsMapOf([["maxHeight", 42], ["overflow", "hidden"], ["position", "absolute"], ["left", 15], ["bottom", 15], ["width", "90%"], ["fontSize", 16], ["fontWeight", "400"], ["lineHeight", "21px"], ["color", "#FFFFFF"]]))], ["uni-media-list", padStyleMapOf(utsMapOf([["paddingTop", 11], ["paddingRight", 15], ["paddingBottom", 11], ["paddingLeft", 15], ["boxSizing", "border-box"], ["display", "flex"], ["width", "100%"], ["flexDirection", "row"]]))], ["uni-media-list-logo", padStyleMapOf(utsMapOf([["width", 90], ["height", 70]]))], ["uni-media-list-body", padStyleMapOf(utsMapOf([["flex", 1], ["paddingLeft", 7], ["justifyContent", "space-around"]]))], ["uni-media-list-text-top", padStyleMapOf(utsMapOf([["fontSize", 14], ["overflow", "hidden"], ["color", "var(--text-color, #333333)"]]))], ["uni-media-list-text-bottom", padStyleMapOf(utsMapOf([["display", "flex"], ["flexDirection", "row"], ["justifyContent", "space-between"]]))], ["uni-media-list-text", padStyleMapOf(utsMapOf([["color", "#9D9D9F"], ["fontSize", 13]]))], ["status-bar", padStyleMapOf(utsMapOf([["height", CSS_VAR_STATUS_BAR_HEIGHT]]))], ["custom-navbar", padStyleMapOf(utsMapOf([["position", "absolute"], ["top", 0], ["left", 0], ["right", 0], ["zIndex", 10], ["backgroundColor", "rgba(0,122,255,0)"]]))], ["custom-navbar-inner", padStyleMapOf(utsMapOf([["position", "relative"], ["flexDirection", "row"], ["justifyContent", "space-between"], ["height", 45]]))], ["left-content", padStyleMapOf(utsMapOf([["display", "flex"], ["justifyContent", "center"], ["alignItems", "center"], ["width", 40], ["height", "100%"]]))], ["left-content-inner", padStyleMapOf(utsMapOf([["display", "flex"], ["justifyContent", "center"], ["alignItems", "center"], ["width", 28], ["height", 28], ["backgroundColor", "#ffffff"], ["borderTopLeftRadius", 40], ["borderTopRightRadius", 40], ["borderBottomRightRadius", 40], ["borderBottomLeftRadius", 40]]))], ["custom-navbar-content", padStyleMapOf(utsMapOf([["position", "absolute"], ["height", "100%"], ["top", 0], ["bottom", 0], ["left", 45], ["right", 45], ["flexDirection", "row"], ["justifyContent", "center"], ["alignItems", "center"]]))], ["content-inner-text", padStyleMapOf(utsMapOf([["opacity", 0], ["color", "#f5f5f5"]]))], ["list-tab", padStyleMapOf(utsMapOf([["position", "relative"], ["zIndex", 9]]))]])]
