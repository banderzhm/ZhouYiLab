
  const __sfc__ = defineComponent({
    data() {
      return {
        list: ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12', '13', '14', '15'],
        totalHeight: 0,		//总高度
        halfMove: false,	//是否Move，响应TouchMove
        halfScreenY: 0,		//响应TouchMove的起始点Y坐标
        halfOffset: 0,		//偏移的位置，translateY
        halfHeight: 0,		//高度
        lastY: 0,			//上次
        lastY2: 0,			//
        bAnimation: false,	//是否动画
        halfNode: null as UniElement | null,
        scrollNode: null as UniElement | null,
        scrollDirection: 'vertical'
      }
    },
    methods: {
      onHalfTouchStart(_ : TouchEvent) {
        this.halfNode?.style?.setProperty('transition-duration', 0);
      },
      onHalfTouchMove(e : TouchEvent) {
        if (this.bAnimation) {//容错处理
          return;
        }
        let top : number = this.scrollNode?.scrollTop ?? 0;
        let p = e.touches[0];
        this.lastY2 = this.lastY;
        this.lastY = p.screenY;
        if (top <= 0.01 || this.halfMove) {
          if (this.halfScreenY == 0) {
            this.halfScreenY = p.screenY;
          }
          let offset = p.screenY - this.halfScreenY;
          if (offset > 0) {//向下滚动
            this.halfMove = true;

            //this.scrollNode?.setAttribute('scroll-y', 'false');
            this.scrollNode?.setAttribute('direction', 'none');




            this.halfNode?.style?.setProperty('transform', 'translateY(' + offset.toFixed(2) + 'px)');
            this.halfOffset = offset;
          } else if (this.halfOffset > 0) {//向上滚动
            offset = this.halfScreenY - p.screenY;
            if (offset > this.halfOffset) {
              offset = 0;
              this.halfMove = false;
            }

            //this.scrollNode?.setAttribute('scroll-y', 'true');
            this.scrollNode?.setAttribute('direction', 'vertical');




            this.halfNode?.style?.setProperty('transform', 'translateY(' + offset.toFixed(2) + 'px)');
            this.halfOffset = offset;
          }
        }



      },
      onHalfTouchEnd(_ : TouchEvent) {
        this.halfScreenY = 0;
        if (this.bAnimation) {//容错处理
          return;
        }
        let top : number = this.scrollNode?.scrollTop ?? 0;
        let bHide = (this.halfHeight - this.halfOffset) < this.halfHeight / 4;
        if (bHide) {
          bHide = this.lastY2 > 0 && this.lastY2 <= this.lastY;
        } else if (top <= 0.01) {
          bHide = (this.lastY - this.lastY2) > 3;		//向下滑动计算加速度判断是否关闭，简单处理未考虑时间
        }
        if (bHide) {
          this.switchHalfScreen(false);
        } else if (this.halfOffset > 0) {
          this.resumeHalfScreen();
        }
      },
      switchHalfScreen(show : boolean) {
        if (show && ('visible' == this.halfNode?.style?.getPropertyValue('visibility'))) {//容错处理
          console.log('quick click button!!!', " at pages/template/half-screen/half-screen.uvue:104");
          return;
        }
        this.halfMove = false;

        //this.scrollNode?.setAttribute('scroll-y', 'true');
        this.scrollNode?.setAttribute('direction', 'vertical');




        this.halfScreenY = 0;
        this.halfOffset = 0;
        let top = this.totalHeight;
        let time = 300;
        if (show) {
          top = this.totalHeight * 30 / 100;	//计算显示的位置
          this.halfNode?.style?.setProperty('visibility', 'visible');
          this.halfNode?.style?.setProperty('transition-timing-function', 'ease-in-out');
        } else {
          this.halfNode?.style?.setProperty('transition-timing-function', 'linear');
          time *= (this.halfHeight / this.totalHeight);		//计算关闭动画时间
        }
        this.halfNode?.style?.setProperty('transition-duration', time.toFixed(0)+'ms');
        this.halfNode?.style?.setProperty('transition-property', 'top');
        this.halfNode?.style?.setProperty('top', top.toFixed(2)+'px');
        setTimeout(() => {
          if (!show) {
            this.halfNode?.style?.setProperty('visibility', 'hidden');
            this.halfNode?.style?.setProperty('transition-duration', 0);
            this.halfNode?.style?.setProperty('transform', '');
          }
          this.halfNode?.style?.setProperty('transition-property', 'none');
          this.bAnimation = false;
        }, time)
        this.bAnimation = true;
      },
      resumeHalfScreen() {
        let time = 300;//(500*this.halfOffset/this.halfHeight).toFixed(0); //回弹动画时间
        this.halfNode?.style?.setProperty('transition-duration', time.toFixed(0)+'ms');
        this.halfNode?.style?.setProperty('transition-timing-function', 'ease-in-out');
        this.halfNode?.style?.setProperty('transition-property', 'transform');
        this.halfNode?.style?.setProperty('transform', 'translateY(0px)');
        this.halfMove = false;

        //this.scrollNode?.setAttribute('scroll-y', 'true');
        this.scrollNode?.setAttribute('direction', 'vertical');




        this.halfScreenY = 0;
        this.halfOffset = 0;
        setTimeout(() => {
          this.bAnimation = false;
          this.halfNode?.style?.setProperty('transition-property', 'none');
        }, time)
        this.bAnimation = true;
      }
    },
    onReady() {
      this.halfNode = this.$refs['halfScreen'] as UniElement;//uni.getElementById('halfScreen');
      this.scrollNode = this.$refs['halfScroll'] as UniElement;//uni.getElementById('halfScroll');
      this.halfNode!.getBoundingClientRectAsync()!.then((rect: DOMRect) => {
          this.halfHeight = rect.height
      });
      (this.$refs['page'] as UniElement).getBoundingClientRectAsync()!.then((rect: DOMRect) => {
          this.totalHeight = rect.height
          this.halfNode?.style?.setProperty('top', this.totalHeight.toFixed(2)+'px');
      });
    },
    onResize() {
      this.halfNode?.getBoundingClientRectAsync()!.then((rect: DOMRect) => {
          this.halfHeight = rect.height
      });
      this.totalHeight = uni.getWindowInfo().windowHeight;
      this.halfNode?.style?.setProperty('top', this.totalHeight.toFixed(2)+'px');
      this.halfNode?.style?.setProperty('visibility', 'hidden');
    },
    onBackPress(): boolean {

      if('visible' == this.halfNode?.style?.getPropertyValue('visibility')){
        this.switchHalfScreen(false);
        return true;
      }
      return false;

    }
  })

export default __sfc__
function GenPagesTemplateHalfScreenHalfScreenRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
  return createElementVNode("view", utsMapOf({
    ref: "page",
    class: "page"
  }), [
    createElementVNode("text", utsMapOf({ class: "tip" }), "半屏弹窗，演示了弹出层内scroll-view滚动到顶时由滚变拖。本效果是通过监听TouchEvent实现，当半屏窗口移动时禁用scroll-view的滚动，避免两者的冲突。"),
    createElementVNode("button", utsMapOf({
      class: "bottomButton",
      onClick: () => {_ctx.switchHalfScreen(true)}
    }), "打开弹窗", 8 /* PROPS */, ["onClick"]),
    createElementVNode("view", utsMapOf({
      ref: "halfScreen",
      class: "halfScreen",
      onTouchstart: _ctx.onHalfTouchStart,
      onTouchmove: _ctx.onHalfTouchMove,
      onTouchend: _ctx.onHalfTouchEnd
    }), [
      createElementVNode("view", utsMapOf({ class: "halfTitle" }), "半屏弹窗标题"),
      createElementVNode("scroll-view", utsMapOf({
        ref: "halfScroll",
        class: "halfScroll",
        bounces: "true",
        direction: _ctx.scrollDirection
      }), [
        createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.list, (item, index, __index, _cached): any => {
          return createElementVNode("view", utsMapOf({
            key: index,
            class: "item"
          }), " half screen content-" + toDisplayString(item), 1 /* TEXT */)
        }), 128 /* KEYED_FRAGMENT */)
      ], 8 /* PROPS */, ["direction"])
    ], 40 /* PROPS, NEED_HYDRATION */, ["onTouchstart", "onTouchmove", "onTouchend"])
  ], 512 /* NEED_PATCH */)
}
const GenPagesTemplateHalfScreenHalfScreenStyles = [utsMapOf([["page", padStyleMapOf(utsMapOf([["flex", 1], ["backgroundColor", "#A9A9A9"]]))], ["tip", padStyleMapOf(utsMapOf([["marginTop", 10], ["marginRight", 10], ["marginBottom", 10], ["marginLeft", 10]]))], ["bottomButton", padStyleMapOf(utsMapOf([["position", "absolute"], ["width", "100%"], ["bottom", 0], ["paddingBottom", "env(safe-area-inset-bottom,0px)"]]))], ["halfScreen", padStyleMapOf(utsMapOf([["position", "absolute"], ["top", "100%"], ["width", "100%"], ["height", "70%"], ["transitionTimingFunction", "ease-in-out"], ["transitionProperty", "top"], ["transitionDuration", "0ms"], ["visibility", "hidden"]]))], ["halfTitle", padStyleMapOf(utsMapOf([["alignItems", "center"], ["justifyContent", "center"], ["height", 48], ["backgroundColor", "#F8F8FF"], ["borderTopLeftRadius", 10], ["borderTopRightRadius", 10], ["borderBottomRightRadius", 0], ["borderBottomLeftRadius", 0]]))], ["halfScroll", padStyleMapOf(utsMapOf([["backgroundColor", "#FFFFFF"], ["flex", 1]]))], ["item", padStyleMapOf(utsMapOf([["height", 100]]))], ["@TRANSITION", utsMapOf([["halfScreen", utsMapOf([["timingFunction", "ease-in-out"], ["property", "top"], ["duration", "0ms"]])]])]])]
