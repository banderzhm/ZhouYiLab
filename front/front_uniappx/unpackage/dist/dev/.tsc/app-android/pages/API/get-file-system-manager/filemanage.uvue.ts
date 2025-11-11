type fileListType = { __$originalPosition?: UTSSourceMapPosition<"fileListType", "pages/API/get-file-system-manager/filemanage.uvue", 50, 7>;
		path : string,
		shotPath : string,
		size : number,
		isFile : boolean,
		modifyTime : string
	}
	
const __sfc__ = defineComponent({
  __name: 'filemanage',
  setup(__props): any | null {
const __ins = getCurrentInstance()!;
const _ctx = __ins.proxy as InstanceType<typeof __sfc__>;
const _cache = __ins.renderCache;

	let fileList = ref([] as fileListType[])
	let count = ref(0), size = ref(0)
	let rootPath = ref("")
	let dialogShow = ref(false)
	const uniIcon = ref("\uE661")
	let currentFile = ref({"path":"","shotPath":"","size":0,isFile:false,modifyTime:''} as fileListType)
  let cacheOrData = ref(uni.env.USER_DATA_PATH)

  const switchCacheOrData = (e : UniRadioGroupChangeEvent) => {
		cacheOrData.value = (e.detail.value=="cache") ? uni.env.CACHE_PATH : uni.env.USER_DATA_PATH
	}

// 遍历文件刷新列表
	const refreshFile = () => {
	  const fileManager = uni.getFileSystemManager()
	  fileManager.stat({
	    // path: uni.env.CACHE_PATH, //沙盒cache目录
	    // path: uni.env.USER_DATA_PATH, //沙盒根目录
      path: cacheOrData.value,
	    recursive: true,
	    success: (res : StatSuccessResult) => {
				// log.value += 'statFileInfoTest success:' + JSON.stringify(res) + '\n\n'
	      console.log('statFileInfo success1', " at pages/API/get-file-system-manager/filemanage.uvue:79")
	      console.log('res.stats', res.stats, " at pages/API/get-file-system-manager/filemanage.uvue:80")
				console.log('uni.env.CACHE_PATH: ',uni.env.CACHE_PATH, " at pages/API/get-file-system-manager/filemanage.uvue:81");
				console.log('uni.env.USER_DATA_PATH: ',uni.env.USER_DATA_PATH, " at pages/API/get-file-system-manager/filemanage.uvue:82");
				console.log('uni.env.SANDBOX_PATH: ',uni.env.SANDBOX_PATH, " at pages/API/get-file-system-manager/filemanage.uvue:83");
				let tempFileList = [] as fileListType[]
				let tempSize = 0
				for (let i=0;i<res.stats.length;i++) {
					console.log("resitem",res.stats[i].path, " at pages/API/get-file-system-manager/filemanage.uvue:87");
          if (i==0) {
            rootPath.value = res.stats[i].path
						continue
          }
					let tempFileItem = {
						"path":"",
						"shotPath":"",
						"size":0,
						isFile:false,
						modifyTime:''
					} as fileListType

					tempFileItem.shotPath = res.stats[i].path.replace(rootPath.value, "")
					tempFileItem.path = res.stats[i].path
					tempFileItem.size = res.stats[i].stats.size/1024
					tempFileItem.isFile = res.stats[i].stats.isFile()
					const mdate = new Date(res.stats[i].stats.lastModifiedTime * 1000)
					tempFileItem.modifyTime = mdate.getFullYear().toString() + "-" + (mdate.getMonth()+1).toString() + "-" + mdate.getDate().toString() + " "
					+ mdate.getHours().toString() + ":" + mdate.getMinutes().toString() + ":" + mdate.getSeconds().toString()
					tempSize = tempSize + res.stats[i].stats.size
					tempFileList.push(tempFileItem)
				}
				fileList.value = tempFileList
				size.value = tempSize/1024/1024
				count.value = tempFileList.length
	    },
	    fail: (res : IUniError) => {
        uni.showModal({
        	title: '获取文件状态失败',
        	content: res.errMsg,
        	showCancel: false
        })
	      console.error('statFileInfo fail', res, " at pages/API/get-file-system-manager/filemanage.uvue:120")
	    },
	    complete: (res : any) => {
	      console.log("statFileInfo complete", res, " at pages/API/get-file-system-manager/filemanage.uvue:123")
	    }
	  } as StatOptions)
	}

	const showDialog = (item: fileListType) => {
		currentFile.value = item
		dialogShow.value = true
	}
	const closeDialog = () => {
		dialogShow.value = false
	}

	const deleteFile = (fileItem: fileListType) => {
    const fileManager = uni.getFileSystemManager()
    let path = cacheOrData.value + fileItem.path
    if (fileItem.isFile) {
      fileManager.unlink({
      	filePath: path,
      	success: () => {
      		refreshFile()
      		closeDialog()
      	},
      	fail: (err : IFileSystemManagerFail) => {
      		uni.showModal({
      			title: '删除文件失败',
      			content: err.errMsg,
      			showCancel: false
      		})
      	}
      })
    } else {
      fileManager.rmdir({
      	dirPath: path,
        recursive: true,
      	success: () => {
      		refreshFile()
      		closeDialog()
      	},
      	fail: (err : IFileSystemManagerFail) => {
      		uni.showModal({
      			title: '删除目录失败',
      			content: err.errMsg,
      			showCancel: false
      		})
      	}
      })
    }
	}

	const clearFile = () => {
    const fileManager = uni.getFileSystemManager()
    // TODO 3个App平台表现不一致 @颜伊林
    fileManager.rmdir({
      "dirPath":uni.env.CACHE_PATH,
      "recursive":true,
      success: (res : FileManagerSuccessResult) => {
        console.log("rmdir success", res, " at pages/API/get-file-system-manager/filemanage.uvue:180")
        fileList.value = [] as fileListType[]
        size.value = 0
        count.value = 0
        uni.showToast({
          title:"清空缓存目录成功",
          icon:"success"
        })
      },
      fail: (res : IFileSystemManagerFail) => {
        console.error('清空缓存目录失败', res, " at pages/API/get-file-system-manager/filemanage.uvue:190")
        uni.showModal({
        	title: '清空缓存目录失败',
        	content: res.errMsg,
        	showCancel: false
        })
      },
      complete: (res : any) => {
        console.log("rmdir complete", res, " at pages/API/get-file-system-manager/filemanage.uvue:198")
      }
    })
  }

  const openFile = (fileItem: fileListType) => {
		let path = cacheOrData.value + fileItem.path
		let suffix = path.split('.').pop()
		switch(suffix) {
			case 'jpg':
			case 'png':
			case 'gif':
			case 'jpeg':
			case 'webp':
			case 'bmp':
			case 'ico':
			case 'heic':
			case 'heif':
			case 'tif':
				console.log("path:", path, " at pages/API/get-file-system-manager/filemanage.uvue:217")
				uni.previewImage({
					urls: [path]
				})
				break

      default:
        uni.openDocument({
        	filePath: path,
        	success: (res) => {
        		console.log("openDocument success", res, " at pages/API/get-file-system-manager/filemanage.uvue:227")
        	},
        	fail: (res) => {
        		console.error("openDocument fail", res, " at pages/API/get-file-system-manager/filemanage.uvue:230")
            uni.showModal({
            	title: '打开文件失败',
            	content: res.errMsg,
            	showCancel: false
            })
        	}
        })

		}
  }
	const snapshot = () => {
			uni.getElementById("rootview")!.takeSnapshot({})
	}

// 根据路径中/的数量计算缩进样式
	const getIndentStyle = (path: string) => {
		const matches = path.match(/\//g)
		// console.log("matches", matches)
		let level = 0
		if (matches!=null) {
			level = matches.length
		}
		return {
			paddingLeft: `${level * 20}px`

		}
	}


return (): any | null => {

const _component_radio = resolveComponent("radio")
const _component_radio_group = resolveComponent("radio-group")

  return createElementVNode(Fragment, null, [
    createElementVNode("view", utsMapOf({
      style: normalizeStyle(utsMapOf({"flex-direction":"row"})),
      id: "rootview"
    }), [
      createVNode(_component_radio_group, utsMapOf({ onChange: switchCacheOrData }), utsMapOf({
        default: withSlotCtx((): any[] => [
          createVNode(_component_radio, utsMapOf({
            value: "data",
            checked: true
          }), utsMapOf({
            default: withSlotCtx((): any[] => ["数据目录"]),
            _: 1 /* STABLE */
          })),
          createVNode(_component_radio, utsMapOf({ value: "cache" }), utsMapOf({
            default: withSlotCtx((): any[] => ["缓存目录"]),
            _: 1 /* STABLE */
          }))
        ]),
        _: 1 /* STABLE */
      })),
      createElementVNode("button", utsMapOf({ onClick: refreshFile }), "刷新"),
      createElementVNode("button", utsMapOf({ onClick: clearFile }), "清空缓存"),
      createElementVNode("button", utsMapOf({ onClick: snapshot }), "截图")
    ], 4 /* STYLE */),
    createElementVNode("text", null, "文件总数：" + toDisplayString(unref(count)) + "；\\n文件总大小：" + toDisplayString(unref(size)) + " M", 1 /* TEXT */),
    createElementVNode("text", utsMapOf({
      style: normalizeStyle(utsMapOf({"border":"1px solid #ccc","margin":"5px"}))
    }), "根路径：" + toDisplayString(unref(rootPath)), 5 /* TEXT, STYLE */),
    createElementVNode("list-view", utsMapOf({
      style: normalizeStyle(utsMapOf({"flex":"1"}))
    }), [
      createElementVNode(Fragment, null, RenderHelpers.renderList(unref(fileList), (item, index, __index, _cached): any => {
        return createElementVNode("list-item", utsMapOf({
          key: index,
          style: normalizeStyle([utsMapOf({"padding":"5px","border-bottom":"1px solid #ccc"}), getIndentStyle(item.shotPath)]),
          onClick: () => {showDialog(item)}
        }), [
          createElementVNode("view", utsMapOf({
            style: normalizeStyle(utsMapOf({"flex-direction":"row"}))
          }), [
            isTrue(!item.isFile)
              ? createElementVNode("text", utsMapOf({
                  key: 0,
                  style: normalizeStyle(utsMapOf({"font-family":"uni-icon"}))
                }), toDisplayString(unref(uniIcon)), 5 /* TEXT, STYLE */)
              : createCommentVNode("v-if", true),
            createElementVNode("text", null, toDisplayString((item.shotPath as string)), 1 /* TEXT */)
          ], 4 /* STYLE */),
          isTrue(item.isFile)
            ? createElementVNode("text", utsMapOf({
                key: 0,
                style: normalizeStyle(utsMapOf({"font-size":"12px","color":"#ccc"}))
              }), toDisplayString(item.size) + "k", 5 /* TEXT, STYLE */)
            : createCommentVNode("v-if", true)
        ], 12 /* STYLE, PROPS */, ["onClick"])
      }), 128 /* KEYED_FRAGMENT */)
    ], 4 /* STYLE */),
    isTrue(unref(dialogShow))
      ? createElementVNode("view", utsMapOf({
          key: 0,
          style: normalizeStyle(utsMapOf({"position":"fixed","top":"0","bottom":"0","left":"0","right":"0","align-items":"center","justify-content":"center","z-index":"1000","background-color":"rgba(16, 16, 16, 0.5)"}))
        }), [
          createElementVNode("view", utsMapOf({
            style: normalizeStyle(utsMapOf({"background-color":"#fff","padding":"10px","border-radius":"10px","width":"90%","margin":"0 auto"}))
          }), [
            createElementVNode("text", utsMapOf({
              style: normalizeStyle(utsMapOf({"font-size":"20px","font-weight":"bold"}))
            }), toDisplayString(unref(currentFile).shotPath), 5 /* TEXT, STYLE */),
            createElementVNode("text", null, "类型：" + toDisplayString(unref(currentFile).isFile ? '文件' : '目录'), 1 /* TEXT */),
            createElementVNode("text", null, "路径：" + toDisplayString(unref(currentFile).path), 1 /* TEXT */),
            createElementVNode("text", null, "大小：" + toDisplayString(unref(currentFile).size) + "k", 1 /* TEXT */),
            createElementVNode("text", null, "修改时间：" + toDisplayString(unref(currentFile).modifyTime), 1 /* TEXT */),
            createElementVNode("view", utsMapOf({
              style: normalizeStyle(utsMapOf({"flex-direction":"row","width":"100%","justify-content":"space-between","padding":"10px"}))
            }), [
              isTrue(unref(currentFile).isFile)
                ? createElementVNode("button", utsMapOf({
                    key: 0,
                    onClick: () => {openFile(unref(currentFile))},
                    size: "mini"
                  }), "打开", 8 /* PROPS */, ["onClick"])
                : createCommentVNode("v-if", true),
              createElementVNode("button", utsMapOf({
                onClick: () => {deleteFile(unref(currentFile))},
                size: "mini"
              }), "删除", 8 /* PROPS */, ["onClick"]),
              createElementVNode("button", utsMapOf({
                onClick: closeDialog,
                size: "mini"
              }), "关闭")
            ], 4 /* STYLE */)
          ], 4 /* STYLE */)
        ], 4 /* STYLE */)
      : createCommentVNode("v-if", true)
  ], 64 /* STABLE_FRAGMENT */)
}
}

})
export default __sfc__
const GenPagesAPIGetFileSystemManagerFilemanageStyles = []
