
  const __sfc__ = defineComponent({
    methods: {
      goOnLoad(type: string){
        uni.navigateTo({
          url: `/pages/API/navigator/new-page/onLoad?type=${type}`
        })
      }
    }
  })

export default __sfc__
function GenPagesAPINavigatorNewPageOnLoadCallApiRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
  return createElementVNode("view", utsMapOf({ class: "uni-padding-wrap" }), [
    createElementVNode("button", utsMapOf({
      onClick: () => {_ctx.goOnLoad('adjustData')}
    }), "onLoad adjust data", 8 /* PROPS */, ["onClick"]),
    createElementVNode("button", utsMapOf({
      onClick: () => {_ctx.goOnLoad('navigateTo')}
    }), "onLoad navigateTo", 8 /* PROPS */, ["onClick"]),
    createElementVNode("button", utsMapOf({
      onClick: () => {_ctx.goOnLoad('navigateBack')}
    }), "onLoad navigateBack", 8 /* PROPS */, ["onClick"]),
    createElementVNode("button", utsMapOf({
      onClick: () => {_ctx.goOnLoad('redirectTo')}
    }), "onLoad redirectTo", 8 /* PROPS */, ["onClick"]),
    createElementVNode("button", utsMapOf({
      onClick: () => {_ctx.goOnLoad('reLaunch')}
    }), "onLoad reLaunch", 8 /* PROPS */, ["onClick"]),
    createElementVNode("button", utsMapOf({
      onClick: () => {_ctx.goOnLoad('switchTab')}
    }), "onLoad switchTab", 8 /* PROPS */, ["onClick"]),
    createElementVNode("button", utsMapOf({
      onClick: () => {_ctx.goOnLoad('showToast')}
    }), "onLoad showToast", 8 /* PROPS */, ["onClick"]),
    createElementVNode("button", utsMapOf({
      onClick: () => {_ctx.goOnLoad('showLoading')}
    }), "onLoad showLoading", 8 /* PROPS */, ["onClick"]),
    createElementVNode("button", utsMapOf({
      onClick: () => {_ctx.goOnLoad('showModal')}
    }), "onLoad showModal", 8 /* PROPS */, ["onClick"]),
    createElementVNode("button", utsMapOf({
      onClick: () => {_ctx.goOnLoad('showActionSheet')}
    }), "onLoad showActionSheet", 8 /* PROPS */, ["onClick"])
  ])
}
const GenPagesAPINavigatorNewPageOnLoadCallApiStyles = []
