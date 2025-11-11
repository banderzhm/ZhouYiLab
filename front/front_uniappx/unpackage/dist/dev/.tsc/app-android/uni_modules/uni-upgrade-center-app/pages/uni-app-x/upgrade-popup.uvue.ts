import { openSchema as utsOpenSchema } from '@/uni_modules/uts-openSchema'
  import { UniUpgradeCenterResult, StoreListItem } from '../../utils/call-check-version'
  import { platform_iOS, platform_Android, platform_Harmony } from '../../utils/utils'

  import { createNotificationProgress, cancelNotificationProgress, finishNotificationProgress } from '@/uni_modules/uts-progressNotification'
  import { type CreateNotificationProgressOptions, type FinishNotificationProgressOptions } from '@/uni_modules/uts-progressNotification/utssdk/interface.uts'


  
const __sfc__ = defineComponent({
  __name: 'upgrade-popup',
  setup(__props): any | null {
const __ins = getCurrentInstance()!;
const _ctx = __ins.proxy as InstanceType<typeof __sfc__>;
const _cache = __ins.renderCache;

  const requiredKey = ['version', 'url', 'type']
  let downloadTask : DownloadTask | null = null;
  let openSchemePromise : Promise<boolean> | null = null;

  const openSchema = (url : string) : Promise<boolean> => new Promise<boolean>((resolve, reject) => {
    try {
      utsOpenSchema(url)
      resolve(true)
    } catch (e) {
      reject(false)
    }
  })

  // 从之前下载安装
  const installForBeforeFilePath = ref<string>('')

  // 安装
  const installed = ref<boolean>(false)
  const installing = ref<boolean>(false)

  // 下载
  const downloadSuccess = ref<boolean>(false)
  const downloading = ref<boolean>(false)

  const downLoadPercent = ref<number>(0)
  const downloadedSize = ref<number>(0)
  const packageFileSize = ref<number>(0)

  // 要安装的本地包地址
  const tempFilePath = ref<string>('')

  // 默认安装包信息
  const title = ref<string>('更新日志')
  const contents = ref<string>('')
  const version = ref<string>('')
  const is_mandatory = ref<boolean>(false)
  const url = ref<string>("")
  const platform = ref<string[]>([])
  const store_list = ref<StoreListItem[] | null>(null)

  // 可自定义属性
  const subTitle = ref<string>('发现新版本')
  const downLoadBtnTextiOS = ref<string>('立即跳转更新')
  const downLoadBtnText = ref<string>('立即下载更新')
  const downLoadingText = ref<string>('安装包下载中，请稍后')

  const isiOS = computed(() : boolean => platform.value.includes(platform_iOS))
  const isHarmony = computed(() : boolean => platform.value.includes(platform_Harmony))
  const isAndroid = computed(() : boolean => platform.value.includes(platform_Android))
  const needNotificationProgress = computed(() : boolean => isAndroid.value && !is_mandatory.value)

  function getCurrentDialogPage() : UniPage | null {
    const pages = getCurrentPages()
    if (pages.length > 0) {
      const dialogPages = pages[pages.length - 1].getDialogPages()
      if (dialogPages.length > 0) {
        return dialogPages[dialogPages.length - 1]
      }
    }
    return null
  }

  function closePopup() {
    downloadSuccess.value = false
    downloading.value = false
    downLoadPercent.value = 0
    downloadedSize.value = 0
    packageFileSize.value = 0
    tempFilePath.value = ''

    installing.value = false
    installed.value = false

    uni.closeDialogPage({
      dialogPage: getCurrentDialogPage(),
      fail(e) {
        console.log('e: ', e, " at uni_modules/uni-upgrade-center-app/pages/uni-app-x/upgrade-popup.uvue:153");
      }
    })
  }

  function askAbortDownload() {
    uni.showModal({
      title: '是否取消下载？',
      cancelText: '否',
      confirmText: '是',
      success: res => {
        if (res.confirm) {
          if (downloadTask !== null) downloadTask!.abort()
          if (needNotificationProgress.value) {

            cancelNotificationProgress();

          }
          closePopup()
        }
      }
    });
  }

  function closeUpdate() {
    if (downloading.value && !needNotificationProgress.value) {
      askAbortDownload()
      return;
    }
    closePopup()
  }

  function jumpToAppStore() {
    openSchema(url.value)
  }

  function show(localPackageInfo : UniUpgradeCenterResult | null) {
    if (localPackageInfo === null) return;
    for (let key in localPackageInfo) {
      if (requiredKey.indexOf(key) != -1 && localPackageInfo[key] === null) {
        console.error(`参数 ${key} 必填，请检查后重试`, " at uni_modules/uni-upgrade-center-app/pages/uni-app-x/upgrade-popup.uvue:193")
        closePopup()
        return;
      }
    }
    title.value = localPackageInfo.title
    url.value = localPackageInfo.url
    contents.value = localPackageInfo.contents
    is_mandatory.value = localPackageInfo.is_mandatory
    platform.value = localPackageInfo.platform
    version.value = localPackageInfo.version
    store_list.value = localPackageInfo.store_list
  }

  function checkStoreScheme() : Promise<boolean> | null {
    if (store_list.value !== null) {
      const storeList : StoreListItem[] = store_list.value!.filter((item : StoreListItem) : boolean => item.enable)
      if (storeList.length > 0) {
        if (openSchemePromise === null) {
          openSchemePromise = Promise.reject() as Promise<boolean>
        }
        storeList
          .sort((cur : StoreListItem, next : StoreListItem) : number => next.priority - cur.priority)
          .map((item : StoreListItem) : string => item.scheme)
          .reduce((promise : Promise<boolean>, cur : string) : Promise<boolean> => {
            openSchemePromise = promise.catch<boolean>(() : Promise<boolean> => openSchema(cur))
            return openSchemePromise!
          }, openSchemePromise!)
        return openSchemePromise!
      }
    }
    return null
  }

  function installPackage() {
    installing.value = true;

    uni.installApk({
      filePath: tempFilePath.value,
      success: _ => {
        installing.value = false;
        installed.value = true;
      },
      fail: err => {
        console.error('installApk fail', err, " at uni_modules/uni-upgrade-center-app/pages/uni-app-x/upgrade-popup.uvue:237");
        // 安装失败需要重新下载安装包
        installing.value = false;
        installed.value = false;
        uni.showModal({
          title: '更新失败，请重新下载',
          content: `uni.installApk 错误码 ${err.errCode}`,
          showCancel: false
        });
      }
    });
    // 安装跳出覆盖安装，此处直接返回上一页
    if (!is_mandatory.value) {
      uni.navigateBack()
    }

  }

  function downloadFail() {
    const errMsg = '下载失败，请点击重试'
    downloadSuccess.value = false;
    downloading.value = false;
    downLoadPercent.value = 0;
    downloadedSize.value = 0;
    packageFileSize.value = 0;
    downLoadBtnText.value = errMsg
    downloadTask = null;
    if (needNotificationProgress.value) {

      finishNotificationProgress({
        title: '升级包下载失败',
        content: '请重新检查更新',
        onClick() { }
      } as FinishNotificationProgressOptions);

    }
  }

  function downLoadComplete() {
    downloadSuccess.value = true;
    downloading.value = false;
    downLoadPercent.value = 0
    downloadedSize.value = 0
    packageFileSize.value = 0
    downloadTask = null;
    if (needNotificationProgress.value) {

      finishNotificationProgress({
        title: "安装升级包",
        content: "下载完成",
        onClick() { }
      } as FinishNotificationProgressOptions)
      installPackage();

      return
    }
    // 强制更新，直接安装
    if (is_mandatory.value) {
      installPackage();
    }
  }

  function downloadPackage() {
    //下载包
    downloadTask = uni.downloadFile({
      url: url.value,
      success: res => {
        if (res.statusCode == 200) {
          tempFilePath.value = res.tempFilePath
          downLoadComplete()
        } else {
          console.log('downloadFile err: ', res, " at uni_modules/uni-upgrade-center-app/pages/uni-app-x/upgrade-popup.uvue:308");
          downloadFail()
        }
      },
      fail: err => {
        console.log('downloadFile err: ', err, " at uni_modules/uni-upgrade-center-app/pages/uni-app-x/upgrade-popup.uvue:313");
        downloadFail()
      }
    });
    if (downloadTask !== null) {
      downloading.value = true;
      if (needNotificationProgress.value) {
        closePopup()
      }
      downloadTask!.onProgressUpdate(res => {
        downLoadPercent.value = parseFloat(res.progress.toFixed(0));
        downloadedSize.value = parseFloat((res.totalBytesWritten / Math.pow(1024, 2)).toFixed(2));
        packageFileSize.value = parseFloat((res.totalBytesExpectedToWrite / Math.pow(1024, 2)).toFixed(2));
        if (needNotificationProgress.value) {

          createNotificationProgress({
            title: "升级中心正在下载安装包……",
            content: `${downLoadPercent.value}%`,
            progress: downLoadPercent.value,
            onClick: () => {
              if (!downloadSuccess.value) {
                askAbortDownload()
              }
            }
          } as CreateNotificationProgressOptions)

        }
      });
    }
  }

  function updateApp() {
    const checkStoreSchemeResult = checkStoreScheme()
    if (checkStoreSchemeResult !== null) {
      checkStoreSchemeResult
        .then(_ => { })
        .catch(() => { downloadPackage() })
        .finally(() => {
          openSchemePromise = null
        })
    } else { downloadPackage() }
  }

  onUnload(() => {
    if (needNotificationProgress.value) {

      cancelNotificationProgress()

    }
  })

  onLoad((onLoadOptions : OnLoadOptions) => {
    const local_storage_key : string | null = onLoadOptions['local_storage_key']
    if (local_storage_key == null) {
      console.error('local_storage_key为空，请检查后重试', " at uni_modules/uni-upgrade-center-app/pages/uni-app-x/upgrade-popup.uvue:367")
      closePopup()
      return;
    };
    const localPackageInfo = uni.getStorageSync(local_storage_key);
    if (localPackageInfo == null) {
      console.error('安装包信息为空，请检查后重试', " at uni_modules/uni-upgrade-center-app/pages/uni-app-x/upgrade-popup.uvue:373")
      closePopup()
      return;
    };
    show(UTSAndroid.consoleDebugError(JSON.parse<UniUpgradeCenterResult>(JSON.stringify(localPackageInfo)), " at uni_modules/uni-upgrade-center-app/pages/uni-app-x/upgrade-popup.uvue:377") as UniUpgradeCenterResult)
  })

  onBackPress((options : OnBackPressOptions) : boolean | null => {
    if (is_mandatory.value) return true
    if (!needNotificationProgress.value) {
      if (downloadTask !== null) {
        downloadTask!.abort()
      }
    }
    return false
  })

return (): any | null => {

const _component_progress = resolveComponent("progress")

  return createElementVNode("view", utsMapOf({ class: "mask flex-center" }), [
    createElementVNode("view", utsMapOf({ class: "content" }), [
      createElementVNode("view", utsMapOf({ class: "content-top" }), [
        createElementVNode("text", utsMapOf({ class: "content-top-text" }), toDisplayString(unref(title)), 1 /* TEXT */),
        createElementVNode("image", utsMapOf({
          class: "content-top-image",
          mode: "widthFix",
          src: "/uni_modules/uni-upgrade-center-app/static/app/bg_top.png"
        }))
      ]),
      createElementVNode("view", utsMapOf({ class: "content-space" })),
      createElementVNode("view", utsMapOf({ class: "content-body" }), [
        createElementVNode("view", utsMapOf({ class: "content-body-title" }), [
          createElementVNode("text", utsMapOf({ class: "text title" }), toDisplayString(unref(subTitle)), 1 /* TEXT */),
          createElementVNode("text", utsMapOf({ class: "text version" }), "v" + toDisplayString(unref(version)), 1 /* TEXT */)
        ]),
        createElementVNode("view", utsMapOf({ class: "body" }), [
          createElementVNode("scroll-view", utsMapOf({
            class: "box-des-scroll",
            "scroll-y": "true"
          }), [
            createElementVNode("text", utsMapOf({ class: "text box-des" }), toDisplayString(unref(contents)), 1 /* TEXT */)
          ])
        ]),
        createElementVNode("view", utsMapOf({ class: "footer flex-center" }), [
          isTrue(unref(isiOS) || unref(isHarmony))
            ? createElementVNode("button", utsMapOf({
                key: 0,
                class: "content-button",
                style: normalizeStyle(utsMapOf({"border":"none","color":"#fff"})),
                type: "primary",
                plain: "",
                onClick: jumpToAppStore
              }), toDisplayString(unref(downLoadBtnTextiOS)), 5 /* TEXT, STYLE */)
            : createElementVNode(Fragment, utsMapOf({ key: 1 }), [
                isTrue(!unref(downloadSuccess))
                  ? createElementVNode(Fragment, utsMapOf({ key: 0 }), [
                      isTrue(unref(downloading))
                        ? createElementVNode("view", utsMapOf({
                            key: 0,
                            class: "progress-box flex-column"
                          }), [
                            createVNode(_component_progress, utsMapOf({
                              class: "progress",
                              percent: unref(downLoadPercent),
                              activeColor: "#3DA7FF",
                              "show-info": true,
                              "stroke-width": 10
                            }), null, 8 /* PROPS */, ["percent"]),
                            createElementVNode("view", utsMapOf({
                              style: normalizeStyle(utsMapOf({"width":"100%","display":"flex","justify-content":"space-around","flex-direction":"row"}))
                            }), [
                              createElementVNode("text", utsMapOf({
                                class: "text",
                                style: normalizeStyle(utsMapOf({"font-size":"14px"}))
                              }), toDisplayString(unref(downLoadingText)), 5 /* TEXT, STYLE */),
                              createElementVNode("text", utsMapOf({
                                class: "text",
                                style: normalizeStyle(utsMapOf({"font-size":"14px"}))
                              }), "(" + toDisplayString(unref(downloadedSize)) + "/" + toDisplayString(unref(packageFileSize)) + "M)", 5 /* TEXT, STYLE */)
                            ], 4 /* STYLE */)
                          ])
                        : createElementVNode("button", utsMapOf({
                            key: 1,
                            class: "content-button",
                            onClick: updateApp
                          }), toDisplayString(unref(downLoadBtnText)), 1 /* TEXT */)
                    ], 64 /* STABLE_FRAGMENT */)
                  : isTrue(unref(downloadSuccess) && !unref(installed))
                    ? createElementVNode("button", utsMapOf({
                        key: 1,
                        class: "content-button",
                        loading: unref(installing),
                        disabled: unref(installing),
                        onClick: installPackage
                      }), toDisplayString(unref(installing) ? '正在安装……' : '下载完成，立即安装'), 9 /* TEXT, PROPS */, ["loading", "disabled"])
                    : isTrue(unref(installed))
                      ? createElementVNode("button", utsMapOf({
                          key: 2,
                          class: "content-button",
                          onClick: installPackage
                        }), " 安装未完成，点击安装 ")
                      : createCommentVNode("v-if", true)
              ], 64 /* STABLE_FRAGMENT */)
        ])
      ]),
      createElementVNode("view", utsMapOf({ class: "content-bottom" }), [
        isTrue(!unref(is_mandatory))
          ? createElementVNode("image", utsMapOf({
              key: 0,
              class: "close-img",
              mode: "widthFix",
              src: "/uni_modules/uni-upgrade-center-app/static/app/app_update_close.png",
              onClick: closeUpdate
            }))
          : createCommentVNode("v-if", true)
      ])
    ])
  ])
}
}

})
export default __sfc__
const GenUniModulesUniUpgradeCenterAppPagesUniAppXUpgradePopupStyles = [utsMapOf([["flex-center", padStyleMapOf(utsMapOf([["justifyContent", "center"], ["alignItems", "center"]]))], ["mask", padStyleMapOf(utsMapOf([["position", "fixed"], ["left", 0], ["top", 0], ["right", 0], ["bottom", 0], ["backgroundColor", "rgba(0,0,0,0.65)"]]))], ["content", padStyleMapOf(utsMapOf([["position", "relative"], ["top", 0], ["width", "600rpx"], ["backgroundColor", "rgba(0,0,0,0)"]]))], ["text", padStyleMapOf(utsMapOf([["fontFamily", "Source Han Sans CN"]]))], ["content-top", padStyleMapOf(utsMapOf([["width", "100%"], ["borderBottomColor", "#ffffff"], ["borderBottomWidth", 15], ["borderBottomStyle", "solid"]]))], ["content-space", padStyleMapOf(utsMapOf([["width", "100%"], ["height", 120], ["backgroundColor", "#ffffff"], ["position", "absolute"], ["top", "30%"], ["zIndex", -1]]))], ["content-top-image", padStyleMapOf(utsMapOf([["width", "100%"], ["position", "relative"], ["bottom", "-10%"]]))], ["content-top-text", padStyleMapOf(utsMapOf([["fontSize", 22], ["fontWeight", "bold"], ["color", "#F8F8FA"], ["position", "absolute"], ["width", "65%"], ["top", "50%"], ["left", 25], ["zIndex", 1]]))], ["content-body", padStyleMapOf(utsMapOf([["boxSizing", "border-box"], ["paddingTop", 0], ["paddingRight", 25], ["paddingBottom", 0], ["paddingLeft", 25], ["width", "100%"], ["backgroundColor", "#ffffff"], ["borderBottomLeftRadius", 15], ["borderBottomRightRadius", 15]]))], ["content-body-title", padStyleMapOf(utsMapOf([["flexDirection", "row"], ["alignItems", "center"]]))], ["version", utsMapOf([[".content-body-title ", utsMapOf([["paddingLeft", 4], ["color", "#ffffff"], ["fontSize", 10], ["marginLeft", 5], ["paddingTop", 2], ["paddingRight", 4], ["paddingBottom", 2], ["borderTopLeftRadius", 10], ["borderTopRightRadius", 10], ["borderBottomRightRadius", 10], ["borderBottomLeftRadius", 10], ["backgroundImage", "none"], ["backgroundColor", "#50aefd"]])]])], ["title", padStyleMapOf(utsMapOf([["fontSize", 16], ["fontWeight", "bold"], ["color", "#3DA7FF"], ["lineHeight", "38px"]]))], ["footer", padStyleMapOf(utsMapOf([["height", 75], ["display", "flex"], ["alignItems", "center"], ["justifyContent", "space-around"]]))], ["box-des-scroll", padStyleMapOf(utsMapOf([["boxSizing", "border-box"], ["paddingTop", 0], ["paddingRight", 15], ["paddingBottom", 0], ["paddingLeft", 15], ["height", 100]]))], ["box-des", padStyleMapOf(utsMapOf([["fontSize", 13], ["color", "#000000"], ["lineHeight", "25px"]]))], ["progress-box", padStyleMapOf(utsMapOf([["width", "100%"]]))], ["progress", padStyleMapOf(utsMapOf([["width", "90%"], ["height", 20]]))], ["content-bottom", padStyleMapOf(utsMapOf([["height", 75]]))], ["close-img", padStyleMapOf(utsMapOf([["width", 35], ["height", 35], ["zIndex", 1000], ["position", "relative"], ["bottom", "-30%"], ["left", "50%"], ["marginLeft", -17]]))], ["content-button", padStyleMapOf(utsMapOf([["width", "100%"], ["height", 40], ["lineHeight", "40px"], ["fontSize", 15], ["fontWeight", "400"], ["borderTopLeftRadius", 20], ["borderTopRightRadius", 20], ["borderBottomRightRadius", 20], ["borderBottomLeftRadius", 20], ["borderTopWidth", "medium"], ["borderRightWidth", "medium"], ["borderBottomWidth", "medium"], ["borderLeftWidth", "medium"], ["borderTopStyle", "none"], ["borderRightStyle", "none"], ["borderBottomStyle", "none"], ["borderLeftStyle", "none"], ["borderTopColor", "#000000"], ["borderRightColor", "#000000"], ["borderBottomColor", "#000000"], ["borderLeftColor", "#000000"], ["color", "#ffffff"], ["textAlign", "center"], ["backgroundColor", "#1785ff"]]))], ["flex-column", padStyleMapOf(utsMapOf([["display", "flex"], ["flexDirection", "column"], ["alignItems", "center"]]))]])]
