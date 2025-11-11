
  const __sfc__ = defineComponent({
    data() {
      return {
        mainView: null as UniElement | null,
        animation: null as UniAnimation | null
      }
    },
    onReady() {
      this.mainView = uni.getElementById("main")
      var roll = uni.getElementById("roll")
      roll!.animate([
        { transform: "translateX(0) rotate(0)" }, // keyframe
        { transform: "translateX(200px) rotate(540deg)" }, // keyframe
      ],
        {
          // keyframe options
          duration: 2000,
          direction: "alternate",

          easing: "ease-in-out",
          iterations: Infinity,
        },
      )
    },
    methods: {
      startAnimate() {
        this.animation = this.mainView!.animate([
          {
            transform: "scale(1)",
            transformOrigin: "0px 0px"
          },
          {
            transform: "scale(0)",
            transformOrigin: "50px 50px"
          },
          {
            transform: "scale(1)",
            transformOrigin: "100px 100px"
          }
        ], {
          duration: 5000,

        })
        this.animation!.oncancel = (_ : UniAnimationPlaybackEvent) => {
          uni.showToast({
            title: "动画被取消了"
          })
        }
        this.animation!.onfinish = (_ : UniAnimationPlaybackEvent) => {
          uni.showToast({
            title: "动画播放完成"
          })
        }
      },
      pauseAnimate() {
        this.animation?.pause()
      },
      resumeAnimate() {
        this.animation?.play()
      },
      cancelAnimate() {
        this.animation?.cancel()
      },
      widthProperty(e : UniPointerEvent) {
        e.currentTarget?.animate({
          width: ["100px", "200px", "100px"]
        }, {
          duration: 1000,
          fill: "forwards"
        })
      },
      heightProperty(e : UniPointerEvent) {
        e.currentTarget?.animate({
          height: ["100px", "200px"]
        }, {
          duration: 1000,
          fill: "forwards"
        })
      },
      marginProperty(e : UniPointerEvent) {
        e.currentTarget?.animate({
          margin: ["8px", "16px", "32px"]
        }, {
          duration: 1000,
          fill: "forwards"
        })
      },
      paddingProperty(e : UniPointerEvent) {
        e.currentTarget?.animate({
          padding: ["0px", "16px", "32px", "0px"]
        }, {
          duration: 1000,
          fill: "forwards"
        })
      },
      backgroundProperty(e : UniPointerEvent) {
        e.currentTarget?.animate([
          {
            offset: 0.3,
            backgroundColor: "yellow"
          },
          {
            offset: 0.6,
            backgroundColor: "red"
          },
          {
            backgroundColor: "blue"
          }
        ], {
          duration: 1000,
          fill: "forwards"
        })
      },
      borderProperty(e : UniPointerEvent) {
        e.currentTarget?.animate([
          {
            offset: 0.3,
            borderColor: "yellow"
          },
          {
            offset: 0.6,
            borderColor: "pink"
          },
          {
            borderColor: "blue"
          }
        ], {
          duration: 1000,
          fill: "forwards"
        })
      },
      transformProperty(e : UniPointerEvent) {
        e.currentTarget?.animate([
          {
            transform: "translateX(0px) scale(1) rotate(0deg)"
          },
          {
            transform: "translateX(100px)"
          },
          {
            transform: "scale(0.8) rotate(180deg)"
          }
        ], {
          duration: 1000,
          fill: "forwards"
        })
      },
      positionProperty(e : UniPointerEvent) {
        e.currentTarget?.animate({
          left: ["0px", "16px", "32px", "0px"]
        }, {
          duration: 1000,
          fill: "forwards"
        })
      },
      backgroundAndWidthProperty(e : UniPointerEvent) {
        e.currentTarget?.animate({
          width: ["100px", "200px"],
          backgroundColor: ["red", "yellow", "blue"]
        }, {
          duration: 1000,
          fill: "forwards"
        })
      },
      backgroundAndMarginLeftProperty(e : UniPointerEvent) {
        e.currentTarget?.animate([
          {
            offset: 0.2,
            backgroundColor: "red"
          }, {
            marginLeft: "10px"
          }, {
            marginLeft: "20px"
          }, {
            marginLeft: "30px",
            backgroundColor: "pink"
          }
        ], {
          duration: 1000,
          fill: "forwards"
        })
      },
      backgroundAndTransformProperty(e : UniPointerEvent) {
        e.currentTarget?.animate([
          {
            offset: 0.2,
            backgroundColor: "red"
          }, {
            transform: "translate(30px,0px)"
          }, {
            transform: "translate(50px,0px)"
          }, {
            transform: "translate(100px,0px)",
            backgroundColor: "pink"
          }
        ], {
          duration: 1000,
          fill: "forwards"
        })
      },
      oneProperty1(e : UniPointerEvent) {
        e.currentTarget?.animate({
          backgroundColor: "green"
        }, {
          duration: 1000,
          fill: "forwards"
        })
      },
      oneProperty2(e : UniPointerEvent) {
        e.currentTarget?.animate([{
          backgroundColor: "blue"
        }], {
          duration: 1000,
          fill: "forwards"
        })
      },
      borderColorMarginLeftProperty(e : UniPointerEvent) {
        e.currentTarget?.animate([
          {
            borderColor: "red",
            marginLeft: "0px",
            offset: 0
          },
          {
            marginLeft: "20px",
            offset: 0.5
          },
          {
            marginLeft: "60px",
            borderColor: "yellow",
            offset: 1
          }
        ], {
          duration: 1000,
          fill: "forwards"
        })
      },
      opacityProperty(e : UniPointerEvent){
        e.currentTarget?.animate([
          {
            offset: 0.3,
            opacity: "1"
          },
          {
            offset: 0.6,
            opacity: "0.1"
          },
          {
            opacity: "1"
          }
        ], {
          duration: 1000,
          fill: "forwards"
        })
      }
    }
  })

export default __sfc__
function GenPagesAPIAnimateAnimateRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
  return createElementVNode("scroll-view", utsMapOf({
    style: normalizeStyle(utsMapOf({"flex":"1"}))
  }), [
    createElementVNode("view", utsMapOf({
      id: "main",
      style: normalizeStyle(utsMapOf({"width":"100px","height":"100px","background-color":"brown","transform":"scale(1)"}))
    }), null, 4 /* STYLE */),
    createElementVNode("button", utsMapOf({ onClick: _ctx.startAnimate }), "开始动画", 8 /* PROPS */, ["onClick"]),
    createElementVNode("button", utsMapOf({ onClick: _ctx.pauseAnimate }), "暂停动画", 8 /* PROPS */, ["onClick"]),
    createElementVNode("button", utsMapOf({ onClick: _ctx.resumeAnimate }), "恢复动画", 8 /* PROPS */, ["onClick"]),
    createElementVNode("button", utsMapOf({ onClick: _ctx.cancelAnimate }), "取消动画", 8 /* PROPS */, ["onClick"]),
    createElementVNode("image", utsMapOf({
      src: "/static/uni.png",
      id: "roll",
      style: normalizeStyle(utsMapOf({"width":"100px","height":"100px","margin":"10px"}))
    }), null, 4 /* STYLE */),
    createElementVNode("view", utsMapOf({
      style: normalizeStyle(utsMapOf({"border-radius":"5px","margin":"4px","padding":"4px","border-style":"solid","background-color":"#eee","border-color":"#eee"}))
    }), [
      createElementVNode("text", utsMapOf({
        style: normalizeStyle(utsMapOf({"margin-bottom":"4px"}))
      }), "修改宽度", 4 /* STYLE */),
      createElementVNode("view", utsMapOf({
        id: "widthProperty",
        style: normalizeStyle(utsMapOf({"width":"100px","height":"100px","background-color":"brown"})),
        onClick: _ctx.widthProperty
      }), null, 12 /* STYLE, PROPS */, ["onClick"])
    ], 4 /* STYLE */),
    createElementVNode("view", utsMapOf({
      style: normalizeStyle(utsMapOf({"border-radius":"5px","margin":"4px","padding":"4px","border-style":"solid","background-color":"#eee","border-color":"#eee"}))
    }), [
      createElementVNode("text", utsMapOf({
        style: normalizeStyle(utsMapOf({"margin-bottom":"4px"}))
      }), "修改高度", 4 /* STYLE */),
      createElementVNode("view", utsMapOf({
        id: "height1",
        style: normalizeStyle(utsMapOf({"width":"100px","height":"100px","background-color":"brown"})),
        onClick: _ctx.heightProperty
      }), null, 12 /* STYLE, PROPS */, ["onClick"])
    ], 4 /* STYLE */),
    createElementVNode("view", utsMapOf({
      style: normalizeStyle(utsMapOf({"border-radius":"5px","margin":"4px","padding":"4px","border-style":"solid","background-color":"#eee","border-color":"#eee"}))
    }), [
      createElementVNode("text", utsMapOf({
        style: normalizeStyle(utsMapOf({"margin-bottom":"4px"}))
      }), "修改margin", 4 /* STYLE */),
      createElementVNode("view", utsMapOf({
        id: "marginProperty",
        style: normalizeStyle(utsMapOf({"width":"100px","height":"100px","background-color":"brown"})),
        onClick: _ctx.marginProperty
      }), null, 12 /* STYLE, PROPS */, ["onClick"])
    ], 4 /* STYLE */),
    createElementVNode("view", utsMapOf({
      style: normalizeStyle(utsMapOf({"border-radius":"5px","margin":"4px","padding":"4px","border-style":"solid","background-color":"#eee","border-color":"#eee"}))
    }), [
      createElementVNode("text", utsMapOf({
        style: normalizeStyle(utsMapOf({"margin-bottom":"4px"}))
      }), "修改padding", 4 /* STYLE */),
      createElementVNode("view", utsMapOf({
        id: "paddingProperty",
        style: normalizeStyle(utsMapOf({"width":"100px","height":"100px","background-color":"brown"})),
        onClick: _ctx.paddingProperty
      }), [
        createElementVNode("view", utsMapOf({
          style: normalizeStyle(utsMapOf({"width":"50px","height":"50px","background-color":"black"}))
        }), null, 4 /* STYLE */)
      ], 12 /* STYLE, PROPS */, ["onClick"])
    ], 4 /* STYLE */),
    createElementVNode("view", utsMapOf({
      style: normalizeStyle(utsMapOf({"border-radius":"5px","margin":"4px","padding":"4px","border-style":"solid","background-color":"#eee","border-color":"#eee"}))
    }), [
      createElementVNode("text", utsMapOf({
        style: normalizeStyle(utsMapOf({"margin-bottom":"4px"}))
      }), "修改border颜色", 4 /* STYLE */),
      createElementVNode("view", utsMapOf({
        id: "borderProperty",
        style: normalizeStyle(utsMapOf({"width":"100px","height":"100px","background-color":"brown","border-width":"10px","border-color":"black","border-style":"solid"})),
        onClick: _ctx.borderProperty
      }), null, 12 /* STYLE, PROPS */, ["onClick"])
    ], 4 /* STYLE */),
    createElementVNode("view", utsMapOf({
      style: normalizeStyle(utsMapOf({"border-radius":"5px","margin":"4px","padding":"4px","border-style":"solid","background-color":"#eee","border-color":"#eee"}))
    }), [
      createElementVNode("text", utsMapOf({
        style: normalizeStyle(utsMapOf({"margin-bottom":"4px"}))
      }), "修改transform", 4 /* STYLE */),
      createElementVNode("view", utsMapOf({
        id: "transformProperty",
        style: normalizeStyle(utsMapOf({"width":"100px","height":"100px","background-color":"brown"})),
        onClick: _ctx.transformProperty
      }), null, 12 /* STYLE, PROPS */, ["onClick"])
    ], 4 /* STYLE */),
    createElementVNode("view", utsMapOf({
      style: normalizeStyle(utsMapOf({"border-radius":"5px","margin":"4px","padding":"4px","border-style":"solid","background-color":"#eee","border-color":"#eee"}))
    }), [
      createElementVNode("text", utsMapOf({
        style: normalizeStyle(utsMapOf({"margin-bottom":"4px"}))
      }), "修改position", 4 /* STYLE */),
      createElementVNode("view", utsMapOf({
        id: "positionProperty",
        style: normalizeStyle(utsMapOf({"width":"100px","height":"100px","background-color":"brown"})),
        onClick: _ctx.positionProperty
      }), null, 12 /* STYLE, PROPS */, ["onClick"])
    ], 4 /* STYLE */),
    createElementVNode("view", utsMapOf({
      style: normalizeStyle(utsMapOf({"border-radius":"5px","margin":"4px","padding":"4px","border-style":"solid","background-color":"#eee","border-color":"#eee"}))
    }), [
      createElementVNode("text", utsMapOf({
        style: normalizeStyle(utsMapOf({"margin-bottom":"4px"}))
      }), "修改背景色和宽度", 4 /* STYLE */),
      createElementVNode("view", utsMapOf({
        id: "backgroundAndWidthProperty",
        style: normalizeStyle(utsMapOf({"width":"100px","height":"100px","background-color":"brown"})),
        onClick: _ctx.backgroundAndWidthProperty
      }), null, 12 /* STYLE, PROPS */, ["onClick"])
    ], 4 /* STYLE */),
    createElementVNode("view", utsMapOf({
      style: normalizeStyle(utsMapOf({"border-radius":"5px","margin":"4px","padding":"4px","border-style":"solid","background-color":"#eee","border-color":"#eee"}))
    }), [
      createElementVNode("text", utsMapOf({
        style: normalizeStyle(utsMapOf({"margin-bottom":"4px"}))
      }), "执行的动画只有一个值1", 4 /* STYLE */),
      createElementVNode("view", utsMapOf({
        id: "oneProperty1",
        style: normalizeStyle(utsMapOf({"width":"100px","height":"100px","background-color":"brown"})),
        onClick: _ctx.oneProperty1
      }), null, 12 /* STYLE, PROPS */, ["onClick"])
    ], 4 /* STYLE */),
    createElementVNode("view", utsMapOf({
      style: normalizeStyle(utsMapOf({"border-radius":"5px","margin":"4px","padding":"4px","border-style":"solid","background-color":"#eee","border-color":"#eee"}))
    }), [
      createElementVNode("text", utsMapOf({
        style: normalizeStyle(utsMapOf({"margin-bottom":"4px"}))
      }), "执行的动画只有一个值2", 4 /* STYLE */),
      createElementVNode("view", utsMapOf({
        id: "oneProperty2",
        style: normalizeStyle(utsMapOf({"width":"100px","height":"100px","background-color":"brown"})),
        onClick: _ctx.oneProperty2
      }), null, 12 /* STYLE, PROPS */, ["onClick"])
    ], 4 /* STYLE */),
    createElementVNode("view", utsMapOf({
      style: normalizeStyle(utsMapOf({"border-radius":"5px","margin":"4px","padding":"4px","border-style":"solid","background-color":"#eee","border-color":"#eee"}))
    }), [
      createElementVNode("text", utsMapOf({
        style: normalizeStyle(utsMapOf({"margin-bottom":"4px"}))
      }), "修改背景色和margin-left(关键帧)", 4 /* STYLE */),
      createElementVNode("view", utsMapOf({
        id: "backgroundAndMarginLeftProperty",
        style: normalizeStyle(utsMapOf({"width":"100px","height":"100px","background-color":"brown"})),
        onClick: _ctx.backgroundAndMarginLeftProperty
      }), null, 12 /* STYLE, PROPS */, ["onClick"])
    ], 4 /* STYLE */),
    createElementVNode("view", utsMapOf({
      style: normalizeStyle(utsMapOf({"border-radius":"5px","margin":"4px","padding":"4px","border-style":"solid","background-color":"#eee","border-color":"#eee"}))
    }), [
      createElementVNode("text", utsMapOf({
        style: normalizeStyle(utsMapOf({"margin-bottom":"4px"}))
      }), "修改背景色和transform(关键帧)", 4 /* STYLE */),
      createElementVNode("view", utsMapOf({
        id: "backgroundAndTransformProperty",
        style: normalizeStyle(utsMapOf({"width":"100px","height":"100px","background-color":"brown"})),
        onClick: _ctx.backgroundAndTransformProperty
      }), null, 12 /* STYLE, PROPS */, ["onClick"])
    ], 4 /* STYLE */),
    createElementVNode("view", utsMapOf({
      style: normalizeStyle(utsMapOf({"border-radius":"5px","margin":"4px","padding":"4px","border-style":"solid","background-color":"#eee","border-color":"#eee"}))
    }), [
      createElementVNode("text", utsMapOf({
        style: normalizeStyle(utsMapOf({"margin-bottom":"4px"}))
      }), "修改背景色(关键帧)", 4 /* STYLE */),
      createElementVNode("view", utsMapOf({
        id: "backgroundProperty",
        style: normalizeStyle(utsMapOf({"width":"100px","height":"100px","background-color":"brown"})),
        onClick: _ctx.backgroundProperty
      }), null, 12 /* STYLE, PROPS */, ["onClick"])
    ], 4 /* STYLE */),
    createElementVNode("view", utsMapOf({
      style: normalizeStyle(utsMapOf({"border-radius":"5px","margin":"4px","padding":"4px","border-style":"solid","background-color":"#eee","border-color":"#eee"}))
    }), [
      createElementVNode("text", utsMapOf({
        style: normalizeStyle(utsMapOf({"margin-bottom":"4px"}))
      }), "修改opacity(关键帧)", 4 /* STYLE */),
      createElementVNode("view", utsMapOf({
        id: "opacityProperty",
        style: normalizeStyle(utsMapOf({"width":"100px","height":"100px","background-color":"brown"})),
        onClick: _ctx.opacityProperty
      }), null, 12 /* STYLE, PROPS */, ["onClick"])
    ], 4 /* STYLE */),
    createElementVNode("view", utsMapOf({
      style: normalizeStyle(utsMapOf({"border-radius":"5px","margin":"4px","padding":"4px","border-style":"solid","background-color":"#eee","border-color":"#eee"}))
    }), [
      createElementVNode("text", utsMapOf({
        style: normalizeStyle(utsMapOf({"margin-bottom":"4px"}))
      }), "修改border-color和margin-left(关键帧)", 4 /* STYLE */),
      createElementVNode("view", utsMapOf({
        id: "borderColorMarginLeftProperty",
        style: normalizeStyle(utsMapOf({"width":"100px","height":"100px","background-color":"brown","border-width":"5px","border-style":"solid"})),
        onClick: _ctx.borderColorMarginLeftProperty
      }), null, 12 /* STYLE, PROPS */, ["onClick"])
    ], 4 /* STYLE */)
  ], 4 /* STYLE */)
}
const GenPagesAPIAnimateAnimateStyles = [utsMapOf([["view-margin", padStyleMapOf(utsMapOf([["marginTop", 8], ["marginRight", 8], ["marginBottom", 8], ["marginLeft", 8]]))]])]
