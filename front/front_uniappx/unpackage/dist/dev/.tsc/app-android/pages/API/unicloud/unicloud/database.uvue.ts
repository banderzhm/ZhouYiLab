
  const __sfc__ = defineComponent({
    data() {
      return {
        title: 'ClientDB',
        addId: '',
        batchAddIds: [] as Array<string>,
        batchAddinserted: 0,
        updateUpdated: 0,
        getData: [] as Array<UTSJSONObject>,
        getWithCommandData: [] as Array<UTSJSONObject>,
        removeDeleted: 0,
        lookupData: [] as Array<UTSJSONObject>,
        multiSendSuccessCount: 0,
        isUniTest: false
      }
    },
    computed: {
      dataTag() : string {
        return this.isUniTest ? 'default-tag' + Date.now() : 'default-tag'
      }
    },
    methods: {
      notify(content : string, title : string) {
        if (!this.isUniTest) {
          uni.showModal({
            title,
            content,
            showCancel: false
          })
        } else {
          console.log(title, content, " at pages/API/unicloud/unicloud/database.uvue:57")
        }
      },
      async dbAdd() : Promise<void> {
        uni.showLoading({
          title: '加载中...'
        })
        const db = uniCloud.databaseForJQL()
        await db.collection('type')
          .add({
            num: 1,
            tag: this.dataTag,
            date: new Date()
          })
          .then(res => {
            uni.hideLoading()
            console.log(res, " at pages/API/unicloud/unicloud/database.uvue:73")
            this.addId = res.id
            this.notify(`新增成功，id: ${res.id}`, '提示')
          })
          .catch((err : any | null) => {
            uni.hideLoading()
            const error = err as UniCloudError
            this.notify(error.errMsg, '错误')
          })
      },
      async dbBatchAdd() : Promise<void> {
        uni.showLoading({
          title: '加载中...'
        })
        const db = uniCloud.databaseForJQL()
        await db.collection('type')
          .add([{
            num: 2,
            tag: this.dataTag,
          }, {
            num: 3,
            tag: this.dataTag,
          }])
          .then((res) => {
            uni.hideLoading()
            console.log(res, " at pages/API/unicloud/unicloud/database.uvue:98")
            console.log('JSON.stringify(res.inserted)', JSON.stringify(res.inserted), " at pages/API/unicloud/unicloud/database.uvue:99")
            this.batchAddIds = res.ids
            this.batchAddinserted = res.inserted
            this.notify(`新增成功条数${res.inserted}, id列表: ${res.ids.join(',')}`, '提示')
          })
          .catch((err : any | null) => {
            uni.hideLoading()
            const error = err as UniCloudError
            this.notify(error.errMsg, '错误')
          })
      },
      async dbGet() : Promise<void> {
        uni.showLoading({
          title: '加载中...'
        })
        const db = uniCloud.databaseForJQL()
        await db.collection('type')
          .where(
            `tag == "${this.dataTag}"`
          )
          .field('num, tag')
          .orderBy('num desc')
          .skip(1)
          .limit(2)
          .get()
          .then(res => {
            uni.hideLoading()
            console.log(res, " at pages/API/unicloud/unicloud/database.uvue:126")
            this.getData = res.data
            this.notify(`获取成功，取到了${res.data.length}条数据`, '提示')
          })
          .catch((err : any | null) => {
            uni.hideLoading()
            const error = err as UniCloudError
            this.notify(error.errMsg, '错误')
          })
      },
      async dbGetWithCommand() : Promise<void> {
        uni.showLoading({
          title: '加载中...'
        })
        const db = uniCloud.databaseForJQL()
        await db.collection('type')
          .where({
            num: db.command.gt(1),
            tag: this.dataTag
          })
          .field('num, tag')
          .orderBy('num desc')
          .skip(1)
          .limit(2)
          .get()
          .then(res => {
            uni.hideLoading()
            console.log(res, " at pages/API/unicloud/unicloud/database.uvue:153")
            this.getWithCommandData = res.data
            this.notify(`获取成功，取到了${res.data.length}条数据`, '提示')
          })
          .catch((err : any | null) => {
            uni.hideLoading()
            const error = err as UniCloudError
            this.notify(error.errMsg, '错误')
          })
      },
      async dbUpdate() : Promise<void> {
        uni.showLoading({
          title: '加载中...'
        })
        const db = uniCloud.databaseForJQL()
        await db.collection('type')
          .where(
            `tag == "${this.dataTag}"`
          )
          .update({
            num: 4
          })
          .then(res => {
            uni.hideLoading()
            console.log(res, " at pages/API/unicloud/unicloud/database.uvue:177")
            this.updateUpdated = res.updated
            this.notify(`更新成功，更新了${res.updated}条数据`, '提示')
          })
          .catch((err : any | null) => {
            uni.hideLoading()
            const error = err as UniCloudError
            this.notify(error.errMsg, '错误')
          })
      },
      async dbRemove() : Promise<void> {
        uni.showLoading({
          title: '加载中...'
        })
        const db = uniCloud.databaseForJQL()
        await db.collection('type')
          .where(
            `tag == "${this.dataTag}"`
          )
          .remove()
          .then(res => {
            uni.hideLoading()
            console.log(res, " at pages/API/unicloud/unicloud/database.uvue:199")
            this.removeDeleted = res.deleted
            this.notify(`删除成功，删掉了${res.deleted}条数据`, '提示')
          })
          .catch((err : any | null) => {
            uni.hideLoading()
            const error = err as UniCloudError
            this.notify(error.errMsg, '错误')
          })
      },
      async dbLookupInit() : Promise<void> {
        uni.showLoading({
          title: '加载中...'
        })
        const db = uniCloud.databaseForJQL()
        await db.collection('local')
          .where(`tag == "${this.dataTag}"`)
          .remove()
          .then(() : Promise<UniCloudDBRemoveResult> => {
            return db.collection('foreign')
              .where(`tag == "${this.dataTag}"`)
              .remove()
          })
          .then(() : Promise<UniCloudDBBatchAddResult> => {
            return db.collection('local')
              .add([{
                id: "local_1",
                name: "local_1_name",
                tag: this.dataTag,
                foreign_id: "foreign_1"
              }, {
                id: "local_2",
                name: "local_2_name",
                tag: this.dataTag,
                foreign_id: "foreign_2"
              }])
          })
          .then(() : Promise<UniCloudDBBatchAddResult> => {
            return db.collection('foreign')
              .add([{
                id: "foreign_1",
                name: "foreign_1_name",
                tag: this.dataTag
              }, {
                id: "foreign_2",
                name: "foreign_2_name",
                tag: this.dataTag
              }])
          })
          .then((_) : void => {
            uni.hideLoading()
            this.notify('数据初始化成功', '提示')
          })
          .catch((err : any | null) => {
            uni.hideLoading()
            console.error(err, " at pages/API/unicloud/unicloud/database.uvue:254")
            const error = err as UniCloudError
            this.notify(error.errMsg, '错误')
          })
      },
      async dbLookup() : Promise<void> {
        uni.showLoading({
          title: '加载中...'
        })
        const db = uniCloud.databaseForJQL()
        const local = db.collection('local')
          .where(`tag == "${this.dataTag}"`)
          .getTemp()
        const foreign = db.collection('foreign')
          .where(`tag == "${this.dataTag}"`)
          .getTemp()
        await db.collection(local, foreign)
          .get()
          .then(res => {
            uni.hideLoading()
            console.log(res, " at pages/API/unicloud/unicloud/database.uvue:274")
            this.lookupData = res.data
            this.notify(`联表查询成功，取到了${res.data.length}条数据`, '提示')
          })
          .catch((err : any | null) => {
            uni.hideLoading()
            const error = err as UniCloudError
            this.notify(error.errMsg, '错误')
          })
      },
      async dbMultiSend() : Promise<void> {
        const db = uniCloud.databaseForJQL()
        const temp1 = db.collection('type')
          .where(
            'tag == "default-tag"'
          ).getTemp()
        const temp2 = db.collection('type')
          .where(
            'tag == "default-tag"'
          ).getTemp()
        await db.multiSend(temp1, temp2)
          .then<void>(res => {
            uni.hideLoading()
            let successCount = 0
            for (let i = 0; i < res.dataList.length; i++) {
              const item = res.dataList[i]
              if (item.errCode == 0) {
                console.log(`第${i}个请求查询到${item.data!.length}条数据`, " at pages/API/unicloud/unicloud/database.uvue:301")
                successCount++
              } else {
                console.error(`第${i}个请求查询失败，错误信息：${item.data!.length}`, " at pages/API/unicloud/unicloud/database.uvue:304")
              }
            }
            this.multiSendSuccessCount = successCount
            this.notify(`合并查询成功，成功查询的语句条数为：${successCount}`, '提示')
          })
          .catch<void>((err : any | null) => {
            uni.hideLoading()
            const error = err as UniCloudError
            console.error(err, " at pages/API/unicloud/unicloud/database.uvue:313")
            this.notify(error.errMsg, '错误')
          })
      }
    }
  })

export default __sfc__
function GenPagesAPIUnicloudUnicloudDatabaseRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
const _component_page_head = resolveEasyComponent("page-head",_easycom_page_head)

  return createElementVNode("scroll-view", utsMapOf({ class: "page-scroll-view" }), [
    createElementVNode("view", null, [
      createVNode(_component_page_head, utsMapOf({ title: _ctx.title }), null, 8 /* PROPS */, ["title"]),
      createElementVNode("view", utsMapOf({ class: "uni-padding-wrap uni-common-mt" }), [
        createElementVNode("view", utsMapOf({ class: "uni-btn-v uni-common-mt" }), [
          createElementVNode("button", utsMapOf({
            type: "primary",
            onClick: _ctx.dbAdd
          }), "新增单条数据", 8 /* PROPS */, ["onClick"]),
          createElementVNode("button", utsMapOf({
            type: "primary",
            onClick: _ctx.dbBatchAdd
          }), "新增多条数据", 8 /* PROPS */, ["onClick"]),
          createElementVNode("button", utsMapOf({
            type: "primary",
            onClick: _ctx.dbUpdate
          }), "更新数据", 8 /* PROPS */, ["onClick"]),
          createElementVNode("button", utsMapOf({
            type: "primary",
            onClick: _ctx.dbGet
          }), "where传字符串获取数据", 8 /* PROPS */, ["onClick"]),
          createElementVNode("button", utsMapOf({
            type: "primary",
            onClick: _ctx.dbGetWithCommand
          }), "where传对象获取数据", 8 /* PROPS */, ["onClick"]),
          createElementVNode("button", utsMapOf({
            type: "primary",
            onClick: _ctx.dbRemove
          }), "删除数据", 8 /* PROPS */, ["onClick"]),
          createElementVNode("button", utsMapOf({
            type: "primary",
            onClick: _ctx.dbLookupInit
          }), "初始化联表查询数据", 8 /* PROPS */, ["onClick"]),
          createElementVNode("button", utsMapOf({
            type: "primary",
            onClick: _ctx.dbLookup
          }), "联表查询", 8 /* PROPS */, ["onClick"]),
          createElementVNode("button", utsMapOf({
            type: "primary",
            onClick: _ctx.dbMultiSend
          }), "合并查询查询", 8 /* PROPS */, ["onClick"])
        ])
      ])
    ])
  ])
}
const GenPagesAPIUnicloudUnicloudDatabaseStyles = []

import _easycom_page_head from '@/components/page-head/page-head.vue'
