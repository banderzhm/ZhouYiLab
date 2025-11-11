
  const __sfc__ = defineComponent({
    data() {
      return {
        log: '',
        logAble: true,
        staticPath: `/static/filemanager/to.zip`,
        isSuccess: false
      }
    },
    methods: {
      testReadZipEntry() {
        const fileManager = uni.getFileSystemManager()
        fileManager.readZipEntry({
          filePath: this.staticPath,
          encoding: 'utf-8',
          success: (res : EntriesResult) => {
            if (this.logAble) {
              this.log += 'testReadZipEntry success' + '\n\n'
            }
            console.log("testReadZipEntry success", res, " at pages/API/get-file-system-manager/testStatic.uvue:44")
            this.isSuccess = true
          },
          fail: (res : IUniError) => {
            if (this.logAble) {
              this.log += 'testReadZipEntry fail:' + JSON.stringify(res) + '\n\n'
            }
            console.log('testReadZipEntry fail', res, " at pages/API/get-file-system-manager/testStatic.uvue:51")
            this.isSuccess = false
          }
        })
      },
      openFileSyncTest(param : string) : string {
        const fileManager = uni.getFileSystemManager()
        let fd = fileManager.openSync({
          filePath: this.staticPath,
          flag: param,
        } as OpenFileSyncOptions)
        return fd
      },
      testFstatFile: function () {
        const fileManager = uni.getFileSystemManager()
        fileManager.fstat({
          fd: this.openFileSyncTest('r'),
          success: (res : FStatSuccessResult) => {
            if (this.logAble) {
              this.log += 'testFstatFile success:' + JSON.stringify(res) + '\n\n'
            }
            console.log("testFstatFile success", res, " at pages/API/get-file-system-manager/testStatic.uvue:72")
            this.isSuccess = true
          },
          fail: (res : IUniError) => {
            if (this.logAble) {
              this.log += 'testFstatFile fail:' + JSON.stringify(res) + '\n\n'
            }
            console.log('testFstatFile fail', res, " at pages/API/get-file-system-manager/testStatic.uvue:79")
            this.isSuccess = false
          }
        })
      },
      testAccessFile: function () {
        const fileManager = uni.getFileSystemManager()
        fileManager.access({
          path: this.staticPath,
          success: (res : FileManagerSuccessResult) => {
            if (this.logAble) {
              this.log += 'testAccessFile success:' + JSON.stringify(res) + '\n\n'
            }
            console.log('success', res, " at pages/API/get-file-system-manager/testStatic.uvue:92")
            this.isSuccess = true
          },
          fail: (res : IUniError) => {
            if (this.logAble) {
              this.log += 'testAccessFile fail:' + JSON.stringify(res) + '\n\n'
            }
            this.isSuccess = false
          }
        })
      },

      testAccessDir: function () {
        const fileManager = uni.getFileSystemManager()
        fileManager.access({
          path: '/static/filemanager',
          success: (res : FileManagerSuccessResult) => {
            if (this.logAble) {
              this.log += 'testAccessDir success:' + JSON.stringify(res) + '\n\n'
            }
            console.log('success', res, " at pages/API/get-file-system-manager/testStatic.uvue:112")
            this.isSuccess = true
          },
          fail: (res : IUniError) => {
            if (this.logAble) {
              this.log += 'testAccessDir fail:' + JSON.stringify(res) + '\n\n'
            }
            this.isSuccess = false
          }
        })
      },

      testReadFile: function () {
        let fileManager = uni.getFileSystemManager()

        fileManager.readFile({
          filePath: this.staticPath,
          encoding: "utf-8",
          success: (res : ReadFileSuccessResult) => {
            if (this.logAble) {
              this.log += 'testReadFile success:' + JSON.stringify(res) + '\n\n'
            }
            console.log('testReadFile success', res, " at pages/API/get-file-system-manager/testStatic.uvue:134")
            this.isSuccess = true
          },
          fail: (res : IUniError) => {
            if (this.logAble) {
              this.log += 'testReadFile fail:' + JSON.stringify(res) + '\n\n'
            }
            console.log('testReadFile', res, " at pages/API/get-file-system-manager/testStatic.uvue:141")
            this.isSuccess = false
          }
        })
      },

      testReadDir: function () {
        let fileManager = uni.getFileSystemManager()

        fileManager.readdir({
          dirPath: `/static/filemanager`,
          success: (res : ReadDirSuccessResult) => {
            if (this.logAble) {
              this.log += 'testReadDir success:' + JSON.stringify(res) + '\n\n'
            }
            console.log('testReadDir success', res, " at pages/API/get-file-system-manager/testStatic.uvue:156")
            this.isSuccess = true
          },
          fail: (res : IUniError) => {
            if (this.logAble) {
              this.log += 'testReadDir fail:' + JSON.stringify(res) + '\n\n'
            }
            console.log('testReadDir', res, " at pages/API/get-file-system-manager/testStatic.uvue:163")
            this.isSuccess = false
          }
        })
      },

      testCopyFile: function () {
        let fileManager = uni.getFileSystemManager()

        fileManager.copyFile({
          srcPath: this.staticPath,
          destPath: `${uni.env.USER_DATA_PATH}/filemanager/to.zip`,
          success: (res : FileManagerSuccessResult) => {
            if (this.logAble) {
              this.log += 'testCopyFile success:' + JSON.stringify(res) + '\n\n'
            }
            console.log('testCopyFile success', res, " at pages/API/get-file-system-manager/testStatic.uvue:179")
            this.isSuccess = true
          },
          fail: (res : IFileSystemManagerFail) => {
            if (this.logAble) {
              this.log += 'testCopyFile fail:' + JSON.stringify(res) + '\n\n'
            }
            console.log('testCopyFile', res, " at pages/API/get-file-system-manager/testStatic.uvue:186")
            this.isSuccess = false
          }
        })
      }

    }
  })

export default __sfc__
function GenPagesAPIGetFileSystemManagerTestStaticRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
  return createElementVNode(Fragment, null, [
    createElementVNode("text", null, "查看通过uni.getFileSystemManager()直接处理Static目录的操作日志"),
    createElementVNode("button", utsMapOf({
      size: "mini",
      onClick: () => {_ctx.log=''}
    }), "清空日志", 8 /* PROPS */, ["onClick"]),
    createElementVNode("scroll-view", utsMapOf({
      style: normalizeStyle(utsMapOf({"max-height":"300px"}))
    }), [
      createElementVNode("text", utsMapOf({
        style: normalizeStyle(utsMapOf({"margin":"2px","padding":"2px","border":"1px solid #000000"}))
      }), toDisplayString(_ctx.log), 5 /* TEXT, STYLE */)
    ], 4 /* STYLE */),
    createElementVNode("scroll-view", utsMapOf({
      style: normalizeStyle(utsMapOf({"flex":"1"}))
    }), [
      createElementVNode("button", utsMapOf({
        class: "btnstyle",
        type: "primary",
        onClick: _ctx.testAccessFile,
        id: "testAccessFile"
      }), "判断Static中的文件是否存在", 8 /* PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({
        class: "btnstyle",
        type: "primary",
        onClick: _ctx.testAccessDir,
        id: "testAccessDir"
      }), "判断Static中的目录是否存在", 8 /* PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({
        class: "btnstyle",
        type: "primary",
        onClick: _ctx.testCopyFile,
        id: "testCopyFile"
      }), "复制Static中的文件", 8 /* PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({
        class: "btnstyle",
        type: "primary",
        onClick: _ctx.testReadFile,
        id: "testReadFile"
      }), "读取Static中的文件", 8 /* PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({
        class: "btnstyle",
        type: "primary",
        onClick: _ctx.testReadDir,
        id: "testReadDir"
      }), "读取Static中filemanager目录内文件列表", 8 /* PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({
        class: "btnstyle",
        type: "primary",
        onClick: _ctx.testFstatFile,
        id: "testFstatFile"
      }), "获取Static中的文件状态信息", 8 /* PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({
        class: "btnstyle",
        type: "primary",
        onClick: _ctx.testReadZipEntry,
        id: "testReadZipEntry"
      }), "获取Static中压缩包内的文件", 8 /* PROPS */, ["onClick"])
    ], 4 /* STYLE */)
  ], 64 /* STABLE_FRAGMENT */)
}
const GenPagesAPIGetFileSystemManagerTestStaticStyles = [utsMapOf([["btnstyle", padStyleMapOf(utsMapOf([["marginTop", 4], ["marginRight", 4], ["marginBottom", 4], ["marginLeft", 4]]))]])]
