const __sfc__ = defineComponent({
  __name: 'u-link',
  props: {
    href: {
      type: String,
      default: ''
    },
    text: {
      type: String,
      default: ''
    },
    inWhiteList: {
      type: Boolean,
      default: false
    }
  },
  setup(__props): any | null {
const __ins = getCurrentInstance()!;
const _ctx = __ins.proxy as InstanceType<typeof __sfc__>;
const _cache = __ins.renderCache;

  /**
   * @description u-link是一个外部网页超链接组件，在小程序内打开内部web-view组件或复制url，在app内打开外部浏览器，在h5端打开新网页
   * @property {String} href 点击后打开的外部网页url，小程序中必须以https://开头
   * @property {String} text 显示的文字
   * @property {Boolean} inWhiteList 是否在小程序白名单中，如果在的话，在小程序端会直接打开内置web-view，否则会只会复制url，提示在外部打开
   * @example * <u-link href="https://ext.dcloud.net.cn" text="https://ext.dcloud.net.cn" :inWhiteList="true"></u-link>
   */
  
  const props = __props
  
  const openURL = () => {
    //




















  }

return (): any | null => {

  return createElementVNode("view", utsMapOf({ class: "uni-row" }), [
    createElementVNode("text", utsMapOf({
      class: "text",
      href: _ctx.href,
      onClick: openURL,
      inWhiteList: _ctx.inWhiteList
    }), toDisplayString(_ctx.text), 9 /* TEXT, PROPS */, ["href", "inWhiteList"])
  ])
}
}

})
export default __sfc__
export type ULinkComponentPublicInstance = InstanceType<typeof __sfc__>;
const GenComponentsULinkULinkStyles = [utsMapOf([["text", padStyleMapOf(utsMapOf([["color", "#7A7E83"], ["fontSize", 14], ["lineHeight", "20px"]]))]])]
