
  const __sfc__ = defineComponent({
    data() {
      return {
        fontSize: '15px'
      }
    },
    methods: {
      // 自动化测试
      setFontSize() {
        this.fontSize = '30px';
      }
    }
  })

export default __sfc__
function GenPagesCSSTextFontSizeRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
  return createElementVNode("view", utsMapOf({
    style: normalizeStyle(utsMapOf({"flex-grow":"1"}))
  }), [
    createElementVNode("view", utsMapOf({
      style: normalizeStyle(utsMapOf({"height":"250px","background-color":"gray","justify-content":"center","align-items":"center"}))
    }), [
      createElementVNode("text", utsMapOf({
        ref: "text",
        style: normalizeStyle(utsMapOf({'font-size': _ctx.fontSize}))
      }), "font-size: " + toDisplayString(_ctx.fontSize), 5 /* TEXT, STYLE */),
      createElementVNode("text", utsMapOf({
        style: normalizeStyle(utsMapOf({"font-size":"30px"}))
      }), "font-size: 30px", 4 /* STYLE */)
    ], 4 /* STYLE */)
  ], 4 /* STYLE */)
}
const GenPagesCSSTextFontSizeStyles = []
