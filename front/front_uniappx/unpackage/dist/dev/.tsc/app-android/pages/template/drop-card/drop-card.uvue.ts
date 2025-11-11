
  import card from './card/card.uvue';
  const __sfc__ = defineComponent({
    components: {
      card
    },
    data() {
      return {
        cardList: [
          'https://web-ext-storage.dcloud.net.cn/hello-uni-app-x/drop-card-1.jpg',
          'https://web-ext-storage.dcloud.net.cn/hello-uni-app-x/drop-card-2.jpg',
          'https://web-ext-storage.dcloud.net.cn/hello-uni-app-x/drop-card-3.jpg'
        ] as string.ImageURIString[]
      }
    }
  })

export default __sfc__
function GenPagesTemplateDropCardDropCardRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
const _component_card = resolveComponent("card")

  return createElementVNode("view", utsMapOf({ class: "root" }), [
    createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.cardList, (item, index, __index, _cached): any => {
      return createVNode(_component_card, utsMapOf({
        key: index,
        ref_for: true,
        ref: "card",
        img: item,
        cardIndex: index
      }), null, 8 /* PROPS */, ["img", "cardIndex"])
    }), 128 /* KEYED_FRAGMENT */)
  ])
}
const GenPagesTemplateDropCardDropCardStyles = [utsMapOf([["root", padStyleMapOf(utsMapOf([["flex", 1], ["position", "relative"], ["alignItems", "center"]]))]])]
