type TypeJestResult = { __$originalPosition?: UTSSourceMapPosition<"TypeJestResult", "pages/API/uni-push/uni-push.uvue", 51, 8>;
    clientId : string,
    sendPushMessageRes : number,
    onPushMessageType:string,
    onPushMessageCallbackInfo:string
  }
  type TypeIsRegister = { __$originalPosition?: UTSSourceMapPosition<"TypeIsRegister", "pages/API/uni-push/uni-push.uvue", 57, 8>;
    state:boolean
  }
  
const __sfc__ = defineComponent({
  __name: 'uni-push',
  setup(__props, { expose: __expose }: SetupContext): any | null {
const __ins = getCurrentInstance()!;
const _ctx = __ins.proxy as InstanceType<typeof __sfc__>;
const _cache = __ins.renderCache;

  // 自动化测试
  const jestResult = reactive({
    clientId:"",
    sendPushMessageRes:-1,
    onPushMessageType:"",
    onPushMessageCallbackInfo:""
  } as TypeJestResult)
  // 自动化测试
  const autoTest = ref(false);
  const updateAutoTest = (value : boolean) => {
    autoTest.value = value
  }

  const channelInfo = ref("")
  const onPushMessageCallback = (res : OnPushMessageCallbackResult) => {
    // 自动化测试
    jestResult.onPushMessageType = res.type
    jestResult.onPushMessageCallbackInfo = JSON.stringify(res.data)
    if (!autoTest.value) {
      uni.showModal({
        title: "onPushMessage回调信息",
        content: `type：${res.type} \n data：${JSON.stringify(res.data)}`
      })
    }
  }

  // 为兼容Android测试例中能获取到，此处用reactive定义
  const isRegister = reactive({
    state:false
  } as TypeIsRegister);

  const handleOnPushMessage = () => {
    if (isRegister.state) {
      uni.showToast({
        icon: "error",
        title: "无需重复注册"
      })
      return
    }
    uni.onPushMessage(onPushMessageCallback)
    isRegister.state = true
    uni.showToast({
      title: "成功注册"
    })
  }

  const handleOffPushMessage = () => {
    if (!isRegister.state) {
      uni.showToast({
        icon: "error",
        title: "未注册, 无需注销"
      })
      return
    }
    uni.offPushMessage(onPushMessageCallback)
    isRegister.state = false
    uni.showToast({
      title: "成功注销"
    })
  }

  const handleCreateChannel = (showToast : boolean) => {

    const manager = uni.getPushChannelManager()
    manager.setPushChannel({
      channelId: "msg-pass",
      channelDesc: "留言审核通过",
      soundName: "#填写配置的声音文件名#",
      enableLights: true,
      enableVibration: true,
      importance: 4,
      lockscreenVisibility: 1
    } as SetPushChannelOptions)
    if (showToast) {
      uni.showToast({
        title: "设置渠道成功"
      })
    }

  }
  const handleGetAllChannels = () => {

    const manager = uni.getPushChannelManager()
    console.log("channels : " + manager.getAllChannels(), " at pages/API/uni-push/uni-push.uvue:142");
    channelInfo.value = `渠道信息为: \n ${manager.getAllChannels()}`

  }
  const handleCreateLocalNotification = () => {
    if (uni.getAppAuthorizeSetting().notificationAuthorized == "authorized") {
      handleCreateChannel(false)
      const date = new Date();
      const hour = date.getHours()
      const minute = date.getMinutes()
      const second = date.getSeconds()
      const formateTime = (target : number) : string => {
        return target < 10 ? `0${target}` : `${target}`
      }
      uni.createPushMessage({
        title: "主标题(title)",
        content: `内容(content)，创建时间: ${formateTime(hour)}:${formateTime(minute)}:${formateTime(second)}`,
        cover: false,
        channelId: "msg-pass",
        when: Date.now() + 10000,
        icon: "/static/uni.png",
        sound: "system",
        delay: 1,
        payload: {
          pkey: "pvalue1"
        },




        category: "IM",

        success(res) {
          console.log("res: " + res, " at pages/API/uni-push/uni-push.uvue:175");
          uni.hideToast()
          uni.showToast({
            title: "创建本地通知消息成功"
          })
        },
        fail(e) {
          console.log("fail :" + e, " at pages/API/uni-push/uni-push.uvue:182");
          uni.hideToast()
          uni.showToast({
            title: "创建本地通知消息失败",
            icon: "error"
          })
        }
      })
    } else {
      uni.showToast({
        title: "请在设置中开启通知权限",
        icon: "error"
      })
    }
  }

  async function getPushClientId(): Promise<string>{
    let pushClientId = '';
    let res:void = await new Promise<void>(resolve => {
      uni.getPushClientId({
        success: (res: GetPushClientIdSuccess) => {
          console.log(res.cid, " at pages/API/uni-push/uni-push.uvue:203")
          pushClientId = res.cid
          resolve()
        },
        fail: (err: GetPushClientIdFail) => {
          resolve()
          console.error(err, " at pages/API/uni-push/uni-push.uvue:209");
          if (err.message.includes('uniPush is not enabled')) {
            uni.showModal({
              title: '获取cid失败',
              content: '当前项目未启用uni-push，检查manifest.json中的uni-push配置',
              showCancel: false
            });
          } else if (err.message.includes('getPushClientId:fail register fail: {\"errorCode\":1,\"errorMsg\":\"\"}')) {
            uni.showModal({
              title: '获取cid失败',
              content: '当前项目未开通uni-push，开通文档：https://uniapp.dcloud.net.cn/unipush-v2.html#%E7%AC%AC%E4%B8%80%E6%AD%A5-%E5%BC%80%E9%80%9A',
              showCancel: false
            });
          } else {
            uni.showToast({
              title: `获取cid失败`,
              icon: "error"
            })
          }
        }
      })
    })
    return pushClientId
  }

  const handleGetClientId = async():Promise<void> =>{
    uni.showLoading({
      title: "正在获取cid",
    })
    const cid = await getPushClientId()
    if (cid != '') {
      // 自动化测试
      jestResult.clientId = cid
      if (!autoTest.value) {
        uni.showModal({
          title: "获取cid",
          content: "获取cid成功" + cid,
          showCancel: false
        })
      }
    }
    uni.hideLoading()
  }
  const handleSendPushMessage = async():Promise<void>=> {
    const pushClientId = await getPushClientId()
    if (pushClientId == ''){
      return
    }
    const uniPushCo = uniCloud.importObject("uni-push-co")
    try {
      const res = await uniPushCo.sendPushMessage(pushClientId)
      // 自动化测试
      jestResult.sendPushMessageRes = res.errCode as number
      if (!autoTest.value) {
        uni.showToast({
          title: "发送通知消息成功"
        })
      }
    } catch (err:any) {
      const error = err as UniCloudError
      console.error(error, " at pages/API/uni-push/uni-push.uvue:269")
      if (!autoTest.value) {
        uni.showToast({
          title: "发送通知消息失败",
          icon: "error"
        })
      }
    }
  }
  const handleSetBadge = () => {
    if (uni.getDeviceInfo().deviceBrand?.toLowerCase() == "xiaomi") {
      if (uni.getAppAuthorizeSetting().notificationAuthorized == "authorized") {
        uni.setAppBadgeNumber(5, {
          title: "AppName",
          content: "您有5条未读消息"
        } as BadgeOptions)
        uni.showToast({
          title: "设置应用角标数为5"
        })
      } else {
        uni.showToast({
          title: "请在设置中开启通知权限",
          icon: "error"
        })
      }

    } else {
      uni.setAppBadgeNumber(5)
      uni.showToast({
        title: "设置应用角标数为5"
      })
    }
  }
  const handleCleanBadge = () => {
    if (uni.getDeviceInfo().deviceBrand?.toLowerCase() == "xiaomi") {
      if (uni.getAppAuthorizeSetting().notificationAuthorized == "authorized") {
        uni.setAppBadgeNumber(0, {} as BadgeOptions)
        uni.showToast({
          title: "清空应用角标数"
        })
      } else {
        uni.showToast({
          title: "请在设置中开启通知权限",
          icon: "error"
        })
      }
    } else {
      uni.setAppBadgeNumber(0)
      uni.showToast({
        title: "清空应用角标数"
      })
    }
  }

  // 自动化测试
  __expose({
    jestResult,
    autoTest,
    updateAutoTest,
    isRegister,
    handleSendPushMessage,
    handleGetClientId,
    handleOnPushMessage,
    handleOffPushMessage
  })

return (): any | null => {

  return createElementVNode("scroll-view", utsMapOf({
    style: normalizeStyle(utsMapOf({"flex":"1"}))
  }), [
    createElementVNode("view", null, [
      createElementVNode("button", utsMapOf({
        class: "normal-button",
        type: "default",
        onClick: () => {handleCreateChannel(true)}
      }), " 创建通知渠道 | setPushChannel ", 8 /* PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({
        class: "normal-button",
        type: "default",
        onClick: handleGetAllChannels
      }), " 获取所有通知渠道信息 | getAllChannels "),
      createElementVNode("textarea", utsMapOf({
        style: normalizeStyle(utsMapOf({"width":"100%"})),
        disabled: true,
        value: unref(channelInfo)
      }), null, 12 /* STYLE, PROPS */, ["value"]),
      createElementVNode("button", utsMapOf({
        class: "normal-button",
        type: "default",
        onClick: handleCreateLocalNotification
      }), " 创建本地通知消息 | createPushMessage "),
      createElementVNode("text", utsMapOf({ class: "instructions" }), " 不同手机厂商的角标显示规则不同，有部分设备的rom版本不支持显示角标，另有部分rom需要在应用的通知管理里开启`桌面角标`配置，才可以设置角标成功。\n 部分rom需要在设置中同时开启`通知开关`和`桌面角标`配置，才允许设置角标，例如鸿蒙4.2。 \n 另外针对高版本小米设备，会借助创建通知栏消息来设置角标数，所以设置时需要注意是否有权限创建通知栏消息。 "),
      createElementVNode("button", utsMapOf({
        class: "normal-button",
        type: "default",
        onClick: handleSetBadge
      }), " 设置角标为5 | setAppBadgeNumber(5) "),
      createElementVNode("button", utsMapOf({
        class: "normal-button",
        type: "default",
        onClick: handleCleanBadge
      }), " 清空角标 | setAppBadgeNumber(0) "),
      createElementVNode("button", utsMapOf({
        class: "normal-button",
        type: "default",
        onClick: handleSendPushMessage
      }), " 发送通知消息 | sendPushMessage "),
      createElementVNode("button", utsMapOf({
        class: "normal-button uni-common-mb",
        type: "default",
        onClick: handleGetClientId
      }), " 获取cid | getPushClientId "),
      createElementVNode("button", utsMapOf({
        class: "normal-button",
        type: "default",
        onClick: handleOnPushMessage
      }), " 注册回调 | onPushMessage "),
      createElementVNode("button", utsMapOf({
        class: "normal-button",
        type: "default",
        onClick: handleOffPushMessage
      }), " 注销回调 | offPushMessage ")
    ])
  ], 4 /* STYLE */)
}
}

})
export default __sfc__
const GenPagesAPIUniPushUniPushStyles = [utsMapOf([["normal-button", padStyleMapOf(utsMapOf([["width", "100%"]]))], ["instructions", padStyleMapOf(utsMapOf([["marginTop", 10], ["marginLeft", 10], ["marginRight", 10], ["backgroundColor", "#eeeeee"]]))]])]
