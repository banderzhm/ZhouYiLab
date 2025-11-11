function checkPlatform() {
































}

/**
 * 获取当前H5所在的环境
 */
function getH5Env() {




















}

// json2的属性全部赋值给json1
function objectAssign(json1:UTSJSONObject,json2:UTSJSONObject) :UTSJSONObject{
	for(let key in json2) {
		json1[key] = json2[key];
	}
	return json1;
}

function getWeixinCode () : Promise<string>{
	return new Promise((resolve, reject) => {












		resolve('')

	})
};

function getAlipayCode () : Promise<string>{
	return new Promise((resolve, reject) => {












		resolve('');

	});

};

export {
	checkPlatform,
	getH5Env,
	objectAssign,
	getWeixinCode,
	getAlipayCode
};
