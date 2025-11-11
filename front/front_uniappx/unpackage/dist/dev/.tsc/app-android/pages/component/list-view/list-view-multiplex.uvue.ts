
  const __sfc__ = defineComponent({
    data() {
      return {
        item_count: 20,
        list_show: true,
        listViewElement: null as UniListViewElement | null,
        displayArrow: false
      }
    },
    onReady() {
      this.listViewElement = uni.getElementById<UniListViewElement>('listview')
    },
    methods: {
      onScrollTolower(_ : ScrollToLowerEvent) {
        setTimeout(() => {
          this.item_count += 20
        }, 300)
      },
      //用于自动化测试
      listViewScrollByY(y : number) {
        this.listViewElement?.scrollBy(0, y)
      },
      itemClick(index : number) {
        console.log("itemTextClick---" + index, " at pages/component/list-view/list-view-multiplex.uvue:48")
      },
      delayShow() {
        this.list_show = !this.list_show
        this.item_count += 20
        //延时显示list-view 测试list-item延时显示bug
        setTimeout(() => {
          this.list_show = !this.list_show
        }, 400)
      },
      switchItemContent() {
        this.displayArrow = !this.displayArrow
        this.modifyItemPadding(1)
      },
      modifyItemPadding(index: number) {
        var element = uni.getElementById("item_"+index)
        element?.style.setProperty("padding", "0px")
      }
    }
  })

export default __sfc__
function GenPagesComponentListViewListViewMultiplexRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
const _component_switch = resolveComponent("switch")

  return createElementVNode("view", utsMapOf({
    style: normalizeStyle(utsMapOf({"flex":"1"}))
  }), [
    createElementVNode("scroll-view", utsMapOf({
      direction: "horizontal",
      style: normalizeStyle(utsMapOf({"flex-direction":"row"}))
    }), [
      createElementVNode("button", utsMapOf({
        class: "button_item",
        onClick: _ctx.delayShow
      }), "测试延时显示", 8 /* PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({
        class: "button_item",
        onClick: _ctx.switchItemContent
      }), "修改item子元素", 8 /* PROPS */, ["onClick"])
    ], 4 /* STYLE */),
    withDirectives(createElementVNode("list-view", utsMapOf({
      id: "listview",
      style: normalizeStyle(utsMapOf({"flex":"1"})),
      "show-scrollbar": "false",
      onScrolltolower: _ctx.onScrollTolower
    }), [
      createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.item_count, (index, __key, __index, _cached): any => {
        return createElementVNode("list-item", utsMapOf({
          id: 'item_'+index,
          class: "item",
          onClick: () => {_ctx.itemClick(index)}
        }), [
          createElementVNode("view", utsMapOf({
            style: normalizeStyle(utsMapOf({"flex-direction":"row"}))
          }), [
            createElementVNode("text", null, [
              "item-------",
              createElementVNode("text", null, toDisplayString(index), 1 /* TEXT */)
            ]),
            createElementVNode("scroll-view", utsMapOf({
              direction: "horizontal",
              "show-scrollbar": "false",
              class: "scroll_item"
            }), [
              createElementVNode("text", null, "scroll-view" + toDisplayString(index) + "：", 1 /* TEXT */),
              createElementVNode(Fragment, null, RenderHelpers.renderList(5, (tab, __key, __index, _cached): any => {
                return createElementVNode("text", utsMapOf({ class: "tip_text" }), "元素" + toDisplayString(tab), 1 /* TEXT */)
              }), 64 /* STABLE_FRAGMENT */)
            ])
          ], 4 /* STYLE */),
          withDirectives(createElementVNode("text", null, [
            "item-------",
            createElementVNode("text", null, toDisplayString(index), 1 /* TEXT */)
          ], 512 /* NEED_PATCH */), [
            [vShow, _ctx.displayArrow]
          ]),
          createVNode(_component_switch, utsMapOf({ checked: true }))
        ], 8 /* PROPS */, ["id", "onClick"])
      }), 256 /* UNKEYED_FRAGMENT */)
    ], 44 /* STYLE, PROPS, NEED_HYDRATION */, ["onScrolltolower"]), [
      [vShow, _ctx.list_show]
    ])
  ], 4 /* STYLE */)
}
const GenPagesComponentListViewListViewMultiplexStyles = [utsMapOf([["item", padStyleMapOf(utsMapOf([["paddingTop", 15], ["paddingRight", 15], ["paddingBottom", 15], ["paddingLeft", 15], ["marginTop", 0], ["marginRight", 0], ["marginBottom", 5], ["marginLeft", 0], ["borderTopWidth", 1], ["borderRightWidth", 1], ["borderBottomWidth", 1], ["borderLeftWidth", 1], ["borderTopStyle", "solid"], ["borderRightStyle", "solid"], ["borderBottomStyle", "solid"], ["borderLeftStyle", "solid"], ["borderTopColor", "#000000"], ["borderRightColor", "#000000"], ["borderBottomColor", "#000000"], ["borderLeftColor", "#000000"], ["backgroundColor", "#fdfdfd"], ["borderTopLeftRadius", 5], ["borderTopRightRadius", 5], ["borderBottomRightRadius", 5], ["borderBottomLeftRadius", 5]]))], ["button_item", padStyleMapOf(utsMapOf([["width", 200]]))], ["scroll_item", padStyleMapOf(utsMapOf([["flex", 1], ["flexDirection", "row"], ["overflow", "hidden"], ["marginLeft", 10]]))], ["tip_text", padStyleMapOf(utsMapOf([["borderTopStyle", "solid"], ["borderRightStyle", "solid"], ["borderBottomStyle", "solid"], ["borderLeftStyle", "solid"], ["borderTopLeftRadius", 3], ["borderTopRightRadius", 3], ["borderBottomRightRadius", 3], ["borderBottomLeftRadius", 3], ["borderTopWidth", 1], ["borderRightWidth", 1], ["borderBottomWidth", 1], ["borderLeftWidth", 1], ["marginTop", 0], ["marginRight", 10], ["marginBottom", 0], ["marginLeft", 10]]))]])]
