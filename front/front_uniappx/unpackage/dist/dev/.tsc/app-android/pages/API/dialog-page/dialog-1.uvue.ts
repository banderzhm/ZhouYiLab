
  import {
    state,
    setLifeCycleNum
  } from '@/store/index.uts'

  const __sfc__ = defineComponent({
    data() {
      return {
        title: 'dialog 1',
        backgroundColorContent: 'transparent'
      }
    },
    onLoad(options : OnLoadOptions) {
      console.log('dialog 1 onLoad', options, " at pages/API/dialog-page/dialog-1.uvue:46")
      // 自动化测试
      setLifeCycleNum(state.lifeCycleNum + 1)
      if (options['name'] == 'dialog1') {
        // 自动化测试
        setLifeCycleNum(state.lifeCycleNum + 1)
      }
    },
    onShow() {
      console.log('dialog 1 onShow', " at pages/API/dialog-page/dialog-1.uvue:55")
      // 自动化测试
      setLifeCycleNum(state.lifeCycleNum + 1)
    },
    onReady() {
      console.log('dialog 1 onReady', " at pages/API/dialog-page/dialog-1.uvue:60")
      // 自动化测试
      setLifeCycleNum(state.lifeCycleNum + 1)
      const currentPages = getCurrentPages()
      const parentPage = this.$page.getParentPage()!
      const grandParentPage = parentPage.getParentPage()
      const dialogPages = parentPage.getDialogPages()
      const dialogPage = this.$page
      if (
        currentPages.length == 1 &&
        grandParentPage == null &&
        dialogPages.indexOf(dialogPage) != -1
      ) {
        // 自动化测试
        setLifeCycleNum(state.lifeCycleNum + 1)
      }
    },
    onHide() {
      console.log('dialog 1 onHide', " at pages/API/dialog-page/dialog-1.uvue:78")
      // 自动化测试
      setLifeCycleNum(state.lifeCycleNum - 1)
    },
    onUnload() {
      console.log('dialog 1 onUnload', " at pages/API/dialog-page/dialog-1.uvue:83")
      // 自动化测试
      setLifeCycleNum(state.lifeCycleNum - 5)
    },
    onBackPress(options : OnBackPressOptions) : boolean | null {
      console.log('dialogPage1 onBackPress', options, " at pages/API/dialog-page/dialog-1.uvue:88")
      // 自动化测试
      setLifeCycleNum(state.lifeCycleNum + 1)
      return true
    },
    methods: {
      goNextPage() {
        uni.navigateTo({ url: '/pages/API/dialog-page/next-page' })
      },
      openDialog2() {
        uni.openDialogPage({
          url: '/pages/API/dialog-page/dialog-2',
          disableEscBack: true,
          success(res) {
            console.log('openDialog2 success', res, " at pages/API/dialog-page/dialog-1.uvue:102")
            // 自动化测试
            setLifeCycleNum(state.lifeCycleNum + 1)
          },
          fail(err) {
            console.log('openDialog2 fail', err, " at pages/API/dialog-page/dialog-1.uvue:107")
            // 自动化测试
            setLifeCycleNum(state.lifeCycleNum - 4)
          },
          complete(res) {
            console.log('openDialog2 complete', res, " at pages/API/dialog-page/dialog-1.uvue:112")
            // 自动化测试
            setLifeCycleNum(state.lifeCycleNum + 1)
          }
        })
      },
      closeDialog() {
        uni.closeDialogPage({
          success(res) {
            console.log('closeDialog success', res, " at pages/API/dialog-page/dialog-1.uvue:121")
            // 自动化测试
            setLifeCycleNum(state.lifeCycleNum + 1)
          },
          fail(err) {
            console.log('closeDialog fail', err, " at pages/API/dialog-page/dialog-1.uvue:126")
            // 自动化测试
            setLifeCycleNum(state.lifeCycleNum - 4)
          },
          complete(res) {
            console.log('closeDialog complete', res, " at pages/API/dialog-page/dialog-1.uvue:131")
            // 自动化测试
            setLifeCycleNum(state.lifeCycleNum + 1)
          }
        })
      },
      closeThisDialog() {
        uni.closeDialogPage({
          dialogPage: this.$page,
          success(res) {
            console.log('closeThisDialog success', res, " at pages/API/dialog-page/dialog-1.uvue:141")
            // 自动化测试
            setLifeCycleNum(state.lifeCycleNum + 1)
          },
          fail(err) {
            console.log('closeThisDialog fail', err, " at pages/API/dialog-page/dialog-1.uvue:146")
            // 自动化测试
            setLifeCycleNum(state.lifeCycleNum - 4)
          },
          complete(res) {
            console.log('closeThisDialog complete', res, " at pages/API/dialog-page/dialog-1.uvue:151")
            // 自动化测试
            setLifeCycleNum(state.lifeCycleNum + 1)
          }
        })
      },
      checkGetParentPage() : boolean {
        const parentPage = this.$page.getParentPage()
        console.log('checkGetParentPage', parentPage, " at pages/API/dialog-page/dialog-1.uvue:159")
        const res = parentPage != null
        uni.showToast(res ? {
          title: 'check success'
        } : {
          title: 'check fail',
          icon: 'error'
        })
        return res
      },
      checkGetElementById() : boolean {
        const page = this.$page
        const element = page.getElementById('dialog1-go-next-page')
        let res = element != null







        console.log('check getElementById', res, " at pages/API/dialog-page/dialog-1.uvue:180")
        uni.showToast(res ? { title: 'check success' } : { title: 'check fail', icon: 'error' })
        return res
      },
      toggleBackgroundColor() {
        this.backgroundColorContent = this.backgroundColorContent == 'transparent' ? 'rgb(0, 122, 255)' : 'transparent'
        this.$page.setPageStyle({
          backgroundColorContent: this.backgroundColorContent
        })
      },
      back() {
        uni.navigateBack()
      }
    }
  })

export default __sfc__
function GenPagesAPIDialogPageDialog1Render(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
  return createElementVNode("view", utsMapOf({
    id: "dialog1",
    class: "dialog-container"
  }), [
    createElementVNode("view", utsMapOf({ class: "dialog-content" }), [
      createElementVNode("text", null, "title: " + toDisplayString(_ctx.title), 1 /* TEXT */),
      createElementVNode("text", utsMapOf({ class: "mt-10" }), "onBackPress return true"),
      createElementVNode("button", utsMapOf({
        class: "mt-10",
        id: "dialog1-go-next-page",
        onClick: _ctx.goNextPage
      }), " go next page ", 8 /* PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({
        class: "mt-10",
        id: "dialog1-open-dialog2",
        onClick: _ctx.openDialog2
      }), " openDialog2 ", 8 /* PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({
        class: "mt-10",
        id: "dialog1-close-dialog",
        onClick: _ctx.closeDialog
      }), " closeDialog ", 8 /* PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({
        class: "mt-10",
        id: "dialog1-close-this-dialog",
        onClick: _ctx.closeThisDialog
      }), " closeThisDialog ", 8 /* PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({
        class: "mt-10",
        onClick: _ctx.checkGetParentPage
      }), " check getParentPage ", 8 /* PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({
        class: "mt-10",
        onClick: _ctx.checkGetElementById
      }), " check getElementById ", 8 /* PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({
        class: "mt-10",
        onClick: _ctx.toggleBackgroundColor
      }), " toggleBackgroundColor ", 8 /* PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({
        class: "mt-10",
        id: "dialog1-back",
        onClick: _ctx.back
      }), "back", 8 /* PROPS */, ["onClick"])
    ])
  ])
}
const GenPagesAPIDialogPageDialog1Styles = [utsMapOf([["dialog-container", padStyleMapOf(utsMapOf([["width", "100%"], ["height", "100%"], ["backgroundColor", "rgba(0,0,0,0.3)"], ["display", "flex"], ["justifyContent", "center"], ["alignItems", "center"]]))], ["dialog-content", padStyleMapOf(utsMapOf([["width", "80%"], ["paddingTop", 10], ["paddingRight", 10], ["paddingBottom", 10], ["paddingLeft", 10], ["backgroundColor", "#ffffff"], ["borderTopLeftRadius", 6], ["borderTopRightRadius", 6], ["borderBottomRightRadius", 6], ["borderBottomLeftRadius", 6]]))], ["mt-10", padStyleMapOf(utsMapOf([["marginTop", 10]]))]])]
