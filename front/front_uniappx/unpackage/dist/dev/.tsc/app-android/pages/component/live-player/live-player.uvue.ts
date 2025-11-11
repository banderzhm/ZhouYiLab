import _easycom_boolean_data from '@/components/boolean-data/boolean-data.vue'
import _easycom_enum_data from '@/components/enum-data/enum-data.vue'
import { ItemType } from '@/components/enum-data/enum-data-types';

  
const __sfc__ = defineComponent({
  __name: 'live-player',
  setup(__props): any | null {
const __ins = getCurrentInstance()!;
const _ctx = __ins.proxy as InstanceType<typeof __sfc__>;
const _cache = __ins.renderCache;

  const context = ref(null as LivePlayerContext | null);
  const src = ref("");
  const autoplay = ref(false);
  const muted = ref(false);
  const objectFit = ref("contain");
  const backgroundMute = ref(false);
  const soundMode = ref("speaker");
  const orientation = ref("vertical");
  const initState = ref(true);
  const playState = ref(false);
  const stopState = ref(false);

  onReady(() => {
    context.value = uni.createLivePlayerContext("live-player", getCurrentInstance()!.proxy);
  });

  const statechange = (e : UniLivePlayerStatechangeEvent) => {
    console.log("statechange", e, " at pages/component/live-player/live-player.uvue:66");
    switch (e.detail.code) {
      case 10004:
        initState.value = false;
        playState.value = true;
        stopState.value = false;
        break;
      case 10009:
        stopState.value = true;
      case 10006:
      case 10007:
      case 10010:
      case 10011:
        playState.value = false;
        break;
    }
  };
  const fullscreenchange = (e : UniLivePlayerFullscreenchangeEvent) => {
    console.log("fullscreenchange", e, " at pages/component/live-player/live-player.uvue:84");
  };
  const error = (e : UniLivePlayerErrorEvent) => {
    console.log("error", e, " at pages/component/live-player/live-player.uvue:87");
  };
  const isSrcValid = () : boolean => {
    const length = src.value.length;
    if (length <= 0) {
      uni.showToast({
        title: "请输入播放地址",
        icon: "none"
      });
    }
    return length > 0;
  };
  const play = () => {
    if (!isSrcValid()) return;
    context.value?.play({
      success: (res) => {
        console.log("play", JSON.stringify(res), " at pages/component/live-player/live-player.uvue:103");
      },
      fail: (err) => {
        console.log("play", JSON.stringify(err), " at pages/component/live-player/live-player.uvue:106");
      },
      complete: (res) => {
        console.log("play", JSON.stringify(res), " at pages/component/live-player/live-player.uvue:109");
      }
    });
  };
  const pause = () => {
    if (!isSrcValid()) return;
    context.value?.pause({
      success: (res) => {
        console.log("pause", JSON.stringify(res), " at pages/component/live-player/live-player.uvue:117");
      },
      fail: (err) => {
        console.log("pause", JSON.stringify(err), " at pages/component/live-player/live-player.uvue:120");
      },
      complete: (res) => {
        console.log("pause", JSON.stringify(res), " at pages/component/live-player/live-player.uvue:123");
      }
    });
  };
  const resume = () => {
    if (!isSrcValid()) return;
    context.value?.resume({
      success: (res) => {
        console.log("resume", JSON.stringify(res), " at pages/component/live-player/live-player.uvue:131");
      },
      fail: (err) => {
        console.log("resume", JSON.stringify(err), " at pages/component/live-player/live-player.uvue:134");
      },
      complete: (res) => {
        console.log("resume", JSON.stringify(res), " at pages/component/live-player/live-player.uvue:137");
      }
    });
  };
  const stop = () => {
    if (!isSrcValid()) return;
    context.value?.stop({
      success: (res) => {
        console.log("stop", JSON.stringify(res), " at pages/component/live-player/live-player.uvue:145");
      },
      fail: (err) => {
        console.log("stop", JSON.stringify(err), " at pages/component/live-player/live-player.uvue:148");
      },
      complete: (res) => {
        console.log("stop", JSON.stringify(res), " at pages/component/live-player/live-player.uvue:151");
      }
    });
  };
  const mute = () => {
    if (!isSrcValid()) return;
    context.value?.mute({
      success: (res) => {
        console.log("mute", JSON.stringify(res), " at pages/component/live-player/live-player.uvue:159");
      },
      fail: (err) => {
        console.log("mute", JSON.stringify(err), " at pages/component/live-player/live-player.uvue:162");
      },
      complete: (res) => {
        console.log("mute", JSON.stringify(res), " at pages/component/live-player/live-player.uvue:165");
      }
    });
  };
  const requestFullScreen = () => {
    if (!isSrcValid()) return;
    context.value?.requestFullScreen({
      success: (res) => {
        console.log("requestFullScreen", JSON.stringify(res), " at pages/component/live-player/live-player.uvue:173");
      },
      fail: (err) => {
        console.log("requestFullScreen", JSON.stringify(err), " at pages/component/live-player/live-player.uvue:176");
      },
      complete: (res) => {
        console.log("requestFullScreen", JSON.stringify(res), " at pages/component/live-player/live-player.uvue:179");
      }
    });
  };
  const exitFullScreen = () => {
    if (!isSrcValid()) return;
    context.value?.exitFullScreen({
      success: (res) => {
        console.log("exitFullScreen", JSON.stringify(res), " at pages/component/live-player/live-player.uvue:187");
      },
      fail: (err) => {
        console.log("exitFullScreen", JSON.stringify(err), " at pages/component/live-player/live-player.uvue:190");
      },
      complete: (res) => {
        console.log("exitFullScreen", JSON.stringify(res), " at pages/component/live-player/live-player.uvue:193");
      }
    });
  };

  const objectFitItemTypes = [{ "value": 0, "name": "contain" }, { "value": 1, "name": "fillCrop" }] as ItemType[];
  const objectFitItems = ["contain", "fillCrop"];
  const soundModeItemTypes = [{ "value": 0, "name": "speaker" }, { "value": 1, "name": "ear" }] as ItemType[];
  const soundModeItems = ["speaker", "ear"];
  const orientationItemTypes = [{ "value": 0, "name": "vertical" }, { "value": 1, "name": "horizontal" }] as ItemType[];
  const orientationItems = ["vertical", "horizontal"];
  const onSrcComfirm = (event : UniInputConfirmEvent) => {
    let value = event.detail.value;
    if (value == '') return;
    src.value = value;
    console.log("src ->", value, " at pages/component/live-player/live-player.uvue:208");
  };
  const onAutoplayChange = (value : boolean) => {
    autoplay.value = value;
    console.log("autoplay ->", autoplay.value, " at pages/component/live-player/live-player.uvue:212");
  };
  const onMutedChange = (value : boolean) => {
    muted.value = value;
    console.log("muted ->", muted.value, " at pages/component/live-player/live-player.uvue:216");
  };
  const onBackgroundMuteChange = (value : boolean) => {
    backgroundMute.value = value;
    console.log("background-mute ->", backgroundMute.value, " at pages/component/live-player/live-player.uvue:220");
  };
  const onObjectFitChange = (value : number) => {
    objectFit.value = objectFitItems[value];
    console.log("object-fit ->", objectFit.value, " at pages/component/live-player/live-player.uvue:224");
  };
  const onSoundModeChange = (value : number) => {
    soundMode.value = soundModeItems[value];
    console.log("sound-mode ->", soundMode.value, " at pages/component/live-player/live-player.uvue:228");
  };
  const onOrientationChange = (value : number) => {
    orientation.value = orientationItems[value];
    console.log("orientation ->", orientation.value, " at pages/component/live-player/live-player.uvue:232");
  };

return (): any | null => {

const _component_boolean_data = resolveEasyComponent("boolean-data",_easycom_boolean_data)
const _component_enum_data = resolveEasyComponent("enum-data",_easycom_enum_data)

  return createElementVNode("view", utsMapOf({ class: "uni-flex-item" }), [
    createElementVNode("live-player", utsMapOf({
      id: "live-player",
      class: "live-player",
      src: unref(src),
      autoplay: unref(autoplay),
      muted: unref(muted),
      "object-fit": unref(objectFit),
      "background-mute": unref(backgroundMute),
      "sound-mode": unref(soundMode),
      orientation: unref(orientation),
      onStatechange: statechange,
      onFullscreenchange: fullscreenchange,
      onError: error
    }), null, 40 /* PROPS, NEED_HYDRATION */, ["src", "autoplay", "muted", "object-fit", "background-mute", "sound-mode", "orientation"]),
    createElementVNode("scroll-view", utsMapOf({ class: "uni-padding-wrap uni-common-mt uni-flex-item" }), [
      createElementVNode("view", utsMapOf({ class: "uni-title" }), [
        createElementVNode("text", utsMapOf({ class: "uni-title-text" }), "API示例")
      ]),
      createElementVNode("view", utsMapOf({ class: "uni-btn-v" }), [
        createElementVNode("button", utsMapOf({
          type: "primary",
          onClick: play,
          disabled: unref(playState)
        }), "播放", 8 /* PROPS */, ["disabled"])
      ]),
      createElementVNode("view", utsMapOf({ class: "uni-btn-v" }), [
        createElementVNode("button", utsMapOf({
          type: "primary",
          onClick: pause,
          disabled: !unref(playState)
        }), "暂停", 8 /* PROPS */, ["disabled"])
      ]),
      createElementVNode("view", utsMapOf({ class: "uni-btn-v" }), [
        createElementVNode("button", utsMapOf({
          type: "primary",
          onClick: resume,
          disabled: unref(initState) || unref(playState) || unref(stopState)
        }), "恢复", 8 /* PROPS */, ["disabled"])
      ]),
      createElementVNode("view", utsMapOf({ class: "uni-btn-v" }), [
        createElementVNode("button", utsMapOf({
          type: "primary",
          onClick: stop,
          disabled: !unref(playState)
        }), "停止", 8 /* PROPS */, ["disabled"])
      ]),
      createElementVNode("view", utsMapOf({ class: "uni-btn-v" }), [
        createElementVNode("button", utsMapOf({
          type: "primary",
          onClick: mute,
          disabled: !unref(playState)
        }), "静音", 8 /* PROPS */, ["disabled"])
      ]),
      createElementVNode("view", utsMapOf({ class: "uni-btn-v" }), [
        createElementVNode("button", utsMapOf({
          type: "primary",
          onClick: requestFullScreen,
          disabled: !unref(playState)
        }), "进入全屏", 8 /* PROPS */, ["disabled"])
      ]),
      createElementVNode("view", utsMapOf({ class: "uni-btn-v" }), [
        createElementVNode("button", utsMapOf({
          type: "primary",
          onClick: exitFullScreen,
          disabled: !unref(playState)
        }), "退出全屏", 8 /* PROPS */, ["disabled"])
      ]),
      createElementVNode("view", utsMapOf({ class: "uni-title" }), [
        createElementVNode("text", utsMapOf({ class: "uni-title-text" }), "属性示例")
      ]),
      createElementVNode("input", utsMapOf({
        class: "input margin-10",
        type: "string",
        placeholder: "设置播放地址",
        onConfirm: onSrcComfirm
      }), null, 32 /* NEED_HYDRATION */),
      createVNode(_component_boolean_data, utsMapOf({
        title: "设置是否自动播放",
        defaultValue: unref(autoplay),
        onChange: onAutoplayChange
      }), null, 8 /* PROPS */, ["defaultValue"]),
      createVNode(_component_boolean_data, utsMapOf({
        title: "设置是否静音",
        defaultValue: unref(muted),
        onChange: onMutedChange
      }), null, 8 /* PROPS */, ["defaultValue"]),
      createVNode(_component_boolean_data, utsMapOf({
        title: "设置进入后台时是否静音",
        defaultValue: unref(backgroundMute),
        onChange: onBackgroundMuteChange
      }), null, 8 /* PROPS */, ["defaultValue"]),
      createVNode(_component_enum_data, utsMapOf({
        title: "设置填充模式",
        items: objectFitItemTypes,
        onChange: onObjectFitChange
      })),
      createVNode(_component_enum_data, utsMapOf({
        title: "设置声音输出方式",
        items: soundModeItemTypes,
        onChange: onSoundModeChange
      })),
      createVNode(_component_enum_data, utsMapOf({
        title: "设置画面方向",
        items: orientationItemTypes,
        onChange: onOrientationChange
      }))
    ])
  ])
}
}

})
export default __sfc__
const GenPagesComponentLivePlayerLivePlayerStyles = [utsMapOf([["live-player", padStyleMapOf(utsMapOf([["width", "100%"], ["height", "40%"]]))], ["input", padStyleMapOf(utsMapOf([["height", 40], ["backgroundImage", "none"], ["backgroundColor", "#FFFFFF"], ["paddingTop", 8], ["paddingRight", 13], ["paddingBottom", 8], ["paddingLeft", 13]]))], ["margin-10", padStyleMapOf(utsMapOf([["marginTop", 10], ["marginRight", 10], ["marginBottom", 10], ["marginLeft", 10]]))]])]
