

  import {
    testInovkeUploadFile,
    CommonOptions
  } from '@/uni_modules/test-invoke-network-api'


  const __sfc__ = defineComponent({
    data() {
      return {
        title: 'uploadFile',
        imageSrc: '',
        task: null as UploadTask | null,
        //自动化测试例专用
        jest_result: false,
      }
    },
    onLoad() {
    },
    onUnload() {
      this.imageSrc = '';
      uni.hideLoading();
      this.task?.abort();
    },
    methods: {
      chooseImage: function () {
        uni.chooseImage({
          count: 1,
          sizeType: ['compressed'],
          sourceType: ['album'],
          success: (res) => {
            console.log('chooseImage success, temp path is', res.tempFilePaths[0], " at pages/API/upload-file/upload-file.uvue:50")
            var imageSrc = res.tempFilePaths[0]
            uni.showLoading({
              title: '上传中'
            })
            this.task = uni.uploadFile({
              url: 'https://unidemo.dcloud.net.cn/upload', //仅为示例，非真实的接口地址
              filePath: imageSrc,
              name: 'file',
              formData: {
                'user': 'test'
              },
              success: (res) => {
                console.log('uploadImage success, res is:', res, " at pages/API/upload-file/upload-file.uvue:63")
                uni.showToast({
                  title: '上传成功',
                  icon: 'success',
                  duration: 1000
                })
                this.imageSrc = imageSrc
              },
              fail: (err) => {
                console.log('uploadImage fail', err, " at pages/API/upload-file/upload-file.uvue:72");
                uni.showModal({
                  content: err.errMsg,
                  showCancel: false
                });
              },
              complete: (res) => {
                uni.hideLoading();
                this.task = null
              }
            });
          },
          fail: (err) => {
            console.log('chooseImage fail', err, " at pages/API/upload-file/upload-file.uvue:85")
          }
        })
      },
      //自动化测试例专用
      jest_uploadFile() {
        const imageSrc = "/static/uni.png";
        uni.uploadFile({
          url: 'https://unidemo.dcloud.net.cn/upload', //仅为示例，非真实的接口地址
          filePath: imageSrc,
          name: 'file',
          formData: {
            'user': 'test'
          },
          success: () => {
            this.jest_result = true;
          },
          fail: () => {
            this.jest_result = false;
          },
        })
      },
      jest_uploadFile_with_uni_env() {
        /**
         * 微信小程序只支持USER_DATA_PATH，且子目录未创建的情况下不能直接下载到子目录内
         */
        const filePath = `${uni.env.USER_DATA_PATH}/uni-app.png`
        uni.downloadFile({
          url: "https://qiniu-web-assets.dcloud.net.cn/unidoc/zh/uni-app.png",
          filePath: filePath,
          success: () => {
            uni.uploadFile({
              url: 'https://unidemo.dcloud.net.cn/upload', //仅为示例，非真实的接口地址
              filePath: filePath,
              name: 'file',
              success: () => {
                this.jest_result = true;
              },
              fail: () => {
                this.jest_result = false;
              },
            })
          },
          fail: () => {
            this.jest_result = false
          }
        });
      },
      jest_set_cookie() {
        uni.request({
          url: "https://request.dcloud.net.cn/api/http/header/setCookie",
          method: "GET",
          timeout: 6000,
          sslVerify: false,
          withCredentials: false,
          firstIpv4: false,
          success: () => {
            this.jest_cookie_upload(true)
          },
          fail: () => {
            this.jest_result = false;
          },
        });
      },

      jest_delete_cookie() {
        uni.request({
          url: "https://request.dcloud.net.cn/api/http/header/deleteCookie",
          method: "GET",
          timeout: 6000,
          sslVerify: false,
          withCredentials: false,
          firstIpv4: false,
          success: () => {
            this.jest_cookie_upload(false)
          },
          fail: () => {
            this.jest_result = false;
          },
        });
      },
      jest_cookie_upload(needCookie : boolean) {
        const imageSrc = "/static/uni.png";
        uni.uploadFile({
          url: 'https://request.dcloud.net.cn/api/http/header/upload',
          filePath: imageSrc,
          name: 'file',
          success: (res : UploadFileSuccess) => {
            const data = UTSAndroid.consoleDebugError(JSON.parseObject(res.data), " at pages/API/upload-file/upload-file.uvue:173")
            const errCode = data?.getNumber("errCode")
            if (errCode != null && errCode == 1000) {
              this.jest_result = needCookie ? false : true;
            } else {
              this.jest_result = needCookie ? true : false;
            }
          },
          fail: () => {
            this.jest_result = false;
          },
        })
      },
      jest_files_upload() {
        const imageSrc = "/static/uni.png";
        uni.uploadFile({
          url: 'https://unidemo.dcloud.net.cn/upload',
          files: [
            {
              name: "file1",
              uri: imageSrc
            } as UploadFileOptionFiles,
            {
              name: "file2",
              uri: imageSrc
            } as UploadFileOptionFiles
          ],
          success: (res : UploadFileSuccess) => {
            if (res.statusCode == 200) {
              this.jest_result = true;
            }
          },
          fail: () => {
            this.jest_result = false;
          },
        })
      },
      jest_uts_module_invoked() {

        testInovkeUploadFile({
          success: (res : any) => {
            this.jest_result = true
          },
          fail: (err : any) => {
            this.jest_result = false
          }
        } as CommonOptions)

      },
      jest_uploadFileWithoutFile(){
        const imageSrc = "/static/uni.png";
        uni.uploadFile({
          url: 'https://unidemo.dcloud.net.cn/upload', //仅为示例，非真实的接口地址
          formData: {
            'user': 'test'
          },
          success: (res) => {
            console.log("success: ",res, " at pages/API/upload-file/upload-file.uvue:230");
            this.jest_result = true;
          },
          fail: (err) => {
            console.log("fail: ", err, " at pages/API/upload-file/upload-file.uvue:234");
            this.jest_result = false;
          },
        })
      },
      jest_uploadFileVerifyUA(){
        uni.uploadFile({
          url: 'https://request.dcloud.net.cn/api/http/header/upload',
          header:{
            "User-Agent":"custom"
          },
          formData: {
            'user': 'test'
          },
          success: (res : UploadFileSuccess) => {
            const data = UTSAndroid.consoleDebugError(JSON.parseObject(res.data), " at pages/API/upload-file/upload-file.uvue:249")
            const innerData = data?.getJSON("data")
            const header = innerData?.getJSON("requestHeaders")
            const uas = header?.getArray("user-agent")
            if(uas != null) {
              this.jest_result = (uas.length == 1)
            }
          },
          fail: () => {
            this.jest_result = false;
          },
        })
      }
    }
  })

export default __sfc__
function GenPagesAPIUploadFileUploadFileRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
const _component_page_head = resolveEasyComponent("page-head",_easycom_page_head)

  return createElementVNode("scroll-view", utsMapOf({ class: "page-scroll-view" }), [
    createElementVNode("view", null, [
      createVNode(_component_page_head, utsMapOf({ title: _ctx.title }), null, 8 /* PROPS */, ["title"]),
      createElementVNode("view", utsMapOf({ class: "uni-padding-wrap uni-common-mt" }), [
        createElementVNode("view", utsMapOf({ class: "demo" }), [
          isTrue(_ctx.imageSrc)
            ? createElementVNode("image", utsMapOf({
                key: 0,
                src: _ctx.imageSrc,
                class: "image",
                mode: "widthFix"
              }), null, 8 /* PROPS */, ["src"])
            : createElementVNode("text", utsMapOf({
                key: 1,
                class: "uni-hello-addfile",
                onClick: _ctx.chooseImage
              }), "+ 选择图片", 8 /* PROPS */, ["onClick"])
        ])
      ])
    ])
  ])
}
const GenPagesAPIUploadFileUploadFileStyles = [utsMapOf([["image", padStyleMapOf(utsMapOf([["width", "100%"]]))], ["demo", padStyleMapOf(utsMapOf([["backgroundImage", "none"], ["backgroundColor", "#ffffff"], ["paddingTop", 25], ["paddingRight", 25], ["paddingBottom", 25], ["paddingLeft", 25], ["justifyContent", "center"], ["alignItems", "center"]]))], ["uni-hello-addfile", padStyleMapOf(utsMapOf([["textAlign", "center"], ["backgroundImage", "none"], ["backgroundColor", "#ffffff"], ["paddingTop", 25], ["paddingRight", 25], ["paddingBottom", 25], ["paddingLeft", 25], ["marginTop", 10], ["fontSize", 19], ["color", "#808080"]]))]])]

import _easycom_page_head from '@/components/page-head/page-head.vue'
