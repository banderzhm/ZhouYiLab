type StatusBarArea = { __$originalPosition?: UTSSourceMapPosition<"StatusBarArea", "pages/API/get-window-info/window-area.uvue", 40, 8>;
    width : number,
    height : number
  }

  type CutoutArea = { __$originalPosition?: UTSSourceMapPosition<"CutoutArea", "pages/API/get-window-info/window-area.uvue", 45, 8>;
    top : number,
    left : number,
    width : number,
    height : number
  }

  type SafeArea = { __$originalPosition?: UTSSourceMapPosition<"SafeArea", "pages/API/get-window-info/window-area.uvue", 52, 8>;
    top : number,
    left : number,
    width : number,
    height : number
  }

  type BottomNavigationIndicatorArea = { __$originalPosition?: UTSSourceMapPosition<"BottomNavigationIndicatorArea", "pages/API/get-window-info/window-area.uvue", 59, 8>;
    width : number,
    height : number
  }

  
const __sfc__ = defineComponent({
  __name: 'window-area',
  setup(__props): any | null {
const __ins = getCurrentInstance()!;
const _ctx = __ins.proxy as InstanceType<typeof __sfc__>;
const _cache = __ins.renderCache;

  const statusBarArea = ref({ width: 0, height: 0 } as StatusBarArea);
  const cutoutArea = ref([] as CutoutArea[]);
  const safeArea = ref({ top: 0, left: 0, width: 0, height: 0 } as SafeArea);
  const bottomNavigationIndicatorArea = ref({ width: 0, height: 0 } as BottomNavigationIndicatorArea);
  const isStatusBarShow = ref(false);
  const isBottomNavigationIndicatorShow = ref(false);

  const getWindowInfo = () => {
    const info = uni.getWindowInfo();
    statusBarArea.value.width = info.windowWidth;
    statusBarArea.value.height = info.safeAreaInsets.top;
    cutoutArea.value.length = 0;
    (info.cutoutArea ?? []).forEach((item) => {
      cutoutArea.value.push({
        top: item.top,
        left: item.left,
        width: item.right - item.left,
        height: item.bottom - item.top
      } as CutoutArea);
    })
    safeArea.value.top = info.safeArea.top;
    safeArea.value.left = info.safeArea.left;
    safeArea.value.width = info.safeArea.width;
    safeArea.value.height = info.safeArea.height;
    bottomNavigationIndicatorArea.value.width = info.windowWidth;
    bottomNavigationIndicatorArea.value.height = info.safeAreaInsets.bottom;
  };

  const statusBarChange = (e : UniSwitchChangeEvent) => {
    const pages = getCurrentPages();
    pages[pages.length - 1].setPageStyle({
      'hideStatusBar': !e.detail.value,
    });
  };

  const bottomNavigationIndicatorChange = (e : UniSwitchChangeEvent) => {
    const pages = getCurrentPages();
    pages[pages.length - 1].setPageStyle({
      'hideBottomNavigationIndicator': !e.detail.value,
    });
  };

  onReady(() => {
    const pages = getCurrentPages();

    isStatusBarShow.value = !(pages[pages.length - 1].getPageStyle()['hideStatusBar'] as boolean);
    isBottomNavigationIndicatorShow.value = !(pages[pages.length - 1].getPageStyle()['hideBottomNavigationIndicator'] as boolean);

    getWindowInfo();
  });

  onResize((_ : OnResizeOptions) => {
    getWindowInfo();
  });

return (): any | null => {

  return createElementVNode("view", utsMapOf({
    style: normalizeStyle(utsMapOf({"flex":"1"}))
  }), [
    isTrue(unref(statusBarArea).width > 0 && unref(statusBarArea).height > 0)
      ? createElementVNode("view", utsMapOf({
          key: 0,
          id: "statusBar",
          class: "statusBar",
          style: normalizeStyle(utsMapOf({'width': unref(statusBarArea).width,'height': unref(statusBarArea).height}))
        }), null, 4 /* STYLE */)
      : createCommentVNode("v-if", true),
    unref(cutoutArea).length > 0
      ? createElementVNode(Fragment, utsMapOf({ key: 1 }), RenderHelpers.renderList(unref(cutoutArea), (item, _, __index, _cached): any => {
          return createElementVNode("view", utsMapOf({
            id: "cutoutArea",
            class: "cutoutArea",
            style: normalizeStyle(utsMapOf({'top': item.top,'left': item.left,'width': item.width,'height': item.height}))
          }), null, 4 /* STYLE */)
        }), 256 /* UNKEYED_FRAGMENT */)
      : createCommentVNode("v-if", true),
    createElementVNode("view", utsMapOf({
      id: "safeArea",
      class: "safeArea",
      style: normalizeStyle(utsMapOf({'top': unref(safeArea).top,'left': unref(safeArea).left,'width': unref(safeArea).width,'height': unref(safeArea).height}))
    }), null, 4 /* STYLE */),
    isTrue(unref(bottomNavigationIndicatorArea).width > 0 && unref(bottomNavigationIndicatorArea).height > 0)
      ? createElementVNode("view", utsMapOf({
          key: 2,
          id: "bottomNavigationIndicator",
          class: "bottomNavigationIndicator",
          style: normalizeStyle(utsMapOf({'width': unref(bottomNavigationIndicatorArea).width,'height': unref(bottomNavigationIndicatorArea).height}))
        }), null, 4 /* STYLE */)
      : createCommentVNode("v-if", true),
    createElementVNode("view", utsMapOf({
      style: normalizeStyle(utsMapOf({"flex":"1","justify-content":"center","align-items":"center"}))
    }), [
      isTrue(unref(statusBarArea).width > 0 && unref(statusBarArea).height > 0)
        ? createElementVNode("view", utsMapOf({
            key: 0,
            style: normalizeStyle(utsMapOf({"margin":"5px 0"}))
          }), [
            createElementVNode("text", utsMapOf({
              style: normalizeStyle(utsMapOf({"color":"red"}))
            }), "系统状态栏区域", 4 /* STYLE */)
          ], 4 /* STYLE */)
        : createCommentVNode("v-if", true),
      unref(cutoutArea).length > 0
        ? createElementVNode("view", utsMapOf({
            key: 1,
            style: normalizeStyle(utsMapOf({"margin":"5px 0"}))
          }), [
            createElementVNode("text", utsMapOf({
              style: normalizeStyle(utsMapOf({"color":"orange"}))
            }), "摄像头区域", 4 /* STYLE */)
          ], 4 /* STYLE */)
        : createCommentVNode("v-if", true),
      createElementVNode("view", utsMapOf({
        style: normalizeStyle(utsMapOf({"margin":"5px 0"}))
      }), [
        createElementVNode("text", utsMapOf({
          style: normalizeStyle(utsMapOf({"color":"green"}))
        }), "安全区域", 4 /* STYLE */)
      ], 4 /* STYLE */),
      isTrue(unref(bottomNavigationIndicatorArea).width > 0 && unref(bottomNavigationIndicatorArea).height > 0)
        ? createElementVNode("view", utsMapOf({
            key: 2,
            style: normalizeStyle(utsMapOf({"margin":"5px 0"}))
          }), [
            createElementVNode("text", utsMapOf({
              style: normalizeStyle(utsMapOf({"color":"blue"}))
            }), "系统导航栏区域", 4 /* STYLE */)
          ], 4 /* STYLE */)
        : createCommentVNode("v-if", true)
    ], 4 /* STYLE */)
  ], 4 /* STYLE */)
}
}

})
export default __sfc__
const GenPagesAPIGetWindowInfoWindowAreaStyles = [utsMapOf([["statusBar", padStyleMapOf(utsMapOf([["position", "absolute"], ["borderTopStyle", "solid"], ["borderRightStyle", "solid"], ["borderBottomStyle", "solid"], ["borderLeftStyle", "solid"], ["borderTopColor", "#FF0000"], ["borderRightColor", "#FF0000"], ["borderBottomColor", "#FF0000"], ["borderLeftColor", "#FF0000"], ["borderTopWidth", 4], ["borderRightWidth", 4], ["borderBottomWidth", 4], ["borderLeftWidth", 4]]))], ["cutoutArea", padStyleMapOf(utsMapOf([["position", "absolute"], ["borderTopStyle", "solid"], ["borderRightStyle", "solid"], ["borderBottomStyle", "solid"], ["borderLeftStyle", "solid"], ["borderTopColor", "#FFA500"], ["borderRightColor", "#FFA500"], ["borderBottomColor", "#FFA500"], ["borderLeftColor", "#FFA500"], ["borderTopWidth", 4], ["borderRightWidth", 4], ["borderBottomWidth", 4], ["borderLeftWidth", 4]]))], ["safeArea", padStyleMapOf(utsMapOf([["position", "absolute"], ["borderTopStyle", "solid"], ["borderRightStyle", "solid"], ["borderBottomStyle", "solid"], ["borderLeftStyle", "solid"], ["borderTopColor", "#008000"], ["borderRightColor", "#008000"], ["borderBottomColor", "#008000"], ["borderLeftColor", "#008000"], ["borderTopWidth", 4], ["borderRightWidth", 4], ["borderBottomWidth", 4], ["borderLeftWidth", 4]]))], ["bottomNavigationIndicator", padStyleMapOf(utsMapOf([["position", "absolute"], ["bottom", 0], ["borderTopStyle", "solid"], ["borderRightStyle", "solid"], ["borderBottomStyle", "solid"], ["borderLeftStyle", "solid"], ["borderTopColor", "#0000FF"], ["borderRightColor", "#0000FF"], ["borderBottomColor", "#0000FF"], ["borderLeftColor", "#0000FF"], ["borderTopWidth", 4], ["borderRightWidth", 4], ["borderBottomWidth", 4], ["borderLeftWidth", 4]]))]])]
