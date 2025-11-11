
  const __sfc__ = defineComponent({

    data() {
      return {
        log: "",
        /**
         * 自动化测试需要关闭log
         */
        logAble: true,
        fileListSuccess: [] as string[],
        fileListComplete: [] as string[],
        accessFileRet: '',
        lastFailError: new UniError("uni-file-manager", 1300000, "mock error"),
        lastCompleteError: new UniError("uni-file-manager", 1300000, "mock error"),
        readDir: 'a',
        readFileRet: "",
        writeFileContent: "中文 en.\r\n\t换行",
        appendFileContent: "append content",
        getFileInfoAlgorithm: "md5",
        getFileInfoSize: -1,
        getFileInfoDigest: "",
        unlinkFile: 'a/1.txt',
        accessFile: 'a/1.txt',
        writeFile: 'a/1.txt',
        writeData: 'insert data哈哈哈',
        brFile: 'a/1.txt.br',
        temFile: 'a/1.txt',
        copyFromFile: 'a/1.txt',
        copyToFile: 'a/2.txt',
        renameFromFile: 'a/2.txt',
        renameToFile: 'a/3.txt',
        getFileInfoFile: 'a/1.txt',
        statFile: '',
        rmDirFile: 'a',
        mkdirFile: 'a',
        readFile: 'a/1.txt',
        recursiveVal: true,
        done: false,
        writeFileEncoding: "utf-8",
        readFileEncoding: "utf-8",
        statsRet: [] as Array<FileStats>,
        unzipFile: 'zip/1.zip',
        targetZip: "unzip",
        renameFileRet: '',
        saveFileRet: '',
        removeSavedFileRet: '',
        fd: '',
        closeFileRet: '',
        bytesWritten: 0,
        fstat: null as Stats | null,
        fstatSize: 0,
        ftruncateRet: '',
        readZipFile: 'to.zip',
        getSavedFileListRet: '',
        arrayBufferRes: 0,
        /**
         * 待测试的全局环境变量
         */








        basePath: uni.env.USER_DATA_PATH,
        copyToBasePath: uni.env.USER_DATA_PATH,
        globalTempPath: uni.env.CACHE_PATH,
        globalRootPath: uni.env.SANDBOX_PATH,
        globalUserDataPath: uni.env.USER_DATA_PATH,

      }
    },
    onLoad() {
    },

    methods: {
      statFileInfoTest: function (_ : any) {
        const fileManager = uni.getFileSystemManager()
        fileManager.stat({
          path: `${this.basePath}${this.statFile}`, //USER_DATA_PATH
          // path: `${this.globalTempPath}${this.statFile}`, //CACHE_PATH
          recursive: this.recursiveVal,
          success: (res : StatSuccessResult) => {
            if (this.logAble) {
              this.log += 'statFileInfoTest success:' + JSON.stringify(res) + '\n\n'
            }
            console.log('statFileInfoTest success', res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:217")
            this.statsRet = res.stats
            console.log('this.statsRet', this.statsRet, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:219")
          },
          fail: (res : IUniError) => {
            if (this.logAble) {
              this.log += 'statFileInfoTest fail:' + JSON.stringify(res) + '\n\n'
            }
            console.log('statFileInfoTest fail', res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:225")
            this.lastFailError = new UniError(res.errSubject, res.errCode, res.errMsg)
          },
          complete: (res : any) => {
            console.log("statFileInfoTest complete", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:229")
            this.done = true
            if (res instanceof UniError) {
              this.lastCompleteError = res
            }
          }
        } as StatOptions)
      },

      getFileInfoTest: function () {
        const fileManager = uni.getFileSystemManager()

        fileManager.getFileInfo({
          filePath: `${this.basePath}${this.getFileInfoFile}`,
          digestAlgorithm: this.getFileInfoAlgorithm,
          success: (res : GetFileInfoSuccessResult) => {
            if (this.logAble) {
              this.log += 'getFileInfoTest success:' + JSON.stringify(res) + '\n\n'
            }
            console.log('success', res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:248")
            this.getFileInfoSize = res.size
            this.getFileInfoDigest = res.digest
          },
          fail: (res : IUniError) => {
            if (this.logAble) {
              this.log += 'getFileInfoTest fail:' + JSON.stringify(res) + '\n\n'
            }
            console.log('fail', res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:256")
            this.lastFailError = new UniError(res.errSubject, res.errCode, res.errMsg)
          },
          complete: (res : any) => {
            console.log("complete", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:260")
            this.done = true
            if (res instanceof UniError) {
              this.lastCompleteError = res
            }
          }
        } as GetFileInfoOptions)
      },

      copyFileTest: function () {
        const fileManager = uni.getFileSystemManager()

        fileManager.copyFile({
          srcPath: `${this.basePath}${this.copyFromFile}`,
          destPath: `${this.copyToBasePath}${this.copyToFile}`,
          success: (res : FileManagerSuccessResult) => {
            if (this.logAble) {
              this.log += 'copyFileTest success:' + JSON.stringify(res) + '\n\n'
            }
            console.log('success', res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:279")
          },
          fail: (res : IUniError) => {
            if (this.logAble) {
              this.log += 'copyFileTest fail:' + JSON.stringify(res) + '\n\n'
            }
            console.log('fail', res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:285")
            this.lastFailError = new UniError(res.errSubject, res.errCode, res.errMsg)
          },
          complete: (res : any) => {
            console.log("complete", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:289")
            this.done = true
            if (res instanceof UniError) {
              this.lastCompleteError = res
            }
          }
        } as CopyFileOptions)
      },

      renameFileTest: function () {
        const fileManager = uni.getFileSystemManager()

        fileManager.rename({
          oldPath: `${this.basePath}${this.renameFromFile}`,
          newPath: `${this.basePath}${this.renameToFile}`,
          success: (res) => {
            if (this.logAble) {
              this.log += 'renameFileTest success:' + JSON.stringify(res) + '\n\n'
            }
            this.renameFileRet = "rename:ok"
            console.log('success', res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:309")
          },
          fail: (res : IUniError) => {
            if (this.logAble) {
              this.log += 'renameFileTest fail:' + JSON.stringify(res) + '\n\n'
            }
            console.log('fail', res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:315")
            this.lastFailError = new UniError(res.errSubject, res.errCode, res.errMsg)
          },
          complete: (res : any) => {
            this.done = true
            console.log("complete", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:320")
            if (res instanceof UniError) {
              this.lastCompleteError = res
            }
          }
        } as RenameOptions)
      },

      readDirTest: function () {
        const fileManager = uni.getFileSystemManager()
        fileManager.readdir({
          dirPath: `${this.basePath}${this.readDir}`,
          success: (res : ReadDirSuccessResult) => {
            if (this.logAble) {
              this.log += 'readDirTest success:' + JSON.stringify(res) + '\n\n'
            }
            console.log("success", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:336")
            this.fileListSuccess = res.files
          },
          fail: (res : IUniError) => {
            if (this.logAble) {
              this.log += 'readDirTest fail:' + JSON.stringify(res) + '\n\n'
            }
            console.log('fail', res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:343")
            this.lastFailError = new UniError(res.errSubject, res.errCode, res.errMsg)
          },
          complete: (res : any) => {
            console.log("complete", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:347")
            this.done = true
            if (res instanceof UniError) {
              this.lastCompleteError = res
            } else {
              this.fileListComplete = (res as ReadDirSuccessResult).files
            }
          }
        } as ReadDirOptions)
      },

      writeFileTest: function (_ : any) {
        const fileManager = uni.getFileSystemManager()

        fileManager.writeFile({
          filePath: `${this.basePath}${this.writeFile}`,
          data: this.writeFileContent,
          encoding: this.writeFileEncoding,
          success: (res) => {
            if (this.logAble) {
              this.log += 'writeFileTest success:' + JSON.stringify(res) + '\n\n'
            }
            console.log('success', res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:369")
          },
          fail: (res : IUniError) => {
            if (this.logAble) {
              this.log += 'writeFileTest fail:' + JSON.stringify(res) + '\n\n'
            }
            console.log('fail', " at pages/API/get-file-system-manager/get-file-system-manager.uvue:375")
            this.lastFailError = new UniError(res.errSubject, res.errCode, res.errMsg)
          },
          complete: (res : any) => {
            this.done = true
            console.log("complete", " at pages/API/get-file-system-manager/get-file-system-manager.uvue:380")
            if (res instanceof UniError) {
              this.lastCompleteError = res
            }

          }
        } as WriteFileOptions)
      },

      readFileTest: function () {
        const fileManager = uni.getFileSystemManager()
        fileManager.readFile({
          filePath: `${this.basePath}${this.readFile}`,
          encoding: this.readFileEncoding,
          success: (res : ReadFileSuccessResult) => {
            if (this.logAble) {
              this.log += 'readFileTest success:' + JSON.stringify(res) + '\n\n'
            }
            console.log('success', res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:398")
            this.readFileRet = res.data.toString()
          },
          fail: (res : IUniError) => {
            if (this.logAble) {
              this.log += 'readFileTest fail:' + JSON.stringify(res) + '\n\n'
            }
            console.log('fail', res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:405")
            this.lastFailError = new UniError(res.errSubject, res.errCode, res.errMsg)
          },
          complete: (res : any) => {
            console.log("complete", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:409")
            this.done = true
            if (res instanceof UniError) {
              this.lastCompleteError = res
            }
          }
        } as ReadFileOptions)
      },

      rmdirTest: function () {
        const fileManager = uni.getFileSystemManager()
        fileManager.rmdir({
          dirPath: `${this.basePath}${this.rmDirFile}`,
          recursive: this.recursiveVal,
          success: (res : FileManagerSuccessResult) => {
            if (this.logAble) {
              this.log += 'rmdirTest success:' + JSON.stringify(res) + '\n\n'
            }
            console.log('success', res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:427")
          },
          fail: (res : IUniError) => {
            if (this.logAble) {
              this.log += 'rmdirTest fail:' + JSON.stringify(res) + '\n\n'
            }
            console.log('fail', res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:433")
            this.lastFailError = new UniError(res.errSubject, res.errCode, res.errMsg)
          },
          complete: (res : any) => {
            console.log("complete", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:437")
            this.done = true
            if (res instanceof UniError) {
              this.lastCompleteError = res
            }
          }
        } as RmDirOptions)
      },

      mkdirTest: function () {
        // 准备测试数据
        const fileManager = uni.getFileSystemManager()

        fileManager.mkdir({
          dirPath: `${this.basePath}${this.mkdirFile}`,
          recursive: this.recursiveVal,
          success: (res : FileManagerSuccessResult) => {
            if (this.logAble) {
              this.log += 'mkdirTest success:' + JSON.stringify(res) + '\n\n'
            }
            console.log('success', res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:457")
          },
          fail: (res : IUniError) => {
            if (this.logAble) {
              this.log += 'mkdirTest fail:' + JSON.stringify(res) + '\n\n'
            }
            console.log('fail', res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:463")
            this.lastFailError = new UniError(res.errSubject, res.errCode, res.errMsg)

          },
          complete: (res : any) => {
            if (res instanceof UniError) {
              this.lastCompleteError = res
            }
            this.done = true
            console.log("complete", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:472")
          }
        } as MkDirOptions)

      },
      accessFileTest: function () {
        this.accessFileRet = ''
        const fileManager = uni.getFileSystemManager()
        fileManager.access({
          path: `${this.basePath}${this.accessFile}`,
          success: (res : FileManagerSuccessResult) => {
            if (this.logAble) {
              this.log += 'accessFileTest success:' + JSON.stringify(res) + '\n\n'
            }
            console.log('success', res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:486")
            this.accessFileRet = res.errMsg
          },
          fail: (res : IUniError) => {
            if (this.logAble) {
              this.log += 'accessFileTest fail:' + JSON.stringify(res) + '\n\n'
            }
            console.log('fail', res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:493")
            this.lastFailError = new UniError(res.errSubject, res.errCode, res.errMsg)

          },
          complete: (res : any) => {
            if (res instanceof UniError) {
              this.lastCompleteError = res
            }
            console.log("complete", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:501")
            this.done = true
          }
        } as AccessOptions)

      },
      unlinkTest: function () {
        const fileManager = uni.getFileSystemManager()

        fileManager.unlink({
          filePath: `${this.basePath}${this.unlinkFile}`,
          success: (res : FileManagerSuccessResult) => {
            if (this.logAble) {
              this.log += 'unlinkTest success:' + JSON.stringify(res) + '\n\n'
            }
            console.log('success', res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:516")
          },
          fail: (res : IUniError) => {
            if (this.logAble) {
              this.log += 'unlinkTest fail:' + JSON.stringify(res) + '\n\n'
            }
            console.log('fail', res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:522")
            this.lastFailError = new UniError(res.errSubject, res.errCode, res.errMsg)

          },
          complete: (res : any) => {
            if (res instanceof UniError) {
              this.lastCompleteError = res
            }
            console.log("complete", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:530")
            this.done = true
          }
        } as UnLinkOptions)
      },
      unlinkAllFileTest: function () {
        const fileManager = uni.getFileSystemManager()
        fileManager.readdir({
          dirPath: `${this.basePath}${this.rmDirFile}`,
          success: (res : ReadDirSuccessResult) => {
            console.log("success to readdir", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:540")
            res.files.forEach(element => {
              console.log(element, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:542")
              let filePath : string
              if (this.rmDirFile.length <= 0) {
                filePath = `${this.basePath}${element}`
              } else {
                filePath = `${this.basePath}${this.rmDirFile}/${element}`
              }
              fileManager.unlink({
                filePath: filePath,
                success: (res : FileManagerSuccessResult) => {
                  if (this.logAble) {
                    this.log += 'unlinkAllFileTest success:' + JSON.stringify(res) + '\n\n'
                  }
                  console.log('success unlink', res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:555")
                },
                fail: (res : IUniError) => {
                  if (this.logAble) {
                    this.log += 'unlinkAllFileTest fail:' + JSON.stringify(res) + '\n\n'
                  }
                  console.log('fail unlink', res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:561")
                  this.lastFailError = new UniError(res.errSubject, res.errCode, res.errMsg)

                },
                complete: (res : any) => {
                  if (res instanceof UniError) {
                    this.lastCompleteError = res
                  }
                  console.log("complete unlink", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:569")
                  this.done = true
                }
              } as UnLinkOptions)
            });
          },
          fail: (res : IUniError) => {
            if (this.logAble) {
              this.log += 'unlinkAllFileTest fail:' + JSON.stringify(res) + '\n\n'
            }
            console.log('fail to readdir', res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:579")
            this.lastFailError = new UniError(res.errSubject, res.errCode, res.errMsg)

          },
          complete: (res : any) => {
            console.log("complete readdir", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:584")
            this.done = true
            if (res instanceof UniError) {
              this.lastCompleteError = res
            } else {
              this.fileListComplete = (res as ReadDirSuccessResult).files
            }
          }
        } as ReadDirOptions)
      },
      copyStaticToFilesTest: function () {
        const fileManager = uni.getFileSystemManager()

        fileManager.copyFile({
          srcPath: "/static/list-mock/mock.json",
          destPath: `${this.copyToBasePath}/a/mock.json`,
          success: (res : FileManagerSuccessResult) => {
            if (this.logAble) {
              this.log += 'copyFileTest success:' + JSON.stringify(res) + '\n\n'
            }
            console.log('success', res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:604")
          },
          fail: (res : IUniError) => {
            if (this.logAble) {
              this.log += 'copyFileTest fail:' + JSON.stringify(res) + '\n\n'
            }
            console.log('fail', res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:610")
            this.lastFailError = new UniError(res.errSubject, res.errCode, res.errMsg)

          },
          complete: (res : any) => {
            console.log("complete", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:615")
            this.done = true
            if (res instanceof UniError) {
              this.lastCompleteError = res
            }
          }
        } as CopyFileOptions)
      },
      //start
      appendFileTest: function (_ : any) {
        const fileManager = uni.getFileSystemManager()
        fileManager.appendFile({
          filePath: `${this.basePath}${this.writeFile}`,
          data: this.appendFileContent,
          encoding: this.writeFileEncoding,
          success: (res : FileManagerSuccessResult) => {
            if (this.logAble) {
              this.log += 'appendFileTest success:' + JSON.stringify(res) + '\n\n'
            }
            console.log('success', res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:634")
          },
          fail: (res : IUniError) => {
            if (this.logAble) {
              this.log += 'appendFileTest fail:' + JSON.stringify(res) + '\n\n'
            }
            console.log('fail', " at pages/API/get-file-system-manager/get-file-system-manager.uvue:640")
            this.lastFailError = new UniError(res.errSubject, res.errCode, res.errMsg)

          },
          complete: (res : any) => {
            this.done = true
            console.log("complete", " at pages/API/get-file-system-manager/get-file-system-manager.uvue:646")
            if (res instanceof UniError) {
              this.lastCompleteError = res
            }

          }
        } as AppendFileOptions)
      },
      writeFileSyncTest: function (_ : any) {
        try {
          const fileManager = uni.getFileSystemManager()
          fileManager.writeFileSync(`${this.basePath}${this.writeFile}`, this.writeFileContent, this.writeFileEncoding)
          if (this.logAble) {
            this.log += 'writeFileSyncTest success:' + '\n\n'
          }
          this.done = true
        } catch (e) {
          if (this.logAble) {
            this.log += 'writeFileSyncTest fail:' + e + '\n\n'
          }
          this.done = true
        }

      },
      readFileSyncTest: function () {
        try {
          const fileManager = uni.getFileSystemManager()
          let data = fileManager.readFileSync(
            `${this.basePath}${this.readFile}`,
            this.readFileEncoding)
          if (this.logAble) {
            this.log += 'readFileSyncTest result:' + data + '\n\n'
          }
          this.done = true
          this.readFileRet = data.toString()
        } catch (e) {
          if (this.logAble) {
            this.log += 'readFileSyncTest fail:' + e + '\n\n'
          }
          this.done = true
        }

      },
      unlinkSyncTest: function () {
        try {
          const fileManager = uni.getFileSystemManager()
          fileManager.unlinkSync(
            `${this.basePath}${this.unlinkFile}`)
          this.done = true
        } catch (e) {
          if (this.logAble) {
            this.log += 'unlinkSyncTest fail:' + e + '\n\n'
          }
          this.done = true
        }
      },
      mkdirSyncTest: function () {
        // 准备测试数据
        try {
          const fileManager = uni.getFileSystemManager()
          fileManager.mkdirSync(`${this.basePath}${this.mkdirFile}`, this.recursiveVal)
          this.done = true
        } catch (e) {
          this.done = true
          if (this.logAble) {
            this.log += 'mkdirSyncTest fail:' + e + '\n\n'
          }
        }

      },
      rmdirSyncTest: function () {
        try {
          const fileManager = uni.getFileSystemManager()
          fileManager.rmdirSync(
            `${this.basePath}${this.rmDirFile}`,
            this.recursiveVal)
          this.done = true
        } catch (e) {
          if (this.logAble) {
            this.log += 'rmdirSyncTest fail:' + e + '\n\n'
          }
          this.done = true
        }
      },
      readDirSyncTest: function () {
        try {
          const fileManager = uni.getFileSystemManager()
          let res = fileManager.readdirSync(
            `${this.basePath}${this.readDir}`)
          if (this.logAble) {
            this.log += 'readDirTest success:' + JSON.stringify(res) + '\n\n'
          }
          if (res != null) {
            this.fileListSuccess = res
          }
          this.done = true
        } catch (e) {
          if (this.logAble) {
            this.log += 'rmdirSyncTest fail:' + e + '\n\n'
          }
          this.done = true
        }
      },
      accessFileSyncTest: function () {
        this.accessFileRet = ''
        const fileManager = uni.getFileSystemManager()
        try {
          fileManager.accessSync(`${this.basePath}${this.accessFile}`)
          this.done = true
          this.accessFileRet = 'access:ok'
        } catch (e) {
          if (this.logAble) {
            this.log += 'rmdirSyncTest fail:' + e + '\n\n'
          }
          this.done = true
        }
      },
      renameFileSync: function () {
        const fileManager = uni.getFileSystemManager()
        try {
          fileManager.renameSync(`${this.basePath}${this.renameFromFile}`,
            `${this.basePath}${this.renameToFile}`)
          this.done = true
          this.renameFileRet = "rename:ok"

        } catch (e) {
          if (this.logAble) {
            this.log += 'rmdirSyncTest fail:' + e + '\n\n'
          }
          console.log('renameSync:' + e, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:775")
          this.done = true
        }
      },
      copyFileSyncTest: function () {
        const fileManager = uni.getFileSystemManager()
        try {
          fileManager.copyFileSync(
            `${this.basePath}${this.copyFromFile}`,
            `${this.copyToBasePath}${this.copyToFile}`)
          this.done = true
        } catch (e) {
          if (this.logAble) {
            this.log += 'rmdirSyncTest fail:' + e + '\n\n'
          }
          this.done = true
        }
      },
      appendFileSyncTest: function (_ : any) {
        const fileManager = uni.getFileSystemManager()
        try {
          fileManager.appendFileSync(
            `${this.basePath}${this.writeFile}`,
            this.appendFileContent,
            this.writeFileEncoding)
          this.done = true
        } catch (e) {
          if (this.logAble) {
            this.log += 'rmdirSyncTest fail:' + e + '\n\n'
          }
          this.done = true
        }
      },
      saveFileTest: function (_ : any) {
        const fileManager = uni.getFileSystemManager()
        this.writeFileSyncTest("")
        fileManager.saveFile({
          tempFilePath: `${this.basePath}${this.temFile}`,
          success: (res : SaveFileSuccessResult) => {
            if (this.logAble) {
              this.log += 'saveFileTest success:' + JSON.stringify(res) + '\n\n'
            }
            console.log('success', res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:817")
            this.saveFileRet = res.savedFilePath
            this.done = true
          },
          fail: (res : IUniError) => {
            if (this.logAble) {
              this.log += 'saveFileTest fail:' + JSON.stringify(res) + '\n\n'
            }
            console.log('saveFileTest fail', res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:825")
            this.lastFailError = new UniError(res.errSubject, res.errCode, res.errMsg)

            this.done = true
          },
          complete: (_) => {
            this.done = true
          }
        } as SaveFileOptions)
      },
      saveFileTest1: function (_ : any) {
        const fileManager = uni.getFileSystemManager()
        this.writeFileSyncTest("")
        fileManager.access({
          path: `${this.basePath}local`,
          success: () => {
            if (fileManager.fstatSync({ fd: fileManager.openSync({ filePath: `${this.basePath}local`, flag: "r" }) }).isDirectory()) {
              fileManager.rmdirSync(`${this.basePath}local`, true)
            } else {
              fileManager.unlinkSync(`${this.basePath}local`)
            }
            fileManager.saveFile({
              tempFilePath: `${this.basePath}${this.temFile}`,
              filePath: `${this.basePath}local/1.txt`,
              success: (res : SaveFileSuccessResult) => {
                if (this.logAble) {
                  this.log += 'saveFileTest success:' + JSON.stringify(res) + '\n\n'
                }
                console.log('success', res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:853")
                this.saveFileRet = res.savedFilePath
                this.done = true
              },
              fail: (res : IUniError) => {
                if (this.logAble) {
                  this.log += 'saveFileTest fail:' + JSON.stringify(res) + '\n\n'
                }
                console.log('saveFileTest fail', res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:861")
                this.lastFailError = new UniError(res.errSubject, res.errCode, res.errMsg)

                this.done = true
              },
              complete: (_) => {
                this.done = true
              }
            } as SaveFileOptions)
          },
          fail: () => {
            fileManager.saveFile({
              tempFilePath: `${this.basePath}${this.temFile}`,
              filePath: `${this.basePath}local/1.txt`,
              success: (res : SaveFileSuccessResult) => {
                if (this.logAble) {
                  this.log += 'saveFileTest success:' + JSON.stringify(res) + '\n\n'
                }
                console.log('success', res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:879")
                this.saveFileRet = res.savedFilePath
                this.done = true
              },
              fail: (res : IUniError) => {
                if (this.logAble) {
                  this.log += 'saveFileTest fail:' + JSON.stringify(res) + '\n\n'
                }
                console.log('saveFileTest fail', res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:887")
                this.lastFailError = new UniError(res.errSubject, res.errCode, res.errMsg)

                this.done = true
              },
              complete: (_) => {
                this.done = true
              }
            } as SaveFileOptions)
          }
        })
      },
      saveFileTest2: function (_ : any) {
        const fileManager = uni.getFileSystemManager()
        this.writeFileSyncTest("")
        fileManager.access({
          path: `${this.basePath}local`,
          success: () => {
            if (fileManager.fstatSync({ fd: fileManager.openSync({ filePath: `${this.basePath}local`, flag: "r" }) }).isDirectory()) {
              fileManager.rmdirSync(`${this.basePath}local`, true)
            } else {
              fileManager.unlinkSync(`${this.basePath}local`)
            }
            fileManager.saveFile({
              tempFilePath: `${this.basePath}${this.temFile}`,
              filePath: `${this.basePath}local`,
              success: (res : SaveFileSuccessResult) => {
                if (this.logAble) {
                  this.log += 'saveFileTest success:' + JSON.stringify(res) + '\n\n'
                }
                console.log('success', res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:917")
                this.saveFileRet = res.savedFilePath
                this.done = true
              },
              fail: (res : IUniError) => {
                if (this.logAble) {
                  this.log += 'saveFileTest fail:' + JSON.stringify(res) + '\n\n'
                }
                console.log('saveFileTest fail', res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:925")
                this.lastFailError = new UniError(res.errSubject, res.errCode, res.errMsg)

                this.done = true
              },
              complete: (_) => {
                this.done = true
              }
            } as SaveFileOptions)
          }
        })
      },
      saveFileTest3: function (_ : any) {
        const fileManager = uni.getFileSystemManager()
        this.writeFileSyncTest("")
        fileManager.access({
          path: `${this.basePath}local`,
          success: () => {
            if (fileManager.fstatSync({ fd: fileManager.openSync({ filePath: `${this.basePath}local`, flag: "r" }) }).isDirectory()) {
              fileManager.rmdirSync(`${this.basePath}local`, true)
            } else {
              fileManager.unlinkSync(`${this.basePath}local`)
            }

            fileManager.saveFile({
              tempFilePath: `${this.basePath}${this.temFile}`,
              filePath: `${this.basePath}local/`,
              success: (res : SaveFileSuccessResult) => {
                if (this.logAble) {
                  this.log += 'saveFileTest success:' + JSON.stringify(res) + '\n\n'
                }
                console.log('success', res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:956")
                this.saveFileRet = res.savedFilePath
                this.done = true
              },
              fail: (res : IUniError) => {
                if (this.logAble) {
                  this.log += 'saveFileTest fail:' + JSON.stringify(res) + '\n\n'
                }
                console.log('saveFileTest fail', res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:964")
                this.lastFailError = new UniError(res.errSubject, res.errCode, res.errMsg)
                this.done = true
              },
              complete: (_) => {
                this.done = true
              }
            } as SaveFileOptions)
          }
        })
      },
      saveFileSyncTest: function (_ : any) {
        const fileManager = uni.getFileSystemManager()
        this.writeFileSyncTest("")
        try {
          fileManager.saveFileSync(
            `${this.basePath}${this.temFile}`, null)
          this.done = true

          //todo 后面打开
          // this.saveFileRet=res
        } catch (e) {
          console.log('saveFileSyncTest:' + e, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:986")
          this.done = true
        }
      },
      unzipFileTest: function (_ : any) {
        const fileManager = uni.getFileSystemManager()
        try {
          fileManager.mkdirSync(`${this.basePath}${this.targetZip}`, true)
        } catch (e) {
          console.error(e, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:995")
        }
        fileManager.unzip({
          zipFilePath: '/static/filemanager/to.zip',
          targetPath: `${this.basePath}${this.targetZip}`,
          success: (res : FileManagerSuccessResult) => {
            if (this.logAble) {
              this.log += 'unzipFileTest success:' + JSON.stringify(res) + '\n\n'
            }
            console.log('success', res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1004")
          },
          fail: (res : IUniError) => {
            if (this.logAble) {
              this.log += 'unzipFileTest fail:' + JSON.stringify(res) + '\n\n'
            }
            console.log('fail', res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1010")
            this.lastFailError = new UniError(res.errSubject, res.errCode, res.errMsg)

          },
          complete: (_) => {
            this.done = true
          }
        } as UnzipFileOptions)
      },

      getSavedFileListTest: function () {
        const fileManager = uni.getFileSystemManager()
        fileManager.getSavedFileList({
          success: (res : GetSavedFileListResult) => {
            if (this.logAble) {
              this.log += 'getSavedFileListTest success:' + JSON.stringify(res) + '\n\n'
            }
            console.log("getSavedFileListTest success", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1027")
            this.fileListSuccess = res.fileList
            this.getSavedFileListRet = "getSavedFileList:ok"
          },
          fail: (res : IUniError) => {
            if (this.logAble) {
              this.log += 'getSavedFileListTest fail:' + JSON.stringify(res) + '\n\n'
            }
            console.log('getSavedFileListTest fail', res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1035")
            this.lastFailError = new UniError(res.errSubject, res.errCode, res.errMsg)

            this.getSavedFileListRet = JSON.stringify(res)
          },
          complete: (res : any) => {
            console.log("complete", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1041")
            this.done = true
            if (res instanceof UniError) {
              this.lastCompleteError = res
            } else {
              this.fileListComplete = (res as GetSavedFileListResult).fileList
            }
          }
        } as GetSavedFileListOptions)
      },
      truncateFileTest() {
        const fileManager = uni.getFileSystemManager()
        fileManager.truncate({
          filePath: `${this.basePath}${this.writeFile}`,
          length: 6,
          success: (res : FileManagerSuccessResult) => {
            if (this.logAble) {
              this.log += 'truncateFileTest success:' + JSON.stringify(res) + '\n\n'
            }
            console.log("success", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1060")
          },
          fail: (res : IUniError) => {
            if (this.logAble) {
              this.log += 'truncateFileTest fail:' + JSON.stringify(res) + '\n\n'
            }
            console.log('fail', res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1066")
            this.lastFailError = new UniError(res.errSubject, res.errCode, res.errMsg)

          },
          complete: (res : any) => {
            console.log("complete", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1071")
            this.done = true
            if (res instanceof UniError) {
              this.lastCompleteError = res
            }
          }
        } as TruncateFileOptions)
      },
      truncateFileSyncTest() {
        const fileManager = uni.getFileSystemManager()
        try {
          fileManager.truncateSync(
            `${this.basePath}${this.writeFile}`,
            3)
          this.done = true
        } catch (e) {
          console.log(e, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1087")
          this.done = true
        }
      },
      readCompressedFileTest() {
        const fileManager = uni.getFileSystemManager()
        fileManager.readCompressedFile({
          filePath: '/static/filemanager/1.txt.br',
          compressionAlgorithm: "br",
          success: (res : ReadCompressedFileResult) => {
            if (this.logAble) {
              this.log += 'readCompressedFileTest success:' + JSON.stringify(res) + '\n\n'
            }
            console.log("success", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1100")
          },
          fail: (res : IUniError) => {
            if (this.logAble) {
              this.log += 'readCompressedFileTest fail:' + JSON.stringify(res) + '\n\n'
            }
            console.log('fail', res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1106")
            this.lastFailError = new UniError(res.errSubject, res.errCode, res.errMsg)

          },
          complete: (_) => {
            this.done = true
          }
        } as ReadCompressedFileOptions)
      },
      readCompressedFileSyncTest() {
        console.log('readCompressedFileSyncTest', " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1116")
        const fileManager = uni.getFileSystemManager()
        try {
          let data = fileManager.readCompressedFileSync(
            '/static/filemanager/1.txt.br',
            "br")
          if (this.logAble) {
            this.log += data
          }
          this.done = true
        } catch (e) {
          if (this.logAble) {
            this.log += 'readCompressedFileSyncTest fail:' + e + '\n\n'
          }
          this.done = true
        }

      },
      removeSavedFileTest() {
        console.log("removeSavedFileTest enter", " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1135")
        const fileManager = uni.getFileSystemManager()
        fileManager.removeSavedFile({
          filePath: `${this.basePath}${this.writeFile}`,
          success: (res : FileManagerSuccessResult) => {
            if (this.logAble) {
              this.log += 'removeSavedFileTest success:' + JSON.stringify(res) + '\n\n'
            }
            this.removeSavedFileRet = res.errMsg
            console.log("removeSavedFileTest success", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1144")
          },
          fail: (res : IUniError) => {
            if (this.logAble) {
              this.log += 'removeSavedFileTest fail:' + JSON.stringify(res) + '\n\n'
            }
            console.log('removeSavedFileTest fail', res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1150")
          },
          complete: (_) => {
            this.done = true
          }
        } as RemoveSavedFileOptions)
      },

      statFileInfoSyncTest: function (_ : any) {
        const fileManager = uni.getFileSystemManager()
        try {
          let res = fileManager.statSync(
            // path: `${this.basePath}${this.statFile}`, //USER_DATA_PATH
            `${this.globalTempPath}${this.statFile}`, //CACHE_PATH
            this.recursiveVal)
          if (this.logAble) {
            this.log += 'statFileInfoSyncTest success:' + JSON.stringify(res) + '\n\n'
          }
          this.statsRet = res
          this.done = true
        } catch (e) {
          if (this.logAble) {
            this.log += 'statFileInfoSyncTest fail:' + e + '\n\n'
          }
          this.done = true
        }

      },
      openFileTest() {
        const fileManager = uni.getFileSystemManager()
        fileManager.open({
          filePath: `${this.basePath}${this.readFile}`,
          flag: "a",
          success: (res : OpenFileSuccessResult) => {
            if (this.logAble) {
              this.log += 'openFileTest success:' + JSON.stringify(res) + '\n\n'
            }
            console.log("success", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1187")
            this.fd = res.fd
          },
          fail: (res : IUniError) => {
            if (this.logAble) {
              this.log += 'openFileTest fail:' + JSON.stringify(res) + '\n\n'
            }
            console.log('fail', res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1194")
            this.lastFailError = new UniError(res.errSubject, res.errCode, res.errMsg)

          },
          complete: (_) => {
            this.done = true
          }
        } as OpenFileOptions)
      },
      openFileSyncTest(param : string, isTest : boolean) : string {
        const fileManager = uni.getFileSystemManager()
        try {
          let fd = fileManager.openSync({
            filePath: `${this.basePath}${this.readFile}`,
            flag: param,
          } as OpenFileSyncOptions)
          if (this.logAble && isTest) {
            this.log += 'openFileSyncTest success:' + fd + '\n\n'
          }
          if (isTest) {
            this.done = true
          }

          this.fd = fd
          return fd
        } catch (e) {
          if (this.logAble) {
            this.log += 'openFileSyncTest fail:' + JSON.stringify(e) + '\n\n'
          }
          console.log('fail', e, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1223")
          this.done = true
        }
        return ""
      },
      closeSyncTest() {
        console.log('closeSyncTest', " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1229")
        const fileManager = uni.getFileSystemManager()
        try {
          console.log('closeSync', " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1232")
          fileManager.closeSync({
            fd: this.openFileSyncTest('r', false)
          } as CloseSyncOptions)
          if (this.logAble) {
            this.log += 'closeSyncTest success:' + '\n\n'
          }
          this.done = true
          this.closeFileRet = "close:ok"
        } catch (e) {
          if (this.logAble) {
            this.log += 'closeSyncTest fail:' + JSON.stringify(e) + '\n\n'
          }
          console.log('fail', e, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1245")
          this.done = true
        }
      },
      closeTest() {
        const fileManager = uni.getFileSystemManager()
        fileManager.close({
          fd: this.openFileSyncTest('r', false),
          success: (res : FileManagerSuccessResult) => {
            if (this.logAble) {
              this.log += 'closeTest success:' + JSON.stringify(res) + '\n\n'
            }
            this.closeFileRet = res.errMsg
            console.log("success", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1258")
          },
          fail: (res : IUniError) => {
            if (this.logAble) {
              this.log += 'closeTest fail:' + JSON.stringify(res) + '\n\n'
            }
            console.log('fail', res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1264")
            this.lastFailError = new UniError(res.errSubject, res.errCode, res.errMsg)

          },
          complete: (_) => {
            this.done = true
          }

        } as CloseOptions)
      },
      writeTest() {
        const fileManager = uni.getFileSystemManager()
        try {
          fileManager.mkdirSync(`${this.basePath}${this.mkdirFile}`, true)
        } catch (e) {
          console.error(e, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1279")
        }

        fileManager.write({
          fd: this.openFileSyncTest('w+', false),
          data: this.writeData,
          encoding: "utf-8",
          success: (res : WriteResult) => {
            if (this.logAble) {
              this.log += 'writeTest success:' + JSON.stringify(res) + '\n\n'
            }
            console.log("success", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1290")
            this.bytesWritten = res.bytesWritten
            this.lastFailError = new UniError('uni-fileSystemManager', 0, 'writeTest success:' + JSON.stringify(res))
          },
          fail: (res : IUniError) => {
            if (this.logAble) {
              this.log += 'writeTest fail:' + JSON.stringify(res) + '\n\n'
            }
            console.log('fail', res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1298")
            this.lastFailError = new UniError(res.errSubject, res.errCode, 'writeTest:' + res.errMsg)

          },
          complete: (_) => {
            this.done = true
          }

        } as WriteOptions)
      },
      writeSyncTest() {
        const fileManager = uni.getFileSystemManager()
        try {
          fileManager.mkdirSync(`${this.basePath}${this.mkdirFile}`, true)
        } catch (e) {
          console.error(e, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1313")
        }
        fileManager.open({
          filePath: `${this.basePath}${this.readFile}`,
          flag: "r+",
          success: (res : OpenFileSuccessResult) => {
            console.log("success", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1319")
            if (res.fd.length <= 0) {
              this.done = true
              return
            }
            try {
              let ret = fileManager.writeSync({
                fd: res.fd,
                data: this.writeData,
                encoding: "utf-8"
              } as WriteSyncOptions)
              if (this.logAble) {
                this.log += 'writeSyncTest success:' + JSON.stringify(ret) + '\n\n'
              }
              console.log("success", ret, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1333")
              this.done = true
              this.bytesWritten = ret.bytesWritten
            } catch (e) {
              if (this.logAble) {
                this.log += 'writeSyncTest fail:' + JSON.stringify(e) + '\n\n'
              }
              console.log('fail', e, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1340")
              this.done = true
            }
          },
          fail: (res : IUniError) => {
            if (this.logAble) {
              this.log += 'openFileTest fail:' + JSON.stringify(res) + '\n\n'
            }
            console.log('fail', res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1348")
            this.lastFailError = new UniError(res.errSubject, res.errCode, res.errMsg)

            this.done = true
          }
        } as OpenFileOptions)

      },
      fstatTest() {
        const fileManager = uni.getFileSystemManager()
        fileManager.fstat({
          fd: this.openFileSyncTest('r', false),
          success: (res : FStatSuccessResult) => {
            if (this.logAble) {
              this.log += 'fstatTest success:' + JSON.stringify(res) + '\n\n'
            }
            console.log("success", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1364")
            this.fstat = res.stats
            this.fstatSize = res.stats.size
          },
          fail: (res : IUniError) => {
            if (this.logAble) {
              this.log += 'fstatTest fail:' + JSON.stringify(res) + '\n\n'
            }
            console.log('fail', res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1372")
            this.lastFailError = new UniError(res.errSubject, res.errCode, res.errMsg)

          },
          complete: (_) => {
            this.done = true
          }

        } as FStatOptions)
      },
      fstatSyncTest() {
        const fileManager = uni.getFileSystemManager()
        try {
          let stat =
            fileManager.fstatSync({
              fd: this.openFileSyncTest('r', false),
            } as FStatSyncOptions)
          if (this.logAble) {
            this.log += 'fstatSyncTest success:' + JSON.stringify(stat) + '\n\n'
          }
          this.done = true
          this.fstat = stat
          this.fstatSize = stat.size
        } catch (e) {
          if (this.logAble) {
            this.log += 'fstatSyncTest fail:' + JSON.stringify(e) + '\n\n'
          }
          this.done = true
        }
      },
      ftruncateFileTest() {
        const fileManager = uni.getFileSystemManager()
        fileManager.ftruncate({
          fd: this.openFileSyncTest('r+', false),
          length: 6,
          success: (res : FileManagerSuccessResult) => {
            if (this.logAble) {
              this.log += 'ftruncateFileTest success:' + JSON.stringify(res) + '\n\n'
            }
            this.ftruncateRet = res.errMsg
          },
          fail: (res : IUniError) => {
            if (this.logAble) {
              this.log += 'ftruncateFileTest fail:' + JSON.stringify(res) + '\n\n'
            }
            console.log('fail', res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1417")
            this.lastFailError = new UniError(res.errSubject, res.errCode, res.errMsg)

          },
          complete: (res : any) => {
            console.log("complete", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1422")
            this.done = true
            if (res instanceof UniError) {
              this.lastCompleteError = res
            }
          }
        } as FTruncateFileOptions)
      },
      ftruncateFileSyncTest() {
        const fileManager = uni.getFileSystemManager()
        try {
          fileManager.ftruncateSync({
            fd: this.openFileSyncTest('r+', false),
            length: 4
          } as FTruncateFileSyncOptions)
          if (this.logAble) {
            this.log += 'ftruncateFileSyncTest success:' + '\n\n'
          }
          this.done = true
          this.ftruncateRet = 'ftruncate:ok'
        } catch (e) {
          if (this.logAble) {
            this.log += 'ftruncateFileSyncTest fail:' + JSON.stringify(e) + '\n\n'
          }
          this.done = true
        }
      },
      readZipEntry() {
        const fileManager = uni.getFileSystemManager()
        fileManager.readZipEntry({
          filePath: '/static/filemanager/to.zip',
          encoding: 'utf-8',
          success: (res : EntriesResult) => {
            if (this.logAble) {
              this.log += 'readZipEntry success:size=' + res.result + '\n\n'
            }
            console.log("success", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1458")
          },
          fail: (res : IUniError) => {
            if (this.logAble) {
              this.log += 'readZipEntry fail:' + JSON.stringify(res) + '\n\n'
            }
            console.log('fail', res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1464")
            this.lastFailError = new UniError(res.errSubject, res.errCode, res.errMsg)

          }
        } as ReadZipEntryOptions)
      },
      testWriteReadFileBuffer() {
        const fs = uni.getFileSystemManager()
        let buffer = new ArrayBuffer(16)
        let float64 = new Float64Array(buffer)
        float64[1] = 1.2222222
        let that = this
        fs.writeFile({
          filePath: `${uni.env.USER_DATA_PATH}/hello.txt`,
          // data: 'test some',
          data: buffer,
          // encoding: 'ascii',
          success: (res) => {
            if (this.logAble) {
              this.log += 'testWriteReadFileBuffer ：' + res.errMsg + '\n\n'
            }
            console.log(res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1485")
            // that.testAppendFile()
            that.testReadFileBuffer()
          },
          fail: (res) => {
            if (this.logAble) {
              this.log += 'testWriteReadFileBuffer fail:' + JSON.stringify(res) + '\n\n'
            }
            this.lastFailError = new UniError(res.errSubject, res.errCode, res.errMsg)

            console.error(res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1495")
            this.done = true
          },
          complete: (_ : any) => {

          }
        } as WriteFileOptions)
      },
      testReadFileBuffer() {
        const fs = uni.getFileSystemManager()
        fs.readFile({
          filePath: `${uni.env.USER_DATA_PATH}/hello.txt`,

          success: (res) => {

            console.log(res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1510")
            // let uint8Array = new Uint8Array(res.data as ArrayBuffer)
            // for (let i = 0; i < uint8Array.length; i++) {
            // 	console.log(`Byte ${i}: ${uint8Array[i]}`);
            // }
            let float64 = new Float64Array(res.data as ArrayBuffer)
            if (this.logAble) {
              for (let i = 0; i < float64.length; i++) {
                if (this.logAble) {
                  this.log += 'testReadFileBuffer success ：' + `Byte ${i}: ${float64[i]}` + '\n\n'
                }
              }
            }
            this.arrayBufferRes = float64[1] //1.2222222
            console.log(this.arrayBufferRes, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1524")
          },
          fail: (res) => {
            if (this.logAble) {
              this.log += 'testReadFileBuffer fail:' + JSON.stringify(res) + '\n\n'
            }
            this.lastFailError = new UniError(res.errSubject, res.errCode, res.errMsg)
            console.error(res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1531")
          },
          complete: (res : any) => {
            console.log("complete", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1534")
            this.done = true
            if (res instanceof UniError) {
              this.lastCompleteError = res
            }
          }
        } as ReadFileOptions)
      },
      testWriteReadFileSyncBuffer() {
        try {
          const fs = uni.getFileSystemManager()
          let buffer = new ArrayBuffer(16)
          // let int8 = new Int8Array(buffer)
          // int8[1] = 20
          let float64 = new Float64Array(buffer)
          float64[1] = 1.333
          fs.writeFileSync(`${uni.env.USER_DATA_PATH}/hello.txt`, buffer, 'utf-8')
          if (this.logAble) {
            this.log += 'testWriteReadFileSyncBuffer success' + '\n\n'
          }
          this.testReadFileSyncBuffer()
          this.done = true
        } catch (e) {
          if (this.logAble) {
            this.log += 'testWriteReadFileSyncBuffer fail:' + JSON.stringify(e) + '\n\n'
          }
          this.done = true
        }

      },
      testReadFileSyncBuffer() {
        try {
          const fs = uni.getFileSystemManager()
          let res = fs.readFileSync(`${uni.env.USER_DATA_PATH}/hello.txt`, null)
          console.log(res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1568")
          let float64 = new Float64Array(res as ArrayBuffer)
          for (let i = 0; i < float64.length; i++) {
            if (this.logAble) {
              this.log += 'testReadFileSyncBuffer success:' + `Byte ${i}: ${float64[i]}` + '\n\n'
            }
            console.log(`Byte ${i}: ${float64[i]}`, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1574");
          }
          this.arrayBufferRes = float64[1] // 1.333
          console.log(this.arrayBufferRes, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1577")
        } catch (e) {
          if (this.logAble) {
            this.log += 'testReadFileSyncBuffer fail:' + JSON.stringify(e) + '\n\n'
          }
          this.done = true
        }

      },

      testWriteReadBuffer() {
        const fileManager = uni.getFileSystemManager()
        try {
          fileManager.mkdirSync(`${this.basePath}${this.mkdirFile}`, true)
        } catch (e) {
          console.error(e, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1592")
        }

        let buffer = new ArrayBuffer(24)
        let float64 = new Float64Array(buffer)
        float64[1] = 1.24
        float64[2] = 1.33

        fileManager.write({
          fd: this.openFileSyncTest('w+', false),
          length: 16,
          offset: 8,
          data: buffer,
          position: 10,
          success: (res : WriteResult) => {
            console.log("success", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1607")

            const ab = new ArrayBuffer(24)
            fileManager.read({
              arrayBuffer: ab,
              fd: this.openFileSyncTest('a+', false),
              length: 8,
              position: 10,
              success: (res : ReadSuccessCallbackResult) => {
                console.log(res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1616")
                let value = new Float64Array(res.arrayBuffer)
                for (let i = 0; i < value.length; i++) {
                  console.log(`Byte ${i}: ${value[i]}`, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1619");
                  if (this.logAble) {
                    this.log += 'testWriteReadBuffer success:' + `Byte ${i}: ${value[i]}` + '\n\n'
                  }
                }
                this.arrayBufferRes = value[0] //1.24
                console.log(this.arrayBufferRes, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1625")
              },
              fail: (res) => {
                console.log(res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1628")
                this.done = true
                if (res instanceof UniError) {
                  this.lastCompleteError = res
                }
              },
              complete: (res : any) => {
                console.log("complete", res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1635")
                this.done = true
                if (res instanceof UniError) {
                  this.lastCompleteError = res
                }
              }
            } as ReadOption)
          },
          fail: (res : IUniError) => {
            this.done = true
            if (res instanceof UniError) {
              this.lastCompleteError = res
            }
          },
          complete: (_) => {

          }

        } as WriteOptions)
      },
      testWriteReadSyncBuffer() {
        const fileManager = uni.getFileSystemManager()
        try {
          fileManager.mkdirSync(`${this.basePath}${this.mkdirFile}`, true)
        } catch (e) {
        }
        try {

          let buffer = new ArrayBuffer(24)
          let float64 = new Float64Array(buffer)
          float64[1] = 1.11
          float64[2] = 1.33
          let ret = fileManager.writeSync({
            fd: this.openFileSyncTest('w+', false),
            // data: this.writeData,
            data: buffer,
            length: 16,
            offset: 8,
            position: 10,
            encoding: "utf-8"
          } as WriteSyncOptions)
          console.log("success", ret, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1676")

          const ab = new ArrayBuffer(24)
          let res = fileManager.readSync({
            arrayBuffer: ab,
            fd: this.openFileSyncTest('a+', false),
            length: 8,
            offset: 8,
            position: 10
          } as ReadSyncOption)
          console.log(res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1686")
          let value = new Float64Array(res.arrayBuffer)
          for (let i = 0; i < value.length; i++) {
            if (this.logAble) {
              this.log += 'testWriteReadSyncBuffer success ：' + `Byte ${i}: ${value[i]}` + '\n\n'
            }
          }
          this.done = true
          this.arrayBufferRes = float64[1] //1.11
          console.log(this.arrayBufferRes, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1695")
        } catch (e) {
          this.done = true
          console.error(e, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1698")
        }
      },


      testAppendFileBufferSync() {
        // 同步接口
        const fs = uni.getFileSystemManager()
        try {
          let buffer = new ArrayBuffer(24)
          let float64 = new Float64Array(buffer)
          float64[1] = 1.2222222
          float64[2] = 1.33
          let ret = fs.writeSync({
            fd: fs.openSync({ filePath: `${uni.env.USER_DATA_PATH}/hello.txt`, flag: 'w+' } as OpenFileSyncOptions),
            data: buffer,
            length: 16,
            offset: 8,
            position: 10
          } as WriteSyncOptions)
          console.log(ret, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1718")


          buffer = new ArrayBuffer(16)
          float64 = new Float64Array(buffer)
          float64[0] = 20
          fs.appendFileSync(`${uni.env.USER_DATA_PATH}/hello.txt`, buffer, null)
          const ab = new ArrayBuffer(32)
          // 打开文件
          // 读取文件到 ArrayBuffer 中
          let readResult = fs.readSync({
            fd: fs.openSync({
              filePath: `${uni.env.USER_DATA_PATH}/hello.txt`,
              flag: 'a+',
            } as OpenFileSyncOptions),
            arrayBuffer: ab,
            length: 24,
            position: 10,
            offset: 8
          } as ReadSyncOption)
          console.log(readResult, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1738")
          float64 = new Float64Array(readResult.arrayBuffer)

          for (let i = 0; i < float64.length; i++) {
            if (this.logAble) {
              this.log += 'testAppendFileBufferSync success ：' + `Byte ${i}: ${float64[i]}` + '\n\n'
            }
            console.log(`Byte ${i}: ${float64[i]}`, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1745");
          }
          this.arrayBufferRes = float64[3] //20
          console.log(this.arrayBufferRes, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1748")
          this.done = true
        } catch (e) {
          this.done = true
          if (this.logAble) {
            this.log += 'testAppendFileBufferSync fail:' + JSON.stringify(e) + '\n\n'
          }
          console.error(e, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1755")
        }
      },

      testAppendFileBuffer() {
        // 同步接口
        const fs = uni.getFileSystemManager()
        try {
          let buffer = new ArrayBuffer(24)
          let float64 = new Float64Array(buffer)
          float64[1] = 1.2222222
          float64[2] = 1.33
          let ret = fs.writeSync({
            fd: fs.openSync({ filePath: `${uni.env.USER_DATA_PATH}/hello.txt`, flag: 'w+' } as OpenFileSyncOptions),
            data: buffer,
            length: 16,
            offset: 8,
            position: 10
          } as WriteSyncOptions)
          console.log(ret, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1774")

          buffer = new ArrayBuffer(16)
          float64 = new Float64Array(buffer)
          float64[0] = 21
          fs.appendFile({
            filePath: `${uni.env.USER_DATA_PATH}/hello.txt`,
            data: buffer,
            success: (res) => {
              console.log(res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1783")
              const ab = new ArrayBuffer(32)
              // 打开文件
              // 读取文件到 ArrayBuffer 中
              let readResult = fs.readSync({
                fd: fs.openSync({
                  filePath: `${uni.env.USER_DATA_PATH}/hello.txt`,
                  flag: 'a+',
                } as OpenFileSyncOptions),
                arrayBuffer: ab,
                length: 24,
                position: 10,
                offset: 8
              } as ReadSyncOption)
              console.log(readResult, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1797")
              float64 = new Float64Array(readResult.arrayBuffer)

              for (let i = 0; i < float64.length; i++) {
                console.log(`Byte ${i}: ${float64[i]}`, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1801");
                if (this.logAble) {
                  this.log += 'testAppendFileBuffer success ：' + `Byte ${i}: ${float64[i]}` + '\n\n'
                }
              }
              this.arrayBufferRes = float64[3] //21
              console.log(this.arrayBufferRes, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1807")
            },
            fail: (res) => {
              console.log(res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1810")
            },
            complete: (_) => {
              this.done = true
            }
          } as AppendFileOptions)

        } catch (e) {
          this.done = true
          if (this.logAble) {
            this.log += 'testAppendFileBufferSync fail:' + JSON.stringify(e) + '\n\n'
          }
          console.error(e, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1822")
        }
      },

      testReadFileEncoding(encoding : string) {
        console.log('testEncoding', encoding, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1827")
        uni.chooseImage({
          count: 1,
          albumMode: "system",
          sizeType: ["original"],
          sourceType: ["album"],
          success: (e) => {
            console.log(e.tempFilePaths[0], " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1834")
            uni.getFileSystemManager().readFile({
              filePath: e.tempFilePaths[0],
              encoding: encoding,
              success: (res) => {
                console.log('success:', (res.data as string).length, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1839")
              }
            })
          }
        })
      },
      testReadFileArrayBuffer() {
        uni.chooseImage({
          count: 1,
          albumMode: "system",
          sizeType: ["original"],
          sourceType: ["album"],
          success: (e) => {
            console.log(e.tempFilePaths[0], " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1852")
            uni.getFileSystemManager().readFile({
              filePath: e.tempFilePaths[0],
              success: (res) => {
                console.log('success:', (res.data as ArrayBuffer).byteLength, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1856")
              }
            })
          }
        })
      },
      testReadFileSyncEncoding(encoding : string) {
        console.log('testEncoding', encoding, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1863")
        uni.chooseImage({
          count: 1,
          albumMode: "system",
          sizeType: ["original"],
          sourceType: ["album"],
          success: (e) => {
            console.log(e.tempFilePaths[0], " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1870")
            try {
              let res = uni.getFileSystemManager().readFileSync(e.tempFilePaths[0], encoding)
              console.log('success:', (res as string).length, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1873")
            } catch (e) {
              console.log(e, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1875")
            }
          }
        })
      },
      testReadFileSyncArrayBuffer() {
        uni.chooseImage({
          count: 1,
          albumMode: "system",
          sizeType: ["original"],
          sourceType: ["album"],
          success: (e) => {
            console.log(e.tempFilePaths[0], " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1887")
            try {
              let res = uni.getFileSystemManager().readFileSync(e.tempFilePaths[0], null)
              console.log('success:', (res as ArrayBuffer).byteLength, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1890")
            } catch (e) {
              console.log(e, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1892")
            }
          }
        })
      },
      copyFileByContent() {
        uni.chooseImage({
          count: 1,
          albumMode: "system",
          sizeType: ["original"],
          sourceType: ["album"],
          success: (e) => {
            console.log(e.tempFilePaths[0], " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1904")
            uni.getFileSystemManager().copyFile({
              srcPath: e.tempFilePaths[0],
              destPath: uni.env.CACHE_PATH + 'copyFileByContent.jpg',
              complete: (res) => {
                console.log('success:', res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1909")
              }
            })
          }
        })
      },
      copyFileSyncByContent() {
        uni.chooseImage({
          count: 1,
          albumMode: "system",
          sizeType: ["original"],
          sourceType: ["album"],
          success: (e) => {
            console.log(e.tempFilePaths[0], " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1922")
            try {
              let res = uni.getFileSystemManager().copyFileSync(e.tempFilePaths[0], uni.env.CACHE_PATH + 'copyFileSyncByContent.jpg',)
              console.log('success:', res, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1925")
            } catch (e) {
              console.log(e, " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1927")
            }
          }
        })
      },

      gotoExplore() {
        uni.navigateTo({
          url: "/pages/API/get-file-system-manager/filemanage"
        })
      },
      gotoTestStatic() {
        uni.navigateTo({
          url: "/pages/API/get-file-system-manager/testStatic"
        })
      },
      getFileInfoByContent(event : UniPointerEvent){
          uni.chooseFile({
            count:1,
            success(e){
              console.log(JSON.stringify(e.tempFiles[0].path), " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1947")
              uni.getFileSystemManager().getFileInfo({
                filePath:e.tempFiles[0].path,
                success(e2) {
                  console.log('success:', (e2), " at pages/API/get-file-system-manager/get-file-system-manager.uvue:1951")
                }
              })
            }
          })
      }
    },


  })

export default __sfc__
function GenPagesAPIGetFileSystemManagerGetFileSystemManagerRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
  return createElementVNode(Fragment, null, [
    createElementVNode("text", null, "显示简易操作日志(可滚动查看),详细日志需真机运行查看"),
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
        onClick: _ctx.statFileInfoTest,
        id: "btn-stat-file"
      }), "递归获取目录files的Stats对象" + toDisplayString(_ctx.statFile), 9 /* TEXT, PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({
        class: "btnstyle",
        type: "primary",
        onClick: _ctx.mkdirTest,
        id: "btn-mkdir"
      }), "创建文件夹" + toDisplayString(_ctx.mkdirFile), 9 /* TEXT, PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({
        class: "btnstyle",
        type: "primary",
        onClick: _ctx.writeFileTest,
        id: "btn-write-file"
      }), "覆盖写入文件" + toDisplayString(_ctx.writeFile), 9 /* TEXT, PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({
        class: "btnstyle",
        type: "primary",
        onClick: _ctx.readDirTest,
        id: "btn-read-dir"
      }), "读取文件夹" + toDisplayString(_ctx.readDir), 9 /* TEXT, PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({
        class: "btnstyle",
        type: "primary",
        onClick: _ctx.readFileTest,
        id: "btn-read-file"
      }), "读取文件" + toDisplayString(_ctx.readFile), 9 /* TEXT, PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({
        class: "btnstyle",
        type: "primary",
        onClick: _ctx.copyFileTest,
        id: "btn-copy-file"
      }), "复制文件" + toDisplayString(_ctx.copyFromFile) + "到" + toDisplayString(_ctx.copyToFile), 9 /* TEXT, PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({
        class: "btnstyle",
        type: "primary",
        onClick: _ctx.renameFileTest,
        id: "btn-rename-file"
      }), "重命名文件" + toDisplayString(_ctx.renameFromFile) + "到" + toDisplayString(_ctx.renameToFile), 9 /* TEXT, PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({
        class: "btnstyle",
        type: "primary",
        onClick: _ctx.accessFileTest,
        id: "btn-access-file"
      }), "判断文件" + toDisplayString(_ctx.accessFile) + "是否存在", 9 /* TEXT, PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({
        class: "btnstyle",
        type: "primary",
        onClick: _ctx.getFileInfoTest,
        id: "btn-get-file-info"
      }), "获取文件信息" + toDisplayString(_ctx.getFileInfoFile), 9 /* TEXT, PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({
        class: "btnstyle",
        type: "primary",
        onClick: _ctx.unlinkTest,
        id: "btn-unlink-file"
      }), "删除文件" + toDisplayString(_ctx.unlinkFile), 9 /* TEXT, PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({
        class: "btnstyle",
        type: "primary",
        onClick: _ctx.unlinkAllFileTest,
        id: "btn-clear-file"
      }), "删除文件夹" + toDisplayString(_ctx.rmDirFile) + "下的所有文件", 9 /* TEXT, PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({
        class: "btnstyle",
        type: "primary",
        onClick: _ctx.rmdirTest,
        id: "btn-remove-dir"
      }), "删除文件夹" + toDisplayString(_ctx.rmDirFile), 9 /* TEXT, PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({
        class: "btnstyle",
        type: "primary",
        onClick: _ctx.statFileInfoSyncTest,
        id: "btn-stat-file-sync"
      }), "同步递归获取目录files的Stats对象" + toDisplayString(_ctx.statFile), 9 /* TEXT, PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({
        class: "btnstyle",
        type: "primary",
        onClick: _ctx.appendFileTest,
        id: "btn-append-file"
      }), "在文件" + toDisplayString(_ctx.readFile) + "结尾追加内容", 9 /* TEXT, PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({
        class: "btnstyle",
        type: "primary",
        onClick: _ctx.appendFileSyncTest,
        id: "btn-append-file-sync"
      }), "同步在文件" + toDisplayString(_ctx.readFile) + "结尾追加内容", 9 /* TEXT, PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({
        class: "btnstyle",
        type: "primary",
        onClick: _ctx.writeFileSyncTest,
        id: "btn-write-file-sync"
      }), "同步覆盖写入文件" + toDisplayString(_ctx.writeFile), 9 /* TEXT, PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({
        class: "btnstyle",
        type: "primary",
        onClick: _ctx.readFileSyncTest,
        id: "btn-read-file-sync"
      }), "同步读取文件" + toDisplayString(_ctx.readFile), 9 /* TEXT, PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({
        class: "btnstyle",
        type: "primary",
        onClick: _ctx.unlinkSyncTest,
        id: "btn-unlink-file-sync"
      }), "同步删除文件" + toDisplayString(_ctx.unlinkFile), 9 /* TEXT, PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({
        class: "btnstyle",
        type: "primary",
        onClick: _ctx.mkdirSyncTest,
        id: "btn-mkdir-sync"
      }), "同步创建文件夹" + toDisplayString(_ctx.mkdirFile), 9 /* TEXT, PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({
        class: "btnstyle",
        type: "primary",
        onClick: _ctx.rmdirSyncTest,
        id: "btn-remove-dir-sync"
      }), "同步删除文件夹" + toDisplayString(_ctx.rmDirFile), 9 /* TEXT, PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({
        class: "btnstyle",
        type: "primary",
        onClick: _ctx.readDirSyncTest,
        id: "btn-read-dir-sync"
      }), "同步读取文件夹" + toDisplayString(_ctx.readDir), 9 /* TEXT, PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({
        class: "btnstyle",
        type: "primary",
        onClick: _ctx.accessFileSyncTest,
        id: "btn-access-file-sync"
      }), "同步判断文件" + toDisplayString(_ctx.accessFile) + "是否存在", 9 /* TEXT, PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({
        class: "btnstyle",
        type: "primary",
        onClick: _ctx.renameFileSync,
        id: "btn-rename-file-sync"
      }), "同步重命名文件" + toDisplayString(_ctx.renameFromFile) + "到" + toDisplayString(_ctx.renameToFile), 9 /* TEXT, PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({
        class: "btnstyle",
        type: "primary",
        onClick: _ctx.copyFileSyncTest,
        id: "btn-copy-file-sync"
      }), "同步复制文件" + toDisplayString(_ctx.copyFromFile) + "到" + toDisplayString(_ctx.copyToFile), 9 /* TEXT, PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({
        class: "btnstyle",
        type: "primary",
        onClick: _ctx.removeSavedFileTest,
        id: "btn-remove-saved-file"
      }), "删除已保存的本地文件", 8 /* PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({
        class: "btnstyle",
        type: "primary",
        onClick: _ctx.getSavedFileListTest,
        id: "btn-getsaved-filelist"
      }), "获取该已保存的本地缓存文件列表", 8 /* PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({
        class: "btnstyle",
        type: "primary",
        onClick: _ctx.truncateFileTest,
        id: "btn-truncate-file"
      }), "对文件" + toDisplayString(_ctx.writeFile) + "内容进行截断操作", 9 /* TEXT, PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({
        class: "btnstyle",
        type: "primary",
        onClick: _ctx.openFileTest,
        id: "btn-open-file"
      }), "打开文件" + toDisplayString(_ctx.readFile) + "，返回描述符", 9 /* TEXT, PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({
        class: "btnstyle",
        type: "primary",
        onClick: () => {_ctx.openFileSyncTest('r',true)},
        id: "btn-open-file-sync"
      }), "同步打开文件" + toDisplayString(_ctx.readFile) + "，返回描述符", 9 /* TEXT, PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({
        class: "btnstyle",
        type: "primary",
        onClick: _ctx.closeTest,
        id: "btn-close-file"
      }), "通过文件描述符关闭文件" + toDisplayString(_ctx.readFile), 9 /* TEXT, PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({
        class: "btnstyle",
        type: "primary",
        onClick: _ctx.closeSyncTest,
        id: "btn-close-file-sync"
      }), "通过文件描述符同步关闭文件" + toDisplayString(_ctx.readFile), 9 /* TEXT, PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({
        class: "btnstyle",
        type: "primary",
        onClick: _ctx.writeTest,
        id: "btn-write"
      }), "通过文件描述符写入文件" + toDisplayString(_ctx.readFile), 9 /* TEXT, PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({
        class: "btnstyle",
        type: "primary",
        onClick: _ctx.writeSyncTest,
        id: "btn-write-sync"
      }), "同步通过文件描述符写入文件" + toDisplayString(_ctx.readFile), 9 /* TEXT, PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({
        class: "btnstyle",
        type: "primary",
        onClick: _ctx.fstatTest,
        id: "btn-fstat-file"
      }), "通过文件描述符获取" + toDisplayString(_ctx.statFile) + "的状态信息", 9 /* TEXT, PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({
        class: "btnstyle",
        type: "primary",
        onClick: _ctx.fstatSyncTest,
        id: "btn-fstat-file-sync"
      }), "同步通过文件描述符获取" + toDisplayString(_ctx.statFile) + "的状态信息", 9 /* TEXT, PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({
        class: "btnstyle",
        type: "primary",
        onClick: _ctx.ftruncateFileTest,
        id: "btn-ftruncate-file"
      }), "通过文件描述符对文件" + toDisplayString(_ctx.writeFile) + "内容进行截断", 9 /* TEXT, PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({
        class: "btnstyle",
        type: "primary",
        onClick: _ctx.ftruncateFileSyncTest,
        id: "btn-ftruncate-file-sync"
      }), "同步通过文件描述符对文件" + toDisplayString(_ctx.writeFile) + "内容进行截断", 9 /* TEXT, PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({
        class: "btnstyle",
        type: "primary",
        onClick: _ctx.testWriteReadFileBuffer,
        id: "btn-writereadfile-buffer"
      }), "写入/读取 ArrayBuffer", 8 /* PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({
        class: "btnstyle",
        type: "primary",
        onClick: _ctx.testWriteReadBuffer,
        id: "btn-writeread-buffer"
      }), "通过文件描述符写入/读取 ArrayBuffer", 8 /* PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({
        class: "btnstyle",
        type: "primary",
        onClick: _ctx.testWriteReadSyncBuffer,
        id: "btn-writereadsync-buffer"
      }), "通过文件描述符同步写入/读取 ArrayBuffer", 8 /* PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({
        class: "btnstyle",
        type: "primary",
        onClick: _ctx.testAppendFileBuffer,
        id: "btn-appendfile-buffer"
      }), "在文件末尾追加 ArrayBuffer", 8 /* PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({
        class: "btnstyle",
        type: "primary",
        onClick: _ctx.testAppendFileBufferSync,
        id: "btn-appendfilesync-buffer"
      }), "同步在文件末尾追加 ArrayBuffer", 8 /* PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({
        class: "btnstyle",
        type: "primary",
        onClick: _ctx.copyStaticToFilesTest,
        id: "btn-copyStatic-file"
      }), "从static目录复制文件到a目录", 8 /* PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({
        class: "btnstyle",
        type: "primary",
        onClick: _ctx.saveFileTest,
        id: "btn-save-file"
      }), "保存临时文件到本地, filePath=null", 8 /* PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({
        class: "btnstyle",
        type: "primary",
        onClick: _ctx.saveFileTest1,
        id: "btn-save-file1"
      }), "保存临时文件到本地, filePath=xxx/path.txt", 8 /* PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({
        class: "btnstyle",
        type: "primary",
        onClick: _ctx.saveFileTest2,
        id: "btn-save-file2"
      }), "保存临时文件到本地, filePath=xxx/path", 8 /* PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({
        class: "btnstyle",
        type: "primary",
        onClick: _ctx.saveFileTest3,
        id: "btn-save-file3"
      }), "保存临时文件到本地, filePath=xxx/path/", 8 /* PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({
        class: "btnstyle",
        type: "primary",
        onClick: _ctx.saveFileSyncTest,
        id: "btn-save-file-sync"
      }), "同步保存临时文件到本地", 8 /* PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({
        class: "btnstyle",
        type: "primary",
        onClick: _ctx.unzipFileTest,
        id: "btn-unzip-file-sync"
      }), "解压文件", 8 /* PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({
        class: "btnstyle",
        type: "primary",
        onClick: _ctx.truncateFileSyncTest,
        id: "btn-truncate-file-sync"
      }), "同步对文件" + toDisplayString(_ctx.writeFile) + "内容进行截断操作", 9 /* TEXT, PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({
        class: "btnstyle",
        type: "primary",
        onClick: _ctx.readCompressedFileTest,
        id: "btn-compressed-file"
      }), "读取指定压缩类型的本地文件内容", 8 /* PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({
        class: "btnstyle",
        type: "primary",
        onClick: _ctx.readCompressedFileSyncTest,
        id: "btn-compressed-file-sync"
      }), "同步读取指定压缩类型的本地文件内容", 8 /* PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({
        class: "btnstyle",
        type: "primary",
        onClick: _ctx.readZipEntry,
        id: "btn-readzip-entry"
      }), "读取压缩包内的文件", 8 /* PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({
        class: "btnstyle",
        type: "primary",
        onClick: _ctx.testWriteReadFileSyncBuffer,
        id: "btn-writereadfilesync-buffer"
      }), "同步写入/读取 ArrayBuffer", 8 /* PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({
        class: "btnstyle",
        type: "primary",
        onClick: () => {_ctx.testReadFileEncoding('base64')}
      }), "readFile(content://base64)", 8 /* PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({
        class: "btnstyle",
        type: "primary",
        onClick: () => {_ctx.testReadFileEncoding('utf-8')}
      }), "readFile(content://utf-8)", 8 /* PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({
        class: "btnstyle",
        type: "primary",
        onClick: () => {_ctx.testReadFileEncoding('ascii')}
      }), "readFile(content://ascii)", 8 /* PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({
        class: "btnstyle",
        type: "primary",
        onClick: () => {_ctx.testReadFileArrayBuffer()}
      }), "readFile(content://arraybuffer)", 8 /* PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({
        class: "btnstyle",
        type: "primary",
        onClick: () => {_ctx.testReadFileSyncEncoding('base64')}
      }), "readFileSync(content://base64)", 8 /* PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({
        class: "btnstyle",
        type: "primary",
        onClick: () => {_ctx.testReadFileSyncEncoding('utf-8')}
      }), "readFileSync(content://utf-8)", 8 /* PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({
        class: "btnstyle",
        type: "primary",
        onClick: () => {_ctx.testReadFileSyncEncoding('ascii')}
      }), "readFileSync(content://ascii)", 8 /* PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({
        class: "btnstyle",
        type: "primary",
        onClick: () => {_ctx.testReadFileSyncArrayBuffer()}
      }), "readFileSync(content://arraybuffer)", 8 /* PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({
        class: "btnstyle",
        type: "primary",
        onClick: () => {_ctx.copyFileByContent()}
      }), "copyFile(content://)", 8 /* PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({
        class: "btnstyle",
        type: "primary",
        onClick: () => {_ctx.copyFileSyncByContent()}
      }), "copyFileSync(content://)", 8 /* PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({
        class: "btnstyle",
        type: "primary",
        onClick: _ctx.getFileInfoByContent
      }), "getFileInfo(content://)", 8 /* PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({
        class: "btnstyle",
        type: "primary",
        onClick: () => {_ctx.gotoExplore()}
      }), "前往沙盒文件管理器", 8 /* PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({
        class: "btnstyle",
        type: "primary",
        onClick: () => {_ctx.gotoTestStatic()}
      }), "前往Static文件测试", 8 /* PROPS */, ["onClick"]),
      createElementVNode("view", utsMapOf({
        style: normalizeStyle(utsMapOf({"height":"4px"}))
      }), null, 4 /* STYLE */)
    ], 4 /* STYLE */)
  ], 64 /* STABLE_FRAGMENT */)
}
const GenPagesAPIGetFileSystemManagerGetFileSystemManagerStyles = [utsMapOf([["btnstyle", padStyleMapOf(utsMapOf([["marginTop", 4], ["marginRight", 4], ["marginBottom", 4], ["marginLeft", 4]]))]])]
