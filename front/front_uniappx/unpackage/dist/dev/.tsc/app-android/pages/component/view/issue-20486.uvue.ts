const __sfc__ = defineComponent({
  __name: 'issue-20486',
  setup(__props, { expose: __expose }: SetupContext): any | null {
const __ins = getCurrentInstance()!;
const _ctx = __ins.proxy as InstanceType<typeof __sfc__>;
const _cache = __ins.renderCache;

  const isDarkMode = ref(false)
  const testViewY = ref(0);

  onReady(() => {
    let ele = uni.getElementById('testView') as UniElement
    const currentPage = getCurrentInstance()!.proxy!.$page
    testViewY.value = ele.getBoundingClientRect().y + currentPage.statusBarHeight + 44
		console.log(testViewY.value, " at pages/component/view/issue-20486.uvue:21")
  })

  const toggleTheme = () => {
    isDarkMode.value = !isDarkMode.value
  }

  function getTestViewY() {
    return testViewY.value;
  }

  __expose({
    getTestViewY
  })

return (): any | null => {

  return createElementVNode("scroll-view", utsMapOf({
    style: normalizeStyle(utsMapOf({"flex":"1"})),
    class: normalizeClass(unref(isDarkMode)? 'dark' : 'light')
  }), [
    createElementVNode("view", utsMapOf({ class: "box" }), [
      createElementVNode("text", utsMapOf({
        class: normalizeClass(unref(isDarkMode)? 'darkText' : 'lightText')
      }), "当前模式：" + toDisplayString(unref(isDarkMode)? 'dark' : 'light'), 3 /* TEXT, CLASS */)
    ]),
    createElementVNode("view", utsMapOf({
      id: "testView",
      "hover-class": "btn-hover",
      class: "btn",
      onClick: toggleTheme
    }), [
      createElementVNode("text", null, "切换:" + toDisplayString(unref(isDarkMode)? 'dark' : 'light') + " (应该无背景色)", 1 /* TEXT */)
    ])
  ], 6 /* CLASS, STYLE */)
}
}

})
export default __sfc__
const GenPagesComponentViewIssue20486Styles = [utsMapOf([["dark", padStyleMapOf(utsMapOf([["-LBoxBgColor", "#000"]]))], ["lightText", padStyleMapOf(utsMapOf([["color", "#000000"]]))], ["darkText", padStyleMapOf(utsMapOf([["color", "#FFFFFF"]]))], ["btn", padStyleMapOf(utsMapOf([["height", 50], ["paddingTop", 10], ["paddingRight", 10], ["paddingBottom", 10], ["paddingLeft", 10]]))], ["btn-hover", padStyleMapOf(utsMapOf([["backgroundColor", "var(--l-btn-hover-color, red)"]]))], ["box", padStyleMapOf(utsMapOf([["width", 140], ["height", 140], ["backgroundColor", "var(--l-box-bg-color, #f5f5f5)"]]))]])]
