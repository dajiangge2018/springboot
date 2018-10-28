window.app{
	/**
	 * 后端发布的url地址
	 */
	serverUrl:"http://192.168.1.5:8080",
	/**
	 * 判断是否为空：ture-不为空；false-为空
	 */
	isNotNull = function(str){
		if(str!=null && str!="" && str =!undefined){
			return true;
		}else{
			return false;
		}
	},
	showToast = function(msg,type){
		plus.nativeUI.toast(msg,{icon:"image/"+ type + ".png",verticalAlign:"center"})
	},
	setUserGlobalInfo = function(user){
		//将用户信息对象转换为json字符串
		var userInfoStr = Json.stringify(user);
		plus.storage.setItem("userInfo",userInfoStr);
		
	},
	getUserGlobaInfo = function(){
		var userInfoStr = plus.storage.getItem("userInfo");
		return userInfoStr;
	}
	
}