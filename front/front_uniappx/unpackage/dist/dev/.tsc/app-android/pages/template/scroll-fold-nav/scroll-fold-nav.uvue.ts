
  //导航栏高度
  const NAVIBARHEIGHT = 88;
  //搜索栏高度
  const SEARCHBARHEIGHT = 40;
  //返回键按钮宽度
  const BACKWIDTH = 32;
  const __sfc__ = defineComponent({
    data() {
      return {
        statusBarHeight: 0,
        nviBarHeight: 0,
        naviElement: null as UniElement | null,
        titleElement: null as UniElement | null,
        searchElement: null as UniElement | null,
        seatElement: null as UniElement | null
      }
    },
    onLoad() {

        this.statusBarHeight = uni.getWindowInfo().statusBarHeight;


      this.nviBarHeight = NAVIBARHEIGHT + this.statusBarHeight;
    },
    onReady() {
      this.naviElement = this.$refs['navigatorbar'] as UniElement;
      this.searchElement = this.$refs['searchbar'] as UniElement;
      this.titleElement = this.$refs['title'] as UniElement;
      this.seatElement = this.$refs['seatbar'] as UniElement;
      this.setStyle();
    },
    onResize(_ : OnResizeOptions) {

      // 监听多窗口模式
      this.statusBarHeight = uni.getWindowInfo().statusBarHeight;
      this.nviBarHeight = NAVIBARHEIGHT + this.statusBarHeight;
      this.setStyle();

    },
    methods: {
      onScroll(e : ScrollEvent) {
        let offset = e.detail.scrollTop>SEARCHBARHEIGHT?SEARCHBARHEIGHT:e.detail.scrollTop;//(e.detail.scrollTop<0?0:e.detail.scrollTop)
        this.naviElement?.style?.setProperty('height', (this.nviBarHeight -offset)+'px');
        this.titleElement?.style?.setProperty('opacity', (1-offset/SEARCHBARHEIGHT).toString());
        this.searchElement?.style?.setProperty('left', ((offset<0)?0:BACKWIDTH*offset/SEARCHBARHEIGHT)+'px');
      },
      back() {
        uni.navigateBack();
      },
      search() {
        uni.showToast({
          title: '暂不支持',
          icon: 'none'
        });
      },
      setStyle() {
        this.naviElement?.style?.setProperty('padding-top', this.statusBarHeight + 'px');
        this.naviElement?.style?.setProperty('height', (NAVIBARHEIGHT + this.statusBarHeight) + 'px');
        this.seatElement?.style?.setProperty('height', (NAVIBARHEIGHT + this.statusBarHeight) + 'px');
      }
    }
  })

export default __sfc__
function GenPagesTemplateScrollFoldNavScrollFoldNavRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
  return createElementVNode("scroll-view", utsMapOf({
    onScroll: _ctx.onScroll,
    class: "page",
    "show-scrollbar": "false"
  }), [
    createElementVNode("view", utsMapOf({
      ref: "seatbar",
      class: "seatbar"
    }), null, 512 /* NEED_PATCH */),
    createElementVNode("view", utsMapOf({ class: "content" }), [
      createElementVNode("view", utsMapOf({ class: "content-item" }), [
        createElementVNode("text", utsMapOf({ class: "text" }), "1. 当前示例监听了 scroll-view 的 scroll 事件 ，滚动页面实时监听scrollTop。"),
        createElementVNode("text", utsMapOf({ class: "text" }), "2. 使用 ref 直接获取元素的节点，并在 scroll 事件中通过节点的 setProperty 方法来修改搜索导航栏的高度、位置和背景颜色等样式，从而达到滚动折叠的效果。"),
        createElementVNode("text", utsMapOf({ class: "text" }), "3. 请向上\\向下滚动页面观察效果。")
      ]),
      createElementVNode(Fragment, null, RenderHelpers.renderList(20, (item, index, __index, _cached): any => {
        return createElementVNode("view", utsMapOf({
          class: "content-item",
          key: index
        }), [
          createElementVNode("text", utsMapOf({ class: "text" }), "content-" + toDisplayString(item), 1 /* TEXT */)
        ])
      }), 64 /* STABLE_FRAGMENT */)
    ]),
    createElementVNode("view", utsMapOf({
      ref: "navigatorbar",
      class: "navigatorbar"
    }), [
      createElementVNode("view", utsMapOf({ class: "titlebar" }), [
        createElementVNode("view", utsMapOf({
          class: "backview",
          onClick: _ctx.back
        }), [
          createElementVNode("image", utsMapOf({
            class: "back",
            src: "/static/template/scroll-fold-nav/back.png",
            mode: "widthFix"
          }))
        ], 8 /* PROPS */, ["onClick"]),
        createElementVNode("text", utsMapOf({
          ref: "title",
          class: "title"
        }), "DCloud 为开发者而生", 512 /* NEED_PATCH */)
      ]),
      createElementVNode("view", utsMapOf({
        ref: "searchbar",
        class: "searchbar",
        onClick: _ctx.search
      }), [
        createElementVNode("image", utsMapOf({
          class: "searchimg",
          src: "/static/template/scroll-fold-nav/search.png",
          mode: "widthFix"
        })),
        createElementVNode("text", utsMapOf({ class: "searchinput" }), "请输入你要搜索的内容"),
        createElementVNode("text", utsMapOf({ class: "searchbutton" }), "搜索")
      ], 8 /* PROPS */, ["onClick"])
    ], 512 /* NEED_PATCH */)
  ], 40 /* PROPS, NEED_HYDRATION */, ["onScroll"])
}
const GenPagesTemplateScrollFoldNavScrollFoldNavStyles = [utsMapOf([["page", padStyleMapOf(utsMapOf([["flex", 1], ["backgroundColor", "#f5f5f5"]]))], ["navigatorbar", padStyleMapOf(utsMapOf([["position", "fixed"], ["paddingTop", 35], ["height", 124], ["borderBottomWidth", 1], ["borderBottomStyle", "solid"], ["borderBottomColor", "#efefef"], ["width", "100%"], ["backgroundColor", "#f5f5f5"]]))], ["titlebar", padStyleMapOf(utsMapOf([["flexDirection", "row"], ["alignItems", "center"], ["height", 44]]))], ["backview", padStyleMapOf(utsMapOf([["width", 44], ["height", 44], ["justifyContent", "center"], ["alignItems", "center"]]))], ["back", padStyleMapOf(utsMapOf([["width", 20]]))], ["title", padStyleMapOf(utsMapOf([["marginTop", 0], ["marginRight", 2], ["marginBottom", 0], ["marginLeft", 2]]))], ["searchbar", padStyleMapOf(utsMapOf([["position", "absolute"], ["bottom", 2], ["left", 0], ["right", 0], ["backgroundColor", "#FFFFFF"], ["borderTopWidth", 1], ["borderRightWidth", 1], ["borderBottomWidth", 1], ["borderLeftWidth", 1], ["borderTopStyle", "solid"], ["borderRightStyle", "solid"], ["borderBottomStyle", "solid"], ["borderLeftStyle", "solid"], ["borderTopColor", "#fbdf0d"], ["borderRightColor", "#fbdf0d"], ["borderBottomColor", "#fbdf0d"], ["borderLeftColor", "#fbdf0d"], ["height", 32], ["borderTopLeftRadius", 100], ["borderTopRightRadius", 100], ["borderBottomRightRadius", 100], ["borderBottomLeftRadius", 100], ["marginTop", 6], ["marginRight", 12], ["marginBottom", 6], ["marginLeft", 12], ["paddingTop", 8], ["paddingRight", 8], ["paddingBottom", 8], ["paddingLeft", 8], ["flexDirection", "row"], ["alignItems", "center"], ["justifyContent", "center"]]))], ["searchimg", padStyleMapOf(utsMapOf([["width", 15]]))], ["searchinput", padStyleMapOf(utsMapOf([["flexGrow", 1], ["fontSize", 12], ["color", "#666666"]]))], ["searchbutton", padStyleMapOf(utsMapOf([["fontSize", 12], ["backgroundColor", "#ff6900"], ["color", "#FFFFFF"], ["paddingTop", 5], ["paddingRight", 8], ["paddingBottom", 5], ["paddingLeft", 8], ["borderTopLeftRadius", 100], ["borderTopRightRadius", 100], ["borderBottomRightRadius", 100], ["borderBottomLeftRadius", 100]]))], ["seatbar", padStyleMapOf(utsMapOf([["height", 124]]))], ["content", padStyleMapOf(utsMapOf([["paddingTop", 5], ["paddingRight", 15], ["paddingBottom", 5], ["paddingLeft", 15]]))], ["content-item", padStyleMapOf(utsMapOf([["paddingTop", 15], ["paddingRight", 15], ["paddingBottom", 15], ["paddingLeft", 15], ["marginTop", 5], ["marginRight", 0], ["marginBottom", 5], ["marginLeft", 0], ["backgroundColor", "#ffffff"], ["borderTopLeftRadius", 5], ["borderTopRightRadius", 5], ["borderBottomRightRadius", 5], ["borderBottomLeftRadius", 5]]))], ["text", padStyleMapOf(utsMapOf([["fontSize", 14], ["color", "#666666"], ["lineHeight", "20px"]]))]])]
