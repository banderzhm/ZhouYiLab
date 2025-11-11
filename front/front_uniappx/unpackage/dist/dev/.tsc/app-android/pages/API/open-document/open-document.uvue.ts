import _easycom_page_head from '@/components/page-head/page-head.vue'
type FileItem = { __$originalPosition?: UTSSourceMapPosition<"FileItem", "pages/API/open-document/open-document.uvue", 17, 8>;
    type : string,
    url : string
  }

  
const __sfc__ = defineComponent({
  __name: 'open-document',
  setup(__props): any | null {
const __ins = getCurrentInstance()!;
const _ctx = __ins.proxy as InstanceType<typeof __sfc__>;
const _cache = __ins.renderCache;

  const title = 'openDocument'
  const fileList = ref<Array<FileItem>>([
    {
      type: 'pdf',
      url: 'https://web-assets.dcloud.net.cn/unidoc/zh/helloworld.pdf'
    },
    {
      type: 'doc',
      url: 'https://web-ext-storage.dcloud.net.cn/uni-app-x/file/helloworld.doc'
    },
    {
      type: 'docx',
      url: 'https://web-ext-storage.dcloud.net.cn/uni-app-x/file/helloworld.docx'
    },
    {
      type: 'ppt',
      url: 'https://web-ext-storage.dcloud.net.cn/uni-app-x/file/helloworld.ppt'
    },
    {
      type: 'pptx',
      url: 'https://web-ext-storage.dcloud.net.cn/uni-app-x/file/helloworld.pptx'
    },
    {
      type: 'xls',
      url: 'https://web-ext-storage.dcloud.net.cn/uni-app-x/file/helloworld.xls'
    },
    {
      type: 'xlsx',
      url: 'https://web-ext-storage.dcloud.net.cn/uni-app-x/file/helloworld.xlsx'
    },
    {
      type: 'zip',
      url: 'https://web-ext-storage.dcloud.net.cn/uni-app-x/file/to.zip'
    },
    {
      type: 'br',
      url: '/static/filemanager/1.txt.br'
    },
    {
      type: 'mp3',
      url: '/static/test-audio/ForElise.mp3'
    },
    {
      type: 'mp4',
      url: '/static/test-video/10second-demo.mp4'
    },
    {
      type: 'svg',
      url: '/static/test-image/logo.svg'
    }
  ])

  const openDocument = (item : FileItem) => {

    if (item.url.startsWith('http')) {
      uni.showLoading({
        title: '下载中',
        mask: true
      })
      uni.downloadFile({
        url: item.url,
        success: (res) => {
          uni.openDocument({
            filePath: res.tempFilePath,
            success: () => {
              uni.hideLoading()
              console.log('打开文档成功', " at pages/API/open-document/open-document.uvue:88")
            },
            fail: (err) => {
              uni.hideLoading()
              console.log('打开文档失败', err, " at pages/API/open-document/open-document.uvue:92")
              uni.showToast({
                title: '错误码：' + err.errCode.toString(),
                icon: "error"
              })
            }
          })
        },
        fail: (err) => {
          uni.hideLoading()
          console.log('下载失败', err, " at pages/API/open-document/open-document.uvue:102")
          uni.showToast({
            title: '下载失败：' + err.errCode.toString(),
            icon: "error"
          })
        }
      })
    } else {
      uni.openDocument({
        filePath: item.url,
        success: () => {
          console.log('打开文档成功', " at pages/API/open-document/open-document.uvue:113")
        },
        fail: (err) => {
          console.log('打开文档失败', err, " at pages/API/open-document/open-document.uvue:116")
          uni.showToast({
            title: '错误码：' + err.errCode.toString(),
            icon: "error"
          })
        }
      })
    }


  }

return (): any | null => {

const _component_page_head = resolveEasyComponent("page-head",_easycom_page_head)

  return createElementVNode(Fragment, null, [
    createVNode(_component_page_head, utsMapOf({ title: title })),
    createElementVNode("scroll-view", utsMapOf({
      direction: "vertical",
      style: normalizeStyle(utsMapOf({"flex":"1"}))
    }), [
      createElementVNode("view", utsMapOf({ class: "uni-common-mt" }), [
        createElementVNode(Fragment, null, RenderHelpers.renderList(unref(fileList), (item, index, __index, _cached): any => {
          return createElementVNode("button", utsMapOf({
            key: index,
            onClick: () => {openDocument(item)},
            style: normalizeStyle(utsMapOf({"margin":"10px"}))
          }), " 打开 " + toDisplayString(item.type) + " 文件 ", 13 /* TEXT, STYLE, PROPS */, ["onClick"])
        }), 128 /* KEYED_FRAGMENT */)
      ])
    ], 4 /* STYLE */)
  ], 64 /* STABLE_FRAGMENT */)
}
}

})
export default __sfc__
const GenPagesAPIOpenDocumentOpenDocumentStyles = []
